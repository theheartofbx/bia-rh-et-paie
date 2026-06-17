package org.sitracel.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.compiere.model.MBPartner;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanAbsence;
import org.sitracel.bean.BeanConge;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.I_HR_Absence;
import org.sitracel.conge.model.I_HR_Autorisation_Conge;
import org.sitracel.conge.model.I_HR_Holiday;
import org.sitracel.conge.model.I_HR_Public_Holiday;
import org.sitracel.conge.model.I_HR_Type_Conge;
import org.sitracel.discipline.model.I_HR_Punishment;
import org.sitracel.discipline.model.I_HR_Sanction_Autorisation;
import org.sitracel.discipline.model.I_HR_TypeSanction;
import org.sitracel.discipline.model.X_HR_TypeSanction;
import org.sitracel.model.I_HR_EmployeeJob;
import org.sitracel.model.I_HR_Parametre_Numerique;
import org.sitracel.model.MADUser;
import org.sitracel.model.MADUserRoles;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.model.MHROrganigramme;
import org.sitracel.paie.model.I_HR_Calcul_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Indemnite_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Paie;
import org.sitracel.paie.model.I_HR_ElementBasePaieEmploye;
import org.sitracel.paie.model.I_HR_GestionPaieEmploye;
import org.sitracel.paie.model.I_HR_Historique_Paie;
import org.sitracel.paie.model.I_HR_Periode_Salariale;
import org.sitracel.paie.model.I_HR_Retenue_Salariale;
import org.sitracel.paie.model.MHRCalculConge;
import org.sitracel.paie.model.MHRCalculIndemniteConge;
import org.sitracel.paie.model.MHRCalculPaie;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRGestionPaieEmploye;
import org.sitracel.paie.model.MHRHistoriquePaie;
import org.sitracel.paie.model.MHRPeriodeSalariale;
import org.sitracel.paie.model.MHRRetenueSalariale;

public class GeneralSqlController {
	public static CLogger	log = CLogger.getCLogger (PO.class);

