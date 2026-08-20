package org.sitracel.evaluation.modelvalidator.eval;

import java.util.logging.Logger;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.sitracel.evaluation.model.I_HR_Eval;

/**
 * ModelValidator HR_Eval
 *
 * BEFORE_NEW :
 *   1. Auto-génération du Name
 *
 * BEFORE_CHANGE :
 *   2. Garde-fou : si IsValidee = Y → interdire modification
 *   3. IsValidee et IsRejetee mutuellement exclusifs
 *
 * BEFORE_DELETE :
 *   4. Interdire suppression si IsGeneree = Y
 */
public class SitracelModelValidatorEval implements ModelValidator {

    private static final Logger log = Logger.getLogger(
            SitracelModelValidatorEval.class.getName());

    private int m_AD_Client_ID = -1;

    // =========================================================================
    // INITIALISATION
    // =========================================================================

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) m_AD_Client_ID = client.getAD_Client_ID();
        engine.addModelChange(I_HR_Eval.Table_Name, this);
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
        if (type == TYPE_BEFORE_NEW) {
            return beforeNew(po);
        }
        if (type == TYPE_BEFORE_CHANGE) {
            return beforeChange(po);
        }
        if (type == TYPE_BEFORE_DELETE) {
            return beforeDelete(po);
        }
        return null;
    }

    // =========================================================================
    // BEFORE_NEW
    // =========================================================================

    private String beforeNew(PO po) {
        genererName(po);
        return null;
    }

    // =========================================================================
    // BEFORE_CHANGE
    // =========================================================================

    private String beforeChange(PO po) {
        // --- Auto-régénérer le Name si les champs clés changent ---
        if (po.is_ValueChanged("C_BPartner_ID")
                || po.is_ValueChanged("HR_EvalGrille_ID")
                || po.is_ValueChanged("HR_EvalPeriode_ID")) {
            genererName(po);
        }

        // --- IsValidee et IsRejetee mutuellement exclusifs ---
        Object isValideeObj = po.get_Value("IsValidee");
        Object isRejeteeObj = po.get_Value("IsRejetee");
        boolean isValidee = isValideeObj != null && "Y".equals(isValideeObj.toString());
        boolean isRejetee = isRejeteeObj != null && "Y".equals(isRejeteeObj.toString());

        if (isValidee && isRejetee) {
            return "Une évaluation ne peut pas être validée et rejetée en même temps.";
        }

        return null;
    }

    // =========================================================================
    // BEFORE_DELETE
    // =========================================================================

    private String beforeDelete(PO po) {
        Object isGenereeObj = po.get_Value("IsGeneree");
        boolean isGeneree = isGenereeObj != null && "Y".equals(isGenereeObj.toString());

        if (isGeneree) {
            return "Impossible de supprimer une évaluation déjà générée. "
                + "Désactivez-la plutôt.";
        }

        return null;
    }

    // =========================================================================
    // MÉTHODES PRIVÉES
    // =========================================================================

    /**
     * Auto-générer le Name : "NOM EMPLOYÉ - Grille - Période"
     */
    private void genererName(PO po) {
        String trx = po.get_TrxName();

        // Nom de l'employé
        int bpartnerId = (Integer) po.get_Value("C_BPartner_ID");
        String nomEmploye = "";
        if (bpartnerId > 0) {
            nomEmploye = DB.getSQLValueString(trx,
                "SELECT Name FROM C_BPartner WHERE C_BPartner_ID = ?", bpartnerId);
            if (nomEmploye == null) nomEmploye = "";
        }

        // Nom de la grille
        int grilleId = (Integer) po.get_Value("HR_EvalGrille_ID");
        String nomGrille = "";
        if (grilleId > 0) {
            nomGrille = DB.getSQLValueString(trx,
                "SELECT Name FROM HR_EvalGrille WHERE HR_EvalGrille_ID = ?", grilleId);
            if (nomGrille == null) nomGrille = "";
        }

        // Nom de la période
        int periodeId = (Integer) po.get_Value("HR_EvalPeriode_ID");
        String nomPeriode = "";
        if (periodeId > 0) {
            nomPeriode = DB.getSQLValueString(trx,
                "SELECT Name FROM HR_EvalPeriode WHERE HR_EvalPeriode_ID = ?", periodeId);
            if (nomPeriode == null) nomPeriode = "";
        }

        String name = nomEmploye + " - " + nomGrille + " - " + nomPeriode;
        po.set_ValueOfColumn("Name", name.trim());
    }
}
