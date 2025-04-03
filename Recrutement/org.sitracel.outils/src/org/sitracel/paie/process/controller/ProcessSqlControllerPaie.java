package org.sitracel.paie.process.controller;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanBareme;
import org.sitracel.bean.BeanElmtPaie;
import org.sitracel.bean.BeanJourNonTravaille;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRTypeAbsence;
import org.sitracel.paie.model.MBIABareme;
import org.sitracel.paie.model.MHRAttribute;
import org.sitracel.paie.model.MHRConcept;
import org.sitracel.paie.model.MHRConceptCategory;
import org.sitracel.paie.model.MHRTypeDeCharge;

public class ProcessSqlControllerPaie {
	public static CLogger log = CLogger.getCLogger(PO.class);
	public static BeanJourNonTravaille getNonJourPaie (Integer cBPartnerID, Timestamp dateDebut, Timestamp dateFin , String trxName)
	{
		BeanJourNonTravaille resultat = BeanFactory.getBeanJourNonTravaille();
		if(cBPartnerID!=null && dateDebut!=null && dateFin!=null) {
			String sql ="SELECT COUNT(CASE WHEN tabs."+MHRTypeAbsence.COLUMNNAME_Nom_Absence+"='Suspendu' THEN 1 END) AS JourSuspendu"
							+" COUNT(CASE WHEN tabs."+MHRTypeAbsence.COLUMNNAME_Nom_Absence+"='En Congé' THEN 1 END) AS JourConges"
							+" FROM "+MHRAbsence.Table_Name+" abs"
							+" LEFT JOIN "+MHRTypeAbsence.Table_Name+" tabs "
									+ "ON tabs."+MHRTypeAbsence.COLUMNNAME_HR_Type_Absence_ID+"abs."+MHRAbsence.COLUMNNAME_HR_Type_Absence_ID
							+" WHERE abs."+MHRAbsence.COLUMNNAME_C_BPartner_ID+"=?"
							+" AND abs."+MHRAbsence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cBPartnerID);
				pstmt.setTimestamp(2, dateDebut);
				pstmt.setTimestamp(3, dateFin);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat.setcBpartnerID(cBPartnerID);
					resultat.setNombreJourConge(rs.getInt("JourConges"));
					resultat.setNombreJourSuspendu(rs.getInt("JourSuspendu"));
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
	
	public static Integer getAttributIDbyValue(Integer cbpartnerid, String value, String trxName) {
		Integer resultat =null;
		if(value!=null && cbpartnerid!=null) {
			String sql ="SELECT att."+MHRAttribute.COLUMNNAME_HR_Attribute_ID
					+" FROM "+MHRAttribute.Table_Name+" att"
					+" LEFT JOIN "+MHRConcept.Table_Name+" con "
							+ "ON con."+MHRConcept.COLUMNNAME_HR_Concept_ID+"=att."+MHRAttribute.COLUMNNAME_HR_Concept_ID
					+" WHERE att."+MHRAttribute.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND con."+MHRConcept.COLUMNNAME_Value+"=?";	
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
			String sql ="SELECT att."+MHRAttribute.COLUMNNAME_Amount
					+" FROM "+MHRAttribute.Table_Name+" att"
					+" LEFT JOIN "+MHRConcept.Table_Name+" con "
							+ "ON con."+MHRConcept.COLUMNNAME_HR_Concept_ID+"=att."+MHRAttribute.COLUMNNAME_HR_Concept_ID
					+" WHERE att."+MHRAttribute.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND con."+MHRConcept.COLUMNNAME_Value+"=?";	
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
	
	public static Boolean isAttributExist (Integer cbpartnerid, String value, String trxName)
	{
		Boolean resultat = false;
		if(cbpartnerid!=null && value!=null) {
			String sql = "SELECT EXISTS (SELECT 1 "
					+" FROM "+MHRAttribute.Table_Name+" att"
					+" LEFT JOIN "+MHRConcept.Table_Name+" con "
							+ "ON con."+MHRConcept.COLUMNNAME_HR_Concept_ID+"=att."+MHRAttribute.COLUMNNAME_HR_Concept_ID
					+" WHERE att."+MHRAttribute.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND con."+MHRConcept.COLUMNNAME_Value+"=?)";
			
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
	
	public static BeanElmtPaie getConceptInfobyValue(String value, String trxName) {
		BeanElmtPaie resultat =null;
		if(value!=null) {
			String sql ="SELECT con."+MHRConcept.COLUMNNAME_HR_Concept_ID+" , "
					+" con."+MHRConcept.COLUMNNAME_Bia_BaseElmt_ID+" , "
					+" con."+MHRConcept.COLUMNNAME_Bia_Pourcentage+" , "
					+" con."+MHRConcept.COLUMNNAME_formule
					+" FROM "+MHRConcept.Table_Name+" con"
					+" WHERE con."+MHRConcept.COLUMNNAME_Value+"=?";			
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
	
	public static ArrayList<String> getConceptValues(String trxName) {
		ArrayList<String> resultat = new ArrayList<String>();
		String sql ="SELECT con."+MHRConcept.COLUMNNAME_Value+" , "
				+" con."+MHRConcept.COLUMNNAME_SeqNo
				+" FROM "+MHRConcept.Table_Name+" con"
				+" WHERE con."+MHRConcept.COLUMNNAME_IsCalculated+"=?"
				+" ORDER BY "+MHRConcept.COLUMNNAME_SeqNo+" ASC";	
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
	
	public static BeanBareme getBareme(Integer conceptid, BigDecimal amount, String trxName) {
		BeanBareme resultat = null;
		if(conceptid!=null && amount!=null) {
			String sql ="SELECT "+MBIABareme.COLUMNNAME_Amt
					+", "+MBIABareme.COLUMNNAME_formule
					+" FROM "+MBIABareme.Table_Name
					+" WHERE "+MBIABareme.COLUMNNAME_AmountFrom+"<=?"
					+" AND "+MBIABareme.COLUMNNAME_AmountTo+">=?"
					+" AND "+MBIABareme.COLUMNNAME_HR_Concept_ID+"=?";	
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
	
	public static BigDecimal getChargesSalariales(Integer cbpartnerID, String trxName) {
		BigDecimal resultat =null;
		if(cbpartnerID!=null) {
			String sql ="SELECT SUM("+MHRAttribute.Table_Name+"."+MHRAttribute.COLUMNNAME_Amount+") AS charges"
					+" FROM "+MHRAttribute.Table_Name
					+" WHERE "+MHRAttribute.Table_Name+"."+MHRAttribute.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+MHRAttribute.Table_Name+"."+MHRAttribute.COLUMNNAME_HR_Concept_ID+" IN "
						+" (SELECT "+MHRConcept.COLUMNNAME_HR_Concept_ID+" FROM "+MHRConcept.Table_Name
						+" LEFT JOIN "+MHRTypeDeCharge.Table_Name+" ON "+MHRTypeDeCharge.Table_Name+"."+MHRTypeDeCharge.COLUMNNAME_HR_TypeDeCharge_ID
						+"="+MHRConcept.Table_Name+"."+MHRConcept.COLUMNNAME_HR_TypeDeCharge_ID
						+" WHERE "+MHRTypeDeCharge.Table_Name+"."+MHRTypeDeCharge.COLUMNNAME_Name+" IN (?,?)"
						+")";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerID);
				pstmt.setString(2, "AUTRES");
				pstmt.setString(3, "SALARIALE");
				log.warning("\nCHARGES SALARIALES BEFORE :"+sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					log.warning("\nCHARGES SALARIALES :"+rs.getBigDecimal("charges"));
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
}
