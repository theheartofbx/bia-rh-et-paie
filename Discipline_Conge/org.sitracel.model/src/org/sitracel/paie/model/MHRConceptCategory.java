package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRConceptCategory extends X_HR_Concept_Category{
	private static final long serialVersionUID = -8157762529121435321L;

	public MHRConceptCategory(Properties ctx, int HR_Concept_Category_ID, String trxName) {
		super(ctx, HR_Concept_Category_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRConceptCategory(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
