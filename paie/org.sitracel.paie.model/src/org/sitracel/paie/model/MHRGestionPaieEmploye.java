package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRGestionPaieEmploye extends X_HR_GestionPaieEmploye{
	private static final long serialVersionUID = 8443931620479417168L;

	public MHRGestionPaieEmploye(Properties ctx, int HR_GestionPaieEmploye_ID, String trxName) {
		super(ctx, HR_GestionPaieEmploye_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRGestionPaieEmploye(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
