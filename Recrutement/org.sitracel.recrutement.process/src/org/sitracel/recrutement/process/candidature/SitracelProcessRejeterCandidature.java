package org.sitracel.recrutement.process.candidature;

import org.compiere.process.SvrProcess;
import org.sitracel.recrutement.process.controller.ProcessControllerRecrutement;

public class SitracelProcessRejeterCandidature extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        ProcessControllerRecrutement.rejeterCandidature(getRecord_ID(), getAD_User_ID());
        return null;
    }
}
