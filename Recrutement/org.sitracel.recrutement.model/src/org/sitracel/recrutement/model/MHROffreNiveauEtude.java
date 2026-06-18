package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHROffreNiveauEtude extends X_HR_OffreNiveauEtude{
	private static final long serialVersionUID = -3522359206851903115L;

	public MHROffreNiveauEtude(Properties ctx, int HR_OffreNiveauEtude_ID, String trxName) {
		super(ctx, HR_OffreNiveauEtude_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHROffreNiveauEtude(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
