package org.sitracel.recrutement.modelvalidator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.compiere.util.DB;
import org.sitracel.bean.BeanCandidatEvaluation;
import org.sitracel.bean.BeanEvaluationCompetence;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.recrutement.model.MHRCandidatEvaluation;
import org.sitracel.recrutement.model.MHRCandidature;
import org.sitracel.recrutement.model.MHROffreCritereEvaluation;

public class ModelValidatorSqlControllerRecrutement {
	public static ArrayList<BeanEvaluationCompetence> getCompetenceFromTestEvaluation (Integer hr_offreTestEvaluation_ID, String trxName)
	{
		ArrayList<BeanEvaluationCompetence> resultat = new ArrayList<BeanEvaluationCompetence>();
		if(hr_offreTestEvaluation_ID!=null) {
			String sql = "SELECT "+MHROffreCritereEvaluation.COLUMNNAME_HR_Competences_ID 
					+ ", "+MHROffreCritereEvaluation.COLUMNNAME_ScoreMax 
					+ " , "+MHROffreCritereEvaluation.COLUMNNAME_Ponderation 
					+ " FROM "+MHROffreCritereEvaluation.Table_Name
					+ " WHERE "
					+ MHROffreCritereEvaluation.COLUMNNAME_HR_OffreTestEvaluation_ID+"=?";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, hr_offreTestEvaluation_ID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanEvaluationCompetence beanEvaluationCompetence = BeanFactory.getBeanEvaluationCompetence();
					beanEvaluationCompetence.setCompetenceID(rs.getInt(MHROffreCritereEvaluation.COLUMNNAME_HR_Competences_ID));
					beanEvaluationCompetence.setScoreMax(rs.getBigDecimal(MHROffreCritereEvaluation.COLUMNNAME_ScoreMax));
					beanEvaluationCompetence.setPonderation(rs.getInt(MHROffreCritereEvaluation.COLUMNNAME_Ponderation));
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
		ArrayList<BeanEvaluationCompetence> resultat = new ArrayList<BeanEvaluationCompetence>();
		if(hr_candidatureID!=null) {
			String sql = "SELECT "
					+ MHRCandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID
					+ " , "+MHRCandidatEvaluation.COLUMNNAME_HR_Competences_ID 
					+ " , "+MHRCandidatEvaluation.COLUMNNAME_ScoreMax 
					+ " , "+MHRCandidatEvaluation.COLUMNNAME_Ponderation 
					+ " , "+MHRCandidatEvaluation.COLUMNNAME_Score
					+ " FROM "+MHRCandidatEvaluation.Table_Name
					+ " WHERE "
					+ MHRCandidatEvaluation.COLUMNNAME_HR_Candidature_ID+"=?";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, hr_candidatureID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanEvaluationCompetence beanEvaluationCompetence = BeanFactory.getBeanEvaluationCompetence();
					beanEvaluationCompetence.setCandidatEvaluationID(rs.getInt(MHRCandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID));
					beanEvaluationCompetence.setCompetenceID(rs.getInt(MHRCandidatEvaluation.COLUMNNAME_HR_Competences_ID));
					beanEvaluationCompetence.setScore(rs.getBigDecimal(MHRCandidatEvaluation.COLUMNNAME_Score));
					beanEvaluationCompetence.setScoreMax(rs.getBigDecimal(MHRCandidatEvaluation.COLUMNNAME_ScoreMax));
					beanEvaluationCompetence.setPonderation(rs.getInt(MHRCandidatEvaluation.COLUMNNAME_Ponderation));
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
		ArrayList<BeanCandidatEvaluation> resultat = new ArrayList<BeanCandidatEvaluation>();
		if(hr_sessionRecrutementID!=null) {
			String sql = "SELECT "+MHRCandidature.COLUMNNAME_HR_Candidature_ID 
					+ " , "+ MHRCandidature.COLUMNNAME_Name
					+ " FROM "+MHRCandidature.Table_Name
					+ " WHERE "
					+ MHRCandidature.COLUMNNAME_HR_SessionRecrutement_ID+"=?";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, hr_sessionRecrutementID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BeanCandidatEvaluation beanCandidatEvaluation = BeanFactory.getBeanCandidatEvaluation();
					beanCandidatEvaluation.setCandidatureID(rs.getInt(MHRCandidature.COLUMNNAME_HR_Candidature_ID));
					beanCandidatEvaluation.setNameCandidature(MHRCandidature.COLUMNNAME_Name);
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
		ArrayList<Integer> resultat = new ArrayList<Integer>();
		if(hr_candidatureID!=null) {
			String sql = "SELECT "+MHRCandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID 
					+ " FROM "+MHRCandidatEvaluation.Table_Name
					+ " WHERE "
					+ MHRCandidatEvaluation.COLUMNNAME_HR_Candidature_ID+"=?";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, hr_candidatureID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resultat.add(rs.getInt(MHRCandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID));
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
