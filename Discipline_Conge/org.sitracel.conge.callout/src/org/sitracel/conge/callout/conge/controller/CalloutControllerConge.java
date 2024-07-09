package org.sitracel.conge.callout.conge.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.callout.bean.BeanConge;
import org.sitracel.callout.bean.BeanIndemniteConge;
import org.sitracel.callout.bean.BeanInfoCongeDepartement;
import org.sitracel.callout.bean.BeanPeriode;
import org.sitracel.callout.bean.BeanPeriodeConge;
import org.sitracel.callout.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.general.controller.GeneralController;
import org.sitracel.general.controller.GeneralSqlController;



public class CalloutControllerConge {
	
	public static BeanPeriodeConge getPeriodeCongeMaternite(Timestamp dateEcheance, Integer idTypeConge) {
		BeanPeriodeConge resultat = BeanFactory.getBeanPeriodeConge();
		if(dateEcheance!=null && idTypeConge!=null) {
			MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), idTypeConge, null);
			int periodeApresEcheance = typeConge.getNombre_Jour_Après_Echeance();
			int periodeAvantEcheance = typeConge.getNombre_Jour_Avant_Echeance();
			Timestamp dateDebut = GeneralController.retirerNombreJour(dateEcheance, periodeAvantEcheance);
			dateDebut = GeneralController.ajusterDebut(dateDebut, dateEcheance);
			Timestamp dateFin = GeneralController.ajouterNombreJour(dateEcheance, periodeApresEcheance);
			dateFin = GeneralController.ajusterFin(dateEcheance, dateFin);
			resultat.setDateDebutConge(dateDebut);
			resultat.setDateFinConge(dateFin);
		}		
		return resultat;		
	}	
	
	public static Integer getNombreJourTravaille(Timestamp dateDebut, Timestamp dateFin) {
		Integer resultat = null;
		if(dateDebut!=null && dateFin!=null) {
			
			Timestamp[] joursFeries = GeneralSqlController.getAllJoursFeries(dateDebut, dateFin, null);
			Calendar cal = Calendar.getInstance();		
			int ferie = 0;
			for (Timestamp jourFerie : joursFeries) {
				cal.setTime(jourFerie);
				if(cal.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY) {
					ferie++;				
				}
			}
			
			Long j1 = TimeUnit.MILLISECONDS.toDays(dateDebut.getTime());
			Long j2 = TimeUnit.MILLISECONDS.toDays(dateFin.getTime());
			resultat = (int) Math.abs(j2-j1+1);
			int sem = resultat / 7;
			int jour = resultat % 7;
			cal  = Calendar.getInstance();
			cal.setTime(dateDebut);
			int jourb = jour; 
			for (int i = 0; i <= jourb; i++) {
				cal.setTime(dateDebut);
				cal.add(Calendar.DAY_OF_WEEK, i);
				if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					jour = jour-1;
				}
			}
			resultat = (sem*6) + jour - ferie;
		}		
		return resultat;
	}
	
	public static Integer getNombreJourCongeTotalEmploye(Integer idCBPartner, Integer idTypeConge) {
		Integer resultat =null;
		if(idCBPartner!=null && idTypeConge!=null) {
			resultat = 0;
			BeanPeriode[] conges = GeneralSqlController.getAllCongesAnnuel(idCBPartner , new Timestamp(System.currentTimeMillis()), null);
			for (BeanPeriode beanPeriode : conges) {
				resultat = resultat + getNombreJourTravaille(beanPeriode.getDateDebutConge(), beanPeriode.getDateFinConge());
			}
		}
		return resultat;		
	}		
	
	public static Integer getDiffAnnee(Timestamp t1, Timestamp t2) {
			Integer resultat = null;
			if(t1!=null && t2!=null) {
				  Calendar cal1 = Calendar.getInstance();
		            cal1.setTime(t1);
		            Calendar cal2 = Calendar.getInstance();
		            cal2.setTime(t2);
		            resultat = cal1.get(Calendar.YEAR)-cal2.get(Calendar.YEAR);   
		            if ((cal1.get(Calendar.MONTH) < cal2.get(Calendar.MONTH))
		                            || ((cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) && (cal1.get(Calendar.DAY_OF_MONTH) < cal2
		                                            .get(Calendar.DAY_OF_MONTH)))) {
		                    --resultat;
		            }
		            if(resultat < 0) {
	                    throw new IllegalArgumentException("différence < 0");
		            }
			}
			return resultat;
	}
	
	public static BeanPeriode isDejaPris(Integer idEmploye, Timestamp dateDebut, Timestamp dateFin) {
		BeanPeriode resultat = null;
		BeanPeriode[] bp = GeneralSqlController.getAllConges(idEmploye, null);
		if(bp!=null) {
			int i = 0;
			while(i < bp.length && resultat==null) {
				if(GeneralController.seChevauche(dateDebut, dateFin, bp[i].getDateDebutConge(), bp[i].getDateFinConge())) {
					resultat = bp[i];
				}
				i++;
			}
		}
		return resultat;
	}
	
	public static BeanInfoCongeDepartement getPeriodeCongeCritique(Integer cbpartnerid, Timestamp dateDebut, Timestamp dateFin) {
		BeanInfoCongeDepartement beanInfoCongeDepartement = BeanFactory.getBeanInfoCongeDepartement();
		if(cbpartnerid!=null && dateDebut!=null && dateFin!=null) {
			BeanPeriode[] tableauCongeDepartement = CalloutSqlControllerConge.getAllCongesDepFromCBPartnerID(dateDebut, dateFin, cbpartnerid, null);
			if(tableauCongeDepartement!=null) {
				Timestamp jourConge = dateDebut;
				Timestamp jourCritique = dateDebut;
				int nombreEmployeConge = 0;
				int nombreEmployeCongeMax = 0;
				while(jourConge.before(dateFin)) {
					if(!GeneralController.isJourFerie(jourConge)) {
						for(BeanPeriode congeDepartement : tableauCongeDepartement) {
							if(jourConge.after(congeDepartement.getDateDebutConge()) && jourConge.before(congeDepartement.getDateFinConge())) {
								nombreEmployeConge++;
							}
						}
						if(nombreEmployeConge>nombreEmployeCongeMax) {
							nombreEmployeCongeMax=nombreEmployeConge;
							jourCritique=jourConge;
						}
					}
					jourConge=GeneralController.ajouterNombreJour(jourConge, 1);
				}
				beanInfoCongeDepartement.setNombreEmployeDepartement(CalloutSqlControllerConge.getNumberEmployeDepartment(cbpartnerid));
				beanInfoCongeDepartement.setJourCritique(jourCritique);
				beanInfoCongeDepartement.setNombreEmployeDepartementConge(nombreEmployeCongeMax);
				beanInfoCongeDepartement.setPourcentageEmployeConge((nombreEmployeCongeMax/nombreEmployeConge)*100);
			}
		}
		return beanInfoCongeDepartement;
	}		
	
	public static String getAmpliation(String ampliation, String initial, String annee) {
		if(ampliation!=null && initial!=null) {
			ampliation = ampliation.replaceAll("\\s+", "");
			if(ampliation.length()>2) {
				ampliation = ampliation.replaceAll("/"+ampliation.substring(ampliation.length()-2), ""); 
			}
			if(!ampliation.isEmpty()) {
				if(ampliation.contains("/"+initial)){
					ampliation = ampliation.replaceAll("/"+initial, "");
				}
				else {
					ampliation = ampliation+"/"+initial;
				}
			}
			else {
				ampliation = ampliation+"/"+initial;
			}
			if(annee.length()>2) {
				ampliation = ampliation + "/"+annee.substring(annee.length()-2);
			}
		}
		return ampliation;
	}
		
	public static BeanConge compenserAbsenceConge(Integer idConge, boolean aCompenserDebut) {
		BeanConge resultat = BeanFactory.getBeanConge();
		if(idConge!=null) {
			MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);
			if(conge!=null) {
				if(aCompenserDebut) {
					Timestamp dateCompense = GeneralController.ajouterNombreJour(conge.getDate_Debut_Effective(), conge.getJours_Conge_A_Compenser());
					dateCompense = GeneralController.ajusterFin(conge.getDate_Debut_Effective(), dateCompense);
					resultat.setDateDebutDernierConge(dateCompense);
					resultat.setDateFindernierConge(conge.getDate_Fin_Effective());
				}
				else {
					Timestamp dateCompense = GeneralController.retirerNombreJour(conge.getDate_Fin_Effective(), conge.getJours_Conge_A_Compenser());
					dateCompense = GeneralController.ajusterDebut(dateCompense, conge.getDate_Fin_Effective());
					resultat.setDateDebutDernierConge(conge.getDate_Debut_Effective());
					resultat.setDateFindernierConge(dateCompense);
				}
			}
		}
		return resultat;
	}		
	
	public static BeanIndemniteConge getIndemniteConge(Integer idConge) {
		BeanIndemniteConge resultat = BeanFactory.getBeanIndemniteConge();
		if(idConge!=null) {
			MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);
			if(conge!=null) {
				resultat.setDateDebutConge(conge.getDate_Dernier_Conge());
				resultat.setDateFinConge(conge.getDate_Debut_Effective());
				String sqlf="select adempiere.getamtconge(?,?,?)";
				
				List<Object> param =new ArrayList<Object>();
				param.add(conge.getC_BPartner_ID());
				param.add(conge.getDate_Dernier_Conge());
				param.add(conge.getDate_Debut_Effective());
				
				BigDecimal emlmt=DB.getSQLValueBD(null, sqlf,param);				

				resultat.setIndemnite(emlmt);
			}
		}
		return resultat;
	}	
}
