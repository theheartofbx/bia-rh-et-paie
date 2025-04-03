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
					+" FROM "+MCBPartner.Table_ID+" cb"
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

	public static Timestamp[] getAllJoursFeries (Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<Timestamp> list = new ArrayList<Timestamp>();
		StringBuilder sql = new StringBuilder("SELECT "+MHRPublicHoliday.COLUMNNAME_Date_Jour_Ferie
				+" FROM "+MHRPublicHoliday.Table_Name
				+" WHERE "+MHRPublicHoliday.COLUMNNAME_Date_Jour_Ferie+" BETWEEN ? AND ?");
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

	public static BeanPeriode[] getCongesValidebyNameConge (Integer idCBPartner, String nomConge,Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> list = new ArrayList<BeanPeriode>();
		if(idCBPartner!=null && nomConge!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql= new StringBuilder("SELECT hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective
					+", hol. "+MHRHoliday.COLUMNNAME_Date_Fin_Effective
					+" FROM "+MHRHoliday.Table_Name+" hol"
					+" LEFT JOIN "+MHRAutorisationConge.Table_Name+" droitconge"
						+" ON droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+MHRHoliday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+MHRTypeConge.Table_Name+" typeconge"
						+" ON typeconge."+MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_IsValidee+"=?"
					+" AND typeconge."+MHRTypeConge.COLUMNNAME_Nom_Conge+"=?"
					+" AND (hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+" BETWEEN ? AND ?"
					+" OR hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" BETWEEN ? AND ?)");
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
				pstmt.setTimestamp(6, dateDebut);
				pstmt.setTimestamp(7, dateFin);
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
					+", hol. "+MHRHoliday.COLUMNNAME_Date_Fin_Effective
					+" FROM "+MHRHoliday.Table_Name+" hol"
					+" LEFT JOIN "+MHRAutorisationConge.Table_Name+" droitconge"
						+" ON droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+MHRHoliday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+MHRTypeConge.Table_Name+" typeconge"
						+" ON typeconge."+MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_IsRejetee+"!=?"
					+" AND typeconge."+MHRTypeConge.COLUMNNAME_Nom_Conge+"=?"
					+" AND (hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+" BETWEEN ? AND ?"
					+" OR hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" BETWEEN ? AND ?)");
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
				pstmt.setTimestamp(6, dateDebut);
				pstmt.setTimestamp(7, dateFin);
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
					+" AND (hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+" BETWEEN ? AND ?"
					+" OR hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" BETWEEN ? AND ?)");
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
					+" AND (hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+" BETWEEN ? AND ?"
					+" OR hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" BETWEEN ? AND ?)");
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
	
	public static BeanConge getDateDernierConge(Integer idCBPartner, Timestamp dateMax, String nomConge, BeanConge beanInfoConge, String trxName) {
		if(idCBPartner!=null && dateMax!=null && nomConge!=null &&beanInfoConge!=null) {
			StringBuilder sql = new StringBuilder("SELECT hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective
					+", hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective
					+", bp."+MCBPartner.COLUMNNAME_DateFrom
					+" FROM "+MHRHoliday.Table_Name+" hol"
					+" RIGHT JOIN "+MCBPartner.Table_Name+" bp"
						+" ON bp."+MCBPartner.COLUMNNAME_C_BPartner_ID+"=hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID
					+" LEFT JOIN "+MHRAutorisationConge.Table_Name+" droitconge"
						+" ON droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID+"=hol."+MHRHoliday.COLUMNNAME_Emission_Conge_ID
					+" LEFT JOIN "+MHRTypeConge.Table_Name+" typeconge"
						+" ON typeconge."+MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID+"=droitconge."+MHRAutorisationConge.COLUMNNAME_HR_Type_Conge_ID
					+" WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+"<?"
					+" AND typeconge."+MHRTypeConge.COLUMNNAME_Nom_Conge+"=?"
					+" ORDER BY hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" DESC"
					+" LIMIT 1");
			;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setTimestamp(2, dateMax);
				pstmt.setString(03, nomConge);				
				rs = pstmt.executeQuery();
				if (rs.next()) {
					beanInfoConge.setDateDebutDernierConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Debut_Effective));
					beanInfoConge.setDateFindernierConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Fin_Effective));
					beanInfoConge.setDateEmbauche(rs.getTimestamp(MCBPartner.COLUMNNAME_DateFrom));
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
		return beanInfoConge;
	}
	
	public static BeanPeriode[] getAllPeriodeSuspensionValide (Integer idCBPartner, Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> listePeriodesSusoensions = new ArrayList<BeanPeriode>();
		if(idCBPartner!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT pun."
					+MHRPunishment.COLUMNNAME_Date_Debut_Application
					+", "+MHRPunishment.COLUMNNAME_Date_Fin_Application
					+" FROM "+MHRPunishment.Table_Name+" pun"
					+" LEFT JOIN "+MHRSanctionAutorisation.Table_Name+" droitsanction"
						+" ON droitsanction."+MHRSanctionAutorisation.COLUMNNAME_HR_Sanction_Autorisation_ID+"=pun."+MHRPunishment.COLUMNNAME_Emission_Sanction_ID
					+" LEFT JOIN "+MHRTypeSanction.Table_Name+" typesanction"
						+" ON typesanction."+MHRTypeSanction.COLUMNNAME_HR_TypeSanction_ID+"=droitsanction."+MHRSanctionAutorisation.COLUMNNAME_HR_TypeSanction_ID
					+" WHERE pun."+MHRPunishment.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND pun."+MHRPunishment.COLUMNNAME_IsValidee+"=?"
					+" AND typesanction."+MHRTypeSanction.COLUMNNAME_Incidence_Sanction_ID+"=?"
					+" AND (pun."+MHRPunishment.COLUMNNAME_Date_Debut_Application+" BETWEEN ? AND ?"
					+" OR pun."+MHRPunishment.COLUMNNAME_Date_Fin_Application+" BETWEEN ? AND ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			log.warning("\n1REQUETE SQL : "+sql);
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setString(3, MHRTypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension);
				pstmt.setTimestamp(4, dateDebut);
				pstmt.setTimestamp(5, dateFin);
				pstmt.setTimestamp(6, dateDebut);
				pstmt.setTimestamp(7, dateFin);
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
	

	public static BeanPeriode[] getAllPeriodeSuspensionNonRejete (Integer idCBPartner, Timestamp dateDebut, Timestamp dateFin, String trxName)
	{
		ArrayList<BeanPeriode> listePeriodesSusoensions = new ArrayList<BeanPeriode>();
		if(idCBPartner!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT pun."
					+MHRPunishment.COLUMNNAME_Date_Debut_Application
					+", "+MHRPunishment.COLUMNNAME_Date_Fin_Application
					+" FROM "+MHRPunishment.Table_Name+" pun"
					+" LEFT JOIN "+MHRSanctionAutorisation.Table_Name+" droitsanction"
						+" ON droitsanction."+MHRSanctionAutorisation.COLUMNNAME_HR_Sanction_Autorisation_ID+"=pun."+MHRPunishment.COLUMNNAME_Emission_Sanction_ID
					+" LEFT JOIN "+MHRTypeSanction.Table_Name+" typesanction"
						+" ON typesanction."+MHRTypeSanction.COLUMNNAME_HR_TypeSanction_ID+"=droitsanction."+MHRSanctionAutorisation.COLUMNNAME_HR_TypeSanction_ID
					+" WHERE pun."+MHRPunishment.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND pun."+MHRPunishment.COLUMNNAME_IsRejetee+"!=?"
					+" AND typesanction."+MHRTypeSanction.COLUMNNAME_Incidence_Sanction_ID+"=?"
					+" AND (pun."+MHRPunishment.COLUMNNAME_Date_Debut_Application+" BETWEEN ? AND ?"
					+" OR pun."+MHRPunishment.COLUMNNAME_Date_Fin_Application+" BETWEEN ? AND ?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			log.warning("\n1REQUETE SQL : "+sql);
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setString(3, MHRTypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension);
				pstmt.setTimestamp(4, dateDebut);
				pstmt.setTimestamp(5, dateFin);
				pstmt.setTimestamp(6, dateDebut);
				pstmt.setTimestamp(7, dateFin);
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
				StringBuilder sql = new StringBuilder("SELECT HR_Organigramme_ID id "
						+ "FROM HR_Organigramme org WHERE "
						+ "org.Poste_ID=? AND "
						+ "org.Poste_Responsable_ID=?");
				
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
}
