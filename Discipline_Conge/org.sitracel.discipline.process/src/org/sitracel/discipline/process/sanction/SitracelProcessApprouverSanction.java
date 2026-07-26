package org.sitracel.discipline.process.sanction;

import org.compiere.process.SvrProcess;
import org.sitracel.discipline.process.sanction.service.DisciplineProcessService;

public class SitracelProcessApprouverSanction extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        String result = DisciplineProcessService.approuverSanction(getRecord_ID(), getAD_User_ID());
        if (result != null) {
            throw new Exception(result);
        }
        return "Approbation effectuee avec succes.";
    }
}
