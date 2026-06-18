package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRTypeDeCharge extends X_HR_TypeDeCharge{
	private static final long serialVersionUID = 8616810649218902961L;

	public MHRTypeDeCharge(Properties ctx, int HR_TypeDeCharge_ID, String trxName) {
		super(ctx, HR_TypeDeCharge_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRTypeDeCharge(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
