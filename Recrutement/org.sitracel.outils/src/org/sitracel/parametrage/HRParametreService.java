package org.sitracel.parametrage;

import org.compiere.util.DB;
import org.sitracel.model.I_HR_Parametre_Numerique;

/**
 * Service transversal - parametres systeme RH.
 *
 * Centralise l'acces aux parametres numeriques configurables
 * definis dans la table HR_Parametre_Numerique.
 *
 * Remplace GeneralSqlController.getParametreFromParametreNumerique().
 */
public final class HRParametreService {

    private HRParametreService() {}

    /**
     * Retourne la valeur entiere d'un parametre systeme par son nom.
     * Retourne 0 si le parametre n'existe pas.
     *
     * Compatible Java 8 : utilise trim().isEmpty() au lieu de
     * isBlank() (Java 11+).
     *
     * @param nomParametre Nom exact du parametre en base
     */
    public static int getParametreNumerique(String nomParametre) {
        if (nomParametre == null || nomParametre.trim().isEmpty()) {
            return 0;
        }

        String sql = "SELECT " + I_HR_Parametre_Numerique.COLUMNNAME_Valeur_Parametre
            + " FROM " + I_HR_Parametre_Numerique.Table_Name
            + " WHERE " + I_HR_Parametre_Numerique.COLUMNNAME_Name + " = ?"
            + " AND IsActive = 'Y'";

        int valeur = DB.getSQLValue(null, sql, nomParametre);
        return valeur > 0 ? valeur : 0;
    }
}
