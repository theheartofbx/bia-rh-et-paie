package org.sitracel.conge.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRAutorisationConge extends X_HR_Autorisation_Conge{
	private static final long serialVersionUID = 2506896566367422969L;

	public MHRAutorisationConge(Properties ctx, int HR_Autorisation_Conge_ID, String trxName) {
		super(ctx, HR_Autorisation_Conge_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRAutorisationConge(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
