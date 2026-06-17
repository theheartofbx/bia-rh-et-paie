package org.sitracel.conge.callout.absence.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.conge.model.I_HR_Absence;
import org.sitracel.conge.model.I_HR_Type_Absence;

public class CalloutSqlControllerAbsence {
	public static CLogger log = CLogger.getCLogger(PO.class);
	public static boolean isAbsenceExist(Timestamp date, Integer cBpartnerID, String trxName) {
		boolean resultat = true;
		if(date!=null && cBpartnerID!=null) {
			StringBuilder sql = new StringBuilder("SELECT * FROM "+I_HR_Absence.Table_Name
					+" WHERE "+I_HR_Absence.COLUMNNAME_C_BPartner_ID+" = ? "
					+" AND "+I_HR_Absence.COLUMNNAME_Date_Absence+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cBpartnerID);
				pstmt.setTimestamp(2, date);
				rs = pstmt.executeQuery();
				resultat = false;
				if (rs.next()) {
					resultat=true;
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

	public static ArrayList<Integer> getListeAbsenceIDByName(Integer cBpartnerID, String nomTypeAbsence, Timestamp dateDebut , Timestamp dateFin,  String trxName) {
		ArrayList<Integer> resultat = new ArrayList<>();
		if(cBpartnerID!=null && nomTypeAbsence!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT "+I_HR_Absence.COLUMNNAME_HR_Absence_ID
					+ " FROM "+I_HR_Absence.Table_Name+" absence"
					+ " LEFT JOIN "+I_HR_Type_Absence.Table_Name+" typeabsence"
					+ " ON typeabsence."+I_HR_Type_Absence.COLUMNNAME_HR_Type_Absence_ID+"=absence."+I_HR_Absence.COLUMNNAME_HR_Type_Absence_ID
					+ " WHERE absence."+I_HR_Absence.COLUMNNAME_C_BPartner_ID+" = ? "
					+ " AND absence."+I_HR_Absence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?"
					+ " AND typeabsence."+I_HR_Type_Absence.COLUMNNAME_Nom_Absence+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			log.warning("\nSQLABSENCE : --> "+sql);
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cBpartnerID);
				pstmt.setTimestamp(2, dateDebut);
				pstmt.setTimestamp(3, dateFin);
				pstmt.setString(4, nomTypeAbsence);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					log.warning("\nSQLABSENCE : --> "+rs.getInt(I_HR_Absence.COLUMNNAME_HR_Absence_ID));
					resultat.add(rs.getInt(I_HR_Absence.COLUMNNAME_HR_Absence_ID));
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
