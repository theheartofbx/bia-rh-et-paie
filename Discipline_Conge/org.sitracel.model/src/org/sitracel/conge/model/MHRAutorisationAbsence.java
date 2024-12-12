package org.sitracel.conge.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRAutorisationAbsence extends X_HR_Autorisation_Absence{
	private static final long serialVersionUID = -2428059454965423483L;
	
	public MHRAutorisationAbsence(Properties ctx, int HR_Autorisation_Absence_ID, String trxName) {
		super(ctx, HR_Autorisation_Absence_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRAutorisationAbsence(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
