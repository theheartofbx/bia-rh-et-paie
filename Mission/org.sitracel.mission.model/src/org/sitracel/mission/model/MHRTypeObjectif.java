package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRTypeObjectif extends X_HR_TypeObjectif{
	private static final long serialVersionUID = -6070347864949120412L;

	public MHRTypeObjectif(Properties ctx, int HR_TypeObjectif_ID, String trxName) {
		super(ctx, HR_TypeObjectif_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRTypeObjectif(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
