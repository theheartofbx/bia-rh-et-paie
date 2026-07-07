package org.sitracel.organigramme.modelvalidator.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.organigramme.model.I_HR_Organigramme;

/**
 * Accès SQL pour les contrôles de cohérence de l'organigramme.
 * Aucune décision métier ici — uniquement de la lecture en base.
 */
public class OrganigrammeValidatorRepository {

    private static final CLogger log = CLogger.getCLogger(OrganigrammeValidatorRepository.class);

    private OrganigrammeValidatorRepository() {}

    /**
     * Vérifie si le triplet (Poste, Poste_Responsable, Catégorie) existe
     * déjà en actif, en excluant la ligne en cours de modification.
     */
    public static boolean existeDoublon(int posteId, int posteResponsableId, int categorieId,
                                         int excludeId, String trxName) {

        String sql = "SELECT 1 FROM " + I_HR_Organigramme.Table_Name
            + " WHERE " + I_HR_Organigramme.COLUMNNAME_Poste_ID + " = ?"
            + " AND " + I_HR_Organigramme.COLUMNNAME_Poste_Responsable_ID + " = ?"
            + " AND " + I_HR_Organigramme.COLUMNNAME_HR_Categorie_Responsabilite_ID + " = ?"
            + " AND " + I_HR_Organigramme.COLUMNNAME_HR_Organigramme_ID + " <> ?"
            + " AND IsActive = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, posteId);
            pstmt.setInt(2, posteResponsableId);
            pstmt.setInt(3, categorieId);
            pstmt.setInt(4, excludeId);
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException erreurCatch) {
            log.warning("existeDoublon : " + erreurCatch.getMessage());
            return false;
        } finally {
            DB.close(rs, pstmt);
        }
    }

    /**
     * Retourne tous les postes responsables actifs d'un poste donné,
     * en excluant une ligne précise (la ligne en cours de modification,
     * pour ne pas se baser sur sa valeur pas encore enregistrée).
     */
    public static List<Integer> getPostesResponsables(int posteId, int excludeId, String trxName) {
        List<Integer> resultat = new ArrayList<>();

        String sql = "SELECT " + I_HR_Organigramme.COLUMNNAME_Poste_Responsable_ID
            + " FROM " + I_HR_Organigramme.Table_Name
            + " WHERE " + I_HR_Organigramme.COLUMNNAME_Poste_ID + " = ?"
            + " AND " + I_HR_Organigramme.COLUMNNAME_HR_Organigramme_ID + " <> ?"
            + " AND IsActive = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, posteId);
            pstmt.setInt(2, excludeId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(rs.getInt(1));
            }
        } catch (SQLException erreurCatch) {
            log.warning("getPostesResponsables : " + erreurCatch.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }
}
