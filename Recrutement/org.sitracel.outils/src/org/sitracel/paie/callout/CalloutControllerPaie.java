package org.sitracel.paie.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.model.PO;
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
import org.sitracel.paie.model.MHRTauxSalarial;
import org.sitracel.paie.model.MHRTypeTauxSalarial;
import org.sitracel.paie.process.controller.ProcessSqlControllerPaie;

public class CalloutControllerPaie {
	private static CLogger log = CLogger.getCLogger(PO.class);

	public static MHRElementBasePaieEmploye getElementBaseCalculPaie(
	        MCBPartner bpartner, MHRPeriodeSalariale periodeSalariale, String trxName) {

	    if (bpartner == null || periodeSalariale == null) {
	        return null;
	    }

	    ArrayList<MHRElementBasePaieEmploye> listeElemmentBasePaie =
	            GeneralSqlController.getElementBasePaieEmploye(
	                    bpartner.getC_BPartner_ID(),
	                    periodeSalariale.getDate_Debut_Defaut(),
	                    periodeSalariale.getDate_Fin_Defaut(),
	                    null
	            );

	    ArrayList<MHRGestionPaieEmploye> listeGestionPaieEmployes =
	            GeneralSqlController.getAllGestionPaieEmploye(trxName);

	    if (listeElemmentBasePaie.isEmpty() || listeGestionPaieEmployes.isEmpty()) {
	        return null;
	    }

	    MHRElementBasePaieEmploye elementPaieFinal = new MHRElementBasePaieEmploye(Env.getCtx(), 0, null);
	    elementPaieFinal.setC_BPartner_ID(bpartner.getC_BPartner_ID());
	    elementPaieFinal.setHR_ElementBasePaieEmploye_ID(listeElemmentBasePaie.get(0).getHR_ElementBasePaieEmploye_ID());

	    MHRGestionPresence gestionPresence = new MHRGestionPresence(Env.getCtx(), 0, trxName);
	    MHRGestionPresence gestionPresenceInter = null;

	    for (int i = 0; i < listeElemmentBasePaie.size(); i++) {
	        MHRElementBasePaieEmploye elementBasePaieEmploye = listeElemmentBasePaie.get(i);

	        if (elementBasePaieEmploye == null) continue;

	        MHRTauxSalarial tauxSalarial = new MHRTauxSalarial(Env.getCtx(), elementBasePaieEmploye.getHR_Taux_Salarial_ID(), trxName);
	        MHRTypeTauxSalarial typeTauxSalarial = tauxSalarial.getHR_Type_Taux_Salarial_ID() > 0
	                ? new MHRTypeTauxSalarial(Env.getCtx(), tauxSalarial.getHR_Type_Taux_Salarial_ID(), trxName)
	                : null;

	        // Récupère les présences
	        if (typeTauxSalarial != null && "Horaire".equalsIgnoreCase(typeTauxSalarial.getName())) {
	            gestionPresenceInter = CalloutControllerPaie.buildPresenceHoraire(
	                    bpartner, periodeSalariale, tauxSalarial,
	                    elementBasePaieEmploye.getDate_Debut(), elementBasePaieEmploye.getDate_Fin()
	            );
	        } else {
	            gestionPresenceInter = CalloutControllerPaie.buildPresenceJournalier(
	                    bpartner, periodeSalariale, tauxSalarial,
	                    elementBasePaieEmploye.getDate_Debut(), elementBasePaieEmploye.getDate_Fin()
	            );
	        }

	        CalloutControllerPaie.updateGestionPresence(gestionPresence, gestionPresenceInter);

	        // Calcul des montants
	        for (MHRGestionPaieEmploye gestionPaieEmploye : listeGestionPaieEmployes) {
	            BigDecimal montant = BigDecimal.ZERO;
	            BigDecimal baseValue = (BigDecimal) elementBasePaieEmploye.get_Value(gestionPaieEmploye.getName());
	            if(baseValue==null) {
	            	baseValue = BigDecimal.ZERO;
	            }

	            if (gestionPaieEmploye.isProportionnelTravail()) {
	                montant = CalloutControllerPaie.regleDeTrois(
	                        baseValue,
	                        gestionPresenceInter.getNombre_Heure_Travaille_Max(),
	                        gestionPresenceInter.getNombre_Heure_Travaille(),
	                        2
	                );
	                elementBasePaieEmploye.set_ValueOfColumn(gestionPaieEmploye.getName(), montant);

	                if (elementPaieFinal.get_Value(gestionPaieEmploye.getName()) == null) {
	                    elementPaieFinal.set_ValueOfColumn(gestionPaieEmploye.getName(), BigDecimal.ZERO);
	                }

	                elementPaieFinal.set_ValueOfColumn(
	                        gestionPaieEmploye.getName(),
	                        ((BigDecimal) elementPaieFinal.get_Value(gestionPaieEmploye.getName())).add(montant)
	                );
	            } else if (i == listeElemmentBasePaie.size() - 1) {
	                elementPaieFinal.set_ValueOfColumn(gestionPaieEmploye.getName(), baseValue);
	            }
	        }

	        // Met à jour la dernière valeur
	        if (i == listeElemmentBasePaie.size() - 1) {
	            gestionPresence.setNombre_Jour_Max(gestionPresenceInter.getNombre_Jour_Max());
	            gestionPresence.setNombre_Heure_Travaille_Max(gestionPresenceInter.getNombre_Heure_Travaille_Max());
	        }
	    }

	    try {
	        if (bpartner.getC_BPartner_ID() > 0 && periodeSalariale.getHR_Periode_Salariale_ID() > 0) {
	    		gestionPresence.setC_BPartner_ID(bpartner.getC_BPartner_ID());
	    		gestionPresence.setHR_Periode_Salariale_ID(periodeSalariale.getHR_Periode_Salariale_ID());
	    		gestionPresence.setDate_Debut(periodeSalariale.getDate_Debut_Defaut());
	    		gestionPresence.setDate_Fin(periodeSalariale.getDate_Fin_Defaut());
	    		gestionPresence.setHR_Gestion_Presence_ID(DB.getNextID(Env.getCtx(), MHRGestionPresence.Table_Name, null));
	            gestionPresence.save();
	            DB.commit(true, null);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return elementPaieFinal;
	}
	
	public static MHRGestionPresence buildPresenceJournalier(MCBPartner bpartner, MHRPeriodeSalariale periodeSalariale, MHRTauxSalarial tauxSalarial, Timestamp dateDebutInit, Timestamp dateFinInit) {
		MHRGestionPresence gestionPresenceInter = null;
		if(bpartner!=null && periodeSalariale!=null) {
			gestionPresenceInter = initGestionPresence(bpartner, periodeSalariale, dateDebutInit, dateFinInit);
			
			if(tauxSalarial!=null && tauxSalarial.getValeur_Integer()>0) {
				gestionPresenceInter.setNombre_Jour_Max(tauxSalarial.getValeur_Integer());
			}
			else {
				gestionPresenceInter.setNombre_Jour_Max(GeneralController.getNombreJourTravaille(gestionPresenceInter.getDate_Debut(), gestionPresenceInter.getDate_Fin()));
			}
			int nbJourEffectif = gestionPresenceInter.getNombre_Jour_Max();
			gestionPresenceInter.setNombre_Jour_Conge_Annuel(CalloutControllerPaie.getNombreJourCongeValideByname(bpartner.getC_BPartner_ID(), "Annuel", gestionPresenceInter.getDate_Debut()	, gestionPresenceInter.getDate_Fin()));
			if(periodeSalariale.isCongeAnnuelDeduit()) {
				nbJourEffectif = nbJourEffectif - gestionPresenceInter.getNombre_Jour_Conge_Annuel();
			}
			if(bpartner.getSex()!=null) {
				if(bpartner.getSex().equalsIgnoreCase(MCBPartner.SEX_Femme)) {
					gestionPresenceInter.setNombre_Jour_Conge_Maternite(CalloutControllerPaie.getNombreJourCongeValideByname(bpartner.getC_BPartner_ID(), "Maternité", gestionPresenceInter.getDate_Debut(), gestionPresenceInter.getDate_Fin()));
					if(periodeSalariale.isCongeMatPatlDeduit()) {
						nbJourEffectif = nbJourEffectif - gestionPresenceInter.getNombre_Jour_Conge_Maternite();
					}
				}
				else if(bpartner.getSex().equalsIgnoreCase(MCBPartner.SEX_Homme)) {
					gestionPresenceInter.setNombre_Jour_Conge_Paternite(CalloutControllerPaie.getNombreJourCongeValideByname(bpartner.getC_BPartner_ID(), "Paternité", gestionPresenceInter.getDate_Debut(), gestionPresenceInter.getDate_Fin()));
					if(periodeSalariale.isCongeMatPatlDeduit()) {
						nbJourEffectif = nbJourEffectif - gestionPresenceInter.getNombre_Jour_Conge_Paternite();
					}
				}
			}
			gestionPresenceInter.setNombre_Jour_Suspension(CalloutControllerPaie.getNombreJourSuspensionValideByname(bpartner.getC_BPartner_ID(), gestionPresenceInter.getDate_Debut(), gestionPresenceInter.getDate_Fin()));
			if(periodeSalariale.isSuspensionDeduit()) {
				nbJourEffectif = nbJourEffectif - gestionPresenceInter.getNombre_Jour_Suspension();
			}
			if(bpartner.getDateFrom()!=null) {
				if(bpartner.getDateFrom().after(gestionPresenceInter.getDate_Debut())
						&& bpartner.getDateFrom().before(gestionPresenceInter.getDate_Fin())) {
					gestionPresenceInter.setNombre_Jour_Avant_DebutContrat(GeneralController.getNombreJourTravaille(gestionPresenceInter.getDate_Debut(), bpartner.getDateFrom()));
				}
				else if(bpartner.getDateFrom().before(gestionPresenceInter.getDate_Debut())) {
					gestionPresenceInter.setNombre_Jour_Avant_DebutContrat(0);
				}
				else if(bpartner.getDateFrom().after(gestionPresenceInter.getDate_Fin())) {
					gestionPresenceInter.setNombre_Jour_Avant_DebutContrat(GeneralController.getNombreJourTravaille(gestionPresenceInter.getDate_Debut(), gestionPresenceInter.getDate_Fin()));
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
	

	public static MHRGestionPresence buildPresenceHoraire(MCBPartner bpartner, MHRPeriodeSalariale periodeSalariale, MHRTauxSalarial tauxSalarial, Timestamp dateDebutInit, Timestamp dateFinInit) {
		MHRGestionPresence gestionPresenceInter = null;
		if(bpartner!=null && periodeSalariale!=null) {
			gestionPresenceInter = initGestionPresence(bpartner, periodeSalariale, dateDebutInit, dateFinInit);
			
			if(tauxSalarial!=null && tauxSalarial.getValeur_Integer()>0) {
				gestionPresenceInter.setNombre_Heure_Travaille_Max(tauxSalarial.getValeur_Integer());
			}
			int nbHeureEffectif = gestionPresenceInter.getNombre_Heure_Travaille_Max();
			
			gestionPresenceInter.setNombre_Heure_Travaille(nbHeureEffectif);
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
	 


	 public static MHRGestionPresence updateGestionPresence(MHRGestionPresence gestionPresence, MHRGestionPresence gestionPresenceInter) {
		 if(gestionPresence!=null && gestionPresenceInter!=null) {
			gestionPresence.setNombre_Heure_Travaille(gestionPresenceInter.getNombre_Heure_Travaille()+gestionPresence.getNombre_Heure_Travaille());
			gestionPresence.setNombre_Jour_Avant_DebutContrat(gestionPresenceInter.getNombre_Jour_Avant_DebutContrat()+gestionPresence.getNombre_Jour_Avant_DebutContrat());
			gestionPresence.setNombre_Jour_Conge_Annuel(gestionPresenceInter.getNombre_Jour_Conge_Annuel()+gestionPresence.getNombre_Jour_Conge_Annuel());
			gestionPresence.setNombre_Jour_Conge_Maternite(gestionPresenceInter.getNombre_Jour_Conge_Maternite()+gestionPresence.getNombre_Jour_Conge_Maternite());
			gestionPresence.setNombre_Jour_Conge_Paternite(gestionPresenceInter.getNombre_Jour_Conge_Paternite()+gestionPresence.getNombre_Jour_Conge_Paternite());
			gestionPresence.setNombre_Jour_Suspension(gestionPresenceInter.getNombre_Jour_Suspension()+gestionPresence.getNombre_Jour_Suspension());
			gestionPresence.setNombre_Jour_Effectif(gestionPresenceInter.getNombre_Jour_Effectif()+gestionPresence.getNombre_Jour_Effectif());
		 }
		 
		 return gestionPresence;
	 }
	 
	 private static MHRGestionPresence initGestionPresence(
		        MCBPartner bpartner, MHRPeriodeSalariale periodeSalariale,
		        Timestamp dateDebutInit, Timestamp dateFinInit) {

		    MHRGestionPresence gestionPresence = ProcessSqlControllerPaie.getGestionPresence(
		            bpartner.getC_BPartner_ID(),
		            periodeSalariale.getHR_Periode_Salariale_ID(),
		            null
		    );

		    if (gestionPresence == null) {
		        gestionPresence = new MHRGestionPresence(Env.getCtx(), 0, null);
		        gestionPresence.setC_BPartner_ID(bpartner.getC_BPartner_ID());
		        gestionPresence.setHR_Periode_Salariale_ID(periodeSalariale.getHR_Periode_Salariale_ID());
		        gestionPresence.setHR_Gestion_Presence_ID(DB.getNextID(Env.getCtx(), MHRGestionPresence.Table_Name, null));
		    }

		    Timestamp dateDebut = (dateDebutInit == null)
		            ? new Timestamp(periodeSalariale.getDate_Debut_Defaut().getTime())
		            : new Timestamp(dateDebutInit.getTime());

		    Timestamp dateFin = (dateFinInit == null)
		            ? new Timestamp(periodeSalariale.getDate_Fin_Defaut().getTime())
		            : new Timestamp(dateFinInit.getTime());

		    gestionPresence.setDate_Debut(dateDebut);
		    gestionPresence.setDate_Fin(dateFin);

		    return gestionPresence;
		}

}
