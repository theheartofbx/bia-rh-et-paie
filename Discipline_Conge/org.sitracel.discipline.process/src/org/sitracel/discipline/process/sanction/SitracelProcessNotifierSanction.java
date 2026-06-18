package org.sitracel.discipline.process.sanction;

import org.compiere.process.SvrProcess;

/**
 * Ancien process de notification manuelle des sanctions.
 *
 * OBSOLÈTE — Le bouton de notification associé peut être retiré
 * de l'interface iDempiere.
 *
 * Les notifications partent automatiquement via le modelvalidator
 * (ModelValidatorDisciplineController) lors de chaque sauvegarde :
 *   → SANCTION_CREATED      : à la création
 *   → SANCTION_APPROVED     : à l'approbation
 *   → SANCTION_DISAPPROVED  : à la désapprobation
 *   → SANCTION_VALIDATED    : à la validation
 *   → SANCTION_REJECTED     : au rejet
 *
 * Ce fichier peut être supprimé après avoir retiré l'enregistrement
 * AD_Process correspondant en base.
 */
public class SitracelProcessNotifierSanction extends SvrProcess {

    @Override
    protected void prepare() {
        // Rien à préparer — process obsolète
    }

    @Override
    protected String doIt() throws Exception {
        return "Notifications gérées automatiquement par le système.";
    }
}
