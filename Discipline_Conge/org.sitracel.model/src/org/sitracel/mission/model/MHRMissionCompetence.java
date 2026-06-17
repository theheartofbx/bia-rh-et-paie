package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRMissionCompetence extends X_HR_Mission_Competence{
	private static final long serialVersionUID = -6619978567791732432L;

	public MHRMissionCompetence(Properties ctx, int HR_Mission_Competence_ID, String trxName) {
		super(ctx, HR_Mission_Competence_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRMissionCompetence(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
