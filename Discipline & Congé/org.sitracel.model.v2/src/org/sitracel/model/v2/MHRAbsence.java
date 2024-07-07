package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRAbsence extends X_HR_Absence{
	private static final long serialVersionUID = 5222884604191928555L;
	
	public MHRAbsence(Properties ctx, int HR_Absence_ID, String trxName) {
		super(ctx, HR_Absence_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRAbsence(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
