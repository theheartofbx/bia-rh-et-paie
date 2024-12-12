package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHROffreTestEvaluation extends X_HR_OffreTestEvaluation{
	private static final long serialVersionUID = -6078664727457423473L;

	public MHROffreTestEvaluation(Properties ctx, int HR_OffreTestEvaluation_ID, String trxName) {
		super(ctx, HR_OffreTestEvaluation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHROffreTestEvaluation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
