package org.sitracel.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.sitracel.bean.BeanIdentifiant;

/**
 * Corrigé cette session : getIdentifiant()/getIdentifiantByBPartner()
 * s'appuyaient sur HR_ElementBasePaieEmploye (ancienne table de paie,
 * jamais migrée) pour retrouver le poste d'un employé — au lieu de
 * HR_Affectation. Resté invisible tant que les employés testés avaient
 * une ligne historique dans cette ancienne table ; révélé en créant des
 * employés de test propres (uniquement dans HR_Contrat/HR_Affectation),
 * qui se retrouvaient avec un poste introuvable (champs "Poste de
 * l'Employé"/"Poste Emetteur" vides malgré leur caractère obligatoire,
 * bloquant tout l'enregistrement dans Absence/Congé/Discipline).
 *
 * Cette méthode est utilisée par TOUT le projet (Congé, Discipline,
 * Mission, Recrutement) via HREmployeService.getIdentifiant(), qui
 * délègue directement ici — donc ce bug affectait potentiellement
 * chaque garde-fou d'habilitation et chaque notification de la session.
 */
public class MCBPartner extends X_C_BPartner {

private static final long serialVersionUID = 1610077844542696959L;

public MCBPartner(Properties ctx, int C_BPartner_ID, String trxName) {
super(ctx, C_BPartner_ID, trxName);
}

public MCBPartner(Properties ctx, ResultSet rs, String trxName) {
super(ctx, rs, trxName);
}

/**
 * Récupère les informations d'identification d'un employé à partir
 * de son utilisateur AD_User (poste retrouvé via son affectation
 * active, pas via un historique de paie).
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
        "LEFT JOIN HR_Affectation a " +
        "       ON a.C_BPartner_ID = cb." + I_C_BPartner.COLUMNNAME_C_BPartner_ID +
        "      AND a.IsActive='Y' " +
        "      AND a.Date_Debut <= now() " +
        "      AND (a.Date_Fin IS NULL OR a.Date_Fin >= now()) " +
        "LEFT JOIN " + I_HR_Job.Table_Name + " job " +
        "       ON job." + I_HR_Job.COLUMNNAME_HR_Job_ID + " = a.HR_Job_ID " +
        "      AND job.IsActive='Y' " +
        "WHERE ad.AD_User_ID = ? " +
        "ORDER BY a.Date_Debut DESC " +
        "";


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
 * à partir de son C_BPartner_ID (poste retrouvé via son affectation
 * active, pas via un historique de paie).
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
        "LEFT JOIN HR_Affectation a " +
        "       ON a.C_BPartner_ID = cb." + I_C_BPartner.COLUMNNAME_C_BPartner_ID +
        "      AND a.IsActive='Y' " +
        "      AND a.Date_Debut <= now() " +
        "      AND (a.Date_Fin IS NULL OR a.Date_Fin >= now()) " +
        "LEFT JOIN " + I_HR_Job.Table_Name + " job " +
        "       ON job." + I_HR_Job.COLUMNNAME_HR_Job_ID + " = a.HR_Job_ID " +
        "      AND job.IsActive='Y' " +
        "WHERE cb." + I_C_BPartner.COLUMNNAME_C_BPartner_ID + " = ? " +
        "ORDER BY a.Date_Debut DESC " +
        "";


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
