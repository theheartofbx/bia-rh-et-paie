package org.sitracel.evaluation.process;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.employe.HRContratService;

/**
 * Processus : Renvoyer l'évaluation vers le N+1
 *
 * Habilitation : le N+2 désigné (Evaluateur_N2_ID) ou RH
 */
public class RenvoyerVersN1 extends SvrProcess {

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
                return "Seul le N+2 désigné ou le service RH peut renvoyer l'évaluation au N+1.";
            }
        }

        // --- Vérifications ---
        String isSoumiseN1 = DB.getSQLValueString(get_TrxName(),
            "SELECT IsSoumiseN1 FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if (!"Y".equals(isSoumiseN1)) {
            return "Le N+1 n'a pas encore soumis. Rien à renvoyer.";
        }

        String isValidee = DB.getSQLValueString(get_TrxName(),
            "SELECT IsValidee FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if ("Y".equals(isValidee)) {
            return "L'évaluation est déjà validée. Impossible de renvoyer.";
        }

        // --- Renvoyer ---
        DB.executeUpdateEx(
            "UPDATE HR_Eval SET IsSoumiseN1 = 'N',"
            + " Date_Soumission_N1 = NULL"
            + " WHERE HR_Eval_ID = ?",
            new Object[]{ evalId }, get_TrxName());

        return "Évaluation renvoyée au N+1 pour correction.";
    }

    private int getBPartnerIdFromUser(int adUserId, String trxName) {
        return DB.getSQLValueEx(trxName,
            "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID = ?", adUserId);
    }
}
