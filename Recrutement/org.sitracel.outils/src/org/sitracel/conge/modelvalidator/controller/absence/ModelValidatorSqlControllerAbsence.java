package org.sitracel.conge.modelvalidator.controller.absence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.compiere.util.DB;
import org.sitracel.bean.BeanInfoAbsence;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.discipline.model.MHRPunishment;

public class ModelValidatorSqlControllerAbsence {
	public static BeanInfoAbsence getAbsenceNonAutoriseNonTraite(Integer cBpartnerID, String trxName) {
		BeanInfoAbsence resultat = BeanFactory.getBeanInfoAbsence();
		if(cBpartnerID!=null) {
			StringBuilder sql = new StringBuilder("SELECT * FROM "+MHRAbsence.Table_Name
					+" WHERE "+MHRAbsence.COLUMNNAME_C_BPartner_ID+" = ? "
					+" AND "+MHRAbsence.COLUMNNAME_IsDemandeExplication+"=?"
					+" AND "+MHRAbsence.COLUMNNAME_IsDemandeExplicationTraite+"!=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cBpartnerID);
				pstmt.setString(2, "Y");
				pstmt.setString(3, "Y");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat.getListAbsenceID().add(rs.getInt(MHRAbsence.COLUMNNAME_HR_Absence_ID));
					resultat.setDate(resultat.getDate()+"-"
					+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(rs.getTimestamp(MHRAbsence.COLUMNNAME_Date_Absence)));
					resultat.setNombreJour(resultat.getListAbsenceID().size());
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
	
	public static BeanInfoAbsence getAbsenceNonAutoriseFromDemandeExplication(Integer demandeExplicationID, String trxName) {
		BeanInfoAbsence resultat = BeanFactory.getBeanInfoAbsence();
		if(demandeExplicationID!=null) {
			StringBuilder sql = new StringBuilder("SELECT * "
					+ "FROM "+MHRAbsence.Table_Name
					+" WHERE "+MHRAbsence.COLUMNNAME_HR_Demande_Explication_ID+" = ? ");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, demandeExplicationID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat.getListAbsenceID().add(rs.getInt(MHRAbsence.COLUMNNAME_HR_Absence_ID));
					resultat.setDate(resultat.getDate()+"-"
							+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(rs.getTimestamp(MHRAbsence.COLUMNNAME_Date_Absence)));
					resultat.setNombreJour(resultat.getListAbsenceID().size());
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

	public static ArrayList<Integer> getSanctionFromDemandeExplication(Integer demandeExplicationID, String trxName) {
		ArrayList<Integer> resultat = new ArrayList<Integer>();
		if(demandeExplicationID!=null) {
			StringBuilder sql = new StringBuilder("SELECT * "
					+ "FROM "+MHRPunishment.Table_Name
					+" WHERE "+MHRPunishment.COLUMNNAME_Demande_Explication_ID+" = ? ");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, demandeExplicationID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat.add(rs.getInt(MHRPunishment.COLUMNNAME_Demande_Explication_ID));
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
