package org.sitracel.absence.modelvalidator.service;

import java.sql.Timestamp;

import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.CLogger;
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

    private static final CLogger log = CLogger.getCLogger(AbsenceValidatorService.class);

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

        // A la creation : verifier la coherence de la date et horodater
        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            String erreur = validerCoherenceDate(absence);
            if (erreur != null) {
                return erreur;
            }
            absence.setDate_Emission(new Timestamp(System.currentTimeMillis()));
        }

        // Apres creation : verifier le seuil d'absences
        // (deplace de BEFORE_NEW car l'absence doit exister en base
        // avant de creer la demande d'explication et ses notifications)
        if (ModelValidator.TYPE_AFTER_NEW == type) {
            traiterDemandeExplicationSuiteAbsence(absence);
        }

        // Avant modification : ne revalider la date que si elle a
        // reellement change
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
     * Cle de contexte utilisee par CongeProcessService et
     * DisciplineProcessService pour signaler que les absences en cours
     * de creation sont generees par le systeme (validation de conge
     * ou de suspension). Dans ce cas, les controles "jour de conge"
     * et "jour de suspension" sont bypasses - le systeme cree
     * legitimement des absences dans ces periodes.
     *
     * Les controles doublon, dimanche et jour ferie restent actifs
     * en toutes circonstances (filet de securite).
     */
    public static final String CTX_CREATION_ABSENCE_SYSTEME =
        "#IS_CREATION_ABSENCE_SYSTEME";

    /**
     * Verifie que la date d'absence ne tombe pas :
     *   - sur une absence deja enregistree
     *   - un dimanche ou un jour ferie
     *   - un jour de conge de l'employe (sauf creation systeme)
     *   - un jour de suspension de l'employe (sauf creation systeme)
     *
     * Memes regles que CalloutDateAbsenceConforme, appliquees ici comme
     * un vrai blocage (pas juste un effacement de champ a l'ecran).
     *
     * Lorsque le drapeau CTX_CREATION_ABSENCE_SYSTEME est present dans
     * le contexte, les controles 4 et 5 sont bypasses car c'est le
     * systeme lui-meme qui cree les absences lors de la validation
     * d'un conge ou d'une suspension.
     */
    private static String validerCoherenceDate(MHRAbsence absence) {
        Timestamp date = absence.getDate_Absence();
        int bpartnerId = absence.getC_BPartner_ID();
        if (date == null || bpartnerId <= 0) {
            return null;
        }
        String trxName = absence.get_TrxName();

        // Controle 1 - doublon (toujours actif)
        if (HRCongeRepository.isAbsenceExist(date, bpartnerId, trxName)) {
            return "Une absence a deja ete enregistree a cette date pour cet employe.";
        }
        // Controle 2 - dimanche (toujours actif)
        if (MHRPublicHoliday.isDimanche(date)) {
            return "La date choisie est un dimanche.";
        }
        // Controle 3 - jour ferie (toujours actif)
        String nomFerie = MHRPublicHoliday.getNomJourFerie(date, trxName);
        if (nomFerie != null) {
            return "La date choisie est un jour ferie (" + nomFerie + ").";
        }

        // Controles 4 et 5 - bypasses si creation systeme
        boolean creationSysteme = "Y".equals(
            Env.getCtx().getProperty(CTX_CREATION_ABSENCE_SYSTEME));

        if (!creationSysteme) {
            // Controle 4 - jour de conge
            if (HRCongeRepository.isJourCongesNonRejeteByNameConge(
                    bpartnerId, "Annuel", date, trxName)) {
                return "La date choisie fait partie des jours de conge de l'employe.";
            }
            // Controle 5 - jour de suspension
            if (HRCongeRepository.isJourSuspensionNonRejete(bpartnerId, date, trxName)) {
                return "La date choisie est comprise dans une periode de suspension de l'employe.";
            }
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
        String sql = "SELECT HR_Demande_Explication_ID"
            + " FROM HR_Demande_Explication"
            + " WHERE HR_Absence_ID = ?"
            + " AND IsActive = 'Y'"
            ;

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

        // Identifiant de l'employe concerne
        BeanIdentifiant employe = HREmployeService.getIdentifiantByBPartner(
            absence.getC_BPartner_ID(), absence.get_TrxName());

        if (employe == null) return;

        // Delai de reponse par defaut (parametre systeme)
        int delaiReponseId = HRParametreService.getParametreNumerique(
            "Délai Réponse Demande Explication");
        if (delaiReponseId <= 0) {
            delaiReponseId = 404; // fallback : 2 jours
        }

        MHRDemandeExplication demande = new MHRDemandeExplication(
            Env.getCtx(), 0, absence.get_TrxName());

        // Employe concerne
        demande.setC_BPartner_ID(absence.getC_BPartner_ID());
        demande.set_ValueOfColumn("Matricule_Employe",
            employe.getMatriculeEmploye() != null ? employe.getMatriculeEmploye() : "");
        demande.set_ValueOfColumn("Poste_Employe_ID", employe.getNumeroPoste());

        // Emetteur
        demande.setEmis_Par_Nom_ID(emetteur.getNumEmploye());
        demande.setEmis_Par_Matricule(emetteur.getMatriculeEmploye());
        demande.setEmis_Par_Poste_ID(emetteur.getNumeroPoste());

        // Lien avec l'absence
        demande.setHR_Absence_ID(absence.getHR_Absence_ID());

        // Champs obligatoires
        demande.setDate_Emission(new Timestamp(System.currentTimeMillis()));
        demande.set_ValueOfColumn("HR_Delai_Reponse_ID", delaiReponseId);
        demande.set_ValueOfColumn("Motif_Demande_Explication",
            "Seuil d'absences injustifiees atteint");
        demande.set_ValueOfColumn("Name",
            "DE-" + employe.getMatriculeEmploye() + "-" + System.currentTimeMillis());
        demande.setIsActive(true);

        if (!demande.save(absence.get_TrxName())) {
            log.warning("creerDemandeExplication : echec de creation pour BPartner "
                + absence.getC_BPartner_ID());
            return;
        }

        absence.setIsDemandeExplication(true);
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
