package org.sitracel.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.sitracel.bean.BeanIdentifiant;
// COMMENTÉ — attend découplage paie: import org.sitracel.paie.model.I_HR_ElementBasePaieEmploye;

public class MCBPartner extends X_C_BPartner{
	private static final long serialVersionUID = 1610077844542696959L;

	public MCBPartner(Properties ctx, int C_BPartner_ID, String trxName) {
		super(ctx, C_BPartner_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCBPartner(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Récupère les informations d'identification d'un employé à partir de son utilisateur AD_User.
	 * On va chercher : nom, matricule, poste, numéro poste, identifiant employé.
	 */
	public static BeanIdentifiant getIdentifiant(int adUserId, String trxName) {

	    if (adUserId <= 0) {
	        return null;
	    }

	    String sql =
	        "SELECT " +
	        " cb." + I_C_BPartner.COLUMNNAME_Name + " AS Nom, " +
	        " cb." + I_C_BPartner.COLUMNNAME_Name2 + " AS Prenom, " +
	        " cb." + I_C_BPartner.COLUMNNAME_Sex + " AS Sexe, " +
	        " cb." + I_C_BPartner.COLUMNNAME_C_BPartner_ID + " AS NumEmploye, " +
	        " cb." + I_C_BPartner.COLUMNNAME_Value + " AS Matricule, " +
	        " job." + I_HR_Job.COLUMNNAME_Name + " AS Poste, " +
	        " job." + I_HR_Job.COLUMNNAME_HR_Job_ID + " AS NumPoste " +
	        "FROM AD_User ad " +
	        "INNER JOIN " + I_C_BPartner.Table_Name + " cb " +
	        "        ON cb." + I_C_BPartner.COLUMNNAME_C_BPartner_ID + " = ad.C_BPartner_ID " +
	        "LEFT JOIN " + "HR_ElementBasePaieEmploye" + " el " +
	        "       ON el." + "C_BPartner_ID" + " = cb." + I_C_BPartner.COLUMNNAME_C_BPartner_ID +
	        "      AND el.IsActive='Y' " +
	        "      AND el." + "Date_Debut" + " = ( " +
	        "          SELECT MAX(e2." + "Date_Debut" + ") " +
	        "          FROM " + "HR_ElementBasePaieEmploye" + " e2 " +
	        "          WHERE e2." + "C_BPartner_ID" + " = cb." + I_C_BPartner.COLUMNNAME_C_BPartner_ID +
	        "          AND e2.IsActive='Y' ) " +
	        "LEFT JOIN " + I_HR_Job.Table_Name + " job " +
	        "       ON job." + I_HR_Job.COLUMNNAME_HR_Job_ID + " = el." + "HR_Job_ID" +
	        "      AND job.IsActive='Y' " +
	        "WHERE ad.AD_User_ID = ?";

	    try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {

	        pstmt.setInt(1, adUserId);

	        try (ResultSet rs = pstmt.executeQuery()) {

	        	if (!rs.next()) {
	                return null;
	            }

	            BeanIdentifiant bean = new BeanIdentifiant();

	            String nomComplet = buildNomComplet(
	                rs.getString("Sexe"),
	                rs.getString("Nom"),
	                rs.getString("Prenom")
	            );

	            bean.setNomEmploye(nomComplet);
	            bean.setNumEmploye(rs.getInt("NumEmploye"));
	            bean.setMatriculeEmploye(rs.getString("Matricule"));
	            bean.setNomPoste(rs.getString("Poste"));
	            bean.setNumeroPoste(rs.getInt("NumPoste"));

	            return bean;
	        }

	    } catch (SQLException e) {
	        throw new AdempiereException(
	            "Erreur lors de la récupération de l'identité utilisateur (AD_User_ID=" + adUserId + ")",
	            e
	        );
	    }
	}
	
	/**
	 * Récupère les informations d'identification d'un employé
	 * à partir de son C_BPartner_ID (employé RH).
	 *
	 * Infos retournées :
	 * - Nom
	 * - Numéro employé
	 * - Matricule
	 * - Poste
	 * - Numéro poste
	 */
	public static BeanIdentifiant getIdentifiantByBPartner(
	        int cBPartnerId,
	        String trxName
	) {

	    if (cBPartnerId <= 0) {
	        return null;
	    }

	    String sql =
	        "SELECT " +
	        " cb." + I_C_BPartner.COLUMNNAME_Name + " AS Nom, " +
	        " cb." + I_C_BPartner.COLUMNNAME_Name2 + " AS Prenom, " +
	        " cb." + I_C_BPartner.COLUMNNAME_Sex + " AS Sexe, " +
	        " cb." + I_C_BPartner.COLUMNNAME_C_BPartner_ID + " AS NumEmploye, " +
	        " cb." + I_C_BPartner.COLUMNNAME_Value + " AS Matricule, " +
	        " job." + I_HR_Job.COLUMNNAME_Name + " AS Poste, " +
	        " job." + I_HR_Job.COLUMNNAME_HR_Job_ID + " AS NumPoste " +
	        "FROM " + I_C_BPartner.Table_Name + " cb " +
	        "LEFT JOIN " + "HR_ElementBasePaieEmploye" + " el " +
	        "       ON el." + "C_BPartner_ID" +
	        "        = cb." + I_C_BPartner.COLUMNNAME_C_BPartner_ID +
	        "      AND el.IsActive='Y' " +
	        "      AND el." + "Date_Debut" + " = ( " +
	        "          SELECT MAX(e2." + "Date_Debut" + ") " +
	        "          FROM " + "HR_ElementBasePaieEmploye" + " e2 " +
	        "          WHERE e2." + "C_BPartner_ID" +
	        "                = cb." + I_C_BPartner.COLUMNNAME_C_BPartner_ID +
	        "          AND e2.IsActive='Y' ) " +
	        "LEFT JOIN " + I_HR_Job.Table_Name + " job " +
	        "       ON job." + I_HR_Job.COLUMNNAME_HR_Job_ID +
	        "        = el." + "HR_Job_ID" +
	        "      AND job.IsActive='Y' " +
	        "WHERE cb." + I_C_BPartner.COLUMNNAME_C_BPartner_ID + " = ?";

	    try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {

	        pstmt.setInt(1, cBPartnerId);

	        try (ResultSet rs = pstmt.executeQuery()) {

	        	if (!rs.next()) {
	                return null;
	            }

	            BeanIdentifiant bean = new BeanIdentifiant();

	            String nomComplet = buildNomComplet(
	                rs.getString("Sexe"),
	                rs.getString("Nom"),
	                rs.getString("Prenom")
	            );

	            bean.setNomEmploye(nomComplet);
	            bean.setNumEmploye(rs.getInt("NumEmploye"));
	            bean.setMatriculeEmploye(rs.getString("Matricule"));
	            bean.setNomPoste(rs.getString("Poste"));
	            bean.setNumeroPoste(rs.getInt("NumPoste"));

	            return bean;
	        }

	    } catch (SQLException e) {
	        throw new AdempiereException(
	            "Erreur récupération identité employé (C_BPartner_ID=" + cBPartnerId + ")",
	            e
	        );
	    }
	}
	
	private static String buildNomComplet(
	        String sexe,
	        String nom,
	        String prenom
	) {
	    String civilite = "";

	    if ("M".equalsIgnoreCase(sexe)) {
	        civilite = "M.";
	    } else if ("F".equalsIgnoreCase(sexe)) {
	        civilite = "Mme";
	    }

	    StringBuilder sb = new StringBuilder();

	    if (!civilite.isEmpty()) {
	        sb.append(civilite).append(" ");
	    }

	    if (nom != null && !nom.trim().isEmpty()) {
	        sb.append(nom.trim());
	    }

	    if (prenom != null && !prenom.trim().isEmpty()) {
	        sb.append(" ").append(prenom.trim());
	    }

	    return sb.toString().trim();
	}


}
