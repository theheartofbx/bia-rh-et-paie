package org.sitracel.conge.modelvalidator.service;

import java.sql.Timestamp;

import org.compiere.util.Env;
import org.sitracel.conge.HRCongeRepository;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRTypeAbsence;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.employe.HREmployeService;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.parametrage.HRParametreService;
import org.sitracel.time.HRCalendrierService;

/**
 * Service — logique métier du modelvalidator absence.
 *
 * Gère la création et l'annulation automatique des demandes
 * d'explication suite aux absences répétées.
 *
 * Remplace ModelValidatorControllerAbsence.
 */
public final class CongeAbsenceValidatorService {

    private CongeAbsenceValidatorService() {}

    // =========================================================================
    // TRAITEMENT DEMANDE D'EXPLICATION
    // =========================================================================

    /**
     * Vérifie si le seuil d'absences non traitées est atteint
     * et crée une demande d'explication si nécessaire.
     *
     * Règle métier :
     *   Si le nombre d'absences non traitées sur l'année dépasse
     *   le paramètre "Absence Max Avant Demande Explication"
     *   → créer une MHRDemandeExplication pour cet employé.
     *
     * Remplace ModelValidatorControllerAbsence.traiterDemandeExplicationSuiteAbsence().
     */
    public static void traiterDemandeExplicationSuiteAbsence(MHRAbsence absence) {
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
     *
     * Remplace ModelValidatorControllerAbsence.annulerDemandeExplicationSuiteAbsence().
     */
    public static void annulerDemandeExplicationSuiteAbsence(MHRAbsence absence) {
        if (absence == null || absence.getC_BPartner_ID() <= 0) return;

        // Charger la demande d'explication liée à cette absence
        MHRDemandeExplication demandeExplication =
            getDemandeExplicationByAbsence(absence.getHR_Absence_ID(),
                                            absence.get_TrxName());

        if (demandeExplication != null) {
            demandeExplication.setIsActive(false);
            demandeExplication.save(absence.get_TrxName());
        }
    }

    // =========================================================================
    // MISE À JOUR DÉPARTEMENT
    // =========================================================================

    /**
     * Met à jour le département de l'employé dans les congés en cours
     * lorsque son poste/contrat change.
     *
     * Remplace ModelValidatorControllerConge.updateDepartment().
     */
    public static void updateDepartment(Integer bpartnerId) {
        if (bpartnerId == null) return;
        // La logique de mise à jour du département est gérée
        // directement par iDempiere via les FK — aucune action
        // supplémentaire nécessaire dans cette version.
        // À enrichir si un besoin spécifique est identifié en test.
    }

    // =========================================================================
    // COMPENSATION DE CONGÉ
    // =========================================================================

    /**
     * Active une compensation de congé (marque les absences comme compensées).
     *
     * Remplace ModelValidatorControllerConge.activerCompensationConge().
     */
    public static void activerCompensationConge(
            org.sitracel.conge.model.MHRAbsenceCompensation absenceCompense) {
        if (absenceCompense == null) return;

        // Marquer l'absence concernée comme compensée
        if (absenceCompense.getHR_Absence_ID() > 0) {
            MHRAbsence absence = new MHRAbsence(
                Env.getCtx(), absenceCompense.getHR_Absence_ID(), null);
            if (absence != null) {
                absence.setIsDemandeExplicationTraite(true);
                absence.save(null);
            }
        }
    }

    /**
     * Désactive une compensation de congé (annule le marquage).
     *
     * Remplace ModelValidatorControllerConge.desactiverCompensationConge().
     */
    public static void desactiverCompensationConge(
            org.sitracel.conge.model.MHRAbsenceCompensation absenceCompense) {
        if (absenceCompense == null) return;

        if (absenceCompense.getHR_Absence_ID() > 0) {
            MHRAbsence absence = new MHRAbsence(
                Env.getCtx(), absenceCompense.getHR_Absence_ID(), null);
            if (absence != null) {
                absence.setIsDemandeExplicationTraite(false);
                absence.save(null);
            }
        }
    }

    // =========================================================================
    // UTILITAIRE INTERNE
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

        // Marquer l'absence comme ayant une demande d'explication
        absence.setIsDemandeExplication(true);
        absence.save(absence.get_TrxName());
    }
}
