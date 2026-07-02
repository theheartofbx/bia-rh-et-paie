package org.sitracel.discipline.process.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.sitracel.discipline.process.demandeexplication.SitracelProcessNotifierDemandeExplication;
import org.sitracel.discipline.process.sanction.SitracelProcessApprouverSanction;
import org.sitracel.discipline.process.sanction.SitracelProcessDesapprouverSanction;
import org.sitracel.discipline.process.sanction.SitracelProcessNotifierSanction;
import org.sitracel.discipline.process.sanction.SitracelProcessRejeterSanction;
import org.sitracel.discipline.process.sanction.SitracelProcessValiderSanction;

public class SitracelDisciplineProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {
		// TODO Auto-generated method stub
		if(className.equals("org.sitracel.discipline.process.sanction.SitracelProcessApprouverSanction")) {
			return new SitracelProcessApprouverSanction();
		}
		if(className.equals("org.sitracel.discipline.process.sanction.SitracelProcessDesapprouverSanction")) {
			return new SitracelProcessDesapprouverSanction();
		}
		if(className.equals("org.sitracel.discipline.process.sanction.SitracelProcessRejeterSanction")) {
			return new SitracelProcessRejeterSanction();
		}
		if(className.equals("org.sitracel.discipline.process.sanction.SitracelProcessValiderSanction")) {
			return new SitracelProcessValiderSanction();
		}
		if(className.equals("org.sitracel.discipline.process.sanction.SitracelProcessNotifierSanction")) {
			return new SitracelProcessNotifierSanction();
		}
		if(className.equals("org.sitracel.discipline.process.demandeexplication.SitracelProcessNotifierDemandeExplication")) {
			return new SitracelProcessNotifierDemandeExplication();
		}		
		
		return null;
	}

}
