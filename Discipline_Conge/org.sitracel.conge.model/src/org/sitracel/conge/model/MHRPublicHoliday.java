package org.sitracel.conge.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MHRPublicHoliday extends X_HR_Public_Holiday {
    private static final long serialVersionUID = 2025386280248483837L;
    private static CLogger log = CLogger.getCLogger(MHRPublicHoliday.class);

    public MHRPublicHoliday(Properties ctx, int HR_Public_Holiday_ID, String trxName) {
        super(ctx, HR_Public_Holiday_ID, trxName);
    }

    public MHRPublicHoliday(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Verifie si une date est un dimanche.
     */
    public static boolean isDimanche(Timestamp date) {
        if (date == null) return false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * Retourne le nom du jour ferie si la date en est un, null sinon.
     */
    public static String getNomJourFerie(Timestamp date, String trxName) {
        if (date == null) return null;

        String sql = "SELECT " + COLUMNNAME_Nom_Jour_Ferie
            + " FROM " + Table_Name
            + " WHERE " + COLUMNNAME_Date_Jour_Ferie + " = ?";

        String nom = DB.getSQLValueString(trxName, sql, date);
        return nom;
    }

    /**
     * Verifie si une date est un jour non ouvrable (dimanche ou jour ferie).
     */
    public static boolean isJourFerie(Timestamp date, String trxName) {
        if (date == null) return false;
        if (isDimanche(date)) return true;
        return getNomJourFerie(date, trxName) != null;
    }
}
