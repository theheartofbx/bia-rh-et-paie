package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRMissionValidation extends X_HR_Mission_Validation{
	private static final long serialVersionUID = -5426265174548654086L;

	public MHRMissionValidation(Properties ctx, int HR_Mission_Validation_ID, String trxName) {
		super(ctx, HR_Mission_Validation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRMissionValidation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
