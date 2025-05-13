package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRRangCalcul extends X_HR_Rang_Calcul{
	private static final long serialVersionUID = -2634177770834596875L;

	public MHRRangCalcul(Properties ctx, int HR_Rang_Calcul_ID, String trxName) {
		super(ctx, HR_Rang_Calcul_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRRangCalcul(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
