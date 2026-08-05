package org.sitracel.recrutement.sessionrecrutement;

import org.compiere.process.SvrProcess;
import org.sitracel.recrutement.process.controller.ProcessControllerRecrutement;

public class SitracelProcessRejeterSessionRecrutement extends SvrProcess {
    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        return ProcessControllerRecrutement.rejeterSessionRecrutement(getRecord_ID(), getAD_User_ID());
    }
}
