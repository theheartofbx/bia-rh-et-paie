package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRMissionObjectif extends X_HR_Mission_Objectif{
	private static final long serialVersionUID = 1563562279275158887L;

	public MHRMissionObjectif(Properties ctx, int HR_Mission_Objectif_ID, String trxName) {
		super(ctx, HR_Mission_Objectif_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRMissionObjectif(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
