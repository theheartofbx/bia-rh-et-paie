package org.sitracel.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.sitracel.bean.BeanAbsence;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.bean.BeanPeriodeConge;

public class GeneralController {

	public static Timestamp ajouterNombreJour(Timestamp date, int nombreJour) {
		Calendar cal = Calendar.getInstance();		
		cal.setTime(date);
		
		if(nombreJour>=0) {
			int q = nombreJour / 6;
			int r = nombreJour % 6;
			for (int i = 0; i < r-1; i++) {
				if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
					cal.add(Calendar.DAY_OF_WEEK, 1);
				}
				cal.add(Calendar.DAY_OF_WEEK, 1);
			}
			cal.add(Calendar.DAY_OF_WEEK, (q*7));
			if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
				cal.add(Calendar.DAY_OF_WEEK, 1);
			}
		}
		return new Timestamp(cal.getTime().getTime());		
	}	

	public static Timestamp retirerNombreJour(Timestamp date, int nombreJour) {
		Calendar cal = Calendar.getInstance();		
		cal.setTime(date);
		
		if(nombreJour>=0) {
			int q = nombreJour / 6;
			int r = nombreJour % 6;
			for (int i = 0; i < r-1; i++) {
				if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
					cal.add(Calendar.DAY_OF_WEEK, -1);
				}
				cal.add(Calendar.DAY_OF_WEEK, -1);
			}
			cal.add(Calendar.DAY_OF_WEEK, (q*-7));
			if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
				cal.add(Calendar.DAY_OF_WEEK, -1);
			}
		}
		return new Timestamp(cal.getTime().getTime());		
	}
	
	public static Timestamp ajusterDebut(Timestamp dateDebut, Timestamp dateFin) {
		Timestamp resultat = null;
		if(dateDebut!=null  && dateFin!=null) {
			Timestamp[] joursFeries = GeneralSqlController.getAllJoursFeries(dateDebut, dateFin, null);
			Calendar cal = Calendar.getInstance();
			for (Timestamp timestamp : joursFeries) {
				cal.setTime(timestamp);
				if(cal.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY) {
					dateDebut = retirerNombreJour(dateDebut, 1);
				}
			}
			resultat = dateDebut;
		}
		return resultat;
	}	
	
	public static Timestamp ajusterFin(Timestamp dateDebut, Timestamp dateFin) {
		Timestamp resultat = null;
		if(dateDebut!=null  && dateFin!=null) {
			Timestamp[] joursFeries = GeneralSqlController.getAllJoursFeries(dateDebut, dateFin, null);
			Calendar cal = Calendar.getInstance();			
			for (Timestamp timestamp : joursFeries) {
				cal.setTime(timestamp);
				if(cal.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY) {
					dateFin = ajouterNombreJour(dateFin, 1);				
				}
			}
			resultat = dateFin;
		}
		return resultat;
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
			Timestamp[] joursFeries = GeneralSqlController.getAllJoursFeries(getFirstDayOfaYear(date), getLastDayOfaYear(date), null);
			Calendar cal = Calendar.getInstance();
			for (Timestamp jourFerie : joursFeries) {
				cal.setTime(jourFerie);
				if(cal.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY && date.compareTo(jourFerie)==0) {
					resultat = retirerNombreJour(resultat, 1);
				}
			}
		}
		return resultat;
	}
	
	public static Timestamp ajusterenAjoutant(Timestamp date) {
		Timestamp resultat = date;
		if(date!=null) {
			Timestamp[] joursFeries = GeneralSqlController.getAllJoursFeries(getFirstDayOfaYear(date), getLastDayOfaYear(date), null);
			Calendar cal = Calendar.getInstance();
			for (Timestamp jourFerie : joursFeries) {
				cal.setTime(jourFerie);
				if(cal.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY && date.compareTo(jourFerie)==0) {
					resultat = ajouterNombreJour(resultat, 1);
				}
			}
		}
		return resultat;
	}

	public static boolean isJourFerie(Timestamp date) {
		boolean resultat = true;
		if(date!=null) {
			resultat = false;
			Calendar cal = Calendar.getInstance();		
			cal.setTime(date);
			if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
				resultat = true;
			}
			else {
				cal.add(Calendar.DAY_OF_WEEK, -1);
				Timestamp hier = new Timestamp(cal.getTime().getTime());
				cal = Calendar.getInstance();		
				cal.setTime(date);
				cal.add(Calendar.DAY_OF_WEEK, 1);
				Timestamp demain = new Timestamp(cal.getTime().getTime());
				
				Timestamp[] joursFeries = GeneralSqlController.getAllJoursFeries(hier, demain, null);
				for(Timestamp jourFerie:joursFeries) {
					if(jourFerie.compareTo(date)==0) {
						resultat = true;
					}
				}		
			}
			
		}
		return resultat;
	}	

	public static boolean isCongeAnnuel(Integer cbpartnerid, Timestamp date) {
		boolean resultat = true;
		if(date!=null && cbpartnerid!=null) {
			resultat = false;
			Calendar cal = Calendar.getInstance();		
			cal.setTime(date);
			if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
				resultat = true;
			}
			else {
				BeanPeriode[] congesAnnuels = GeneralSqlController.getAllCongesAnnuel(cbpartnerid, date, null);
				for(BeanPeriode congeAnnuel:congesAnnuels) {
					if(date.after(congeAnnuel.getDateDebutConge()) && date.before(congeAnnuel.getDateFinConge())) {
						resultat = true;
					}
				}		
			}
			
		}
		return resultat;
	}
	
	public static boolean isJourSuspension(Integer cbpartnerid, Timestamp date) {
		boolean resultat = true;
		if(date!=null && cbpartnerid!=null) {
			resultat = false;
			Calendar cal = Calendar.getInstance();		
			cal.setTime(date);
			if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
				resultat = true;
			}
			else {
				BeanPeriode[] periodesSuspension = GeneralSqlController.getAllPeriodeSuspension(cbpartnerid, date, null);
				for(BeanPeriode periodeSuspension:periodesSuspension) {
					if(date.after(periodeSuspension.getDateDebutConge()) && date.before(periodeSuspension.getDateFinConge())) {
						resultat = true;
					}
				}		
			}
			
		}
		return resultat;
	}	
	
	public static BeanPeriode isPeriodeSuspensionIn(Integer cbpartnerid, Timestamp dateDebut, Timestamp dateFin) {
		BeanPeriode resultat = null;
		if(cbpartnerid!=null && dateDebut!=null && dateFin!=null) {
			if(dateDebut.after(dateFin)) {
				Timestamp inter = dateFin;
				dateFin=dateDebut;
				dateDebut=inter;
			}
			BeanPeriode[] periodesSuspension = GeneralSqlController.getAllPeriodeSuspension(cbpartnerid, dateDebut, null);
			for(BeanPeriode periodeSuspension:periodesSuspension) {
				if((dateDebut.after(periodeSuspension.getDateDebutConge()) && dateDebut.before(periodeSuspension.getDateFinConge())) || 
						(dateFin.after(periodeSuspension.getDateDebutConge()) && dateFin.before(periodeSuspension.getDateFinConge()))) {
					resultat = periodeSuspension;
				}
			}	
		}		
		return resultat;
	}
	
	public static BeanAbsence isAbsenceIn(Integer cbpartnerid, Timestamp dateDebut, Timestamp dateFin) {
		BeanAbsence resultat = null;
		if(dateDebut!=null && dateFin!=null && cbpartnerid!=null) {
			BeanAbsence[] absences = GeneralSqlController.getAllAbsenceConge(cbpartnerid, new Timestamp(System.currentTimeMillis()), null);
			for(BeanAbsence absence:absences) {
				if(absence.getDateAbsence().after(dateDebut) && absence.getDateAbsence().before(dateFin)) {
					resultat = absence;
				}
			}	
			
		}
		return resultat;
	}	

	public static BeanPeriode isPeriodeInConge(Integer idEmploye, Timestamp dateDebut, Timestamp dateFin) {
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

	public static boolean seChevauche(BeanPeriodeConge b1, BeanPeriodeConge b2) {
		boolean resultat=false;
		if(b1!=null && b2!=null) {
			if(b1.getDateDebutConge()!=null && b1.getDateFinConge()!=null 
					&& b2.getDateDebutConge()!=null && b2.getDateFinConge()!=null) {
				
				Long j1 = TimeUnit.MILLISECONDS.toDays(b1.getDateDebutConge().getTime());
				Long j2 = TimeUnit.MILLISECONDS.toDays(b1.getDateFinConge().getTime());
				Long gap = Math.abs((j2-j1)/2);
				Long j3 = gap;
				if(j2>j1) {
					j3 = j3+j1;
				}
				else {
					j3 = j3+j2;
				}
				j1 = TimeUnit.MILLISECONDS.toDays(b2.getDateDebutConge().getTime());
				j2 = TimeUnit.MILLISECONDS.toDays(b2.getDateFinConge().getTime());
				Long gap0 = Math.abs((j2-j1)/2);
				Long j30 = gap0;
				if(j2>j1) {
					j30 = j30+j1;
				}
				else {
					j30 = j30+j2;
				}
				if(Math.abs(j3-j30)<Math.abs(gap+gap0)) {
					resultat = true;
				}
			}
		}
		return resultat;
	}
	
	public static boolean seChevauche(Timestamp t1, Timestamp t2, Timestamp p1, Timestamp p2) {
		boolean resultat=false;
		if(t1!=null && t2!=null 
				&& p1!=null && p2!=null) {
			
			Long j1 = TimeUnit.MILLISECONDS.toDays(t1.getTime());
			Long j2 = TimeUnit.MILLISECONDS.toDays(t2.getTime());
			Long gap = Math.abs((j2-j1)/2);
			Long j3 = gap;
			if(j2>j1) {
				j3 = j3+j1;
			}
			else {
				j3 = j3+j2;
			}
			j1 = TimeUnit.MILLISECONDS.toDays(p1.getTime());
			j2 = TimeUnit.MILLISECONDS.toDays(p2.getTime());
			Long gap0 = Math.abs((j2-j1)/2);
			Long j30 = gap0;
			if(j2>j1) {
				j30 = j30+j1;
			}
			else {
				j30 = j30+j2;
			}
			if(Math.abs(j3-j30)<Math.abs(gap+gap0)) {
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
			/*
			 * message.setFrom(new InternetAddress(senderMail));
			 * message.setRecipients(Message.RecipientType.TO, recieverMail);
			 * message.setSubject(subject); message.setText(subject);
			 * 
			 * 
			 * ArrayList<String> adressesMailRH =
			 * ProcessSqlController.getMailRH(ad_Role_RH_ID, null); for(String adresseMail :
			 * adressesMailRH) { if(adresseMail!=null) {
			 * if(adresseMail.matches(".+@.+\\.[a-z]+")) {
			 * message.addRecipient(RecipientType.CC, new InternetAddress(adresseMail)); } }
			 * }
			 * 
			 * ProcessSqlController.getLogo(ad_Role_RH_ID, null); MimeMultipart multipart =
			 * new MimeMultipart("related"); BodyPart messageBodyPart = new MimeBodyPart();
			 * String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
			 * messageBodyPart.setContent(htmlText, "text/html");
			 * multipart.addBodyPart(messageBodyPart);
			 * 
			 * MimeBodyPart imagePart = new MimeBodyPart(); String logopath =
			 * ProcessSqlController.getLogo(ad_Role_RH_ID, null); log.warning("\nOK : "+new
			 * File(logopath).getAbsolutePath()); //DataSource fds = new FileDataSource(
			 * logopath);
			 * 
			 * 
			 * //messageBodyPart.setDataHandler(new DataHandler(fds));
			 * imagePart.setHeader("Content-ID", "<image>");
			 * imagePart.setDisposition(MimeBodyPart.INLINE);
			 * imagePart.attachFile(logopath);
			 * 
			 * 
			 * multipart.addBodyPart(imagePart);
			 * 
			 * message.setContent(multipart);
			 */
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
