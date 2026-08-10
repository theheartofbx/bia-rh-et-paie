package org.sitracel.formation.modelvalidator.validators;

import org.compiere.model.PO;
import org.compiere.util.DB;
import java.sql.Timestamp;

public class SitracelModelValidatorFormationSession {

    public static String beforeSave(PO po, boolean newRecord) {
        Timestamp dateDebut = (Timestamp) po.get_Value("Date_Debut");
        Timestamp dateFin = (Timestamp) po.get_Value("Date_Fin");

        if (dateDebut != null && dateFin != null && dateFin.before(dateDebut)) {
            return "La date de fin ne peut pas être antérieure à la date de début.";
        }

        if (!newRecord) {
            int sessionID = po.get_ID();

            // Si dates modifiées, vérifier que les lignes planning sont toujours dans la période
            if (po.is_ValueChanged("Date_Debut") || po.is_ValueChanged("Date_Fin")) {
                int horsPlage = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne pl"
                    + " JOIN HR_FormationPlanning p ON p.HR_FormationPlanning_ID = pl.HR_FormationPlanning_ID"
                    + " WHERE p.HR_FormationSession_ID=? AND pl.IsActive='Y'"
                    + " AND pl.Date_Planning IS NOT NULL"
                    + " AND (pl.Date_Planning < ? OR pl.Date_Planning > ?)",
                    sessionID, dateDebut, dateFin);
                if (horsPlage > 0) {
                    return "Impossible de modifier les dates : " + horsPlage + " ligne(s) de planning sont hors de la nouvelle période.";
                }
            }

            // Si Nombre_Places réduit, vérifier qu'on ne passe pas sous le nombre de participants
            if (po.is_ValueChanged("Nombre_Places")) {
                int nbPlaces = po.get_ValueAsInt("Nombre_Places");
                if (nbPlaces > 0) {
                    int nbParticipants = DB.getSQLValueEx(null,
                        "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationSession_ID=? AND IsActive='Y'",
                        sessionID);
                    if (nbParticipants > nbPlaces) {
                        return "Impossible de réduire à " + nbPlaces + " places : il y a déjà " + nbParticipants + " participants inscrits.";
                    }
                }
            }
        }

        return null;
    }

    public static String beforeDelete(PO po) {
        int sessionID = po.get_ID();

        int nbParticipants = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationSession_ID=? AND IsActive='Y'",
            sessionID);
        if (nbParticipants > 0) {
            return "Impossible de supprimer : " + nbParticipants + " participant(s) inscrit(s) à cette session.";
        }

        int nbDemandes = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationDemande WHERE HR_FormationSession_ID=? AND IsActive='Y'",
            sessionID);
        if (nbDemandes > 0) {
            return "Impossible de supprimer : " + nbDemandes + " demande(s) de participation existent pour cette session.";
        }

        return null;
    }
}
