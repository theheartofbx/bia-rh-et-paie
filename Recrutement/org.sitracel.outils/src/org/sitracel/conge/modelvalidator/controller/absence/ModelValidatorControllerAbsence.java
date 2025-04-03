package org.sitracel.conge.modelvalidator.controller.absence;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.compiere.model.MBPartner;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.bean.BeanInfoAbsence;
import org.sitracel.bean.BeanNotification;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRTypeAbsence;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.discipline.model.MHRDelaiReponse;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.model.MCBPartner;

public class ModelValidatorControllerAbsence {
	private static String expediteur = "groupe.sitracel@gmail.com";
	private static String mdp = "vzccxxisbuazhhys";

	public static int getNombreJourCongeAnnuelBase() {
		return GeneralSqlController.getParametreFromParametreNumerique("Congé Annuel de Base");
	}

	public static int getNombreJourMaxAbsenceAvantDemandeExplication() {
		return GeneralSqlController.getParametreFromParametreNumerique("Absence Max Avant Demande Explication");
	}

	public static int getRHSystemID() {
		return GeneralSqlController.getParametreFromParametreNumerique("RH_Système_ID");
	}
	
	public static int getDelaiReponse() {
		return GeneralSqlController.getParametreFromParametreNumerique("Délai Réponse Demande Explication");
	}
	
