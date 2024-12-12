package org.sitracel.conge.callout.conge.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanConge;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.bean.BeanResumeAbsence;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAutorisationConge;
import org.sitracel.conge.model.MHREmployeeChildren;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.model.MCBPartner;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.model.MHRJob;

public class CalloutSqlControllerConge {


	private static CLogger	log = CLogger.getCLogger (PO.class);
	public static BeanConge getInfoConge(Integer idCBPartner, Integer idTypeConge,String trxName) {
		BeanConge beanConge =BeanFactory.getBeanConge();
		if(idCBPartner!=null && idTypeConge!=null) {
			int res = 0;
			MHRTypeConge conge = new MHRTypeConge(Env.getCtx(), idTypeConge, trxName);
			if(conge!=null) {
				if(conge.isCongeAnnuel()) {
					BeanPeriode[] conges = GeneralSqlController.getAllCongesAnnuel(idCBPartner, new Timestamp(System.currentTimeMillis()), null);
					if(conges!=null) {
						for (BeanPeriode beanPeriode : conges) {
							res = res + CalloutControllerConge.getNombreJourTravaille(beanPeriode.getDateDebutConge(), beanPeriode.getDateFinConge());
						}
					}
					Timestamp now = new Timestamp(System.currentTimeMillis());
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.YEAR, -6);
					Timestamp petit = new Timestamp(cal.getTime().getTime());

					StringBuilder sql = new StringBuilder("SELECT cb.");
					sql.append(MCBPartner.COLUMNNAME_Sex).append(", cb.").append(MCBPartner.COLUMNNAME_DateFrom).append(", child.").append(MHREmployeeChildren.COLUMNNAME_Date_Naissance)
					.append(" FROM ").append(MCBPartner.Table_Name).append(" cb LEFT JOIN ").append(MHREmployeeChildren.Table_Name)
					.append(" child ON cb.").append(MCBPartner.COLUMNNAME_C_BPartner_ID).append("=child.").append(MHREmployeeChildren.COLUMNNAME_C_BPartner_ID).append(" AND child.")
					.append(MHREmployeeChildren.COLUMNNAME_Date_Naissance).append(">?").append(" WHERE cb.").append(MCBPartner.COLUMNNAME_C_BPartner_ID).append("=?")
					;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try
					{
						pstmt = DB.prepareStatement(sql.toString(), trxName);
						pstmt.setTimestamp(1, petit);
						pstmt.setInt(2, idCBPartner);
						rs = pstmt.executeQuery();
						int in = 0;
						List<Integer> list = new ArrayList<Integer>();
						while (rs.next()) {
							if(in==0) {
								beanConge.setAnneeAnciennete(CalloutControllerConge.getDiffAnnee(now, rs.getTimestamp(MCBPartner.COLUMNNAME_DateFrom)));
								beanConge.setMoisAnciennete(cal.get(Calendar.MONTH));
								beanConge.setJourAnciennete(cal.get(Calendar.DAY_OF_MONTH));								
								beanConge.setGenre(rs.getString(MCBPartner.COLUMNNAME_Sex));
							}
							if(rs.getTimestamp(MHREmployeeChildren.COLUMNNAME_Date_Naissance)!=null) {
								cal.setTime(rs.getTimestamp(MHREmployeeChildren.COLUMNNAME_Date_Naissance));
								list.add(cal.get(Calendar.YEAR));
							}
							in++;
						}
						beanConge.setNombreEnfantPetit(list.size());
						int nombreJourCongeBase = GeneralSqlController.getParametreFromParametreNumerique("Congé Annuel de Base");
						int nbBase = 0;
						if(beanConge.getAnneeAnciennete()!=null) {
							nbBase = nombreJourCongeBase;
							nbBase = nbBase + (2*((int)beanConge.getAnneeAnciennete()/3));
						}
						if(beanConge.getGenre().equals(MCBPartner.SEX_Femme)) {
							nbBase = nbBase+(2*beanConge.getNombreEnfantPetit());
						}
						beanConge.setNombreJourCongeTotal(nbBase);
						beanConge.setNombreJourCongeUtilise(res);
						beanConge.setAnneeNaissance(list);
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
					beanConge = getDateDernierConge(idCBPartner, beanConge);
					BeanPeriode dernierConge = getDateDernierCongeAnnuel(idCBPartner);
					if(dernierConge!=null && beanConge!=null) {
						beanConge.setDateDebutDernierConge(dernierConge.getDateDebutConge());
						beanConge.setDateFindernierConge(dernierConge.getDateFinConge());
					}
					beanConge.setDetteConge(getNombreJourAbsencesConge(new Timestamp(System.currentTimeMillis()), null));
				}
			}
		}
		return beanConge;		
	}
	
