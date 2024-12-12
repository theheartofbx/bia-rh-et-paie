package org.sitracel.conge.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRTypeConge extends X_HR_Type_Conge{
	private static final long serialVersionUID = 7056939466864769004L;
	
	public MHRTypeConge(Properties ctx, int HR_Type_Conge_ID, String trxName) {
		super(ctx, HR_Type_Conge_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRTypeConge(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
