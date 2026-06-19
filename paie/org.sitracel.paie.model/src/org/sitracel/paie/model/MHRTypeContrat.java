/******************************************************************************
 * BIA RH et Paie — Projet Sitracel
 *****************************************************************************/
package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Classe modèle pour la table HR_TypeContrat (Type de contrat de travail)
 *  @author BIA (généré lors de la refonte)
 */
public class MHRTypeContrat extends X_HR_TypeContrat {

    private static final long serialVersionUID = 1L;

    /** Constructeur standard — chargement par ID */
    public MHRTypeContrat(Properties ctx, int HR_TypeContrat_ID, String trxName) {
        super(ctx, HR_TypeContrat_ID, trxName);
    }

    /** Constructeur ResultSet — utilisé par la factory */
    public MHRTypeContrat(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
