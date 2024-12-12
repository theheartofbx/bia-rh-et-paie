package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHROffreCritereEvaluation extends X_HR_OffreCritereEvaluation{
	private static final long serialVersionUID = -9032036337088641932L;

	public MHROffreCritereEvaluation(Properties ctx, int HR_OffreCritereEvaluation_ID, String trxName) {
		super(ctx, HR_OffreCritereEvaluation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHROffreCritereEvaluation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
