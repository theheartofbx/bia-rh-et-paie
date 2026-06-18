package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRExperience extends X_HR_Experience{
	private static final long serialVersionUID = -8898863912709432070L;

	public MHRExperience(Properties ctx, int HR_Experience_ID, String trxName) {
		super(ctx, HR_Experience_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRExperience(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
