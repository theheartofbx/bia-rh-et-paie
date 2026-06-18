package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHREtablissement extends X_HR_Etablissement{
	private static final long serialVersionUID = -8549095627841500376L;

	public MHREtablissement(Properties ctx, int HR_Etablissement_ID, String trxName) {
		super(ctx, HR_Etablissement_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHREtablissement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
