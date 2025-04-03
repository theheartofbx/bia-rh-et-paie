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
					BeanPeriode[] conges = GeneralSqlController.getCongesValidebyNameConge(idCBPartner, "Annuel", 
							GeneralController.getFirstDayOfThisYear(), GeneralController.getLastDayOfThisYear(), null);
					if(conges!=null) {
						for (BeanPeriode beanPeriode : conges) {
							res = res + GeneralController.getNombreJourTravaille(beanPeriode.getDateDebutConge(), beanPeriode.getDateFinConge());
						}
					}
					Timestamp now = new Timestamp(System.currentTimeMillis());
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.YEAR, -6);
					Timestamp moinsDeSixAns = new Timestamp(cal.getTime().getTime());
					StringBuilder sql = new StringBuilder("SELECT bp."+MCBPartner.COLUMNNAME_Sex
							+", bp."+MCBPartner.COLUMNNAME_DateFrom
							+", child."+MHREmployeeChildren.COLUMNNAME_Date_Naissance
							+" FROM "+MCBPartner.Table_Name+" bp"
							+" LEFT JOIN "+MHREmployeeChildren.Table_Name+" child"
								+" ON child."+MHREmployeeChildren.COLUMNNAME_C_BPartner_ID+"=bp."+MCBPartner.COLUMNNAME_C_BPartner_ID
								+" AND child."+MHREmployeeChildren.COLUMNNAME_Date_Naissance+">?"
							+" WHERE bp."+MCBPartner.COLUMNNAME_C_BPartner_ID+"=?");					;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try
					{
						pstmt = DB.prepareStatement(sql.toString(), trxName);
						pstmt.setTimestamp(1, moinsDeSixAns);
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
					GeneralSqlController.getDateDernierConge(idCBPartner, new Timestamp(System.currentTimeMillis()), "Annuel", beanConge, null);
					beanConge.setDetteConge(getNombreJourAbsencesConge(new Timestamp(System.currentTimeMillis()), null));
				}
			}
		}
		return beanConge;		
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
			String sql ="SELECT hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective
					+", hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective
					+ " FROM "+MHRHoliday.Table_Name+" hol "
					+ " WHERE (hol."+MHRHoliday.COLUMNNAME_Date_Debut_Effective+" BETWEEN ? AND ? "
					+ " OR hol."+MHRHoliday.COLUMNNAME_Date_Fin_Effective+" BETWEEN ? AND ?)"
					+ " AND hol."+MHRHoliday.COLUMNNAME_IsValidee+"=?"
					+ " AND hol."+MHRHoliday.COLUMNNAME_C_BPartner_ID+" IN (SELECT a."+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+" FROM"
					+ " (SELECT "+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+", "+MHREmployeeJob.COLUMNNAME_HR_Job_ID+", MAX("+MHREmployeeJob.COLUMNNAME_DateFrom+") AS maxdate"
							+ " FROM "+MHREmployeeJob.Table_Name+" GROUP BY "+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+") a "
							+ " LEFT JOIN "+MHRJob.Table_Name+" job"
									+ " ON job."+MHRJob.COLUMNNAME_HR_Job_ID+"=a."+MHREmployeeJob.COLUMNNAME_HR_Job_ID
							+ " WHERE job."+MHRJob.COLUMNNAME_HR_Department_ID+"=(SELECT job1.+"+MHRJob.COLUMNNAME_HR_Department_ID+" FROM a"
							+ " LEFT JOIN "+MHRJob.Table_Name+" job1"
									+ " ON a."+MHREmployeeJob.COLUMNNAME_HR_Job_ID+"=job1."+MHRJob.COLUMNNAME_HR_Job_ID
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
	
	public static BeanResumeAbsence getResumeAbsenceConge (Integer cBpartnerID, Timestamp dateFrom, Timestamp dateTo,String trxName)
	{
		String date = "";
		int nombre = 0;
		BeanResumeAbsence resultat = null;
		if(cBpartnerID!=null && dateFrom!=null && dateTo!=null) {
			resultat = BeanFactory.getBeanResumeAbsence();
			resultat.setListAbsenceID(new ArrayList<Integer>());
			StringBuilder sql = new StringBuilder("SELECT "+MHRAbsence.COLUMNNAME_HR_Absence_ID
					+", "+MHRAbsence.COLUMNNAME_Date_Absence
					+" FROM "+MHRAbsence.Table_Name
					+" WHERE "+MHRAbsence.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+MHRAbsence.COLUMNNAME_IsConge+"=?"
					+" AND "+MHRAbsence.COLUMNNAME_IsCongeTraite+"!=?"
					+" AND "+MHRAbsence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cBpartnerID);
				pstmt.setString(2, "Y");
				pstmt.setString(3, "Y");
				pstmt.setTimestamp(4, dateFrom);
				pstmt.setTimestamp(5, dateTo);
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
			resultat.setDate(date);
			resultat.setNombreJour(nombre);
		}
		return resultat;
	}	

	public static Integer getNumberEmployeDepartment(Integer cbpartnerid) {
		Integer resultat = 0;
		StringBuilder sql = new StringBuilder("SELECT COUNT(a."+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+") AS count FROM"
				+ " (SELECT "+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+", "+MHREmployeeJob.COLUMNNAME_HR_Job_ID+", MAX("+MHREmployeeJob.COLUMNNAME_DateFrom+") AS maxdate"
				+ " FROM "+MHREmployeeJob.Table_Name+" GROUP BY "+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+") a "
				+ " LEFT JOIN "+MHRJob.Table_Name+" job"
						+ " ON job."+MHRJob.COLUMNNAME_HR_Job_ID+"=a."+MHREmployeeJob.COLUMNNAME_HR_Job_ID
				+ " WHERE job."+MHRJob.COLUMNNAME_HR_Department_ID+"=(SELECT job1.+"+MHRJob.COLUMNNAME_HR_Department_ID+" FROM a"
				+ " LEFT JOIN "+MHRJob.Table_Name+" job1"
						+ " ON a."+MHREmployeeJob.COLUMNNAME_HR_Job_ID+"=job1."+MHRJob.COLUMNNAME_HR_Job_ID
				+ " WHERE a."+MHREmployeeJob.COLUMNNAME_C_BPartner_ID+"=?)");
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
	
	public static int getNombreJourAbsencesConge (Timestamp date, String trxName)
	{
		Timestamp firstDayOfYear = GeneralController.getFirstDayOfThisYear();
		Timestamp lastDayOfYear = GeneralController.getLastDayOfThisYear();
		int resultat = 0;
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS count FROM "
				+" "+MHRAbsence.Table_Name
				+" WHERE "+MHRAbsence.COLUMNNAME_IsConge+"=?"
				+" AND "+MHRAbsence.COLUMNNAME_IsCongeTraite+"=?"
				+" AND "+MHRAbsence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?");
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
}
