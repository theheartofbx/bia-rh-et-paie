package org.sitracel.evaluation.process;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.employe.HRContratService;

/**
 * Processus : Renvoyer l'évaluation vers l'employé
 *
 * Habilitation : le N+1 désigné (Evaluateur_N1_ID) ou RH
 */
public class RenvoyerVersEmploye extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int evalId = getRecord_ID();
        int adUserId = Env.getAD_User_ID(getCtx());

        // --- Habilitation ---
        int bpartnerConnecte = getBPartnerIdFromUser(adUserId, get_TrxName());
        int evaluateurN1 = DB.getSQLValueEx(get_TrxName(),
            "SELECT Evaluateur_N1_ID FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if (bpartnerConnecte != evaluateurN1) {
            if (!HRContratService.isUserRH(adUserId, get_TrxName())) {
                return "Seul le N+1 désigné ou le service RH peut renvoyer l'évaluation à l'employé.";
            }
        }

        // --- Vérifications ---
        String isSoumiseEmploye = DB.getSQLValueString(get_TrxName(),
            "SELECT IsSoumiseEmploye FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if (!"Y".equals(isSoumiseEmploye)) {
            return "L'employé n'a pas encore soumis. Rien à renvoyer.";
        }

        String isValidee = DB.getSQLValueString(get_TrxName(),
            "SELECT IsValidee FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if ("Y".equals(isValidee)) {
            return "L'évaluation est déjà validée. Impossible de renvoyer.";
        }

        // --- Renvoyer ---
        DB.executeUpdateEx(
            "UPDATE HR_Eval SET IsSoumiseEmploye = 'N', IsSoumiseN1 = 'N',"
            + " Date_Soumission_Employe = NULL, Date_Soumission_N1 = NULL"
            + " WHERE HR_Eval_ID = ?",
            new Object[]{ evalId }, get_TrxName());

        return "Évaluation renvoyée à l'employé pour correction.";
    }

    private int getBPartnerIdFromUser(int adUserId, String trxName) {
        return DB.getSQLValueEx(trxName,
            "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID = ?", adUserId);
    }
}
