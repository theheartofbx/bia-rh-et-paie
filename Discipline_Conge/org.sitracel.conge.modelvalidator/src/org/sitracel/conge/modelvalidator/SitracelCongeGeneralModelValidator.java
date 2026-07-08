package org.sitracel.conge.modelvalidator;

import java.sql.Timestamp;
import java.util.Calendar;

import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.sitracel.conge.HRCongeRepository;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.conge.modelvalidator.service.CongeAbsenceValidatorService;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.NotificationControler;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.time.HRCalendrierService;

/**
 * Point central de traitement des evenements conges.
 *
 * CORRECTION (Session 9, suite) : detecterTransitionEtatConge()
 * utilisait holiday.isApprobation() pour detecter la transition et
 * declencher la notification HOLIDAY_APPROVED - or IsApprobation est
 * une colonne virtuelle de droit ("ai-je le droit d'approuver"),
 * calculee pour l'utilisateur qui consulte, pas l'etat reel de
 * l'enregistrement. Le bon champ est IsApprouve (etat persistant).
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
            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_IsApprobation_Createur, false);
            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_IsValidation_Createur, false);
            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_Date_Emission,
                new Timestamp(System.currentTimeMillis()));

            String erreur = validerCoherenceConge((MHRHoliday) po);
            if (erreur != null) {
                return erreur;
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

    /**
     * CORRECTION : isApprouve()/COLUMNNAME_IsApprouve (etat reel),
     * pas isApprobation()/COLUMNNAME_IsApprobation (droit virtuel).
     */
    private static void detecterTransitionEtatConge(MHRHoliday holiday, PO po) {

        if (holiday.isApprouve()
                && !getBooleanOld(po, MHRHoliday.COLUMNNAME_IsApprouve)) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_APPROVED, po);
            return;
        }

        if (holiday.isDesapprouve()
                && !getBooleanOld(po, MHRHoliday.COLUMNNAME_IsDesapprouve)) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_DISAPPROVED, po);
            return;
        }

        if (holiday.isValidee()
                && !getBooleanOld(po, MHRHoliday.COLUMNNAME_IsValidee)) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_VALIDATED, po);
            return;
        }

        if (holiday.isRejetee()
                && !getBooleanOld(po, MHRHoliday.COLUMNNAME_IsRejetee)) {
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

    // =========================================================================
    // UTILITAIRE
    // =========================================================================

    private static boolean getBooleanOld(PO po, String columnName) {
        Object oldValue = po.get_ValueOld(columnName);
        if (oldValue instanceof Boolean) {
            return (Boolean) oldValue;
        }
        if (oldValue instanceof String) {
            return "Y".equalsIgnoreCase((String) oldValue);
        }
        return false;
    }
}