	public static boolean isJourAbsence (Integer bpartnerID, Timestamp date, String trxName)
	{
		boolean resultat = false;
		if(bpartnerID!=null && date!=null) {
			StringBuilder sql = new StringBuilder("SELECT 1"
					+" FROM "+I_HR_Absence.Table_Name
					+" WHERE "+I_HR_Absence.COLUMNNAME_Date_Absence+"=? "
					+" AND "+I_HR_Absence.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND EXTRACT(DOW FROM "+I_HR_Absence.COLUMNNAME_Date_Absence+") !=?");
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
					+ " FROM " + I_HR_Absence.Table_Name + " abs"
					+ " WHERE abs." + I_HR_Absence.COLUMNNAME_Date_Absence + " BETWEEN ? AND ?"
					+ " AND abs." + I_HR_Absence.COLUMNNAME_C_BPartner_ID + " = ?"
					+ " AND EXTRACT(DOW FROM abs." + I_HR_Absence.COLUMNNAME_Date_Absence + ") <> ?"
					+ " ORDER BY abs." + I_HR_Absence.COLUMNNAME_Date_Absence);
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
					+" FROM "+I_HR_Holiday.Table_Name
					+" WHERE "+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective+"<=?"
					+" AND "+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective+">=?"
					+" AND "+I_HR_Holiday.COLUMNNAME_IsRejetee+"!=?"
					+" AND "+I_HR_Holiday.COLUMNNAME_C_BPartner_ID+"=?");
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
					+" FROM "+I_HR_Holiday.Table_Name
					+" WHERE "+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective+"<=?"
					+" AND "+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective+">=?"
					+" AND "+I_HR_Holiday.COLUMNNAME_IsValidee+"=?"
					+" AND "+I_HR_Holiday.COLUMNNAME_C_BPartner_ID+"=?");
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
					+" FROM "+I_HR_Holiday.Table_Name+" hol"
					+" INNER JOIN "+I_HR_Autorisation_Conge.Table_Name+" droitconge"
						+" ON droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
					+" INNER JOIN "+I_HR_Type_Conge.Table_Name+" typeconge"
						+" ON typeconge."+I_HR_Type_Conge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+I_HR_Holiday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_IsValidee+"=?"
					+" AND typeconge."+I_HR_Type_Conge.COLUMNNAME_Nom_Conge+"=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective+"<=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective+">= ?");
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
					+" FROM "+I_HR_Holiday.Table_Name+" hol"
					+" INNER JOIN "+I_HR_Autorisation_Conge.Table_Name+" droitconge"
						+" ON droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
					+" INNER JOIN "+I_HR_Type_Conge.Table_Name+" typeconge"
						+" ON typeconge."+I_HR_Type_Conge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+I_HR_Holiday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_IsRejetee+"!=?"
					+" AND typeconge."+I_HR_Type_Conge.COLUMNNAME_Nom_Conge+"=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective+"<=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective+">= ?");
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
					+" FROM "+I_HR_Holiday.Table_Name+" hol"
					+" LEFT JOIN "+I_HR_Autorisation_Conge.Table_Name+" droitconge"
						+" ON droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+I_HR_Type_Conge.Table_Name+" typeconge"
						+" ON typeconge."+I_HR_Type_Conge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+I_HR_Holiday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_IsValidee+"=?"
					+" AND typeconge."+I_HR_Type_Conge.COLUMNNAME_Nom_Conge+"=?"
					+" AND (hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective+", hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective+")"
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
					+" FROM "+I_HR_Holiday.Table_Name+" hol"
					+" LEFT JOIN "+I_HR_Autorisation_Conge.Table_Name+" droitconge"
						+" ON droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+I_HR_Type_Conge.Table_Name+" typeconge"
						+" ON typeconge."+I_HR_Type_Conge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+I_HR_Holiday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_IsRejetee+"!=?"
					+" AND typeconge."+I_HR_Type_Conge.COLUMNNAME_Nom_Conge+"=?"
					+" AND (hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective+", hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective+")"
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
					+ " FROM " + I_HR_Holiday.Table_Name + " hol"
					+ " WHERE hol." + I_HR_Holiday.COLUMNNAME_IsRejetee + " <> ?"
					+ " AND hol." + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + " = ?"
					+ " AND ((hol." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective + ", hol." + I_HR_Holiday.COLUMNNAME_Date_Fin_Effective + ")"
					+ " OVERLAPS (?, ?))"
					+ " ORDER BY hol." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective);
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
					+" FROM "+I_HR_Punishment.Table_Name
					+" WHERE "+I_HR_Punishment.COLUMNNAME_Date_Debut_Application+"<=?"
					+" AND "+I_HR_Punishment.COLUMNNAME_Date_Fin_Application+">=?"
					+" AND "+I_HR_Punishment.COLUMNNAME_IsValidee+"=?"
					+" AND "+I_HR_Punishment.COLUMNNAME_C_BPartner_ID+"=?");
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
					+" FROM "+I_HR_Punishment.Table_Name
					+" WHERE "+I_HR_Punishment.COLUMNNAME_Date_Debut_Application+"<=?"
					+" AND "+I_HR_Punishment.COLUMNNAME_Date_Fin_Application+">=?"
					+" AND "+I_HR_Punishment.COLUMNNAME_IsRejetee+"!=?"
					+" AND "+I_HR_Punishment.COLUMNNAME_C_BPartner_ID+"=?");
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
					+" FROM "+I_HR_Punishment.Table_Name
					+ " WHERE " + I_HR_Punishment.COLUMNNAME_IsRejetee + " <> ?"
					+ " AND " + I_HR_Punishment.COLUMNNAME_C_BPartner_ID + " = ?"
					+ " AND ((" + I_HR_Punishment.COLUMNNAME_Date_Debut_Application + ", " + I_HR_Punishment.COLUMNNAME_Date_Fin_Application + ")"
					+ " OVERLAPS (?, ?))"
					+ " ORDER BY " + I_HR_Punishment.COLUMNNAME_Date_Debut_Application);
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
			StringBuilder sql = new StringBuilder("SELECT "+I_HR_Public_Holiday.COLUMNNAME_Date_Jour_Ferie
					+" FROM "+I_HR_Public_Holiday.Table_Name
					+" WHERE "+I_HR_Public_Holiday.COLUMNNAME_Date_Jour_Ferie+" BETWEEN ? AND ?"
					+" AND EXTRACT(DOW FROM "+I_HR_Public_Holiday.COLUMNNAME_Date_Jour_Ferie+") !=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, dateDebut);
				pstmt.setTimestamp(2, dateFin);
				pstmt.setInt(3, 0);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					feries.add(rs.getTimestamp(I_HR_Public_Holiday.COLUMNNAME_Date_Jour_Ferie).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
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
			//	Convert to array
		}
		return feries;
	}

	public static BeanPeriode[] getCongesValidebyNameConge (Integer idCBPartner, String nomConge,Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> list = new ArrayList<>();
		if(idCBPartner!=null && nomConge!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql= new StringBuilder("SELECT hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective
					+", hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective
					+" FROM "+I_HR_Holiday.Table_Name+" hol"
					+" LEFT JOIN "+I_HR_Autorisation_Conge.Table_Name+" droitconge"
						+" ON droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+I_HR_Type_Conge.Table_Name+" typeconge"
						+" ON typeconge."+I_HR_Type_Conge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+I_HR_Holiday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_IsValidee+"=?"
					+" AND typeconge."+I_HR_Type_Conge.COLUMNNAME_Nom_Conge+"=?"
					+" AND (CAST (hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective+" AS timestamp), "
					+" CAST (hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective+" AS timestamp))"
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
					bp.setDateDebutConge(rs.getTimestamp(I_HR_Holiday.COLUMNNAME_Date_Debut_Effective));
					bp.setDateFinConge(rs.getTimestamp(I_HR_Holiday.COLUMNNAME_Date_Fin_Effective));
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
		for (int i = 0; i < retValue.length; i++) {
			retValue[i] = (list.get(i));
		}
		return retValue;
	}

	public static BeanPeriode[] getCongesNonRejetebyNameConge (Integer idCBPartner, String nomConge,Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> list = new ArrayList<>();
		if(idCBPartner!=null && nomConge!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql= new StringBuilder("SELECT hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective
					+", hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective
					+" FROM "+I_HR_Holiday.Table_Name+" hol"
					+" LEFT JOIN "+I_HR_Autorisation_Conge.Table_Name+" droitconge"
						+" ON droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+I_HR_Type_Conge.Table_Name+" typeconge"
						+" ON typeconge."+I_HR_Type_Conge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+I_HR_Holiday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_IsRejetee+"!=?"
					+" AND typeconge."+I_HR_Type_Conge.COLUMNNAME_Nom_Conge+"=?"
					+" AND (CAST (hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective+" AS timestamp), "
					+" CAST (hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective+" AS timestamp))"
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
					bp.setDateDebutConge(rs.getTimestamp(I_HR_Holiday.COLUMNNAME_Date_Debut_Effective));
					bp.setDateFinConge(rs.getTimestamp(I_HR_Holiday.COLUMNNAME_Date_Fin_Effective));
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
		for (int i = 0; i < retValue.length; i++) {
			retValue[i] = (list.get(i));
		}
		return retValue;
	}

	public static BeanPeriode[] getAllCongesValide (Integer idCBPartner, Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> list = new ArrayList<>();
		if(idCBPartner!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql= new StringBuilder("SELECT hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective
					+", hol. "+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective
					+" FROM "+I_HR_Holiday.Table_Name+" hol"
					+" WHERE hol."+I_HR_Holiday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_IsValidee+"=?"
					+" AND (CAST (hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective+" AS timestamp), "
					+" CAST (hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective+" AS timestamp))"
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
					bp.setDateDebutConge(rs.getTimestamp(I_HR_Holiday.COLUMNNAME_Date_Debut_Effective));
					bp.setDateFinConge(rs.getTimestamp(I_HR_Holiday.COLUMNNAME_Date_Fin_Effective));
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
		for (int i = 0; i < retValue.length; i++) {
			retValue[i] = (list.get(i));
		}
		return retValue;
	}

	public static BeanPeriode[] getAllCongesNonRejete (Integer idCBPartner, Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> list = new ArrayList<>();
		if(idCBPartner!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql= new StringBuilder("SELECT hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective
					+", hol. "+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective
					+" FROM "+I_HR_Holiday.Table_Name+" hol"
					+" WHERE hol."+I_HR_Holiday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_IsRejetee+"!=?"
					+" AND (CAST (hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective+" AS timestamp), "
					+" CAST (hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective+" AS timestamp))"
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
					bp.setDateDebutConge(rs.getTimestamp(I_HR_Holiday.COLUMNNAME_Date_Debut_Effective));
					bp.setDateFinConge(rs.getTimestamp(I_HR_Holiday.COLUMNNAME_Date_Fin_Effective));
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
		for (int i = 0; i < retValue.length; i++) {
			retValue[i] = (list.get(i));
		}
		return retValue;
	}

	public static ArrayList<MHRElementBasePaieEmploye> getDatesDerniersContrats(Integer idCBPartner, Timestamp dateMax, String trxName) {
		ArrayList<MHRElementBasePaieEmploye> resultat = new ArrayList<>();
		if(idCBPartner!=null && dateMax!=null) {
			StringBuilder sql = new StringBuilder("SELECT *"
								+" FROM "+I_HR_ElementBasePaieEmploye.Table_Name
								+" WHERE "+I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID+"=?"
								+" AND "+I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut+"<=?"
								+" ORDER BY "+I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut+" DESC");

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
			StringBuilder sql = new StringBuilder("SELECT hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective
					+", hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective
					+" FROM "+I_HR_Holiday.Table_Name+" hol"
					+" LEFT JOIN "+I_HR_Autorisation_Conge.Table_Name+" droitconge"
						+" ON droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+I_HR_Type_Conge.Table_Name+" typeconge"
						+" ON typeconge."+I_HR_Type_Conge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+I_HR_Autorisation_Conge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+I_HR_Holiday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective+"<?"
					+" AND hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective+">?"
			        +" AND hol."+I_HR_Holiday.COLUMNNAME_Date_Debut_Effective+" < hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective
					+" AND typeconge."+I_HR_Type_Conge.COLUMNNAME_Nom_Conge+"=?"
					+" ORDER BY hol."+I_HR_Holiday.COLUMNNAME_Date_Fin_Effective+" DESC");

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
					beanInfoConge.setDateDebutDernierConge(rs.getTimestamp(I_HR_Holiday.COLUMNNAME_Date_Debut_Effective));
					beanInfoConge.setDateFinDernierConge(rs.getTimestamp(I_HR_Holiday.COLUMNNAME_Date_Fin_Effective));
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
		ArrayList<BeanPeriode> listePeriodesSuspensions = new ArrayList<>();
		if(idCBPartner!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT pun."
					+I_HR_Punishment.COLUMNNAME_Date_Debut_Application
					+", pun."+I_HR_Punishment.COLUMNNAME_Date_Fin_Application
					+" FROM "+I_HR_Punishment.Table_Name+" pun"
					+" LEFT JOIN "+I_HR_Sanction_Autorisation.Table_Name+" droitsanction"
						+" ON droitsanction."+I_HR_Sanction_Autorisation.COLUMNNAME_HR_Sanction_Autorisation_ID+"=pun."+I_HR_Punishment.COLUMNNAME_Emission_Sanction_ID
					+" LEFT JOIN "+I_HR_TypeSanction.Table_Name+" typesanction"
						+" ON typesanction."+I_HR_TypeSanction.COLUMNNAME_HR_TypeSanction_ID+"=droitsanction."+I_HR_Sanction_Autorisation.COLUMNNAME_HR_TypeSanction_ID
					+" WHERE pun."+I_HR_Punishment.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND pun."+I_HR_Punishment.COLUMNNAME_IsValidee+"=?"
					+" AND typesanction."+I_HR_TypeSanction.COLUMNNAME_Incidence_Sanction_ID+"=?"
					+" AND (CAST (pun."+I_HR_Punishment.COLUMNNAME_Date_Debut_Application+" AS timestamp), "
					+" CAST(pun."+I_HR_Punishment.COLUMNNAME_Date_Fin_Application+" AS timestamp))"
					+" OVERLAPS (?, ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setString(3, X_HR_TypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension);
				pstmt.setTimestamp(4, dateDebut);
				pstmt.setTimestamp(5, dateFin);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanPeriode beanPeriode =BeanFactory.getBeanPeriode();
					beanPeriode.setDateDebutConge(rs.getTimestamp(I_HR_Punishment.COLUMNNAME_Date_Debut_Application));
					beanPeriode.setDateFinConge(rs.getTimestamp(I_HR_Punishment.COLUMNNAME_Date_Fin_Application));
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
		for (int i = 0; i < periodesSuspensions.length; i++) {
			periodesSuspensions[i] = (listePeriodesSuspensions.get(i));
		}
		return periodesSuspensions;
	}


	public static BeanPeriode[] getAllPeriodeSuspensionNonRejete (Integer idCBPartner, Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> listePeriodesSusoensions = new ArrayList<>();
		if(idCBPartner!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT pun."
					+I_HR_Punishment.COLUMNNAME_Date_Debut_Application
					+", pun."+I_HR_Punishment.COLUMNNAME_Date_Fin_Application
					+" FROM "+I_HR_Punishment.Table_Name+" pun"
					+" LEFT JOIN "+I_HR_Sanction_Autorisation.Table_Name+" droitsanction"
						+" ON droitsanction."+I_HR_Sanction_Autorisation.COLUMNNAME_HR_Sanction_Autorisation_ID+"=pun."+I_HR_Punishment.COLUMNNAME_Emission_Sanction_ID
					+" LEFT JOIN "+I_HR_TypeSanction.Table_Name+" typesanction"
						+" ON typesanction."+I_HR_TypeSanction.COLUMNNAME_HR_TypeSanction_ID+"=droitsanction."+I_HR_Sanction_Autorisation.COLUMNNAME_HR_TypeSanction_ID
					+" WHERE pun."+I_HR_Punishment.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND pun."+I_HR_Punishment.COLUMNNAME_IsRejetee+"!=?"
					+" AND typesanction."+I_HR_TypeSanction.COLUMNNAME_Incidence_Sanction_ID+"=?"
					+" AND (CAST (pun."+I_HR_Punishment.COLUMNNAME_Date_Debut_Application+" AS timestamp), "
					+" CAST(pun."+I_HR_Punishment.COLUMNNAME_Date_Fin_Application+" AS timestamp))"
					+" OVERLAPS (?, ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setString(3, X_HR_TypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension);
				pstmt.setTimestamp(4, dateDebut);
				pstmt.setTimestamp(5, dateFin);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanPeriode beanPeriode =BeanFactory.getBeanPeriode();
					beanPeriode.setDateDebutConge(rs.getTimestamp(I_HR_Punishment.COLUMNNAME_Date_Debut_Application));
					beanPeriode.setDateFinConge(rs.getTimestamp(I_HR_Punishment.COLUMNNAME_Date_Fin_Application));
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
		for (int i = 0; i < periodesSuspensions.length; i++) {
			periodesSuspensions[i] = (listePeriodesSusoensions.get(i));
		}
		return periodesSuspensions;
	}

	public static BeanAbsence[] getAllAbsenceConge (Integer cBpartnerID, Timestamp date, String trxName)
	{
		ArrayList<BeanAbsence> listeAbsences = new ArrayList<>();
		if(cBpartnerID!=null && date!=null) {
			StringBuilder sql = new StringBuilder("SELECT "+I_HR_Absence.COLUMNNAME_HR_Absence_ID
					+", "+I_HR_Absence.COLUMNNAME_Date_Absence
					+" FROM "+I_HR_Absence.Table_Name
					+" WHERE "+I_HR_Absence.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+I_HR_Absence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?");
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
					beanAbsence.setDateAbsence(rs.getTimestamp(I_HR_Absence.COLUMNNAME_Date_Absence));
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
		for (int i = 0; i < absences.length; i++) {
			absences[i] = (listeAbsences.get(i));
		}
		return absences;
	}

	public static int getParametreFromParametreNumerique(String nomParametre) {
		int resultat = 0;
		if(nomParametre!=null) {
			StringBuilder sql = new StringBuilder("SELECT para."+I_HR_Parametre_Numerique.COLUMNNAME_Valeur_Parametre
					+" FROM "+I_HR_Parametre_Numerique.Table_Name+" para"
					+" WHERE para."+I_HR_Parametre_Numerique.COLUMNNAME_Name+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setString(1, nomParametre);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getInt(I_HR_Parametre_Numerique.COLUMNNAME_Valeur_Parametre);
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
				+" FROM "+I_HR_Absence.Table_Name
				+" WHERE "+I_HR_Absence.COLUMNNAME_IsConge+"=?"
				+" AND "+I_HR_Absence.COLUMNNAME_IsCongeTraite+"=?"
				+" ANd "+I_HR_Absence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?");
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
		ArrayList<MHRElementBasePaieEmploye> resultat = new ArrayList<>();
		if(bpartnerID!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("WITH intervals AS ("
					+" SELECT "+I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_ElementBasePaieEmploye_ID
					+", "+I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut
					+", COALESCE("+I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin
						+", LEAD("+I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut+") OVER (ORDER BY "+I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut+") - INTERVAL '1 day'"
						+", ?) AS "+I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin+""
					+" FROM "+I_HR_ElementBasePaieEmploye.Table_Name
					+" WHERE "+I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID+"=?)"
					+" SELECT "+I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_ElementBasePaieEmploye_ID
					+", "+I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin
					+" FROM intervals"
					+" WHERE (CAST(" + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + " AS timestamp),"
					+" CAST(" + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin + " AS timestamp))"
					+" OVERLAPS (?, ?)"
					+" ORDER BY " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + " ASC");
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
					MHRElementBasePaieEmploye elementBasePaieEmploye = new MHRElementBasePaieEmploye(Env.getCtx(), rs.getInt(I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_ElementBasePaieEmploye_ID), null);
					if(elementBasePaieEmploye!=null) {
						if(elementBasePaieEmploye.getDate_Debut()!=null && elementBasePaieEmploye.getDate_Debut().before(dateDebut)) {
							elementBasePaieEmploye.setDate_Debut(dateDebut);
						}
						if(elementBasePaieEmploye.getDate_Fin()==null) {
							if( dateFin.before(rs.getTimestamp(I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin))) {
								elementBasePaieEmploye.setDate_Fin(dateFin);
							}
							else {
								elementBasePaieEmploye.setDate_Fin(rs.getTimestamp(I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin));
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
					+" FROM "+I_HR_Historique_Paie.Table_Name+" his"
					+" WHERE his."+I_HR_Historique_Paie.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND his."+I_HR_Historique_Paie.COLUMNNAME_HR_Element_Base_Paie_ID+"=?"
					+" AND his."+I_HR_Historique_Paie.COLUMNNAME_HR_Periode_Salariale_ID+"=?");
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
					+" FROM "+I_HR_EmployeeJob.Table_Name+" empjob"
					+" WHERE empjob."+I_HR_EmployeeJob.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND empjob."+I_HR_EmployeeJob.COLUMNNAME_DateFrom+"=?");
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
		ArrayList<MHRPeriodeSalariale> resultat = new ArrayList<>();
		if(dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT * FROM"
					+" "+I_HR_Periode_Salariale.Table_Name
					+" WHERE (CAST("+I_HR_Periode_Salariale.COLUMNNAME_Date_Debut_Defaut+"  AS timestamp),"
					+" CAST("+I_HR_Periode_Salariale.COLUMNNAME_Date_Fin_Defaut+"  AS timestamp))"
					+" OVERLAPS (?, ?)"
					+" ORDER BY "+I_HR_Periode_Salariale.COLUMNNAME_Date_Debut_Defaut+" ASC");
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
					+" FROM "+I_HR_Calcul_Indemnite_Conge.Table_Name+" ic"
					+" WHERE ic."+I_HR_Calcul_Indemnite_Conge.COLUMNNAME_C_BPartner_ID+"=?");
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
					+" FROM "+I_HR_Calcul_Paie.Table_Name+" cp"
					+" WHERE cp."+I_HR_Calcul_Paie.COLUMNNAME_C_BPartner_ID+"=?");
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
					+" FROM "+I_HR_Calcul_Conge.Table_Name+" cc"
					+" WHERE cc."+I_HR_Calcul_Conge.COLUMNNAME_C_BPartner_ID+"=?");
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
		ArrayList<MHRGestionPaieEmploye> resultat = new ArrayList<>();
		StringBuilder sql = new StringBuilder(" SELECT gpe.*"
	            +" FROM "+I_HR_GestionPaieEmploye.Table_Name+" gpe"
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
		ArrayList<MHRGestionPaieEmploye> resultat = new ArrayList<>();
		StringBuilder sql = new StringBuilder(" SELECT gpe.*"
	            +" FROM "+I_HR_GestionPaieEmploye.Table_Name+" gpe"
	            +" LEFT JOIN HR_Rang_Calcul rc ON gpe.HR_Rang_Calcul_ID = rc.HR_Rang_Calcul_ID"
	            +" WHERE gpe."+I_HR_GestionPaieEmploye.COLUMNNAME_IsIndemniteConge+"=?"
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
	    	StringBuilder sql = new StringBuilder("SELECT * FROM "+I_HR_Periode_Salariale.Table_Name
		               +" WHERE "+I_HR_Periode_Salariale.COLUMNNAME_Date_Debut_Defaut+" >= ? "
		               +"ORDER BY "+I_HR_Periode_Salariale.COLUMNNAME_Date_Debut_Defaut+" ASC ");
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
		ArrayList<MHRRetenueSalariale> resultat = new ArrayList<>();
		if(bpartnerID!=null && periodeSalariale!=null) {
			StringBuilder sql = new StringBuilder(" SELECT rs.*"
		            +" FROM "+I_HR_Retenue_Salariale.Table_Name+" rs"
		            +" LEFT JOIN "+I_HR_Periode_Salariale.Table_Name+" ps1"
		            +" ON ps1."+I_HR_Periode_Salariale.COLUMNNAME_HR_Periode_Salariale_ID+"=rs."+I_HR_Retenue_Salariale.COLUMNNAME_Debut_Prelevement_ID
		            +" LEFT JOIN "+I_HR_Periode_Salariale.Table_Name+" ps2"
		            +" ON ps2."+I_HR_Periode_Salariale.COLUMNNAME_HR_Periode_Salariale_ID+"=rs."+I_HR_Retenue_Salariale.COLUMNNAME_Fin_Prelevement_ID
		            +" WHERE "+I_HR_Retenue_Salariale.COLUMNNAME_C_BPartner_ID+"=?"
		            +" AND ps1."+I_HR_Periode_Salariale.COLUMNNAME_Date_Debut_Defaut+"<=?"
		            +" AND ps2."+I_HR_Periode_Salariale.COLUMNNAME_Date_Fin_Defaut+">=?");
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
	
	public static Integer getCurrentJobId(Integer cBPartnerId) {
		if(cBPartnerId!=null) {
			String sql = "SELECT "+MHREmployeeJob.COLUMNNAME_HR_EmployeeJob_ID
		    		+ " FROM "+MHREmployeeJob.Table_Name
		    		+" WHERE "+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+" = ? "
		    		+" ORDER BY "+MHREmployeeJob.COLUMNNAME_DateFrom+" DESC "
		    		+" LIMIT 1 ";

		    return DB.getSQLValue(null, sql, cBPartnerId);
		}
		else return null;	    
	}
	
	public static List<Integer> getPostesResponsables(Integer posteId) {

	    List<Integer> list = new ArrayList<>();
	    
	    if(posteId!=null) {
	    	
		    String sql = "SELECT "+MHROrganigramme.COLUMNNAME_Poste_Responsable_ID
		        + " FROM "+MHROrganigramme.Table_Name
		        + " WHERE "+MHROrganigramme.COLUMNNAME_Poste_ID+" = ?";


		    try (PreparedStatement ps = DB.prepareStatement(sql, null)) {
		        ps.setInt(1, posteId);
		        try (ResultSet rs = ps.executeQuery()) {
		            while (rs.next()) {
		                list.add(rs.getInt(1));
		            }
		        }
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    }
	    }

	    return list;
	}
	

	public static List<Integer> getPostesResponsablesbyCategorie(Integer posteId, Integer categorieResponsabiliteId) {

	    List<Integer> list = new ArrayList<>();
	    
	    if(posteId==null || categorieResponsabiliteId==null) {
	    	return null;
	    }
	    

	    String sql = "SELECT "+MHROrganigramme.COLUMNNAME_Poste_Responsable_ID
	        + " FROM "+MHROrganigramme.Table_Name
	        + " WHERE "+MHROrganigramme.COLUMNNAME_Poste_ID+" = ?"
	        + " AND "+MHROrganigramme.COLUMNNAME_HR_Categorie_Responsabilite_ID+" = ?";


	    try (PreparedStatement ps = DB.prepareStatement(sql, null)) {
	        ps.setInt(1, posteId);
	        ps.setInt(2, categorieResponsabiliteId);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                list.add(rs.getInt(1));
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }

	    return list;
	}
	
	public static List<Integer> getEmployeesByJob(Integer posteId) {

	    List<Integer> list = new ArrayList<>();
	    
	    if(posteId!=null) {
	    	String sql = "SELECT "+MHREmployeeJob.COLUMNNAME_C_BPartner_ID
	    	        + " FROM "+MHREmployeeJob.Table_Name
	    	        + " WHERE "+MHREmployeeJob.COLUMNNAME_HR_Job_ID+" = ?"
	    	        + " ORDER BY "+MHREmployeeJob.COLUMNNAME_DateFrom+ "DESC";


	    	    try (PreparedStatement ps = DB.prepareStatement(sql, null)) {
	    	        ps.setInt(1, posteId);
	    	        try (ResultSet rs = ps.executeQuery()) {
	    	            while (rs.next()) {
	    	                list.add(rs.getInt(1));
	    	            }
	    	        }
	    	    } catch (SQLException e) {
	    	        throw new RuntimeException(e);
	    	    }
	    }

	    return list;
	}
	
    public static List<Integer> getEmployeesByRoles(
            List<Integer> roleIds
    ) {

        List<Integer> result = new ArrayList<Integer>();

        if (roleIds == null || roleIds.isEmpty()) {
            return result;
        }

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT bp.C_BPartner_ID ");
        sql.append("FROM "+MADUser.Table_Name+" u ");
        sql.append("JOIN "+MBPartner.Table_Name+" bp ON "+MBPartner.COLUMNNAME_C_BPartner_ID+" = u."+MBPartner.COLUMNNAME_C_BPartner_ID+" ");
        sql.append("JOIN "+MADUserRoles.Table_Name+" ur ON ur."+MADUserRoles.COLUMNNAME_AD_User_ID+" = u."+MADUser.COLUMNNAME_AD_User_ID+" ");
        sql.append("WHERE u."+MADUser.COLUMNNAME_IsActive+"='Y' ");
        sql.append("AND bp."+MBPartner.COLUMNNAME_IsActive+"='Y' ");
        sql.append("AND ur."+MADUserRoles.COLUMNNAME_AD_Role_ID+" IN (");

        appendPlaceholders(sql, roleIds.size());
        sql.append(")");

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = DB.prepareStatement(sql.toString(), null);

            int index = 1;
            for (Integer id : roleIds) {
                ps.setInt(index++, id);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, ps);
        }

        return result;
    }

	
	public static List<Integer> getEmployeesByCategoriesResponsabilite(
	        List<Integer> categorieIds,
	        Timestamp dateReference
	) {

	    List<Integer> result = new ArrayList<Integer>();

	    if (categorieIds == null || categorieIds.isEmpty()) {
	        return result;
	    }

	    if (dateReference == null) {
	        dateReference = new Timestamp(System.currentTimeMillis());
	    }

	    StringBuilder sql = new StringBuilder();

	    sql.append("SELECT DISTINCT bp."+MBPartner.COLUMNNAME_C_BPartner_ID+" ");
	    sql.append("FROM "+MBPartner.Table_Name+" bp ");

	    // Dernier poste valide de l'employé
	    sql.append("JOIN "+MHRElementBasePaieEmploye.Table_Name+" ebpe ");
	    sql.append("  ON ebpe."+MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID+" = bp."+MBPartner.COLUMNNAME_C_BPartner_ID+" ");

	    sql.append("JOIN ( ");
	    sql.append("    SELECT "+MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID+", MAX("+MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut+") AS Max_Date ");
	    sql.append("    FROM "+MHRElementBasePaieEmploye.Table_Name+" ");
	    sql.append("    WHERE "+MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut+" <= ? ");
	    sql.append("    GROUP BY "+MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID+" ");
	    sql.append(") last_poste ");
	    sql.append("  ON last_poste."+MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID+" = ebpe."+MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID+" ");
	    sql.append(" AND last_poste.Max_Date = ebpe."+MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut+" ");

	    // Lien poste → catégorie de responsabilité
	    sql.append("JOIN "+MHROrganigramme.Table_Name+" org ");
	    sql.append("  ON org."+MHROrganigramme.COLUMNNAME_Poste_ID+" = ebpe."+MHRElementBasePaieEmploye.COLUMNNAME_HR_Job_ID+" ");

	    sql.append("WHERE bp."+MBPartner.COLUMNNAME_IsActive+"='Y' ");
	    sql.append("AND org."+MHROrganigramme.COLUMNNAME_HR_Categorie_Responsabilite_ID+" IN (");
	    appendPlaceholders(sql, categorieIds.size());
	    sql.append(")");

	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        ps = DB.prepareStatement(sql.toString(), null);

	        int index = 1;

	        // date de référence
	        ps.setTimestamp(index++, dateReference);

	        // catégories
	        for (Integer id : categorieIds) {
	            ps.setInt(index++, id);
	        }

	        rs = ps.executeQuery();

	        while (rs.next()) {
	            result.add(rs.getInt(1));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DB.close(rs, ps);
	    }

	    return result;
	}
	
	private static void appendPlaceholders(
	        StringBuilder sql,
	        int count
	) {
	    if (count <= 0) {
	        throw new IllegalArgumentException(
	            "Le nombre de placeholders doit être > 0"
	        );
	    }

	    for (int i = 0; i < count; i++) {
	        if (i > 0) {
	            sql.append(", ");
	        }
	        sql.append("?");
	    }
	}



}