	public static void traiterDemandeExplicationSuiteAbsence(MHRAbsence absence) {
		if(absence!=null) {
			Integer typeAbsenceId = absence.getHR_Type_Absence_ID();
			MHRDemandeExplication demandeExplication = new MHRDemandeExplication(Env.getCtx(), null, null);
			if(typeAbsenceId!=null) {
				MHRTypeAbsence typeAbsence = new MHRTypeAbsence(Env.getCtx(), typeAbsenceId, null);
				int absMax = ModelValidatorControllerAbsence.getNombreJourMaxAbsenceAvantDemandeExplication();
				int rhSystemID = ModelValidatorControllerAbsence.getRHSystemID();
				int delaiReponse = ModelValidatorControllerAbsence.getDelaiReponse();
				BeanIdentifiant beanRHSystem = GeneralSqlController.getBeanIdentifiant(rhSystemID, null);
				BeanIdentifiant beanPartner = GeneralSqlController.getBeanIdentifiant(absence.getC_BPartner_ID(), null);
				
				if(typeAbsence!=null) {					
					BeanInfoAbsence infoAbsence = ModelValidatorSqlControllerAbsence.getAbsenceNonAutoriseNonTraite(absence.getC_BPartner_ID(), null);
					if(typeAbsence.isDemandeExplication()) {
						if(infoAbsence!=null) {
							if((infoAbsence.getNombreJour()+1)>=absMax) {							
								try {
									demandeExplication.setMotif_Demande_Explication("Vous avez été absent(e) le(s) "+infoAbsence.getDate()+" sans justification, nous vous prions d'éclaircir la situation et de nous donner des explications sur cet état de fait.");
									demandeExplication.setC_BPartner_ID(absence.getC_BPartner_ID());
									demandeExplication.setMatricule_Employe(absence.getMatricule_Employe());
									demandeExplication.setPoste_Employe_ID(absence.getPoste_Employe_ID());
									demandeExplication.setEmis_Par_Nom_ID(beanRHSystem.getNumEmploye());
									demandeExplication.setEmis_Par_Matricule(beanRHSystem.getMatriculeEmploye());
									demandeExplication.setHR_Delai_Reponse_ID(delaiReponse);									
									demandeExplication.setEmis_Par_Poste_ID(beanRHSystem.getNumeroPoste());
									demandeExplication.setName("Absence"+beanPartner.getNomEmploye());
									demandeExplication.save();
									demandeExplication.setName(demandeExplication.getHR_Demande_Explication_ID()+"-"+beanPartner.getNomEmploye());
									demandeExplication.save();
									DB.commit(true, demandeExplication.get_TrxName());
									absence.setHR_Demande_Explication_ID(demandeExplication.getHR_Demande_Explication_ID());
									absence.setIsDemandeExplicationTraite(true);
									ModelValidatorControllerAbsence.notifierDemandeExplication(demandeExplication.getHR_Demande_Explication_ID());
									for (Integer AbsId : infoAbsence.getListAbsenceID()) {
										if(AbsId != null) {
											MHRAbsence modif = new MHRAbsence(Env.getCtx(), AbsId, null);
											if(modif!=null) {
												modif.setHR_Demande_Explication_ID(demandeExplication.getHR_Demande_Explication_ID());
												modif.setIsDemandeExplicationTraite(true);
												modif.save();
												DB.commit(true, modif.get_TrxName());
											}
										}
									}
								} catch (IllegalStateException | SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
								finally {
									
								}
							}
						}
					}					
				}
			}
		}
	}
	

	public static void annulerDemandeExplicationSuiteAbsence(MHRAbsence absence) {
		if(absence!=null) {
			Integer typeAbsenceId = absence.getHR_Type_Absence_ID();
			MHRDemandeExplication demandeExplication = new MHRDemandeExplication(Env.getCtx(), absence.getHR_Demande_Explication_ID(), null);
			int absMax = ModelValidatorControllerAbsence.getNombreJourMaxAbsenceAvantDemandeExplication();
			if(typeAbsenceId!=null && demandeExplication!=null) {	
				try {
					BeanInfoAbsence infoAbsence = ModelValidatorSqlControllerAbsence.getAbsenceNonAutoriseFromDemandeExplication(demandeExplication.getHR_Demande_Explication_ID(), null);
					if(infoAbsence.getNombreJour()==absMax) {
						for (Integer AbsId : infoAbsence.getListAbsenceID()) {
							if(AbsId != null) {
								MHRAbsence modif = new MHRAbsence(Env.getCtx(), AbsId, null);
								if(modif!=null) {
									modif.setHR_Demande_Explication_ID(0);
									modif.setIsDemandeExplicationTraite(false);
									modif.save();
									DB.commit(true, modif.get_TrxName());								
								}
							}
						}
						ArrayList<Integer> sanctionsID = ModelValidatorSqlControllerAbsence.getSanctionFromDemandeExplication(demandeExplication.getHR_Demande_Explication_ID(), null);
						for(Integer sanctionID : sanctionsID) {
							if(sanctionID!=null) {
								MHRPunishment sanction = new MHRPunishment(Env.getCtx(), sanctionID, null);
								sanction.setIsValidee(false);
								sanction.setIsRejetee(true);
								sanction.setIsTraitee(true);
								sanction.save();
								DB.commit(true, sanction.get_TrxName());
								sanction.delete(true);
								DB.commit(true, sanction.get_TrxName());
							}
						}
						absence.setHR_Demande_Explication_ID(0);
						demandeExplication.delete(true);
						DB.commit(true, demandeExplication.get_TrxName());
					}
				} catch (IllegalStateException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		}
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
	
	public static void notifierDemandeExplication(Integer idDemandeExplication) {
		
		String obj="Emission d'une Demande d'Explication";
		BeanNotification bn = getBeanNotificationDE(idDemandeExplication);
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
		GeneralController.sendEmail(expediteur, mdp, dest, obj, getNotifierDemandeExplicationMessage(idDemandeExplication));
		
	}
	
	private static String getNotifierDemandeExplicationMessage(Integer idDemandeExplication) {
		String resultat = "";
		if(idDemandeExplication!=null) {
			MHRDemandeExplication demandeExplication = new MHRDemandeExplication(Env.getCtx(), idDemandeExplication, null);
			if(demandeExplication!=null) {
				MCBPartner employe = new MCBPartner(Env.getCtx(), demandeExplication.getC_BPartner_ID(), null);
				MCBPartner emetteur = new MCBPartner(Env.getCtx(), demandeExplication.getEmis_Par_Nom_ID(), null);
				String nameEmploye = employe.getName();
				if(employe.getName2()!=null) {
					nameEmploye = nameEmploye+" "+employe.getName2();
				}
				String nameEmetteur = emetteur.getName();
				if(emetteur.getName2()!=null) {
					nameEmetteur = nameEmetteur+" "+emetteur.getName2();
				}
				String dateReponse ="";
				if(demandeExplication.getDate_Reponse()!=null) {
					dateReponse = new SimpleDateFormat("EEEE, dd MMMM yyyy hh:mm:ss", Locale.FRENCH).format(demandeExplication.getDate_Reponse());
				}
				String motif = "";
				if(demandeExplication.getMotif_Demande_Explication()!=null) {
					motif = demandeExplication.getMotif_Demande_Explication();
				}
				int delaiReponseID = demandeExplication.getHR_Delai_Reponse_ID();
				MHRDelaiReponse delaiReponse = new MHRDelaiReponse(Env.getCtx(), delaiReponseID, null);
				String delai ="";
				if(delaiReponse!=null) {
					delai = delaiReponse.getName();
				}
				String reponse = "";
				if(demandeExplication.getReponse_Demande_Explication()!=null) {
					reponse = demandeExplication.getReponse_Demande_Explication();
				}
				String dateEmission="";
				if(demandeExplication.getDate_Emission()!=null) {
					dateEmission = new SimpleDateFormat("EEEE, dd MMMM yyyy hh:mm:ss", Locale.FRENCH).format(demandeExplication.getDate_Emission());
				}
				if(reponse!=null) {
					if(!reponse.equalsIgnoreCase("") && !reponse.equalsIgnoreCase(" ")) {
						resultat = "Mr/Mme "+nameEmploye+",\n\nUne demande d'explication a été émise à votre encontre, "
								+"\nPar M/Mme "+ nameEmetteur+" au sujet de : "
								+"\n\n"+motif+"."
								+"\n\nVous avez "+delai
								+" pour y répondre, à compter du "+dateEmission
								+ "\n\nVous trouverez de plus amples informations en vous connectant à la plateforme\nTrès Cordialement.";
					}
				}
				else {
					resultat = "Mr/Mme ,\n\nSuite à la demande d'explication émise à l'encontre de Mr/Mme"+nameEmploye
							+"\nPar M/Mme "+ nameEmetteur+" au sujet de : "
							+"\n\n"+motif+"."
							+"\n\nUne réponse a été apportée le "+dateReponse+" : \n"+reponse
							+ "\n\nVous trouverez de plus amples informations en vous connectant à la plateforme\nTrès Cordialement.";
				}
			}
		}
		return resultat;
	}	

}
