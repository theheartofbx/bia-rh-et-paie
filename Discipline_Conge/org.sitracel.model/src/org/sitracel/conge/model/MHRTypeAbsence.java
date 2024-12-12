package org.sitracel.conge.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRTypeAbsence extends X_HR_Type_Absence{
	private static final long serialVersionUID = -223097985766997066L;
	
	public MHRTypeAbsence(Properties ctx, int HR_Type_Absence_ID, String trxName) {
		super(ctx, HR_Type_Absence_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRTypeAbsence(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
