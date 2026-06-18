package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRMissionFrais extends X_HR_Mission_Frais{
	private static final long serialVersionUID = -2157519416987854180L;

	public MHRMissionFrais(Properties ctx, int HR_Mission_Frais_ID, String trxName) {
		super(ctx, HR_Mission_Frais_ID, trxName);
		// TODO Auto-generated constructor stub
	}


	public MHRMissionFrais(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
