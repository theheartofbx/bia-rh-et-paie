package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHREntreprise extends X_HR_Entreprise{
	private static final long serialVersionUID = 7599379547938486074L;

	public MHREntreprise(Properties ctx, int HR_Entreprise_ID, String trxName) {
		super(ctx, HR_Entreprise_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHREntreprise(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
