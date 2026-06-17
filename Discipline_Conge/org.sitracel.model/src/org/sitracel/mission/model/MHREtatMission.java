package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHREtatMission extends X_HR_Etat_Mission{
	private static final long serialVersionUID = 7353107363064512111L;

	public MHREtatMission(Properties ctx, int HR_Etat_Mission_ID, String trxName) {
		super(ctx, HR_Etat_Mission_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHREtatMission(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
