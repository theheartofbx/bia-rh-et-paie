package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRTypeTauxSalarial extends X_HR_Type_Taux_Salarial{
	private static final long serialVersionUID = -2013926380795349377L;

	public MHRTypeTauxSalarial(Properties ctx, int HR_Type_Taux_Salarial_ID, String trxName) {
		super(ctx, HR_Type_Taux_Salarial_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRTypeTauxSalarial(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
