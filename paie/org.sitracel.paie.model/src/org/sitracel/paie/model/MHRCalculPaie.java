package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRCalculPaie extends X_HR_Calcul_Paie{
	private static final long serialVersionUID = -5182251629374818892L;

	public MHRCalculPaie(Properties ctx, int HR_Calcul_Paie_ID, String trxName) {
		super(ctx, HR_Calcul_Paie_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRCalculPaie(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
