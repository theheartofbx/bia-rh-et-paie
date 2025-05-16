package org.sitracel.controller;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.compiere.util.Env;
import org.sitracel.bean.BeanConge;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.callout.conge.controller.CalloutSqlControllerConge;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.model.MCBPartner;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;

public class GeneralController {

	public static BeanConge getInfoConge(Integer idCBPartner, Integer idTypeConge, Timestamp dateActuelle,String trxName) {
		BeanConge beanConge =BeanFactory.getBeanConge();
		if(idCBPartner!=null && idTypeConge!=null) {
			int res = 0;
			MHRTypeConge conge = new MHRTypeConge(Env.getCtx(), idTypeConge, trxName);
			if(conge!=null) {
				if(conge.isCongeAnnuel()) {
					MHRElementBasePaieEmploye dernierContrat = GeneralController.getDateDernierContrat(idCBPartner, dateActuelle);
					Timestamp dateDebutContrat = null;
					if(dernierContrat!=null) {
						dateDebutContrat = dernierContrat.getDate_Debut();
					}
					BeanPeriode[] conges = GeneralSqlController.getCongesValidebyNameConge(idCBPartner, "Annuel", 
							GeneralController.getFirstDayOfaYear(dateActuelle), GeneralController.getLastDayOfaYear(dateActuelle), null);
					if(conges!=null) {
						for (BeanPeriode beanPeriode : conges) {
							res = res + GeneralController.getNombreJourTravaille(beanPeriode.getDateDebutConge(), beanPeriode.getDateFinConge());
						}
					}
					beanConge = CalloutSqlControllerConge.getEnfantMoins6(idCBPartner, dateActuelle, beanConge, trxName);
					beanConge = GeneralController.setAnciennete(beanConge, dateDebutContrat, dateActuelle);
					int nombreJourCongeBase = GeneralController.getNombreJourCongeAnnuelBase();
					int nbBase = 0;
					if(beanConge.getAnneeAnciennete()!=null) {
						nbBase = nombreJourCongeBase;
						nbBase = nbBase + (2*((int)beanConge.getAnneeAnciennete()/3));
					}
					if(beanConge.getGenre().equals(MCBPartner.SEX_Femme)) {
						nbBase = nbBase+(2*beanConge.getNombreEnfantPetit());
					}
					beanConge.setNombreJourCongeTotal(nbBase);
					beanConge.setNombreJourCongeUtilise(res);
					beanConge = GeneralSqlController.getDateDernierConge(idCBPartner, new Timestamp(System.currentTimeMillis()), dateDebutContrat, "Annuel", beanConge, null);
					beanConge.setDetteConge(CalloutSqlControllerConge.getNombreJourAbsencesConge(new Timestamp(System.currentTimeMillis()), null));
				}
			}
		}
		return beanConge;		
	}	

	public static BeanConge getNombreJourCongeMax(Integer idCBPartner, Timestamp dateActuelle,String trxName) {
		BeanConge beanConge = BeanFactory.getBeanConge();
		if(idCBPartner!=null) {
			MHRElementBasePaieEmploye dernierContrat = GeneralController.getDateDernierContrat(idCBPartner, dateActuelle);
			Timestamp dateDebutContrat = null;
			if(dernierContrat!=null) {
				dateDebutContrat = dernierContrat.getDate_Debut();
			}
			beanConge = CalloutSqlControllerConge.getEnfantMoins6(idCBPartner, dateActuelle, beanConge, trxName);
			beanConge = GeneralController.setAnciennete(beanConge, dateDebutContrat, dateActuelle);
			int nombreJourCongeBase = GeneralController.getNombreJourCongeAnnuelBase();
			int nbBase = 0;
			if(beanConge.getAnneeAnciennete()!=null) {
				nbBase = nombreJourCongeBase;
				nbBase = nbBase + (2*((int)beanConge.getAnneeAnciennete()/3));
			}
			if(beanConge.getGenre().equals(MCBPartner.SEX_Femme)) {
				nbBase = nbBase+(2*beanConge.getNombreEnfantPetit());
			}
			beanConge.setNombreJourCongeTotal(nbBase);
		}
		return beanConge;		
	}
	
