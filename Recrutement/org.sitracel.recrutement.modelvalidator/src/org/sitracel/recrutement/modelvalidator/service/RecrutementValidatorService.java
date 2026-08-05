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
import org.sitracel.recrutement.model.MHROffreCritereEvaluation;
import org.sitracel.recrutement.model.MHROffreEmploi;
import org.sitracel.recrutement.model.MHROffreTestEvaluation;
import org.sitracel.recrutement.model.MHRSessionRecrutement;
import org.sitracel.recrutement.modelvalidator.controller.ModelValidatorSqlControllerRecrutement;

/**
 * Service — logique métier du modelvalidator recrutement.
 *
 * Session 18 : garde-fous complets sur test/critère/session.
 */
public final class RecrutementValidatorService {

    private static final CLogger log = CLogger.getCLogger(RecrutementValidatorService.class);

    private RecrutementValidatorService() {}

    // =========================================================================
    // OFFRE D'EMPLOI
    // =========================================================================

    public static void avantCreationOffreEmploi(MHROffreEmploi offreEmploi) {
        if (offreEmploi == null) return;
        offreEmploi.setDate_Creation(new Timestamp(System.currentTimeMillis()));
    }

    public static void apresCreationOffreEmploi(MHROffreEmploi offreEmploi) {
        if (offreEmploi == null) return;
        NotificationControler.notify(NotificationEvent.OFFRE_EMPLOI_CREEE, offreEmploi);
    }

    // =========================================================================
    // SESSION DE RECRUTEMENT
    // =========================================================================

