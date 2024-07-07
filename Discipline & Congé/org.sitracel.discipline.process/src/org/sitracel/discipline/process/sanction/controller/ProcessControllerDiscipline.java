package org.sitracel.discipline.process.sanction.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.compiere.model.MBPartner;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.sitracel.callout.bean.BeanIdentifiant;
import org.sitracel.callout.bean.BeanMiseaPieds;
import org.sitracel.callout.bean.BeanNotification;
import org.sitracel.callout.beanfactory.BeanFactory;
import org.sitracel.discipline.model.MHRDelaiReponse;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRDureeSanction;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;
import org.sitracel.general.controller.GeneralController;
import org.sitracel.model.MCBPartner;

public class ProcessControllerDiscipline {


	private static CLogger log = CLogger.getCLogger (PO.class);
	private static String expediteur = "groupe.sitracel@gmail.com";
	private static String mdp = "vzccxxisbuazhhys";
	private static Integer ad_Role_RH_ID = 1003738;

	public static BeanMiseaPieds getBeanMiseaPieds(Integer idSanction) {
		BeanMiseaPieds resultat = BeanFactory.getBeanMiseaPieds();
		MHRPunishment pun = new MHRPunishment(Env.getCtx(), idSanction, null);
		if(pun!=null) {
			MHRDureeSanction dureeSanction = new MHRDureeSanction(Env.getCtx(), pun.getHR_Duree_Sanction_ID(), null);
			if(dureeSanction!=null) {
				resultat.setDelaiSanction(dureeSanction.getNombre_De_Jour());
			}
			resultat.setDateDebutApplication(pun.getDate_Debut_Application());
			resultat.setIsValide(pun.isValidee() ? "Y":"N");
			resultat.setIsReject(pun.isRejetee() ? "Y":"N");
			MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(Env.getCtx(), pun.getEmission_Sanction_ID(), null);
			if(autorisation!=null) {
				MHRTypeSanction typeSanction = new MHRTypeSanction(Env.getCtx(), autorisation.getHR_TypeSanction_ID(), null);
				if(typeSanction!=null) {
					resultat.setTypeSanction(typeSanction.getNom_Sanction());
				}
			}
		}
		return resultat;
	}		

	public static BeanNotification getBeanNotificationSanction(Integer idSanction) {
		BeanNotification resultat = BeanFactory.getBeanNotification();
		
		if(idSanction!=null) {			
			MHRPunishment bhrp = new MHRPunishment(Env.getCtx(), idSanction, null);
			if(bhrp!=null) {
				MCBPartner cb = new MCBPartner(Env.getCtx(), bhrp.getC_BPartner_ID(), null);
				if(bhrp.getValide_Rejete_Par_Poste()!=null) {
					resultat.setPosteValidateur(bhrp.getValide_Rejete_Par_Poste().getHR_Job_ID());
				}
				if(bhrp.getEmis_Par_Poste()!=null) {
					resultat.setPosteEmetteur(bhrp.getEmis_Par_Poste().getHR_Job_ID());
				}
				if(bhrp.getPoste_Employe()!=null) {
					resultat.setPosteEmploye(bhrp.getPoste_Employe().getHR_Job_ID());
				}
				
				resultat.setDateEmission(bhrp.getDate_Emission());
				resultat.setDatereponse(bhrp.getDate_Reponse_DE());
				resultat.setDateValidation(bhrp.getDate_Validation());
				MHRDelaiReponse delai = new MHRDelaiReponse(Env.getCtx(), bhrp.getDelai_Reponse_DE_ID(), null);
				if(delai!=null) {
					resultat.setDelaiReponse(delai.getName());
				}
				
				resultat.setMotif(bhrp.getMotif_Demande_Explication());
				MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(Env.getCtx(), bhrp.getEmission_Sanction_ID(), null);
				if(autorisation!=null) {
					MHRTypeSanction typeSanction = new MHRTypeSanction(Env.getCtx(), autorisation.getHR_TypeSanction_ID(), null);
					if(typeSanction!=null) {
						resultat.setTypeSanction(typeSanction.getNom_Sanction());
					}
				}
				if(cb!=null) {
					resultat.setMailEmploye(cb.getEMail());
					resultat.setNomEmploye(cb.getName()+" "+cb.getName2());
					MCBPartner cbsup = new MCBPartner(Env.getCtx(), bhrp.getEmis_Par_Nom_ID(), null); 
					MCBPartner cbdirp = new MCBPartner(Env.getCtx(), bhrp.getValide_Rejete_Par_Nom_ID(), null); 
					if(cbsup!=null) {
						resultat.setNomEmetteur(cbsup.getName()+" "+cbsup.getName2());
						resultat.setMailEmetteur(cbsup.getEMail());
					}
					if(cbdirp!=null) {
						resultat.setNomValidateur(cbdirp.getName()+" "+cbdirp.getName2());
						resultat.setMailValidateur(cbdirp.getEMail());
					}					
				}
			}
		}
		
		return resultat;	
		
	}	
	

