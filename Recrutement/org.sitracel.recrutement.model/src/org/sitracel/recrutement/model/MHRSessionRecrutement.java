package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRSessionRecrutement extends X_HR_SessionRecrutement{
	private static final long serialVersionUID = 8858328992641402971L;

	public MHRSessionRecrutement(Properties ctx, int HR_SessionRecrutement_ID, String trxName) {
		super(ctx, HR_SessionRecrutement_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRSessionRecrutement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
