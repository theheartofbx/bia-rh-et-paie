package org.sitracel.formation.modelvalidator.validators;

import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class SitracelModelValidatorFormationDemande {

    public static String beforeNew(PO po) {
        int sessionID = po.get_ValueAsInt("HR_FormationSession_ID");

        // M5 : Pré-remplir C_BPartner_ID depuis l'utilisateur connecté (sécurité serveur)
        int bpartnerID = po.get_ValueAsInt("C_BPartner_ID");
        if (bpartnerID <= 0) {
            int userID = Env.getAD_User_ID(Env.getCtx());
            int bpFromUser = DB.getSQLValueEx(null,
                "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID=?",
                userID);
            if (bpFromUser > 0) {
                po.set_ValueOfColumn("C_BPartner_ID", bpFromUser);
            }
        }

        // R4 : La demande n'est possible que si la session est validée
        if (sessionID > 0) {
            String isValidee = DB.getSQLValueStringEx(null,
                "SELECT IsValidee FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
                sessionID);
            if (!"Y".equals(isValidee)) {
                return "Impossible de soumettre une demande : la session de formation n'est pas encore validée.";
            }

            // R4 bis : Session pas encore terminée ou annulée
            String statut = DB.getSQLValueStringEx(null,
                "SELECT s.Value FROM HR_FormationSessionStatut s"
                + " JOIN HR_FormationSession fs ON fs.HR_FormationSessionStatut_ID = s.HR_FormationSessionStatut_ID"
                + " WHERE fs.HR_FormationSession_ID=?",
                sessionID);
            if ("TE".equals(statut) || "AN".equals(statut)) {
                return "Impossible de soumettre une demande : la session est terminée ou annulée.";
            }
        }

        return null;
    }
}
