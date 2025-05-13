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
import org.sitracel.paie.model.MBIABareme;
import org.sitracel.paie.model.MHRAttribute;
import org.sitracel.paie.model.MHRBareme;
import org.sitracel.paie.model.MHRBaremeConge;
import org.sitracel.paie.model.MHRCalculConge;
import org.sitracel.paie.model.MHRCalculPaie;
import org.sitracel.paie.model.MHRConcept;
import org.sitracel.paie.model.MHRElementBasePaie;
import org.sitracel.paie.model.MHRElementConge;
import org.sitracel.paie.model.MHRGestionPresence;
import org.sitracel.paie.model.MHRRangCalcul;
import org.sitracel.paie.model.MHRTypeDeCharge;

public class ProcessSqlControllerPaie {
	public static CLogger log = CLogger.getCLogger(PO.class);

	public static Integer getAttributIDbyValue(Integer cbpartnerid, String value, String trxName) {
		Integer resultat =null;
		if(value!=null && cbpartnerid!=null) {
			StringBuilder sql = new StringBuilder("SELECT att."+MHRAttribute.COLUMNNAME_HR_Attribute_ID
					+" FROM "+MHRAttribute.Table_Name+" att"
					+" INNER JOIN "+MHRConcept.Table_Name+" con "
							+ "ON con."+MHRConcept.COLUMNNAME_HR_Concept_ID+"=att."+MHRAttribute.COLUMNNAME_HR_Concept_ID
					+" WHERE att."+MHRAttribute.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND con."+MHRConcept.COLUMNNAME_Value+"=?");	
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				pstmt.setString(2, value);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getInt(MHRAttribute.COLUMNNAME_HR_Attribute_ID);
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
			StringBuilder sql = new StringBuilder("SELECT att."+MHRAttribute.COLUMNNAME_Amount
					+" FROM "+MHRAttribute.Table_Name+" att"
					+" INNER JOIN "+MHRConcept.Table_Name+" con "
							+ "ON con."+MHRConcept.COLUMNNAME_HR_Concept_ID+"=att."+MHRAttribute.COLUMNNAME_HR_Concept_ID
					+" WHERE att."+MHRAttribute.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND con."+MHRConcept.COLUMNNAME_Value+"=?");	
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				pstmt.setString(2, value);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getBigDecimal(MHRAttribute.COLUMNNAME_Amount);
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
					+" FROM "+MHRCalculPaie.Table_Name+" cal"
					+" INNER JOIN "+MHRElementBasePaie.Table_Name+" elmt "
							+ "ON elmt."+MHRElementBasePaie.COLUMNNAME_HR_Element_Base_Paie_ID+"=cal."+MHRCalculPaie.COLUMNNAME_HR_Element_Base_Paie_ID
							+" AND elmt."+MHRElementBasePaie.COLUMNNAME_Value+"=?"
					+" WHERE cal."+MHRCalculPaie.COLUMNNAME_C_BPartner_ID+"=?");
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
					+" FROM "+MHRCalculConge.Table_Name+" cal"
					+" INNER JOIN "+MHRElementConge.Table_Name+" elmt "
							+ "ON elmt."+MHRElementConge.COLUMNNAME_HR_Element_Conge_ID+"=cal."+MHRCalculConge.COLUMNNAME_HR_Element_Conge_ID
							+" AND elmt."+MHRElementConge.COLUMNNAME_Value+"=?"
					+" WHERE cal."+MHRCalculConge.COLUMNNAME_C_BPartner_ID+"=?");
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
					+" FROM "+MHRAttribute.Table_Name+" att"
					+" INNER JOIN "+MHRConcept.Table_Name+" con "
							+ "ON con."+MHRConcept.COLUMNNAME_HR_Concept_ID+"=att."+MHRAttribute.COLUMNNAME_HR_Concept_ID
					+" WHERE att."+MHRAttribute.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND con."+MHRConcept.COLUMNNAME_Value+"=?)");
			
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
					+" FROM "+MHRCalculPaie.Table_Name+" cal"
					+" INNER JOIN "+MHRElementBasePaie.Table_Name+" elmt "
							+ "ON elmt."+MHRElementBasePaie.COLUMNNAME_HR_Element_Base_Paie_ID+"=cal."+MHRCalculPaie.COLUMNNAME_HR_Element_Base_Paie_ID
							+" AND elmt."+MHRElementBasePaie.COLUMNNAME_Value+"=?"
					+" WHERE cal."+MHRCalculPaie.COLUMNNAME_C_BPartner_ID+"=?)");
			
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
					+" FROM "+MHRGestionPresence.Table_Name
					+" WHERE "+MHRGestionPresence.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+MHRGestionPresence.COLUMNNAME_HR_Periode_Salariale_ID+"=?)");
			
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
			StringBuilder sql = new StringBuilder("SELECT "+MHRGestionPresence.COLUMNNAME_HR_Gestion_Presence_ID
					+" FROM "+MHRGestionPresence.Table_Name
					+" WHERE "+MHRGestionPresence.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+MHRGestionPresence.COLUMNNAME_HR_Periode_Salariale_ID+"=?");
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				pstmt.setInt(2, periodeSalarialeID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = new MHRGestionPresence(Env.getCtx(), rs.getInt(MHRGestionPresence.COLUMNNAME_HR_Gestion_Presence_ID), trxName);
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
			StringBuilder sql = new StringBuilder("SELECT con."+MHRConcept.COLUMNNAME_HR_Concept_ID+" , "
					+" con."+MHRConcept.COLUMNNAME_Bia_BaseElmt_ID+" , "
					+" con."+MHRConcept.COLUMNNAME_Bia_Pourcentage+" , "
					+" con."+MHRConcept.COLUMNNAME_formule
					+" FROM "+MHRConcept.Table_Name+" con"
					+" WHERE con."+MHRConcept.COLUMNNAME_Value+"=?");			
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
					resultat.setConceptID(rs.getInt(MHRConcept.COLUMNNAME_HR_Concept_ID));
					resultat.setBaseCalculID(rs.getInt(MHRConcept.COLUMNNAME_Bia_BaseElmt_ID));
					resultat.setTaux(rs.getBigDecimal(MHRConcept.COLUMNNAME_Bia_Pourcentage));
					resultat.setFormule(rs.getString(MHRConcept.COLUMNNAME_formule));
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
					+" FROM "+MHRElementBasePaie.Table_Name
					+" WHERE "+MHRElementBasePaie.COLUMNNAME_Value+"=?");			
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
					+" FROM "+MHRElementConge.Table_Name
					+" WHERE "+MHRElementConge.COLUMNNAME_Value+"=?");			
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
		ArrayList<String> resultat = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("SELECT con."+MHRConcept.COLUMNNAME_Value+" , "
				+" con."+MHRConcept.COLUMNNAME_SeqNo
				+" FROM "+MHRConcept.Table_Name+" con"
				+" WHERE con."+MHRConcept.COLUMNNAME_IsCalculated+"=?"
				+" ORDER BY "+MHRConcept.COLUMNNAME_SeqNo+" ASC");	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, "Y");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resultat.add(rs.getString(MHRConcept.COLUMNNAME_Value)); 
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
		ArrayList<MHRElementBasePaie> resultat = new ArrayList<MHRElementBasePaie>();
		StringBuilder sql = new StringBuilder("SELECT elmt.*"+MHRElementBasePaie.COLUMNNAME_HR_Element_Base_Paie_ID
				+" FROM "+MHRElementBasePaie.Table_Name+" elmt"
				+" LEFT JOIN "+MHRRangCalcul.Table_Name+" rang"
					+" ON rang."+MHRRangCalcul.COLUMNNAME_HR_Rang_Calcul_ID+"=elmt."+MHRElementBasePaie.COLUMNNAME_HR_Rang_Calcul_ID
				+" WHERE elmt."+MHRElementBasePaie.COLUMNNAME_IsCalcul_Initial+"=?"
				+" ORDER BY rang."+MHRRangCalcul.COLUMNNAME_Rang+" ASC");	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, "Y");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resultat.add(new MHRElementBasePaie(Env.getCtx(), rs.getInt(MHRElementBasePaie.COLUMNNAME_HR_Element_Base_Paie_ID), trxName)); 
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
		ArrayList<MHRElementConge> resultat = new ArrayList<MHRElementConge>();
		StringBuilder sql = new StringBuilder("SELECT elmt.*"
				+" FROM "+MHRElementConge.Table_Name+" elmt"
				+" LEFT JOIN "+MHRRangCalcul.Table_Name+" rang"
					+" ON rang."+MHRRangCalcul.COLUMNNAME_HR_Rang_Calcul_ID+"=elmt."+MHRElementConge.COLUMNNAME_HR_Rang_Calcul_ID
				+" WHERE elmt."+MHRElementConge.COLUMNNAME_IsCalcul_Initial+"=?"
				+" ORDER BY rang."+MHRRangCalcul.COLUMNNAME_Rang+" ASC");	
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
			StringBuilder sql = new StringBuilder("SELECT "+MBIABareme.COLUMNNAME_Amt
					+", "+MBIABareme.COLUMNNAME_formule
					+" FROM "+MBIABareme.Table_Name
					+" WHERE "+MBIABareme.COLUMNNAME_AmountFrom+"<=?"
					+" AND "+MBIABareme.COLUMNNAME_AmountTo+">=?"
					+" AND "+MBIABareme.COLUMNNAME_HR_Concept_ID+"=?");	
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
					resultat.setAmountBareme(rs.getBigDecimal(MBIABareme.COLUMNNAME_Amt));
					resultat.setFormuleBareme(rs.getString(MBIABareme.COLUMNNAME_formule));
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
					+" FROM "+MHRBareme.Table_Name
					+" WHERE "+MHRBareme.COLUMNNAME_Montant_Debut+"<=?"
					+" AND "+MHRBareme.COLUMNNAME_Montant_Fin+">=?"
					+" AND "+MHRBareme.COLUMNNAME_HR_Element_Base_Paie_ID+"=?");	
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
					+" FROM "+MHRBaremeConge.Table_Name
					+" WHERE "+MHRBaremeConge.COLUMNNAME_Montant_Debut+"<=?"
					+" AND "+MHRBaremeConge.COLUMNNAME_Montant_Fin+">=?"
					+" AND "+MHRBaremeConge.COLUMNNAME_HR_Element_Conge_ID+"=?");	
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
			StringBuilder sql = new StringBuilder("SELECT SUM("+MHRAttribute.Table_Name+"."+MHRAttribute.COLUMNNAME_Amount+") AS charges"
					+" FROM "+MHRAttribute.Table_Name
					+" WHERE "+MHRAttribute.Table_Name+"."+MHRAttribute.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+MHRAttribute.Table_Name+"."+MHRAttribute.COLUMNNAME_HR_Concept_ID+" IN "
						+" (SELECT "+MHRConcept.COLUMNNAME_HR_Concept_ID+" FROM "+MHRConcept.Table_Name
						+" LEFT JOIN "+MHRTypeDeCharge.Table_Name+" ON "+MHRTypeDeCharge.Table_Name+"."+MHRTypeDeCharge.COLUMNNAME_HR_TypeDeCharge_ID
						+"="+MHRConcept.Table_Name+"."+MHRConcept.COLUMNNAME_HR_TypeDeCharge_ID
						+" WHERE "+MHRTypeDeCharge.Table_Name+"."+MHRTypeDeCharge.COLUMNNAME_Name+" IN (?,?)"
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
			StringBuilder sql = new StringBuilder("SELECT SUM("+MHRCalculPaie.Table_Name+"."+MHRCalculPaie.COLUMNNAME_Montant+") AS charges"
					+" FROM "+MHRCalculPaie.Table_Name
					+" WHERE "+MHRCalculPaie.Table_Name+"."+MHRCalculPaie.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+MHRCalculPaie.Table_Name+"."+MHRCalculPaie.COLUMNNAME_HR_Element_Base_Paie_ID+" IN "
						+" (SELECT "+MHRElementBasePaie.COLUMNNAME_HR_Element_Base_Paie_ID
						+" FROM "+MHRElementBasePaie.Table_Name
						+" LEFT JOIN "+MHRTypeDeCharge.Table_Name
							+" ON "+MHRTypeDeCharge.Table_Name+"."+MHRTypeDeCharge.COLUMNNAME_HR_TypeDeCharge_ID
							+"="+MHRElementBasePaie.Table_Name+"."+MHRElementBasePaie.COLUMNNAME_HR_TypeDeCharge_ID
							+" WHERE "+MHRTypeDeCharge.Table_Name+"."+MHRTypeDeCharge.COLUMNNAME_Name+" IN (?,?)"
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
			StringBuilder sql = new StringBuilder("SELECT SUM("+MHRCalculConge.Table_Name+"."+MHRCalculConge.COLUMNNAME_Montant+") AS charges"
					+" FROM "+MHRCalculConge.Table_Name
					+" WHERE "+MHRCalculConge.Table_Name+"."+MHRCalculConge.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+MHRCalculConge.Table_Name+"."+MHRCalculConge.COLUMNNAME_HR_Element_Conge_ID+" IN "
						+" (SELECT "+MHRElementConge.COLUMNNAME_HR_Element_Conge_ID
						+" FROM "+MHRElementConge.Table_Name
						+" LEFT JOIN "+MHRTypeDeCharge.Table_Name
							+" ON "+MHRTypeDeCharge.Table_Name+"."+MHRTypeDeCharge.COLUMNNAME_HR_TypeDeCharge_ID
							+"="+MHRElementConge.Table_Name+"."+MHRElementConge.COLUMNNAME_HR_TypeDeCharge_ID
							+" WHERE "+MHRTypeDeCharge.Table_Name+"."+MHRTypeDeCharge.COLUMNNAME_Name+" = ?"
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
			StringBuilder sql = new StringBuilder("SELECT "+MHRGestionPresence.COLUMNNAME_HR_Gestion_Presence_ID
					+" FROM "+MHRGestionPresence.Table_Name
					+" WHERE "+MHRGestionPresence.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+MHRGestionPresence.COLUMNNAME_HR_Periode_Salariale_ID+"=?");	
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				pstmt.setInt(2, periodeSalarialeID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getInt(MHRGestionPresence.COLUMNNAME_HR_Gestion_Presence_ID);
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
