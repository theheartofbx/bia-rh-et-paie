package org.sitracel.paie.process.service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.paie.model.I_BIA_Bareme;
import org.sitracel.paie.model.I_HR_Attribute;
import org.sitracel.paie.model.I_HR_Bareme;
import org.sitracel.paie.model.I_HR_Bareme_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Paie;
import org.sitracel.paie.model.I_HR_Concept;
import org.sitracel.paie.model.I_HR_Element_Base_Paie;
import org.sitracel.paie.model.I_HR_Element_Conge;
import org.sitracel.paie.model.I_HR_GestionPaieEmploye;
import org.sitracel.paie.model.I_HR_Gestion_Presence;
import org.sitracel.paie.model.I_HR_Rang_Calcul;
import org.sitracel.paie.model.I_HR_TypeDeCharge;
import org.sitracel.paie.model.MHRBareme;
import org.sitracel.paie.model.MHRBaremeConge;
import org.sitracel.paie.model.MHRCalculConge;
import org.sitracel.paie.model.MHRCalculPaie;
import org.sitracel.paie.model.MHRElementBasePaie;
import org.sitracel.paie.model.MHRElementConge;
import org.sitracel.paie.model.MHRGestionPaieEmploye;
import org.sitracel.paie.model.MHRGestionPresence;
import org.sitracel.bean.BeanBareme;
import org.sitracel.beanfactory.BeanFactory;

/**
 * Repository SQL pour le module Paie.
 * Toutes les lectures base de données liées au calcul de paie.
 * Remplace ProcessSqlControllerPaie.
 */
public class PayrollRepository {

    private static final CLogger log = CLogger.getCLogger(PayrollRepository.class);

    // -------------------------------------------------------------------------
    // Éléments de paie
    // -------------------------------------------------------------------------

