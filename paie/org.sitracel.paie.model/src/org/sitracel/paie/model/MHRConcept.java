package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRConcept extends X_HR_Concept{
	private static final long serialVersionUID = 2978015105808036562L;

	public MHRConcept(Properties ctx, int HR_Concept_ID, String trxName) {
		super(ctx, HR_Concept_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRConcept(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
