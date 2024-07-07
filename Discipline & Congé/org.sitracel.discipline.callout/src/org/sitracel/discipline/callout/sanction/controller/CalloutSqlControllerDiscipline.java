package org.sitracel.discipline.callout.sanction.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.callout.bean.BeanAbsence;
import org.sitracel.callout.bean.BeanPeriode;
import org.sitracel.callout.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;
import org.sitracel.general.controller.GeneralController;
import org.sitracel.model.MHROrganigramme;

public class CalloutSqlControllerDiscipline {

	public static Integer getAutorisationSanctionID (Integer typeSanction_ID, Integer poste_ID, Integer posteResponsable_ID, String trxName)
	{	
		Integer resultat = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			//Image image = ImageIO.read(new File(sitracelLogoPath));
			if(typeSanction_ID!=null && poste_ID!=null && posteResponsable_ID!=null) {
				String sql = "SELECT HR_Sanction_Autorisation_ID id "
						+ "FROM HR_Sanction_Autorisation sanc WHERE "
						+ "sanc.HR_TypeSanction_ID=? AND "
						+ "sanc.HR_Categorie_Responsabilite_ID="
						+ "(SELECT HR_Categorie_Responsabilite_ID FROM "
						+ "HR_Organigramme org WHERE org.Poste_ID=? AND "
						+ "org.Poste_Responsable_ID=?"
						+ ")";
				
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, typeSanction_ID);
				pstmt.setInt(2, poste_ID);
				pstmt.setInt(3, posteResponsable_ID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = rs.getInt("id");
				}
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();				
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return resultat;
	}
	
	public static MHROrganigramme getOrganigramme (Integer poste_ID, Integer posteResponsable_ID, String trxName)
	{	
		MHROrganigramme resultat = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			//Image image = ImageIO.read(new File(sitracelLogoPath));
			if(poste_ID!=null && posteResponsable_ID!=null) {
				String sql = "SELECT HR_Organigramme_ID id "
						+ "FROM HR_Organigramme org WHERE "
						+ "org.Poste_ID=? AND "
						+ "org.Poste_Responsable_ID=?";
				
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, poste_ID);
				pstmt.setInt(2, posteResponsable_ID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = new MHROrganigramme(Env.getCtx(), rs.getInt("id"), trxName) ;
				}
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();				
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return resultat;
	}	

	public static BeanPeriode[] getAllConges (Integer idCBPartner, String trxName)
	{
		ArrayList<BeanPeriode> list = new ArrayList<BeanPeriode>();
		if(idCBPartner!=null ) {
			
			StringBuilder sql = new StringBuilder("SELECT ");
			sql.append(MHRHoliday.Table_Name).append(".").append(MHRHoliday.COLUMNNAME_Date_Debut_Effective)
			.append(", ").append(MHRHoliday.Table_Name).append(".").append(MHRHoliday.COLUMNNAME_Date_Fin_Effective)
			.append(" FROM ").append(MHRHoliday.Table_Name).append(" WHERE ").append(MHRHoliday.Table_Name+".").append(MHRHoliday.COLUMNNAME_C_BPartner_ID)
			.append("=?").append(" AND ").append(MHRHoliday.Table_Name+".")
			.append(MHRHoliday.COLUMNNAME_Date_Debut_Effective).append(">?").append(" AND ").append(MHRHoliday.Table_Name+".").append(MHRHoliday.COLUMNNAME_Date_Fin_Effective)
			.append("<?").append(" AND ").append(MHRHoliday.Table_Name+".")
			.append(MHRHoliday.COLUMNNAME_IsRejetee).append("!=?")
			;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setTimestamp(2, GeneralController.getFirstDayOfThisYear());
				pstmt.setTimestamp(3, GeneralController.getLastDayOfThisYear());
				pstmt.setString(4, "Y");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanPeriode bp =BeanFactory.getBeanPeriode();
					bp.setDateDebutConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Debut_Effective));
					bp.setDateFinConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Debut_Effective));
					list.add(bp);
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
		BeanPeriode[] retValue = new BeanPeriode[list.size()];
		for (int i = 0; i < retValue.length; i++)
			retValue[i] = ((BeanPeriode)list.get(i));
		return retValue;
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
			.append(" AND pun.").append(MHRPunishment.COLUMNNAME_IsRejetee).append("!=?")
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
				pstmt.setString(4, "N");
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

	public static BeanAbsence[] getAllAbsenceConge (Integer cBpartnerID, Timestamp date, String trxName)
	{
		ArrayList<BeanAbsence> listeAbsences = new ArrayList<BeanAbsence>();
		if(cBpartnerID!=null && date!=null) {
			Timestamp firstDayOfYear = GeneralController.getFirstDayOfaYear(date);
			Timestamp lasttDayOfYear = GeneralController.getLastDayOfaYear(date);
			StringBuilder sql = new StringBuilder("SELECT ");
			sql.append(MHRAbsence.COLUMNNAME_HR_Absence_ID)
			.append(", ").append(MHRAbsence.COLUMNNAME_Date_Absence)
			.append(" FROM ").append(MHRAbsence.Table_Name).append(" WHERE "+MHRAbsence.COLUMNNAME_C_BPartner_ID)
			.append(" = ? AND "+MHRAbsence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cBpartnerID);
				pstmt.setTimestamp(2, firstDayOfYear);
				pstmt.setTimestamp(3, lasttDayOfYear);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanAbsence beanAbsence = BeanFactory.getBeanAbsence();
					beanAbsence.setDateAbsence(rs.getTimestamp(MHRAbsence.COLUMNNAME_Date_Absence));
					beanAbsence.setIdCBpartner(rs.getInt(cBpartnerID));
					listeAbsences.add(beanAbsence);
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
		BeanAbsence[] absences = new BeanAbsence[listeAbsences.size()];
		for (int i = 0; i < absences.length; i++)
			absences[i] = ((BeanAbsence)listeAbsences.get(i));
		return absences;
	}
	
}
