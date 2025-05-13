package org.sitracel.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanAbsence;
import org.sitracel.bean.BeanConge;
import org.sitracel.bean.BeanIdentifiant;
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
import org.sitracel.model.MCBPartner;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.model.MHRJob;
import org.sitracel.model.MHROrganigramme;
import org.sitracel.model.MHRParametreNumerique;
import org.sitracel.paie.model.MHRCalculConge;
import org.sitracel.paie.model.MHRCalculIndemniteConge;
import org.sitracel.paie.model.MHRCalculPaie;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRGestionPaieEmploye;
import org.sitracel.paie.model.MHRHistoriquePaie;
import org.sitracel.paie.model.MHRPeriodeSalariale;
import org.sitracel.paie.model.MHRRetenueSalariale;

public class GeneralSqlController {
	private static CLogger	log = CLogger.getCLogger (PO.class);
	public static BeanIdentifiant getBeanIdentifiant (Integer idADUser, String trxName)
	{
		BeanIdentifiant bi =BeanFactory.getBeanIdentifiant();
		if(idADUser!=null) {
			StringBuilder sql = new StringBuilder("SELECT job."+MHRJob.COLUMNNAME_Name+" AS Poste"
					+", job."+MHRJob.COLUMNNAME_HR_Job_ID+" AS NumPoste"
					+", cb."+MCBPartner.COLUMNNAME_Name+" AS Nom"
					+", cb."+MCBPartner.COLUMNNAME_C_BPartner_ID+" AS NumEmploye"
					+", cb."+MCBPartner.COLUMNNAME_Value+" AS Matricule"
					+" FROM "+MCBPartner.Table_Name+" cb"
					+" LEFT JOIN AD_User ad"
						+" ON ad.C_BPartner_ID=cb."+MCBPartner.COLUMNNAME_C_BPartner_ID
					+" LEFT JOIN "+MHREmployeeJob.Table_Name+" hjob"
						+" ON hjob."+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+"=cb."+MCBPartner.COLUMNNAME_C_BPartner_ID
						+" AND hjob."+MHREmployeeJob.COLUMNNAME_DateFrom+"="
							+"(SELECT MAX("+MHREmployeeJob.COLUMNNAME_DateFrom+")"
							+" FROM "+MHREmployeeJob.Table_Name
							+" WHERE "+MHREmployeeJob.Table_Name+"."+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+"=cb."+MCBPartner.COLUMNNAME_C_BPartner_ID+")"
					+" LEFT JOIN "+MHRJob.Table_Name+" job"
						+" ON job."+MHRJob.COLUMNNAME_HR_Job_ID+"=hjob."+MHREmployeeJob.COLUMNNAME_HR_Job_ID
					+" WHERE ad.AD_User_ID=?");
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idADUser);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					bi.setNomEmploye(rs.getString("Nom"));
					bi.setMatriculeEmploye(rs.getString("Matricule"));
					bi.setNomPoste(rs.getString("Poste"));
					bi.setNumeroPoste(rs.getInt("NumPoste"));
					bi.setNumEmploye(rs.getInt("NumEmploye"));
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
		return bi;
	}
	
