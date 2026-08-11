package org.sitracel.formation.modelvalidator.planningligne;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationPlanningLigne implements ModelValidator {
    private int clientID = -1;

    private static String toHHmm(Object val) {
        if (val == null) return null;
        if (val instanceof Timestamp) {
            return new SimpleDateFormat("HH:mm").format((Timestamp) val);
        }
        String s = val.toString().trim();
        if (s.isEmpty()) return null;
        if (s.length() >= 16) return s.substring(11, 16);
        if (s.length() >= 5) return s.substring(0, 5);
        return s;
    }

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) clientID = client.getAD_Client_ID();
        engine.addModelChange("HR_FormationPlanningLigne", this);
    }

    @Override
    public int getAD_Client_ID() { return clientID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {

        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            Object rawHeureDebut = po.get_Value("Heure_Debut");
            Object rawHeureFin = po.get_Value("Heure_Fin");
            String heureDebut = toHHmm(rawHeureDebut);
            String heureFin = toHHmm(rawHeureFin);
            Timestamp datePlanning = (Timestamp) po.get_Value("Date_Planning");
            int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
            int programmeID = po.get_ValueAsInt("HR_FormationProgramme_ID");
            int moduleID = po.get_ValueAsInt("HR_FormationModule_ID");
            int numeroPartie = po.get_ValueAsInt("Numero_Partie");
            int ligneID = po.get_ID();

            // Anti-doublon
            if (planningID > 0 && programmeID > 0 && moduleID > 0 && numeroPartie > 0) {
                int doublon = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                    + " WHERE HR_FormationPlanning_ID=?"
                    + " AND HR_FormationProgramme_ID=?"
                    + " AND HR_FormationModule_ID=?"
                    + " AND Numero_Partie=?"
                    + " AND HR_FormationPlanningLigne_ID != ?"
                    + " AND IsActive='Y'",
                    planningID, programmeID, moduleID, numeroPartie, ligneID);
                if (doublon > 0)
                    return "Cette ligne existe deja : meme module, meme partie (" + numeroPartie + ").";
            }

            // Heure_Fin > Heure_Debut
            if (heureDebut != null && heureFin != null) {
                if (heureFin.compareTo(heureDebut) <= 0)
                    return "L'heure de fin doit etre posterieure a l'heure de debut.";
            }

            // Date_Planning dans la période de la session
            if (datePlanning != null && planningID > 0) {
                int sessionID = DB.getSQLValueEx(null,
                    "SELECT HR_FormationSession_ID FROM HR_FormationPlanning WHERE HR_FormationPlanning_ID=?", planningID);
                if (sessionID > 0) {
                    Timestamp sessDebut = (Timestamp) DB.getSQLValueTSEx(null,
                        "SELECT Date_Debut FROM HR_FormationSession WHERE HR_FormationSession_ID=?", sessionID);
                    Timestamp sessFin = (Timestamp) DB.getSQLValueTSEx(null,
                        "SELECT Date_Fin FROM HR_FormationSession WHERE HR_FormationSession_ID=?", sessionID);
                    if (sessDebut != null && datePlanning.before(sessDebut))
                        return "La date du planning est anterieure a la date de debut de la session.";
                    if (sessFin != null && datePlanning.after(sessFin))
                        return "La date du planning est posterieure a la date de fin de la session.";
                }
            }

            // Anti-collision horaire dans le MÊME planning (avec cast SQL)
            if (datePlanning != null && rawHeureDebut != null && rawHeureFin != null && planningID > 0) {
                int c = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                    + " WHERE HR_FormationPlanning_ID = ?"
                    + " AND HR_FormationPlanningLigne_ID != ?"
                    + " AND Date_Planning = ?"
                    + " AND IsActive='Y'"
                    + " AND Heure_Debut < ? AND Heure_Fin > ?",
                    planningID, ligneID, datePlanning, rawHeureFin, rawHeureDebut);
                if (c > 0)
                    return "Collision horaire : un autre module est deja prevu sur ce creneau dans ce planning.";
            }

            // Bloquer si participants et IsOk passe à N
            if (type == TYPE_BEFORE_CHANGE && po.is_ValueChanged("IsOk") && "N".equals(po.get_Value("IsOk"))) {
                int nbP = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
                    planningID);
                if (nbP > 0)
                    return "Impossible de modifier : " + nbP + " participant(s) assignes a ce planning.";
            }

            // Auto IsOk
            if (datePlanning != null && heureDebut != null && heureFin != null) {
                po.set_ValueOfColumn("IsOk", "Y");
            } else {
                po.set_ValueOfColumn("IsOk", "N");
            }
        }

        // BEFORE_DELETE
        if (type == TYPE_BEFORE_DELETE) {
            int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");

            if (planningID > 0) {
                int nbPart = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
                    planningID);
                if (nbPart > 0)
                    return "Impossible de supprimer cette ligne : " + nbPart + " participant(s) sont assignes a ce planning.";
            }

            if (planningID > 0) {
                int sessionID = DB.getSQLValueEx(null,
                    "SELECT HR_FormationSession_ID FROM HR_FormationPlanning WHERE HR_FormationPlanning_ID=?", planningID);
                if (sessionID > 0) {
                    int catalogueID = DB.getSQLValueEx(null,
                        "SELECT HR_FormationCatalogue_ID FROM HR_FormationSession WHERE HR_FormationSession_ID=?", sessionID);
                    if (catalogueID > 0) {
                        int totalAttendu = DB.getSQLValueEx(null,
                            "SELECT COALESCE(SUM(Nombre_Partie), 0) FROM HR_FormationProgramme"
                            + " WHERE HR_FormationCatalogue_ID=? AND IsActive='Y'", catalogueID);
                        int totalActuel = DB.getSQLValueEx(null,
                            "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                            + " WHERE HR_FormationPlanning_ID=? AND IsActive='Y'", planningID);
                        if ((totalActuel - 1) < totalAttendu) {
                            return "Impossible de supprimer : le planning deviendrait incomplet ("
                                + (totalActuel - 1) + "/" + totalAttendu + " lignes). "
                                + "Supprimez le planning entier si vous souhaitez le reconstruire.";
                        }
                    }
                }
            }
        }

        // AFTER : recalcul IsOk parent
        if (type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE || type == TYPE_AFTER_DELETE) {
            int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
            if (planningID > 0) {
                int total = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationPlanning_ID=? AND IsActive='Y'", planningID);
                int ok = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationPlanning_ID=? AND IsActive='Y' AND IsOk='Y'", planningID);
                String v = (total > 0 && total == ok) ? "Y" : "N";
                DB.executeUpdateEx("UPDATE HR_FormationPlanning SET IsOk='" + v + "' WHERE HR_FormationPlanning_ID=" + planningID, null);
            }

            // Mise a jour des absences quand la date change
            if (type == TYPE_AFTER_CHANGE && po.is_ValueChanged("Date_Planning")) {
                int typeAbsID = DB.getSQLValueEx(null,
                    "SELECT HR_Type_Absence_ID FROM HR_Type_Absence WHERE Nom_Absence='Formation'");
                if (typeAbsID > 0) {
                    java.sql.Timestamp oldDate = (java.sql.Timestamp) po.get_ValueOld("Date_Planning");
                    java.sql.Timestamp newDate = (java.sql.Timestamp) po.get_Value("Date_Planning");
                    String sqlPart = "SELECT C_BPartner_ID FROM HR_FormationParticipant"
                        + " WHERE HR_FormationPlanning_ID=" + planningID + " AND IsActive='Y'"; 
                    if (oldDate != null) {
                        DB.executeUpdateEx(
                            "DELETE FROM HR_Absence WHERE HR_Type_Absence_ID=" + typeAbsID
                            + " AND Date_Absence=" + org.compiere.util.DB.TO_DATE(oldDate)
                            + " AND C_BPartner_ID IN (" + sqlPart + ")",
                            null);
                    }
                    if (newDate != null) {
                        java.sql.PreparedStatement pstmt2 = null;
                        java.sql.ResultSet rs2 = null;
                        try {
                            pstmt2 = DB.prepareStatement(sqlPart, null);
                            rs2 = pstmt2.executeQuery();
                            while (rs2.next()) {
                                int bpID = rs2.getInt("C_BPartner_ID");
                                int existe = DB.getSQLValueEx(null,
                                    "SELECT COUNT(*) FROM HR_Absence WHERE C_BPartner_ID=" + bpID
                                    + " AND Date_Absence=" + org.compiere.util.DB.TO_DATE(newDate)
                                    + " AND HR_Type_Absence_ID=" + typeAbsID + " AND IsActive='Y'");
                                if (existe <= 0) {
                                    int cid = Env.getAD_Client_ID(Env.getCtx());
                                    int oid = Env.getAD_Org_ID(Env.getCtx());
                                    int uid = Env.getAD_User_ID(Env.getCtx());
                                    int apID = DB.getSQLValueEx(null, "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID=" + uid);
                                    if (apID <= 0) apID = 0;
                                    String mAp = DB.getSQLValueStringEx(null, "SELECT Value FROM C_BPartner WHERE C_BPartner_ID=" + apID);
                                    if (mAp == null) mAp = "N/A";
                                    int pAp = DB.getSQLValueEx(null, "SELECT HR_Job_ID FROM HR_Affectation WHERE C_BPartner_ID=" + apID + " AND IsActive='Y' ORDER BY Date_Debut DESC FETCH FIRST 1 ROWS ONLY");
                                    if (pAp < 0) pAp = 0;
                                    String mEm = DB.getSQLValueStringEx(null, "SELECT Value FROM C_BPartner WHERE C_BPartner_ID=" + bpID);
                                    if (mEm == null) mEm = "N/A";
                                    int pEm = DB.getSQLValueEx(null, "SELECT HR_Job_ID FROM HR_Affectation WHERE C_BPartner_ID=" + bpID + " AND IsActive='Y' ORDER BY Date_Debut DESC FETCH FIRST 1 ROWS ONLY");
                                    if (pEm < 0) pEm = 0;
                                    int absID = DB.getNextID(cid, "HR_Absence", null);
                                    DB.executeUpdateEx("INSERT INTO HR_Absence (HR_Absence_ID, AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, C_BPartner_ID, Date_Absence, HR_Type_Absence_ID, Date_Emission, IsConge, IsDemandeExplication, IsCongeTraite, IsDemandeExplicationTraite, Emis_Par_Nom_ID, Emis_Par_Poste_ID, Emis_Par_Matricule, Matricule_Employe, Poste_Employe_ID) VALUES (" + absID + ", " + cid + ", " + oid + ", now(), " + uid + ", now(), " + uid + ", 'Y', " + bpID + ", " + org.compiere.util.DB.TO_DATE(newDate) + ", " + typeAbsID + ", now(), 'N', 'N', 'Y', 'Y', " + apID + ", " + pAp + ", '" + mAp + "', '" + mEm + "', " + pEm + ")", null);
                                }
                            }
                        } finally {
                            DB.close(rs2, pstmt2);
                        }
                    }
                }
            }
        }

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
