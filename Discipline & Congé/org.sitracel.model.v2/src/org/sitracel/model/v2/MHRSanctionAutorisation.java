package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRSanctionAutorisation extends X_HR_Sanction_Autorisation{
	private static final long serialVersionUID = 4881615761907857388L;

	public MHRSanctionAutorisation(Properties ctx, int HR_Sanction_Autorisation_ID, String trxName) {
		super(ctx, HR_Sanction_Autorisation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRSanctionAutorisation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
