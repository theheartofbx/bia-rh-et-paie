package org.sitracel.conge.modelvalidator.controller.conge;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.compiere.util.DB;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;

public class ModelValidatorSqlControllerConge {
	
	public static Integer getHR_Job_ID_FromHREmployeeJob (Integer c_BPartber_id, String trxName)
	{
		Integer resultat = null;
		if(c_BPartber_id!=null) {
			String sql = "SELECT HR_EmployeeJob.HR_Job_ID FROM HR_EmployeeJob WHERE "
					+ "HR_EmployeeJob.C_Bpartner_ID=? AND "
					+ "HR_EmployeeJob.DateFrom=(SELECT MAX(HR_EmployeeJob.DateFrom) "
					+ "FROM HR_EmployeeJob WHERE HR_EmployeeJob.C_Bpartner_ID=?)";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, c_BPartber_id);
				pstmt.setInt(2, c_BPartber_id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = rs.getInt("HR_Job_ID");
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
	

	public static Integer getC_BPartner_ID_FromHREmployeeJob (Integer hr_Job_id, String trxName)
	{
		Integer resultat = null;
		if(hr_Job_id!=null) {
			String sql = "SELECT HR_EmployeeJob.C_Bpartner_ID FROM HR_EmployeeJob WHERE "
					+ "HR_EmployeeJob.HR_Job_ID=? AND "
					+ "HR_EmployeeJob.DateFrom=(SELECT MAX(HR_EmployeeJob.DateFrom) "
					+ "FROM HR_EmployeeJob WHERE HR_EmployeeJob.HR_Job_ID=?)";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, hr_Job_id);
				pstmt.setInt(2, hr_Job_id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = rs.getInt("C_Bpartner_ID");
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
	

	public static Integer getHR_Dossier_Disciplinaire_ID_FromHRDossierDisciplinaire (Integer HR_punishment_ID, String trxName)
	{
		Integer resultat = null;
		if(HR_punishment_ID!=null) {
			String sql = "SELECT "+MHRDossierDisciplinaire.Table_Name+"."+MHRDossierDisciplinaire.COLUMNNAME_HR_Dossier_Disciplinaire_ID
					+" FROM "+MHRDossierDisciplinaire.Table_Name+" WHERE "
					+ MHRDossierDisciplinaire.Table_Name+"."+MHRDossierDisciplinaire.COLUMNNAME_HR_Punishment_ID
					+ "=?";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, HR_punishment_ID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = rs.getInt(MHRDossierDisciplinaire.COLUMNNAME_HR_Dossier_Disciplinaire_ID);
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

	public static ArrayList<String> getMailRH (Integer ad_Role_ID, String trxName)
	{
		ArrayList<String> resultat = new ArrayList<String>();
		String mail;
		if(ad_Role_ID!=null) {
			String sql = "SELECT cb.Email mail FROM C_BPartner cb "
					+ "LEFT JOIN AD_User ad on ad.C_BPartner_ID = cb.C_BPartner_ID "
					+ "LEFT JOIN AD_User_Roles adr on adr.Ad_user_ID=ad.AD_User_ID "
					+ "where adr.Ad_Role_ID=?";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, ad_Role_ID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					mail = rs.getString("mail");
					if(mail!=null) {
						if(!mail.equalsIgnoreCase("") && !mail.equalsIgnoreCase(" ")) {
							resultat.add(mail);
						}
					}
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
}
