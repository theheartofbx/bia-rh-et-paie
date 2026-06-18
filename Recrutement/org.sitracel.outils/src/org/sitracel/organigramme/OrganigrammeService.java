package org.sitracel.organigramme;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.controller.GeneralSqlController;

/**
 * Service central de gestion de l'organigramme.
 *
 * Répond à la question : "Pour cet employé, sur ce type de document,
 * qui peut effectuer quelle action ?"
 *
 * Architecture :
 *   1. On récupère le poste courant de l'employé
 *   2. On remonte la hiérarchie via HR_Organigramme
 *      (Poste_ID → Poste_Responsable_ID + Categorie)
 *   3. Pour chaque catégorie trouvée, on vérifie dans la table
 *      d'autorisation du module si elle a le droit d'effectuer l'action
 *   4. On retourne les BPartner IDs des employés habilités
 *
 * Usage :
 *   // Qui peut approuver le congé annuel de cet employé ?
 *   List<Integer> approbateurs = OrganigrammeService.getActeurs(
 *       employeId, typeCongeId, ModuleAutorisation.CONGE, ActionOrganigramme.APPROBATION
 *   );
 *
 *   // Qui peut valider cette mise à pied ?
 *   List<Integer> validateurs = OrganigrammeService.getActeurs(
 *       employeId, typeSanctionId, ModuleAutorisation.SANCTION, ActionOrganigramme.VALIDATION
 *   );
 */
public final class OrganigrammeService {

    private static final CLogger log =
        CLogger.getCLogger(OrganigrammeService.class);

    private OrganigrammeService() {}

    // =========================================================================
    // API PRINCIPALE
    // =========================================================================

    /**
     * Retourne les IDs (C_BPartner_ID) de tous les employés habilités
     * à effectuer une action sur un type de document pour un employé donné.
     *
     * @param employeId   C_BPartner_ID de l'employé concerné
     * @param typeId      ID du type de document (TypeConge, TypeSanction, TypeAbsence)
     * @param module      Module concerné (CONGE, SANCTION, ABSENCE)
     * @param action      Action recherchée (EMISSION, APPROBATION, VALIDATION, COMPENSATION)
     * @param trxName     Nom de la transaction
     * @return Liste des C_BPartner_ID habilités, vide si aucun
     */
    public static List<Integer> getActeurs(
            int employeId,
            int typeId,
            ModuleAutorisation module,
            ActionOrganigramme action,
            String trxName
    ) {
        Set<Integer> resultat = new LinkedHashSet<>();

        if (employeId <= 0 || typeId <= 0 || module == null || action == null) {
            return new ArrayList<>(resultat);
        }

        // 1. Poste courant de l'employé
        Integer posteCourant = GeneralSqlController.getCurrentJobId(employeId);
        if (posteCourant == null || posteCourant <= 0) {
            log.warning("OrganigrammeService: aucun poste trouvé pour l'employé #" + employeId);
            return new ArrayList<>(resultat);
        }

        // 2. Remonter la hiérarchie et collecter les acteurs habilités
        Set<Integer> postesVisites = new HashSet<>();
        collecterActeurs(
            posteCourant, typeId, module, action,
            postesVisites, resultat, trxName
        );

        return new ArrayList<>(resultat);
    }

    /**
     * Raccourci — retourne les approbateurs pour un congé.
     */
    public static List<Integer> getApprobateursConge(
            int employeId, int typeCongeId, String trxName) {
        return getActeurs(
            employeId, typeCongeId,
            ModuleAutorisation.CONGE, ActionOrganigramme.APPROBATION,
            trxName
        );
    }

    /**
     * Raccourci — retourne les validateurs pour un congé.
     */
    public static List<Integer> getValidateursConge(
            int employeId, int typeCongeId, String trxName) {
        return getActeurs(
            employeId, typeCongeId,
            ModuleAutorisation.CONGE, ActionOrganigramme.VALIDATION,
            trxName
        );
    }

    /**
     * Raccourci — retourne les responsables de compensation congé.
     */
    public static List<Integer> getResponsablesCompensation(
            int employeId, int typeCongeId, String trxName) {
        return getActeurs(
            employeId, typeCongeId,
            ModuleAutorisation.CONGE, ActionOrganigramme.COMPENSATION,
            trxName
        );
    }

    /**
     * Raccourci — retourne les approbateurs pour une sanction.
     */
    public static List<Integer> getApprobateursSanction(
            int employeId, int typeSanctionId, String trxName) {
        return getActeurs(
            employeId, typeSanctionId,
            ModuleAutorisation.SANCTION, ActionOrganigramme.APPROBATION,
            trxName
        );
    }

    /**
     * Raccourci — retourne les validateurs pour une sanction.
     */
    public static List<Integer> getValidateursSanction(
            int employeId, int typeSanctionId, String trxName) {
        return getActeurs(
            employeId, typeSanctionId,
            ModuleAutorisation.SANCTION, ActionOrganigramme.VALIDATION,
            trxName
        );
    }

    /**
     * Raccourci — retourne les émetteurs habilités pour une absence.
     */
    public static List<Integer> getEmetteursAbsence(
            int employeId, int typeAbsenceId, String trxName) {
        return getActeurs(
            employeId, typeAbsenceId,
            ModuleAutorisation.ABSENCE, ActionOrganigramme.EMISSION,
            trxName
        );
    }

