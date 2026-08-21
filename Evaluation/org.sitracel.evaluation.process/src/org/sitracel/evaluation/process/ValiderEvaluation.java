package org.sitracel.evaluation.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.employe.HRContratService;

/**
 * Processus : Valider l'évaluation (par le N+2)
 *
 * Habilitation : le N+2 désigné (Evaluateur_N2_ID) ou RH
 */
public class ValiderEvaluation extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int evalId = getRecord_ID();
        int adUserId = Env.getAD_User_ID(getCtx());

        // --- Habilitation ---
        int bpartnerConnecte = getBPartnerIdFromUser(adUserId, get_TrxName());
        int evaluateurN2 = DB.getSQLValueEx(get_TrxName(),
            "SELECT Evaluateur_N2_ID FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if (bpartnerConnecte != evaluateurN2) {
            if (!HRContratService.isUserRH(adUserId, get_TrxName())) {
                return "Seul le N+2 désigné ou le service RH peut valider l'évaluation.";
            }
        }

        // --- Vérifications ---
        String isSoumiseN1 = DB.getSQLValueString(get_TrxName(),
            "SELECT IsSoumiseN1 FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if (!"Y".equals(isSoumiseN1)) {
            return "Le N+1 n'a pas encore soumis son évaluation.";
        }

        String isValidee = DB.getSQLValueString(get_TrxName(),
            "SELECT IsValidee FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if ("Y".equals(isValidee)) {
            return "L'évaluation est déjà validée.";
        }

        // --- Vérifier que tous les objectifs ont un ScoreFinal ---
        int nbNonEvalues = DB.getSQLValueEx(get_TrxName(),
            "SELECT COUNT(*) FROM HR_EvalLigne"
            + " WHERE HR_Eval_ID = ? AND IsActive = 'Y' AND IsEvalue = 'N'",
            evalId);
        if (nbNonEvalues > 0) {
            return nbNonEvalues + " objectif(s) sans score final. "
                + "Tous les objectifs doivent être évalués avant la validation.";
        }

        // --- Vérifier les objectifs éliminatoires ---
        int nbEliminatoires = DB.getSQLValueEx(get_TrxName(),
            "SELECT COUNT(*) FROM HR_EvalLigne"
            + " WHERE HR_Eval_ID = ? AND IsActive = 'Y'"
            + " AND IsEliminatoire = 'Y'"
            + " AND SeuilEchec IS NOT NULL"
            + " AND ScoreFinal < SeuilEchec",
            evalId);

        // --- Valider ---
        Timestamp now = new Timestamp(System.currentTimeMillis());
        DB.executeUpdateEx(
            "UPDATE HR_Eval SET IsValidee = 'Y', IsRejetee = 'N',"
            + " Date_Validation = ?"
            + " WHERE HR_Eval_ID = ?",
            new Object[]{ now, evalId }, get_TrxName());

        String msg = "Évaluation validée avec succès.";
        if (nbEliminatoires > 0) {
            msg += " ATTENTION : " + nbEliminatoires
                + " objectif(s) éliminatoire(s) en échec.";
        }

        return msg;
    }

    private int getBPartnerIdFromUser(int adUserId, String trxName) {
        return DB.getSQLValueEx(trxName,
            "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID = ?", adUserId);
    }
}
