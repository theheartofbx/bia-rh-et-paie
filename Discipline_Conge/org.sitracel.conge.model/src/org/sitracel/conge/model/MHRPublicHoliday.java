package org.sitracel.conge.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MHRPublicHoliday extends X_HR_Public_Holiday{
	private static final long serialVersionUID = 2025386280248483837L;
	private static CLogger log = CLogger.get();

	public MHRPublicHoliday(Properties ctx, int HR_Public_Holiday_ID, String trxName) {
		super(ctx, HR_Public_Holiday_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRPublicHoliday(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static boolean isJourFerie(Timestamp date, String trxName) {
	    if (date == null) {
	        return false;
	    }

	    String sql = "SELECT 1 FROM " + I_HR_Public_Holiday.Table_Name +
	                 " WHERE " + I_HR_Public_Holiday.COLUMNNAME_Date_Jour_Ferie + " = ? LIMIT 1";

	    try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {
	        pstmt.setTimestamp(1, date);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            return rs.next();
	        }
	    } catch (SQLException e) {
	        log.severe("Erreur SQL dans isJourFerie: " + e.getMessage());
	        return false;
	    }
	}


}
