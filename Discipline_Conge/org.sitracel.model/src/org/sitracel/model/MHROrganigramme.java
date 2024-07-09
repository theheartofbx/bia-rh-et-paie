package org.sitracel.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHROrganigramme extends X_HR_Organigramme{
	private static final long serialVersionUID = 1L;

	public MHROrganigramme(Properties ctx, int HR_Organigramme_ID, String trxName) {
		super(ctx, HR_Organigramme_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHROrganigramme(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
