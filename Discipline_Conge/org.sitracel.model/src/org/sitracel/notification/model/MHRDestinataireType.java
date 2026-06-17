package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRDestinataireType extends X_HR_DestinataireType{
	private static final long serialVersionUID = 3682040569270027736L;

	public MHRDestinataireType(Properties ctx, int HR_DestinataireType_ID, String trxName) {
		super(ctx, HR_DestinataireType_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRDestinataireType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
