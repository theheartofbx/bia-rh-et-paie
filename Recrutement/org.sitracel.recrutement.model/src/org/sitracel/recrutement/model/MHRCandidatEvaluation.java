package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRCandidatEvaluation extends X_HR_CandidatEvaluation{
	private static final long serialVersionUID = 3656363197165198403L;

	public MHRCandidatEvaluation(Properties ctx, int HR_Candidat_Evaluation_ID, String trxName) {
		super(ctx, HR_Candidat_Evaluation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRCandidatEvaluation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
