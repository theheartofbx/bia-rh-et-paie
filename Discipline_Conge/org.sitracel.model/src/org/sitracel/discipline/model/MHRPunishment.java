package org.sitracel.discipline.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRPunishment extends X_HR_Punishment{

	private static final long serialVersionUID = 4925474680186092992L;

	public MHRPunishment(Properties ctx, int HR_Punishment_ID, String trxName) {
		super(ctx, HR_Punishment_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRPunishment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
