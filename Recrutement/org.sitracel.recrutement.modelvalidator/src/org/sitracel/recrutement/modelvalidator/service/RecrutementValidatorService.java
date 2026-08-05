package org.sitracel.recrutement.modelvalidator.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.sitracel.bean.BeanCandidatEvaluation;
import org.sitracel.bean.BeanEvaluationCompetence;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.NotificationControler;
import org.sitracel.recrutement.model.MHRCandidatEvaluation;
import org.sitracel.recrutement.model.MHRCandidature;
import org.sitracel.recrutement.model.MHROffreEmploi;
import org.sitracel.recrutement.model.MHROffreTestEvaluation;
import org.sitracel.recrutement.model.MHRSessionRecrutement;
import org.sitracel.recrutement.modelvalidator.controller.ModelValidatorSqlControllerRecrutement;

/**
 * Service — logique métier du modelvalidator recrutement.
 *
 * Corrigé Session 18 :
 * - Split BEFORE_NEW / AFTER_NEW pour persister Date_Creation
 * - Suppression DB.commit() dans création candidature
 * - Constructeur MHRCandidatEvaluation : 0 au lieu de null
 * - Catch avec log.warning au lieu de catch vide
 * - Ajout validerScoreEvaluation (garde-fou serveur, dupliqué du callout)
 * - Propagation trxName dans actualiserCandidature et suppressionCandidature
 */
public final class RecrutementValidatorService {

    private static final CLogger log = CLogger.getCLogger(RecrutementValidatorService.class);

    private RecrutementValidatorService() {}

    // =========================================================================
    // OFFRE D'EMPLOI
    // =========================================================================

    /** BEFORE_NEW : initialiser la date de création (persistée avec le save principal) */
    public static void avantCreationOffreEmploi(MHROffreEmploi offreEmploi) {
        if (offreEmploi == null) return;
        offreEmploi.setDate_Creation(new Timestamp(System.currentTimeMillis()));
    }

    /** AFTER_NEW : notification (l'ID est maintenant assigné) */
    public static void apresCreationOffreEmploi(MHROffreEmploi offreEmploi) {
        if (offreEmploi == null) return;
        NotificationControler.notify(NotificationEvent.OFFRE_EMPLOI_CREEE, offreEmploi);
    }

    // =========================================================================
    // SESSION DE RECRUTEMENT
    // =========================================================================

    /** BEFORE_NEW : initialiser la date de création */
    public static void creationSessionRecrutement(MHRSessionRecrutement sessionRecrutement) {
        if (sessionRecrutement == null) return;
        sessionRecrutement.setDate_Creation(new Timestamp(System.currentTimeMillis()));
    }

    // =========================================================================
    // TEST D'ÉVALUATION
    // =========================================================================

    /** BEFORE_NEW : initialiser la date de création */
    public static void creationTestEvaluation(MHROffreTestEvaluation testEvaluation) {
        if (testEvaluation == null) return;
        testEvaluation.setDate_Creation(new Timestamp(System.currentTimeMillis()));
    }

    // =========================================================================
    // CANDIDATURE
    // =========================================================================

