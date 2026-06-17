package org.sitracel.conge.modelvalidator.controller.conge;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.discipline.model.I_HR_Dossier_Disciplinaire;
import org.sitracel.paie.model.I_HR_ElementBasePaieEmploye;

public class ModelValidatorSqlControllerConge {

	private static CLogger log = CLogger.get();

	public static Integer getHRJobIdFromLastElement(Integer cBPartnerId, String trxName) {
	    if (cBPartnerId == null) {
			return null;
		}

	    Integer hrJobId = null;

	    String sql = "SELECT " + I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_Job_ID +
	                 " FROM " + I_HR_ElementBasePaieEmploye.Table_Name +
	                 " WHERE " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + " = ? " +
	                 " AND " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + " = (" +
	                 "     SELECT MAX(" + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + ") " +
	                 "     FROM " + I_HR_ElementBasePaieEmploye.Table_Name +
	                 "     WHERE " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + " = ?" +
	                 " )";

	    try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {
	        pstmt.setInt(1, cBPartnerId);
	        pstmt.setInt(2, cBPartnerId);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                hrJobId = rs.getInt(I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_Job_ID);
	            }
	        }
	    } catch (SQLException e) {
	        log.warning(e.getMessage());
	        e.printStackTrace();
	        return null;
	    }

	    return hrJobId;
	}

	public static Integer getC_BPartnerIdFromLastElement(Integer hrJobId, String trxName) {
	    if (hrJobId == null) {
			return null;
		}

	    Integer cBPartnerId = null;

	    String sql = "SELECT " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID +
	                 " FROM " + I_HR_ElementBasePaieEmploye.Table_Name +
	                 " WHERE " + I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_Job_ID + " = ? " +
	                 " AND " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + " = (" +
	                 "     SELECT MAX(" + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + ") " +
	                 "     FROM " + I_HR_ElementBasePaieEmploye.Table_Name +
	                 "     WHERE " + I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_Job_ID + " = ?" +
	                 " )";

	    try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {
	        pstmt.setInt(1, hrJobId);
	        pstmt.setInt(2, hrJobId);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                cBPartnerId = rs.getInt(I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID);
	            }
	        }
	    } catch (SQLException e) {
	        log.warning(e.getMessage());
	        e.printStackTrace();
	        return null;
	    }

	    return cBPartnerId;
	}


	public static Integer getHR_Dossier_Disciplinaire_ID_FromHRDossierDisciplinaire (Integer HR_punishment_ID, String trxName)
	{
		Integer resultat = null;
		if(HR_punishment_ID!=null) {
			StringBuilder sql = new StringBuilder("SELECT "+I_HR_Dossier_Disciplinaire.Table_Name+"."+I_HR_Dossier_Disciplinaire.COLUMNNAME_HR_Dossier_Disciplinaire_ID
					+" FROM "+I_HR_Dossier_Disciplinaire.Table_Name
					+" WHERE "+ I_HR_Dossier_Disciplinaire.Table_Name+"."+I_HR_Dossier_Disciplinaire.COLUMNNAME_HR_Punishment_ID+ "=?");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, HR_punishment_ID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = rs.getInt(I_HR_Dossier_Disciplinaire.COLUMNNAME_HR_Dossier_Disciplinaire_ID);
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
		ArrayList<String> resultat = new ArrayList<>();
		String mail;
		if(ad_Role_ID!=null) {
			StringBuilder sql = new StringBuilder("SELECT cb.Email mail FROM C_BPartner cb "
					+ "LEFT JOIN AD_User ad on ad.C_BPartner_ID = cb.C_BPartner_ID "
					+ "LEFT JOIN AD_User_Roles adr on adr.Ad_user_ID=ad.AD_User_ID "
					+ "where adr.Ad_Role_ID=?");

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
