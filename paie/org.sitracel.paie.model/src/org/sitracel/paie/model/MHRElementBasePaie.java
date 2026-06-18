package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRElementBasePaie extends X_HR_Element_Base_Paie{
	private static final long serialVersionUID = -1455528649938523652L;

	public MHRElementBasePaie(Properties ctx, int HR_Element_Base_Paie_ID, String trxName) {
		super(ctx, HR_Element_Base_Paie_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRElementBasePaie(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