	public static BeanNotification getBeanNotificationDE(Integer idDemandeExplication) {
		BeanNotification resultat = BeanFactory.getBeanNotification();
		
		if(idDemandeExplication!=null) {			
			MHRDemandeExplication bhrp = new MHRDemandeExplication(Env.getCtx(), idDemandeExplication, null);
			if(bhrp!=null) {
				MBPartner cb = new MBPartner(Env.getCtx(), bhrp.getC_BPartner_ID(), null);
				
				if(bhrp.getEmis_Par_Poste()!=null) {
					resultat.setPosteEmetteur(bhrp.getEmis_Par_Poste().getHR_Job_ID());
				}
				if(bhrp.getPoste_Employe()!=null) {
					resultat.setPosteEmploye(bhrp.getPoste_Employe().getHR_Job_ID());
				}
				
				resultat.setDateEmission(bhrp.getDate_Emission());
				resultat.setDatereponse(bhrp.getDate_Reponse());
				MHRDelaiReponse delai = new MHRDelaiReponse(Env.getCtx(), bhrp.getHR_Delai_Reponse_ID(), null);
				if(delai!=null) {
					resultat.setDelaiReponse(delai.getName());
				}
				
				resultat.setMotif(bhrp.getMotif_Demande_Explication());
				if(cb!=null) {
					resultat.setMailEmploye(cb.get_ValueAsString(MCBPartner.COLUMNNAME_EMail));
					resultat.setNomEmploye(cb.getName()+" "+cb.getName2());
					MBPartner cbsup = new MBPartner(Env.getCtx(), bhrp.getEmis_Par_Nom_ID(), null);  
					if(cbsup!=null) {
						resultat.setNomEmetteur(cbsup.getName()+" "+cbsup.getName2());
						resultat.setMailEmetteur(cbsup.get_ValueAsString(MCBPartner.COLUMNNAME_EMail));
					}		
				}
			}
		}
		
		return resultat;	
		
	}	
	
	public static void updateDelaiSanctionApresValidation(BeanMiseaPieds bmp, Timestamp debut, Integer idSanction, Integer idADUser) {
		if(idSanction!=null && bmp.getTypeSanction()!=null && idADUser!=null ){
			MHRPunishment pun = new MHRPunishment(Env.getCtx(), idSanction, null);
			if(pun!=null) {
				MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(Env.getCtx(), pun.getEmission_Sanction_ID(), null);
				if(autorisation!=null) {
					MHRTypeSanction typeSanction = new MHRTypeSanction(Env.getCtx(), autorisation.getHR_TypeSanction_ID(), null);
					if(typeSanction!=null) {
						if(typeSanction.getIncidence_Sanction_ID().equalsIgnoreCase(MHRTypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension)){
							enregistrer(idSanction, debut, bmp.getDateDebutApplication(),bmp.getDelaiSanction(), idADUser);
						  }
						else if (typeSanction.getIncidence_Sanction_ID().equalsIgnoreCase(MHRTypeSanction.INCIDENCE_SANCTION_ID_Licenciement)){
							enregistrer(idSanction, debut, bmp.getDateDebutApplication(),0, idADUser);
						}
						else {
							setSanctionValide(idSanction, idADUser);
						}
					}
				}				
			}						
		}
		else {
			setSanctionValide(idSanction, idADUser);
		}		
	}
	
