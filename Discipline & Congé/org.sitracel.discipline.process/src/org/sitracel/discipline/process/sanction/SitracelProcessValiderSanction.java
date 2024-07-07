package org.sitracel.discipline.process.sanction;

import java.sql.Timestamp;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.callout.bean.BeanMiseaPieds;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;
import org.sitracel.discipline.process.sanction.controller.ProcessControllerDiscipline;
import org.sitracel.discipline.process.sanction.controller.ProcessSqlControllerDiscipline;

public class SitracelProcessValiderSanction extends SvrProcess{

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		MHRPunishment punishment = new MHRPunishment(Env.getCtx(), getRecord_ID(), null);
		if(punishment!=null ) {
			BeanMiseaPieds bmp = ProcessControllerDiscipline.getBeanMiseaPieds(getRecord_ID());
			ProcessControllerDiscipline.updateDelaiSanctionApresValidation(bmp, new Timestamp(System.currentTimeMillis()), getRecord_ID(), getAD_User_ID());
			punishment = new MHRPunishment(Env.getCtx(), getRecord_ID(), null);	
			MHRDossierDisciplinaire dossier =ProcessSqlControllerDiscipline.getDossierDisciplinaire(getRecord_ID(), null);
			if(dossier==null) {
				dossier = new MHRDossierDisciplinaire(Env.getCtx(), null, null);
				dossier.setC_BPartner_ID(punishment.getC_BPartner_ID());
				dossier.setPoste_Employe_ID(punishment.getPoste_Employe_ID());
				dossier.setMatricule_Employe(punishment.getMatricule_Employe());
				dossier.setValide_Rejete_Par_Nom_ID(punishment.getValide_Rejete_Par_Nom_ID());
				dossier.setValide_Rejete_Par_Poste_ID(punishment.getValide_Rejete_Par_Poste_ID());
				dossier.setValide_Rejete_Par_Matricule(punishment.getValide_Rejete_Par_Matricule());
				dossier.setHR_Punishment_ID(punishment.getHR_Punishment_ID());
				dossier.setMotif(punishment.getMotif_Demande_Explication());
				MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(Env.getCtx(), punishment.getEmission_Sanction_ID(), null);
				if(autorisation!=null) {
					MHRTypeSanction typeSanction = new MHRTypeSanction(Env.getCtx(), autorisation.getHR_TypeSanction_ID(), null);
					if(typeSanction!=null) {
						dossier.setHR_TypeSanction_ID(typeSanction.getHR_TypeSanction_ID());
					}
				}
				dossier.setDate_Emission(punishment.getDate_Emission());
				dossier.setDate_Validation(punishment.getDate_Validation());
				dossier.save();
				DB.commit(true, dossier.get_TrxName());	
			}				
		}	
		return null;
	}

}
