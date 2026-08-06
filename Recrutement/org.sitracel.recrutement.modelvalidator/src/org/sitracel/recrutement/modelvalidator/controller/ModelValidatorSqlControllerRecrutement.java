package org.sitracel.recrutement.modelvalidator.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanCandidatEvaluation;
import org.sitracel.bean.BeanEvaluationCompetence;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.recrutement.model.I_HR_CandidatEvaluation;
import org.sitracel.recrutement.model.MHRCandidatEvaluation;
import org.sitracel.recrutement.model.I_HR_Candidature;
import org.sitracel.recrutement.model.I_HR_OffreCritereEvaluation;

/**
 * Requêtes SQL du module Recrutement.
 * Corrigé Session 18 : ajout méthodes de comptage et suppression
 * pour les garde-fous test/critère/session.
 */
public class ModelValidatorSqlControllerRecrutement {

    // =========================================================================
    // MÉTHODES EXISTANTES
    // =========================================================================

    public static ArrayList<BeanEvaluationCompetence> getCompetenceFromTestEvaluation(
            Integer hr_offreTestEvaluation_ID, String trxName) {
        ArrayList<BeanEvaluationCompetence> resultat = new ArrayList<>();
        if (hr_offreTestEvaluation_ID == null) return resultat;
        String sql = "SELECT "
                + I_HR_OffreCritereEvaluation.COLUMNNAME_HR_Competences_ID
                + ", " + I_HR_OffreCritereEvaluation.COLUMNNAME_ScoreMax
                + ", " + I_HR_OffreCritereEvaluation.COLUMNNAME_Ponderation
                + " FROM " + I_HR_OffreCritereEvaluation.Table_Name
                + " WHERE " + I_HR_OffreCritereEvaluation.COLUMNNAME_HR_OffreTestEvaluation_ID + "=?"
                + " AND IsActive='Y'";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, hr_offreTestEvaluation_ID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BeanEvaluationCompetence bean = BeanFactory.getBeanEvaluationCompetence();
                bean.setCompetenceID(rs.getInt(I_HR_OffreCritereEvaluation.COLUMNNAME_HR_Competences_ID));
                bean.setScoreMax(rs.getBigDecimal(I_HR_OffreCritereEvaluation.COLUMNNAME_ScoreMax));
                bean.setPonderation(rs.getInt(I_HR_OffreCritereEvaluation.COLUMNNAME_Ponderation));
                resultat.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    public static ArrayList<BeanEvaluationCompetence> getScoresCandidature(
            Integer hr_candidatureID, String trxName) {
        ArrayList<BeanEvaluationCompetence> resultat = new ArrayList<>();
        if (hr_candidatureID == null) return resultat;
        String sql = "SELECT "
                + I_HR_CandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID
                + ", " + I_HR_CandidatEvaluation.COLUMNNAME_HR_Competences_ID
                + ", " + I_HR_CandidatEvaluation.COLUMNNAME_ScoreMax
                + ", " + I_HR_CandidatEvaluation.COLUMNNAME_Ponderation
                + ", " + I_HR_CandidatEvaluation.COLUMNNAME_Score
                + " FROM " + I_HR_CandidatEvaluation.Table_Name
                + " WHERE " + I_HR_CandidatEvaluation.COLUMNNAME_HR_Candidature_ID + "=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, hr_candidatureID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BeanEvaluationCompetence bean = BeanFactory.getBeanEvaluationCompetence();
                bean.setCandidatEvaluationID(rs.getInt(I_HR_CandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID));
                bean.setCompetenceID(rs.getInt(I_HR_CandidatEvaluation.COLUMNNAME_HR_Competences_ID));
                bean.setScore(rs.getBigDecimal(I_HR_CandidatEvaluation.COLUMNNAME_Score));
                bean.setScoreMax(rs.getBigDecimal(I_HR_CandidatEvaluation.COLUMNNAME_ScoreMax));
                bean.setPonderation(rs.getInt(I_HR_CandidatEvaluation.COLUMNNAME_Ponderation));
                resultat.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    public static ArrayList<BeanCandidatEvaluation> getListeCandidatureFromSessionRecrutement(
            Integer hr_sessionRecrutementID, String trxName) {
        ArrayList<BeanCandidatEvaluation> resultat = new ArrayList<>();
        if (hr_sessionRecrutementID == null) return resultat;
        String sql = "SELECT " + I_HR_Candidature.COLUMNNAME_HR_Candidature_ID
                + ", " + I_HR_Candidature.COLUMNNAME_Name
                + " FROM " + I_HR_Candidature.Table_Name
                + " WHERE " + I_HR_Candidature.COLUMNNAME_HR_SessionRecrutement_ID + "=?"
                + " AND IsActive='Y'";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, hr_sessionRecrutementID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BeanCandidatEvaluation bean = BeanFactory.getBeanCandidatEvaluation();
                bean.setCandidatureID(rs.getInt(I_HR_Candidature.COLUMNNAME_HR_Candidature_ID));
                bean.setNameCandidature(rs.getString(I_HR_Candidature.COLUMNNAME_Name));
                resultat.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    public static ArrayList<BeanCandidatEvaluation> getCandidaturesFromSessionRecrutement(
            Integer sessionRecrutementID, String trxName) {
        return getListeCandidatureFromSessionRecrutement(sessionRecrutementID, trxName);
    }

    public static ArrayList<Integer> getListeCompetenceFromCandidatureID(
            Integer hr_candidatureID, String trxName) {
        ArrayList<Integer> resultat = new ArrayList<>();
        if (hr_candidatureID == null) return resultat;
        String sql = "SELECT " + I_HR_CandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID
                + " FROM " + I_HR_CandidatEvaluation.Table_Name
                + " WHERE " + I_HR_CandidatEvaluation.COLUMNNAME_HR_Candidature_ID + "=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, hr_candidatureID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(rs.getInt(I_HR_CandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    public static ArrayList<MHRCandidatEvaluation> getEvaluationsFromCandidature(
            int candidatureID, String trxName) {
        ArrayList<MHRCandidatEvaluation> resultat = new ArrayList<>();
        String sql = "SELECT * FROM " + I_HR_CandidatEvaluation.Table_Name
                + " WHERE " + I_HR_CandidatEvaluation.COLUMNNAME_HR_Candidature_ID + " = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, candidatureID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MHRCandidatEvaluation(Env.getCtx(), rs, trxName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    // =========================================================================
    // NOUVELLES MÉTHODES — GARDE-FOUS SESSION 18
    // =========================================================================

    /** Nombre de candidats actifs dans une session */
    public static int compteCandidatsDansSession(int sessionID, String trxName) {
        String sql = "SELECT COUNT(*) FROM HR_Candidature"
                + " WHERE HR_SessionRecrutement_ID = ? AND IsActive = 'Y'";
        return DB.getSQLValueEx(trxName, sql, sessionID);
    }

    /** Nombre d'évaluations avec score saisi dans une session */
    public static int compteScoresSaisisDansSession(int sessionID, String trxName) {
        String sql = "SELECT COUNT(*) FROM HR_CandidatEvaluation ce"
                + " JOIN HR_Candidature c ON c.HR_Candidature_ID = ce.HR_Candidature_ID"
                + " WHERE c.HR_SessionRecrutement_ID = ?"
                + " AND ce.IsActive = 'Y' AND ce.Score IS NOT NULL AND ce.Score > 0";
        return DB.getSQLValueEx(trxName, sql, sessionID);
    }

    /** Nombre de candidats dans les sessions utilisant un test donné */
    public static int compteCandidatsPourTest(int testID, String trxName) {
        String sql = "SELECT COUNT(*) FROM HR_Candidature c"
                + " JOIN HR_SessionRecrutement s ON s.HR_SessionRecrutement_ID = c.HR_SessionRecrutement_ID"
                + " WHERE s.HR_OffreTestEvaluation_ID = ?"
                + " AND c.IsActive = 'Y' AND s.IsActive = 'Y'";
        return DB.getSQLValueEx(trxName, sql, testID);
    }

    /** Nombre de scores saisis pour un test (toutes sessions confondues) */
    public static int compteScoresSaisisPourTest(int testID, String trxName) {
        String sql = "SELECT COUNT(*) FROM HR_CandidatEvaluation ce"
                + " JOIN HR_Candidature c ON c.HR_Candidature_ID = ce.HR_Candidature_ID"
                + " JOIN HR_SessionRecrutement s ON s.HR_SessionRecrutement_ID = c.HR_SessionRecrutement_ID"
                + " WHERE s.HR_OffreTestEvaluation_ID = ?"
                + " AND ce.IsActive = 'Y' AND ce.Score IS NOT NULL AND ce.Score > 0";
        return DB.getSQLValueEx(trxName, sql, testID);
    }

    /** Nombre de scores saisis pour une compétence spécifique d'un test */
    public static int compteScoresPourTestEtCompetence(int testID, int competenceID, String trxName) {
        String sql = "SELECT COUNT(*) FROM HR_CandidatEvaluation ce"
                + " JOIN HR_Candidature c ON c.HR_Candidature_ID = ce.HR_Candidature_ID"
                + " JOIN HR_SessionRecrutement s ON s.HR_SessionRecrutement_ID = c.HR_SessionRecrutement_ID"
                + " WHERE s.HR_OffreTestEvaluation_ID = ?"
                + " AND ce.HR_Competences_ID = ?"
                + " AND ce.IsActive = 'Y' AND ce.Score IS NOT NULL AND ce.Score > 0";
        return DB.getSQLValueEx(trxName, sql, testID, competenceID);
    }

    /** Supprimer toutes les évaluations des candidats d'une session */
    public static int supprimerEvaluationsSession(int sessionID, String trxName) {
        String sql = "DELETE FROM HR_CandidatEvaluation"
                + " WHERE HR_Candidature_ID IN ("
                + "   SELECT HR_Candidature_ID FROM HR_Candidature"
                + "   WHERE HR_SessionRecrutement_ID = ?"
                + " )";
        return DB.executeUpdateEx(sql, new Object[]{sessionID}, trxName);
    }

    /** Supprimer les évaluations d'une compétence pour toutes les sessions d'un test */
    public static int supprimerEvaluationsCompetencePourTest(int testID, int competenceID, String trxName) {
        String sql = "DELETE FROM HR_CandidatEvaluation"
                + " WHERE HR_Competences_ID = ?"
                + " AND HR_Candidature_ID IN ("
                + "   SELECT c.HR_Candidature_ID FROM HR_Candidature c"
                + "   JOIN HR_SessionRecrutement s ON s.HR_SessionRecrutement_ID = c.HR_SessionRecrutement_ID"
                + "   WHERE s.HR_OffreTestEvaluation_ID = ?"
                + " )";
        return DB.executeUpdateEx(sql, new Object[]{competenceID, testID}, trxName);
    }

    /** Liste des IDs de candidatures d'une session */
    public static ArrayList<Integer> getCandidatureIDsFromSession(int sessionID, String trxName) {
        ArrayList<Integer> resultat = new ArrayList<>();
        String sql = "SELECT HR_Candidature_ID FROM HR_Candidature"
                + " WHERE HR_SessionRecrutement_ID = ? AND IsActive = 'Y'";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, sessionID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    /** Vérifie si un candidat (BPartner) est déjà dans une session */
    public static int compteDoublonCandidature(int sessionID, int bpartnerID, String trxName) {
        String sql = "SELECT COUNT(*) FROM HR_Candidature"
                + " WHERE HR_SessionRecrutement_ID = ? AND C_BPartner_ID = ? AND IsActive = 'Y'";
        return DB.getSQLValueEx(trxName, sql, sessionID, bpartnerID);
    }

    /** Liste des IDs de sessions utilisant un test donné */
    public static ArrayList<Integer> getSessionIDsPourTest(int testID, String trxName) {
        ArrayList<Integer> resultat = new ArrayList<>();
        String sql = "SELECT HR_SessionRecrutement_ID FROM HR_SessionRecrutement"
                + " WHERE HR_OffreTestEvaluation_ID = ? AND IsActive = 'Y'";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, testID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }
}
