package org.sitracel.employe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.contrat.model.I_HR_Affectation;
import org.sitracel.contrat.model.I_HR_Contrat;
import org.sitracel.contrat.model.I_HR_ContratStatut;
import org.sitracel.contrat.model.MHRAffectation;
import org.sitracel.contrat.model.MHRContrat;

/**
 * Accès SQL pour les questions de contrat et d'affectation.
 * Aucune décision métier ici — uniquement de la lecture en base.
 */
public class HRContratRepository {

    private static final CLogger log = CLogger.getCLogger(HRContratRepository.class);

    /**
     * Retourne le contrat actif d'un employé couvrant la date donnée,
     * ou null s'il n'y en a aucun.
     *
     * "Actif" = statut HR_ContratStatut.Name = 'Actif' ET la date donnée
     * tombe entre Date_Debut et Date_Fin (Date_Fin null = en cours).
     */
    public static MHRContrat getContratActif(Properties ctx, int bpartnerId, Timestamp dateReference, String trxName) {
        if (bpartnerId <= 0 || dateReference == null) {
            return null;
        }

        String sql = "SELECT c.* FROM " + I_HR_Contrat.Table_Name + " c"
            + " JOIN " + I_HR_ContratStatut.Table_Name + " s"
            + "   ON s." + I_HR_ContratStatut.COLUMNNAME_HR_ContratStatut_ID
            + "    = c." + I_HR_Contrat.COLUMNNAME_HR_ContratStatut_ID
            + " WHERE c." + I_HR_Contrat.COLUMNNAME_C_BPartner_ID + " = ?"
            + "   AND s.Name = 'Actif'"
            + "   AND c." + I_HR_Contrat.COLUMNNAME_Date_Debut + " <= ?"
            + "   AND (c." + I_HR_Contrat.COLUMNNAME_Date_Fin + " IS NULL"
            + "        OR c." + I_HR_Contrat.COLUMNNAME_Date_Fin + " >= ?)"
            + "   AND c.IsActive = 'Y'"
            + " ORDER BY c." + I_HR_Contrat.COLUMNNAME_Date_Debut + " DESC"
            + " LIMIT 1";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateReference);
            pstmt.setTimestamp(3, dateReference);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new MHRContrat(ctx, rs, trxName);
            }
        } catch (SQLException e) {
            log.warning("getContratActif : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    /**
     * Retourne l'affectation active d'un employé couvrant la date donnée,
     * ou null s'il n'y en a aucune.
     *
     * HR_Affectation n'a pas de colonne de statut : "active" se détermine
     * uniquement par les dates (Date_Fin null = en cours).
     */
    public static MHRAffectation getAffectationActive(Properties ctx, int bpartnerId, Timestamp dateReference, String trxName) {
        if (bpartnerId <= 0 || dateReference == null) {
            return null;
        }

        String sql = "SELECT * FROM " + I_HR_Affectation.Table_Name
            + " WHERE " + I_HR_Affectation.COLUMNNAME_C_BPartner_ID + " = ?"
            + "   AND " + I_HR_Affectation.COLUMNNAME_Date_Debut + " <= ?"
            + "   AND (" + I_HR_Affectation.COLUMNNAME_Date_Fin + " IS NULL"
            + "        OR " + I_HR_Affectation.COLUMNNAME_Date_Fin + " >= ?)"
            + "   AND IsActive = 'Y'"
            + " ORDER BY " + I_HR_Affectation.COLUMNNAME_Date_Debut + " DESC"
            + " LIMIT 1";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateReference);
            pstmt.setTimestamp(3, dateReference);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new MHRAffectation(ctx, rs, trxName);
            }
        } catch (SQLException e) {
            log.warning("getAffectationActive : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    /**
     * Retourne les C_BPartner_ID des employés dont l'affectation active,
     * à la date de référence, correspond au poste donné.
     *
     * Remplace HREmployeRepository.getEmployeesByJob(), qui interrogeait
     * l'ancienne table HR_EmployeeJob sans filtre de date (bug latent :
     * retournait aussi les employés ayant occupé ce poste dans le passé).
     */
    public static List<Integer> getBPartnersAffectesAuPoste(int posteId, Timestamp dateReference, String trxName) {
        List<Integer> resultat = new ArrayList<>();
        if (posteId <= 0 || dateReference == null) {
            return resultat;
        }

        String sql = "SELECT " + I_HR_Affectation.COLUMNNAME_C_BPartner_ID
            + " FROM " + I_HR_Affectation.Table_Name
            + " WHERE " + I_HR_Affectation.COLUMNNAME_HR_Job_ID + " = ?"
            + "   AND " + I_HR_Affectation.COLUMNNAME_Date_Debut + " <= ?"
            + "   AND (" + I_HR_Affectation.COLUMNNAME_Date_Fin + " IS NULL"
            + "        OR " + I_HR_Affectation.COLUMNNAME_Date_Fin + " >= ?)"
            + "   AND IsActive = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, posteId);
            pstmt.setTimestamp(2, dateReference);
            pstmt.setTimestamp(3, dateReference);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            log.warning("getBPartnersAffectesAuPoste : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    /**
     * Retourne l'ID du département du poste actuellement occupé par un
     * employé (via son affectation active), à la date donnée.
     *
     * Le département est un attribut du POSTE (HR_Job.HR_Department_ID),
     * pas de l'affectation elle-même — HR_Affectation ne porte aucune
     * colonne "département".
     *
     * @return null si l'employé n'a pas d'affectation active à cette date,
     *         ou si son poste n'a pas de département renseigné.
     */
    public static Integer getDepartementActuel(Properties ctx, int bpartnerId, Timestamp dateReference, String trxName) {
        MHRAffectation affectation = getAffectationActive(ctx, bpartnerId, dateReference, trxName);
        if (affectation == null || affectation.getHR_Job_ID() <= 0) {
            return null;
        }

        String sql = "SELECT HR_Department_ID FROM HR_Job WHERE HR_Job_ID = ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, affectation.getHR_Job_ID());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int departementId = rs.getInt(1);
                return rs.wasNull() ? null : departementId;
            }
        } catch (SQLException e) {
            log.warning("getDepartementActuel : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    /**
     * Retourne les C_BPartner_ID de tous les employés dont le poste actuel
     * (via leur affectation active) appartient au département donné, à
     * la date de référence.
     *
     * Remplace l'ancienne jointure directe sur HR_EmployeeJob.HR_Department_ID,
     * qui n'a jamais fonctionné : cette colonne n'existe pas sur
     * HR_EmployeeJob (bug jamais détecté car l'erreur SQL était avalée
     * silencieusement par le code appelant).
     */
    public static List<Integer> getBPartnersMemeDepartement(int departementId, Timestamp dateReference, String trxName) {
        List<Integer> resultat = new ArrayList<>();
        if (departementId <= 0 || dateReference == null) {
            return resultat;
        }

        String sql = "SELECT a." + I_HR_Affectation.COLUMNNAME_C_BPartner_ID
            + " FROM " + I_HR_Affectation.Table_Name + " a"
            + " JOIN HR_Job j ON j.HR_Job_ID = a." + I_HR_Affectation.COLUMNNAME_HR_Job_ID
            + " WHERE j.HR_Department_ID = ?"
            + "   AND a." + I_HR_Affectation.COLUMNNAME_Date_Debut + " <= ?"
            + "   AND (a." + I_HR_Affectation.COLUMNNAME_Date_Fin + " IS NULL"
            + "        OR a." + I_HR_Affectation.COLUMNNAME_Date_Fin + " >= ?)"
            + "   AND a.IsActive = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, departementId);
            pstmt.setTimestamp(2, dateReference);
            pstmt.setTimestamp(3, dateReference);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            log.warning("getBPartnersMemeDepartement : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }
}
