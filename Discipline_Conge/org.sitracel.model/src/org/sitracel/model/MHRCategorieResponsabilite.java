package org.sitracel.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRCategorieResponsabilite extends X_HR_Categorie_Responsabilite{
	private static final long serialVersionUID = -3276988883898789062L;

	public MHRCategorieResponsabilite(Properties ctx, int HR_Categorie_Responsabilite_ID, String trxName) {
		super(ctx, HR_Categorie_Responsabilite_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRCategorieResponsabilite(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
