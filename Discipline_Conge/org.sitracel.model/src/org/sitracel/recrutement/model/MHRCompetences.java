package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRCompetences extends X_HR_Competences{
	private static final long serialVersionUID = 6884578960399738308L;

	public MHRCompetences(Properties ctx, int HR_Competences_ID, String trxName) {
		super(ctx, HR_Competences_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRCompetences(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
