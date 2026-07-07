package org.sitracel.absence.modelvalidator.service;

import java.sql.Timestamp;

import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.absence.model.MHRAbsence;
import org.sitracel.absence.model.MHRAbsenceCompensation;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.conge.HRCongeRepository;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.employe.HREmployeService;
import org.sitracel.parametrage.HRParametreService;
import org.sitracel.time.HRCalendrierService;

/**
 * Service - logique metier propre a Absence : coherence de la date,
 * seuil de demandes d'explication, et compensation d'absence.
 *
 * Remplace la partie Absence de CongeAbsenceValidatorService
 * (restee dans conge.modelvalidator pour ce qui concerne Conge).
 *
 * Depuis Session 9 (v2) : les 4 controles de coherence de date, jusque-la
 * uniquement dans le Callout (CalloutDateAbsenceConforme, actif seulement
 * dans le contexte d'une fenetre), sont desormais aussi appliques ici,
 * au niveau du ModelValidator - donc garantis quel que soit le point
 * d'entree (fenetre, import, API). Le Callout reste en place pour le
 * confort de saisie immediat ; les deux ne sont pas redondants.
 */
public final class AbsenceValidatorService {

    private AbsenceValidatorService() {}

    // =========================================================================
    // EVENEMENTS SUR HR_ABSENCE
    // =========================================================================

    /**
     * Traite les evenements sur MHRAbsence.
     *
     * Retourne un message d'erreur (bloque l'enregistrement) si la
     * coherence de la date n'est pas respectee, sinon null.
     *
     * CORRECTION BUG (Session 8) : traiterDemandeExplication n'est
     * appelee QUE si IsDemandeExplication passe de false a true
     * (plus de doublon).
     */
    public static String traiterEvenement(PO po, MHRAbsence absence, int type) {

        // A la creation : verifier la coherence de la date, horodater,
        // et verifier le seuil d'absences
        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            String erreur = validerCoherenceDate(absence);
            if (erreur != null) {
                return erreur;
            }
            absence.setDate_Emission(new Timestamp(System.currentTimeMillis()));
            traiterDemandeExplicationSuiteAbsence(absence);
        }

        // Avant modification : ne revalider la date que si elle a
        // reellement change (evite un faux positif sur un enregistrement
        // qui se retrouve lui-meme en base)
        if (ModelValidator.TYPE_BEFORE_CHANGE == type) {
            if (dateAChange(po, absence.getDate_Absence())) {
                String erreur = validerCoherenceDate(absence);
                if (erreur != null) {
                    return erreur;
                }
            }
        }

        // Avant suppression : annuler la demande d'explication liee
        if (ModelValidator.TYPE_BEFORE_DELETE == type) {
            annulerDemandeExplicationSuiteAbsence(absence);
        }

        // Apres modification : traiter uniquement si IsDemandeExplication
        // vient de passer a true (transition false -> true)
        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            boolean nouvelleValeur = absence.isDemandeExplication();
            boolean ancienneValeur = getBooleanOld(po,
                MHRAbsence.COLUMNNAME_IsDemandeExplication);

