package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;
import org.sitracel.model.MHRCibleType;

public class MHRNotificationAcces extends X_HR_NotificationAcces{
	private static final long serialVersionUID = 5965462671629206610L;

	public MHRNotificationAcces(Properties ctx, int HR_NotificationAcces_ID, String trxName) {
		super(ctx, HR_NotificationAcces_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRNotificationAcces(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	public boolean isRoleTarget() {

	    int cibleTypeId = getHR_CibleType_ID();
	    if (cibleTypeId <= 0) {
	        return false;
	    }

	    MHRCibleType typeCible = new MHRCibleType(
	            Env.getCtx(),
	            cibleTypeId,
	            get_TrxName()
	    );

	    String name = typeCible.getName();
	    return "ROLE".equalsIgnoreCase(name);
	}

	public boolean isHierarchyTarget() {
		
		int cibleTypeId = getHR_CibleType_ID();
	    if (cibleTypeId <= 0) {
	        return false;
	    }

	    MHRCibleType typeCible = new MHRCibleType(
	            Env.getCtx(),
	            cibleTypeId,
	            get_TrxName()
	    );

	    String name = typeCible.getName();
	    return "HIERARCHY".equalsIgnoreCase(name);
	}

	public boolean isBP() {
		
		int cibleTypeId = getHR_CibleType_ID();
	    if (cibleTypeId <= 0) {
	        return false;
	    }

	    MHRCibleType typeCible = new MHRCibleType(
	            Env.getCtx(),
	            cibleTypeId,
	            get_TrxName()
	    );

	    String name = typeCible.getName();
	    
	    return "BP".equalsIgnoreCase(name);
	}
	

	public boolean isEmetteur() {
		
		int cibleTypeId = getHR_CibleType_ID();
	    if (cibleTypeId <= 0) {
	        return false;
	    }

	    MHRCibleType typeCible = new MHRCibleType(
	            Env.getCtx(),
	            cibleTypeId,
	            get_TrxName()
	    );

	    String name = typeCible.getName();
	    
	    return "Emetteur".equalsIgnoreCase(name);
	}
	

	public boolean isEmploye() {
		
		int cibleTypeId = getHR_CibleType_ID();
	    if (cibleTypeId <= 0) {
	        return false;
	    }

	    MHRCibleType typeCible = new MHRCibleType(
	            Env.getCtx(),
	            cibleTypeId,
	            get_TrxName()
	    );

	    String name = typeCible.getName();
	    
	    return "Employe".equalsIgnoreCase(name);
	}

}
