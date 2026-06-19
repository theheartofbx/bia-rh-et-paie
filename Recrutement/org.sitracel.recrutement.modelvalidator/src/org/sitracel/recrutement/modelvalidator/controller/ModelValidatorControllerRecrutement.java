package org.sitracel.recrutement.modelvalidator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
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

public class ModelValidatorControllerRecrutement {

    private static CLogger log = CLogger.getCLogger(PO.class);

    // =========================================================================
    // OFFRE D'EMPLOI
    // =========================================================================

    public static void creationOffreEmploi(MHROffreEmploi offreEmploi) {
        if (offreEmploi == null) return;
        offreEmploi.setDate_Creation(new Timestamp(System.currentTimeMillis()));
        NotificationControler.notify(NotificationEvent.OFFRE_EMPLOI_CREEE, offreEmploi);
    }

    // =========================================================================
    // SESSION DE RECRUTEMENT
    // =========================================================================

    public static void CreationSessionRecrutement(MHRSessionRecrutement sessionRecrutement) {
        if (sessionRecrutement == null) return;
        sessionRecrutement.setDate_Creation(new Timestamp(System.currentTimeMillis()));
    }

    // =========================================================================
    // TEST D'ÉVALUATION
    // =========================================================================

    public static void CreationTestEvaluation(MHROffreTestEvaluation testEvaluation) {
        if (testEvaluation == null) return;
        testEvaluation.setDate_Creation(new Timestamp(System.currentTimeMillis()));
    }

    // =========================================================================
    // CANDIDATURE
    // =========================================================================

    public static void creationCandidature(MHRCandidature candidature) {
        if (candidature == null) return;

        candidature.setDate_Creation(new Timestamp(System.currentTimeMillis()));

        try {
            DB.commit(true, candidature.get_TrxName());
            MHRSessionRecrutement sessionRecrutement = new MHRSessionRecrutement(
                    Env.getCtx(),
                    candidature.getHR_SessionRecrutement_ID(),
                    candidature.get_TrxName()
            );
            if (sessionRecrutement != null) {
                MHROffreTestEvaluation testEvaluation = new MHROffreTestEvaluation(
                        Env.getCtx(),
                        sessionRecrutement.getHR_OffreTestEvaluation_ID(),
                        candidature.get_TrxName()
                );
                if (testEvaluation != null) {
                    ArrayList<BeanEvaluationCompetence> listeCompetence =
                            ModelValidatorSqlControllerRecrutement
                                    .getCompetenceFromTestEvaluation(
                                            testEvaluation.getHR_OffreTestEvaluation_ID(),
                                            candidature.get_TrxName()
                                    );
                    if (listeCompetence != null) {
                        BigDecimal scoreMax = BigDecimal.valueOf(0);
                        for (BeanEvaluationCompetence competence : listeCompetence) {
                            MHRCandidatEvaluation evaluationCandidat =
                                    new MHRCandidatEvaluation(Env.getCtx(), 0, null);
                            evaluationCandidat.setHR_Candidature_ID(
                                    candidature.getHR_Candidature_ID());
                            evaluationCandidat.setHR_Competences_ID(
                                    competence.getCompetenceID());
                            evaluationCandidat.setScoreMax(competence.getScoreMax());
                            evaluationCandidat.setPonderation(competence.getPonderation());
                            evaluationCandidat.setIsCompetenceEvalue(false);
                            evaluationCandidat.save();
                            scoreMax = scoreMax.add(
                                    competence.getScoreMax().multiply(
                                            BigDecimal.valueOf(competence.getPonderation())));
                            DB.commit(true, evaluationCandidat.get_TrxName());
                        }
                        candidature.setScoreTotalMax(scoreMax);
                        candidature.save();
                        DB.commit(true, candidature.get_TrxName());
                    }
                }
            }
        } catch (IllegalStateException | SQLException erreurCatch) {
            log.warning("creationCandidature: " + erreurCatch.getMessage());
        }

        // Notification après la logique métier
        NotificationControler.notify(NotificationEvent.CANDIDATURE_RECUE, candidature);
    }

    public static void suppressionCandidature(MHRCandidature candidature) {
        if (candidature == null) return;
        ArrayList<Integer> listeCompetenceID =
                ModelValidatorSqlControllerRecrutement
                        .getListeCompetenceFromCandidatureID(
                                candidature.getHR_Candidature_ID(), null);
        for (Integer competenceID : listeCompetenceID) {
            if (competenceID != null) {
                MHRCandidatEvaluation candidatEvaluation =
                        new MHRCandidatEvaluation(Env.getCtx(), competenceID, null);
                if (candidatEvaluation != null) {
                    candidatEvaluation.delete(true);
                }
            }
        }
    }

    // =========================================================================
    // CLASSEMENT CANDIDATURES
    // =========================================================================

    public static void actualiserCandidature(
            Integer hr_sessionRecrutementID,
            MHRCandidatEvaluation candidatEvaluation) {

        if (hr_sessionRecrutementID == null) return;

        ArrayList<BeanCandidatEvaluation> listeCandidatures =
                calculerRangCandidatures(hr_sessionRecrutementID, candidatEvaluation);
        if (listeCandidatures == null) return;

        try {
            for (BeanCandidatEvaluation candidature : listeCandidatures) {
                MHRCandidature candidature0 =
                        new MHRCandidature(Env.getCtx(), candidature.getCandidatureID(), null);
                candidature0.setRangCandidat(candidature.getRang());
                candidature0.setScoreTotal(candidature.getScoreTotal());
                candidature0.save();
                DB.commit(true, candidature0.get_TrxName());

                // Notification : candidature classée (une seule fois par candidature)
                NotificationControler.notify(
                        NotificationEvent.CANDIDATURE_CLASSEE, candidature0);
            }
        } catch (IllegalStateException | SQLException erreurCatch) {
            log.warning("actualiserCandidature: " + erreurCatch.getMessage());
        }
    }

    public static ArrayList<BeanCandidatEvaluation> calculerRangCandidatures(
            Integer hr_sessionRecrutementID,
            MHRCandidatEvaluation candidatEvaluation) {

        ArrayList<BeanCandidatEvaluation> listeCandidatures = null;
        if (hr_sessionRecrutementID == null) return null;

        listeCandidatures = ModelValidatorSqlControllerRecrutement
                .getListeCandidatureFromSessionRecrutement(hr_sessionRecrutementID, null);

        for (BeanCandidatEvaluation candidature : listeCandidatures) {
            ArrayList<BigDecimal> listeScore =
                    ModelValidatorSqlControllerRecrutement
                            .getListeScoreFromCandidature(
                                    candidature.getHR_Candidature_ID(), null);
            BigDecimal scoreTotal = BigDecimal.valueOf(0);
            for (BigDecimal score : listeScore) {
                if (score != null) {
                    scoreTotal = scoreTotal.add(score);
                }
            }
            candidature.setScoreTotal(scoreTotal);
        }

        Collections.sort(listeCandidatures);
        int rang = 1;
        for (BeanCandidatEvaluation candidature : listeCandidatures) {
            candidature.setRang(rang++);
        }

        return listeCandidatures;
    }
}
