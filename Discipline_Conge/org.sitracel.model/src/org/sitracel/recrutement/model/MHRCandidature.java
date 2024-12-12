package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRCandidature extends X_HR_Candidature{
	private static final long serialVersionUID = -7043416023753076256L;

	public MHRCandidature(Properties ctx, int HR_Candidature_ID, String trxName) {
		super(ctx, HR_Candidature_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRCandidature(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
