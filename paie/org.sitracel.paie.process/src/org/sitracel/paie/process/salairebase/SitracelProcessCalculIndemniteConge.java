package org.sitracel.paie.process.salairebase;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.sitracel.paie.process.service.PayrollOrchestrator;

/**
 * Process iDempiere — Calcul de l'indemnité de congé.
 *
 * Point d'entrée OSGi : reçoit les paramètres de l'interface iDempiere
 * et délègue immédiatement à PayrollOrchestrator.
 *
 * Cette classe ne contient AUCUNE logique métier.
 */
public class SitracelProcessCalculIndemniteConge extends SvrProcess {

    private int bpartnerID;
    private int holidayID;

    @Override
    protected void prepare() {
        for (ProcessInfoParameter para : getParameter()) {
            String name = para.getParameterName();
            if ("C_BPartner_ID".equals(name)) {
                bpartnerID = para.getParameterAsInt();
            } else if ("HR_Holiday_ID".equals(name)) {
                holidayID = para.getParameterAsInt();
            }
        }
    }

    @Override
    protected String doIt() throws Exception {
        if (bpartnerID <= 0 || holidayID <= 0) {
            throw new AdempiereUserError(
                    "Veuillez sélectionner un employé et un congé avant de lancer le calcul.");
        }
        return PayrollOrchestrator.calculerIndemniteConge(bpartnerID, holidayID);
    }
}
