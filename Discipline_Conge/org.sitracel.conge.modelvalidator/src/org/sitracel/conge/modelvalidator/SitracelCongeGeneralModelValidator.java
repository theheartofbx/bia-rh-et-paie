package org.sitracel.conge.modelvalidator;

import java.sql.Timestamp;
import java.util.Calendar;

import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.sitracel.conge.HRCongeRepository;
import org.sitracel.conge.model.CongeStatut;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.conge.modelvalidator.service.CongeAbsenceValidatorService;
import org.sitracel.employe.HRContratService;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.NotificationControler;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.time.HRCalendrierService;

/**
 * Point central de traitement des evenements conges.
 *
 * Depuis Session 9 (v4) : verification de l'eligibilite contrat/
 * affectation (HRContratService.estEligible()) a la creation, avec
 * derogation possible pour les roles RH (Lot 1, integration prevue
 * depuis le debut du plan, jamais faite jusqu'ici).
 *
 * NE PAS ajouter de logique metier directement ici.
 */
public class SitracelCongeGeneralModelValidator {

    private static final CLogger log = CLogger.getCLogger(SitracelCongeGeneralModelValidator.class);

    // =========================================================================
    // CONGES - detection des transitions d'etat + coherence
    // =========================================================================

    public static String conge(PO po, int type) {

        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            MHRHoliday holiday = (MHRHoliday) po;

            String erreurEligibilite = validerEligibilite(holiday);
            if (erreurEligibilite != null) {
                return erreurEligibilite;
            }

            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_HR_CongeStatut_ID, CongeStatut.EMIS);
            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_IsApprobation_Createur, false);
            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_IsValidation_Createur, false);
            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_Date_Emission,
                new Timestamp(System.currentTimeMillis()));

            String erreurCoherence = validerCoherenceConge(holiday);
            if (erreurCoherence != null) {
                return erreurCoherence;
            }
        }

        if (ModelValidator.TYPE_BEFORE_CHANGE == type) {
            MHRHoliday holiday = (MHRHoliday) po;
            if (datesSouhaiteesOntChange(po, holiday)) {
                String erreur = validerCoherenceConge(holiday);
                if (erreur != null) {
                    return erreur;
                }
            }
        }

        if (ModelValidator.TYPE_AFTER_NEW == type) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_CREATED, po);
        }

        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            MHRHoliday holiday = (MHRHoliday) po;
            detecterTransitionEtatConge(holiday, po);
        }

        return null;
    }

    /**
     * Verifie que l'employe concerne par la demande a un contrat actif
     * et une affectation, sauf derogation pour un role RH.
     */
    private static String validerEligibilite(MHRHoliday conge) {
        if (conge.getC_BPartner_ID() <= 0) {
            return null;
        }

        Timestamp maintenant = new Timestamp(System.currentTimeMillis());
        String trxName = conge.get_TrxName();

        if (HRContratService.estEligible(conge.getC_BPartner_ID(), maintenant, trxName)) {
            return null;
        }

        int adRoleId = Env.getAD_Role_ID(Env.getCtx());
        if (HRContratService.beneficieDerogationEligibilite(adRoleId, trxName)) {
            log.info("Dérogation d'éligibilité appliquée pour C_BPartner_ID "
                + conge.getC_BPartner_ID() + " (rôle RH)");
            return null;
        }

        String motif = HRContratService.getMotifInaligibilite(
            conge.getC_BPartner_ID(), maintenant, trxName);
        return "Impossible de créer cette demande de congé : "
            + (motif != null ? motif : "employé non éligible.");
    }

    private static String validerCoherenceConge(MHRHoliday conge) {
        if (conge.getEmission_Conge_ID() <= 0) {
            return null;
        }

        MHRTypeConge typeConge = new MHRTypeConge(
            Env.getCtx(), conge.getEmission_Conge_ID(), conge.get_TrxName());
        if (!typeConge.isCongeAnnuel()) {
            return null;
        }

        int bpartnerId = conge.getC_BPartner_ID();
        Timestamp dateDebut = conge.getDate_Debut_Souhaitee();
        Timestamp dateFin = conge.getDate_Fin_Souhaitee();
        if (bpartnerId <= 0 || dateDebut == null || dateFin == null) {
            return null;
        }
        String trxName = conge.get_TrxName();

        if (!dateDebut.before(dateFin)) {
            return "La date de début ne peut pas être après la date de fin.";
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Timestamp debutAutorise = new Timestamp(cal.getTime().getTime());
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.add(Calendar.YEAR, 1);
        Timestamp finAutorisee = new Timestamp(cal.getTime().getTime());

        if (!dateDebut.after(debutAutorise) || !dateDebut.before(finAutorisee)
                || !dateFin.before(finAutorisee) || !dateFin.after(debutAutorise)) {
            return "Vous devez prendre vos congés au moins 1 semaine à compter"
                + " d'aujourd'hui et au plus tard dans 1 an.";
        }

        if (HRCongeRepository.chevaucheAnyCongeNonRejete(bpartnerId, dateDebut, dateFin, trxName)) {
            return "La période choisie coïncide avec une autre période de congé.";
        }
        if (HRCongeRepository.chevaucheSuspensionNonRejete(bpartnerId, dateDebut, dateFin, trxName)) {
            return "Une période de suspension est déjà émise durant cette période.";
        }
        if (HRCongeRepository.isPeriodeAbsence(bpartnerId, dateDebut, dateFin, trxName)) {
            return "Une absence a déjà été enregistrée durant la période choisie.";
        }

        Integer nbJour = HRCalendrierService.getNombreJourTravaille(dateDebut, dateFin);
        if (nbJour != null) {
            int dejaUtilise = conge.getJours_Conge_Deja_Utilise();
            int total = conge.getJours_Conge_Total();
            if ((total - dejaUtilise - nbJour) < 0) {
                return "La période choisie (" + nbJour + " jour(s)) dépasse le nombre"
                    + " de jours restants (" + (total - dejaUtilise) + " jours).";
            }
        }

        return null;
    }

    private static boolean datesSouhaiteesOntChange(PO po, MHRHoliday holiday) {
        Object ancienDebut = po.get_ValueOld(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee);
        Object ancienFin = po.get_ValueOld(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee);

        if (!(ancienDebut instanceof Timestamp) || !(ancienFin instanceof Timestamp)) {
            return true;
        }

        boolean debutIdentique = ancienDebut.equals(holiday.getDate_Debut_Souhaitee());
        boolean finIdentique = ancienFin.equals(holiday.getDate_Fin_Souhaitee());
        return !(debutIdentique && finIdentique);
    }

    private static void detecterTransitionEtatConge(MHRHoliday holiday, PO po) {

        Object ancienneValeur = po.get_ValueOld(MHRHoliday.COLUMNNAME_HR_CongeStatut_ID);
        int ancienStatut = (ancienneValeur instanceof Integer) ? (Integer) ancienneValeur : -1;
        int nouveauStatut = holiday.getHR_CongeStatut_ID();

        if (ancienStatut == nouveauStatut) {
            return;
        }

        if (nouveauStatut == CongeStatut.APPROUVE) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_APPROVED, po);
        } else if (nouveauStatut == CongeStatut.DESAPPROUVE) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_DISAPPROVED, po);
        } else if (nouveauStatut == CongeStatut.VALIDE) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_VALIDATED, po);
        } else if (nouveauStatut == CongeStatut.REJETE) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_REJECTED, po);
        }
    }

    // =========================================================================
    // AFFECTATION EMPLOYE (changement de poste/contrat)
    // =========================================================================

    public static void employeeJobD(MHRElementBasePaieEmploye lastElement, int type) {
        if (ModelValidator.TYPE_AFTER_CHANGE == type
                || ModelValidator.TYPE_AFTER_NEW == type) {
            CongeAbsenceValidatorService.updateDepartment(lastElement.getC_BPartner_ID());
        }
    }
}
