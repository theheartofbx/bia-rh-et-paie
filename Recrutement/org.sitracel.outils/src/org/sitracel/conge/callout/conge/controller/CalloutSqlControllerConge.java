package org.sitracel.conge.callout.conge.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.bean.BeanResumeAbsence;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.I_HR_Absence;
import org.sitracel.conge.model.I_HR_Holiday;
import org.sitracel.controller.GeneralController;
import org.sitracel.model.I_HR_Job;
import org.sitracel.paie.model.I_HR_ElementBasePaieEmploye;

public class CalloutSqlControllerConge {
	private static CLogger	log = CLogger.getCLogger (PO.class);

	public static BeanPeriode[] getAllCongesDepFromCBPartnerID(
	        Timestamp dateDebut, Timestamp dateFin, Integer cbPartnerId, String trxName) {

	    List<BeanPeriode> periodes = new ArrayList<>();

	    if (dateDebut != null && dateFin != null && cbPartnerId != null) {

	        Timestamp firstDayOfYear = GeneralController.getFirstDayOfThisYear();
	        Timestamp lastDayOfYear = GeneralController.getLastDayOfThisYear();

	        if (dateDebut.before(firstDayOfYear)) {
	            dateDebut = firstDayOfYear;
	        }
	        if (dateFin.after(lastDayOfYear)) {
	            dateFin = lastDayOfYear;
	        }

	        String sql =
	            "WITH DernierPosteEmploye AS ( " +
	            "    SELECT ebe." + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + ", ebe." +
	                     I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_Job_ID + " " +
	            "    FROM " + I_HR_ElementBasePaieEmploye.Table_Name + " ebe " +
	            "    WHERE ebe." + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + " = ? " +
	            "    ORDER BY ebe." + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + " DESC " +
	            "    LIMIT 1 " +
	            "), " +
	            "DepartementEmploye AS ( " +
	            "    SELECT job." + I_HR_Job.COLUMNNAME_HR_Department_ID + " " +
	            "    FROM DernierPosteEmploye dp " +
	            "    JOIN " + I_HR_Job.Table_Name + " job " +
	            "      ON job." + I_HR_Job.COLUMNNAME_HR_Job_ID + " = dp." + I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_Job_ID +
	            "), " +
	            "DernierPosteCollegues AS ( " +
	            "    SELECT ebe." + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + ", ebe." +
	                          I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_Job_ID + " " +
	            "    FROM " + I_HR_ElementBasePaieEmploye.Table_Name + " ebe " +
	            "    JOIN ( " +
	            "        SELECT " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + ", " +
	            "               MAX(" + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + ") AS DateMax " +
	            "        FROM " + I_HR_ElementBasePaieEmploye.Table_Name + " " +
	            "        GROUP BY " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID +
	            "    ) x ON x." + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID +
	                    " = ebe." + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID +
	                    " AND x.DateMax = ebe." + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut +
	            "    JOIN " + I_HR_Job.Table_Name + " job " +
	            "      ON job." + I_HR_Job.COLUMNNAME_HR_Job_ID + " = ebe." + I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_Job_ID +
	            "    WHERE job." + I_HR_Job.COLUMNNAME_HR_Department_ID +
	                  " = (SELECT HR_Department_ID FROM DepartementEmploye) " +
	            ") " +
	            "SELECT hol." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective + ", " +
	                   "hol." + I_HR_Holiday.COLUMNNAME_Date_Fin_Effective + " " +
	            "FROM " + I_HR_Holiday.Table_Name + " hol " +
	            "WHERE (hol." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective + ", hol." +
	                       I_HR_Holiday.COLUMNNAME_Date_Fin_Effective + ") OVERLAPS (?, ?) " +
	            "  AND hol." + I_HR_Holiday.COLUMNNAME_IsValidee + " = 'Y' " +
	            "  AND hol." + I_HR_Holiday.COLUMNNAME_C_BPartner_ID +
	                  " IN (SELECT " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + " FROM DernierPosteCollegues)";

	        try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {

	            // Paramètres : ID employé pour CTE + dateDebut et dateFin pour OVERLAPS
	            pstmt.setInt(1, cbPartnerId);
	            pstmt.setTimestamp(2, dateDebut);
	            pstmt.setTimestamp(3, dateFin);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                while (rs.next()) {
	                    BeanPeriode bp = BeanFactory.getBeanPeriode();
	                    bp.setDateDebutConge(rs.getTimestamp(I_HR_Holiday.COLUMNNAME_Date_Debut_Effective));
	                    bp.setDateFinConge(rs.getTimestamp(I_HR_Holiday.COLUMNNAME_Date_Fin_Effective));
	                    periodes.add(bp);
	                }
	            }

	        } catch (SQLException e) {
	            log.warning(e.getMessage());
	            return null;
	        }
	    }

