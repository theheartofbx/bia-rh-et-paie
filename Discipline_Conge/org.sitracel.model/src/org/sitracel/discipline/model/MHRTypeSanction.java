package org.sitracel.discipline.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRTypeSanction extends X_HR_TypeSanction{
	private static final long serialVersionUID = 3838849082413089306L;

	public MHRTypeSanction(Properties ctx, int HR_TypeSanction_ID, String trxName) {
		super(ctx, HR_TypeSanction_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRTypeSanction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
