package org.sitracel.discipline.process.sanction.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;
import org.sitracel.model.MCBPartner;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.model.MHRJob;

public class ProcessSqlControllerDiscipline {

	private static CLogger log = CLogger.getCLogger (PO.class);
	public static String sitracelLogoPath ="SitracelLogo.png";
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
				e.printStackTrace();
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return bi;
	}
	
	public static MHRDossierDisciplinaire getDossierDisciplinaire (Integer hr_Punishment_id, String trxName)
	{
		MHRDossierDisciplinaire resultat = null;
		Integer hr_dossier_disciplinaire_id;
		if(hr_Punishment_id!=null) {
			String sql = "SELECT "+MHRDossierDisciplinaire.COLUMNNAME_HR_Dossier_Disciplinaire_ID+" id FROM "
					+ MHRDossierDisciplinaire.Table_Name +" WHERE "
					+ MHRDossierDisciplinaire.COLUMNNAME_HR_Punishment_ID+"=?";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, hr_Punishment_id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					hr_dossier_disciplinaire_id = rs.getInt("id");
					if(hr_dossier_disciplinaire_id!=null) {
						resultat = new MHRDossierDisciplinaire(Env.getCtx(), hr_dossier_disciplinaire_id, trxName);
					}
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}
	
	public static Boolean isAbsenceExist (Integer cBPArtnerID, Timestamp dateAbsence, String trxName)
	{
		Boolean resultat = false;
		if(cBPArtnerID!=null && dateAbsence!=null) {
			String sql = "SELECT EXISTS (SELECT 1 FROM "+MHRAbsence.Table_Name+" WHERE "
					+MHRAbsence.COLUMNNAME_C_BPartner_ID+" = ?"
					+ " AND "+MHRAbsence.COLUMNNAME_Date_Absence+"=?)";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cBPArtnerID);
				pstmt.setTimestamp(2, dateAbsence);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					resultat = rs.getBoolean(1);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
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
					+ "where adr.AD_Role_ID=?";
			
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
				e.printStackTrace();
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static String getLogo (Integer AD_Role_ID, String trxName)
	{	
		String resultat = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			//Image image = ImageIO.read(new File(sitracelLogoPath));
			if(AD_Role_ID!=null) {
				String sql = "SELECT img.binarydata as logo FROM AD_Image img "
						+ "LEFT JOIN AD_Role role ON role.AD_Role_ID=?"
						+ "LEFT JOIN AD_Client client ON client.AD_Client_ID=role.AD_Client_ID "
						+ "LEFT JOIN AD_ClientInfo clientinfo ON client.AD_Client_ID=clientinfo.AD_Client_ID "
						+ "WHERE clientinfo.Logo_ID=img.AD_Image_ID";
				
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, AD_Role_ID);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					InputStream inputStream =rs.getBinaryStream("logo");
					Path currRelativePath = Paths.get("");
			        String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();
					resultat = currAbsolutePathString+"/"+sitracelLogoPath;
					FileOutputStream fileOutputStream = new FileOutputStream(resultat);
					int k;
					while ((k=inputStream.read())!=-1) {
						fileOutputStream.write(k);
					}
					fileOutputStream.close();
				}
			}
			
		}
		catch (Exception e)
		{
			log.warning("\nEXCEPTION : -- : "+e.getMessage());
			e.printStackTrace();				
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return resultat;
	}
}
