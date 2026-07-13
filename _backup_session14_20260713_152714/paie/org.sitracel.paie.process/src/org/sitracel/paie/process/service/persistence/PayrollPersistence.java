package org.sitracel.paie.process.service.persistence;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.paie.model.I_HR_Calcul_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Indemnite_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Paie;
import org.sitracel.paie.model.I_HR_Historique_Paie;
import org.sitracel.paie.model.MHRCalculConge;
import org.sitracel.paie.model.MHRCalculIndemniteConge;
import org.sitracel.paie.model.MHRCalculPaie;
import org.sitracel.paie.model.MHRElementBasePaie;
import org.sitracel.paie.model.MHRHistoriquePaie;
import org.sitracel.paie.model.MHRPeriodeSalariale;

/**
 * Toutes les écritures en base pour le module Paie.
 *
 * Responsabilités :
 *   - Créer ou mettre à jour HR_Calcul_Paie
 *   - Créer ou mettre à jour HR_Calcul_Conge
 *   - Créer ou mettre à jour HR_Historique_Paie
 *   - Supprimer (reset) les calculs avant recalcul
 */
public class PayrollPersistence {

    private static final CLogger log = CLogger.getCLogger(PayrollPersistence.class);

    // -------------------------------------------------------------------------
    // Calcul Paie
    // -------------------------------------------------------------------------

    /**
     * Sauvegarde ou met à jour un élément de calcul de paie.
     * Si l'enregistrement existe déjà (même employé + même élément + même période),
     * on met à jour le montant. Sinon on crée.
     *
     * @param bpartnerId       ID de l'employé
     * @param elementBasePaie  définition de l'élément (SBR, IRPP, etc.)
     * @param periodeSalariale période de paie concernée
     * @param montant          montant calculé
     * @param trxName          transaction active (peut être null)
     */
    public static void sauvegarderCalculPaie(int bpartnerId,
                                              MHRElementBasePaie elementBasePaie,
                                              MHRPeriodeSalariale periodeSalariale,
                                              BigDecimal montant,
                                              String trxName) {
        if (elementBasePaie == null || periodeSalariale == null) return;

        // Chercher si un calcul existe déjà pour cet employé / élément / période
        MHRCalculPaie calculPaie = getCalculPaieExistant(
                bpartnerId,
                elementBasePaie.getHR_Element_Base_Paie_ID(),
                periodeSalariale.getHR_Periode_Salariale_ID(),
                trxName);

        if (calculPaie == null) {
            calculPaie = new MHRCalculPaie(Env.getCtx(), 0, trxName);
            calculPaie.setHR_Calcul_Paie_ID(
                    DB.getNextID(Env.getCtx(), I_HR_Calcul_Paie.Table_Name, trxName));
            calculPaie.setC_BPartner_ID(bpartnerId);
            calculPaie.setHR_Element_Base_Paie_ID(elementBasePaie.getHR_Element_Base_Paie_ID());
            calculPaie.setHR_Periode_Salariale_ID(periodeSalariale.getHR_Periode_Salariale_ID());
        }

        calculPaie.setMontant(montant != null ? montant : BigDecimal.ZERO);
        calculPaie.save();
    }