	public static BeanConge setAnciennete(BeanConge beanConge, Timestamp dateDebut, Timestamp dateFin) {
		if(beanConge!=null && dateDebut!=null && dateFin!=null) {
			LocalDate date1 = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate date2 = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        Period periode = Period.between(date1, date2);
			beanConge.setAnneeAnciennete(periode.getYears());
			beanConge.setMoisAnciennete((int)ChronoUnit.MONTHS.between(date1, date2));
			beanConge.setJourAnciennete((int)ChronoUnit.DAYS.between(date1, date2));	
		}
		return beanConge;
	}
	
	public static MHRElementBasePaieEmploye getDateDernierContrat(Integer bpartnerID, Timestamp dateMax) {
		MHRElementBasePaieEmploye resultat = null;
		if(bpartnerID!=null && dateMax!=null) {
			ArrayList<MHRElementBasePaieEmploye> listeContrat = GeneralSqlController.getDatesDerniersContrats(bpartnerID, dateMax, null);
			if(!listeContrat.isEmpty()) {
				 for (int i = 0; i < listeContrat.size(); i++) {
					 MHRElementBasePaieEmploye contratActuel = listeContrat.get(i);
					 if (i + 1 < listeContrat.size()) {
			            MHRElementBasePaieEmploye contratPrecedent = listeContrat.get(i + 1);
			            if (contratPrecedent.getDate_Fin() != null) {
			                resultat = contratActuel;
			                break;
			            }
			        } else {
			            // Pas de précédent, donc c'est la plus ancienne
			            resultat = contratActuel;
			            break;
			        }
			    }
			}
		}		
		return resultat;
	}
	
	public static Timestamp ajouterNombreJour(Timestamp date, int nombreJour) {
		if(date!=null) {
			LocalDate jour = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			for(int i=1;i<=nombreJour;i++) {
				jour = jour.plusDays(1);
				date = GeneralController.ajusterenAjoutant(Timestamp.valueOf(LocalDateTime.of(jour, date.toLocalDateTime().toLocalTime())));
			}
		}
		return date;		
	}	

	public static Timestamp retirerNombreJour(Timestamp date, int nombreJour) {
		if(date!=null) {
			LocalDate jour = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			for(int i=1;i<=nombreJour;i++) {
				jour = jour.minusDays(1);
				date = GeneralController.ajusterenRetirant(Timestamp.valueOf(LocalDateTime.of(jour, date.toLocalDateTime().toLocalTime())));
			}
		}
		return date;		
	}
	
	public static Timestamp getFirstDayOfThisYear() {
		Calendar cal = Calendar.getInstance();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		cal.setTime(now);
		int year = cal.get(Calendar.YEAR);
		cal.set(Calendar.YEAR, year-1);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		return new Timestamp(cal.getTime().getTime());
	}
	
	public static Timestamp getLastDayOfThisYear() {
		Calendar cal = Calendar.getInstance();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		cal.setTime(now);
		int year = cal.get(Calendar.YEAR);
		cal.set(Calendar.YEAR, year+1);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return new Timestamp(cal.getTime().getTime());
	}
	
