package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Classe métier pour HR_MouvementPaieType.
 * Hérite du modèle généré X_HR_MouvementPaieType.
 */
public class MHRMouvementPaieType extends X_HR_MouvementPaieType {

    private static final long serialVersionUID = 1L;

    public MHRMouvementPaieType(Properties ctx, int HR_MouvementPaieType_ID, String trxName) {
        super(ctx, HR_MouvementPaieType_ID, trxName);
    }

    public MHRMouvementPaieType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Retourne IsIndemnite ('Y' ou 'N').
     * Utilise get_Value car la colonne n'a pas de getter généré.
     */
    public String getIsIndemnite() {
        Object val = get_Value("IsIndemnite");
        return val != null ? val.toString() : "N";
    }

    public boolean isIndemnite() {
        return "Y".equalsIgnoreCase(getIsIndemnite());
    }
}
