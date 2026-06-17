package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRCategorieTypeObjectif extends X_HR_CategorieTypeObjectif{
	private static final long serialVersionUID = 7712213546984492961L;

	public MHRCategorieTypeObjectif(Properties ctx, int HR_CategorieTypeObjectif_ID, String trxName) {
		super(ctx, HR_CategorieTypeObjectif_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRCategorieTypeObjectif(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