    /**
     * Retourne le montant déjà calculé pour un employé et un code élément.
     * Ex: getCalculPaieByValue(1000010, "SBR", null) → montant du SBR
     */
    public static MHRCalculPaie getCalculPaieByValue(int bpartnerId, String value, String trxName) {
        if (value == null) return null;

        String sql = "SELECT * FROM " + I_HR_Calcul_Paie.Table_Name
                + " WHERE " + I_HR_Calcul_Paie.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND " + I_HR_Calcul_Paie.COLUMNNAME_HR_Element_Base_Paie_ID
                + " IN (SELECT " + I_HR_Element_Base_Paie.COLUMNNAME_HR_Element_Base_Paie_ID
                + " FROM " + I_HR_Element_Base_Paie.Table_Name
                + " WHERE " + I_HR_Element_Base_Paie.COLUMNNAME_Value + "=?)";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setString(2, value);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new MHRCalculPaie(Env.getCtx(), rs, trxName);
            }
        } catch (SQLException e) {
            log.severe("getCalculPaieByValue [" + value + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    /**
     * Retourne la définition d'un élément de base de paie par son code.
     * Ex: getElementBasePaieByValue("SBR", null) → définition de l'élément SBR
     */
    public static MHRElementBasePaie getElementBasePaieByValue(String value, String trxName) {
        if (value == null) return null;

        String sql = "SELECT * FROM " + I_HR_Element_Base_Paie.Table_Name
                + " WHERE " + I_HR_Element_Base_Paie.COLUMNNAME_Value + "=?"
                + " AND " + I_HR_Element_Base_Paie.COLUMNNAME_IsActive + "='Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setString(1, value);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new MHRElementBasePaie(Env.getCtx(), rs, trxName);
            }
        } catch (SQLException e) {
            log.severe("getElementBasePaieByValue [" + value + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    /**
     * Retourne tous les éléments de paie initiaux, triés par rang de calcul.
     * Ce sont les éléments à calculer dans l'ordre pour chaque employé.
     */
    public static List<MHRElementBasePaie> getElementBasePaieInitialValues(String trxName) {
        List<MHRElementBasePaie> resultat = new ArrayList<>();

        String sql = "SELECT elmt.* FROM " + I_HR_Element_Base_Paie.Table_Name + " elmt"
                + " LEFT JOIN " + I_HR_Rang_Calcul.Table_Name + " rang"
                + " ON rang." + I_HR_Rang_Calcul.COLUMNNAME_HR_Rang_Calcul_ID
                + "=elmt." + I_HR_Element_Base_Paie.COLUMNNAME_HR_Rang_Calcul_ID
                + " WHERE elmt." + I_HR_Element_Base_Paie.COLUMNNAME_IsCalcul_Initial + "='Y'"
                + " AND elmt." + I_HR_Element_Base_Paie.COLUMNNAME_IsActive + "='Y'"
                + " ORDER BY rang." + I_HR_Rang_Calcul.COLUMNNAME_Rang + " ASC";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MHRElementBasePaie(Env.getCtx(), rs, trxName));
            }
        } catch (SQLException e) {
            log.severe("getElementBasePaieInitialValues : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    // -------------------------------------------------------------------------
    // Barèmes
    // -------------------------------------------------------------------------

    /**
     * Retourne le barème applicable pour un élément et un montant donnés.
     */
    public static MHRBareme getBareme(int elementBasePaieId, BigDecimal montant, String trxName) {
        if (montant == null) return null;

        String sql = "SELECT * FROM " + I_HR_Bareme.Table_Name
                + " WHERE " + I_HR_Bareme.COLUMNNAME_Montant_Debut + "<=?"
                + " AND " + I_HR_Bareme.COLUMNNAME_Montant_Fin + ">=?"
                + " AND " + I_HR_Bareme.COLUMNNAME_HR_Element_Base_Paie_ID + "=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setBigDecimal(1, montant);
            pstmt.setBigDecimal(2, montant);
            pstmt.setInt(3, elementBasePaieId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new MHRBareme(Env.getCtx(), rs, trxName);
            }
        } catch (SQLException e) {
            log.severe("getBareme [elementId=" + elementBasePaieId + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    /**
     * Retourne le barème congé applicable pour un élément et un montant donnés.
     */
    public static MHRBaremeConge getBaremeConge(int elementCongeId, BigDecimal montant, String trxName) {
        if (montant == null) return null;

        String sql = "SELECT * FROM " + I_HR_Bareme_Conge.Table_Name
                + " WHERE " + I_HR_Bareme_Conge.COLUMNNAME_Montant_Debut + "<=?"
                + " AND " + I_HR_Bareme_Conge.COLUMNNAME_Montant_Fin + ">=?"
                + " AND " + I_HR_Bareme_Conge.COLUMNNAME_HR_Element_Conge_ID + "=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setBigDecimal(1, montant);
            pstmt.setBigDecimal(2, montant);
            pstmt.setInt(3, elementCongeId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new MHRBaremeConge(Env.getCtx(), rs, trxName);
            }
        } catch (SQLException e) {
            log.severe("getBaremeConge [elementId=" + elementCongeId + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    /**
     * Retourne le barème BIA applicable pour un concept et un montant donnés.
     */
    public static BeanBareme getBiaBareme(int conceptId, BigDecimal montant, String trxName) {
        if (montant == null) return null;

        String sql = "SELECT " + I_BIA_Bareme.COLUMNNAME_Amt
                + ", " + I_BIA_Bareme.COLUMNNAME_formule
                + " FROM " + I_BIA_Bareme.Table_Name
                + " WHERE " + I_BIA_Bareme.COLUMNNAME_AmountFrom + "<=?"
                + " AND " + I_BIA_Bareme.COLUMNNAME_AmountTo + ">=?"
                + " AND " + I_BIA_Bareme.COLUMNNAME_HR_Concept_ID + "=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setBigDecimal(1, montant);
            pstmt.setBigDecimal(2, montant);
            pstmt.setInt(3, conceptId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                BeanBareme bareme = BeanFactory.getBareme();
                bareme.setAmountBareme(rs.getBigDecimal(I_BIA_Bareme.COLUMNNAME_Amt));
                bareme.setFormuleBareme(rs.getString(I_BIA_Bareme.COLUMNNAME_formule));
                return bareme;
            }
        } catch (SQLException e) {
            log.severe("getBiaBareme [conceptId=" + conceptId + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    // -------------------------------------------------------------------------
    // Charges et retenues
    // -------------------------------------------------------------------------

    /**
     * Retourne la somme des charges salariales d'un employé (PV + PF + CFC-S...).
     * Utilisé pour calculer le net à payer.
     */
    public static BigDecimal getSumChargesSalariales(int bpartnerId, String trxName) {
        String sql = "SELECT COALESCE(SUM(" + I_HR_Calcul_Paie.COLUMNNAME_Montant + "), 0)"
                + " FROM " + I_HR_Calcul_Paie.Table_Name
                + " WHERE " + I_HR_Calcul_Paie.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND " + I_HR_Calcul_Paie.COLUMNNAME_HR_Element_Base_Paie_ID + " IN ("
                + " SELECT " + I_HR_Element_Base_Paie.COLUMNNAME_HR_Element_Base_Paie_ID
                + " FROM " + I_HR_Element_Base_Paie.Table_Name
                + " LEFT JOIN " + I_HR_TypeDeCharge.Table_Name
                + " ON " + I_HR_TypeDeCharge.Table_Name + "." + I_HR_TypeDeCharge.COLUMNNAME_HR_TypeDeCharge_ID
                + "=" + I_HR_Element_Base_Paie.Table_Name + "." + I_HR_Element_Base_Paie.COLUMNNAME_HR_TypeDeCharge_ID
                + " WHERE " + I_HR_TypeDeCharge.Table_Name + "." + I_HR_TypeDeCharge.COLUMNNAME_Name + "=?)";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setString(2, "RETENUE SALARIALE");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            log.severe("getSumChargesSalariales [bpartnerId=" + bpartnerId + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return BigDecimal.ZERO;
    }

    // -------------------------------------------------------------------------
    // Gestion paie employé
    // -------------------------------------------------------------------------

    /**
     * Retourne tous les employés actifs à payer ce mois.
     */
    public static List<MHRGestionPaieEmploye> getAllGestionPaieEmploye(String trxName) {
        List<MHRGestionPaieEmploye> resultat = new ArrayList<>();

        String sql = "SELECT * FROM " + I_HR_GestionPaieEmploye.Table_Name
                + " WHERE " + I_HR_GestionPaieEmploye.COLUMNNAME_IsActive + "='Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MHRGestionPaieEmploye(Env.getCtx(), rs, trxName));
            }
        } catch (SQLException e) {
            log.severe("getAllGestionPaieEmploye : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    // -------------------------------------------------------------------------
    // Congés — éléments et calculs
    // -------------------------------------------------------------------------

    /**
     * Retourne tous les éléments de calcul de congé, triés par rang.
     */
    public static List<MHRElementConge> getElementsCongeInitialValues(String trxName) {
        List<MHRElementConge> resultat = new ArrayList<>();

        String sql = "SELECT elmt.* FROM " + I_HR_Element_Conge.Table_Name + " elmt"
                + " LEFT JOIN " + I_HR_Rang_Calcul.Table_Name + " rang"
                + " ON rang." + I_HR_Rang_Calcul.COLUMNNAME_HR_Rang_Calcul_ID
                + "=elmt." + I_HR_Element_Conge.COLUMNNAME_HR_Rang_Calcul_ID
                + " WHERE elmt." + I_HR_Element_Conge.COLUMNNAME_IsCalcul_Initial + "='Y'"
                + " AND elmt." + I_HR_Element_Conge.COLUMNNAME_IsActive + "='Y'"
                + " ORDER BY rang." + I_HR_Rang_Calcul.COLUMNNAME_Rang + " ASC";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MHRElementConge(Env.getCtx(), rs, trxName));
            }
        } catch (SQLException e) {
            log.severe("getElementsCongeInitialValues : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    /**
     * Retourne le montant déjà calculé pour un employé et un code élément congé.
     */
    public static MHRCalculConge getCalculCongeByValue(int bpartnerId, String value, String trxName) {
        if (value == null) return null;

        String sql = "SELECT * FROM " + I_HR_Calcul_Conge.Table_Name
                + " WHERE " + I_HR_Calcul_Conge.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND " + I_HR_Calcul_Conge.COLUMNNAME_HR_Element_Conge_ID
                + " IN (SELECT " + I_HR_Element_Conge.COLUMNNAME_HR_Element_Conge_ID
                + " FROM " + I_HR_Element_Conge.Table_Name
                + " WHERE " + I_HR_Element_Conge.COLUMNNAME_Value + "=?)";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setString(2, value);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new MHRCalculConge(Env.getCtx(), rs, trxName);
            }
        } catch (SQLException e) {
            log.severe("getCalculCongeByValue [" + value + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    // -------------------------------------------------------------------------
    // Attributs employé (contrat, salaire de base)
    // -------------------------------------------------------------------------

    /**
     * Retourne le montant d'un attribut employé par son code concept.
     * Ex: getAttributAmountByValue(1000010, "SB", null) → salaire de base
     */
    public static BigDecimal getAttributAmountByValue(int bpartnerId, String value, String trxName) {
        if (value == null) return BigDecimal.ZERO;

        String sql = "SELECT att." + I_HR_Attribute.COLUMNNAME_Amount
                + " FROM " + I_HR_Attribute.Table_Name + " att"
                + " INNER JOIN " + I_HR_Concept.Table_Name + " con"
                + " ON con." + I_HR_Concept.COLUMNNAME_HR_Concept_ID
                + "=att." + I_HR_Attribute.COLUMNNAME_HR_Concept_ID
                + " WHERE att." + I_HR_Attribute.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND con." + I_HR_Concept.COLUMNNAME_Value + "=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setString(2, value);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                BigDecimal val = rs.getBigDecimal(I_HR_Attribute.COLUMNNAME_Amount);
                return val != null ? val : BigDecimal.ZERO;
            }
        } catch (SQLException e) {
            log.severe("getAttributAmountByValue [" + value + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return BigDecimal.ZERO;
    }

    /**
     * Retourne l'ID d'un attribut employé par son code concept.
     */
    public static Integer getAttributIdByValue(int bpartnerId, String value, String trxName) {
        if (value == null) return null;

        String sql = "SELECT att." + I_HR_Attribute.COLUMNNAME_HR_Attribute_ID
                + " FROM " + I_HR_Attribute.Table_Name + " att"
                + " INNER JOIN " + I_HR_Concept.Table_Name + " con"
                + " ON con." + I_HR_Concept.COLUMNNAME_HR_Concept_ID
                + "=att." + I_HR_Attribute.COLUMNNAME_HR_Concept_ID
                + " WHERE att." + I_HR_Attribute.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND con." + I_HR_Concept.COLUMNNAME_Value + "=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setString(2, value);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(I_HR_Attribute.COLUMNNAME_HR_Attribute_ID);
            }
        } catch (SQLException e) {
            log.severe("getAttributIdByValue [" + value + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    // -------------------------------------------------------------------------
    // Présence
    // -------------------------------------------------------------------------

    /**
     * Retourne l'ID de la fiche de présence d'un employé pour une période.
     */
    public static Integer getGestionPresenceId(int bpartnerId, int periodeSalarialeId, String trxName) {
        String sql = "SELECT " + I_HR_Gestion_Presence.COLUMNNAME_HR_Gestion_Presence_ID
                + " FROM " + I_HR_Gestion_Presence.Table_Name
                + " WHERE " + I_HR_Gestion_Presence.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND " + I_HR_Gestion_Presence.COLUMNNAME_HR_Periode_Salariale_ID + "=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setInt(2, periodeSalarialeId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(I_HR_Gestion_Presence.COLUMNNAME_HR_Gestion_Presence_ID);
            }
        } catch (SQLException e) {
            log.severe("getGestionPresenceId : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }
}
