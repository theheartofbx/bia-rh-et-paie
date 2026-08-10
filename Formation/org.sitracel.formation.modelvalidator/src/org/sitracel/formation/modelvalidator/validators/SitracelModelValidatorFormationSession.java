package org.sitracel.formation.modelvalidator.validators;

import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.ModelValidator;
import java.sql.Timestamp;

public class SitracelModelValidatorFormationSession {

    public static String beforeSave(PO po, boolean newRecord) {
        Timestamp dateDebut = (Timestamp) po.get_Value("Date_Debut");
        Timestamp dateFin = (Timestamp) po.get_Value("Date_Fin");

        // S1 : Date_Fin >= Date_Debut
        if (dateDebut != null && dateFin != null && dateFin.before(dateDebut)) {
            return "La date de fin ne peut pas être antérieure à la date de début.";
        }

        return null;
    }
}
