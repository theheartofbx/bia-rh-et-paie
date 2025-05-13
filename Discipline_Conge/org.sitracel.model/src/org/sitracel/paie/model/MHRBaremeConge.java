package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRBaremeConge extends X_HR_Bareme_Conge{
	private static final long serialVersionUID = -4414082833271388514L;

	public MHRBaremeConge(Properties ctx, int HR_Bareme_Conge_ID, String trxName) {
		super(ctx, HR_Bareme_Conge_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRBaremeConge(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
