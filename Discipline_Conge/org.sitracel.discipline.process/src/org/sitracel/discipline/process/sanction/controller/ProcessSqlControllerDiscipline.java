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
import org.sitracel.conge.model.I_HR_Absence;
import org.sitracel.discipline.model.I_HR_Dossier_Disciplinaire;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;

public class ProcessSqlControllerDiscipline {

	private static CLogger log = CLogger.getCLogger (PO.class);
	public static String sitracelLogoPath ="SitracelLogo.png";


	public static MHRDossierDisciplinaire getDossierDisciplinaire (Integer hr_Punishment_id, String trxName)
	{
		MHRDossierDisciplinaire resultat = null;
		Integer hr_dossier_disciplinaire_id;
		if(hr_Punishment_id!=null) {
			String sql = "SELECT "+I_HR_Dossier_Disciplinaire.COLUMNNAME_HR_Dossier_Disciplinaire_ID+" id FROM "
					+ I_HR_Dossier_Disciplinaire.Table_Name +" WHERE "
					+ I_HR_Dossier_Disciplinaire.COLUMNNAME_HR_Punishment_ID+"=?";

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
			String sql = "SELECT EXISTS (SELECT 1 FROM "+I_HR_Absence.Table_Name+" WHERE "
					+I_HR_Absence.COLUMNNAME_C_BPartner_ID+" = ?"
					+ " AND "+I_HR_Absence.COLUMNNAME_Date_Absence+"=?)";

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
		ArrayList<String> resultat = new ArrayList<>();
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
