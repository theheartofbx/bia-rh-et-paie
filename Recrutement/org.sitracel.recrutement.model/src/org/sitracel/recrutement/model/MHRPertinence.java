package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRPertinence extends X_HR_Pertinence{
	private static final long serialVersionUID = 2904394675215374593L;

	public MHRPertinence(Properties ctx, int HR_Pertinence_ID, String trxName) {
		super(ctx, HR_Pertinence_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRPertinence(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
