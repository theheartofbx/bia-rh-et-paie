package org.sitracel.conge.process.conge;

import org.compiere.process.SvrProcess;
import org.sitracel.conge.process.conge.service.CongeProcessService;

public class SitracelProcessApprouverConge extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        CongeProcessService.approuverConge(getRecord_ID(), getAD_User_ID());
        return null;
    }
}
