package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRTypeEtablissement extends X_HR_TypeEtablissement{
	private static final long serialVersionUID = -3440155812298649718L;

	public MHRTypeEtablissement(Properties ctx, int HR_TypeEtablissement_ID, String trxName) {
		super(ctx, HR_TypeEtablissement_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRTypeEtablissement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
