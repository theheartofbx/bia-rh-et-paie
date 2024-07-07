package org.sitracel.discipline.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRDemandeExplication extends X_HR_Demande_Explication{	
	private static final long serialVersionUID = -8068919652132853925L;
	
	public MHRDemandeExplication(Properties ctx, int HR_Demande_Explication_ID, String trxName) {
		super(ctx, HR_Demande_Explication_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRDemandeExplication(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	

}
