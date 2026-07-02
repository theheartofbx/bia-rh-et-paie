package org.sitracel.paie.process.salairebase;

import org.compiere.process.SvrProcess;

/**
 * Process iDempiere — Actualiser les jours non payés.
 *
 * NOTE : l'implémentation d'origine (ProcessControllerPaie.mAJJNonPaie)
 * était vide — ce process n'a jamais rien fait de fonctionnel.
 * Laissé en no-op volontairement en attendant une vraie spécification
 * de ce que "actualiser les jours non payés" doit faire.
 */
public class SitracelProcessActualiserJourNonPaye extends SvrProcess {

    @Override
    protected void prepare() {
        // Aucun paramètre pour l'instant
    }

    @Override
    protected String doIt() throws Exception {
        return "Aucune action — process non implémenté (voir note de code).";
    }
}
