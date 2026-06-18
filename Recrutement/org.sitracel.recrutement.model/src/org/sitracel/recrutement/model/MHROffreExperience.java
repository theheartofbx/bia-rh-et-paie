package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHROffreExperience extends X_HR_OffreExperience{
	private static final long serialVersionUID = -6473008879684777997L;

	public MHROffreExperience(Properties ctx, int HR_OffreExperience_ID, String trxName) {
		super(ctx, HR_OffreExperience_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHROffreExperience(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