	public static Timestamp getFirstDayOfaYear(Timestamp anyDayOfYear) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(anyDayOfYear);
		int year = cal.get(Calendar.YEAR);
		cal.set(Calendar.YEAR, year-1);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		return new Timestamp(cal.getTime().getTime());
	}
	
	public static Timestamp getLastDayOfaYear(Timestamp anyDayOfYear) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(anyDayOfYear);
		int year = cal.get(Calendar.YEAR);
		cal.set(Calendar.YEAR, year+1);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return new Timestamp(cal.getTime().getTime());
	}	
	
	public static Timestamp ajusterenRetirant(Timestamp date) {
		Timestamp resultat = date;
		if(date!=null) {
			LocalDate jour = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if(jour.getDayOfWeek()==DayOfWeek.SUNDAY || GeneralSqlController.isJourFerie(date, null)) {
				resultat = Timestamp.valueOf(jour.minusDays(1).atStartOfDay());
			}
		}
		return resultat;
	}
	
	public static Timestamp ajusterenAjoutant(Timestamp date) {
		Timestamp resultat = date;
		if(date!=null) {
			LocalDate jour = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if(jour.getDayOfWeek()==DayOfWeek.SUNDAY || GeneralSqlController.isJourFerie(date, null)) {
				resultat = Timestamp.valueOf(jour.plusDays(1).atStartOfDay());
			}
		}
		return resultat;
	}
	
	public static Integer getNombreJourTravaille(Timestamp dateDebut, Timestamp dateFin) {
		Integer resultat = null;
		if(dateDebut!=null && dateFin!=null) {
			LocalDate debut = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate fin = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Set<LocalDate> joursFeries = GeneralSqlController.getAllJoursFeries(dateDebut, dateFin, null);
			resultat = 0;
			for(LocalDate date = debut; !date.isAfter(fin); date = date.plusDays(1)) {
				if(date.getDayOfWeek()!=DayOfWeek.SUNDAY && !joursFeries.contains(date)) {
					resultat++;
				}
			}
		}		
		return resultat;
	}

	public static Integer getNombreJour(Timestamp dateDebut, Timestamp dateFin) {
		Integer resultat = null;
		if(dateDebut!=null && dateFin!=null) {
			LocalDate debut = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate fin = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return (int) ChronoUnit.DAYS.between(debut,fin);
		}		
		return resultat;
	}

	public static Integer getNombreMois(Timestamp dateDebut, Timestamp dateFin) {
		Integer resultat = null;
		if(dateDebut!=null && dateFin!=null) {
			LocalDate debut = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate fin = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return (int) ChronoUnit.MONTHS.between(debut,fin);
		}		
		return resultat;
	}
	
	public static boolean isPeriodeDisponible(Integer bpartnerID, Timestamp dateDebut, Timestamp dateFin) {
		boolean resultat = false;
		if(bpartnerID!=null && dateDebut!=null && dateFin!=null) {
			if(!GeneralSqlController.chevaucheAnyCongeNonRejete(bpartnerID, dateDebut, dateFin, null)
					&& !GeneralSqlController.chevaucheSuspensionNonRejete(bpartnerID, dateDebut, dateFin, null)
					&& !GeneralSqlController.isPeriodeAbsence(bpartnerID, dateDebut, dateFin, null)) {
				resultat = true;
			}
		}
		return resultat;
	}

	public static boolean isJourDisponible(Integer bpartnerID, Timestamp date) {
		boolean resultat = false;
		if(bpartnerID!=null && date!=null) {
			if(!GeneralSqlController.isJourAnyCongeNonRejete(bpartnerID, date, null)
					&& !GeneralSqlController.isJourSuspensionNonRejete(bpartnerID, date, null)
					&& !GeneralSqlController.isJourAbsence(bpartnerID, date, null)
					&& !GeneralSqlController.isJourFerie(date, null)) {
				resultat = true;
			}
		}
		return resultat;
	}
	
	public static int getNombreJourCongeAnnuelBase() {
		return GeneralSqlController.getParametreFromParametreNumerique("Congé Annuel de Base");
	}

	public static int getNombreJourMaxAbsenceAvantDemandeExplication() {
		return GeneralSqlController.getParametreFromParametreNumerique("Absence Max Avant Demande Explication");
	}
	
	public static void sendEmail(final String senderMail,final String password,InternetAddress[] recieverMail,String subject,String msg){
	    Properties property = new Properties();
	    
	    property.put("mail.smtp.auth", "true");
	    property.put("mail.smtp.starttls.enable", "true");
	    property.put("mail.smtp.host", "smtp.gmail.com");
	    property.put("mail.smtp.port", "587");
	    
	    Session session = Session.getInstance(property, new Authenticator() {
	    	@Override
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		return new PasswordAuthentication(senderMail, password);
	    	}
	    	
	    });
	    
        Message message = prepareMessage(session, senderMail, recieverMail, subject, msg);
       
	    try {
         	Transport.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//log.warning("\nEXCEPTION : -- "+e.getMessage());
			e.printStackTrace();
		}
	}
	

	private static Message prepareMessage(Session session, String senderMail, InternetAddress recieverMail[], String subject, String msg) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(senderMail));
			message.setRecipients(Message.RecipientType.TO, recieverMail);
			message.setSubject(subject);
			message.setText(subject);
			message.setText(msg);
			return message;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null,e);
			e.printStackTrace();
			return null;
		}
	}
}
