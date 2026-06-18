package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRTauxSalarial extends X_HR_Taux_Salarial{
	private static final long serialVersionUID = -2115662014928298028L;

	public MHRTauxSalarial(Properties ctx, int HR_Taux_Salarial_ID, String trxName) {
		super(ctx, HR_Taux_Salarial_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRTauxSalarial(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
