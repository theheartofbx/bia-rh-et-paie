package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRMouvementPaie extends X_HR_Mouvement_Paie{
	private static final long serialVersionUID = -5716466313248555122L;

	public MHRMouvementPaie(Properties ctx, int HR_Mouvement_Paie_ID, String trxName) {
		super(ctx, HR_Mouvement_Paie_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRMouvementPaie(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
