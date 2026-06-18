package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRTypeDeCalcul extends X_HR_Type_Calcul{
	private static final long serialVersionUID = 4324740703094635932L;

	public MHRTypeDeCalcul(Properties ctx, int HR_Type_Calcul_ID, String trxName) {
		super(ctx, HR_Type_Calcul_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRTypeDeCalcul(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
