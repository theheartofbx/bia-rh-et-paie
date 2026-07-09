package org.sitracel.discipline.modelvalidator.sanction;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.modelvalidator.service.DisciplineValidatorService;

/**
 * Corrigé : modelChange() ignorait complètement le retour de
 * DisciplineValidatorService.discipline() (renvoyait toujours null) —
 * même si le service voulait bloquer un enregistrement, rien ne
 * l'empêchait jamais réellement. Le service retourne maintenant un
 * message d'erreur (String) au lieu de void, et ce message est propagé.
 */
public class SitracelModelValidatorDiscipline implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRPunishment.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() {
        return 0;
    }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
        return null;
    }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (!(po instanceof MHRPunishment)) {
            CLogger.get().severe("Objet non attendu : " + po.getClass().getName());
            return null;
        }
        try {
            return DisciplineValidatorService.discipline((MHRPunishment) po, type);
        } catch (Exception e) {
            CLogger.get().severe("Erreur discipline() : " + e.getMessage());
            throw e;
        }
    }

    @Override
    public String docValidate(PO po, int timing) {
        return null;
    }
}
