package org.sitracel.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanAbsence;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAutorisationConge;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;
import org.sitracel.model.MHROrganigramme;
import org.sitracel.model.MHRParametreNumerique;

public class GeneralSqlController {
	private static CLogger	log = CLogger.getCLogger (PO.class);
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
			log.warning(e.getMessage());
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

	public static BeanPeriode[] getAllCongesAnnuel (Integer idCBPartner, Timestamp anyDayOfYear, String trxName)
	{
		ArrayList<BeanPeriode> list = new ArrayList<BeanPeriode>();
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
			.append(MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID).append("=droitconge.").append(MHRAutorisationConge.COLUMNNAME_HR_Type_Conge_ID)
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
				pstmt.setTimestamp(4, firstDayOfYear);
				pstmt.setTimestamp(5, lastDayOfYear);
				pstmt.setString(6, "Y");
				pstmt.setString(7, "Y");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanPeriode bp =BeanFactory.getBeanPeriode();
					bp.setDateDebutConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Debut_Effective));
					bp.setDateFinConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Fin_Effective));
					list.add(bp);
				}
			}
			catch (SQLException e)
			{
				log.warning(e.getMessage());
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
					bp.setDateFinConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Fin_Effective));
					list.add(bp);
				}
			}
			catch (SQLException e)
			{
				log.warning(e.getMessage());
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
	
	public static BeanPeriode[] getAllPeriodeSuspension (Integer idCBPartner, Timestamp anyDayOfYear, String trxName)
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
			.append(MHRTypeSanction.COLUMNNAME_HR_TypeSanction_ID).append("=droitSanction.").append(MHRSanctionAutorisation.COLUMNNAME_HR_TypeSanction_ID)
			.append(" WHERE pun."+MHRPunishment.COLUMNNAME_C_BPartner_ID).append("=?")
			.append(" AND pun.").append(MHRPunishment.COLUMNNAME_Date_Debut_Application).append(" BETWEEN ? AND ?")
			.append(" OR pun.").append(MHRPunishment.COLUMNNAME_Date_Fin_Application).append(" BETWEEN ? AND ?")
			.append(" AND pun.").append(MHRPunishment.COLUMNNAME_IsRejetee).append("!=?")
			.append(" AND typeSanction.").append(MHRTypeSanction.COLUMNNAME_Incidence_Sanction_ID).append("=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			log.warning("\n1REQUETE SQL : "+sql);
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setTimestamp(2, firstDayOfYear);
				pstmt.setTimestamp(3, lastDayOfYear);
				pstmt.setTimestamp(4, firstDayOfYear);
				pstmt.setTimestamp(5, lastDayOfYear);
				pstmt.setString(6, "N");
				pstmt.setString(7, MHRTypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanPeriode beanPeriode =BeanFactory.getBeanPeriode();
					beanPeriode.setDateDebutConge(rs.getTimestamp(MHRPunishment.COLUMNNAME_Date_Debut_Application));
					beanPeriode.setDateFinConge(rs.getTimestamp(MHRPunishment.COLUMNNAME_Date_Fin_Application));
					listePeriodesSusoensions.add(beanPeriode);
				}
			}
			catch (SQLException e)
			{
				log.warning(e.getMessage());
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
				log.warning(e.getMessage());
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
	
	public static int getParametreFromParametreNumerique(String nomParametre) {
		int resultat = 0;
		if(nomParametre!=null) {
			StringBuilder sql = new StringBuilder("SELECT para."+MHRParametreNumerique.COLUMNNAME_Valeur_Parametre);
			sql.append(" FROM ")
			.append(MHRParametreNumerique.Table_Name+" para")			
			.append(" WHERE para."+MHRParametreNumerique.COLUMNNAME_Name+"=? ");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setString(1, nomParametre);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getInt(MHRParametreNumerique.COLUMNNAME_Valeur_Parametre);
				}
					
			}
			catch (SQLException e)
			{
				log.warning(e.getMessage());
				return resultat;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
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
			log.warning(e.getMessage());
			e.printStackTrace();				
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return resultat;
	}	
	
	public static Integer getIDFromTableNameAndName(String searchColumnName, String tableName, String columnName, String value, String trxName) {
		Integer resultat = null;
		if(searchColumnName!=null && tableName!=null && columnName!=null && value!=null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "SELECT "+searchColumnName
						+ " FROM "+tableName
						+ " WHERE "+tableName+"."+columnName+"=?";
				
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setString(1, value);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = rs.getInt(searchColumnName);
				}
				
			}
			catch (Exception e)
			{
				log.warning(e.getMessage());
				e.printStackTrace();				
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static boolean idExists(String searchColumnName, String tableName, int searchValue, String trxName) {
		boolean resultat = false;
		if(searchColumnName!=null && tableName!=null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "SELECT "+searchColumnName
						+ " FROM "+tableName
						+ " WHERE "+tableName+"."+searchColumnName+"=?";
				
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, searchValue);
				rs = pstmt.executeQuery();
				while (rs.next() && !resultat) {
					resultat = true;
				}
				
			}
			catch (Exception e)
			{
				log.warning(e.getMessage());
				e.printStackTrace();				
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}
}
