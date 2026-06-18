package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRTypeFrais extends X_HR_TypeFrais{
	private static final long serialVersionUID = 250117467309863251L;

	public MHRTypeFrais(Properties ctx, int HR_TypeFrais_ID, String trxName) {
		super(ctx, HR_TypeFrais_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRTypeFrais(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