	public static void updateDelaiSanctionApresRejet(BeanMiseaPieds bmp, Timestamp debut, Integer idSanction, Integer idADUser) {
		if(idSanction!=null && bmp.getTypeSanction()!=null && idADUser!=null){
			MHRPunishment pun = new MHRPunishment(Env.getCtx(), idSanction, null);
			if(pun!=null) {
				MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(Env.getCtx(), pun.getEmission_Sanction_ID(), null);
				if(autorisation!=null) {
					MHRTypeSanction typeSanction = new MHRTypeSanction(Env.getCtx(), autorisation.getHR_TypeSanction_ID(), null);
					if(typeSanction!=null) {
						if(typeSanction.getIncidence_Sanction_ID().equalsIgnoreCase(MHRTypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension)) {
							BeanIdentifiant bi = ProcessSqlControllerDiscipline.getBeanIdentifiant(idADUser, null);
							if(pun!=null && bi!=null) {
								if(bi.getNumEmploye()!=null) {
									pun.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
									pun.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
									pun.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
									pun.setDate_Validation(null);
									pun.setDate_Rejet(debut);
									pun.setDate_Fin_Application(null);
									pun.setIsValidee(false);
									pun.setIsRejetee(true);
									pun.setIsTraitee(true);
									pun.save(null);
								}
							}
						}
					}
				}
				else {
					setSanctionRejected(idSanction, idADUser);
				}
			}
		}
		else {
			setSanctionRejected(idSanction, idADUser);
		}
		
	}
	
	public static void setSanctionValide(Integer idSanction, Integer idADUser) {
		if(idSanction!=null && idADUser!=null) {
			MHRPunishment pun = new MHRPunishment(Env.getCtx(), idSanction, null);
			BeanIdentifiant bi = ProcessSqlControllerDiscipline.getBeanIdentifiant(idADUser, null);
			if(pun!=null && bi!=null) {
				if(bi.getNumEmploye()!=null) {
					pun.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
					pun.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
					pun.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
					pun.setIsValidee(true);
					pun.setIsRejetee(false);
					pun.setIsTraitee(true);
					pun.setDate_Validation(new Timestamp(System.currentTimeMillis()));
					pun.setDate_Rejet(null);
					pun.save(null);
				}
			}
		}		
	}	

	public static void setSanctionRejected(Integer idSanction, Integer idADUser) {
		if(idSanction!=null && idADUser!=null) {
			MHRPunishment pun = new MHRPunishment(Env.getCtx(), idSanction, null);
			BeanIdentifiant bi = ProcessSqlControllerDiscipline.getBeanIdentifiant(idADUser, null);
			if(pun!=null && bi!=null) {
				if(bi.getNumEmploye()!=null) {
					pun.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
					pun.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
					pun.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
					pun.setIsValidee(false);
					pun.setIsRejetee(true);
					pun.setIsTraitee(true);
					pun.setDate_Validation(null);
					pun.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
					pun.save(null);
				}
			}
		}		
	}	

