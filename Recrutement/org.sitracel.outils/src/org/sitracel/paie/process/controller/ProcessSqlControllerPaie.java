package org.sitracel.paie.process.controller;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanBareme;
import org.sitracel.bean.BeanElmtPaie;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.paie.model.I_BIA_Bareme;
import org.sitracel.paie.model.I_HR_Attribute;
import org.sitracel.paie.model.I_HR_Bareme;
import org.sitracel.paie.model.I_HR_Bareme_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Paie;
import org.sitracel.paie.model.I_HR_Concept;
import org.sitracel.paie.model.I_HR_Element_Base_Paie;
import org.sitracel.paie.model.I_HR_Element_Conge;
import org.sitracel.paie.model.I_HR_Gestion_Presence;
import org.sitracel.paie.model.I_HR_Rang_Calcul;
import org.sitracel.paie.model.I_HR_TypeDeCharge;
import org.sitracel.paie.model.MHRBareme;
import org.sitracel.paie.model.MHRBaremeConge;
import org.sitracel.paie.model.MHRCalculConge;
import org.sitracel.paie.model.MHRCalculPaie;
import org.sitracel.paie.model.MHRElementBasePaie;
import org.sitracel.paie.model.MHRElementConge;
import org.sitracel.paie.model.MHRGestionPresence;

public class ProcessSqlControllerPaie {
	public static CLogger log = CLogger.getCLogger(PO.class);