    /** BEFORE_NEW : initialiser la date de création (persistée avec le save principal) */
    public static void avantCreationCandidature(MHRCandidature candidature) {
        if (candidature == null) return;
        candidature.setDate_Creation(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * AFTER_NEW : créer automatiquement une ligne HR_CandidatEvaluation
     * par compétence du test d'évaluation, puis notifier.
     *
     * Corrigé Session 18 :
     * - Supprimé DB.commit() (laisse iDempiere gérer la transaction)
     * - Constructeur : 0 au lieu de null
     * - Catch avec log.warning au lieu de catch vide
     */
    public static void apresCreationCandidature(MHRCandidature candidature) {
        if (candidature == null) return;
        String trxName = candidature.get_TrxName();
        try {
            MHRSessionRecrutement session = new MHRSessionRecrutement(
                Env.getCtx(), candidature.getHR_SessionRecrutement_ID(), trxName);
            if (session == null || session.get_ID() == 0) return;

            MHROffreTestEvaluation testEvaluation = new MHROffreTestEvaluation(
                Env.getCtx(), session.getHR_OffreTestEvaluation_ID(), trxName);
            if (testEvaluation == null || testEvaluation.get_ID() == 0) return;

            ArrayList<BeanEvaluationCompetence> listeCompetence =
                ModelValidatorSqlControllerRecrutement.getCompetenceFromTestEvaluation(
                    testEvaluation.getHR_OffreTestEvaluation_ID(), trxName);

            if (listeCompetence != null) {
                for (BeanEvaluationCompetence competence : listeCompetence) {
                    MHRCandidatEvaluation evaluation =
                        new MHRCandidatEvaluation(Env.getCtx(), 0, trxName);
                    evaluation.setHR_Candidature_ID(candidature.getHR_Candidature_ID());
                    evaluation.setHR_Competences_ID(competence.getCompetenceID());
                    evaluation.setScoreMax(competence.getScoreMax());
                    evaluation.setPonderation(competence.getPonderation());
                    evaluation.setIsCompetenceEvalue(false);
                    evaluation.save(trxName);
                }
            }
        } catch (Exception erreurCatch) {
            log.log(Level.WARNING,
                "Création évaluations candidature " + candidature.get_ID()
                + " : " + erreurCatch.getMessage(), erreurCatch);
        }
        NotificationControler.notify(NotificationEvent.CANDIDATURE_RECUE, candidature);
    }

    // =========================================================================
    // SUPPRESSION CANDIDATURE
    // =========================================================================

    /** BEFORE_DELETE : supprimer les lignes d'évaluation enfants */
    public static void suppressionCandidature(MHRCandidature candidature) {
        if (candidature == null) return;
        String trxName = candidature.get_TrxName();
        ArrayList<Integer> listeEvaluationID =
            ModelValidatorSqlControllerRecrutement.getListeCompetenceFromCandidatureID(
                candidature.getHR_Candidature_ID(), trxName);
        if (listeEvaluationID == null) return;
        for (Integer evaluationID : listeEvaluationID) {
            if (evaluationID != null) {
                MHRCandidatEvaluation evaluation =
                    new MHRCandidatEvaluation(Env.getCtx(), evaluationID.intValue(), trxName);
                if (evaluation != null && evaluation.get_ID() > 0) {
                    evaluation.delete(true, trxName);
                }
            }
        }
    }

    // =========================================================================
    // ÉVALUATION DES CANDIDATS — VALIDATION + CLASSEMENT
    // =========================================================================

    /**
     * Garde-fou serveur : le score ne peut pas dépasser le maximum.
     * Duplique la logique du callout CalloutScoreCompetence.
     *
     * @return message d'erreur si invalide, null si OK
     */
    public static String validerScoreEvaluation(MHRCandidatEvaluation evaluation) {
        if (evaluation == null) return null;
        BigDecimal score = evaluation.getScore();
        BigDecimal scoreMax = evaluation.getScoreMax();
        if (score != null && scoreMax != null
                && scoreMax.compareTo(BigDecimal.ZERO) > 0
                && score.compareTo(scoreMax) > 0) {
            return "Le score (" + score + ") ne peut pas dépasser le maximum (" + scoreMax + ")";
        }
        return null;
    }

    /**
     * Recalcule le classement de toutes les candidatures d'une session
     * après modification d'une évaluation.
     */
    public static void actualiserCandidature(Integer sessionRecrutementID,
                                              MHRCandidatEvaluation candidatEvaluation) {
        if (sessionRecrutementID == null) return;
        String trxName = (candidatEvaluation != null) ? candidatEvaluation.get_TrxName() : null;
        ArrayList<BeanCandidatEvaluation> listeCandidatures =
            calculerRangCandidatures(sessionRecrutementID, trxName);
        if (listeCandidatures == null) return;
        for (BeanCandidatEvaluation candidature : listeCandidatures) {
            MHRCandidature c = new MHRCandidature(
                Env.getCtx(), candidature.getCandidatureID(), trxName);
            c.setRangCandidat(candidature.getRang());
            c.setScoreTotal(candidature.getScoreTotal());
            c.save(trxName);
        }
        NotificationControler.notify(
            NotificationEvent.CANDIDATURE_CLASSEE, candidatEvaluation);
    }

    /**
     * Calcule le score total et le rang de chaque candidature dans une session.
     * Tri décroissant par score total → rang 1 = meilleur candidat.
     */
    private static ArrayList<BeanCandidatEvaluation> calculerRangCandidatures(
            Integer sessionRecrutementID, String trxName) {
        ArrayList<BeanCandidatEvaluation> listeCandidatures =
            ModelValidatorSqlControllerRecrutement
                .getCandidaturesFromSessionRecrutement(sessionRecrutementID, trxName);
        if (listeCandidatures == null || listeCandidatures.isEmpty()) return null;

        for (BeanCandidatEvaluation candidature : listeCandidatures) {
            ArrayList<MHRCandidatEvaluation> listeEvaluations =
                ModelValidatorSqlControllerRecrutement
                    .getEvaluationsFromCandidature(candidature.getCandidatureID(), trxName);
            BigDecimal scoreTotal = BigDecimal.ZERO;
            if (listeEvaluations != null) {
                for (MHRCandidatEvaluation evaluation : listeEvaluations) {
                    if (evaluation.getScore() != null) {
                        scoreTotal = scoreTotal.add(evaluation.getScore());
                    }
                }
            }
            candidature.setScoreTotal(scoreTotal);
        }

        Collections.sort(listeCandidatures,
            (a, b) -> b.getScoreTotal().compareTo(a.getScoreTotal()));

        for (int i = 0; i < listeCandidatures.size(); i++) {
            listeCandidatures.get(i).setRang(i + 1);
        }
        return listeCandidatures;
    }
}
