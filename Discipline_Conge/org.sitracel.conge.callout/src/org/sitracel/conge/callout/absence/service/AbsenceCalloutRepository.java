package org.sitracel.conge.callout.absence.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.conge.model.I_HR_Absence;

/**
 * Repository — requêtes SQL propres au callout absence.
 *
 * Regroupe les accès base de données nécessaires à la validation
 * des absences dans les callouts.
 *
 * Remplace CalloutSqlControllerAbsence.
 */
public final class AbsenceCalloutRepository {

    private static final CLogger log = CLogger.getCLogger(AbsenceCalloutRepository.class);

    private AbsenceCalloutRepository() {}

    // =========================================================================
    // VÉRIFICATION EXISTENCE
    // =========================================================================

    /**
     * Vérifie si une absence existe déjà pour un employé à une date donnée.
     *
     * Remplace CalloutSqlControllerAbsence.isAbsenceExist().
     */
    public static boolean isAbsenceExist(Timestamp dateAbsence,
                                          Integer bpartnerId,
                                          String trxName) {
        if (dateAbsence == null || bpartnerId == null) return false;

        String sql = "SELECT 1 FROM " + I_HR_Absence.Table_Name
            + " WHERE " + I_HR_Absence.COLUMNNAME_Date_Absence + " = ?"
            + " AND "   + I_HR_Absence.COLUMNNAME_C_BPartner_ID + " = ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setTimestamp(1, dateAbsence);
            pstmt.setInt(2, bpartnerId);
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            log.warning("isAbsenceExist : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return false;
    }

    // =========================================================================
    // ANNULATION ABSENCES SUITE CONGÉ
    // =========================================================================

    /**
     * Supprime toutes les absences de type "En Congé" d'un employé
     * sur une période donnée.
     *
     * Appelé lors du rejet d'un congé pour nettoyer les absences
     * créées lors de la validation.
     *
     * Remplace CalloutSqlControllerAbsence.annulerAbsenceConge().
     */
    public static void annulerAbsenceConge(Integer bpartnerId,
                                            Timestamp dateDebut,
                                            Timestamp dateFin,
                                            String trxName) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) return;

        String sql = "DELETE FROM " + I_HR_Absence.Table_Name
            + " WHERE " + I_HR_Absence.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND "   + I_HR_Absence.COLUMNNAME_Date_Absence + " BETWEEN ? AND ?"
            + " AND "   + I_HR_Absence.COLUMNNAME_IsConge + " = 'N'"
            + " AND "   + I_HR_Absence.COLUMNNAME_IsCongeTraite + " = 'Y'"
            + " AND "   + I_HR_Absence.COLUMNNAME_IsDemandeExplicationTraite + " = 'Y'";

        PreparedStatement pstmt = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateDebut);
            pstmt.setTimestamp(3, dateFin);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.warning("annulerAbsenceConge : " + e.getMessage());
        } finally {
            DB.close(null, pstmt);
        }
    }

    // =========================================================================
    // COMPTAGE ABSENCES NON TRAITÉES
    // =========================================================================

    /**
     * Retourne le nombre d'absences non traitées d'un employé
     * sur l'année en cours.
     *
     * Utilisé par le modelvalidator pour décider si une demande
     * d'explication doit être créée.
     */
    public static int getNombreAbsencesNonTraitees(Integer bpartnerId,
                                                    Timestamp dateDebut,
                                                    Timestamp dateFin,
                                                    String trxName) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) return 0;

        String sql = "SELECT COUNT(*) FROM " + I_HR_Absence.Table_Name
            + " WHERE " + I_HR_Absence.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND "   + I_HR_Absence.COLUMNNAME_Date_Absence + " BETWEEN ? AND ?"
            + " AND "   + I_HR_Absence.COLUMNNAME_IsDemandeExplication + " = 'N'"
            + " AND "   + I_HR_Absence.COLUMNNAME_IsDemandeExplicationTraite + " = 'N'"
            + " AND "   + I_HR_Absence.COLUMNNAME_IsConge + " = 'N'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateDebut);
            pstmt.setTimestamp(3, dateFin);
            rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            log.warning("getNombreAbsencesNonTraitees : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return 0;
    }
}
