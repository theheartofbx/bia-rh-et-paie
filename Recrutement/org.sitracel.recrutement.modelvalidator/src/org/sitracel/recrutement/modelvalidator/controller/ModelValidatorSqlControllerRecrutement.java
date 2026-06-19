package org.sitracel.recrutement.modelvalidator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.compiere.util.DB;
import org.sitracel.bean.BeanCandidatEvaluation;
import org.sitracel.bean.BeanEvaluationCompetence;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.recrutement.model.I_HR_CandidatEvaluation;
import org.sitracel.recrutement.model.I_HR_Candidature;
import org.sitracel.recrutement.model.I_HR_OffreCritereEvaluation;

public class ModelValidatorSqlControllerRecrutement {
	public static ArrayList<BeanEvaluationCompetence> getCompetenceFromTestEvaluation (Integer hr_offreTestEvaluation_ID, String trxName)
	{
		ArrayList<BeanEvaluationCompetence> resultat = new ArrayList<>();
		if(hr_offreTestEvaluation_ID!=null) {
			String sql = "SELECT "
					+ I_HR_OffreCritereEvaluation.COLUMNNAME_HR_Competences_ID
					+ ", "+I_HR_OffreCritereEvaluation.COLUMNNAME_ScoreMax
					+ " , "+I_HR_OffreCritereEvaluation.COLUMNNAME_Ponderation
					+ " FROM "+I_HR_OffreCritereEvaluation.Table_Name
					+ " WHERE "
					+ I_HR_OffreCritereEvaluation.COLUMNNAME_HR_OffreTestEvaluation_ID+"=?";

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, hr_offreTestEvaluation_ID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanEvaluationCompetence beanEvaluationCompetence = BeanFactory.getBeanEvaluationCompetence();
					beanEvaluationCompetence.setCompetenceID(rs.getInt(I_HR_OffreCritereEvaluation.COLUMNNAME_HR_Competences_ID));
					beanEvaluationCompetence.setScoreMax(rs.getBigDecimal(I_HR_OffreCritereEvaluation.COLUMNNAME_ScoreMax));
					beanEvaluationCompetence.setPonderation(rs.getInt(I_HR_OffreCritereEvaluation.COLUMNNAME_Ponderation));
					if(beanEvaluationCompetence!=null) {
						resultat.add(beanEvaluationCompetence);
					}
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static ArrayList<BeanEvaluationCompetence> getScoresCandidature (Integer hr_candidatureID, String trxName)
	{
		ArrayList<BeanEvaluationCompetence> resultat = new ArrayList<>();
		if(hr_candidatureID!=null) {
			String sql = "SELECT "
					+ I_HR_CandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID
					+ " , "+I_HR_CandidatEvaluation.COLUMNNAME_HR_Competences_ID
					+ " , "+I_HR_CandidatEvaluation.COLUMNNAME_ScoreMax
					+ " , "+I_HR_CandidatEvaluation.COLUMNNAME_Ponderation
					+ " , "+I_HR_CandidatEvaluation.COLUMNNAME_Score
					+ " FROM "+I_HR_CandidatEvaluation.Table_Name
					+ " WHERE "
					+ I_HR_CandidatEvaluation.COLUMNNAME_HR_Candidature_ID+"=?";

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, hr_candidatureID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanEvaluationCompetence beanEvaluationCompetence = BeanFactory.getBeanEvaluationCompetence();
					beanEvaluationCompetence.setCandidatEvaluationID(rs.getInt(I_HR_CandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID));
					beanEvaluationCompetence.setCompetenceID(rs.getInt(I_HR_CandidatEvaluation.COLUMNNAME_HR_Competences_ID));
					beanEvaluationCompetence.setScore(rs.getBigDecimal(I_HR_CandidatEvaluation.COLUMNNAME_Score));
					beanEvaluationCompetence.setScoreMax(rs.getBigDecimal(I_HR_CandidatEvaluation.COLUMNNAME_ScoreMax));
					beanEvaluationCompetence.setPonderation(rs.getInt(I_HR_CandidatEvaluation.COLUMNNAME_Ponderation));
					if(beanEvaluationCompetence!=null) {
						resultat.add(beanEvaluationCompetence);
					}
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static ArrayList<BeanCandidatEvaluation> getListeCandidatureFromSessionRecrutement (Integer hr_sessionRecrutementID, String trxName)
	{
		ArrayList<BeanCandidatEvaluation> resultat = new ArrayList<>();
		if(hr_sessionRecrutementID!=null) {
			String sql = "SELECT "+I_HR_Candidature.COLUMNNAME_HR_Candidature_ID
					+ " , "+ I_HR_Candidature.COLUMNNAME_Name
					+ " FROM "+I_HR_Candidature.Table_Name
					+ " WHERE "
					+ I_HR_Candidature.COLUMNNAME_HR_SessionRecrutement_ID+"=?";

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, hr_sessionRecrutementID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanCandidatEvaluation beanCandidatEvaluation = BeanFactory.getBeanCandidatEvaluation();
					beanCandidatEvaluation.setCandidatureID(rs.getInt(I_HR_Candidature.COLUMNNAME_HR_Candidature_ID));
					beanCandidatEvaluation.setNameCandidature(I_HR_Candidature.COLUMNNAME_Name);
					if(beanCandidatEvaluation!=null) {
						resultat.add(beanCandidatEvaluation);
					}
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}

	public static ArrayList<Integer> getListeCompetenceFromCandidatureID (Integer hr_candidatureID, String trxName)
	{
		ArrayList<Integer> resultat = new ArrayList<>();
		if(hr_candidatureID!=null) {
			String sql = "SELECT "+I_HR_CandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID
					+ " FROM "+I_HR_CandidatEvaluation.Table_Name
					+ " WHERE "
					+ I_HR_CandidatEvaluation.COLUMNNAME_HR_Candidature_ID+"=?";

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, hr_candidatureID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat.add(rs.getInt(I_HR_CandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID));
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return resultat;
	}
}
