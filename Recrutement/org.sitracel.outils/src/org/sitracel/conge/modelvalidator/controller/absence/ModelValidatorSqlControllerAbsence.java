package org.sitracel.conge.modelvalidator.controller.absence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.compiere.util.DB;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.bean.BeanInfoAbsence;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.model.MCBPartner;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.model.MHRJob;
import org.sitracel.model.MHRParametreNumerique;

public class ModelValidatorSqlControllerAbsence {
	
	public static BeanIdentifiant getBeanIdentifiant (Integer idCBPartner, String trxName)
	{
		BeanIdentifiant bi =BeanFactory.getBeanIdentifiant();
		if(idCBPartner!=null) {
			StringBuilder sql = new StringBuilder("SELECT job.").append(MHRJob.COLUMNNAME_Name).append(" AS Poste, job.").append(MHRJob.COLUMNNAME_HR_Job_ID)
					.append(" AS NumPoste, cb.").append(MCBPartner.COLUMNNAME_Name).append(" AS Nom, cb.").append(MCBPartner.COLUMNNAME_C_BPartner_ID).append(" AS NumEmploye, cb.")
					.append(MCBPartner.COLUMNNAME_Value).append(" AS Matricule FROM ")
					.append(MCBPartner.Table_Name).append(" cb ")
					.append(" LEFT JOIN ").append(MHREmployeeJob.Table_Name)
					.append(" hjob ON hjob.").append(MHREmployeeJob.COLUMNNAME_C_BPartner_ID).append("=cb.").append(MCBPartner.COLUMNNAME_C_BPartner_ID)
					.append(" AND hjob.").append(MHREmployeeJob.COLUMNNAME_DateFrom).append("=(SELECT MAX(").append(MHREmployeeJob.COLUMNNAME_DateFrom)
					.append(") FROM ").append(MHREmployeeJob.Table_Name).append(" WHERE ").append(MHREmployeeJob.Table_Name).append(".")
					.append(MHREmployeeJob.COLUMNNAME_C_BPartner_ID).append("=cb.").append(MCBPartner.COLUMNNAME_C_BPartner_ID).append(")")
					.append(" LEFT JOIN ").append(MHRJob.Table_Name).append(" job ON job.").append(MHRJob.COLUMNNAME_HR_Job_ID).append("=hjob.")
					.append(MHRJob.COLUMNNAME_HR_Job_ID).append(" WHERE cb."+MCBPartner.COLUMNNAME_C_BPartner_ID).append("=?");
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, idCBPartner);
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
	
	public static BeanInfoAbsence getAbsenceNonAutorise(Integer cBpartnerID, String trxName) {
		BeanInfoAbsence resultat = BeanFactory.getBeanInfoAbsence();
		if(cBpartnerID!=null) {
			String sql = "SELECT * FROM "+MHRAbsence.Table_Name
					+" WHERE "+MHRAbsence.COLUMNNAME_C_BPartner_ID+" = ? "
					+" AND "+MHRAbsence.COLUMNNAME_IsDemandeExplication+"=?"
					+" AND "+MHRAbsence.COLUMNNAME_IsDemandeExplicationTraite+"=?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cBpartnerID);
				pstmt.setString(2, "Y");
				pstmt.setString(3, "N");
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
			String sql = "SELECT * FROM "+MHRAbsence.Table_Name
					+" WHERE "+MHRAbsence.COLUMNNAME_HR_Demande_Explication_ID+" = ? ";
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
			String sql = "SELECT * FROM "+MHRPunishment.Table_Name
					+" WHERE "+MHRPunishment.COLUMNNAME_Demande_Explication_ID+" = ? ";
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

	public static int getParametreFromParametreNumerique(String nomParametre) {
		int resultat = 0;
		if(nomParametre!=null) {
			StringBuilder sql = new StringBuilder("SELECT para."+MHRParametreNumerique.COLUMNNAME_Valeur_Parametre);
			sql.append(" FROM ")
			.append(MHRParametreNumerique.Table_Name+" para")			
			.append(" WHERE para."+MHRParametreNumerique.COLUMNNAME_Name+"=? ");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setString(1, nomParametre);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getInt(MHRParametreNumerique.COLUMNNAME_Valeur_Parametre);
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
