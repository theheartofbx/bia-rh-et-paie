package org.sitracel.parametrage;

import org.compiere.util.DB;
import org.sitracel.model.I_HR_Parametre_Numerique;

/**
 * Service transversal — paramètres système RH.
 *
 * Centralise l'accès aux paramètres numériques configurables
 * définis dans la table HR_Parametre_Numerique.
 *
 * Remplace GeneralSqlController.getParametreFromParametreNumerique().
 */
public final class HRParametreService {

    private HRParametreService() {}

    /**
     * Retourne la valeur entière d'un paramètre système par son nom.
     * Retourne 0 si le paramètre n'existe pas.
     *
     * @param nomParametre Nom exact du paramètre en base
     */
    public static int getParametreNumerique(String nomParametre) {
        if (nomParametre == null || nomParametre.isBlank()) {
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
