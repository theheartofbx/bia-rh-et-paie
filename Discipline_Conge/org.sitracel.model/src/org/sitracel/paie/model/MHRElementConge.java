package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRElementConge extends X_HR_Element_Conge{
	private static final long serialVersionUID = 6428211452767979305L;

	public MHRElementConge(Properties ctx, int HR_Element_Conge_ID, String trxName) {
		super(ctx, HR_Element_Conge_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRElementConge(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
