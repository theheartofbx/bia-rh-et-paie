package org.sitracel.formation.process.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.sitracel.formation.process.*;

public class SitracelFormationProcessFactory implements IProcessFactory {

    @Override
    public ProcessCall newProcessInstance(String className) {
        if ("org.sitracel.formation.process.SitracelProcessValiderSessionFormation".equals(className))
            return new SitracelProcessValiderSessionFormation();
        if ("org.sitracel.formation.process.SitracelProcessAnnulerSessionFormation".equals(className))
            return new SitracelProcessAnnulerSessionFormation();
        if ("org.sitracel.formation.process.SitracelProcessGenererPlanningFormation".equals(className))
            return new SitracelProcessGenererPlanningFormation();
        if ("org.sitracel.formation.process.SitracelProcessApprouverDemandeFormation".equals(className))
            return new SitracelProcessApprouverDemandeFormation();
        if ("org.sitracel.formation.process.SitracelProcessRejeterDemandeFormation".equals(className))
            return new SitracelProcessRejeterDemandeFormation();
        return null;
    }
}
