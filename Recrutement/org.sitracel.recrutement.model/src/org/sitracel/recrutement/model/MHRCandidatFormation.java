package org.sitracel.recrutement.model;
import java.sql.ResultSet;
import java.util.Properties;

public class MHRCandidatFormation extends X_HR_Candidat_Formation{
	private static final long serialVersionUID = 3551905883167219127L;

	public MHRCandidatFormation(Properties ctx, int HR_Candidat_Formation_ID, String trxName) {
		super(ctx, HR_Candidat_Formation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRCandidatFormation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
