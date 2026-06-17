package org.sitracel.discipline.process.sanction.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.compiere.model.MBPartner;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.bean.BeanNotification;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.callout.absence.controller.CalloutSqlControllerAbsence;
import org.sitracel.conge.model.I_HR_Type_Absence;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.discipline.model.MHRDelaiReponse;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;
import org.sitracel.discipline.model.MHRDureeSanction;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;
import org.sitracel.discipline.model.X_HR_TypeSanction;
import org.sitracel.model.I_C_BPartner;
import org.sitracel.model.MCBPartner;

public class ProcessControllerDiscipline {


	private static CLogger log = CLogger.getCLogger (PO.class);
	private static String expediteur = "groupe.sitracel@gmail.com";
	private static String mdp = "vzccxxisbuazhhys";
	private static Integer ad_Role_RH_ID = 1003738;

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
					resultat.setMailEmploye(cb.get_ValueAsString(I_C_BPartner.COLUMNNAME_EMail));
					resultat.setNomEmploye(cb.getName()+" "+cb.getName2());
					MBPartner cbsup = new MBPartner(Env.getCtx(), bhrp.getEmis_Par_Nom_ID(), null);
					if(cbsup!=null) {
						resultat.setNomEmetteur(cbsup.getName()+" "+cbsup.getName2());
						resultat.setMailEmetteur(cbsup.get_ValueAsString(I_C_BPartner.COLUMNNAME_EMail));
					}
				}
			}
		}

		return resultat;

	}

	public static void validerSanction(Integer idSanction, Integer idADUser) {
		if(idSanction!=null && idADUser!=null ){
			MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);
			BeanIdentifiant beanIdentifiant = MCBPartner.getIdentifiant(idADUser, null);
			if(punishment!=null && beanIdentifiant!=null) {
				if(beanIdentifiant.getNumEmploye()!=null) {
					if(punishment.getDate_Debut_Application()!=null) {
						Timestamp dateFin = punishment.getDate_Debut_Application();
						MHRDureeSanction duree = new MHRDureeSanction(Env.getCtx(), punishment.getHR_Duree_Sanction_ID(), null);
						if(duree!=null) {
							dateFin = GeneralController.ajouterNombreJour(dateFin, duree.getNombre_De_Jour()-1);
							punishment.setDate_Fin_Application(dateFin);
						}
					}
					punishment.setValide_Rejete_Par_Nom_ID(beanIdentifiant.getNumEmploye());
					punishment.setValide_Rejete_Par_Poste_ID(beanIdentifiant.getNumeroPoste());
					punishment.setValide_Rejete_Par_Matricule(beanIdentifiant.getMatriculeEmploye());
					punishment.setIsValidee(true);
					punishment.setIsRejetee(false);
					punishment.setIsTraitee(true);
					punishment.setDate_Validation(new Timestamp(System.currentTimeMillis()));
					punishment.setDate_Rejet(null);
					punishment.save(null);
					ProcessControllerDiscipline.gererAbsenceApresValidationSanction(punishment, beanIdentifiant);
					ProcessControllerDiscipline.gererDossierDisciplinaireApresValidationSanction(punishment);
				}
			}
		}
	}

	public static void rejeterSanction(Integer idSanction, Integer idADUser) {
		if(idSanction!=null && idADUser!=null){
			MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);
			BeanIdentifiant beanIdentifiant = MCBPartner.getIdentifiant(idADUser, null);
			if(punishment!=null && beanIdentifiant!=null) {
				if(beanIdentifiant.getNumEmploye()!=null) {
					ProcessControllerDiscipline.gererAbsenceApresRejetSanction(punishment, beanIdentifiant);
					ProcessControllerDiscipline.gererDossierDisciplinaireApresRejetSanction(idSanction);
					punishment.setValide_Rejete_Par_Nom_ID(beanIdentifiant.getNumEmploye());
					punishment.setValide_Rejete_Par_Poste_ID(beanIdentifiant.getNumeroPoste());
					punishment.setValide_Rejete_Par_Matricule(beanIdentifiant.getMatriculeEmploye());
					punishment.setDate_Validation(null);
					punishment.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
					punishment.setDate_Fin_Application(null);
					punishment.setIsValidee(false);
					punishment.setIsRejetee(true);
					punishment.setIsTraitee(true);
					punishment.save(null);
				}
			}
		}
	}

	public static void approuverSanction(Integer idSanction, Integer idADUser) {
		if(idSanction!=null && idADUser!=null) {
			MHRPunishment pun = new MHRPunishment(Env.getCtx(), idSanction, null);
			BeanIdentifiant bi = MCBPartner.getIdentifiant(idADUser, null);
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

	public static void desapprouveSanction(Integer idSanction, Integer idADUser) {
		if(idSanction!=null && idADUser!=null) {
			MHRPunishment pun = new MHRPunishment(Env.getCtx(), idSanction, null);
			BeanIdentifiant bi = MCBPartner.getIdentifiant(idADUser, null);
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
		ArrayList<InternetAddress> recieverMail = new ArrayList<>();
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

	private static void gererAbsenceApresValidationSanction(MHRPunishment punishment, BeanIdentifiant beanIdentifiant) {
		if(punishment!=null && beanIdentifiant!=null) {
			MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(Env.getCtx(), punishment.getEmission_Sanction_ID(), null);
			if(autorisation!=null) {
				MHRTypeSanction typeSanction = new MHRTypeSanction(Env.getCtx(), autorisation.getHR_TypeSanction_ID(), null);
				if(typeSanction!=null) {
					if(typeSanction.getIncidence_Sanction_ID().equalsIgnoreCase(X_HR_TypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension)){
						MHRDureeSanction dureeSanction = new MHRDureeSanction(Env.getCtx(), punishment.getHR_Duree_Sanction_ID(), null);
						int delaiApplication = 0;
						if(dureeSanction!=null) {
							delaiApplication = dureeSanction.getNombre_De_Jour();
						}
						Timestamp debutApplicationAbs = punishment.getDate_Debut_Application();
						Timestamp finApplicationAbs = GeneralController.ajouterNombreJour(punishment.getDate_Debut_Application(), delaiApplication);
						Integer typeAbsenceID = GeneralSqlController.getIDFromTableNameAndName(I_HR_Type_Absence.COLUMNNAME_HR_Type_Absence_ID,
								I_HR_Type_Absence.Table_Name, I_HR_Type_Absence.COLUMNNAME_Nom_Absence, "Suspendu", null);
						if(typeAbsenceID!=null) {
							if(delaiApplication>0) {
								punishment.setDate_Fin_Application(finApplicationAbs);
							}
							else {
								punishment.setDate_Fin_Application(null);
							}
							int i = 1;
							while(debutApplicationAbs.before(finApplicationAbs)) {
								Calendar cal = Calendar.getInstance();
								cal.setTime(debutApplicationAbs);
								if(cal.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY || !MHRPublicHoliday.isJourFerie(debutApplicationAbs,null)) {
									if(!ProcessSqlControllerDiscipline.isAbsenceExist(punishment.getC_BPartner_ID(), debutApplicationAbs, null)) {
										MHRAbsence absence = new MHRAbsence(Env.getCtx(), null, null);
										absence.setC_BPartner_ID(punishment.getC_BPartner_ID());
										absence.setPoste_Employe_ID(punishment.getPoste_Employe_ID());
										absence.setMatricule_Employe(punishment.getMatricule_Employe());
										absence.setEmis_Par_Nom_ID(beanIdentifiant.getNumEmploye());
										absence.setEmis_Par_Poste_ID(beanIdentifiant.getNumeroPoste());
										absence.setEmis_Par_Matricule(beanIdentifiant.getMatriculeEmploye());
										absence.setDate_Absence(debutApplicationAbs);
										absence.setDate_Emission(new Timestamp(System.currentTimeMillis()));
										absence.setHR_Type_Absence_ID(typeAbsenceID);
										absence.setIsDemandeExplication(false);
										absence.setIsConge(false);
										absence.setIsDemandeExplicationTraite(true);
										absence.setIsCongeTraite(true);
										absence.save(null);
									}
								}
								else {
									finApplicationAbs=GeneralController.ajouterNombreJour(finApplicationAbs, 1);
								}
								debutApplicationAbs=GeneralController.ajouterNombreJour(debutApplicationAbs, 1);
							}
						}

					}
					else if (typeSanction.getIncidence_Sanction_ID().equalsIgnoreCase(X_HR_TypeSanction.INCIDENCE_SANCTION_ID_Licenciement)) {

					}
				}
			}
		}
	}

	private static void gererAbsenceApresRejetSanction(MHRPunishment punishment, BeanIdentifiant beanIdentifiant) {
		if(punishment!=null && beanIdentifiant!=null) {
			MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(Env.getCtx(), punishment.getEmission_Sanction_ID(), null);
			if(autorisation!=null) {
				MHRTypeSanction typeSanction = new MHRTypeSanction(Env.getCtx(), autorisation.getHR_TypeSanction_ID(), null);
				if(typeSanction!=null) {
					if(typeSanction.getIncidence_Sanction_ID().equalsIgnoreCase(X_HR_TypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension)) {
						ArrayList<Integer> listeAbsenceID = CalloutSqlControllerAbsence.getListeAbsenceIDByName(punishment.getC_BPartner_ID(), "Suspendu", punishment.getDate_Debut_Application(), punishment.getDate_Fin_Application(), null);
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
	}

	private static void gererDossierDisciplinaireApresValidationSanction(MHRPunishment punishment) {
		if(punishment!=null) {
			MHRDossierDisciplinaire dossier =ProcessSqlControllerDiscipline.getDossierDisciplinaire(punishment.getHR_Punishment_ID(), null);
			if(dossier==null) {
				try {
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
				} catch (IllegalStateException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static void gererDossierDisciplinaireApresRejetSanction(Integer idSanction) {
		MHRDossierDisciplinaire dossier =ProcessSqlControllerDiscipline.getDossierDisciplinaire(idSanction, null);
		if(dossier!=null) {
			try {
				if(dossier!=null) {
					dossier.delete(false);
				}
				DB.commit(true, dossier.get_TrxName());
			} catch (IllegalStateException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
