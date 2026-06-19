/******************************************************************************
 * BIA RH et Paie — Projet Sitracel
 *****************************************************************************/
package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Classe modèle pour la table HR_Echelon (Échelon professionnel)
 *  @author BIA (généré lors de la refonte)
 */
public class MHREchelon extends X_HR_Echelon {

    private static final long serialVersionUID = 1L;

    /** Constructeur standard — chargement par ID */
    public MHREchelon(Properties ctx, int HR_Echelon_ID, String trxName) {
        super(ctx, HR_Echelon_ID, trxName);
    }

    /** Constructeur ResultSet — utilisé par la factory */
    public MHREchelon(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
