package org.sitracel.conge.process.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.sitracel.conge.process.conge.SitracelProcessActualiserAbsence;
import org.sitracel.conge.process.conge.SitracelProcessApprouverConge;
import org.sitracel.conge.process.conge.SitracelProcessDesapprouverConge;
import org.sitracel.conge.process.conge.SitracelProcessRejeterConge;
import org.sitracel.conge.process.conge.SitracelProcessValiderConge;

public class SitracelCongeProcessFactory implements IProcessFactory {

    @Override
    public ProcessCall newProcessInstance(String className) {

        if (className.equals("org.sitracel.conge.process.conge.SitracelProcessActualiserAbsence")) {
            return new SitracelProcessActualiserAbsence();
        }
        if (className.equals("org.sitracel.conge.process.conge.SitracelProcessApprouverConge")) {
            return new SitracelProcessApprouverConge();
        }
        if (className.equals("org.sitracel.conge.process.conge.SitracelProcessDesapprouverConge")) {
            return new SitracelProcessDesapprouverConge();
        }
        if (className.equals("org.sitracel.conge.process.conge.SitracelProcessValiderConge")) {
            return new SitracelProcessValiderConge();
        }
        if (className.equals("org.sitracel.conge.process.conge.SitracelProcessRejeterConge")) {
            return new SitracelProcessRejeterConge();
        }

        return null;
    }
}
