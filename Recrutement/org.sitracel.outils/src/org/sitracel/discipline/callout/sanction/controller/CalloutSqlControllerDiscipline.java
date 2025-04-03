package org.sitracel.discipline.callout.sanction.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.model.MHROrganigramme;

public class CalloutSqlControllerDiscipline {

	public static Integer getAutorisationSanctionID (Integer typeSanction_ID, Integer poste_ID, Integer posteResponsable_ID, String trxName)
	{	
		Integer resultat = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			//Image image = ImageIO.read(new File(sitracelLogoPath));
			if(typeSanction_ID!=null && poste_ID!=null && posteResponsable_ID!=null) {
				String sql = "SELECT HR_Sanction_Autorisation_ID id "
						+ "FROM HR_Sanction_Autorisation sanc WHERE "
						+ "sanc.HR_TypeSanction_ID=? AND "
						+ "sanc.HR_Categorie_Responsabilite_ID="
						+ "(SELECT HR_Categorie_Responsabilite_ID FROM "
						+ "HR_Organigramme org WHERE org.Poste_ID=? AND "
						+ "org.Poste_Responsable_ID=?"
						+ ")";
				
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, typeSanction_ID);
				pstmt.setInt(2, poste_ID);
				pstmt.setInt(3, posteResponsable_ID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = rs.getInt("id");
				}
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();				
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return resultat;
	}
	
	public static MHROrganigramme getOrganigramme (Integer poste_ID, Integer posteResponsable_ID, String trxName)
	{	
		MHROrganigramme resultat = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			//Image image = ImageIO.read(new File(sitracelLogoPath));
			if(poste_ID!=null && posteResponsable_ID!=null) {
				StringBuilder sql = new StringBuilder("SELECT HR_Organigramme_ID id "
						+ "FROM HR_Organigramme org WHERE "
						+ "org.Poste_ID=? AND "
						+ "org.Poste_Responsable_ID=?");
				
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, poste_ID);
				pstmt.setInt(2, posteResponsable_ID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat = new MHROrganigramme(Env.getCtx(), rs.getInt("id"), trxName) ;
				}
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();				
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return resultat;
	}	
}
