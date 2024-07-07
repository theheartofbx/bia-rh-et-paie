package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRParametreNumerique extends X_HR_Parametre_Numerique{
	private static final long serialVersionUID = 6300389732144808412L;
	
	public MHRParametreNumerique(Properties ctx, int HR_Parametre_Numerique_ID, String trxName) {
		super(ctx, HR_Parametre_Numerique_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRParametreNumerique(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