	public static BeanConge getDateDernierConge(Integer idCBPartner, BeanConge beanInfoConge) {

		StringBuilder sql = new StringBuilder("SELECT hol.");
		sql.append(MHRHoliday.COLUMNNAME_Date_Debut_Effective).append(", hol.").append(MHRHoliday.COLUMNNAME_Date_Fin_Effective)
		.append(" FROM ").append(MHRHoliday.Table_Name).append(" hol ")
		.append(" LEFT JOIN ").append(MHRAutorisationConge.Table_Name)
		.append(" droitconge ON droitconge.").append(MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID).append("=hol.").append(MHRHoliday.COLUMNNAME_Emission_Conge_ID)
		.append(" LEFT JOIN ").append(MHRTypeConge.Table_Name)
		.append(" typeconge ON typeconge.").append(MHRTypeConge.COLUMNNAME_HR_Type_Conge_ID).append("=droitconge.").append(MHRAutorisationConge.COLUMNNAME_HR_Autorisation_Conge_ID)
		.append(" WHERE hol.").append(MHRHoliday.COLUMNNAME_C_BPartner_ID).append("=? AND typeconge.").append(MHRTypeConge.COLUMNNAME_IsCongeAnnuel).append("=? AND hol.")
		.append(MHRHoliday.COLUMNNAME_Date_Fin_Effective).append(" < NOW() FETCH FIRST ROW ONLY")
		;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, idCBPartner);
			pstmt.setString(2, "Y");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				beanInfoConge.setDateDebutDernierConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Debut_Effective));
				beanInfoConge.setDateFindernierConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Fin_Effective));
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

		return beanInfoConge;
	}
	
	public static BeanPeriode[] getAllCongesDepFromCBPartnerID (Timestamp dateDebut, Timestamp dateFin, Integer cbpartnerid, String trxName)
	{
		ArrayList<BeanPeriode> list = new ArrayList<BeanPeriode>();
		if(dateDebut!=null && dateFin!=null && cbpartnerid!=null) {
			Timestamp firstDayOfthisYear = GeneralController.getFirstDayOfThisYear();
			Timestamp lastDayOfthisYear = GeneralController.getLastDayOfThisYear();
			if(dateDebut.before(firstDayOfthisYear)) {
				dateDebut=firstDayOfthisYear;
			}
			if(dateFin.after(lastDayOfthisYear)) {
				dateFin=lastDayOfthisYear;
			}
			String sql ="SELECT hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+", hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective
					+ " FROM "+MHRHoliday.Table_Name+" hol "
					+ " WHERE hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+" BETWEEN ? AND ? "
					+ " OR hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" BETWEEN ? AND ?"
					+ " AND hol."+MHRHoliday.COLUMNNAME_IsValidee+"=?"
					+ " AND hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+" IN (SELECT a."+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+" FROM"
					+ " (SELECT "+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+", "+MHREmployeeJob.COLUMNNAME_HR_Job_ID+", MAX("+MHREmployeeJob.COLUMNNAME_DateFrom+") AS maxdate"
							+ " FROM "+MHREmployeeJob.Table_Name+" GROUP BY "+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+") a "
							+ " LEFT JOIN "+MHRJob.Table_Name+" job ON job."+MHRJob.COLUMNNAME_HR_Job_ID+"=a."+MHREmployeeJob.COLUMNNAME_HR_Job_ID
							+ " WHERE job."+MHRJob.COLUMNNAME_HR_Department_ID+"=(SELECT job1.+"+MHRJob.COLUMNNAME_HR_Department_ID+" FROM a"
							+ " LEFT JOIN "+MHRJob.Table_Name+" job1 ON a."+MHREmployeeJob.COLUMNNAME_HR_Job_ID+"=job1."+MHRJob.COLUMNNAME_HR_Job_ID
							+ " WHERE a."+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+"=?))";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, dateDebut);
				pstmt.setTimestamp(2, dateFin);
				pstmt.setTimestamp(3, dateDebut);
				pstmt.setTimestamp(4, dateFin);
				pstmt.setString(5, "Y");
				pstmt.setInt(6, cbpartnerid);
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
	
	public static BeanResumeAbsence getResumeAbsenceConge (Integer cBpartnerID, String trxName)
	{
		String date = "";
		int nombre = 0;
		BeanResumeAbsence resultat = BeanFactory.getBeanResumeAbsence();
		resultat.setListAbsenceID(new ArrayList<Integer>());
		if(cBpartnerID!=null) {
			StringBuilder sql = new StringBuilder("SELECT ");
			sql.append(MHRAbsence.Table_Name+"."+MHRAbsence.COLUMNNAME_HR_Absence_ID)
			.append(", ").append(MHRAbsence.Table_Name).append("."+MHRAbsence.COLUMNNAME_Date_Absence)
			.append(" FROM ").append(MHRAbsence.Table_Name).append(" WHERE "+MHRAbsence.COLUMNNAME_C_BPartner_ID)
			.append(" = ? AND ").append(MHRAbsence.COLUMNNAME_IsConge).append(" = ? AND ")
			.append(MHRAbsence.COLUMNNAME_IsCongeTraite).append(" != ?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cBpartnerID);
				pstmt.setString(2, "Y");
				pstmt.setString(3, "N");
				rs = pstmt.executeQuery();
				boolean first = true;
				while (rs.next()) {
					nombre++;
					if(first) {
						date = date + ""+ new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(rs.getTimestamp(MHRAbsence.COLUMNNAME_Date_Absence));
						first= false;
					}
					else {
						date = date + ", "+ new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(rs.getTimestamp(MHRAbsence.COLUMNNAME_Date_Absence));
					}
					resultat.getListAbsenceID().add(rs.getInt(MHRAbsence.COLUMNNAME_HR_Absence_ID));
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
		resultat.setDate(date);
		resultat.setNombreJour(nombre);
		return resultat;
	}	

	public static Integer getNumberEmployeDepartment(Integer cbpartnerid) {
		Integer resultat = 0;
		String sql = "SELECT COUNT(a."+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+") AS count FROM"
				+ " (SELECT "+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+", "+MHREmployeeJob.COLUMNNAME_HR_Job_ID+", MAX("+MHREmployeeJob.COLUMNNAME_DateFrom+") AS maxdate"
				+ " FROM "+MHREmployeeJob.Table_Name+" GROUP BY "+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+") a "
				+ " LEFT JOIN "+MHRJob.Table_Name+" job ON job."+MHRJob.COLUMNNAME_HR_Job_ID+"=a."+MHREmployeeJob.COLUMNNAME_HR_Job_ID
				+ " WHERE job."+MHRJob.COLUMNNAME_HR_Department_ID+"=(SELECT job1.+"+MHRJob.COLUMNNAME_HR_Department_ID+" FROM a"
				+ " LEFT JOIN "+MHRJob.Table_Name+" job1 ON a."+MHREmployeeJob.COLUMNNAME_HR_Job_ID+"=job1."+MHRJob.COLUMNNAME_HR_Job_ID
				+ " WHERE a."+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+"=?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, cbpartnerid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				resultat = rs.getInt("count");
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
		return resultat;
	}	

	public static Integer getCbpartnerIDFromHRJob(Integer HRJobID) {
		Integer resultat = null;
		String sql  = "SELECT "+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+" FROM "+MHREmployeeJob.Table_Name
				+" empjob WHERE  empjob."+MHREmployeeJob.COLUMNNAME_HR_Job_ID+"=? AND empjob."+MHREmployeeJob.COLUMNNAME_DateFrom+" = "
				+ "(SELECT MAX(empjob1."+MHREmployeeJob.COLUMNNAME_DateFrom+") FROM "+MHREmployeeJob.Table_Name+" empjob1 WHERE empjob1."+MHREmployeeJob.COLUMNNAME_HR_Job_ID+"=?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, HRJobID);
			pstmt.setInt(2, HRJobID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				resultat = rs.getInt(MHREmployeeJob.COLUMNNAME_C_BPartner_ID);
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
		return resultat;
	}	

	public static int getNombreJourAbsencesConge (Timestamp date, String trxName)
	{
		Timestamp firstDayOfYear = GeneralController.getFirstDayOfThisYear();
		Timestamp lastDayOfYear = GeneralController.getLastDayOfThisYear();
		int resultat = 0;
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS count FROM ");
		sql.append(MHRAbsence.Table_Name)
		.append(" WHERE "+MHRAbsence.COLUMNNAME_IsConge+"=? AND ")
		.append(MHRAbsence.COLUMNNAME_IsCongeTraite+"=? AND ")
		.append(MHRAbsence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?");
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
			log.warning(e.getMessage());
			return resultat;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return resultat;
	}	

	public static BeanPeriode getDateDernierCongeAnnuel(Integer idCBPartner) {
		BeanPeriode resultat = BeanFactory.getBeanPeriode();
		if(idCBPartner!=null) {
			StringBuilder sql = new StringBuilder("SELECT hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+", ");
			sql.append("hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" FROM ")
			.append(MHRHoliday.Table_Name)			
			.append(" hol WHERE hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=? AND hol."+MHRHoliday.COLUMNNAME_IsCongeAnnuel+"=?")
			.append(" AND hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+"=(SELECT MAX("+MHRHoliday.COLUMNNAME_Date_Debut_Effective+")")
			.append(" FROM "+MHRHoliday.Table_Name+" WHERE "+MHRHoliday.COLUMNNAME_C_BPartner_ID+"=? AND ")
			.append(MHRHoliday.COLUMNNAME_IsCongeAnnuel+"=?)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, idCBPartner);
				pstmt.setString(2, "Y");
				pstmt.setInt(3, idCBPartner);
				pstmt.setString(4, "Y");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat.setDateDebutConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Debut_Effective));
					resultat.setDateFinConge(rs.getTimestamp(MHRHoliday.COLUMNNAME_Date_Fin_Effective));
				}
				else {
					MCBPartner employe = new MCBPartner(Env.getCtx(), idCBPartner, null);
					if(employe!=null) {
						resultat.setDateDebutConge(employe.getDateFrom());
						resultat.setDateFinConge(employe.getDateFrom());
					}
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
	
}
