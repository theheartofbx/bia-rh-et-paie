package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRCandidatExperience extends X_HR_Candidat_Expérience{
	private static final long serialVersionUID = 7387404969240764544L;

	public MHRCandidatExperience(Properties ctx, int HR_Candidat_Expérience_ID, String trxName) {
		super(ctx, HR_Candidat_Expérience_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MHRCandidatExperience(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