	public static void setSanctionApprouve(Integer idSanction, Integer idADUser) {
		if(idSanction!=null && idADUser!=null) {
			MHRPunishment pun = new MHRPunishment(Env.getCtx(), idSanction, null);
			BeanIdentifiant bi = ProcessSqlControllerDiscipline.getBeanIdentifiant(idADUser, null);
			if(pun!=null && bi!=null) {
				if(bi.getNumEmploye()!=null) {
					pun.setApprouve_Desapprouve_Nom_ID(bi.getNumEmploye());
					pun.setApprouve_Desapprouve_Poste_ID(bi.getNumeroPoste());
					pun.setApprouve_Desapprouve_Matricule(bi.getMatriculeEmploye());
					pun.setIsApprouve(true);
					pun.setIsDesapprouve(false);
					pun.setDate_Approbation(new Timestamp(System.currentTimeMillis()));
					pun.setDate_Desapprobation(null);
					pun.save(null);
				}
			}
		}		
	}	

	public static void setSanctionDesapprouve(Integer idSanction, Integer idADUser) {
		if(idSanction!=null && idADUser!=null) {
			MHRPunishment pun = new MHRPunishment(Env.getCtx(), idSanction, null);
			BeanIdentifiant bi = ProcessSqlControllerDiscipline.getBeanIdentifiant(idADUser, null);
			if(pun!=null && bi!=null) {
				if(bi.getNumEmploye()!=null) {
					pun.setApprouve_Desapprouve_Nom_ID(bi.getNumEmploye());
					pun.setApprouve_Desapprouve_Poste_ID(bi.getNumeroPoste());
					pun.setApprouve_Desapprouve_Matricule(bi.getMatriculeEmploye());
					pun.setIsApprouve(false);
					pun.setIsDesapprouve(true);
					pun.setDate_Approbation(null);
					pun.setDate_Desapprobation(new Timestamp(System.currentTimeMillis()));
					pun.save(null);
				}
			}
		}		
	}		
	
