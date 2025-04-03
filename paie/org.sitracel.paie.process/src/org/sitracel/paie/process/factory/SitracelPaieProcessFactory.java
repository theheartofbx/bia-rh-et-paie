package org.sitracel.paie.process.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.sitracel.paie.process.salairebase.SitracelProcessActualiserJourNonPaye;
import org.sitracel.paie.process.salairebase.SitracelProcessCalculPaie;

public class SitracelPaieProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {
		// TODO Auto-generated method stub
		if(className.equals("org.sitracel.paie.process.salairebase.SitracelProcessActualiserJourNonPaye")) {
			return new SitracelProcessActualiserJourNonPaye();
		}
		if(className.equals("org.sitracel.paie.process.salairebase.SitracelProcessCalculPaie")) {
			return new SitracelProcessCalculPaie();
		}
		return null;
	}

}
