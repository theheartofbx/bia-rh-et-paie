package org.sitracel.conge.process.conge;

import org.compiere.process.SvrProcess;

/**
 * Ancien process de notification manuelle des congés.
 *
 * OBSOLÈTE — Le bouton de notification associé peut être retiré
 * de l'interface iDempiere.
 *
 * Les notifications partent automatiquement via le modelvalidator
 * (SitracelCongeGeneralModelValidator) lors de chaque sauvegarde :
 *   → HOLIDAY_CREATED      : à la création
 *   → HOLIDAY_APPROVED     : à l'approbation
 *   → HOLIDAY_DISAPPROVED  : à la désapprobation
 *   → HOLIDAY_VALIDATED    : à la validation
 *   → HOLIDAY_REJECTED     : au rejet
 *
 * Ce fichier peut être supprimé après avoir retiré l'enregistrement
 * AD_Process correspondant en base.
 */
public class SitracelProcessNotifierConge extends SvrProcess {

    @Override
    protected void prepare() {
        // Rien à préparer — process obsolète
    }

    @Override
    protected String doIt() throws Exception {
        return "Notifications gérées automatiquement par le système.";
    }
}
