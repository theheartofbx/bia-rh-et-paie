package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRCalculConge extends X_HR_Calcul_Conge{
	private static final long serialVersionUID = -1620368999967284644L;

	public MHRCalculConge(Properties ctx, int HR_Calcul_Conge_ID, String trxName) {
		super(ctx, HR_Calcul_Conge_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRCalculConge(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
