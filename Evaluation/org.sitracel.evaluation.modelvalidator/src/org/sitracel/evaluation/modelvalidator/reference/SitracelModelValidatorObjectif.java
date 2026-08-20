package org.sitracel.evaluation.modelvalidator.reference;

import java.util.logging.Logger;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.sitracel.evaluation.model.I_HR_EvalObjectif;

/**
 * ModelValidator HR_EvalObjectif
 *
 * BEFORE_NEW / BEFORE_CHANGE :
 *   1. Name unique parmi les objectifs actifs
 *   2. Value unique parmi les objectifs actifs
 */
public class SitracelModelValidatorObjectif implements ModelValidator {

    private static final Logger log = Logger.getLogger(
            SitracelModelValidatorObjectif.class.getName());

    private int m_AD_Client_ID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) m_AD_Client_ID = client.getAD_Client_ID();
        engine.addModelChange(I_HR_EvalObjectif.Table_Name, this);
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
        int currentId = isNew ? 0 : po.get_ID();
        String trx = po.get_TrxName();

        // --- Garde-fou 1 : Name unique ---
        String name = (String) po.get_Value("Name");
        if (name != null && !name.trim().isEmpty()) {
            int doublonName = DB.getSQLValueEx(trx,
                "SELECT COUNT(*) FROM HR_EvalObjectif"
                + " WHERE Name = ? AND IsActive = 'Y'"
                + " AND HR_EvalObjectif_ID != ?",
                name.trim(), currentId);
            if (doublonName > 0) {
                return "Un objectif avec le nom '" + name.trim() + "' existe déjà.";
            }
        }

        // --- Garde-fou 2 : Value unique ---
        String value = (String) po.get_Value("Value");
        if (value != null && !value.trim().isEmpty()) {
            int doublonValue = DB.getSQLValueEx(trx,
                "SELECT COUNT(*) FROM HR_EvalObjectif"
                + " WHERE Value = ? AND IsActive = 'Y'"
                + " AND HR_EvalObjectif_ID != ?",
                value.trim(), currentId);
            if (doublonValue > 0) {
                return "Un objectif avec le code '" + value.trim() + "' existe déjà.";
            }
        }

        return null;
    }
}
