package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRRoleMissionEmploye extends X_HR_RoleMissionEmploye{
	private static final long serialVersionUID = -4902243176787616244L;

	public MHRRoleMissionEmploye(Properties ctx, int HR_RoleMissionEmploye_ID, String trxName) {
		super(ctx, HR_RoleMissionEmploye_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRRoleMissionEmploye(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
