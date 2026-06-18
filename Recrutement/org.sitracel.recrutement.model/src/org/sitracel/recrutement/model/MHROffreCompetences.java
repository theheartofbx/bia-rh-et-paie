package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHROffreCompetences extends X_HR_OffreCompetences{
	private static final long serialVersionUID = -3980515622889892604L;

	public MHROffreCompetences(Properties ctx, int HR_OffreCompetences_ID, String trxName) {
		super(ctx, HR_OffreCompetences_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHROffreCompetences(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
