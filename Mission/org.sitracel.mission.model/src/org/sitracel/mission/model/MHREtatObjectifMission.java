package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHREtatObjectifMission extends X_HR_EtatObjectifMission{
	private static final long serialVersionUID = 9030980976923357781L;

	public MHREtatObjectifMission(Properties ctx, int HR_EtatObjectifMission_ID, String trxName) {
		super(ctx, HR_EtatObjectifMission_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHREtatObjectifMission(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
