package org.sitracel.evaluation.modelvalidator.reference;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.sitracel.evaluation.model.I_HR_EvalAppreciation;

/**
 * ModelValidator HR_EvalAppreciation
 *
 * BEFORE_NEW / BEFORE_CHANGE :
 *   1. Pourcentage unique parmi les appréciations actives
 */
public class SitracelModelValidatorAppreciation implements ModelValidator {

    private static final Logger log = Logger.getLogger(
            SitracelModelValidatorAppreciation.class.getName());

    private int m_AD_Client_ID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) m_AD_Client_ID = client.getAD_Client_ID();
        engine.addModelChange(I_HR_EvalAppreciation.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return m_AD_Client_ID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String docValidate(PO po, int timing) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            return beforeSave(po, type == TYPE_BEFORE_NEW);
        }
        return null;
    }

    private String beforeSave(PO po, boolean isNew) {
        BigDecimal pourcentage = (BigDecimal) po.get_Value("Pourcentage");
        if (pourcentage == null) return null;

        int currentId = isNew ? 0 : po.get_ID();

        int doublon = DB.getSQLValueEx(po.get_TrxName(),
            "SELECT COUNT(*) FROM HR_EvalAppreciation"
            + " WHERE Pourcentage = ? AND IsActive = 'Y'"
            + " AND HR_EvalAppreciation_ID != ?",
            pourcentage, currentId);

        if (doublon > 0) {
            String nomExistant = DB.getSQLValueString(po.get_TrxName(),
                "SELECT Name FROM HR_EvalAppreciation"
                + " WHERE Pourcentage = ? AND IsActive = 'Y'"
                + " AND HR_EvalAppreciation_ID != ?",
                pourcentage, currentId);
            return "Le pourcentage " + pourcentage
                + "% est déjà utilisé par l'appréciation '" + nomExistant + "'.";
        }

        return null;
    }
}
