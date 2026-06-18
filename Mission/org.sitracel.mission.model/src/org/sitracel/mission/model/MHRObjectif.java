package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRObjectif extends X_HR_Objectif{
	private static final long serialVersionUID = 412106968109259690L;

	public MHRObjectif(Properties ctx, int HR_Objectif_ID, String trxName) {
		super(ctx, HR_Objectif_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRObjectif(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
