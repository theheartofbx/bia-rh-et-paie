/******************************************************************************
 * BIA RH et Paie — Projet Sitracel
 *****************************************************************************/
package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Classe modèle pour la table HR_RecapSalaire (Récapitulatif des derniers salaires)
 *  @author BIA (généré lors de la refonte)
 */
public class MHRRecapSalaire extends X_HR_RecapSalaire {

    private static final long serialVersionUID = 1L;

    /** Constructeur standard — chargement par ID */
    public MHRRecapSalaire(Properties ctx, int HR_RecapSalaire_ID, String trxName) {
        super(ctx, HR_RecapSalaire_ID, trxName);
    }

    /** Constructeur ResultSet — utilisé par la factory */
    public MHRRecapSalaire(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
