package org.sitracel.paie.process.salairebase;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.sitracel.paie.process.service.PayrollOrchestrator;

/**
 * Process iDempiere — Calcul de la paie.
 *
 * Point d'entrée OSGi : reçoit les paramètres de l'interface iDempiere
 * et délègue immédiatement à PayrollOrchestrator.
 *
 * Deux modes d'utilisation :
 *
 *   MODE 1 — Calcul pour UN employé :
 *     Paramètres : C_BPartner_ID + HR_Periode_Salariale_ID
 *     → PayrollOrchestrator.calculerPaie(bpartnerId, periodeId)
 *
 *   MODE 2 — Calcul pour TOUS les employés :
 *     Paramètres : HR_Periode_Salariale_ID uniquement (C_BPartner_ID = 0)
 *     → PayrollOrchestrator.calculerPaieTousEmployes(periodeId)
 *
 * Cette classe ne contient AUCUNE logique métier.
 */
public class SitracelProcessCalculPaie extends SvrProcess {

    private int bpartnerId     = 0;
    private int periodeId      = 0;

    @Override
    protected void prepare() {
        for (ProcessInfoParameter para : getParameter()) {
            String name = para.getParameterName();
            if ("C_BPartner_ID".equals(name)) {
                bpartnerId = para.getParameterAsInt();
            } else if ("HR_Periode_Salariale_ID".equals(name)) {
                periodeId = para.getParameterAsInt();
            }
        }
    }

    @Override
    protected String doIt() throws Exception {
        if (periodeId <= 0) {
            throw new AdempiereUserError(
                    "Veuillez sélectionner une période salariale avant de lancer le calcul.");
        }

        if (bpartnerId > 0) {
            // MODE 1 : un seul employé
            return PayrollOrchestrator.calculerPaie(bpartnerId, periodeId);
        } else {
            // MODE 2 : tous les employés de la période
            return PayrollOrchestrator.calculerPaieTousEmployes(periodeId);
        }
    }
}