    /**
     * Retourne TOUS les supérieurs hiérarchiques sans filtre d'action.
     * Utilisé pour les notifications CC/BCC (information générale).
     */
    public static List<Integer> getSuperieurs(int employeId, String trxName) {

        Set<Integer> resultat = new LinkedHashSet<>();

        Integer posteCourant = GeneralSqlController.getCurrentJobId(employeId);
        if (posteCourant == null || posteCourant <= 0) {
            return new ArrayList<>();
        }

        Set<Integer> postesVisites = new HashSet<>();
        collecterTousSuperieurs(posteCourant, postesVisites, resultat, trxName);

        return new ArrayList<>(resultat);
    }

    // =========================================================================
    // NAVIGATION HIÉRARCHIQUE
    // =========================================================================

    /**
     * Remonte la hiérarchie depuis un poste donné et collecte les employés
     * habilités pour l'action demandée dans le module concerné.
     */
    private static void collecterActeurs(
            int posteCourant,
            int typeId,
            ModuleAutorisation module,
            ActionOrganigramme action,
            Set<Integer> postesVisites,
            Set<Integer> resultat,
            String trxName
    ) {
        if (postesVisites.contains(posteCourant)) return;
        postesVisites.add(posteCourant);

        // Récupérer les postes responsables + leur catégorie
        List<int[]> liensHierarchiques =
            getLiensHierarchiques(posteCourant, trxName);

        for (int[] lien : liensHierarchiques) {
            int posteResponsable   = lien[0];
            int categorieId        = lien[1];

            // Vérifier si cette catégorie a le droit d'effectuer l'action
            // sur ce type de document dans ce module
            boolean habilite = estHabilite(
                categorieId, typeId, module, action, trxName
            );

            if (habilite) {
                // Ajouter tous les employés occupant ce poste
                List<Integer> employes =
                    GeneralSqlController.getEmployeesByJob(posteResponsable);
                resultat.addAll(employes);
            }
        }
    }

    /**
     * Remonte la hiérarchie sans filtre — pour les notifications information.
     */
    private static void collecterTousSuperieurs(
            int posteCourant,
            Set<Integer> postesVisites,
            Set<Integer> resultat,
            String trxName
    ) {
        if (postesVisites.contains(posteCourant)) return;
        postesVisites.add(posteCourant);

        List<int[]> liens = getLiensHierarchiques(posteCourant, trxName);

        for (int[] lien : liens) {
            int posteResponsable = lien[0];
            List<Integer> employes =
                GeneralSqlController.getEmployeesByJob(posteResponsable);
            resultat.addAll(employes);
            // Récursif — remonter encore si nécessaire
            collecterTousSuperieurs(
                posteResponsable, postesVisites, resultat, trxName
            );
        }
    }

    /**
     * Retourne les liens hiérarchiques depuis un poste :
     * liste de [posteResponsable_ID, categorieResponsabilite_ID].
     */
    private static List<int[]> getLiensHierarchiques(
            int posteId, String trxName) {

        List<int[]> liens = new ArrayList<>();

        String sql =
            "SELECT Poste_Responsable_ID, HR_Categorie_Responsabilite_ID "
            + "FROM adempiere.HR_Organigramme "
            + "WHERE Poste_ID = ? AND IsActive = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, posteId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                liens.add(new int[]{
                    rs.getInt(1), // posteResponsable_ID
                    rs.getInt(2)  // categorieResponsabilite_ID
                });
            }
        } catch (Exception erreurCatch) {
            log.warning("getLiensHierarchiques: " + erreurCatch.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }

        return liens;
    }

    // =========================================================================
    // VÉRIFICATION D'HABILITATION
    // =========================================================================

    /**
     * Vérifie si une catégorie de responsabilité est habilitée à effectuer
     * une action sur un type de document dans un module donné.
     *
     * Interroge la table d'autorisation du module concerné.
     */
    private static boolean estHabilite(
            int categorieId,
            int typeId,
            ModuleAutorisation module,
            ActionOrganigramme action,
            String trxName
    ) {
        String sql = buildSqlHabilitation(module, action);
        if (sql == null) return false;

        try {
            int count = DB.getSQLValueEx(trxName, sql, typeId, categorieId);
            return count > 0;
        } catch (Exception erreurCatch) {
            log.warning("estHabilite: " + erreurCatch.getMessage());
            return false;
        }
    }

    /**
     * Construit la requête SQL de vérification d'habilitation
     * selon le module et l'action.
     */
    private static String buildSqlHabilitation(
            ModuleAutorisation module,
            ActionOrganigramme action
    ) {
        String table  = module.getTableAutorisation();
        String colType = module.getColonneType();
        String colAction = getColonneAction(action);

        if (table == null || colType == null || colAction == null) {
            return null;
        }

        return "SELECT COUNT(*) FROM adempiere." + table
            + " WHERE " + colType + " = ?"
            + " AND HR_Categorie_Responsabilite_ID = ?"
            + " AND " + colAction + " = 'Y'"
            + " AND IsActive = 'Y'";
    }

    /**
     * Mappe une ActionOrganigramme vers le nom de colonne en base.
     */
    private static String getColonneAction(ActionOrganigramme action) {
        switch (action) {
            case EMISSION:      return "IsEmission";
            case APPROBATION:   return "IsApprobation";
            case VALIDATION:    return "IsValidation";
            case COMPENSATION:  return "IsCompensation";
            default:            return null;
        }
    }
}
