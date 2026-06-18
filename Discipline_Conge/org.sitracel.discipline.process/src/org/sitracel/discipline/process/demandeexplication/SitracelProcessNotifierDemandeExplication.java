package org.sitracel.discipline.process.demandeexplication;

import org.compiere.process.SvrProcess;

/**
 * Ancien process de notification manuelle des demandes d'explication.
 *
 * OBSOLÈTE — Le bouton de notification associé à ce process peut être
 * retiré de l'interface iDempiere.
 *
 * Les notifications sont désormais envoyées automatiquement par le
 * modelvalidator lors de chaque sauvegarde de MHRDemandeExplication :
 *   → DEMANDE_EXPLICATION_CREATED : à la création
 *   → DEMANDE_EXPLICATION_REPLIED : quand l'employé saisit sa réponse
 *
 * Ce fichier est conservé pour éviter les erreurs OSGi si le process
 * est encore enregistré en base (AD_Process). Il peut être supprimé
 * après avoir retiré l'enregistrement AD_Process correspondant.
 */
public class SitracelProcessNotifierDemandeExplication extends SvrProcess {

    @Override
    protected void prepare() {
        // Rien à préparer — process obsolète
    }

    @Override
    protected String doIt() throws Exception {
        // Process obsolète — les notifications partent automatiquement
        // via ModelValidatorDisciplineController
        return "Notifications gérées automatiquement par le système.";
    }
}
