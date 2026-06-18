package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRGestionPresence extends X_HR_Gestion_Presence{
	private static final long serialVersionUID = 1888219544252492806L;

	public MHRGestionPresence(Properties ctx, int HR_Gestion_Presence_ID, String trxName) {
		super(ctx, HR_Gestion_Presence_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRGestionPresence(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
