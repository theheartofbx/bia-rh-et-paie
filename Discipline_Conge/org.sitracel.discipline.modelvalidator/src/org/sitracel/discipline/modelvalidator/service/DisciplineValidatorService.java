package org.sitracel.discipline.modelvalidator.service;

import java.sql.Timestamp;

import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.conge.HRCongeRepository;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRDureeSanction;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.NotificationControler;
import org.sitracel.time.HRCalendrierService;
import org.sitracel.employe.HRContratService;

/**
 * Service — logique métier du modelvalidator discipline.
 *
 * Gère les transitions d'état des sanctions et demandes d'explication,
 * déclenche les notifications correspondantes, et applique les
 * garde-fous de validation.
 *
 * Depuis cette session : duplication des garde-fous du callout
 * (CalloutDureeSuspension/CalloutDateDebutApplication) côté serveur —
 * même principe déjà appliqué à Congé et Absence. Un callout ne se
 * déclenche que depuis l'écran iDempiere ; seul le ModelValidator
 * garantit la règle peu importe l'origine de l'enregistrement (import,
 * API, workflow...).
 *
 * Remplace ModelValidatorDisciplineController.
 */
public final class DisciplineValidatorService {

    private DisciplineValidatorService() {}

    // =========================================================================
    // DEMANDES D'EXPLICATION
    // =========================================================================

    /**
     * Traite les événements sur MHRDemandeExplication.
     *
     * À la création : horodater l'émission et notifier l'employé.
     * À la modification : détecter si une réponse vient d'être saisie.
     */
    public static String demandeExplication(MHRDemandeExplication demandeExplication,
                                           int type) {

        // À la création : controles + horodater + nommer
        if (ModelValidator.TYPE_BEFORE_NEW == type) {

            int bpartnerId = demandeExplication.getC_BPartner_ID();
            String trxName = demandeExplication.get_TrxName();
            Timestamp maintenant = new Timestamp(System.currentTimeMillis());

            // A. Eligibilite : contrat + affectation actifs
            if (bpartnerId > 0 && !"Y".equals(demandeExplication.get_ValueAsString("IsCreatedBySystem"))) {
                if (!HRContratService.estEligible(bpartnerId, maintenant, trxName)) {
                    String motif = HRContratService.getMotifInaligibilite(bpartnerId, maintenant, trxName);
                    return "Impossible de creer cette demande d'explication : "
                        + (motif != null ? motif : "employe non eligible.");
                }
            }

            // B. Emetteur habilite : superieur hierarchique ou RH
            if (!"Y".equals(demandeExplication.get_ValueAsString("IsCreatedBySystem"))) {
                int adUserId = Env.getAD_User_ID(Env.getCtx());
                int emitterBP = org.compiere.util.DB.getSQLValue(trxName,
                    "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID = ?", adUserId);
                if (emitterBP == bpartnerId) {
                    return "Un employe ne peut pas emettre une demande d'explication pour lui-meme.";
                }
                int hasAccess = org.compiere.util.DB.getSQLValue(trxName,
                    "SELECT adempiere.fn_has_access(?, ?)", bpartnerId, adUserId);
                if (hasAccess != 1) {
                    return "Vous n'etes pas autorise a emettre une demande d'explication pour cet employe.";
                }
            }

            demandeExplication.setDate_Emission(maintenant);

            // Nom unique : DE-AAAA-NNNNN
            int nextSeq = org.compiere.util.DB.getSQLValue(trxName,
                "SELECT COALESCE(MAX(HR_Demande_Explication_ID), 0) + 1 FROM HR_Demande_Explication");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            String nomDE = String.format("DE-%d-%05d", cal.get(java.util.Calendar.YEAR), nextSeq);
            demandeExplication.setName(nomDE);
        }

        // Après création : notifier l'employé
        if (ModelValidator.TYPE_AFTER_NEW == type) {
            NotificationControler.notify(
                NotificationEvent.DEMANDE_EXPLICATION_CREATED,
                demandeExplication);
        }

        // Avant modification : horodater si une réponse vient d'être saisie
        if (ModelValidator.TYPE_BEFORE_CHANGE == type) {
            MHRDemandeExplication ancienne = new MHRDemandeExplication(
                Env.getCtx(), demandeExplication.get_ID(), null);
            if (ancienne != null) {
                String ancienneReponse = ancienne.getReponse_Demande_Explication();
                String nouvelleReponse = demandeExplication.getReponse_Demande_Explication();
                if (nouvelleReponse != null && !nouvelleReponse.equals(ancienneReponse)) {
                    demandeExplication.setDate_Reponse(
                        new Timestamp(System.currentTimeMillis()));
                }
            }
        }

        // Après modification : notifier si une réponse vient d'être saisie
        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            String ancienneReponse = (String) demandeExplication.get_ValueOld(
                MHRDemandeExplication.COLUMNNAME_Reponse_Demande_Explication);
            String nouvelleReponse = demandeExplication.getReponse_Demande_Explication();

            if (nouvelleReponse != null && !nouvelleReponse.equals(ancienneReponse)) {
                NotificationControler.notify(
                    NotificationEvent.DEMANDE_EXPLICATION_REPLIED,
                    demandeExplication);
            }
        }

