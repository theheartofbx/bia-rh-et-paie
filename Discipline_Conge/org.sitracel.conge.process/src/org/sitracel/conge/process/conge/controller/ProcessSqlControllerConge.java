package org.sitracel.conge.process.conge.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.util.DB;
import org.sitracel.callout.bean.BeanIdentifiant;
import org.sitracel.callout.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.general.controller.GeneralController;
import org.sitracel.model.MCBPartner;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.model.MHRJob;

public class ProcessSqlControllerConge {
	
	public static BeanIdentifiant getBeanIdentifiant (Integer idADUser, String trxName)
	{
		BeanIdentifiant bi =BeanFactory.getBeanIdentifiant();
		if(idADUser!=null) {
			StringBuilder sql = new StringBuilder("SELECT job.").append(MHRJob.COLUMNNAME_Name).append(" AS Poste, job.").append(MHRJob.COLUMNNAME_HR_Job_ID)
					.append(" AS NumPoste, cb.").append(MCBPartner.COLUMNNAME_Name).append(" AS Nom, cb.").append(MCBPartner.COLUMNNAME_C_BPartner_ID).append(" AS NumEmploye, cb.")
					.append(MCBPartner.COLUMNNAME_Value).append(" AS Matricule FROM ")
					.append(MCBPartner.Table_Name).append(" cb LEFT JOIN ").append("AD_User").append(" ad ON ").append("cb.")
					.append(MCBPartner.COLUMNNAME_C_BPartner_ID).append("=").append("ad.").append("C_BPartner_ID")
					.append(" LEFT JOIN ").append(MHREmployeeJob.Table_Name)
					.append(" hjob ON hjob.").append(MHREmployeeJob.COLUMNNAME_C_BPartner_ID).append("=cb.").append(MCBPartner.COLUMNNAME_C_BPartner_ID)
					.append(" AND hjob.").append(MHREmployeeJob.COLUMNNAME_DateFrom).append("=(SELECT MAX(").append(MHREmployeeJob.COLUMNNAME_DateFrom)
					.append(") FROM ").append(MHREmployeeJob.Table_Name).append(" WHERE ").append(MHREmployeeJob.Table_Name).append(".")
					.append(MHREmployeeJob.COLUMNNAME_C_BPartner_ID).append("=cb.").append(MCBPartner.COLUMNNAME_C_BPartner_ID).append(")")
					.append(" LEFT JOIN ").append(MHRJob.Table_Name).append(" job ON job.").append(MHRJob.COLUMNNAME_HR_Job_ID).append("=hjob.")
					.append(MHRJob.COLUMNNAME_HR_Job_ID).append(" WHERE ad.").append("AD_User_ID=?");
			
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
			return resultat;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return resultat;
	}
}
