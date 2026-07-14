package org.sitracel.paie.process.periodes;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.sitracel.paie.process.service.PeriodeSalarialeService;

/**
 * Process iDempiere — Générer les périodes salariales d'une année.
 *
 * Paramètre : Annee (Integer) — l'année à générer
 *
 * Usage : lancer une fois par an (ou via scheduler en décembre)
 * pour préparer les 12 périodes de l'année suivante.
 *
 * Enregistrement dictionnaire :
 *   Classname : org.sitracel.paie.process.periodes.SitracelProcessGenererPeriodes
 */
public class SitracelProcessGenererPeriodes extends SvrProcess {

    private int annee = 0;

    @Override
    protected void prepare() {
        for (ProcessInfoParameter p : getParameter()) {
            if ("Annee".equalsIgnoreCase(p.getParameterName())) {
                annee = p.getParameterAsInt();
            }
        }

        // Si pas de paramètre, générer l'année suivante par défaut
        if (annee <= 0) {
            annee = java.time.LocalDate.now().getYear() + 1;
        }
    }

    @Override
    protected String doIt() throws Exception {

        // Vérifier la cohérence avant de générer
        String coherence = PeriodeSalarialeService.verifierCoherence(get_TrxName());
        if (!coherence.startsWith("OK")) {
            addLog("ANOMALIES detectees avant generation : " + coherence);
        }

        addLog("Génération des périodes pour l'année " + annee + "...");
        int crees = PeriodeSalarialeService.genererAnnee(annee, get_TrxName());

        // Vérifier la cohérence après génération
        String coherenceApres = PeriodeSalarialeService.verifierCoherence(get_TrxName());
        if (!coherenceApres.startsWith("OK")) {
            addLog("ANOMALIES detectees apres generation : " + coherenceApres);
        } else {
            addLog("OK - Coherence verifiee, aucune anomalie");
        }

        return crees + " période(s) créée(s) pour " + annee;
    }
}
