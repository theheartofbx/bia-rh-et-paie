package org.sitracel.evaluation.modelvalidator.reference;

import java.sql.Timestamp;
import java.util.logging.Logger;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.sitracel.evaluation.model.I_HR_EvalPeriode;

/**
 * ModelValidator HR_EvalPeriode
 *
 * BEFORE_NEW / BEFORE_CHANGE :
 *   1. Date_Fin > Date_Debut
 *   2. Si IsCloturee = Y → interdire modification des dates
 *
 * BEFORE_DELETE :
 *   3. Interdire suppression si des évaluations utilisent cette période
 */
public class SitracelModelValidatorPeriode implements ModelValidator {

    private static final Logger log = Logger.getLogger(
            SitracelModelValidatorPeriode.class.getName());

    private int m_AD_Client_ID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) m_AD_Client_ID = client.getAD_Client_ID();
        engine.addModelChange(I_HR_EvalPeriode.Table_Name, this);
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
        if (type == TYPE_BEFORE_DELETE) {
            return beforeDelete(po);
        }
        return null;
    }

    private String beforeSave(PO po, boolean isNew) {
        Timestamp dateDebut = (Timestamp) po.get_Value("Date_Debut");
        Timestamp dateFin = (Timestamp) po.get_Value("Date_Fin");

        // --- Garde-fou 1 : Date_Fin > Date_Debut ---
        if (dateDebut != null && dateFin != null && !dateFin.after(dateDebut)) {
            return "La date de fin doit être postérieure à la date de début.";
        }

        // --- Garde-fou 2 : clôturée → dates non modifiables ---
        if (!isNew) {
            Object isClotureeObj = po.get_Value("IsCloturee");
            boolean isCloturee = isClotureeObj != null && "Y".equals(isClotureeObj.toString());

            if (isCloturee) {
                if (po.is_ValueChanged("Date_Debut") || po.is_ValueChanged("Date_Fin")) {
                    return "La période est clôturée, les dates ne peuvent plus être modifiées.";
                }
            }
        }

        return null;
    }

    private String beforeDelete(PO po) {
        int periodeId = po.get_ID();

        int nbEval = DB.getSQLValueEx(po.get_TrxName(),
            "SELECT COUNT(*) FROM HR_Eval"
            + " WHERE HR_EvalPeriode_ID = ? AND IsActive = 'Y'",
            periodeId);
        if (nbEval > 0) {
            return "Impossible de supprimer : " + nbEval
                + " évaluation(s) utilisent cette période.";
        }

        return null;
    }
}