            if (nouvelleValeur && !ancienneValeur) {
                annulerDemandeExplicationSuiteAbsence(absence);
                traiterDemandeExplicationSuiteAbsence(absence);
            }
        }

        return null;
    }

    /**
     * Verifie que la date d'absence ne tombe pas :
     *   - sur une absence deja enregistree
     *   - un dimanche ou un jour ferie
     *   - un jour de conge de l'employe
     *   - un jour de suspension de l'employe
     *
     * Memes regles que CalloutDateAbsenceConforme, appliquees ici comme
     * un vrai blocage (pas juste un effacement de champ a l'ecran).
     */
    private static String validerCoherenceDate(MHRAbsence absence) {
        Timestamp date = absence.getDate_Absence();
        int bpartnerId = absence.getC_BPartner_ID();
        if (date == null || bpartnerId <= 0) {
            return null;
        }
        String trxName = absence.get_TrxName();

        if (HRCongeRepository.isAbsenceExist(date, bpartnerId, trxName)) {
            return "Une absence a deja ete enregistree a cette date pour cet employe.";
        }
        if (MHRPublicHoliday.isJourFerie(date, trxName)) {
            return "La date choisie est un dimanche ou un jour ferie.";
        }
        if (HRCongeRepository.isJourCongesNonRejeteByNameConge(bpartnerId, "Annuel", date, trxName)) {
            return "La date choisie fait partie des jours de conge de l'employe.";
        }
        if (HRCongeRepository.isJourSuspensionNonRejete(bpartnerId, date, trxName)) {
            return "La date choisie est comprise dans une periode de suspension de l'employe.";
        }
        return null;
    }

    /**
     * Vérifie si le seuil d'absences non traitées est atteint
     * et crée une demande d'explication si nécessaire.
     */
    private static void traiterDemandeExplicationSuiteAbsence(MHRAbsence absence) {
        if (absence == null || absence.getC_BPartner_ID() <= 0) return;

        int seuil = HRParametreService.getParametreNumerique(
            "Absence Max Avant Demande Explication");
        if (seuil <= 0) return;

        Timestamp debutAnnee = HRCalendrierService.getFirstDayOfThisYear();
        Timestamp finAnnee   = HRCalendrierService.getLastDayOfThisYear();

        int nbAbsences = HRCongeRepository.getNombreAbsencesNonTraitees(
            absence.getC_BPartner_ID(), debutAnnee, finAnnee, absence.get_TrxName());

        if (nbAbsences >= seuil) {
            creerDemandeExplication(absence);
        }
    }

    /**
     * Annule la demande d'explication liée à une absence supprimée.
     */
    private static void annulerDemandeExplicationSuiteAbsence(MHRAbsence absence) {
        if (absence == null || absence.getC_BPartner_ID() <= 0) return;

        MHRDemandeExplication demandeExplication =
            getDemandeExplicationByAbsence(absence.getHR_Absence_ID(),
                                            absence.get_TrxName());

        if (demandeExplication != null) {
            demandeExplication.setIsActive(false);
            demandeExplication.save(absence.get_TrxName());
        }
    }

    // =========================================================================
    // EVENEMENTS SUR HR_ABSENCE_COMPENSATION
    // =========================================================================

    public static void traiterCompensation(MHRAbsenceCompensation absenceCompense, int type) {

        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            activerCompensationConge(absenceCompense);
        }

        if (ModelValidator.TYPE_BEFORE_DELETE == type) {
            desactiverCompensationConge(absenceCompense);
        }

        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            desactiverCompensationConge(absenceCompense);
            activerCompensationConge(absenceCompense);
        }
    }

    private static void activerCompensationConge(MHRAbsenceCompensation absenceCompense) {
        if (absenceCompense == null) return;

        if (absenceCompense.getAbsence_ID() > 0) {
            MHRAbsence absence = new MHRAbsence(
                Env.getCtx(), absenceCompense.getAbsence_ID(), null);
            if (absence != null) {
                absence.setIsDemandeExplicationTraite(true);
                absence.save(null);
            }
        }
    }

    private static void desactiverCompensationConge(MHRAbsenceCompensation absenceCompense) {
        if (absenceCompense == null) return;

        if (absenceCompense.getAbsence_ID() > 0) {
            MHRAbsence absence = new MHRAbsence(
                Env.getCtx(), absenceCompense.getAbsence_ID(), null);
            if (absence != null) {
                absence.setIsDemandeExplicationTraite(false);
                absence.save(null);
            }
        }
    }

    // =========================================================================
    // UTILITAIRES INTERNES
    // =========================================================================

    private static MHRDemandeExplication getDemandeExplicationByAbsence(
            int absenceId, String trxName) {
        String sql = "SELECT HR_DemandeExplication_ID"
            + " FROM HR_DemandeExplication"
            + " WHERE HR_Absence_ID = ?"
            + " AND IsActive = 'Y'"
            + " LIMIT 1";

        int id = org.compiere.util.DB.getSQLValue(trxName, sql, absenceId);
        if (id > 0) {
            return new MHRDemandeExplication(Env.getCtx(), id, trxName);
        }
        return null;
    }

    private static void creerDemandeExplication(MHRAbsence absence) {
        BeanIdentifiant emetteur = HREmployeService.getIdentifiantByBPartner(
            absence.getEmis_Par_Nom_ID(), absence.get_TrxName());

        if (emetteur == null) return;

        MHRDemandeExplication demande = new MHRDemandeExplication(
            Env.getCtx(), null, absence.get_TrxName());

        demande.setC_BPartner_ID(absence.getC_BPartner_ID());
        demande.setHR_Absence_ID(absence.getHR_Absence_ID());
        demande.setEmis_Par_Nom_ID(emetteur.getNumEmploye());
        demande.setEmis_Par_Matricule(emetteur.getMatriculeEmploye());
        demande.setEmis_Par_Poste_ID(emetteur.getNumeroPoste());
        demande.setDate_Emission(new Timestamp(System.currentTimeMillis()));
        demande.setIsActive(true);
        demande.save(absence.get_TrxName());

        absence.setIsDemandeExplication(true);
        absence.save(absence.get_TrxName());
    }

    /**
     * Compare l'ancienne et la nouvelle valeur de Date_Absence.
     * Retourne true si la date a reellement change (ou si l'ancienne
     * valeur est inconnue - par prudence).
     */
    private static boolean dateAChange(PO po, Timestamp nouvelleDate) {
        Object ancienneValeur = po.get_ValueOld(MHRAbsence.COLUMNNAME_Date_Absence);
        if (!(ancienneValeur instanceof Timestamp)) {
            return true;
        }
        Timestamp ancienneDate = (Timestamp) ancienneValeur;
        if (nouvelleDate == null) {
            return ancienneDate != null;
        }
        return !nouvelleDate.equals(ancienneDate);
    }

    /**
     * Recupere l'ancienne valeur booleenne d'un champ avant modification.
     */
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
