package org.sitracel.formation.process.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class ProcessControllerFormation {

    private static final String TYPE_ABSENCE_FORMATION = "Formation";

    private static int getTypeAbsenceFormationID() {
        return DB.getSQLValueEx(null,
            "SELECT HR_Type_Absence_ID FROM HR_Type_Absence WHERE Nom_Absence=?",
            TYPE_ABSENCE_FORMATION);
    }

    // =====================================================
    // F1 : Valider Session
    // =====================================================
    public static String validerSession(int sessionID) {
        String isValidee = DB.getSQLValueStringEx(null,
            "SELECT IsValidee FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
            sessionID);
        if ("Y".equals(isValidee)) {
            throw new AdempiereException("Cette session est deja validee.");
        }

        int nbLignes = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationPlanningLigne pl"
            + " JOIN HR_FormationPlanning p ON p.HR_FormationPlanning_ID = pl.HR_FormationPlanning_ID"
            + " WHERE p.HR_FormationSession_ID=? AND pl.IsActive='Y'",
            sessionID);
        if (nbLignes == 0) {
            throw new AdempiereException("Impossible de valider : aucun planning n'a ete genere pour cette session.");
        }

        DB.executeUpdateEx(
            "UPDATE HR_FormationSession SET IsValidee='Y', Date_Validation=now() WHERE HR_FormationSession_ID=" + sessionID,
            null);

        return "Session de formation validee avec succes.";
    }

    // =====================================================
    // F2 : Annuler Session
    // =====================================================
    public static String annulerSession(int sessionID) {
        int nbParticipants = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationSession_ID=? AND IsActive='Y'",
            sessionID);

        // Supprimer les absences Formation de tous les participants (basees sur le planning)
        if (nbParticipants > 0) {
            int typeAbsID = getTypeAbsenceFormationID();
            if (typeAbsID > 0) {
                DB.executeUpdateEx(
                    "DELETE FROM HR_Absence WHERE HR_Type_Absence_ID=" + typeAbsID
                    + " AND C_BPartner_ID IN ("
                    + "   SELECT C_BPartner_ID FROM HR_FormationParticipant"
                    + "   WHERE HR_FormationSession_ID=" + sessionID + " AND IsActive='Y'"
                    + " )"
                    + " AND Date_Absence IN ("
                    + "   SELECT pl.Date_Planning FROM HR_FormationPlanningLigne pl"
                    + "   JOIN HR_FormationPlanning p ON p.HR_FormationPlanning_ID = pl.HR_FormationPlanning_ID"
                    + "   WHERE p.HR_FormationSession_ID=" + sessionID
                    + "   AND pl.IsActive='Y' AND pl.Date_Planning IS NOT NULL"
                    + " )",
                    null);
            }
        }
            }
        }

        // Désactiver tous les participants
        if (nbParticipants > 0) {
            DB.executeUpdateEx(
                "UPDATE HR_FormationParticipant SET IsActive='N' WHERE HR_FormationSession_ID=" + sessionID,
                null);
        }

        // Désactiver toutes les demandes en attente
        DB.executeUpdateEx(
            "UPDATE HR_FormationDemande SET IsActive='N'"
            + " WHERE HR_FormationSession_ID=" + sessionID
            + " AND IsValidee='N' AND IsRejetee='N'",
            null);

        // Marquer la session comme non validée
        DB.executeUpdateEx(
            "UPDATE HR_FormationSession SET IsValidee='N' WHERE HR_FormationSession_ID=" + sessionID,
            null);

        return "Session annulee. " + nbParticipants + " participant(s) desinscrit(s), absences supprimees.";
    }

    // =====================================================
    // F3 : Générer Planning (inchangé - déplacé dans ModelValidator)
    // =====================================================
    public static String genererPlanning(int sessionID) {
        String isValidee = DB.getSQLValueStringEx(null,
            "SELECT IsValidee FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
            sessionID);
        if ("Y".equals(isValidee)) {
            throw new AdempiereException("Impossible de generer le planning : la session est deja validee.");
        }

        int catalogueID = DB.getSQLValueEx(null,
            "SELECT HR_FormationCatalogue_ID FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
            sessionID);
        if (catalogueID <= 0) {
            throw new AdempiereException("Aucun catalogue associe a cette session.");
        }

        int nbProgrammes = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationProgramme WHERE HR_FormationCatalogue_ID=? AND IsActive='Y'",
            catalogueID);
        if (nbProgrammes == 0) {
            throw new AdempiereException("Le catalogue n'a aucun programme defini.");
        }

        int planningID = DB.getNextID(Env.getAD_Client_ID(Env.getCtx()), "HR_FormationPlanning", null);
        String sessionName = DB.getSQLValueStringEx(null,
            "SELECT Name FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
            sessionID);

        DB.executeUpdateEx(
            "INSERT INTO HR_FormationPlanning ("
            + "HR_FormationPlanning_ID, HR_FormationPlanning_UU, AD_Client_ID, AD_Org_ID,"
            + " Created, CreatedBy, Updated, UpdatedBy, IsActive,"
            + " HR_FormationSession_ID, Name, IsOk"
            + ") VALUES ("
            + planningID + ", uuid_generate_v4()::varchar, "
            + Env.getAD_Client_ID(Env.getCtx()) + ", "
            + Env.getAD_Org_ID(Env.getCtx()) + ","
            + " now(), " + Env.getAD_User_ID(Env.getCtx())
            + ", now(), " + Env.getAD_User_ID(Env.getCtx())
            + ", 'Y',"
            + sessionID + ", 'Planning - " + (sessionName != null ? sessionName.replace("'", "''") : "") + "', 'N')",
            null);

        int totalLignes = 0;
        String sql = "SELECT HR_FormationProgramme_ID, HR_FormationModule_ID, Nombre_Partie, SeqNo"
            + " FROM HR_FormationProgramme"
            + " WHERE HR_FormationCatalogue_ID=? AND IsActive='Y'"
            + " ORDER BY SeqNo";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setInt(1, catalogueID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int programmeID = rs.getInt("HR_FormationProgramme_ID");
                int moduleID = rs.getInt("HR_FormationModule_ID");
                int nbParties = rs.getInt("Nombre_Partie");
                if (nbParties <= 0) nbParties = 1;

                for (int partie = 1; partie <= nbParties; partie++) {
                    int ligneID = DB.getNextID(Env.getAD_Client_ID(Env.getCtx()), "HR_FormationPlanningLigne", null);
                    DB.executeUpdateEx(
                        "INSERT INTO HR_FormationPlanningLigne ("
                        + "HR_FormationPlanningLigne_ID, HR_FormationPlanningLigne_UU,"
                        + " AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,"
                        + " HR_FormationPlanning_ID, HR_FormationProgramme_ID, HR_FormationModule_ID,"
                        + " Numero_Partie, IsOk"
                        + ") VALUES ("
                        + ligneID + ", uuid_generate_v4()::varchar,"
                        + " " + Env.getAD_Client_ID(Env.getCtx())
                        + ", " + Env.getAD_Org_ID(Env.getCtx())
                        + ", now(), " + Env.getAD_User_ID(Env.getCtx())
                        + ", now(), " + Env.getAD_User_ID(Env.getCtx())
                        + ", 'Y',"
                        + " " + planningID + ", " + programmeID + ", " + moduleID + ","
                        + " " + partie + ", 'N')",
                        null);
                    totalLignes++;
                }
            }
        } catch (Exception e) {
            throw new AdempiereException("Erreur generation planning : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }

        return "Planning genere : " + nbProgrammes + " module(s), " + totalLignes + " ligne(s).";
    }

    // =====================================================
    // F4 : Approuver Demande
    // =====================================================
    public static String approuverDemande(int demandeID) {
        String isValidee = DB.getSQLValueStringEx(null,
            "SELECT IsValidee FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);
        if ("Y".equals(isValidee)) {
            throw new AdempiereException("Cette demande est deja approuvee.");
        }

        String isRejetee = DB.getSQLValueStringEx(null,
            "SELECT IsRejetee FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);
        if ("Y".equals(isRejetee)) {
            throw new AdempiereException("Cette demande a deja ete rejetee.");
        }

        int sessionID = DB.getSQLValueEx(null,
            "SELECT HR_FormationSession_ID FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);
        int bpartnerID = DB.getSQLValueEx(null,
            "SELECT C_BPartner_ID FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);

        // Vérifier nombre de places
        int nbPlaces = DB.getSQLValueEx(null,
            "SELECT COALESCE(Nombre_Places, 0) FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
            sessionID);
        if (nbPlaces > 0) {
            int nbParticipants = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationSession_ID=? AND IsActive='Y'",
                sessionID);
            if (nbParticipants >= nbPlaces) {
                throw new AdempiereException("Toutes les places sont prises (" + nbPlaces + "/" + nbPlaces + ").");
            }
        }

        // Vérifier doublon
        int dejaInscrit = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationParticipant"
            + " WHERE HR_FormationSession_ID=? AND C_BPartner_ID=? AND IsActive='Y'",
            sessionID, bpartnerID);
        if (dejaInscrit > 0) {
            throw new AdempiereException("Cet employe est deja inscrit comme participant.");
        }

        // Vérifier disponibilité : l'employé a-t-il une absence sur la période de la session ?
        Timestamp dateDebut = (Timestamp) DB.getSQLValueTSEx(null,
            "SELECT Date_Debut FROM HR_FormationSession WHERE HR_FormationSession_ID=?", sessionID);
        Timestamp dateFin = (Timestamp) DB.getSQLValueTSEx(null,
            "SELECT Date_Fin FROM HR_FormationSession WHERE HR_FormationSession_ID=?", sessionID);

        if (dateDebut != null && dateFin != null) {
            int nbAbsences = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_Absence"
                + " WHERE C_BPartner_ID=? AND Date_Absence >= ? AND Date_Absence <= ? AND IsActive='Y'",
                bpartnerID, dateDebut, dateFin);
            if (nbAbsences > 0) {
                throw new AdempiereException("Impossible d'approuver : l'employe a " + nbAbsences
                    + " jour(s) d'absence (conge, suspension ou autre) durant la periode de formation.");
            }
        }

        // Approuver la demande
        int userID = Env.getAD_User_ID(Env.getCtx());
        int approuvParID = DB.getSQLValueEx(null,
            "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID=?", userID);

        DB.executeUpdateEx(
            "UPDATE HR_FormationDemande SET IsValidee='Y', Date_Decision=now()"
            + (approuvParID > 0 ? ", Valide_Rejete_Par_Nom_ID=" + approuvParID : "")
            + " WHERE HR_FormationDemande_ID=" + demandeID,
            null);

        // Créer le participant
        int participantID = DB.getNextID(Env.getAD_Client_ID(Env.getCtx()), "HR_FormationParticipant", null);
        DB.executeUpdateEx(
            "INSERT INTO HR_FormationParticipant ("
            + "HR_FormationParticipant_ID, HR_FormationParticipant_UU,"
            + " AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,"
            + " HR_FormationSession_ID, C_BPartner_ID, HR_FormationDemande_ID"
            + ") VALUES ("
            + participantID + ", uuid_generate_v4()::varchar,"
            + " " + Env.getAD_Client_ID(Env.getCtx())
            + ", " + Env.getAD_Org_ID(Env.getCtx())
            + ", now(), " + userID + ", now(), " + userID + ", 'Y',"
            + " " + sessionID + ", " + bpartnerID + ", " + demandeID + ")",
            null);

        return "Demande approuvee. L'employe a ete inscrit comme participant.";
    }

    // =====================================================
    // F5 : Rejeter Demande
    // =====================================================
    public static String rejeterDemande(int demandeID) {
        String isValidee = DB.getSQLValueStringEx(null,
            "SELECT IsValidee FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);
        if ("Y".equals(isValidee)) {
            throw new AdempiereException("Impossible de rejeter : cette demande est deja approuvee.");
        }

        String isRejetee = DB.getSQLValueStringEx(null,
            "SELECT IsRejetee FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);
        if ("Y".equals(isRejetee)) {
            throw new AdempiereException("Cette demande est deja rejetee.");
        }

        String motifRejet = DB.getSQLValueStringEx(null,
            "SELECT Motif_Rejet FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);
        if (motifRejet == null || motifRejet.trim().isEmpty()) {
            throw new AdempiereException("Veuillez renseigner le motif de rejet.");
        }

        int userID = Env.getAD_User_ID(Env.getCtx());
        int rejetParID = DB.getSQLValueEx(null,
            "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID=?", userID);

        DB.executeUpdateEx(
            "UPDATE HR_FormationDemande SET IsRejetee='Y', Date_Decision=now()"
            + (rejetParID > 0 ? ", Valide_Rejete_Par_Nom_ID=" + rejetParID : "")
            + " WHERE HR_FormationDemande_ID=" + demandeID,
            null);

        return "Demande rejetee.";
    }
}
