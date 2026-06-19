package org.sitracel.conge.process.conge;

import org.compiere.process.SvrProcess;
import org.sitracel.conge.process.conge.service.CongeProcessService;

public class SitracelProcessActualiserAbsence extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        CongeProcessService.updateAbsenceConge(getRecord_ID());
        return null;
    }
}
