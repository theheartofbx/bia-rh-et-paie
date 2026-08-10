package org.sitracel.formation.process;

import org.compiere.process.SvrProcess;
import org.sitracel.formation.process.controller.ProcessControllerFormation;

public class SitracelProcessApprouverDemandeFormation extends SvrProcess {
    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        return ProcessControllerFormation.approuverDemande(getRecord_ID());
    }
}