    public static void creationSessionRecrutement(MHRSessionRecrutement session) {
        if (session == null) return;
        session.setDate_Creation(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * Garde-fou : changement de test sur une session.
     * - Pas de candidats → autorisé
     * - Candidats sans scores → supprimer évaluations, recréer avec nouveau test
     * - Candidats avec scores → BLOQUÉ
     *
     * @return message d'erreur si bloqué, null si OK
     */
    public static String verifierChangementTestSession(MHRSessionRecrutement session) {
        if (session == null) return null;
        if (!session.is_ValueChanged(MHRSessionRecrutement.COLUMNNAME_HR_OffreTestEvaluation_ID)) {
            return null; // pas de changement de test
        }

        int sessionID = session.getHR_SessionRecrutement_ID();
        String trxName = session.get_TrxName();

        int nbCandidats = ModelValidatorSqlControllerRecrutement
                .compteCandidatsDansSession(sessionID, trxName);
        if (nbCandidats == 0) return null; // pas de candidat, libre

        int nbScores = ModelValidatorSqlControllerRecrutement
                .compteScoresSaisisDansSession(sessionID, trxName);
        if (nbScores > 0) {
            return "Impossible de changer le test : " + nbScores
                    + " score(s) déjà saisi(s) pour " + nbCandidats
                    + " candidat(s). Supprimez les évaluations d'abord.";
        }

        // Candidats sans scores → régénérer
        regenererEvaluationsSession(session);
        return null;
    }

    /**
     * Supprime toutes les évaluations d'une session et les recrée
     * selon les critères du test actuellement lié.
     */
    private static void regenererEvaluationsSession(MHRSessionRecrutement session) {
        int sessionID = session.getHR_SessionRecrutement_ID();
        String trxName = session.get_TrxName();

        // 1. Supprimer les anciennes évaluations
        ModelValidatorSqlControllerRecrutement.supprimerEvaluationsSession(sessionID, trxName);

        // 2. Récupérer le nouveau test et ses critères
        int nouveauTestID = session.getHR_OffreTestEvaluation_ID();
        if (nouveauTestID <= 0) return;

        ArrayList<BeanEvaluationCompetence> criteres =
                ModelValidatorSqlControllerRecrutement
                        .getCompetenceFromTestEvaluation(nouveauTestID, trxName);
        if (criteres == null || criteres.isEmpty()) return;

        // 3. Recréer pour chaque candidat
        ArrayList<Integer> candidatureIDs =
                ModelValidatorSqlControllerRecrutement
                        .getCandidatureIDsFromSession(sessionID, trxName);
        for (Integer candidatureID : candidatureIDs) {
            creerEvaluationsPourCandidature(candidatureID, criteres, trxName);
        }

        log.info("Session " + sessionID + " : évaluations régénérées pour "
                + candidatureIDs.size() + " candidat(s) × "
                + criteres.size() + " critère(s)");
    }

    // =========================================================================
    // TEST D'ÉVALUATION
    // =========================================================================

    public static void creationTestEvaluation(MHROffreTestEvaluation test) {
        if (test == null) return;
        test.setDate_Creation(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * Garde-fou : suppression d'un test.
     * Bloqué si utilisé dans une session avec des candidats.
     *
     * @return message d'erreur si bloqué, null si OK
     */
    public static String verifierSuppressionTest(MHROffreTestEvaluation test) {
        if (test == null) return null;
        String trxName = test.get_TrxName();
        int testID = test.getHR_OffreTestEvaluation_ID();

        int nbCandidats = ModelValidatorSqlControllerRecrutement
                .compteCandidatsPourTest(testID, trxName);
        if (nbCandidats > 0) {
            return "Impossible de supprimer ce test : il est utilisé dans une session "
                    + "qui contient " + nbCandidats + " candidat(s).";
        }
        return null;
    }

    // =========================================================================
    // CRITÈRE D'ÉVALUATION (HR_OffreCritereEvaluation)
    // =========================================================================

    /**
     * AFTER_NEW d'un critère : créer les évaluations pour les candidats existants.
     * Si aucun candidat → ne fait rien.
     */
    public static void apresCreationCritere(MHROffreCritereEvaluation critere) {
        if (critere == null) return;
        String trxName = critere.get_TrxName();
        int testID = critere.getHR_OffreTestEvaluation_ID();

        ArrayList<Integer> sessionIDs =
                ModelValidatorSqlControllerRecrutement.getSessionIDsPourTest(testID, trxName);
        if (sessionIDs.isEmpty()) return;

        for (Integer sessionID : sessionIDs) {
            ArrayList<Integer> candidatureIDs =
                    ModelValidatorSqlControllerRecrutement
                            .getCandidatureIDsFromSession(sessionID, trxName);
            for (Integer candidatureID : candidatureIDs) {
                MHRCandidatEvaluation eval =
                        new MHRCandidatEvaluation(Env.getCtx(), 0, trxName);
                eval.setHR_Candidature_ID(candidatureID);
                eval.setHR_Competences_ID(critere.getHR_Competences_ID());
                eval.setScoreMax(critere.getScoreMax());
                eval.setPonderation(critere.getPonderation());
                eval.setIsCompetenceEvalue(false);
                eval.save(trxName);
            }
        }
    }

    /**
     * BEFORE_CHANGE d'un critère : vérifier si des scores existent.
     * - Scores saisis → bloquer la modification
     * - Pas de scores → propager ScoreMax et Pondération aux évaluations
     *
     * @return message d'erreur si bloqué, null si OK
     */
    public static String verifierModificationCritere(MHROffreCritereEvaluation critere) {
        if (critere == null) return null;

        boolean scoreMaxChange = critere.is_ValueChanged(
                MHROffreCritereEvaluation.COLUMNNAME_ScoreMax);
        boolean ponderationChange = critere.is_ValueChanged(
                MHROffreCritereEvaluation.COLUMNNAME_Ponderation);
        boolean competenceChange = critere.is_ValueChanged(
                MHROffreCritereEvaluation.COLUMNNAME_HR_Competences_ID);

        if (!scoreMaxChange && !ponderationChange && !competenceChange) {
            return null; // rien de critique n'a changé
        }

        String trxName = critere.get_TrxName();
        int testID = critere.getHR_OffreTestEvaluation_ID();

        int nbCandidats = ModelValidatorSqlControllerRecrutement
                .compteCandidatsPourTest(testID, trxName);
        if (nbCandidats == 0) return null; // pas de candidat, libre

        if (competenceChange) {
            return "Impossible de changer la compétence : "
                    + nbCandidats + " candidat(s) ont déjà des évaluations liées.";
        }

        int nbScores = ModelValidatorSqlControllerRecrutement
                .compteScoresPourTestEtCompetence(
                        testID, critere.getHR_Competences_ID(), trxName);
        if (nbScores > 0) {
            return "Impossible de modifier ce critère : " + nbScores
                    + " score(s) déjà saisi(s). Effacez les scores d'abord.";
        }

        // Pas de scores → la propagation se fera dans AFTER_CHANGE
        return null;
    }

    /**
     * AFTER_CHANGE d'un critère (appelé seulement si BEFORE a passé) :
     * propager ScoreMax/Pondération aux évaluations non notées.
     */
    public static void propagerModificationCritere(MHROffreCritereEvaluation critere) {
        if (critere == null) return;

        boolean scoreMaxChange = critere.is_ValueChanged(
                MHROffreCritereEvaluation.COLUMNNAME_ScoreMax);
        boolean ponderationChange = critere.is_ValueChanged(
                MHROffreCritereEvaluation.COLUMNNAME_Ponderation);
        if (!scoreMaxChange && !ponderationChange) return;

        String trxName = critere.get_TrxName();
        int testID = critere.getHR_OffreTestEvaluation_ID();
        int competenceID = critere.getHR_Competences_ID();

        ArrayList<Integer> sessionIDs =
                ModelValidatorSqlControllerRecrutement.getSessionIDsPourTest(testID, trxName);

        for (Integer sessionID : sessionIDs) {
            ArrayList<Integer> candidatureIDs =
                    ModelValidatorSqlControllerRecrutement
                            .getCandidatureIDsFromSession(sessionID, trxName);
            for (Integer candidatureID : candidatureIDs) {
                ArrayList<MHRCandidatEvaluation> evals =
                        ModelValidatorSqlControllerRecrutement
                                .getEvaluationsFromCandidature(candidatureID, trxName);
                for (MHRCandidatEvaluation eval : evals) {
                    if (eval.getHR_Competences_ID() == competenceID) {
                        if (scoreMaxChange) eval.setScoreMax(critere.getScoreMax());
                        if (ponderationChange) eval.setPonderation(critere.getPonderation());
                        eval.save(trxName);
                    }
                }
            }
        }
    }

    /**
     * BEFORE_DELETE d'un critère : vérifier si des scores existent.
     * - Scores saisis → bloquer
     * - Pas de scores → supprimer les évaluations correspondantes
     *
     * @return message d'erreur si bloqué, null si OK
     */
    public static String verifierSuppressionCritere(MHROffreCritereEvaluation critere) {
        if (critere == null) return null;
        String trxName = critere.get_TrxName();
        int testID = critere.getHR_OffreTestEvaluation_ID();
        int competenceID = critere.getHR_Competences_ID();

        int nbScores = ModelValidatorSqlControllerRecrutement
                .compteScoresPourTestEtCompetence(testID, competenceID, trxName);
        if (nbScores > 0) {
            return "Impossible de supprimer ce critère : " + nbScores
                    + " score(s) déjà saisi(s) pour cette compétence.";
        }

        // Pas de scores → supprimer les évaluations orphelines
        ModelValidatorSqlControllerRecrutement
                .supprimerEvaluationsCompetencePourTest(testID, competenceID, trxName);
        return null;
    }

    // =========================================================================
    // CANDIDATURE
    // =========================================================================

    public static void avantCreationCandidature(MHRCandidature candidature) {
        if (candidature == null) return;
        candidature.setDate_Creation(new Timestamp(System.currentTimeMillis()));
    }

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

            ArrayList<BeanEvaluationCompetence> criteres =
                    ModelValidatorSqlControllerRecrutement.getCompetenceFromTestEvaluation(
                            testEvaluation.getHR_OffreTestEvaluation_ID(), trxName);

            if (criteres != null) {
                creerEvaluationsPourCandidature(
                        candidature.getHR_Candidature_ID(), criteres, trxName);
            }
        } catch (Exception erreurCatch) {
            log.log(Level.WARNING,
                    "Création évaluations candidature " + candidature.get_ID()
                            + " : " + erreurCatch.getMessage(), erreurCatch);
        }
        NotificationControler.notify(NotificationEvent.CANDIDATURE_RECUE, candidature);
    }

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
    // ÉVALUATION — VALIDATION + CLASSEMENT
    // =========================================================================

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

    // =========================================================================
    // MÉTHODES PRIVÉES
    // =========================================================================

    /** Créer une ligne CandidatEvaluation par critère pour une candidature */
    private static void creerEvaluationsPourCandidature(
            int candidatureID, ArrayList<BeanEvaluationCompetence> criteres, String trxName) {
        for (BeanEvaluationCompetence critere : criteres) {
            MHRCandidatEvaluation eval =
                    new MHRCandidatEvaluation(Env.getCtx(), 0, trxName);
            eval.setHR_Candidature_ID(candidatureID);
            eval.setHR_Competences_ID(critere.getCompetenceID());
            eval.setScoreMax(critere.getScoreMax());
            eval.setPonderation(critere.getPonderation());
            eval.setIsCompetenceEvalue(false);
            eval.save(trxName);
        }
    }

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
