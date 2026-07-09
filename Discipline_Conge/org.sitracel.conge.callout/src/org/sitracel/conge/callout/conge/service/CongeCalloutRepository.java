package org.sitracel.conge.callout.conge.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.conge.model.I_HR_Holiday;
import org.sitracel.employe.HRContratService;

/**
 * Repository — requêtes SQL propres au callout congé.
 *
 * Depuis Session 9 (suite) : les 3 méthodes de disponibilité département
 * n'interrogent plus HR_EmployeeJob directement. Cette table n'a jamais
 * eu de colonne HR_Department_ID en base réelle — le code d'origine
 * levait donc systématiquement une erreur SQL, avalée silencieusement
 * (catch + log.warning + return 0), sans que personne ne s'en aperçoive
 * puisque Congé n'a jamais été déployé.
 *
 * Le département est en réalité porté par le POSTE (HR_Job.HR_Department_ID),
 * pas par l'affectation. On passe donc par HRContratService — porte
 * d'entrée unique pour toute question de contrat/affectation — qui
 * retrouve le département via le poste actuel de l'employé
 * (HR_Affectation -> HR_Job -> HR_Department).
 */
public final class CongeCalloutRepository {

    private static final CLogger log = CLogger.getCLogger(CongeCalloutRepository.class);

    private CongeCalloutRepository() {}

    // =========================================================================
    // DISPONIBILITÉ DÉPARTEMENT
    // =========================================================================

    /**
     * Retourne le nombre d'employés appartenant au même département que
     * l'employé donné (poste occupé à la date de référence).
     *
     * Retourne 0 si l'employé n'a pas d'affectation active à cette date,
     * ou si son poste n'a pas de département renseigné — ce n'est pas une
     * erreur, simplement un cas où le contrôle de disponibilité ne
     * s'applique pas (ex : poste "Directeur comptable" sans département).
     */
    public static int getNombreEmployeDepartement(Integer bpartnerId, Timestamp dateReference, String trxName) {
        if (bpartnerId == null || dateReference == null) return 0;

        List<Integer> collegues = HRContratService.getBPartnersMemeDepartement(bpartnerId, dateReference, trxName);
        return collegues.size();
    }

    /**
     * Parmi les employés du même département, compte ceux qui ont un
     * congé non rejeté chevauchant la période donnée.
     */
    public static int getNombreEmployeDepartementEnConge(Integer bpartnerId,
                                                          Timestamp dateDebut,
                                                          Timestamp dateFin,
                                                          String trxName) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) return 0;

        List<Integer> collegues = HRContratService.getBPartnersMemeDepartement(bpartnerId, dateDebut, trxName);
        if (collegues.isEmpty()) return 0;

        String placeholders = construirePlaceholders(collegues.size());

        String sql = "SELECT COUNT(DISTINCT h." + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + ")"
            + " FROM " + I_HR_Holiday.Table_Name + " h"
            + " WHERE h." + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + " IN (" + placeholders + ")"
            + " AND h." + I_HR_Holiday.COLUMNNAME_IsRejetee + " = 'N'"
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective + " <= ?"
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Fin_Effective + " >= ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            int index = 1;
            for (Integer collegueId : collegues) {
                pstmt.setInt(index++, collegueId);
            }
            pstmt.setTimestamp(index++, dateFin);
            pstmt.setTimestamp(index, dateDebut);
            rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            log.warning("getNombreEmployeDepartementEnConge : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return 0;
    }

    /**
     * Retourne la date où le plus grand nombre d'employés du département
     * sont en congé simultanément sur la période. Retourne null si aucun
     * congé trouvé.
     */
    public static Timestamp getJourCritiqueDepartement(Integer bpartnerId,
                                                        Timestamp dateDebut,
                                                        Timestamp dateFin,
                                                        String trxName) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) return null;

        List<Integer> collegues = HRContratService.getBPartnersMemeDepartement(bpartnerId, dateDebut, trxName);
        if (collegues.isEmpty()) return null;

        String placeholders = construirePlaceholders(collegues.size());

        String sql = "SELECT h." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective
            + " FROM " + I_HR_Holiday.Table_Name + " h"
            + " WHERE h." + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + " IN (" + placeholders + ")"
            + " AND h." + I_HR_Holiday.COLUMNNAME_IsRejetee + " = 'N'"
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective + " <= ?"
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Fin_Effective + " >= ?"
            + " ORDER BY h." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective + " DESC"
            + " LIMIT 1";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            int index = 1;
            for (Integer collegueId : collegues) {
                pstmt.setInt(index++, collegueId);
            }
            pstmt.setTimestamp(index++, dateFin);
            pstmt.setTimestamp(index, dateDebut);
            rs = pstmt.executeQuery();
            if (rs.next()) return rs.getTimestamp(1);
        } catch (SQLException e) {
            log.warning("getJourCritiqueDepartement : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    private static String construirePlaceholders(int nombre) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nombre; i++) {
            if (i > 0) sb.append(",");
            sb.append("?");
        }
        return sb.toString();
    }
}
