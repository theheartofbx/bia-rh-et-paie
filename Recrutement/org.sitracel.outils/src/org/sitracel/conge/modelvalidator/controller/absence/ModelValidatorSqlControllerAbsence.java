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
import org.sitracel.conge.model.I_HR_Absence;
import org.sitracel.discipline.model.I_HR_Punishment;

public class ModelValidatorSqlControllerAbsence {
	public static BeanInfoAbsence getAbsenceNonAutoriseNonTraite(Integer cBpartnerID, String trxName) {
		BeanInfoAbsence resultat = BeanFactory.getBeanInfoAbsence();
		if(cBpartnerID!=null) {
			StringBuilder sql = new StringBuilder("SELECT * FROM "+I_HR_Absence.Table_Name
					+" WHERE "+I_HR_Absence.COLUMNNAME_C_BPartner_ID+" = ? "
					+" AND "+I_HR_Absence.COLUMNNAME_IsDemandeExplication+"=?"
					+" AND "+I_HR_Absence.COLUMNNAME_IsDemandeExplicationTraite+"!=?");
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
					resultat.getListAbsenceID().add(rs.getInt(I_HR_Absence.COLUMNNAME_HR_Absence_ID));
					resultat.setDate(resultat.getDate()+"-"
					+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(rs.getTimestamp(I_HR_Absence.COLUMNNAME_Date_Absence)));
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
					+ "FROM "+I_HR_Absence.Table_Name
					+" WHERE "+I_HR_Absence.COLUMNNAME_HR_Demande_Explication_ID+" = ? ");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, demandeExplicationID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat.getListAbsenceID().add(rs.getInt(I_HR_Absence.COLUMNNAME_HR_Absence_ID));
					resultat.setDate(resultat.getDate()+"-"
							+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(rs.getTimestamp(I_HR_Absence.COLUMNNAME_Date_Absence)));
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
		ArrayList<Integer> resultat = new ArrayList<>();
		if(demandeExplicationID!=null) {
			StringBuilder sql = new StringBuilder("SELECT * "
					+ "FROM "+I_HR_Punishment.Table_Name
					+" WHERE "+I_HR_Punishment.COLUMNNAME_Demande_Explication_ID+" = ? ");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, demandeExplicationID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat.add(rs.getInt(I_HR_Punishment.COLUMNNAME_Demande_Explication_ID));
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
