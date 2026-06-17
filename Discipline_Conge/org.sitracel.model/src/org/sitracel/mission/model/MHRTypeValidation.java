package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRTypeValidation extends X_HR_TypeValidation{
	private static final long serialVersionUID = -3035453984900863568L;

	public MHRTypeValidation(Properties ctx, int HR_TypeValidation_ID, String trxName) {
		super(ctx, HR_TypeValidation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRTypeValidation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
