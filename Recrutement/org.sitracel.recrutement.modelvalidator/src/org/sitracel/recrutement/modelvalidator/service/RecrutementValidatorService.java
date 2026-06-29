package org.sitracel.recrutement.modelvalidator.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

import org.compiere.util.DB;
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
 * Remplace ModelValidatorControllerRecrutement.
 */
public final class RecrutementValidatorService {

    private RecrutementValidatorService() {}

    public static void creationOffreEmploi(MHROffreEmploi offreEmploi) {
        if (offreEmploi == null) return;
        offreEmploi.setDate_Creation(new Timestamp(System.currentTimeMillis()));
        NotificationControler.notify(NotificationEvent.OFFRE_EMPLOI_CREEE, offreEmploi);
    }

    public static void creationSessionRecrutement(MHRSessionRecrutement sessionRecrutement) {
        if (sessionRecrutement == null) return;
        sessionRecrutement.setDate_Creation(new Timestamp(System.currentTimeMillis()));
    }

    public static void creationTestEvaluation(MHROffreTestEvaluation testEvaluation) {
        if (testEvaluation == null) return;
        testEvaluation.setDate_Creation(new Timestamp(System.currentTimeMillis()));
    }

    public static void creationCandidature(MHRCandidature candidature) {
        if (candidature == null) return;
        candidature.setDate_Creation(new Timestamp(System.currentTimeMillis()));
        try {
            DB.commit(true, candidature.get_TrxName());
            MHRSessionRecrutement session = new MHRSessionRecrutement(
                Env.getCtx(), candidature.getHR_SessionRecrutement_ID(),
                candidature.get_TrxName());
            if (session == null) return;
            MHROffreTestEvaluation testEvaluation = new MHROffreTestEvaluation(
                Env.getCtx(), session.getHR_OffreTestEvaluation_ID(),
                candidature.get_TrxName());
            if (testEvaluation == null) return;
            ArrayList<BeanEvaluationCompetence> listeCompetence =
                ModelValidatorSqlControllerRecrutement.getCompetenceFromTestEvaluation(
                    testEvaluation.getHR_OffreTestEvaluation_ID(),
                    candidature.get_TrxName());
            if (listeCompetence != null) {
                for (BeanEvaluationCompetence competence : listeCompetence) {
                    MHRCandidatEvaluation evaluation =
                        new MHRCandidatEvaluation(Env.getCtx(), null,
                            candidature.get_TrxName());
                    evaluation.setHR_Candidature_ID(candidature.getHR_Candidature_ID());
                    evaluation.setHR_Competence_ID(competence.getCompetenceID());
                    evaluation.setHR_NiveauCompetence_ID(competence.getNiveauCompetenceID());
                    evaluation.save(candidature.get_TrxName());
                }
            }
        } catch (Exception e) {
            // Log sans bloquer la création
        }
        NotificationControler.notify(NotificationEvent.CANDIDATURE_RECUE, candidature);
    }

    public static void suppressionCandidature(MHRCandidature candidature) {
        if (candidature == null) return;
        ArrayList<Integer> listeCompetenceID =
            ModelValidatorSqlControllerRecrutement.getListeCompetenceFromCandidatureID(
                candidature.getHR_Candidature_ID(), null);
        for (Integer competenceID : listeCompetenceID) {
            if (competenceID != null) {
                MHRCandidatEvaluation evaluation =
                    new MHRCandidatEvaluation(Env.getCtx(), competenceID, null);
                if (evaluation != null) evaluation.delete(true);
            }
        }
    }

    public static void actualiserCandidature(Integer sessionRecrutementID,
                                              MHRCandidatEvaluation candidatEvaluation) {
        if (sessionRecrutementID == null) return;
        ArrayList<BeanCandidatEvaluation> listeCandidatures =
            calculerRangCandidatures(sessionRecrutementID, candidatEvaluation);
        if (listeCandidatures == null) return;
        for (BeanCandidatEvaluation candidature : listeCandidatures) {
            MHRCandidature c = new MHRCandidature(
                Env.getCtx(), candidature.getCandidatureID(), null);
            c.setRangCandidat(candidature.getRang());
            c.setScoreTotal(candidature.getScoreTotal());
            c.save(null);
        }
        NotificationControler.notify(
            NotificationEvent.CANDIDATURE_CLASSEE, candidatEvaluation);
    }

    private static ArrayList<BeanCandidatEvaluation> calculerRangCandidatures(
            Integer sessionRecrutementID, MHRCandidatEvaluation candidatEvaluation) {
        ArrayList<BeanCandidatEvaluation> listeCandidatures =
            ModelValidatorSqlControllerRecrutement
                .getCandidaturesFromSessionRecrutement(sessionRecrutementID, null);
        if (listeCandidatures == null || listeCandidatures.isEmpty()) return null;
        for (BeanCandidatEvaluation candidature : listeCandidatures) {
            ArrayList<MHRCandidatEvaluation> listeEvaluations =
                ModelValidatorSqlControllerRecrutement
                    .getEvaluationsFromCandidature(candidature.getCandidatureID(), null);
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
