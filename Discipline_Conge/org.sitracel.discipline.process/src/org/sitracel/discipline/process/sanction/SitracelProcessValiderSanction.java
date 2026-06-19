package org.sitracel.discipline.process.sanction;

import org.compiere.process.SvrProcess;
import org.sitracel.discipline.process.sanction.service.DisciplineProcessService;

public class SitracelProcessValiderSanction extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        DisciplineProcessService.validerSanction(getRecord_ID(), getAD_User_ID());
        return null;
    }
}
