package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRElementBasePaieEmploye extends X_HR_ElementBasePaieEmploye{
	private static final long serialVersionUID = 8110747265653286978L;

	public MHRElementBasePaieEmploye(Properties ctx, int HR_Element_Base_Paie_Employe_ID, String trxName) {
		super(ctx, HR_Element_Base_Paie_Employe_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRElementBasePaieEmploye(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
