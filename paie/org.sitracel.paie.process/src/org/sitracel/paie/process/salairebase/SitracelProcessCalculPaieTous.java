package org.sitracel.paie.process.salairebase;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.sitracel.paie.process.service.PayrollOrchestrator;

/**
 * Process iDempiere — Calcul de la paie pour TOUS les employés actifs.
 *
 * Paramètre unique : HR_Periode_Salariale_ID
 * Délègue à PayrollOrchestrator.calculerPaieTousEmployes()
 */
public class SitracelProcessCalculPaieTous extends SvrProcess {

    private int periodeId = 0;

    @Override
    protected void prepare() {
        for (ProcessInfoParameter para : getParameter()) {
            if ("HR_Periode_Salariale_ID".equals(para.getParameterName())) {
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
        return PayrollOrchestrator.calculerPaieTousEmployes(periodeId);
    }
}