    /**
     * Supprime tous les calculs de paie d'un employé (avant recalcul).
     */
    public static void resetCalculPaie(int bpartnerId, String trxName) {
        String sql = "SELECT * FROM " + I_HR_Calcul_Paie.Table_Name
                + " WHERE " + I_HR_Calcul_Paie.COLUMNNAME_C_BPartner_ID + "=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                new MHRCalculPaie(Env.getCtx(), rs, trxName).delete(true);
            }
        } catch (SQLException e) {
            log.warning("resetCalculPaie [bpartnerId=" + bpartnerId + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
    }

    // -------------------------------------------------------------------------
    // Calcul Congé
    // -------------------------------------------------------------------------

    /**
     * Sauvegarde ou met à jour un élément de calcul d'indemnité de congé.
     */
    public static void sauvegarderCalculConge(int bpartnerId,
                                               int elementCongeId,
                                               BigDecimal montant,
                                               String trxName) {
        MHRCalculConge calculConge = getCalculCongeExistant(bpartnerId, elementCongeId, trxName);

        if (calculConge == null) {
            calculConge = new MHRCalculConge(Env.getCtx(), 0, trxName);
            calculConge.setHR_Calcul_Conge_ID(
                    DB.getNextID(Env.getCtx(), I_HR_Calcul_Conge.Table_Name, trxName));
            calculConge.setC_BPartner_ID(bpartnerId);
            calculConge.setHR_Element_Conge_ID(elementCongeId);
        }

        calculConge.setMontant(montant != null ? montant : BigDecimal.ZERO);
        calculConge.save();
    }

    /**
     * Supprime tous les calculs de congé d'un employé (avant recalcul).
     */
    public static void resetCalculConge(int bpartnerId, String trxName) {
        String sql = "SELECT * FROM " + I_HR_Calcul_Conge.Table_Name
                + " WHERE " + I_HR_Calcul_Conge.COLUMNNAME_C_BPartner_ID + "=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                new MHRCalculConge(Env.getCtx(), rs, trxName).delete(true);
            }
        } catch (SQLException e) {
            log.warning("resetCalculConge [bpartnerId=" + bpartnerId + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
    }

    /**
     * Supprime toutes les indemnités de congé d'un employé (avant recalcul).
     */
    public static void resetIndemniteConge(int bpartnerId, String trxName) {
        String sql = "SELECT * FROM " + I_HR_Calcul_Indemnite_Conge.Table_Name
                + " WHERE " + I_HR_Calcul_Indemnite_Conge.COLUMNNAME_C_BPartner_ID + "=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                new MHRCalculIndemniteConge(Env.getCtx(), rs, trxName).delete(true);
            }
        } catch (SQLException e) {
            log.warning("resetIndemniteConge [bpartnerId=" + bpartnerId + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
    }

    // -------------------------------------------------------------------------
    // Historique Paie
    // -------------------------------------------------------------------------

    /**
     * Sauvegarde l'historique de paie après calcul.
     * L'historique conserve une trace mensuelle de chaque élément calculé.
     */
    public static void sauvegarderHistorique(int bpartnerId,
                                              MHRElementBasePaie elementBasePaie,
                                              MHRPeriodeSalariale periodeSalariale,
                                              BigDecimal montant,
                                              String trxName) {
        if (elementBasePaie == null || periodeSalariale == null) return;

        MHRHistoriquePaie historique = getHistoriqueExistant(
                bpartnerId,
                elementBasePaie.getHR_Element_Base_Paie_ID(),
                periodeSalariale.getHR_Periode_Salariale_ID(),
                trxName);

        if (historique == null) {
            historique = new MHRHistoriquePaie(Env.getCtx(), 0, trxName);
            historique.setHR_Historique_Paie_ID(
                    DB.getNextID(Env.getCtx(), I_HR_Historique_Paie.Table_Name, trxName));
            historique.setC_BPartner_ID(bpartnerId);
            historique.setHR_Element_Base_Paie_ID(elementBasePaie.getHR_Element_Base_Paie_ID());
            historique.setHR_Periode_Salariale_ID(periodeSalariale.getHR_Periode_Salariale_ID());
        }

        historique.setDate_Debut(periodeSalariale.getDate_Debut_Defaut());
        historique.setMontant(montant != null ? montant : BigDecimal.ZERO);
        historique.save();
    }

    // -------------------------------------------------------------------------
    // Méthodes privées — recherche d'existants
    // -------------------------------------------------------------------------

    private static MHRCalculPaie getCalculPaieExistant(int bpartnerId,
                                                        int elementId,
                                                        int periodeId,
                                                        String trxName) {
        String sql = "SELECT * FROM " + I_HR_Calcul_Paie.Table_Name
                + " WHERE " + I_HR_Calcul_Paie.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND " + I_HR_Calcul_Paie.COLUMNNAME_HR_Element_Base_Paie_ID + "=?"
                + " AND " + I_HR_Calcul_Paie.COLUMNNAME_HR_Periode_Salariale_ID + "=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setInt(2, elementId);
            pstmt.setInt(3, periodeId);
            rs = pstmt.executeQuery();
            if (rs.next()) return new MHRCalculPaie(Env.getCtx(), rs, trxName);
        } catch (SQLException e) {
            log.warning("getCalculPaieExistant : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    private static MHRCalculConge getCalculCongeExistant(int bpartnerId,
                                                          int elementCongeId,
                                                          String trxName) {
        String sql = "SELECT * FROM " + I_HR_Calcul_Conge.Table_Name
                + " WHERE " + I_HR_Calcul_Conge.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND " + I_HR_Calcul_Conge.COLUMNNAME_HR_Element_Conge_ID + "=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setInt(2, elementCongeId);
            rs = pstmt.executeQuery();
            if (rs.next()) return new MHRCalculConge(Env.getCtx(), rs, trxName);
        } catch (SQLException e) {
            log.warning("getCalculCongeExistant : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    private static MHRHistoriquePaie getHistoriqueExistant(int bpartnerId,
                                                            int elementId,
                                                            int periodeId,
                                                            String trxName) {
        String sql = "SELECT * FROM " + I_HR_Historique_Paie.Table_Name
                + " WHERE " + I_HR_Historique_Paie.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND " + I_HR_Historique_Paie.COLUMNNAME_HR_Element_Base_Paie_ID + "=?"
                + " AND " + I_HR_Historique_Paie.COLUMNNAME_HR_Periode_Salariale_ID + "=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setInt(2, elementId);
            pstmt.setInt(3, periodeId);
            rs = pstmt.executeQuery();
            if (rs.next()) return new MHRHistoriquePaie(Env.getCtx(), rs, trxName);
        } catch (SQLException e) {
            log.warning("getHistoriqueExistant : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }
}
