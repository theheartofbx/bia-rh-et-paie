package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHROffreEmploi extends X_HR_OffreEmploi{
	private static final long serialVersionUID = 5332751716555956885L;

	public MHROffreEmploi(Properties ctx, int HR_OffreEmploi_ID, String trxName) {
		super(ctx, HR_OffreEmploi_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHROffreEmploi(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
