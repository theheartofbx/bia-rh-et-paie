package org.sitracel.recrutement.callout.candidatevaluation;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.sitracel.recrutement.model.MHRCandidatEvaluation;

public class CalloutScoreCompetence implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		BigDecimal score = (BigDecimal)mTab.getValue(MHRCandidatEvaluation.COLUMNNAME_Score);
		BigDecimal scoreMax = (BigDecimal)mTab.getValue(MHRCandidatEvaluation.COLUMNNAME_ScoreMax);
		if(score!=null && scoreMax!=null) {
			if(score.compareTo(scoreMax)>0) {
				mTab.setValue(MHRCandidatEvaluation.COLUMNNAME_Score, BigDecimal.valueOf(0));
			}
		}
		/*
		 * Integer candidatureID =
		 * (Integer)mTab.getValue(MHRCandidatEvaluation.COLUMNNAME_HR_Candidature_ID);
		 * if(candidatureID!=null) { MHRCandidature candidature = new
		 * MHRCandidature(Env.getCtx(), candidatureID, null); if(candidature!=null) {
		 * ModelValidatorControllerRecrutement.actualiserCandidature(candidature.
		 * getHR_SessionRecrutement_ID()); } }
		 */
		return null;
	}

}
