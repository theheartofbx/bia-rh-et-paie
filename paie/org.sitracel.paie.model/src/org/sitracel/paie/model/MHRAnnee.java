/******************************************************************************
 * BIA RH et Paie — Projet Sitracel
 *****************************************************************************/
package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Classe modèle pour la table HR_Annee (Année de référence paie)
 *  @author BIA (généré lors de la refonte)
 */
public class MHRAnnee extends X_HR_Annee {

    private static final long serialVersionUID = 1L;

    /** Constructeur standard — chargement par ID */
    public MHRAnnee(Properties ctx, int HR_Annee_ID, String trxName) {
        super(ctx, HR_Annee_ID, trxName);
    }

    /** Constructeur ResultSet — utilisé par la factory */
    public MHRAnnee(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