	public static Integer getAttributIDbyValue(Integer cbpartnerid, String value, String trxName) {
		Integer resultat =null;
		if(value!=null && cbpartnerid!=null) {
			StringBuilder sql = new StringBuilder("SELECT att."+I_HR_Attribute.COLUMNNAME_HR_Attribute_ID
					+" FROM "+I_HR_Attribute.Table_Name+" att"
					+" INNER JOIN "+I_HR_Concept.Table_Name+" con "
							+ "ON con."+I_HR_Concept.COLUMNNAME_HR_Concept_ID+"=att."+I_HR_Attribute.COLUMNNAME_HR_Concept_ID
					+" WHERE att."+I_HR_Attribute.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND con."+I_HR_Concept.COLUMNNAME_Value+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				pstmt.setString(2, value);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getInt(I_HR_Attribute.COLUMNNAME_HR_Attribute_ID);
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static BigDecimal getAttributAmountbyValue(Integer cbpartnerid, String value, String trxName) {
		BigDecimal resultat = new BigDecimal(0);
		if(value!=null && cbpartnerid!=null) {
			StringBuilder sql = new StringBuilder("SELECT att."+I_HR_Attribute.COLUMNNAME_Amount
					+" FROM "+I_HR_Attribute.Table_Name+" att"
					+" INNER JOIN "+I_HR_Concept.Table_Name+" con "
							+ "ON con."+I_HR_Concept.COLUMNNAME_HR_Concept_ID+"=att."+I_HR_Attribute.COLUMNNAME_HR_Concept_ID
					+" WHERE att."+I_HR_Attribute.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND con."+I_HR_Concept.COLUMNNAME_Value+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				pstmt.setString(2, value);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getBigDecimal(I_HR_Attribute.COLUMNNAME_Amount);
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static MHRCalculPaie getCalculPaiebyValue(Integer cbpartnerid, String value, String trxName) {
		MHRCalculPaie resultat = null;
		if(value!=null && cbpartnerid!=null) {
			StringBuilder sql = new StringBuilder("SELECT cal.*"
					+" FROM "+I_HR_Calcul_Paie.Table_Name+" cal"
					+" INNER JOIN "+I_HR_Element_Base_Paie.Table_Name+" elmt "
							+ "ON elmt."+I_HR_Element_Base_Paie.COLUMNNAME_HR_Element_Base_Paie_ID+"=cal."+I_HR_Calcul_Paie.COLUMNNAME_HR_Element_Base_Paie_ID
							+" AND elmt."+I_HR_Element_Base_Paie.COLUMNNAME_Value+"=?"
					+" WHERE cal."+I_HR_Calcul_Paie.COLUMNNAME_C_BPartner_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setString(1, value);
				pstmt.setInt(2, cbpartnerid);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = new MHRCalculPaie(Env.getCtx(), rs, trxName);
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static MHRCalculConge getCalculCongebyValue(Integer cbpartnerid, String value, String trxName) {
		MHRCalculConge resultat = null;
		if(value!=null && cbpartnerid!=null) {
			StringBuilder sql = new StringBuilder("SELECT cal.*"
					+" FROM "+I_HR_Calcul_Conge.Table_Name+" cal"
					+" INNER JOIN "+I_HR_Element_Conge.Table_Name+" elmt "
							+ "ON elmt."+I_HR_Element_Conge.COLUMNNAME_HR_Element_Conge_ID+"=cal."+I_HR_Calcul_Conge.COLUMNNAME_HR_Element_Conge_ID
							+" AND elmt."+I_HR_Element_Conge.COLUMNNAME_Value+"=?"
					+" WHERE cal."+I_HR_Calcul_Conge.COLUMNNAME_C_BPartner_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setString(1, value);
				pstmt.setInt(2, cbpartnerid);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = new MHRCalculConge(Env.getCtx(), rs, trxName);
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static Boolean isAttributExist (Integer cbpartnerid, String value, String trxName)
	{
		Boolean resultat = false;
		if(cbpartnerid!=null && value!=null) {
			StringBuilder sql = new StringBuilder("SELECT EXISTS (SELECT 1 "
					+" FROM "+I_HR_Attribute.Table_Name+" att"
					+" INNER JOIN "+I_HR_Concept.Table_Name+" con "
							+ "ON con."+I_HR_Concept.COLUMNNAME_HR_Concept_ID+"=att."+I_HR_Attribute.COLUMNNAME_HR_Concept_ID
					+" WHERE att."+I_HR_Attribute.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND con."+I_HR_Concept.COLUMNNAME_Value+"=?)");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				pstmt.setString(2, value);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getBoolean(1);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static Boolean isCalculPaieExist (Integer cbpartnerid, String value, String trxName)
	{
		Boolean resultat = false;
		if(cbpartnerid!=null && value!=null) {
			StringBuilder sql = new StringBuilder("SELECT EXISTS (SELECT 1 "
					+" FROM "+I_HR_Calcul_Paie.Table_Name+" cal"
					+" INNER JOIN "+I_HR_Element_Base_Paie.Table_Name+" elmt "
							+ "ON elmt."+I_HR_Element_Base_Paie.COLUMNNAME_HR_Element_Base_Paie_ID+"=cal."+I_HR_Calcul_Paie.COLUMNNAME_HR_Element_Base_Paie_ID
							+" AND elmt."+I_HR_Element_Base_Paie.COLUMNNAME_Value+"=?"
					+" WHERE cal."+I_HR_Calcul_Paie.COLUMNNAME_C_BPartner_ID+"=?)");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setString(1, value);
				pstmt.setInt(2, cbpartnerid);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getBoolean(1);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static boolean isGestionPresenceExist (Integer cbpartnerid, Integer periodeSalarialeID, String trxName)
	{
		boolean resultat = false;
		if(cbpartnerid!=null && periodeSalarialeID!=null) {
			StringBuilder sql = new StringBuilder("SELECT EXISTS (SELECT 1 "
					+" FROM "+I_HR_Gestion_Presence.Table_Name
					+" WHERE "+I_HR_Gestion_Presence.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+I_HR_Gestion_Presence.COLUMNNAME_HR_Periode_Salariale_ID+"=?)");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				pstmt.setInt(2, periodeSalarialeID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getBoolean(1);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return resultat;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static MHRGestionPresence getGestionPresence (Integer cbpartnerid, Integer periodeSalarialeID, String trxName)
	{
		MHRGestionPresence resultat = null;
		if(cbpartnerid!=null && periodeSalarialeID!=null) {
			StringBuilder sql = new StringBuilder("SELECT "+I_HR_Gestion_Presence.COLUMNNAME_HR_Gestion_Presence_ID
					+" FROM "+I_HR_Gestion_Presence.Table_Name
					+" WHERE "+I_HR_Gestion_Presence.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+I_HR_Gestion_Presence.COLUMNNAME_HR_Periode_Salariale_ID+"=?");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				pstmt.setInt(2, periodeSalarialeID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = new MHRGestionPresence(Env.getCtx(), rs.getInt(I_HR_Gestion_Presence.COLUMNNAME_HR_Gestion_Presence_ID), trxName);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return resultat;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static BeanElmtPaie getConceptInfobyValue(String value, String trxName) {
		BeanElmtPaie resultat =null;
		if(value!=null) {
			StringBuilder sql = new StringBuilder("SELECT con."+I_HR_Concept.COLUMNNAME_HR_Concept_ID+" , "
					+" con."+I_HR_Concept.COLUMNNAME_Bia_BaseElmt_ID+" , "
					+" con."+I_HR_Concept.COLUMNNAME_Bia_Pourcentage+" , "
					+" con."+I_HR_Concept.COLUMNNAME_formule
					+" FROM "+I_HR_Concept.Table_Name+" con"
					+" WHERE con."+I_HR_Concept.COLUMNNAME_Value+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setString(1, value);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = BeanFactory.getBeanElmtPaie();
					resultat.setValue(value);
					resultat.setConceptID(rs.getInt(I_HR_Concept.COLUMNNAME_HR_Concept_ID));
					resultat.setBaseCalculID(rs.getInt(I_HR_Concept.COLUMNNAME_Bia_BaseElmt_ID));
					resultat.setTaux(rs.getBigDecimal(I_HR_Concept.COLUMNNAME_Bia_Pourcentage));
					resultat.setFormule(rs.getString(I_HR_Concept.COLUMNNAME_formule));
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static MHRElementBasePaie getElementBasePaieFromValue(String value, String trxName) {
		MHRElementBasePaie resultat =null;
		if(value!=null) {
			StringBuilder sql = new StringBuilder("SELECT *"
					+" FROM "+I_HR_Element_Base_Paie.Table_Name
					+" WHERE "+I_HR_Element_Base_Paie.COLUMNNAME_Value+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setString(1, value);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = new MHRElementBasePaie(Env.getCtx(), rs, trxName);
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static MHRElementConge getElementBaseCongeFromValue(String value, String trxName) {
		MHRElementConge resultat =null;
		if(value!=null) {
			StringBuilder sql = new StringBuilder("SELECT *"
					+" FROM "+I_HR_Element_Conge.Table_Name
					+" WHERE "+I_HR_Element_Conge.COLUMNNAME_Value+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setString(1, value);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = new MHRElementConge(Env.getCtx(), rs, trxName);
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static ArrayList<String> getConceptValues(String trxName) {
		ArrayList<String> resultat = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT con."+I_HR_Concept.COLUMNNAME_Value+" , "
				+" con."+I_HR_Concept.COLUMNNAME_SeqNo
				+" FROM "+I_HR_Concept.Table_Name+" con"
				+" WHERE con."+I_HR_Concept.COLUMNNAME_IsCalculated+"=?"
				+" ORDER BY "+I_HR_Concept.COLUMNNAME_SeqNo+" ASC");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, "Y");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resultat.add(rs.getString(I_HR_Concept.COLUMNNAME_Value));
			}
		}
		catch (SQLException e)
		{
			return null;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return resultat;
	}

	public static ArrayList<MHRElementBasePaie> getElementBasePaieInitialValues(String trxName) {
		ArrayList<MHRElementBasePaie> resultat = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT elmt.*"+I_HR_Element_Base_Paie.COLUMNNAME_HR_Element_Base_Paie_ID
				+" FROM "+I_HR_Element_Base_Paie.Table_Name+" elmt"
				+" LEFT JOIN "+I_HR_Rang_Calcul.Table_Name+" rang"
					+" ON rang."+I_HR_Rang_Calcul.COLUMNNAME_HR_Rang_Calcul_ID+"=elmt."+I_HR_Element_Base_Paie.COLUMNNAME_HR_Rang_Calcul_ID
				+" WHERE elmt."+I_HR_Element_Base_Paie.COLUMNNAME_IsCalcul_Initial+"=?"
				+" ORDER BY rang."+I_HR_Rang_Calcul.COLUMNNAME_Rang+" ASC");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, "Y");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resultat.add(new MHRElementBasePaie(Env.getCtx(), rs.getInt(I_HR_Element_Base_Paie.COLUMNNAME_HR_Element_Base_Paie_ID), trxName));
			}
		}
		catch (SQLException e)
		{
			return null;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return resultat;
	}

	public static ArrayList<MHRElementConge> getElementBaseCongeInitialValues(String trxName) {
		ArrayList<MHRElementConge> resultat = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT elmt.*"
				+" FROM "+I_HR_Element_Conge.Table_Name+" elmt"
				+" LEFT JOIN "+I_HR_Rang_Calcul.Table_Name+" rang"
					+" ON rang."+I_HR_Rang_Calcul.COLUMNNAME_HR_Rang_Calcul_ID+"=elmt."+I_HR_Element_Conge.COLUMNNAME_HR_Rang_Calcul_ID
				+" WHERE elmt."+I_HR_Element_Conge.COLUMNNAME_IsCalcul_Initial+"=?"
				+" ORDER BY rang."+I_HR_Rang_Calcul.COLUMNNAME_Rang+" ASC");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, "Y");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resultat.add(new MHRElementConge(Env.getCtx(), rs, trxName));
			}
		}
		catch (SQLException e)
		{
			return null;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return resultat;
	}

	public static BeanBareme getBIABareme(Integer conceptid, BigDecimal amount, String trxName) {
		BeanBareme resultat = null;
		if(conceptid!=null && amount!=null) {
			StringBuilder sql = new StringBuilder("SELECT "+I_BIA_Bareme.COLUMNNAME_Amt
					+", "+I_BIA_Bareme.COLUMNNAME_formule
					+" FROM "+I_BIA_Bareme.Table_Name
					+" WHERE "+I_BIA_Bareme.COLUMNNAME_AmountFrom+"<=?"
					+" AND "+I_BIA_Bareme.COLUMNNAME_AmountTo+">=?"
					+" AND "+I_BIA_Bareme.COLUMNNAME_HR_Concept_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setBigDecimal(1, amount);
				pstmt.setBigDecimal(2, amount);
				pstmt.setInt(3, conceptid);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = BeanFactory.getBareme();
					resultat.setAmountBareme(rs.getBigDecimal(I_BIA_Bareme.COLUMNNAME_Amt));
					resultat.setFormuleBareme(rs.getString(I_BIA_Bareme.COLUMNNAME_formule));
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static MHRBareme getBareme(Integer elementBasePaieID, BigDecimal montant, String trxName) {
		MHRBareme resultat = null;
		if(elementBasePaieID!=null && montant!=null) {
			StringBuilder sql = new StringBuilder("SELECT *"
					+" FROM "+I_HR_Bareme.Table_Name
					+" WHERE "+I_HR_Bareme.COLUMNNAME_Montant_Debut+"<=?"
					+" AND "+I_HR_Bareme.COLUMNNAME_Montant_Fin+">=?"
					+" AND "+I_HR_Bareme.COLUMNNAME_HR_Element_Base_Paie_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setBigDecimal(1, montant);
				pstmt.setBigDecimal(2, montant);
				pstmt.setInt(3, elementBasePaieID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = new MHRBareme(Env.getCtx(), rs, trxName);
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static MHRBaremeConge getBaremeConge(Integer elementCongeID, BigDecimal montant, String trxName) {
		MHRBaremeConge resultat = null;
		if(elementCongeID!=null && montant!=null) {
			StringBuilder sql = new StringBuilder("SELECT *"
					+" FROM "+I_HR_Bareme_Conge.Table_Name
					+" WHERE "+I_HR_Bareme_Conge.COLUMNNAME_Montant_Debut+"<=?"
					+" AND "+I_HR_Bareme_Conge.COLUMNNAME_Montant_Fin+">=?"
					+" AND "+I_HR_Bareme_Conge.COLUMNNAME_HR_Element_Conge_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setBigDecimal(1, montant);
				pstmt.setBigDecimal(2, montant);
				pstmt.setInt(3, elementCongeID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = new MHRBaremeConge(Env.getCtx(), rs, trxName);
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static BigDecimal getChargesSalariales(Integer cbpartnerID, String trxName) {
		BigDecimal resultat =null;
		if(cbpartnerID!=null) {
			StringBuilder sql = new StringBuilder("SELECT SUM("+I_HR_Attribute.Table_Name+"."+I_HR_Attribute.COLUMNNAME_Amount+") AS charges"
					+" FROM "+I_HR_Attribute.Table_Name
					+" WHERE "+I_HR_Attribute.Table_Name+"."+I_HR_Attribute.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+I_HR_Attribute.Table_Name+"."+I_HR_Attribute.COLUMNNAME_HR_Concept_ID+" IN "
						+" (SELECT "+I_HR_Concept.COLUMNNAME_HR_Concept_ID+" FROM "+I_HR_Concept.Table_Name
						+" LEFT JOIN "+I_HR_TypeDeCharge.Table_Name+" ON "+I_HR_TypeDeCharge.Table_Name+"."+I_HR_TypeDeCharge.COLUMNNAME_HR_TypeDeCharge_ID
						+"="+I_HR_Concept.Table_Name+"."+I_HR_Concept.COLUMNNAME_HR_TypeDeCharge_ID
						+" WHERE "+I_HR_TypeDeCharge.Table_Name+"."+I_HR_TypeDeCharge.COLUMNNAME_Name+" IN (?,?)"
						+")");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerID);
				pstmt.setString(2, "AUTRES RETENUES");
				pstmt.setString(3, "CHARGE SALARIALE");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getBigDecimal("charges");
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}


	public static BigDecimal getSumChargesSalariales(Integer cbpartnerID, String trxName) {
		BigDecimal resultat =null;
		if(cbpartnerID!=null) {
			StringBuilder sql = new StringBuilder("SELECT SUM("+I_HR_Calcul_Paie.Table_Name+"."+I_HR_Calcul_Paie.COLUMNNAME_Montant+") AS charges"
					+" FROM "+I_HR_Calcul_Paie.Table_Name
					+" WHERE "+I_HR_Calcul_Paie.Table_Name+"."+I_HR_Calcul_Paie.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+I_HR_Calcul_Paie.Table_Name+"."+I_HR_Calcul_Paie.COLUMNNAME_HR_Element_Base_Paie_ID+" IN "
						+" (SELECT "+I_HR_Element_Base_Paie.COLUMNNAME_HR_Element_Base_Paie_ID
						+" FROM "+I_HR_Element_Base_Paie.Table_Name
						+" LEFT JOIN "+I_HR_TypeDeCharge.Table_Name
							+" ON "+I_HR_TypeDeCharge.Table_Name+"."+I_HR_TypeDeCharge.COLUMNNAME_HR_TypeDeCharge_ID
							+"="+I_HR_Element_Base_Paie.Table_Name+"."+I_HR_Element_Base_Paie.COLUMNNAME_HR_TypeDeCharge_ID
							+" WHERE "+I_HR_TypeDeCharge.Table_Name+"."+I_HR_TypeDeCharge.COLUMNNAME_Name+" IN (?,?)"
							+")");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerID);
				pstmt.setString(2, "AUTRES RETENUES");
				pstmt.setString(3, "CHARGE SALARIALE");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getBigDecimal("charges");
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static BigDecimal getSumRetenueConge(Integer cbpartnerID, String trxName) {
		BigDecimal resultat =null;
		if(cbpartnerID!=null) {
			StringBuilder sql = new StringBuilder("SELECT SUM("+I_HR_Calcul_Conge.Table_Name+"."+I_HR_Calcul_Conge.COLUMNNAME_Montant+") AS charges"
					+" FROM "+I_HR_Calcul_Conge.Table_Name
					+" WHERE "+I_HR_Calcul_Conge.Table_Name+"."+I_HR_Calcul_Conge.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+I_HR_Calcul_Conge.Table_Name+"."+I_HR_Calcul_Conge.COLUMNNAME_HR_Element_Conge_ID+" IN "
						+" (SELECT "+I_HR_Element_Conge.COLUMNNAME_HR_Element_Conge_ID
						+" FROM "+I_HR_Element_Conge.Table_Name
						+" LEFT JOIN "+I_HR_TypeDeCharge.Table_Name
							+" ON "+I_HR_TypeDeCharge.Table_Name+"."+I_HR_TypeDeCharge.COLUMNNAME_HR_TypeDeCharge_ID
							+"="+I_HR_Element_Conge.Table_Name+"."+I_HR_Element_Conge.COLUMNNAME_HR_TypeDeCharge_ID
							+" WHERE "+I_HR_TypeDeCharge.Table_Name+"."+I_HR_TypeDeCharge.COLUMNNAME_Name+" = ?"
							+")");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerID);
				pstmt.setString(2, "RETENUE CONGÉ");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getBigDecimal("charges");
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}


	public static Integer getGestionPresenceID(Integer cbpartnerid, Integer periodeSalarialeID, String trxName) {
		Integer resultat =null;
		if(periodeSalarialeID!=null && cbpartnerid!=null) {
			StringBuilder sql = new StringBuilder("SELECT "+I_HR_Gestion_Presence.COLUMNNAME_HR_Gestion_Presence_ID
					+" FROM "+I_HR_Gestion_Presence.Table_Name
					+" WHERE "+I_HR_Gestion_Presence.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+I_HR_Gestion_Presence.COLUMNNAME_HR_Periode_Salariale_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				pstmt.setInt(2, periodeSalarialeID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getInt(I_HR_Gestion_Presence.COLUMNNAME_HR_Gestion_Presence_ID);
				}
			}
			catch (SQLException e)
			{
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}
}
