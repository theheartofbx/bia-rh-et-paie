package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRHistoriquePaie extends X_HR_Historique_Paie{
	private static final long serialVersionUID = 2856545576686863437L;

	public MHRHistoriquePaie(Properties ctx, int HR_Historique_Paie_ID, String trxName) {
		super(ctx, HR_Historique_Paie_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRHistoriquePaie(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
