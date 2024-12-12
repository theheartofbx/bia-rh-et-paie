package org.sitracel.conge.callout.absence.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.util.DB;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAutorisationConge;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.conge.model.MHRTypeAbsence;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.controller.GeneralController;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;

public class CalloutSqlControllerAbsence {

	public static BeanPeriode[] getAllCongesAnnuel (Integer idCBPartner, Timestamp anyDayOfYear, String trxName)
	{
		ArrayList<BeanPeriode> listePeriodeConge = new ArrayList<BeanPeriode>();
		if(idCBPartner!=null && anyDayOfYear!=null) {
			Timestamp firstDayOfYear = GeneralController.getFirstDayOfaYear(anyDayOfYear);
			Timestamp lastDayOfYear = GeneralController.getLastDayOfaYear(anyDayOfYear);
			StringBuilder sql = new StringBuilder("SELECT hol.");
			sql.append(MHRHoliday.COLUMNNAME_Date_Debut_Effective)
			.append(", hol.").append(MHRHoliday.COLUMNNAME_Date_Fin_Effective)
			.append(" FROM ").append(MHRHoliday.Table_Name).append(" hol ")
			.append("LEFT JOIN ").append(MHRAutorisationConge.Table_Name).append(" droitconge ON droitconge.")
			.append(MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID).append("=hol.").append(MHRHoliday.COLUMNNAME_Emission_Conge_ID)
			.append(" LEFT JOIN ").append(MHRTypeConge.Table_Name).append(" typeconge ON typeconge.")
			.append(MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID).append("droitconge.").append(MHRAutorisationConge.COLUMNNAME_HR_Type_Conge_ID)
			.append(" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID).append("=?")
			.append(" AND hol.").append(MHRHoliday.COLUMNNAME_Date_Debut_Effective).append(" BETWEEN ? AND ?")
			.append(" AND hol.").append(MHRHoliday.COLUMNNAME_Date_Fin_Effective).append(" BETWEEN ? AND ?")
			.append(" AND hol.").append(MHRHoliday.COLUMNNAME_IsValidee).append("=?")
			.append(" AND typeconge.").append(MHRTypeConge.COLUMNNAME_IsCongeAnnuel).append("=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setTimestamp(2, firstDayOfYear);
				pstmt.setTimestamp(3, lastDayOfYear);
				pstmt.setTimestamp(2, firstDayOfYear);
				pstmt.setTimestamp(3, lastDayOfYear);
				pstmt.setString(4, "Y");
				pstmt.setString(5, "Y");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanPeriode beanPeriode =BeanFactory.getBeanPeriode();
					beanPeriode.setDateDebutConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Debut_Effective));
					beanPeriode.setDateFinConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Debut_Effective));
					listePeriodeConge.add(beanPeriode);
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
		//	Convert to array
		BeanPeriode[] periodesConges = new BeanPeriode[listePeriodeConge.size()];
		for (int i = 0; i < periodesConges.length; i++)
			periodesConges[i] = ((BeanPeriode)listePeriodeConge.get(i));
		return periodesConges;
	}
	
	public static BeanPeriode[] getAllPeriodeSuspenion (Integer idCBPartner, Timestamp anyDayOfYear, String trxName)
	{
		ArrayList<BeanPeriode> listePeriodesSusoensions = new ArrayList<BeanPeriode>();
		if(idCBPartner!=null && anyDayOfYear!=null) {
			Timestamp firstDayOfYear = GeneralController.getFirstDayOfaYear(anyDayOfYear);
			Timestamp lastDayOfYear = GeneralController.getLastDayOfaYear(anyDayOfYear);
			StringBuilder sql = new StringBuilder("SELECT pun.");
			sql.append(MHRPunishment.COLUMNNAME_Date_Debut_Application)
			.append(", pun.").append(MHRPunishment.COLUMNNAME_Date_Fin_Application)
			.append(" FROM ").append(MHRPunishment.Table_Name).append(" pun ")
			.append("LEFT JOIN ").append(MHRSanctionAutorisation.Table_Name).append(" droitSanction ON droitSanction.")
			.append(MHRSanctionAutorisation.COLUMNNAME_HR_Sanction_Autorisation_ID).append("=pun.").append(MHRPunishment.COLUMNNAME_Emission_Sanction_ID)
			.append(" LEFT JOIN ").append(MHRTypeSanction.Table_Name).append(" typeSanction ON typeSanction.")
			.append(MHRTypeSanction.COLUMNNAME_HR_TypeSanction_ID).append("droitSanction.").append(MHRSanctionAutorisation.COLUMNNAME_HR_TypeSanction_ID)
			.append(" WHERE pun."+MHRPunishment.COLUMNNAME_C_BPartner_ID).append("=?")
			.append(" AND pun.").append(MHRPunishment.COLUMNNAME_Date_Debut_Application).append(" BETWEEN ? AND ?")
			.append(" OR pun.").append(MHRPunishment.COLUMNNAME_Date_Fin_Application).append(" BETWEEN ? AND ?")
			.append(" AND pun.").append(MHRPunishment.COLUMNNAME_IsValidee).append("=?")
			.append(" AND typeSanction.").append(MHRTypeSanction.COLUMNNAME_Incidence_Sanction_ID).append("=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setTimestamp(2, firstDayOfYear);
				pstmt.setTimestamp(3, lastDayOfYear);
				pstmt.setTimestamp(2, firstDayOfYear);
				pstmt.setTimestamp(3, lastDayOfYear);
				pstmt.setString(4, "Y");
				pstmt.setString(5, MHRTypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanPeriode beanPeriode =BeanFactory.getBeanPeriode();
					beanPeriode.setDateDebutConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Debut_Effective));
					beanPeriode.setDateFinConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Debut_Effective));
					listePeriodesSusoensions.add(beanPeriode);
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
		//	Convert to array
		BeanPeriode[] periodesSuspensions = new BeanPeriode[listePeriodesSusoensions.size()];
		for (int i = 0; i < periodesSuspensions.length; i++)
			periodesSuspensions[i] = ((BeanPeriode)listePeriodesSusoensions.get(i));
		return periodesSuspensions;
	}	

	public static Timestamp[] getAllJoursFeries (Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<Timestamp> list = new ArrayList<Timestamp>();
		StringBuilder sql = new StringBuilder("SELECT ");
		sql.append(MHRPublicHoliday.Table_Name+"."+MHRPublicHoliday.COLUMNNAME_Date_Jour_Ferie).
		append(" FROM ").append(MHRPublicHoliday.Table_Name).append(" WHERE "+MHRPublicHoliday.COLUMNNAME_Date_Jour_Ferie)
		.append(" BETWEEN ?::timestamp AND ?::timestamp");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setTimestamp(1, dateDebut);
			pstmt.setTimestamp(2, dateFin);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(rs.getTimestamp(MHRPublicHoliday.COLUMNNAME_Date_Jour_Ferie));
		}
		catch (SQLException e)
		{
			return null;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	Convert to array
		Timestamp[] retValue = new Timestamp[list.size()];
		for (int i = 0; i < retValue.length; i++)
			retValue[i] = ((Timestamp)list.get(i));
		return retValue;
	}

	public static boolean isAbsenceExist(Timestamp date, Integer cBpartnerID, String trxName) {
		boolean resultat = true;
		if(date!=null && cBpartnerID!=null) {
			String sql = "SELECT * FROM "+MHRAbsence.Table_Name
					+" WHERE "+MHRAbsence.COLUMNNAME_C_BPartner_ID+" = ? ";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cBpartnerID);
				rs = pstmt.executeQuery();
				resultat = false;
				while (rs.next() && !resultat) {
					if(date.compareTo(rs.getTimestamp(MHRAbsence.COLUMNNAME_Date_Absence))==0) {
						resultat=true;
					}
				}
			}
			catch (SQLException e)
			{
				return resultat;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}		
		}
		return resultat;
	}
	
	public static ArrayList<Integer> getListeAbsenceID(Integer cBpartnerID, String nomTypeAbsence, Timestamp dateDebut , Timestamp dateFin,  String trxName) {
		ArrayList<Integer> resultat = new ArrayList<Integer>();
		if(cBpartnerID!=null && nomTypeAbsence!=null && dateDebut!=null && dateFin!=null) {
			String sql = "SELECT "+MHRAbsence.COLUMNNAME_HR_Absence_ID
					+ " FROM "+MHRAbsence.Table_Name+" absence"
					+ " LEFT JOIN "+MHRTypeAbsence.Table_Name+" typeabsence"
					+ " ON typeabsence."+MHRTypeAbsence.COLUMNNAME_HR_Type_Absence_ID+"=absence."+MHRAbsence.COLUMNNAME_HR_Type_Absence_ID
					+ " WHERE absence."+MHRAbsence.COLUMNNAME_C_BPartner_ID+" = ? "
					+ " AND absence."+MHRAbsence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?"
					+ " AND typeabsence."+MHRTypeAbsence.COLUMNNAME_Nom_Absence+"=?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cBpartnerID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					
				}
			}
			catch (SQLException e)
			{
				return resultat;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}		
		return resultat;
	}
}
