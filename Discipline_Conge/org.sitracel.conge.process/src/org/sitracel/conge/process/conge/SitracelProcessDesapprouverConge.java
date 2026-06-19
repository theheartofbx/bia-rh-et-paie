package org.sitracel.conge.process.conge;

import org.compiere.process.SvrProcess;
import org.sitracel.conge.process.conge.service.CongeProcessService;

public class SitracelProcessDesapprouverConge extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        CongeProcessService.desapprouverConge(getRecord_ID(), getAD_User_ID());
        return null;
    }
}