        return null;
    }

    // =========================================================================
    // SANCTIONS
    // =========================================================================

    /**
     * Traite les événements sur MHRPunishment.
     *
     * Avant enregistrement (création ou modification) : valide la période
     * de suspension (garde-fou dupliqué du callout).
     * À la création : initialise les flags et horodate.
     * Après création : notifie la création.
     * Après modification : détecte la transition d'état.
     *
     * @return null si tout est valide, un message d'erreur bloquant sinon
     */
    public static String discipline(MHRPunishment punishment, int type) {

        // Garde-fou : valider la période de suspension avant tout enregistrement
        if (ModelValidator.TYPE_BEFORE_NEW == type || ModelValidator.TYPE_BEFORE_CHANGE == type) {
            String erreur = validerPeriodeSuspension(punishment);
            if (erreur != null) {
                return erreur;
            }
        }

        // À la création : controles + initialiser
        if (ModelValidator.TYPE_BEFORE_NEW == type) {

            int bpartnerId = punishment.getC_BPartner_ID();
            String trxName = punishment.get_TrxName();
            Timestamp maintenant = new Timestamp(System.currentTimeMillis());

            // A. Eligibilite : contrat + affectation actifs
            if (bpartnerId > 0) {
                if (!HRContratService.estEligible(bpartnerId, maintenant, trxName)) {
                    String motif = HRContratService.getMotifInaligibilite(bpartnerId, maintenant, trxName);
                    return "Impossible de creer cette mesure disciplinaire : "
                        + (motif != null ? motif : "employe non eligible.");
                }
            }

            // B. Demande d'explication obligatoire
            if (punishment.getDemande_Explication_ID() <= 0) {
                return "Une demande d'explication est obligatoire pour emettre une mesure disciplinaire.";
            }

            // B2. Une DE ne peut servir qu'a une seule sanction
            int sanctionExistante = org.compiere.util.DB.getSQLValue(trxName,
                "SELECT HR_Punishment_ID FROM HR_Punishment "
                + "WHERE Demande_Explication_ID = ? AND IsActive = 'Y'",
                punishment.getDemande_Explication_ID());
            if (sanctionExistante > 0) {
                return "Cette demande d'explication est deja liee a une mesure disciplinaire existante.";
            }

            // C. Emetteur habilite
            int adUserId = Env.getAD_User_ID(Env.getCtx());
            int emitterBP = org.compiere.util.DB.getSQLValue(trxName,
                "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID = ?", adUserId);
            if (emitterBP == bpartnerId) {
                return "Un employe ne peut pas emettre une mesure disciplinaire pour lui-meme.";
            }
            int hasAccess = org.compiere.util.DB.getSQLValue(trxName,
                "SELECT adempiere.fn_has_access(?, ?)", bpartnerId, adUserId);
            if (hasAccess != 1) {
                return "Vous n'etes pas autorise a emettre une mesure disciplinaire pour cet employe.";
            }

            punishment.setIsApprobation_Createur(false);
            punishment.setIsValidation_Createur(false);
            punishment.setDate_Emission(maintenant);

            // Nom unique : SANC-AAAA-NNNNN
            int nextSeq = org.compiere.util.DB.getSQLValue(
                punishment.get_TrxName(),
                "SELECT COALESCE(MAX(HR_Punishment_ID), 0) + 1 FROM HR_Punishment");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            String nomSanction = String.format("SANC-%d-%05d", cal.get(java.util.Calendar.YEAR), nextSeq);
            punishment.setName(nomSanction);
        }

        // Après création : poser flags createur + notifier
        if (ModelValidator.TYPE_AFTER_NEW == type) {
            // Verifier si le createur peut approuver/valider
            int adUserId = Env.getAD_User_ID(Env.getCtx());
            if (punishment.getEmission_Sanction_ID() > 0) {
                int typeSanctionId = org.compiere.util.DB.getSQLValue(
                    punishment.get_TrxName(),
                    "SELECT hr_typesanction_id FROM HR_Sanction_Autorisation WHERE HR_Sanction_Autorisation_ID = ?",
                    punishment.getEmission_Sanction_ID());
                String peutApprouver = org.compiere.util.DB.getSQLValueString(
                    punishment.get_TrxName(),
                    "SELECT adempiere.fn_est_habilite(?, ?, ?, 'hr_sanction_autorisation', 'hr_typesanction_id', 'isapprobation')",
                    punishment.getC_BPartner_ID(), adUserId, typeSanctionId);
                String peutValider = org.compiere.util.DB.getSQLValueString(
                    punishment.get_TrxName(),
                    "SELECT adempiere.fn_est_habilite(?, ?, ?, 'hr_sanction_autorisation', 'hr_typesanction_id', 'isvalidation')",
                    punishment.getC_BPartner_ID(), adUserId, typeSanctionId);
                punishment.setIsApprobation_Createur("Y".equals(peutApprouver));
                punishment.setIsValidation_Createur("Y".equals(peutValider));
                punishment.save(punishment.get_TrxName());
            }

            NotificationControler.notify(
                NotificationEvent.SANCTION_CREATED, punishment);
        }

        // Après modification : détecter la transition
        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            detecterTransitionEtatSanction(punishment);
        }

        return null;
    }

    // =========================================================================
    // UTILITAIRES PRIVÉS
    // =========================================================================

    /**
     * Valide qu'une période de suspension ne chevauche pas une autre
     * suspension, un congé ou une absence, et que la date de début n'est
     * pas dans le passé.
     *
     * Ne s'applique que si Date_Debut_Application ET HR_Duree_Sanction_ID
     * sont tous les deux renseignés (sinon rien à valider pour l'instant).
     *
     * @return null si valide, un message d'erreur bloquant sinon
     */
    private static String validerPeriodeSuspension(MHRPunishment punishment) {
        Timestamp dateDebut = punishment.getDate_Debut_Application();
        if (dateDebut == null || punishment.getHR_Duree_Sanction_ID() <= 0) {
            return null;
        }

        Timestamp maintenant = new Timestamp(System.currentTimeMillis());
        if (dateDebut.before(maintenant)) {
            return "La date de début de la sanction ne peut pas être avant la date d'aujourd'hui.";
        }

        MHRDureeSanction dureeSanction = new MHRDureeSanction(
            Env.getCtx(), punishment.getHR_Duree_Sanction_ID(), punishment.get_TrxName());
        Timestamp dateFin = HRCalendrierService.ajouterJoursOuvrables(
            dateDebut, dureeSanction.getNombre_De_Jour());

        Integer bpartnerId = punishment.getC_BPartner_ID();
        String trxName = punishment.get_TrxName();

        if (HRCongeRepository.chevaucheSuspensionNonRejete(bpartnerId, dateDebut, dateFin, trxName)) {
            return "Une autre période de suspension a été enregistrée durant cette période.";
        }
        if (HRCongeRepository.chevaucheAnyCongeNonRejete(bpartnerId, dateDebut, dateFin, trxName)) {
            return "Une période de congé a été enregistrée durant cette période.";
        }
        if (HRCongeRepository.isPeriodeAbsence(bpartnerId, dateDebut, dateFin, trxName)) {
            return "Une absence a été enregistrée durant cette période.";
        }

        return null;
    }

    /**
     * Détecte la transition d'état d'une sanction et déclenche la notification.
     * Ordre de priorité : Approbation > Désapprobation > Validation > Rejet.
     */
    private static void detecterTransitionEtatSanction(MHRPunishment punishment) {

        // Approbation
        if (punishment.isApprouve()
                && !getBooleanOld(punishment, MHRPunishment.COLUMNNAME_IsApprouve)) {
            NotificationControler.notify(NotificationEvent.SANCTION_APPROVED, punishment);
            return;
        }

        // Désapprobation
        if (punishment.isDesapprouve()
                && !getBooleanOld(punishment, MHRPunishment.COLUMNNAME_IsDesapprouve)) {
            NotificationControler.notify(NotificationEvent.SANCTION_DISAPPROVED, punishment);
            return;
        }

        // Validation
        if (punishment.isValidee()
                && !getBooleanOld(punishment, MHRPunishment.COLUMNNAME_IsValidee)) {
            NotificationControler.notify(NotificationEvent.SANCTION_VALIDATED, punishment);
            return;
        }

        // Rejet
        if (punishment.isRejetee()
                && !getBooleanOld(punishment, MHRPunishment.COLUMNNAME_IsRejetee)) {
            NotificationControler.notify(NotificationEvent.SANCTION_REJECTED, punishment);
        }
    }

    private static boolean getBooleanOld(PO po, String columnName) {
        Object oldValue = po.get_ValueOld(columnName);
        if (oldValue instanceof Boolean) return (Boolean) oldValue;
        if (oldValue instanceof String) return "Y".equalsIgnoreCase((String) oldValue);
        return false;
    }
}
