package org.sitracel.conge.process.conge.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.bean.BeanNotificationConge;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.callout.absence.controller.CalloutSqlControllerAbsence;
import org.sitracel.conge.model.I_HR_Type_Absence;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAutorisationConge;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.model.MCBPartner;

public class ProcessControllerConge {
	private static String expediteur = "groupe.sitracel@gmail.com";
	private static String mdp = "vzccxxisbuazhhys";

	public static void validerConge(Integer idConge, Integer adUserID) {
		if(idConge!=null && adUserID!=null) {
			BeanIdentifiant beanIdentifiant = MCBPartner.getIdentifiant(adUserID, null);
			MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);
			if(conge!=null && beanIdentifiant!=null) {
				if(beanIdentifiant.getNomEmploye()!=null) {
					MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), conge.getEmission_Conge_ID(), null);
					int detteConge = GeneralSqlController.getNombreJourAbsencesCongeNonTraite(conge.getDate_Debut_Souhaitee(), null);
					if(typeConge!=null) {
						if(typeConge.isCongeAnnuel()) {
							if(conge.getJours_Conge_Total()-conge.getJours_Conge_Correspondant()-conge.getJours_Conge_Deja_Utilise()-detteConge<0) {
								conge.setMessage_Alerte("Attention la période de congé dépasse les droits de l'employé");
								conge.setIsMessageAlerteDisplayed(true);
								conge.save();
							}
							else {
								conge.setJours_Conge_A_Compenser(detteConge);
								conge.save();
							}
						}
					}
					conge.setValide_Rejete_Par_Nom_ID(beanIdentifiant.getNumEmploye());
					conge.setValide_Rejete_Par_Matricule(beanIdentifiant.getMatriculeEmploye());
					conge.setValide_Rejete_Par_Poste_ID(beanIdentifiant.getNumeroPoste());
					conge.setIsTraitee(true);
					conge.setIsValidee(true);
					conge.setIsRejetee(false);
					conge.setDate_Validation(new Timestamp(System.currentTimeMillis()));
					conge.setDate_Rejet(null);
					conge.save(null);
					ProcessControllerConge.gererAbsenceApresValidationConge(conge, beanIdentifiant);
				}
			}
		}
	}

	public static void rejeterConge(Integer idConge, Integer adUserID) {
		if(idConge!=null && adUserID!=null) {
			BeanIdentifiant beanIdentifiant = MCBPartner.getIdentifiant(adUserID, null);
			MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);
			if(conge!=null && beanIdentifiant!=null) {
				if(beanIdentifiant.getNomEmploye()!=null) {
					MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), conge.getEmission_Conge_ID(), null);
					int detteConge = GeneralSqlController.getNombreJourAbsencesCongeNonTraite(conge.getDate_Debut_Souhaitee(), null);
					if(typeConge!=null) {
						if(typeConge.isCongeAnnuel()) {
							if(conge.getJours_Conge_Total()-conge.getJours_Conge_Correspondant()-conge.getJours_Conge_Deja_Utilise()-detteConge<0) {
								conge.setMessage_Alerte("Attention la période de congé dépasse les droits de l'employé");
								conge.setIsMessageAlerteDisplayed(true);
								conge.save();
							}
						}
					}
					conge.setValide_Rejete_Par_Nom_ID(beanIdentifiant.getNumEmploye());
					conge.setValide_Rejete_Par_Matricule(beanIdentifiant.getMatriculeEmploye());
					conge.setValide_Rejete_Par_Poste_ID(beanIdentifiant.getNumeroPoste());
					conge.setIsTraitee(true);
					conge.setIsValidee(false);
					conge.setIsRejetee(true);
					conge.setDate_Validation(null);
					conge.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
					conge.save(null);
					ProcessControllerConge.gererAbsenceApresRejetSanction(conge, beanIdentifiant);
				}
			}
		}
	}

	public static void approuverConge(Integer idConge, Integer adUserID) {
		if(idConge!=null && adUserID!=null) {
			BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
			MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);
			if(conge!=null && bi!=null) {
				if(bi.getNomEmploye()!=null) {
					MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), conge.getEmission_Conge_ID(), null);
					int detteConge = GeneralSqlController.getNombreJourAbsencesCongeNonTraite(conge.getDate_Debut_Souhaitee(), null);
					if(typeConge!=null) {
						if(typeConge.isCongeAnnuel()) {
							if(conge.getJours_Conge_Total()-conge.getJours_Conge_Correspondant()-conge.getJours_Conge_Deja_Utilise()-detteConge<0) {
								conge.setMessage_Alerte("Attention la période de congé dépasse les droits de l'employé");
								conge.setIsMessageAlerteDisplayed(true);
								conge.save();
							}
						}
					}
					conge.setApprouve_Desapprouve_Nom_ID(bi.getNumEmploye());
					conge.setApprouve_Desapprouve_Matricule(bi.getMatriculeEmploye());
					conge.setApprouve_Desapprouve_Poste_ID(bi.getNumeroPoste());
					conge.setIsApprouve(true);
					conge.setIsDesapprouve(false);
					conge.setDate_Approbation(new Timestamp(System.currentTimeMillis()));
					conge.save(null);
				}
			}
		}
	}

	public static void desapprouverConge(Integer idConge, Integer adUserID) {
		if(idConge!=null && adUserID!=null) {
			BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
			MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);
			if(conge!=null && bi!=null) {
				if(bi.getNomEmploye()!=null) {
					MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), conge.getEmission_Conge_ID(), null);
					int detteConge = GeneralSqlController.getNombreJourAbsencesCongeNonTraite(conge.getDate_Debut_Souhaitee(), null);
					if(typeConge!=null) {
						if(typeConge.isCongeAnnuel()) {
							if(conge.getJours_Conge_Total()-conge.getJours_Conge_Correspondant()-conge.getJours_Conge_Deja_Utilise()-detteConge<0) {
								conge.setMessage_Alerte("Attention la période de congé dépasse les droits de l'employé");
								conge.setIsMessageAlerteDisplayed(true);
								conge.save();
							}
						}
					}
					conge.setApprouve_Desapprouve_Nom_ID(bi.getNumEmploye());
					conge.setApprouve_Desapprouve_Matricule(bi.getMatriculeEmploye());
					conge.setApprouve_Desapprouve_Poste_ID(bi.getNumeroPoste());
					conge.setIsApprouve(false);
					conge.setIsDesapprouve(true);
					conge.setDate_Approbation(new Timestamp(System.currentTimeMillis()));
					conge.save(null);
				}
			}
		}
	}

	public static BeanNotificationConge getBeanNotificationConge(Integer idConge) {
		BeanNotificationConge resultat = BeanFactory.getBeanNotificationConge();

		if(idConge!=null) {
			MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);
			if(conge!=null) {
				MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), conge.getEmission_Conge_ID(), null);
				if(conge!=null) {
					MCBPartner employe = new MCBPartner(Env.getCtx(), conge.getC_BPartner_ID(), null);

					resultat.setPosteValidateur(conge.getValide_Rejete_Par_Poste().getName());
					resultat.setPosteEmetteur(conge.getEmis_Par_Poste().getName());
					resultat.setPosteEmploye(conge.getPoste_Employe().getName());

					resultat.setDateEmission(conge.getDate_Emission());
					resultat.setDateValidation(conge.getDate_Validation());
					resultat.setDateRejet(conge.getDate_Rejet());
					resultat.setDateApprobation(conge.getDate_Approbation());
					resultat.setDateDesapprobation(conge.getDate_Desapprobation());
					resultat.setDateDebutAjustee(conge.getDate_Debut_Ajustee());
					resultat.setDateFinAjustee(conge.getDate_Fin_Ajustee());
					if(typeConge!=null) {
						resultat.setTypeConge(typeConge.getNom_Conge());
					}
					if(employe!=null) {
						resultat.setMailEmploye(employe.getEMail());
						if(employe.getName()!=null) {
							resultat.setNomEmploye(employe.getName());
						}
						else {
							resultat.setNomEmploye("");
						}
						if(employe.getName2()!=null) {
							resultat.setNomEmploye(resultat.getNomEmploye()+" "+employe.getName2());
						}
						MCBPartner emetteur = new MCBPartner(Env.getCtx(), conge.getEmis_Par_Nom_ID(), null);
						MCBPartner validateur = new MCBPartner(Env.getCtx(), conge.getValide_Rejete_Par_Nom_ID(), null);
						if(emetteur!=null) {
							if(emetteur.getName()!=null) {
								resultat.setNomEmetteur(emetteur.getName());
							}
							else {
								resultat.setNomEmetteur("");
							}
							if(emetteur.getName2()!=null) {
								resultat.setNomEmetteur(resultat.getNomEmetteur()+" "+emetteur.getName2());
							}
							resultat.setMailEmetteur(emetteur.getEMail());
						}
						if(validateur!=null) {
							if(validateur.getName()!=null) {
								resultat.setNomValidateur(validateur.getName());
							}
							else {
								resultat.setNomValidateur("");
							}
							if(validateur.getName2()!=null) {
								resultat.setNomValidateur(resultat.getNomValidateur()+" "+validateur.getName2());
							}
							resultat.setMailValidateur(validateur.getEMail());
						}
					}
				}
			}
		}

		return resultat;

	}


	public static void updateAbsenceConge(Integer idConge) {
		if(idConge!=null) {
			MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);
			if(conge!=null) {
				if(!conge.isValidee() && !conge.isRejetee() && conge.getDate_Debut_Effective().after(new Timestamp(System.currentTimeMillis()))) {
					conge.setJours_Conge_A_Compenser(GeneralSqlController.getNombreJourAbsencesCongeNonTraite(conge.getDate_Debut_Souhaitee(),null));
					conge.save();
				}
			}
		}
	}


	private static void gererAbsenceApresValidationConge(MHRHoliday holiday, BeanIdentifiant beanIdentifiant) {
		if(holiday!=null && beanIdentifiant!=null) {
			MHRAutorisationConge autorisation = new MHRAutorisationConge(Env.getCtx(), holiday.getEmission_Conge_ID(), null);
			if(autorisation!=null) {
				MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), autorisation.getHR_Type_Conge_ID(), null);
				if(typeConge!=null) {
					Timestamp dateDebutConge = holiday.getDate_Debut_Effective();
					Timestamp dateFinConge = holiday.getDate_Fin_Effective();
					Integer typeAbsenceID = GeneralSqlController.getIDFromTableNameAndName(I_HR_Type_Absence.COLUMNNAME_HR_Type_Absence_ID,
							I_HR_Type_Absence.Table_Name, I_HR_Type_Absence.COLUMNNAME_Nom_Absence, "En Congé", null);
					if(typeAbsenceID!=null) {
						while(dateDebutConge.before(dateFinConge)) {
							Calendar cal = Calendar.getInstance();
							cal.setTime(dateDebutConge);
							if(cal.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY || !MHRPublicHoliday.isJourFerie(dateDebutConge,null)) {
								MHRAbsence absence = new MHRAbsence(Env.getCtx(), null, null);
								absence.setEmis_Par_Nom_ID(beanIdentifiant.getNumEmploye());
								absence.setEmis_Par_Poste_ID(beanIdentifiant.getNumeroPoste());
								absence.setEmis_Par_Matricule(beanIdentifiant.getMatriculeEmploye());
								absence.setDate_Absence(dateDebutConge);
								absence.setDate_Emission(new Timestamp(System.currentTimeMillis()));
								absence.setHR_Type_Absence_ID(typeAbsenceID);
								absence.setIsDemandeExplication(false);
								absence.setIsConge(false);
								absence.setIsDemandeExplicationTraite(true);
								absence.setIsCongeTraite(true);
								absence.save(null);
							}
							dateDebutConge=GeneralController.ajouterNombreJour(dateDebutConge, 1);
						}
					}
				}
			}
		}
	}

	private static void gererAbsenceApresRejetSanction(MHRHoliday holiday, BeanIdentifiant beanIdentifiant) {
		if(holiday!=null && beanIdentifiant!=null) {
			MHRAutorisationConge autorisation = new MHRAutorisationConge(Env.getCtx(), holiday.getEmission_Conge_ID(), null);
			if(autorisation!=null) {
				MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), autorisation.getHR_Type_Conge_ID(), null);
				if(typeConge!=null) {
					ArrayList<Integer> listeAbsenceID = CalloutSqlControllerAbsence.getListeAbsenceIDByName(holiday.getC_BPartner_ID(), "En Congé", holiday.getDate_Debut_Effective(), holiday.getDate_Fin_Effective(), null);
					for(Integer absenceID:listeAbsenceID) {
						MHRAbsence absence = new MHRAbsence(Env.getCtx(), absenceID,null);
						if(absence!=null) {
							absence.delete(true);
						}
					}
				}
			}
		}
	}

	private static String getMessageNotificationConge(Integer idConge) {
		String resultat = "";
		if(idConge!=null) {
			MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);
			if(conge!=null) {
				MCBPartner employe = new MCBPartner(Env.getCtx(), conge.getC_BPartner_ID(), null);
				MCBPartner approbateur = new MCBPartner(Env.getCtx(), conge.getApprouve_Desapprouve_Nom_ID(), null);
				String nameEmploye = employe.getName();
				MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), conge.getEmission_Conge_ID(), null);
				if(employe.getName2()!=null) {
					nameEmploye = nameEmploye+" "+employe.getName2();
				}
				String nameApprobateur = approbateur.getName();
				if(approbateur.getName2()!=null) {
					nameApprobateur = nameApprobateur+" "+approbateur.getName2();
				}
				String dateEmission="";
				if(conge.getDate_Emission()!=null) {
					dateEmission = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.FRENCH).format(conge.getDate_Emission());
				}
				String dateDebutSouhaitee="";
				if(conge.getDate_Debut_Souhaitee()!=null) {
					dateDebutSouhaitee = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.FRENCH).format(conge.getDate_Debut_Souhaitee());
				}
				String dateFinSouhaitee="";
				if(conge.getDate_Debut_Souhaitee()!=null) {
					dateFinSouhaitee = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.FRENCH).format(conge.getDate_Fin_Souhaitee());
				}
				if(!conge.isApprouve() && !conge.isDesapprouve() && !conge.isValidee() && !conge.isRejetee()){
					resultat = "Mr/Mme "+nameEmploye+","
							+"\n\nSouhaiterais obtenir un congé de type "+typeConge.getNom_Conge()
							+"\nqui débutereait le "+dateDebutSouhaitee+" et se terminerait le "+dateFinSouhaitee+"."
							+ "\n\nVous trouverez de plus amples informations en vous connectant à la plateforme\nTrès Cordialement.";
				}
				else if(conge.isApprouve()) {
					resultat = "Mr/Mme "+nameEmploye+","
							+"\n\nSuite à votre demande de congé de type "+typeConge.getNom_Conge()
							+"\nqui débutererait le "+dateDebutSouhaitee+" et se terminerait le "+dateFinSouhaitee+"."
							+"\n\nNous vous informons que votre demande a été approuvée par Mr/Mme "+nameApprobateur
							+".\n\nToutefois, il est nécessaire qu'elle soit validée. "
							+ "\nNous vous prions donc de patienter jusqu'à la fin du processus de traitement, "
							+ "\nNous vous tiendrons informé de toute évolution."
							+ "\n\nVous trouverez de plus amples informations en vous connectant à la plateforme\nTrès Cordialement.";
				}
				else if(conge.isDesapprouve()) {
					resultat = "Mr/Mme "+nameEmploye+","
							+"\n\nSuite à votre demande de congé de type "+typeConge.getNom_Conge()
							+"\nqui débutereait le "+dateDebutSouhaitee+" et se terminerait le "+dateFinSouhaitee+"."
							+"\n\nNous vous informons que votre demande a été désapprouvée par Mr/Mme "+nameApprobateur
							+ "\nNous vous prions tout de même de patienter jusqu'à la fin du processus de traitement, "
							+ "\nNous vous tiendrons informé de toute évolution."
							+ "\n\nVous trouverez de plus amples informations en vous connectant à la plateforme\nTrès Cordialement.";
				}
				else if(conge.isValidee()) {
					MCBPartner validateur = new MCBPartner(Env.getCtx(), conge.getValide_Rejete_Par_Nom_ID(), null);
					String nameValidateur = validateur.getName();
					if(validateur.getName2()!=null) {
						nameValidateur = nameValidateur+" "+validateur.getName2();
					}
					resultat = "Mr/Mme "+nameEmploye+","
							+"\n\nSuite à votre demande de congé de type "+typeConge.getNom_Conge()
							+"\nqui débutereait le "+dateDebutSouhaitee+" et se terminerait le "+dateFinSouhaitee+"."
							+"\n\nNous avons le plaisir de vous informer que votre demande a été validée par Mr/Mme "+nameValidateur
							+ "\n\nVous trouverez de plus amples informations en vous connectant à la plateforme\nTrès Cordialement.";
				}
				else if(conge.isRejetee()) {
					MCBPartner validateur = new MCBPartner(Env.getCtx(), conge.getValide_Rejete_Par_Nom_ID(), null);
					String nameValidateur = validateur.getName();
					resultat = "Mr/Mme "+nameEmploye+","
							+"\n\nSuite à votre demande de congé de type "+typeConge.getNom_Conge()
							+"\nqui débutereait le "+dateDebutSouhaitee+" et se terminerait le "+dateFinSouhaitee+"."
							+"\n\nNous avons le regtet de vous informer que votre demande a été rejetée par Mr/Mme "+nameValidateur
							+ "\n\nVous trouverez de plus amples informations en vous connectant à la plateforme\nTrès Cordialement.";
				}
			}
		}
		return resultat;
	}

	public static void notifierTraitementConge(Integer idConge) {

		String obj="Traitement d'une Demande de Congé";
		BeanNotificationConge bn = getBeanNotificationConge(idConge);
		ArrayList<InternetAddress> recieverMail = new ArrayList<>();
		try {
			if(bn.getMailEmploye()!=null) {
				recieverMail.add(new InternetAddress(bn.getMailEmploye()));
			}
			if(bn.getMailEmetteur()!=null) {
				recieverMail.add(new InternetAddress(bn.getMailEmetteur()));
			}
			if(bn.getMailValidateur()!=null) {
				recieverMail.add(new InternetAddress(bn.getMailValidateur()));
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InternetAddress[] dest =null;
		if(recieverMail!=null) {
			dest = recieverMail.toArray(new InternetAddress[0]);
		}
		else {
			dest = new InternetAddress[0];
		}
		GeneralController.sendEmail(expediteur, mdp, dest, obj, getMessageNotificationConge(idConge));
	}
}
