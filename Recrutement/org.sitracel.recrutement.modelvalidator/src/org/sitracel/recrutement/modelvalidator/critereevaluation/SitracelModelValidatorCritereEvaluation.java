package org.sitracel.recrutement.modelvalidator.critereevaluation;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.recrutement.model.MHROffreCritereEvaluation;
import org.sitracel.recrutement.modelvalidator.service.RecrutementValidatorService;

/**
 * Garde-fous sur les critères d'évaluation (HR_OffreCritereEvaluation).
 *
 * - Ajout d'un critère → créer les évaluations pour les candidats existants
 * - Modification ScoreMax/Pondération → propager si pas de scores, bloquer sinon
 * - Suppression → bloquer si des scores existent, supprimer les évaluations sinon
 */
public class SitracelModelValidatorCritereEvaluation implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHROffreCritereEvaluation.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return 0; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        MHROffreCritereEvaluation critere = (MHROffreCritereEvaluation) po;

        if (type == ModelValidator.TYPE_BEFORE_CHANGE) {
            String erreur = RecrutementValidatorService.verifierModificationCritere(critere);
            if (erreur != null) return erreur;
        }
        if (type == ModelValidator.TYPE_AFTER_NEW) {
            RecrutementValidatorService.apresCreationCritere(critere);
        }
        if (type == ModelValidator.TYPE_AFTER_CHANGE) {
            RecrutementValidatorService.propagerModificationCritere(critere);
        }
        if (type == ModelValidator.TYPE_BEFORE_DELETE) {
            String erreur = RecrutementValidatorService.verifierSuppressionCritere(critere);
            if (erreur != null) return erreur;
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
