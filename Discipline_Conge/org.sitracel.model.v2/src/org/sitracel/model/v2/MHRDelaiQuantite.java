package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRDelaiQuantite extends X_HR_Delai_Quantite{
	private static final long serialVersionUID = 7333729087827327830L;

	public MHRDelaiQuantite(Properties ctx, int HR_Delai_Quantite_ID, String trxName) {
		super(ctx, HR_Delai_Quantite_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MHRDelaiQuantite(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
