package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRCalculIndemniteConge extends X_HR_Calcul_Indemnite_Conge{
	private static final long serialVersionUID = -5250701264798025091L;

	public MHRCalculIndemniteConge(Properties ctx, int HR_Calcul_Indemnite_Conge_ID, String trxName) {
		super(ctx, HR_Calcul_Indemnite_Conge_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRCalculIndemniteConge(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