	    return periodes.toArray(new BeanPeriode[0]);
	}

	public static BeanResumeAbsence getResumeAbsenceConge (Integer cBpartnerID, Timestamp dateFrom, Timestamp dateTo,String trxName)
	{
		String date = "";
		int nombre = 0;
		BeanResumeAbsence resultat = null;
		if(cBpartnerID!=null && dateFrom!=null && dateTo!=null) {
			resultat = BeanFactory.getBeanResumeAbsence();
			resultat.setListAbsenceID(new ArrayList<>());
			StringBuilder sql = new StringBuilder("SELECT "+I_HR_Absence.COLUMNNAME_HR_Absence_ID
					+", "+I_HR_Absence.COLUMNNAME_Date_Absence
					+" FROM "+I_HR_Absence.Table_Name
					+" WHERE "+I_HR_Absence.COLUMNNAME_C_BPartner_ID+"=?"
					+" AND "+I_HR_Absence.COLUMNNAME_IsConge+"=?"
					+" AND "+I_HR_Absence.COLUMNNAME_IsCongeTraite+"!=?"
					+" AND "+I_HR_Absence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?");
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
						date = date + ""+ new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(rs.getTimestamp(I_HR_Absence.COLUMNNAME_Date_Absence));
						first= false;
					}
					else {
						date = date + ", "+ new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(rs.getTimestamp(I_HR_Absence.COLUMNNAME_Date_Absence));
					}
					resultat.getListAbsenceID().add(rs.getInt(I_HR_Absence.COLUMNNAME_HR_Absence_ID));
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

	public static Integer getNumberEmployeDepartment(Integer cbPartnerId) {
	    if (cbPartnerId == null) {
	        return 0;
	    }

	    Integer resultat = 0;

	    String sql =
	        "WITH DernierPoste AS ( " +
	        "    SELECT " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + ", " +
	        "           " + I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_Job_ID + " " +
	        "    FROM " + I_HR_ElementBasePaieEmploye.Table_Name + " " +
	        "    WHERE (" + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + ", " +
	        "           " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + ") IN ( " +
	        "        SELECT " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + ", " +
	        "               MAX(" + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + ") " +
	        "        FROM " + I_HR_ElementBasePaieEmploye.Table_Name + " " +
	        "        GROUP BY " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + " " +
	        "    ) " +
	        "), " +
	        "DepartementCible AS ( " +
	        "    SELECT job." + I_HR_Job.COLUMNNAME_HR_Department_ID + " AS department_id " +
	        "    FROM DernierPoste dp " +
	        "    JOIN " + I_HR_Job.Table_Name + " job " +
	        "        ON job." + I_HR_Job.COLUMNNAME_HR_Job_ID + " = dp." + I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_Job_ID + " " +
	        "    WHERE dp." + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + " = ? " +
	        ") " +
	        "SELECT COUNT(dp." + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + ") AS count " +
	        "FROM DernierPoste dp " +
	        "JOIN " + I_HR_Job.Table_Name + " job " +
	        "    ON job." + I_HR_Job.COLUMNNAME_HR_Job_ID + " = dp." + I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_Job_ID + " " +
	        "JOIN DepartementCible dc " +
	        "    ON dc.department_id = job." + I_HR_Job.COLUMNNAME_HR_Department_ID;

	    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
	        pstmt.setInt(1, cbPartnerId);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                resultat = rs.getInt("count");
	            }
	        }
	    } catch (SQLException e) {
	        log.warning(e.getMessage());
	        e.printStackTrace();
	        return null;
	    }

	    return resultat;
	}


	public static int getNombreJourAbsencesConge (Timestamp date, String trxName)
	{
		Timestamp firstDayOfYear = GeneralController.getFirstDayOfThisYear();
		Timestamp lastDayOfYear = GeneralController.getLastDayOfThisYear();
		int resultat = 0;
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS count FROM "
				+" "+I_HR_Absence.Table_Name
				+" WHERE "+I_HR_Absence.COLUMNNAME_IsConge+"=?"
				+" AND "+I_HR_Absence.COLUMNNAME_IsCongeTraite+"=?"
				+" AND "+I_HR_Absence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?");
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