	public static void notifierSanction(Integer idSanction) {
		
		String obj="Emission d'une Mesure Disciplinaire";
		BeanNotification bn = ProcessControllerDiscipline.getBeanNotificationSanction(idSanction);
		ArrayList<InternetAddress> recieverMail = new ArrayList<InternetAddress>();
		try {
			if(bn.getMailEmploye()!=null) {
				if(bn.getMailEmploye().matches(".+@.+\\.[a-z]+")) {
					recieverMail.add(new InternetAddress(bn.getMailEmploye()));
				}
			}
			if(bn.getMailEmetteur()!=null) {
				if(bn.getMailEmetteur().matches(".+@.+\\.[a-z]+")) {
					recieverMail.add(new InternetAddress(bn.getMailEmetteur()));
				}
			}
			if(bn.getMailValidateur()!=null) {
				if(bn.getMailValidateur().matches(".+@.+\\.[a-z]+")) {
					recieverMail.add(new InternetAddress(bn.getMailValidateur()));
				}
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
		GeneralController.sendEmail(expediteur, mdp, dest, obj, getNotifierSanctionMessage(idSanction));
		
	}
	
	private static void enregistrer(Integer idSanction,Timestamp debut, Timestamp debutApplication, int delaiApplication, Integer idADUser) {	
		Timestamp finApplication = GeneralController.ajouterNombreJour(debutApplication, delaiApplication);
		MHRPunishment pun = new MHRPunishment(Env.getCtx(), idSanction, null);
		BeanIdentifiant bi = ProcessSqlControllerDiscipline.getBeanIdentifiant(idADUser, null);
		if(pun!=null && bi!=null) {
			if(bi.getNumEmploye()!=null) {
				pun.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
				pun.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
				pun.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
				pun.setDate_Validation(debut);
				pun.setDate_Rejet(null);
				pun.setDate_Debut_Application(debutApplication);
				 if(delaiApplication>0) {
					pun.setDate_Fin_Application(finApplication);
				  }
				  else {
						pun.setDate_Fin_Application(null);
				  }
				pun.setIsValidee(true);
				pun.setIsRejetee(false);
				pun.setIsTraitee(true);
				pun.save(null);
			}
		}
	}	

	private static String getNotifierSanctionMessage(Integer idSanction) {
		String resultat = "";
		if(idSanction!=null) {
			MHRPunishment sanction = new MHRPunishment(Env.getCtx(), idSanction, null);
			if(sanction!=null) {
				MCBPartner employe = new MCBPartner(Env.getCtx(), sanction.getC_BPartner_ID(), null);
				MCBPartner emetteur = new MCBPartner(Env.getCtx(), sanction.getEmis_Par_Nom_ID(), null);
				MCBPartner validateur = new MCBPartner(Env.getCtx(), sanction.getValide_Rejete_Par_Nom_ID(), null);
				String nameTypeSanction = "";
				MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(Env.getCtx(), sanction.getEmission_Sanction_ID(), null);
				if(autorisation!=null) {
					MHRTypeSanction typeSanction = new MHRTypeSanction(Env.getCtx(), autorisation.getHR_TypeSanction_ID(), null);
					if(typeSanction!=null) {
						nameTypeSanction = typeSanction.getNom_Sanction();
					}
				}
				String nameEmploye = employe.getName();
				if(employe.getName2()!=null) {
					nameEmploye = nameEmploye+" "+employe.getName2();
				}
				String nameEmetteur = emetteur.getName();
				if(emetteur.getName2()!=null) {
					nameEmetteur = nameEmetteur+" "+emetteur.getName2();
				}
				String dateEmission="";
				if(sanction.getDate_Emission()!=null) {
					dateEmission = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.FRENCH).format(sanction.getDate_Emission());
				}				
				if(!sanction.isValidee() && !sanction.isRejetee()) {
					resultat = "\tMr/Mme "+nameEmploye+", "
							
							+" \n\nUne mesure disciplinaire de type "+nameTypeSanction
							+" a été émise à votre encontre le : "+dateEmission
							+"\nPar M/Mme "+ nameEmetteur+"."	
							+ "\nVous trouverez de plus amples informations en vous connectant à la plateforme\nTrès Cordialement.";
					
				}
				else if(sanction.isValidee()) {
					String dateValidation="";
					if(sanction.getDate_Validation()!=null) {
						dateValidation = new SimpleDateFormat("EEEE, dd MM yyyy", Locale.FRENCH).format(sanction.getDate_Validation());
					}
					String nameValidateur = validateur.getName();
					if(validateur.getName2()!=null) {
						nameValidateur = nameValidateur+" "+validateur.getName2();
					}
					resultat = "\tMr/Mme "+nameEmploye+", "
						
						+" \n\nla mesure disciplinaire de type "+nameTypeSanction
						+" émise à votre encontre le : "+dateEmission
						+" par M/Mme "+ nameEmetteur+", "	
						+"\na été validée le "+dateValidation
						+" par "+nameValidateur+"."
						+ "\n\nVous trouverez de plus amples informations en vous connectant à la plateforme\nTrès Cordialement.";
				}
				else if(sanction.isRejetee()) {
					String dateRejet="";
					if(sanction.getDate_Rejet()!=null) {
						dateRejet = new SimpleDateFormat("EEEE, dd MM yyyy", Locale.FRENCH).format(sanction.getDate_Rejet());
					}
					String nameValidateur = validateur.getName();
					if(validateur.getName2()!=null) {
						nameValidateur = nameValidateur+" "+validateur.getName2();
					}
					resultat = "\tMr/Mme "+nameEmploye+", "
						
						+" \n\nla mesure disciplinaire de type "+nameTypeSanction
						+" émise à votre encontre le : "+dateEmission
						+" par M/Mme "+ nameEmetteur+", "	
						+"\na été rejetée le "+dateRejet
						+" par "+nameValidateur+"."
						+ "\n\nVous trouverez de plus amples informations en vous connectant à la plateforme\nTrès Cordialement.";
				}
			}
		}
		return resultat;
	}	
}
