package org.sitracel.discipline.process.sanction;

import java.sql.Timestamp;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.sitracel.callout.bean.BeanMiseaPieds;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;
import org.sitracel.discipline.process.sanction.controller.ProcessControllerDiscipline;
import org.sitracel.discipline.process.sanction.controller.ProcessSqlControllerDiscipline;

public class SitracelProcessRejeterSanction extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub

		BeanMiseaPieds bmp = ProcessControllerDiscipline.getBeanMiseaPieds(getRecord_ID());
		ProcessControllerDiscipline.updateDelaiSanctionApresRejet(bmp, new Timestamp(System.currentTimeMillis()), getRecord_ID(), getAD_User_ID());
		MHRDossierDisciplinaire dossier =ProcessSqlControllerDiscipline.getDossierDisciplinaire(getRecord_ID(), null);
		if(dossier!=null) {
			dossier.delete(false);
			DB.commit(true, dossier.get_TrxName());	
		}
		return null;
	}

}
