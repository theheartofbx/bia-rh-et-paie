package org.sitracel.conge.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRAbsenceCompensation extends X_HR_Absence_Compensation{
	private static final long serialVersionUID = -8334579225094634758L;
	
	public MHRAbsenceCompensation(Properties ctx, int HR_Absence_Compensation_ID, String trxName) {
		super(ctx, HR_Absence_Compensation_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRAbsenceCompensation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
