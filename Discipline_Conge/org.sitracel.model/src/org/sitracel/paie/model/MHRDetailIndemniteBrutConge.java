package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRDetailIndemniteBrutConge extends X_HR_DetailIndemniteBrutConge{

	private static final long serialVersionUID = -3861197975717175447L;

	public MHRDetailIndemniteBrutConge(Properties ctx, int HR_DetailIndemniteBrutConge_ID, String trxName) {
		super(ctx, HR_DetailIndemniteBrutConge_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRDetailIndemniteBrutConge(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
