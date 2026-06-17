package org.sitracel.discipline.process.demandeexplication.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.compiere.util.Env;
import org.sitracel.bean.BeanNotification;
import org.sitracel.controller.GeneralController;
import org.sitracel.discipline.model.MHRDelaiReponse;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.process.sanction.controller.ProcessControllerDiscipline;
import org.sitracel.model.MCBPartner;

public class ProcessControllerDemandeExplication {
	private static String expediteur = "groupe.sitracel@gmail.com";
	private static String mdp = "vzccxxisbuazhhys";

	public static void notifierDemandeExplication(Integer idDemandeExplication) {

		String obj="Emission d'une Demande d'Explication";
		BeanNotification bn = ProcessControllerDiscipline.getBeanNotificationDE(idDemandeExplication);
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
								+"\nPar M/Mme "+ nameEmetteur+" pour avoir plus d'informations au sujet de : "
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
