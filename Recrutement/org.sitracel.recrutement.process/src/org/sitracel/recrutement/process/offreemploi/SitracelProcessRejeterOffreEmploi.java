package org.sitracel.recrutement.process.offreemploi;

import org.compiere.process.SvrProcess;
import org.sitracel.recrutement.process.controller.ProcessControllerRecrutement;

public class SitracelProcessRejeterOffreEmploi extends SvrProcess {
    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        return ProcessControllerRecrutement.rejeterOffreEmploi(getRecord_ID(), getAD_User_ID());
    }
}
