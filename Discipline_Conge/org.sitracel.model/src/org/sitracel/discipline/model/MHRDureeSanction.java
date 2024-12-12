package org.sitracel.discipline.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRDureeSanction extends X_HR_Duree_Sanction{
	private static final long serialVersionUID = 1L;

	public MHRDureeSanction(Properties ctx, int HR_Duree_Sanction_ID, String trxName) {
		super(ctx, HR_Duree_Sanction_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MHRDureeSanction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