	public static boolean isJourFerie (Timestamp date, String trxName)
	{
		boolean resultat = false;
		if(date!=null) {
			StringBuilder sql = new StringBuilder("SELECT 1"
					+" FROM "+MHRPublicHoliday.Table_Name
					+" WHERE "+MHRPublicHoliday.COLUMNNAME_Date_Jour_Ferie+" = ? "
					+ " AND EXTRACT(DOW FROM " + MHRPublicHoliday.COLUMNNAME_Date_Jour_Ferie + ") <> ?" 
					+ " ORDER BY " + MHRPublicHoliday.COLUMNNAME_Date_Jour_Ferie);
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, date);
				pstmt.setInt(2, 0);
				rs = pstmt.executeQuery();
				if (rs.next()){
					resultat = true;
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
		//	Convert to array
		return resultat;
	}
	

	public static boolean isJourAbsence (Integer bpartnerID, Timestamp date, String trxName)
	{
		boolean resultat = false;
		if(bpartnerID!=null && date!=null) {
			StringBuilder sql = new StringBuilder("SELECT 1"
					+" FROM "+MHRAbsence.Table_Name
					+" WHERE "+MHRAbsence.COLUMNNAME_Date_Absence+"=? "
					+" AND "+MHRAbsence.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND EXTRACT(DOW FROM "+MHRAbsence.COLUMNNAME_Date_Absence+") !=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, date);
				pstmt.setInt(2, bpartnerID);
				pstmt.setInt(3, 0);
				rs = pstmt.executeQuery();
				if (rs.next()){
					resultat = true;
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
		//	Convert to array
		return resultat;
	}

	public static boolean isPeriodeAbsence (Integer bpartnerID, Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		boolean resultat = false;
		if(bpartnerID!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT 1"
					+ " FROM " + MHRAbsence.Table_Name + " abs"
					+ " WHERE abs." + MHRAbsence.COLUMNNAME_Date_Absence + " BETWEEN ? AND ?"
					+ " AND abs." + MHRAbsence.COLUMNNAME_C_BPartner_ID + " = ?"
					+ " AND EXTRACT(DOW FROM abs." + MHRAbsence.COLUMNNAME_Date_Absence + ") <> ?"
					+ " ORDER BY abs." + MHRAbsence.COLUMNNAME_Date_Absence);
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, dateDebut);
				pstmt.setTimestamp(2, dateFin);
				pstmt.setInt(3, bpartnerID);
				pstmt.setInt(4, 0);
				rs = pstmt.executeQuery();
				if (rs.next()){
					resultat = true;
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
		//	Convert to array
		return resultat;
	}
	
	public static boolean isJourAnyCongeNonRejete (Integer bpartnerID, Timestamp date, String trxName)
	{
		boolean resultat = false;
		if(bpartnerID!=null && date!=null) {
			StringBuilder sql = new StringBuilder("SELECT 1"
					+" FROM "+MHRHoliday.Table_Name
					+" WHERE "+MHRHoliday.COLUMNNAME_Date_Debut_Effective+"<=?"
					+" AND "+MHRHoliday.COLUMNNAME_Date_Fin_Effective+">=?"
					+" AND "+MHRHoliday.COLUMNNAME_IsRejetee+"!=?"
					+" AND "+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, date);
				pstmt.setTimestamp(2, date);
				pstmt.setString(3, "Y");
				pstmt.setInt(4, bpartnerID);
				rs = pstmt.executeQuery();
				if (rs.next()){
					resultat = true;
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
		//	Convert to array
		return resultat;
	}

	public static boolean isJourAnyCongeValide (Integer bpartnerID, Timestamp date, String trxName)
	{
		boolean resultat = false;
		if(bpartnerID!=null && date!=null) {
			StringBuilder sql = new StringBuilder("SELECT 1"
					+" FROM "+MHRHoliday.Table_Name
					+" WHERE "+MHRHoliday.COLUMNNAME_Date_Debut_Effective+"<=?"
					+" AND "+MHRHoliday.COLUMNNAME_Date_Fin_Effective+">=?"
					+" AND "+MHRHoliday.COLUMNNAME_IsValidee+"=?"
					+" AND "+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, date);
				pstmt.setTimestamp(2, date);
				pstmt.setString(3, "Y");
				pstmt.setInt(4, bpartnerID);
				rs = pstmt.executeQuery();
				if (rs.next()){
					resultat = true;
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
		//	Convert to array
		return resultat;
	}	

	public static boolean isJourCongesValidebyNameConge (Integer idCBPartner, String nomConge,Timestamp date, String trxName)
	{
		boolean resultat = false;
		if(idCBPartner!=null && nomConge!=null && date!=null) {
			StringBuilder sql= new StringBuilder("SELECT 1"
					+" FROM "+MHRHoliday.Table_Name+" hol"
					+" INNER JOIN "+MHRAutorisationConge.Table_Name+" droitconge"
						+" ON droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+MHRHoliday.COLUMNNAME_Emission_Conge_ID
					+" INNER JOIN "+MHRTypeConge.Table_Name+" typeconge"
						+" ON typeconge."+MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_IsValidee+"=?"
					+" AND typeconge."+MHRTypeConge.COLUMNNAME_Nom_Conge+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+"<=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+">= ?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setString(3, nomConge);
				pstmt.setTimestamp(4, date);
				pstmt.setTimestamp(5, date);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = true;
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

	public static boolean isJourCongesNonRejetebyNameConge (Integer idCBPartner, String nomConge,Timestamp date, String trxName)
	{
		boolean resultat = false;
		if(idCBPartner!=null && nomConge!=null && date!=null) {
			StringBuilder sql= new StringBuilder("SELECT 1"
					+" FROM "+MHRHoliday.Table_Name+" hol"
					+" INNER JOIN "+MHRAutorisationConge.Table_Name+" droitconge"
						+" ON droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+MHRHoliday.COLUMNNAME_Emission_Conge_ID
					+" INNER JOIN "+MHRTypeConge.Table_Name+" typeconge"
						+" ON typeconge."+MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_IsRejetee+"!=?"
					+" AND typeconge."+MHRTypeConge.COLUMNNAME_Nom_Conge+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+"<=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+">= ?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setString(3, nomConge);
				pstmt.setTimestamp(4, date);
				pstmt.setTimestamp(5, date);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = true;
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

	
	public static boolean chevaucheCongesValidebyNameConge (Integer idCBPartner, String nomConge,Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		boolean resultat = false;
		if(idCBPartner!=null && nomConge!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql= new StringBuilder("SELECT 1"
					+" FROM "+MHRHoliday.Table_Name+" hol"
					+" LEFT JOIN "+MHRAutorisationConge.Table_Name+" droitconge"
						+" ON droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+MHRHoliday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+MHRTypeConge.Table_Name+" typeconge"
						+" ON typeconge."+MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_IsValidee+"=?"
					+" AND typeconge."+MHRTypeConge.COLUMNNAME_Nom_Conge+"=?"
					+" AND (hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+", hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+")"
					+" OVERLAPS (?, ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setString(3, nomConge);
				pstmt.setTimestamp(4, dateDebut);
				pstmt.setTimestamp(5, dateFin);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = true;
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

	public static boolean chevaucheCongesNonRejetebyNameConge (Integer idCBPartner, String nomConge,Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		boolean resultat = false;
		if(idCBPartner!=null && nomConge!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql= new StringBuilder("SELECT 1"
					+" FROM "+MHRHoliday.Table_Name+" hol"
					+" LEFT JOIN "+MHRAutorisationConge.Table_Name+" droitconge"
						+" ON droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+MHRHoliday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+MHRTypeConge.Table_Name+" typeconge"
						+" ON typeconge."+MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_IsRejetee+"!=?"
					+" AND typeconge."+MHRTypeConge.COLUMNNAME_Nom_Conge+"=?"
					+" AND (hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+", hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+")"
					+" OVERLAPS (?, ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setString(3, nomConge);
				pstmt.setTimestamp(4, dateDebut);
				pstmt.setTimestamp(5, dateFin);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = true;
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
	
	public static boolean chevaucheAnyCongeNonRejete (Integer bpartnerID, Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		boolean resultat = false;
		if(bpartnerID!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT 1"
					+ " FROM " + MHRHoliday.Table_Name + " hol"
					+ " WHERE hol." + MHRHoliday.COLUMNNAME_IsRejetee + " <> ?"
					+ " AND hol." + MHRHoliday.COLUMNNAME_C_BPartner_ID + " = ?"
					+ " AND ((hol." + MHRHoliday.COLUMNNAME_Date_Debut_Effective + ", hol." + MHRHoliday.COLUMNNAME_Date_Fin_Effective + ")"
					+ " OVERLAPS (?, ?))"
					+ " ORDER BY hol." + MHRHoliday.COLUMNNAME_Date_Debut_Effective);
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setString(1, "Y");
				pstmt.setInt(2, bpartnerID);
				pstmt.setTimestamp(3, dateDebut);
				pstmt.setTimestamp(4, dateFin);
				rs = pstmt.executeQuery();
				if (rs.next()){
					resultat = true;
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
		//	Convert to array
		return resultat;
	}

	public static boolean isJourSuspensionValide (Integer bpartnerID, Timestamp date, String trxName)
	{
		boolean resultat = false;
		if(bpartnerID!=null && date!=null) {
			StringBuilder sql = new StringBuilder("SELECT 1"
					+" FROM "+MHRPunishment.Table_Name
					+" WHERE "+MHRPunishment.COLUMNNAME_Date_Debut_Application+"<=?"
					+" AND "+MHRPunishment.COLUMNNAME_Date_Fin_Application+">=?"
					+" AND "+MHRPunishment.COLUMNNAME_IsValidee+"=?"
					+" AND "+MHRPunishment.COLUMNNAME_C_BPartner_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, date);
				pstmt.setTimestamp(2, date);
				pstmt.setString(3, "Y");
				pstmt.setInt(4, bpartnerID);
				rs = pstmt.executeQuery();
				if (rs.next()){
					resultat = true;
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
		//	Convert to array
		return resultat;
	}

	public static boolean isJourSuspensionNonRejete (Integer bpartnerID, Timestamp date, String trxName)
	{
		boolean resultat = false;
		if(bpartnerID!=null && date!=null) {
			StringBuilder sql = new StringBuilder("SELECT 1"
					+" FROM "+MHRPunishment.Table_Name
					+" WHERE "+MHRPunishment.COLUMNNAME_Date_Debut_Application+"<=?"
					+" AND "+MHRPunishment.COLUMNNAME_Date_Fin_Application+">=?"
					+" AND "+MHRPunishment.COLUMNNAME_IsRejetee+"!=?"
					+" AND "+MHRPunishment.COLUMNNAME_C_BPartner_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, date);
				pstmt.setTimestamp(2, date);
				pstmt.setString(3, "Y");
				pstmt.setInt(4, bpartnerID);
				rs = pstmt.executeQuery();
				if (rs.next()){
					resultat = true;
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
		//	Convert to array
		return resultat;
	}
	
	public static boolean chevaucheSuspensionNonRejete (Integer bpartnerID, Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		boolean resultat = false;
		if(bpartnerID!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT 1"
					+" FROM "+MHRPunishment.Table_Name
					+ " WHERE " + MHRPunishment.COLUMNNAME_IsRejetee + " <> ?"
					+ " AND " + MHRPunishment.COLUMNNAME_C_BPartner_ID + " = ?"
					+ " AND ((" + MHRPunishment.COLUMNNAME_Date_Debut_Application + ", " + MHRPunishment.COLUMNNAME_Date_Fin_Application + ")"
					+ " OVERLAPS (?, ?))"
					+ " ORDER BY " + MHRPunishment.COLUMNNAME_Date_Debut_Application);
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setString(1, "Y");
				pstmt.setInt(2, bpartnerID);
				pstmt.setTimestamp(3, dateDebut);
				pstmt.setTimestamp(4, dateFin);
				rs = pstmt.executeQuery();
				if (rs.next()){
					resultat = true;
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
		//	Convert to array
		return resultat;
	}

	public static Set<LocalDate> getAllJoursFeries (Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		Set<LocalDate> feries = new HashSet<>();
		if(dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT "+MHRPublicHoliday.COLUMNNAME_Date_Jour_Ferie
					+" FROM "+MHRPublicHoliday.Table_Name
					+" WHERE "+MHRPublicHoliday.COLUMNNAME_Date_Jour_Ferie+" BETWEEN ? AND ?"
					+" AND EXTRACT(DOW FROM "+MHRPublicHoliday.COLUMNNAME_Date_Jour_Ferie+") !=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, dateDebut);
				pstmt.setTimestamp(2, dateFin);
				pstmt.setInt(3, 0);
				rs = pstmt.executeQuery();
				while (rs.next())
					feries.add(rs.getTimestamp(MHRPublicHoliday.COLUMNNAME_Date_Jour_Ferie).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
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
		}
		return feries;
	}		

	public static BeanPeriode[] getCongesValidebyNameConge (Integer idCBPartner, String nomConge,Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> list = new ArrayList<BeanPeriode>();
		if(idCBPartner!=null && nomConge!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql= new StringBuilder("SELECT hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective
					+", hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective
					+" FROM "+MHRHoliday.Table_Name+" hol"
					+" LEFT JOIN "+MHRAutorisationConge.Table_Name+" droitconge"
						+" ON droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+MHRHoliday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+MHRTypeConge.Table_Name+" typeconge"
						+" ON typeconge."+MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_IsValidee+"=?"
					+" AND typeconge."+MHRTypeConge.COLUMNNAME_Nom_Conge+"=?"
					+" AND (CAST (hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+" AS timestamp), "
					+" CAST (hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" AS timestamp))"
					+" OVERLAPS (?, ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setString(3, nomConge);
				pstmt.setTimestamp(4, dateDebut);
				pstmt.setTimestamp(5, dateFin);
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
	
	public static BeanPeriode[] getCongesNonRejetebyNameConge (Integer idCBPartner, String nomConge,Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> list = new ArrayList<BeanPeriode>();
		if(idCBPartner!=null && nomConge!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql= new StringBuilder("SELECT hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective
					+", hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective
					+" FROM "+MHRHoliday.Table_Name+" hol"
					+" LEFT JOIN "+MHRAutorisationConge.Table_Name+" droitconge"
						+" ON droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+MHRHoliday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+MHRTypeConge.Table_Name+" typeconge"
						+" ON typeconge."+MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_IsRejetee+"!=?"
					+" AND typeconge."+MHRTypeConge.COLUMNNAME_Nom_Conge+"=?"
					+" AND (CAST (hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+" AS timestamp), "
					+" CAST (hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" AS timestamp))"
					+" OVERLAPS (?, ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setString(3, nomConge);
				pstmt.setTimestamp(4, dateDebut);
				pstmt.setTimestamp(5, dateFin);
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
	
	public static BeanPeriode[] getAllCongesValide (Integer idCBPartner, Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> list = new ArrayList<BeanPeriode>();
		if(idCBPartner!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql= new StringBuilder("SELECT hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective
					+", hol. "+MHRHoliday.COLUMNNAME_Date_Fin_Effective
					+" FROM "+MHRHoliday.Table_Name+" hol"
					+" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_IsValidee+"=?"
					+" AND (CAST (hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+" AS timestamp), "
					+" CAST (hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" AS timestamp))"
					+" OVERLAPS (?, ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setTimestamp(3, dateDebut);
				pstmt.setTimestamp(4, dateFin);
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
	
	public static BeanPeriode[] getAllCongesNonRejete (Integer idCBPartner, Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> list = new ArrayList<BeanPeriode>();
		if(idCBPartner!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql= new StringBuilder("SELECT hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective
					+", hol. "+MHRHoliday.COLUMNNAME_Date_Fin_Effective
					+" FROM "+MHRHoliday.Table_Name+" hol"
					+" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_IsRejetee+"!=?"
					+" AND (CAST (hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+" AS timestamp), "
					+" CAST (hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" AS timestamp))"
					+" OVERLAPS (?, ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setTimestamp(3, dateDebut);
				pstmt.setTimestamp(4, dateFin);
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

	public static ArrayList<MHRElementBasePaieEmploye> getDatesDerniersContrats(Integer idCBPartner, Timestamp dateMax, String trxName) {
		ArrayList<MHRElementBasePaieEmploye> resultat = new ArrayList<MHRElementBasePaieEmploye>();
		if(idCBPartner!=null && dateMax!=null) {
			StringBuilder sql = new StringBuilder("SELECT *"
								+" FROM "+MHRElementBasePaieEmploye.Table_Name
								+" WHERE "+MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID+"=?"
								+" AND "+MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut+"<=?"
								+" ORDER BY "+MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut+" DESC");
			;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setTimestamp(2, dateMax);		
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat.add(new MHRElementBasePaieEmploye(Env.getCtx(), rs, trxName));
				}
			}
			catch (SQLException e)
			{
				log.warning(e.getMessage());
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
	
	public static BeanConge getDateDernierConge(Integer idCBPartner, Timestamp dateMax, Timestamp dateMin, String nomConge, BeanConge beanInfoConge, String trxName) {
		if(idCBPartner!=null && dateMax!=null && dateMin!=null && nomConge!=null && beanInfoConge!=null) {
			StringBuilder sql = new StringBuilder("SELECT hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective
					+", hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective
					+" FROM "+MHRHoliday.Table_Name+" hol"
					+" LEFT JOIN "+MHRAutorisationConge.Table_Name+" droitconge"
						+" ON droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+MHRHoliday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+MHRTypeConge.Table_Name+" typeconge"
						+" ON typeconge."+MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Type_Conge_ID					
					+" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+"<?"
					+" AND hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+">?"
			        +" AND hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+" < hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective
					+" AND typeconge."+MHRTypeConge.COLUMNNAME_Nom_Conge+"=?"
					+" ORDER BY hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" DESC");
			;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setTimestamp(2, dateMax);
				pstmt.setTimestamp(3, dateMin);
				pstmt.setString(4, nomConge);				
				rs = pstmt.executeQuery();
				if (rs.next()) {
					beanInfoConge.setDateDebutDernierConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Debut_Effective));
					beanInfoConge.setDateFinDernierConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Fin_Effective));
				}
				beanInfoConge.setDateEmbauche(dateMin);
			}
			catch (SQLException e)
			{
				log.warning(e.getMessage());
				e.printStackTrace();
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return beanInfoConge;
	}
	
	public static BeanPeriode[] getAllPeriodeSuspensionValide (Integer idCBPartner, Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> listePeriodesSuspensions = new ArrayList<BeanPeriode>();
		if(idCBPartner!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT pun."
					+MHRPunishment.COLUMNNAME_Date_Debut_Application
					+", pun."+MHRPunishment.COLUMNNAME_Date_Fin_Application
					+" FROM "+MHRPunishment.Table_Name+" pun"
					+" LEFT JOIN "+MHRSanctionAutorisation.Table_Name+" droitsanction"
						+" ON droitsanction."+MHRSanctionAutorisation.COLUMNNAME_HR_Sanction_Autorisation_ID+"=pun."+MHRPunishment.COLUMNNAME_Emission_Sanction_ID
					+" LEFT JOIN "+MHRTypeSanction.Table_Name+" typesanction"
						+" ON typesanction."+MHRTypeSanction.COLUMNNAME_HR_TypeSanction_ID+"=droitsanction."+MHRSanctionAutorisation.COLUMNNAME_HR_TypeSanction_ID
					+" WHERE pun."+MHRPunishment.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND pun."+MHRPunishment.COLUMNNAME_IsValidee+"=?"
					+" AND typesanction."+MHRTypeSanction.COLUMNNAME_Incidence_Sanction_ID+"=?"
					+" AND (CAST (pun."+MHRPunishment.COLUMNNAME_Date_Debut_Application+" AS timestamp), "
					+" CAST(pun."+MHRPunishment.COLUMNNAME_Date_Fin_Application+" AS timestamp))"
					+" OVERLAPS (?, ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setString(3, MHRTypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension);
				pstmt.setTimestamp(4, dateDebut);
				pstmt.setTimestamp(5, dateFin);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanPeriode beanPeriode =BeanFactory.getBeanPeriode();
					beanPeriode.setDateDebutConge(rs.getTimestamp(MHRPunishment.COLUMNNAME_Date_Debut_Application));
					beanPeriode.setDateFinConge(rs.getTimestamp(MHRPunishment.COLUMNNAME_Date_Fin_Application));
					listePeriodesSuspensions.add(beanPeriode);
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
		BeanPeriode[] periodesSuspensions = new BeanPeriode[listePeriodesSuspensions.size()];
		for (int i = 0; i < periodesSuspensions.length; i++)
			periodesSuspensions[i] = ((BeanPeriode)listePeriodesSuspensions.get(i));
		return periodesSuspensions;
	}
	

	public static BeanPeriode[] getAllPeriodeSuspensionNonRejete (Integer idCBPartner, Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> listePeriodesSusoensions = new ArrayList<BeanPeriode>();
		if(idCBPartner!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT pun."
					+MHRPunishment.COLUMNNAME_Date_Debut_Application
					+", pun."+MHRPunishment.COLUMNNAME_Date_Fin_Application
					+" FROM "+MHRPunishment.Table_Name+" pun"
					+" LEFT JOIN "+MHRSanctionAutorisation.Table_Name+" droitsanction"
						+" ON droitsanction."+MHRSanctionAutorisation.COLUMNNAME_HR_Sanction_Autorisation_ID+"=pun."+MHRPunishment.COLUMNNAME_Emission_Sanction_ID
					+" LEFT JOIN "+MHRTypeSanction.Table_Name+" typesanction"
						+" ON typesanction."+MHRTypeSanction.COLUMNNAME_HR_TypeSanction_ID+"=droitsanction."+MHRSanctionAutorisation.COLUMNNAME_HR_TypeSanction_ID
					+" WHERE pun."+MHRPunishment.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND pun."+MHRPunishment.COLUMNNAME_IsRejetee+"!=?"
					+" AND typesanction."+MHRTypeSanction.COLUMNNAME_Incidence_Sanction_ID+"=?"
					+" AND (CAST (pun."+MHRPunishment.COLUMNNAME_Date_Debut_Application+" AS timestamp), "
					+" CAST(pun."+MHRPunishment.COLUMNNAME_Date_Fin_Application+" AS timestamp))"
					+" OVERLAPS (?, ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setString(3, MHRTypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension);
				pstmt.setTimestamp(4, dateDebut);
				pstmt.setTimestamp(5, dateFin);
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
			StringBuilder sql = new StringBuilder("SELECT "+MHRAbsence.COLUMNNAME_HR_Absence_ID
					+", "+MHRAbsence.COLUMNNAME_Date_Absence
					+" FROM "+MHRAbsence.Table_Name
					+" WHERE "+MHRAbsence.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+MHRAbsence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cBpartnerID);
				pstmt.setTimestamp(2, GeneralController.getFirstDayOfaYear(date));
				pstmt.setTimestamp(3, GeneralController.getLastDayOfaYear(date));
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
			StringBuilder sql = new StringBuilder("SELECT para."+MHRParametreNumerique.COLUMNNAME_Valeur_Parametre
					+" FROM "+MHRParametreNumerique.Table_Name+" para"
					+" WHERE para."+MHRParametreNumerique.COLUMNNAME_Name+"=?");
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
				StringBuilder sql = new StringBuilder("SELECT "
						+ "FROM HR_Organigramme org WHERE "
						+ "org.Poste_ID=? AND "
						+ "org.Poste_Responsable_ID=?");
				
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, poste_ID);
				pstmt.setInt(2, posteResponsable_ID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = new MHROrganigramme(Env.getCtx(), rs, trxName) ;
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
				StringBuilder sql = new StringBuilder("SELECT "+searchColumnName
						+ " FROM "+tableName
						+ " WHERE "+tableName+"."+columnName+"=?");
				
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
				StringBuilder sql = new StringBuilder("SELECT "+searchColumnName
						+ " FROM "+tableName
						+ " WHERE "+tableName+"."+searchColumnName+"=?");
				
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
	

	public static int getNombreJourAbsencesCongeNonTraite (Timestamp date, String trxName)
	{
		Timestamp firstDayOfYear = GeneralController.getFirstDayOfThisYear();
		Timestamp lastDayOfYear = GeneralController.getLastDayOfThisYear();
		int resultat = 0;
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS count"
				+" FROM "+MHRAbsence.Table_Name
				+" WHERE "+MHRAbsence.COLUMNNAME_IsConge+"=?"
				+" AND "+MHRAbsence.COLUMNNAME_IsCongeTraite+"=?"
				+" ANd "+MHRAbsence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, "Y");
			pstmt.setString(2, "N");
			pstmt.setTimestamp(3, firstDayOfYear);
			pstmt.setTimestamp(4, lastDayOfYear);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				resultat = rs.getInt("count");
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
		return resultat;
	}
	
	public static ArrayList<MHRElementBasePaieEmploye> getElementBasePaieEmploye(Integer bpartnerID, Timestamp dateDebut, Timestamp dateFin, String trxName){
		ArrayList<MHRElementBasePaieEmploye> resultat = new ArrayList<MHRElementBasePaieEmploye>();
		if(bpartnerID!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("WITH intervals AS ("
					+" SELECT "+MHRElementBasePaieEmploye.COLUMNNAME_HR_ElementBasePaieEmploye_ID
					+", "+MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut
					+", COALESCE("+MHRElementBasePaieEmploye.COLUMNNAME_Date_Fin
						+", LEAD("+MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut+") OVER (ORDER BY "+MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut+") - INTERVAL '1 day'"
						+", ?) AS "+MHRElementBasePaieEmploye.COLUMNNAME_Date_Fin+""
					+" FROM "+MHRElementBasePaieEmploye.Table_Name
					+" WHERE "+MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID+"=?)"
					+" SELECT "+MHRElementBasePaieEmploye.COLUMNNAME_HR_ElementBasePaieEmploye_ID
					+", "+MHRElementBasePaieEmploye.COLUMNNAME_Date_Fin
					+" FROM intervals"
					+" WHERE (CAST(" + MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut + " AS timestamp),"
					+" CAST(" + MHRElementBasePaieEmploye.COLUMNNAME_Date_Fin + " AS timestamp))"
					+" OVERLAPS (?, ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, dateFin);
				pstmt.setInt(2, bpartnerID);
				pstmt.setTimestamp(3, dateDebut);
				pstmt.setTimestamp(4, dateFin);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					MHRElementBasePaieEmploye elementBasePaieEmploye = new MHRElementBasePaieEmploye(Env.getCtx(), rs.getInt(MHRElementBasePaieEmploye.COLUMNNAME_HR_ElementBasePaieEmploye_ID), null);
					if(elementBasePaieEmploye!=null) {
						if(elementBasePaieEmploye.getDate_Debut()!=null && elementBasePaieEmploye.getDate_Debut().before(dateDebut)) {
							elementBasePaieEmploye.setDate_Debut(dateDebut);
						}
						if(elementBasePaieEmploye.getDate_Fin()==null) {
							if( dateFin.before(rs.getTimestamp(MHRElementBasePaieEmploye.COLUMNNAME_Date_Fin))) {
								elementBasePaieEmploye.setDate_Fin(dateFin);
							}
							else {
								elementBasePaieEmploye.setDate_Fin(rs.getTimestamp(MHRElementBasePaieEmploye.COLUMNNAME_Date_Fin));
							}
						}
						else if(dateFin.before(elementBasePaieEmploye.getDate_Fin())) {
							elementBasePaieEmploye.setDate_Fin(dateFin);
						}
						resultat.add(elementBasePaieEmploye);
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

	public static MHRHistoriquePaie getHistoriquePaie(Integer cbpartnerid, Integer hrElementBasePaieID, Integer periodeSalarialeID, String trxName) {
		MHRHistoriquePaie resultat = null;
		if(cbpartnerid!=null && hrElementBasePaieID!=null && periodeSalarialeID!=null) {
			StringBuilder sql = new StringBuilder("SELECT *"
					+" FROM "+MHRHistoriquePaie.Table_Name+" his"
					+" WHERE his."+MHRHistoriquePaie.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND his."+MHRHistoriquePaie.COLUMNNAME_HR_Element_Base_Paie_ID+"=?"
					+" AND his."+MHRHistoriquePaie.COLUMNNAME_HR_Periode_Salariale_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				pstmt.setInt(2, hrElementBasePaieID);
				pstmt.setInt(3, periodeSalarialeID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = new MHRHistoriquePaie(Env.getCtx(), rs, trxName);
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

	public static MHREmployeeJob getEmployeeJob(Integer cbpartnerid, Timestamp date, String trxName) {
		MHREmployeeJob resultat = null;
		if(cbpartnerid!=null && date!=null) {
			StringBuilder sql = new StringBuilder("SELECT *"
					+" FROM "+MHREmployeeJob.Table_Name+" empjob"
					+" WHERE empjob."+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND empjob."+MHREmployeeJob.COLUMNNAME_DateFrom+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				pstmt.setTimestamp(2, date);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = new MHREmployeeJob(Env.getCtx(), rs, trxName);
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
	
	public static ArrayList<MHRPeriodeSalariale> getAllPeriodeSalarialeFromPeriodeReference(Timestamp dateDebut, Timestamp dateFin, String trxName){
		ArrayList<MHRPeriodeSalariale> resultat = new ArrayList<MHRPeriodeSalariale>();
		if(dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT * FROM"
					+" "+MHRPeriodeSalariale.Table_Name
					+" WHERE (CAST("+MHRPeriodeSalariale.COLUMNNAME_Date_Debut_Defaut+"  AS timestamp),"
					+" CAST("+MHRPeriodeSalariale.COLUMNNAME_Date_Fin_Defaut+"  AS timestamp))"
					+" OVERLAPS (?, ?)"
					+" ORDER BY "+MHRPeriodeSalariale.COLUMNNAME_Date_Debut_Defaut+" ASC");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, dateDebut);
				pstmt.setTimestamp(2, dateFin);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat.add(new MHRPeriodeSalariale(Env.getCtx(), rs, trxName));
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

	public static void resetIndemniteConge(Integer cbpartnerid, String trxName) {
		MHRCalculIndemniteConge resultat = null;
		if(cbpartnerid!=null) {
			StringBuilder sql = new StringBuilder("SELECT *"
					+" FROM "+MHRCalculIndemniteConge.Table_Name+" ic"
					+" WHERE ic."+MHRCalculIndemniteConge.COLUMNNAME_C_BPartner_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = new MHRCalculIndemniteConge(Env.getCtx(), rs, trxName);
					resultat.delete(true);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
	}

	public static void resetCalculPaie(Integer cbpartnerid, String trxName) {
		MHRCalculPaie resultat = null;
		if(cbpartnerid!=null) {
			StringBuilder sql = new StringBuilder("SELECT *"
					+" FROM "+MHRCalculPaie.Table_Name+" cp"
					+" WHERE cp."+MHRCalculPaie.COLUMNNAME_C_BPartner_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = new MHRCalculPaie(Env.getCtx(), rs, trxName);
					resultat.delete(true);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
	}

	public static void resetCalculConge(Integer cbpartnerid, String trxName) {
		MHRCalculConge resultat = null;
		if(cbpartnerid!=null) {
			StringBuilder sql = new StringBuilder("SELECT *"
					+" FROM "+MHRCalculConge.Table_Name+" cc"
					+" WHERE cc."+MHRCalculConge.COLUMNNAME_C_BPartner_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cbpartnerid);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = new MHRCalculConge(Env.getCtx(), rs, trxName);
					resultat.delete(true);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
	}

	public static ArrayList<MHRGestionPaieEmploye> getAllGestionPaieEmploye(String trxName){
		ArrayList<MHRGestionPaieEmploye> resultat = new ArrayList<MHRGestionPaieEmploye>();
		StringBuilder sql = new StringBuilder(" SELECT gpe.*"
	            +" FROM "+MHRGestionPaieEmploye.Table_Name+" gpe"
	            +" LEFT JOIN HR_Rang_Calcul rc ON gpe.HR_Rang_Calcul_ID = rc.HR_Rang_Calcul_ID"
	            +" ORDER BY rc.Rang ASC");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MHRGestionPaieEmploye gestionPaieEmploye = new MHRGestionPaieEmploye(Env.getCtx(), rs, null);
				if(gestionPaieEmploye!=null) {
					resultat.add(gestionPaieEmploye);
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
		return resultat;
	}

	public static ArrayList<MHRGestionPaieEmploye> getAllGestionCongeEmploye(String trxName){
		ArrayList<MHRGestionPaieEmploye> resultat = new ArrayList<MHRGestionPaieEmploye>();
		StringBuilder sql = new StringBuilder(" SELECT gpe.*"
	            +" FROM "+MHRGestionPaieEmploye.Table_Name+" gpe"
	            +" LEFT JOIN HR_Rang_Calcul rc ON gpe.HR_Rang_Calcul_ID = rc.HR_Rang_Calcul_ID"
	            +" WHERE gpe."+MHRGestionPaieEmploye.COLUMNNAME_IsIndemniteConge+"=?"
	            +" ORDER BY rc.Rang ASC");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, "Y");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MHRGestionPaieEmploye gestionPaieEmploye = new MHRGestionPaieEmploye(Env.getCtx(), rs, null);
				if(gestionPaieEmploye!=null) {
					resultat.add(gestionPaieEmploye);
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
		return resultat;
	}

	public static MHRPeriodeSalariale getPeriodeSalarialeFinRetenue(Timestamp dateDebut, Integer nombreMensualite,String trxName) {
	    if(dateDebut!=null && nombreMensualite!=null && nombreMensualite>0) {
	    	StringBuilder sql = new StringBuilder("SELECT * FROM "+MHRPeriodeSalariale.Table_Name
		               +" WHERE "+MHRPeriodeSalariale.COLUMNNAME_Date_Debut_Defaut+" >= ? "
		               +"ORDER BY "+MHRPeriodeSalariale.COLUMNNAME_Date_Debut_Defaut+" ASC "); 
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    try {
		        pstmt = DB.prepareStatement(sql.toString(), trxName);
		        pstmt.setTimestamp(1, dateDebut);
		        rs = pstmt.executeQuery();
		        int i = 0;
		        while (rs.next()) {
		        	i++;
		        	if(i==nombreMensualite) {
			            return new MHRPeriodeSalariale(Env.getCtx(), rs, trxName);
		        	}
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        DB.close(rs, pstmt);
		    }
	    }
	    return null;
	}

	public static ArrayList<MHRRetenueSalariale> getAllRetenueEmploye(Integer bpartnerID, MHRPeriodeSalariale periodeSalariale, String trxName){
		ArrayList<MHRRetenueSalariale> resultat = new ArrayList<MHRRetenueSalariale>();
		if(bpartnerID!=null && periodeSalariale!=null) {
			StringBuilder sql = new StringBuilder(" SELECT rs.*"
		            +" FROM "+MHRRetenueSalariale.Table_Name+" rs"
		            +" LEFT JOIN "+MHRPeriodeSalariale.Table_Name+" ps1"
		            +" ON ps1."+MHRPeriodeSalariale.COLUMNNAME_HR_Periode_Salariale_ID+"=rs."+MHRRetenueSalariale.COLUMNNAME_Debut_Prelevement_ID
		            +" LEFT JOIN "+MHRPeriodeSalariale.Table_Name+" ps2"
		            +" ON ps2."+MHRPeriodeSalariale.COLUMNNAME_HR_Periode_Salariale_ID+"=rs."+MHRRetenueSalariale.COLUMNNAME_Fin_Prelevement_ID
		            +" WHERE "+MHRRetenueSalariale.COLUMNNAME_C_BPartner_ID+"=?"
		            +" AND ps1."+MHRPeriodeSalariale.COLUMNNAME_Date_Debut_Defaut+"<=?"
		            +" AND ps2."+MHRPeriodeSalariale.COLUMNNAME_Date_Fin_Defaut+">=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, bpartnerID);
		        pstmt.setTimestamp(2, periodeSalariale.getDate_Debut_Defaut());
		        pstmt.setTimestamp(3, periodeSalariale.getDate_Fin_Defaut());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					MHRRetenueSalariale retenueSalariale = new MHRRetenueSalariale(Env.getCtx(), rs, null);
					if(retenueSalariale!=null) {
						resultat.add(retenueSalariale);
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

}
