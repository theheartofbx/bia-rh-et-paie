package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRDossierDisciplinaire extends X_HR_Dossier_Disciplinaire{
	private static final long serialVersionUID = -2451757982617769995L;

	public MHRDossierDisciplinaire(Properties ctx, int HR_Dossier_Disciplinaire_ID, String trxName) {
		super(ctx, HR_Dossier_Disciplinaire_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRDossierDisciplinaire(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
