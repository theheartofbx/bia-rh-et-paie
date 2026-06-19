/******************************************************************************
 * BIA RH et Paie — Projet Sitracel
 *****************************************************************************/
package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Classe modèle pour la table HR_Mois (Mois de référence paie)
 *  @author BIA (généré lors de la refonte)
 */
public class MHRMois extends X_HR_Mois {

    private static final long serialVersionUID = 1L;

    /** Constructeur standard — chargement par ID */
    public MHRMois(Properties ctx, int HR_Mois_ID, String trxName) {
        super(ctx, HR_Mois_ID, trxName);
    }

    /** Constructeur ResultSet — utilisé par la factory */
    public MHRMois(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
