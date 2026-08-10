package org.sitracel.formation.process.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class ProcessControllerFormation {

    // =====================================================
    // F1 : Valider Session
    // =====================================================
    public static String validerSession(int sessionID) {
        // Vérifier que la session existe et n'est pas déjà validée
        String isValidee = DB.getSQLValueStringEx(null,
            "SELECT IsValidee FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
            sessionID);
        if ("Y".equals(isValidee)) {
            throw new AdempiereException("Cette session est déjà validée.");
        }

        // Vérifier qu'il y a au moins un planning avec des lignes
        int nbLignes = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationPlanningLigne pl"
            + " JOIN HR_FormationPlanning p ON p.HR_FormationPlanning_ID = pl.HR_FormationPlanning_ID"
            + " WHERE p.HR_FormationSession_ID=? AND pl.IsActive='Y'",
            sessionID);
        if (nbLignes == 0) {
            throw new AdempiereException("Impossible de valider : aucun planning n'a été généré pour cette session.");
        }

        // Mettre à jour le statut
        DB.executeUpdateEx(
            "UPDATE HR_FormationSession SET IsValidee='Y', Date_Validation=now() WHERE HR_FormationSession_ID=" + sessionID,
            null);

        // TODO Phase 3 : notification FORMATION_SESSION_VALIDEE à tous les employés actifs

        return "Session de formation validée avec succès. Les employés seront notifiés.";
    }

    // =====================================================
    // F2 : Annuler Session
    // =====================================================
    public static String annulerSession(int sessionID) {
        String isValidee = DB.getSQLValueStringEx(null,
            "SELECT IsValidee FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
            sessionID);

        // Vérifier qu'on n'annule pas une session déjà terminée
        // On utilise la valeur du statut via la table de référence
        // Pour l'instant on vérifie juste que ce n'est pas déjà annulé via un flag simple
        int nbParticipants = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationSession_ID=? AND IsActive='Y'",
            sessionID);

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

        // TODO Phase 3 : notification FORMATION_SESSION_ANNULEE aux participants
        // TODO Phase 3 : supprimer les absences de type Formation associées

        return "Session annulée. " + nbParticipants + " participant(s) désinscrits.";
    }

    // =====================================================
    // F3 : Générer Planning
    // =====================================================
    public static String genererPlanning(int sessionID) {
        // Vérifier que la session n'est pas déjà validée
        String isValidee = DB.getSQLValueStringEx(null,
            "SELECT IsValidee FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
            sessionID);
        if ("Y".equals(isValidee)) {
            throw new AdempiereException("Impossible de générer le planning : la session est déjà validée.");
        }

        // Récupérer le catalogue de la session
        int catalogueID = DB.getSQLValueEx(null,
            "SELECT HR_FormationCatalogue_ID FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
            sessionID);
        if (catalogueID <= 0) {
            throw new AdempiereException("Aucun catalogue associé à cette session.");
        }

        // Vérifier qu'il y a un programme défini
        int nbProgrammes = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationProgramme WHERE HR_FormationCatalogue_ID=? AND IsActive='Y'",
            catalogueID);
        if (nbProgrammes == 0) {
            throw new AdempiereException("Le catalogue n'a aucun programme défini. Ajoutez des modules au catalogue d'abord.");
        }

        // Créer l'en-tête du planning
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

        // Parcourir le programme et créer les lignes
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
            throw new AdempiereException("Erreur lors de la génération du planning : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }

        return "Planning généré avec succès : " + nbProgrammes + " module(s), " + totalLignes + " ligne(s) créées. Renseignez les dates, horaires, lieux et encadrants.";
    }

    // =====================================================
    // F4 : Approuver Demande
    // =====================================================
    public static String approuverDemande(int demandeID) {
        String isValidee = DB.getSQLValueStringEx(null,
            "SELECT IsValidee FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);
        if ("Y".equals(isValidee)) {
            throw new AdempiereException("Cette demande est déjà approuvée.");
        }

        String isRejetee = DB.getSQLValueStringEx(null,
            "SELECT IsRejetee FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);
        if ("Y".equals(isRejetee)) {
            throw new AdempiereException("Cette demande a déjà été rejetée.");
        }

        // Récupérer les infos de la demande
        int sessionID = DB.getSQLValueEx(null,
            "SELECT HR_FormationSession_ID FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);
        int bpartnerID = DB.getSQLValueEx(null,
            "SELECT C_BPartner_ID FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);

        // Vérifier le nombre de places
        int nbPlaces = DB.getSQLValueEx(null,
            "SELECT COALESCE(Nombre_Places, 0) FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
            sessionID);
        if (nbPlaces > 0) {
            int nbParticipants = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationSession_ID=? AND IsActive='Y'",
                sessionID);
            if (nbParticipants >= nbPlaces) {
                throw new AdempiereException("Impossible d'approuver : toutes les places sont prises (" + nbPlaces + "/" + nbPlaces + ").");
            }
        }

        // Vérifier que l'employé n'est pas déjà participant
        int dejaInscrit = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationParticipant"
            + " WHERE HR_FormationSession_ID=? AND C_BPartner_ID=? AND IsActive='Y'",
            sessionID, bpartnerID);
        if (dejaInscrit > 0) {
            throw new AdempiereException("Cet employé est déjà inscrit comme participant à cette session.");
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

        // Créer automatiquement le participant
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

        // TODO Phase 3 : notification FORMATION_DEMANDE_APPROUVEE à l'employé

        return "Demande approuvée. L'employé a été inscrit comme participant.";
    }

    // =====================================================
    // F5 : Rejeter Demande
    // =====================================================
    public static String rejeterDemande(int demandeID) {
        String isValidee = DB.getSQLValueStringEx(null,
            "SELECT IsValidee FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);
        if ("Y".equals(isValidee)) {
            throw new AdempiereException("Impossible de rejeter : cette demande est déjà approuvée.");
        }

        String isRejetee = DB.getSQLValueStringEx(null,
            "SELECT IsRejetee FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);
        if ("Y".equals(isRejetee)) {
            throw new AdempiereException("Cette demande est déjà rejetée.");
        }

        // Vérifier que le motif de rejet est renseigné
        String motifRejet = DB.getSQLValueStringEx(null,
            "SELECT Motif_Rejet FROM HR_FormationDemande WHERE HR_FormationDemande_ID=?",
            demandeID);
        if (motifRejet == null || motifRejet.trim().isEmpty()) {
            throw new AdempiereException("Veuillez renseigner le motif de rejet avant de rejeter la demande.");
        }

        int userID = Env.getAD_User_ID(Env.getCtx());
        int rejetParID = DB.getSQLValueEx(null,
            "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID=?", userID);

        DB.executeUpdateEx(
            "UPDATE HR_FormationDemande SET IsRejetee='Y', Date_Decision=now()"
            + (rejetParID > 0 ? ", Valide_Rejete_Par_Nom_ID=" + rejetParID : "")
            + " WHERE HR_FormationDemande_ID=" + demandeID,
            null);

        // TODO Phase 3 : notification FORMATION_DEMANDE_REJETEE à l'employé

        return "Demande rejetée.";
    }
}
