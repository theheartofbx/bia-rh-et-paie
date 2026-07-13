package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRRetenueSalariale extends X_HR_Retenue_Salariale{
	private static final long serialVersionUID = -5716466313248555122L;

	public MHRRetenueSalariale(Properties ctx, int HR_Retenue_Salariale_ID, String trxName) {
		super(ctx, HR_Retenue_Salariale_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRRetenueSalariale(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
