package org.sitracel.formation.modelvalidator.session;

import java.sql.Timestamp;
import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationSession implements ModelValidator {
    private int clientID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) clientID = client.getAD_Client_ID();
        engine.addModelChange("HR_FormationSession", this);
    }

    @Override
    public int getAD_Client_ID() { return clientID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            Timestamp dateDebut = (Timestamp) po.get_Value("Date_Debut");
            Timestamp dateFin = (Timestamp) po.get_Value("Date_Fin");
            if (dateDebut != null && dateFin != null && dateFin.before(dateDebut))
                return "La date de fin ne peut pas être antérieure à la date de début.";

            if (!po.is_new()) {
                int sessionID = po.get_ID();
                if (po.is_ValueChanged("Date_Debut") || po.is_ValueChanged("Date_Fin")) {
                    int horsPlage = DB.getSQLValueEx(null,
                        "SELECT COUNT(*) FROM HR_FormationPlanningLigne pl"
                        + " JOIN HR_FormationPlanning p ON p.HR_FormationPlanning_ID = pl.HR_FormationPlanning_ID"
                        + " WHERE p.HR_FormationSession_ID=? AND pl.IsActive='Y'"
                        + " AND pl.Date_Planning IS NOT NULL"
                        + " AND (pl.Date_Planning < ? OR pl.Date_Planning > ?)",
                        sessionID, dateDebut, dateFin);
                    if (horsPlage > 0)
                        return "Impossible de modifier les dates : " + horsPlage + " ligne(s) de planning sont hors de la nouvelle période.";
                }
                if (po.is_ValueChanged("Nombre_Places")) {
                    int nbPlaces = po.get_ValueAsInt("Nombre_Places");
                    if (nbPlaces > 0) {
                        int nbPart = DB.getSQLValueEx(null,
                            "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationSession_ID=? AND IsActive='Y'",
                            sessionID);
                        if (nbPart > nbPlaces)
                            return "Impossible de réduire à " + nbPlaces + " places : il y a déjà " + nbPart + " participants.";
                    }
                }
            }
        }
        if (type == TYPE_BEFORE_DELETE) {
            int sessionID = po.get_ID();
            int nbPart = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationSession_ID=? AND IsActive='Y'", sessionID);
            if (nbPart > 0) return "Impossible de supprimer : " + nbPart + " participant(s) inscrit(s).";
            int nbDem = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationDemande WHERE HR_FormationSession_ID=? AND IsActive='Y'", sessionID);
            if (nbDem > 0) return "Impossible de supprimer : " + nbDem + " demande(s) existent.";
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
