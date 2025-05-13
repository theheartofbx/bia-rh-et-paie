package org.sitracel.paie.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.model.MCBPartner;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRGestionPaieEmploye;
import org.sitracel.paie.model.MHRGestionPresence;
import org.sitracel.paie.model.MHRPeriodeSalariale;
import org.sitracel.paie.process.controller.ProcessSqlControllerPaie;

public class CalloutControllerPaie {

	public static MHRElementBasePaieEmploye getElementBaseCalculPaie(MCBPartner bpartner, MHRPeriodeSalariale periodeSalariale, String trxName) {
		MHRElementBasePaieEmploye elementPaieFinal = null;
		if(bpartner!=null && periodeSalariale!=null) {
			if(bpartner!=null && periodeSalariale!=null) {
				MHRGestionPresence gestionPresence = CalloutControllerPaie.getInfoPresence(bpartner, periodeSalariale, null, null);
				try {
					if(bpartner.getC_BPartner_ID()>0 && periodeSalariale.getHR_Periode_Salariale_ID()>0) {
						gestionPresence.save();
						DB.commit(true, null);
					}
				} catch (IllegalStateException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ArrayList<MHRElementBasePaieEmploye> listeElemmentBasePaie = GeneralSqlController.getElementBasePaieEmploye(bpartner.getC_BPartner_ID(), periodeSalariale.getDate_Debut_Defaut(), periodeSalariale.getDate_Fin_Defaut(), null);
				ArrayList<MHRGestionPaieEmploye> listeGestionPaieEmployes = GeneralSqlController.getAllGestionPaieEmploye(trxName);
				if(!listeElemmentBasePaie.isEmpty() && !listeGestionPaieEmployes.isEmpty()) {
					elementPaieFinal = new MHRElementBasePaieEmploye(Env.getCtx(), 0, null);
					elementPaieFinal.setC_BPartner_ID(bpartner.getC_BPartner_ID());
					elementPaieFinal.setHR_ElementBasePaieEmploye_ID(listeElemmentBasePaie.get(0).getHR_ElementBasePaieEmploye_ID());
					BigDecimal montant = BigDecimal.ZERO;
					for(MHRGestionPaieEmploye gestionPaieEmploye :listeGestionPaieEmployes) {
						elementPaieFinal.set_ValueOfColumn(gestionPaieEmploye.getName(), BigDecimal.ZERO);
					}
					for(MHRElementBasePaieEmploye elementBasePaieEmploye : listeElemmentBasePaie) {
						elementBasePaieEmploye = CalloutControllerPaie.getElementBaseCalculPaieIntermediaire(bpartner, periodeSalariale, gestionPresence, elementBasePaieEmploye);
						for(MHRGestionPaieEmploye gestionPaieEmploye : listeGestionPaieEmployes) {
							montant = ((BigDecimal)elementPaieFinal.get_Value(gestionPaieEmploye.getName())).add((BigDecimal)elementBasePaieEmploye.get_Value(gestionPaieEmploye.getName()));
							elementPaieFinal.set_ValueOfColumn(gestionPaieEmploye.getName(), montant);
						}
					}
				}
			}
		}
		return elementPaieFinal;
	}

	public static MHRElementBasePaieEmploye getElementBaseCalculPaieIntermediaire(MCBPartner bpartner, MHRPeriodeSalariale periodeSalariale, 
			MHRGestionPresence gestionPresence, MHRElementBasePaieEmploye elementBasePaieEmployeInit) {
		MHRElementBasePaieEmploye elementBasePaieEmploye = new MHRElementBasePaieEmploye(Env.getCtx(), 0, null);
		if(bpartner!=null && periodeSalariale!=null && gestionPresence!=null && elementBasePaieEmployeInit!=null) {
			MHRGestionPresence gestionPresenceInter = CalloutControllerPaie.getInfoPresence(bpartner, periodeSalariale, 
					elementBasePaieEmployeInit.getDate_Debut(), elementBasePaieEmployeInit.getDate_Fin());
			ArrayList <MHRGestionPaieEmploye>  listeGestionPaieEmploye = GeneralSqlController.getAllGestionPaieEmploye(null);
			BigDecimal montant = BigDecimal.ZERO;
			if(gestionPresenceInter!=null && !listeGestionPaieEmploye.isEmpty()) {
				for(MHRGestionPaieEmploye gestionPaieEmploye : listeGestionPaieEmploye) {
					montant = CalloutControllerPaie.regleDeTrois((BigDecimal)elementBasePaieEmployeInit.get_Value(gestionPaieEmploye.getName()), gestionPresence.getNombre_Jour_Max(), gestionPresenceInter.getNombre_Jour_Effectif(), 2);
					elementBasePaieEmploye.set_ValueOfColumn(gestionPaieEmploye.getName(), montant);
				}
			}
		}
		return elementBasePaieEmploye;
	}

	public static MHRGestionPresence getInfoPresence(MCBPartner bpartner, MHRPeriodeSalariale periodeSalariale, Timestamp dateDebutInit, Timestamp dateFinInit) {
		MHRGestionPresence gestionPresenceInter = null;
		if(bpartner!=null && periodeSalariale!=null) {
			gestionPresenceInter = ProcessSqlControllerPaie.getGestionPresence(bpartner.getC_BPartner_ID(), periodeSalariale.getHR_Periode_Salariale_ID(), null);
			if(gestionPresenceInter==null) {
				gestionPresenceInter = new MHRGestionPresence(Env.getCtx(), 0, null);
				gestionPresenceInter.setC_BPartner_ID(bpartner.getC_BPartner_ID());
				gestionPresenceInter.setHR_Periode_Salariale_ID(periodeSalariale.getHR_Periode_Salariale_ID());
				gestionPresenceInter.setHR_Gestion_Presence_ID(DB.getNextID(Env.getCtx(), MHRGestionPresence.Table_Name, null));			
			}
			Timestamp dateDebut = null;
			Timestamp dateFin = null;
			if(dateDebutInit==null) {
				dateDebut = new Timestamp(periodeSalariale.getDate_Debut_Defaut().getTime());
			}
			else {
				dateDebut = new Timestamp(dateDebutInit.getTime());
			}
			if(dateFinInit==null) {
				dateFin = new Timestamp(periodeSalariale.getDate_Fin_Defaut().getTime());
			}
			else {
				dateFin = new Timestamp(dateFinInit.getTime());
			}
			gestionPresenceInter.setDate_Debut(dateDebut);
			gestionPresenceInter.setDate_Fin(dateFin);
			gestionPresenceInter.setNombre_Jour_Max(GeneralController.getNombreJourTravaille(dateDebut, dateFin));
			int nbJourEffectif = gestionPresenceInter.getNombre_Jour_Max();
			gestionPresenceInter.setNombre_Jour_Conge_Annuel(CalloutControllerPaie.getNombreJourCongeValideByname(bpartner.getC_BPartner_ID(), "Annuel", dateDebut, dateFin));
			if(periodeSalariale.isCongeAnnuelDeduit()) {
				nbJourEffectif = nbJourEffectif - gestionPresenceInter.getNombre_Jour_Conge_Annuel();
			}
			if(bpartner.getSex()!=null) {
				if(bpartner.getSex().equalsIgnoreCase(MCBPartner.SEX_Femme)) {
					gestionPresenceInter.setNombre_Jour_Conge_Maternite(CalloutControllerPaie.getNombreJourCongeValideByname(bpartner.getC_BPartner_ID(), "Maternité", dateDebut, dateFin));
					if(periodeSalariale.isCongeMatPatlDeduit()) {
						nbJourEffectif = nbJourEffectif - gestionPresenceInter.getNombre_Jour_Conge_Maternite();
					}
				}
				else if(bpartner.getSex().equalsIgnoreCase(MCBPartner.SEX_Homme)) {
					gestionPresenceInter.setNombre_Jour_Conge_Paternite(CalloutControllerPaie.getNombreJourCongeValideByname(bpartner.getC_BPartner_ID(), "Paternité", dateDebut, dateFin));
					if(periodeSalariale.isCongeMatPatlDeduit()) {
						nbJourEffectif = nbJourEffectif - gestionPresenceInter.getNombre_Jour_Conge_Paternite();
					}
				}
			}
			gestionPresenceInter.setNombre_Jour_Suspension(CalloutControllerPaie.getNombreJourSuspensionValideByname(bpartner.getC_BPartner_ID(), dateDebut, dateFin));
			if(periodeSalariale.isSuspensionDeduit()) {
				nbJourEffectif = nbJourEffectif - gestionPresenceInter.getNombre_Jour_Suspension();
			}
			if(bpartner.getDateFrom()!=null) {
				if(bpartner.getDateFrom().after(dateDebut)
						&& bpartner.getDateFrom().before(dateFin)) {
					gestionPresenceInter.setNombre_Jour_Avant_DebutContrat(GeneralController.getNombreJourTravaille(dateDebut, bpartner.getDateFrom()));
				}
				else if(bpartner.getDateFrom().before(dateDebut)) {
					gestionPresenceInter.setNombre_Jour_Avant_DebutContrat(0);
				}
				else if(bpartner.getDateFrom().after(dateFin)) {
					gestionPresenceInter.setNombre_Jour_Avant_DebutContrat(GeneralController.getNombreJourTravaille(dateDebut, dateFin));
				}
			}
			else {
				gestionPresenceInter.setNombre_Jour_Avant_DebutContrat(0);
			}
			if(periodeSalariale.isAvantDebutContratDeduit()) {
				nbJourEffectif = nbJourEffectif - gestionPresenceInter.getNombre_Jour_Avant_DebutContrat();
			}
			gestionPresenceInter.setNombre_Jour_Effectif(nbJourEffectif);
		}
		return gestionPresenceInter;
	}
	
	public static int getNombreJourCongeValideByname(Integer bpartnerID, String nomConge, Timestamp dateDebut, Timestamp dateFin) {
		int resultat = 0;
		if(bpartnerID!=null && nomConge!=null && dateDebut!=null && dateFin!=null) {
			MCBPartner bpartner = new MCBPartner(Env.getCtx(), bpartnerID, null);
			if(bpartner!=null) {
				BeanPeriode[] conges = GeneralSqlController.getCongesValidebyNameConge(bpartnerID, nomConge, dateDebut, dateFin, nomConge);
				if(conges!=null) {
					for(BeanPeriode conge:conges) {
						if(conge.getDateDebutConge().after(dateDebut) && conge.getDateFinConge().before(dateFin)) {
							resultat = resultat + GeneralController.getNombreJourTravaille(conge.getDateDebutConge(), conge.getDateFinConge());
						}
						else if(conge.getDateDebutConge().before(dateDebut) && conge.getDateFinConge().after(dateFin)) {
							resultat = resultat + GeneralController.getNombreJourTravaille(dateDebut, dateFin);
						}
						else if(conge.getDateDebutConge().before(dateDebut) && conge.getDateFinConge().after(dateDebut) 
								&& conge.getDateFinConge().before(dateFin)) {
							resultat = resultat + GeneralController.getNombreJourTravaille(dateDebut, conge.getDateFinConge());
						}
						else if(conge.getDateFinConge().after(dateFin) && conge.getDateDebutConge().after(dateDebut)
								&& conge.getDateDebutConge().before(dateFin)) {
							resultat = resultat + GeneralController.getNombreJourTravaille(conge.getDateDebutConge(), dateFin);
						}
					}
				}
			}
		}
		return resultat;
	}
	
	public static int getNombreJourSuspensionValideByname(Integer bpartnerID, Timestamp dateDebut, Timestamp dateFin) {
		int resultat = 0;
		if(bpartnerID!=null && dateDebut!=null && dateFin!=null) {
			MCBPartner bpartner = new MCBPartner(Env.getCtx(), bpartnerID, null);
			if(bpartner!=null) {
				BeanPeriode[] suspensions = GeneralSqlController.getAllPeriodeSuspensionValide(bpartnerID, dateDebut, dateFin, null);
				if(suspensions!=null) {
					for(BeanPeriode suspension:suspensions) {
						if(suspension.getDateDebutConge().after(dateDebut) && suspension.getDateFinConge().before(dateFin)) {
							resultat = resultat + GeneralController.getNombreJourTravaille(suspension.getDateDebutConge(), suspension.getDateFinConge());
						}
						else if(suspension.getDateDebutConge().before(dateDebut) && suspension.getDateFinConge().after(dateFin)) {
							resultat = resultat + GeneralController.getNombreJourTravaille(dateDebut, dateFin);
						}
						else if(suspension.getDateDebutConge().before(dateDebut) && suspension.getDateFinConge().after(dateDebut) 
								&& suspension.getDateFinConge().before(dateFin)) {
							resultat = resultat + GeneralController.getNombreJourTravaille(dateDebut, suspension.getDateFinConge());
						}
						else if(suspension.getDateFinConge().after(dateFin) && suspension.getDateDebutConge().after(dateDebut)
								&& suspension.getDateDebutConge().before(dateFin)) {
							resultat = resultat + GeneralController.getNombreJourTravaille(suspension.getDateDebutConge(), dateFin);
						}
					}
				}
			}
		}
		return resultat;
	}
	
	 public static BigDecimal regleDeTrois(BigDecimal a, Integer b, Integer c, int scale) {
	        // Vérifie les valeurs nulles ou zéro
	        if (a == null || a.compareTo(BigDecimal.ZERO) == 0 || b == null || b == 0 || c == null || c == 0) {
	            return BigDecimal.ZERO;
	        }
	        
	        BigDecimal bDecimal = BigDecimal.valueOf(b);
	        BigDecimal cDecimal = BigDecimal.valueOf(c);
	        BigDecimal resultat = a.multiply(cDecimal).divide(bDecimal, scale, RoundingMode.HALF_UP);
	        return resultat;
	    }

}
