package org.sitracel.evaluation.modelvalidator.grille;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.sitracel.evaluation.model.I_HR_EvalGrille;

/**
 * ModelValidator HR_EvalGrille
 *
 * BEFORE_CHANGE :
 *   1. SeuilEchec < SeuilReussite
 *
 * BEFORE_DELETE :
 *   2. Interdire suppression si des évaluations utilisent cette grille
 */
public class SitracelModelValidatorGrille implements ModelValidator {

    private static final Logger log = Logger.getLogger(
            SitracelModelValidatorGrille.class.getName());

    private int m_AD_Client_ID = -1;

    // =========================================================================
    // INITIALISATION
    // =========================================================================

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) m_AD_Client_ID = client.getAD_Client_ID();
        engine.addModelChange(I_HR_EvalGrille.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return m_AD_Client_ID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String docValidate(PO po, int timing) { return null; }

    // =========================================================================
    // DISPATCH
    // =========================================================================

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            return beforeSave(po);
        }
        if (type == TYPE_BEFORE_DELETE) {
            return beforeDelete(po);
        }
        return null;
    }

    // =========================================================================
    // BEFORE_NEW / BEFORE_CHANGE
    // =========================================================================

    private String beforeSave(PO po) {
        // --- Garde-fou : SeuilEchec < SeuilReussite ---
        BigDecimal seuilEchec = (BigDecimal) po.get_Value("SeuilEchec");
        BigDecimal seuilReussite = (BigDecimal) po.get_Value("SeuilReussite");
        if (seuilEchec != null && seuilReussite != null
                && seuilEchec.compareTo(seuilReussite) >= 0) {
            return "Le seuil d'échec (" + seuilEchec
                + ") doit être inférieur au seuil de réussite (" + seuilReussite + ").";
        }

        return null;
    }

    // =========================================================================
    // BEFORE_DELETE
    // =========================================================================

    private String beforeDelete(PO po) {
        int grilleId = po.get_ID();

        // --- Interdire suppression si des évaluations utilisent cette grille ---
        int nbEval = DB.getSQLValueEx(po.get_TrxName(),
            "SELECT COUNT(*) FROM HR_Eval"
            + " WHERE HR_EvalGrille_ID = ? AND IsActive = 'Y'",
            grilleId);
        if (nbEval > 0) {
            return "Impossible de supprimer : " + nbEval
                + " évaluation(s) utilisent cette grille.";
        }

        return null;
    }
}
