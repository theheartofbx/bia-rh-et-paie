package org.sitracel.evaluation.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.employe.HRContratService;

/**
 * Processus : Soumettre l'évaluation par l'employé
 *
 * Habilitation : l'employé évalué uniquement (ou RH)
 */
public class SoumettreEmploye extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int evalId = getRecord_ID();
        int adUserId = Env.getAD_User_ID(getCtx());

        // --- Habilitation ---
        int bpartnerConnecte = getBPartnerIdFromUser(adUserId, get_TrxName());
        int bpartnerEvalue = DB.getSQLValueEx(get_TrxName(),
            "SELECT C_BPartner_ID FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if (bpartnerConnecte != bpartnerEvalue) {
            if (!HRContratService.isUserRH(adUserId, get_TrxName())) {
                return "Seul l'employé évalué peut soumettre son auto-évaluation.";
            }
        }

        // --- Vérifications ---
        String isGeneree = DB.getSQLValueString(get_TrxName(),
            "SELECT IsGeneree FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if (!"Y".equals(isGeneree)) {
            return "L'évaluation n'a pas encore été générée.";
        }

        String isSoumise = DB.getSQLValueString(get_TrxName(),
            "SELECT IsSoumiseEmploye FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if ("Y".equals(isSoumise)) {
            return "L'évaluation a déjà été soumise par l'employé.";
        }

        // --- Vérifier qu'au moins 1 score employé est renseigné ---
        int nbScores = DB.getSQLValueEx(get_TrxName(),
            "SELECT COUNT(*) FROM HR_EvalLigne"
            + " WHERE HR_Eval_ID = ? AND IsActive = 'Y'"
            + " AND Score_Employe IS NOT NULL",
            evalId);
        if (nbScores == 0) {
            return "Aucun score n'a été saisi. Renseignez au moins un score avant de soumettre.";
        }

        // --- Soumettre ---
        Timestamp now = new Timestamp(System.currentTimeMillis());
        DB.executeUpdateEx(
            "UPDATE HR_Eval SET IsSoumiseEmploye = 'Y',"
            + " Date_Soumission_Employe = ?, IsRejetee = 'N'"
            + " WHERE HR_Eval_ID = ?",
            new Object[]{ now, evalId }, get_TrxName());

        return "Évaluation soumise par l'employé.";
    }

    private int getBPartnerIdFromUser(int adUserId, String trxName) {
        return DB.getSQLValueEx(trxName,
            "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID = ?", adUserId);
    }
}
