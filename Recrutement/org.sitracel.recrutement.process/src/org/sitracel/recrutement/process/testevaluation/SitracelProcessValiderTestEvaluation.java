package org.sitracel.recrutement.process.testevaluation;

import org.compiere.process.SvrProcess;
import org.sitracel.recrutement.process.controller.ProcessControllerRecrutement;

public class SitracelProcessValiderTestEvaluation extends SvrProcess {
    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        return ProcessControllerRecrutement.validerTestEvaluation(getRecord_ID(), getAD_User_ID());
    }
}
