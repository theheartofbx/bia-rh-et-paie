package org.sitracel.formation.modelvalidator.validators;

import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationDemandeDelete {

    public static String beforeDelete(PO po) {
        int demandeID = po.get_ID();
        int nbPart = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationDemande_ID=? AND IsActive='Y'",
            demandeID);
        if (nbPart > 0) {
            return "Impossible de supprimer cette demande : un participant a déjà été créé suite à cette demande.";
        }
        return null;
    }
}
