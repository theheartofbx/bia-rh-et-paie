package org.sitracel.recrutement.modelvalidator.evaluationcandidature;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.recrutement.model.MHRCandidatEvaluation;
import org.sitracel.recrutement.modelvalidator.service.RecrutementValidatorService;

public class SitracelModelValidatorEvaluationCandidature implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRCandidatEvaluation.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return 0; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        MHRCandidatEvaluation evaluation = (MHRCandidatEvaluation) po;

        // Garde-fou serveur : score ne peut pas dépasser le maximum
        // (dupliqué du callout CalloutScoreCompetence pour protection côté serveur)
        if (type == ModelValidator.TYPE_BEFORE_NEW
                || type == ModelValidator.TYPE_BEFORE_CHANGE) {
            String erreur = RecrutementValidatorService.validerScoreEvaluation(evaluation);
            if (erreur != null) return erreur;
        }

        // Recalcul du classement après toute modification d'évaluation
        if (type == ModelValidator.TYPE_AFTER_NEW
                || type == ModelValidator.TYPE_AFTER_CHANGE) {
            RecrutementValidatorService.actualiserCandidature(
                evaluation.getHR_Candidature().getHR_SessionRecrutement_ID(),
                evaluation);
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
