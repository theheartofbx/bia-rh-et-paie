package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRPeriodeSalariale extends X_HR_Periode_Salariale{
	private static final long serialVersionUID = -1146797556088576719L;

	public MHRPeriodeSalariale(Properties ctx, int HR_Periode_Salariale_ID, String trxName) {
		super(ctx, HR_Periode_Salariale_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRPeriodeSalariale(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
