package org.sitracel.paie.process.periodes;

import org.compiere.process.SvrProcess;
import org.sitracel.paie.process.service.PeriodeSalarialeService;

/**
 * Process iDempiere — Vérifier la cohérence des périodes salariales.
 *
 * Rapport sur les trous et chevauchements.
 * Sans paramètre, sans modification — lecture seule.
 *
 * Enregistrement dictionnaire :
 *   Classname : org.sitracel.paie.process.periodes.SitracelProcessVerifierPeriodes
 */
public class SitracelProcessVerifierPeriodes extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        String rapport = PeriodeSalarialeService.verifierCoherence(get_TrxName());
        addLog(rapport);
        return rapport.startsWith("OK") ? "Aucune anomalie" : "Anomalies détectées — voir le log";
    }
}
