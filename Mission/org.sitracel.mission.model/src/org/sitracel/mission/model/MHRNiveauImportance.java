package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRNiveauImportance extends X_HR_Niveau_Importance{
	private static final long serialVersionUID = 4084914841148525448L;

	public MHRNiveauImportance(Properties ctx, int HR_Niveau_Importance_ID, String trxName) {
		super(ctx, HR_Niveau_Importance_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRNiveauImportance(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
