package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRDelaiReponse extends X_HR_Delai_Reponse{
	private static final long serialVersionUID = 1L;

	public MHRDelaiReponse(Properties ctx, int HR_Delai_Reponse_ID, String trxName) {
		super(ctx, HR_Delai_Reponse_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRDelaiReponse(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
