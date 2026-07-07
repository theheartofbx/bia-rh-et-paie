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
}
