package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MBIABareme extends X_BIA_Bareme{
	private static final long serialVersionUID = 3142183321437647302L;

	public MBIABareme(Properties ctx, int BIA_Bareme_ID, String trxName) {
		super(ctx, BIA_Bareme_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBIABareme(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
