package org.sitracel.organigramme.modelvalidator;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.organigramme.model.MHROrganigramme;
import org.sitracel.organigramme.modelvalidator.service.OrganigrammeValidatorService;

/**
 * ModelValidator sur HR_Organigramme.
 *
 * Règles appliquées :
 *   1. Pas de doublon : le même triplet (Poste, Poste_Responsable, Catégorie)
 *      ne peut pas exister deux fois en actif.
 *   2. Pas de boucle : en remontant la hiérarchie depuis Poste_Responsable,
 *      on ne doit jamais retomber sur le Poste de départ.
 *
 * MINCE : toute la logique est déléguée à OrganigrammeValidatorService.
 * Ne pas ajouter de logique métier directement ici.
 */
public class SitracelModelValidatorOrganigramme implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHROrganigramme.Table_Name, this);
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
        if (po == null) {
            return null;
        }

        boolean evenementPertinent = (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE);
        if (!evenementPertinent) {
            return null;
        }

        if (po instanceof MHROrganigramme) {
            return OrganigrammeValidatorService.validerLien((MHROrganigramme) po);
        }

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) {
        return null;
    }
}
