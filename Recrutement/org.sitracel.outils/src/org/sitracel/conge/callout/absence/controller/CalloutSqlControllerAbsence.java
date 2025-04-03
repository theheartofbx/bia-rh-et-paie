package org.sitracel.conge.callout.absence.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRTypeAbsence;

public class CalloutSqlControllerAbsence {
	public static CLogger log = CLogger.getCLogger(PO.class);
	public static boolean isAbsenceExist(Timestamp date, Integer cBpartnerID, String trxName) {
		boolean resultat = true;
		if(date!=null && cBpartnerID!=null) {
			StringBuilder sql = new StringBuilder("SELECT * FROM "+MHRAbsence.Table_Name
					+" WHERE "+MHRAbsence.COLUMNNAME_C_BPartner_ID+" = ? "
					+" AND "+MHRAbsence.COLUMNNAME_Date_Absence+"=?");
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
		ArrayList<Integer> resultat = new ArrayList<Integer>();
		if(cBpartnerID!=null && nomTypeAbsence!=null && dateDebut!=null && dateFin!=null) {
			StringBuilder sql = new StringBuilder("SELECT "+MHRAbsence.COLUMNNAME_HR_Absence_ID
					+ " FROM "+MHRAbsence.Table_Name+" absence"
					+ " LEFT JOIN "+MHRTypeAbsence.Table_Name+" typeabsence"
					+ " ON typeabsence."+MHRTypeAbsence.COLUMNNAME_HR_Type_Absence_ID+"=absence."+MHRAbsence.COLUMNNAME_HR_Type_Absence_ID
					+ " WHERE absence."+MHRAbsence.COLUMNNAME_C_BPartner_ID+" = ? "
					+ " AND absence."+MHRAbsence.COLUMNNAME_Date_Absence+" BETWEEN ? AND ?"
					+ " AND typeabsence."+MHRTypeAbsence.COLUMNNAME_Nom_Absence+"=?");
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
					log.warning("\nSQLABSENCE : --> "+rs.getInt(MHRAbsence.COLUMNNAME_HR_Absence_ID));
					resultat.add(rs.getInt(MHRAbsence.COLUMNNAME_HR_Absence_ID));
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
