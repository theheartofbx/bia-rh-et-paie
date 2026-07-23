package org.sitracel.paie.process.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Logger;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.paie.model.I_HR_Periode_Salariale;
import org.sitracel.paie.model.MHRPeriodeSalariale;

/**
 * Accès SQL pour les périodes salariales.
 */
public class PeriodeSalarialeRepository {

    private static final Logger log =
            Logger.getLogger(PeriodeSalarialeRepository.class.getName());

    /**
     * Retourne la période dont la date de début correspond exactement.
     */
    public static MHRPeriodeSalariale getPeriodeParDebut(
            Timestamp dateDebut, String trxName) {

        String sql = "SELECT * FROM " + I_HR_Periode_Salariale.Table_Name
                + " WHERE " + I_HR_Periode_Salariale.COLUMNNAME_Date_Debut_Defaut + "=?"
                + " AND " + I_HR_Periode_Salariale.COLUMNNAME_IsActive + "='Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setTimestamp(1, dateDebut);
            rs = pstmt.executeQuery();
            if (rs.next())
                return new MHRPeriodeSalariale(Env.getCtx(), rs, trxName);
        } catch (SQLException e) {
            log.severe("getPeriodeParDebut : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    /**
     * Retourne la période active qui contient la date donnée.
     */
    public static MHRPeriodeSalariale getPeriodePourDate(
            Timestamp date, String trxName) {

        String sql = "SELECT * FROM " + I_HR_Periode_Salariale.Table_Name
                + " WHERE " + I_HR_Periode_Salariale.COLUMNNAME_Date_Debut_Defaut + "<=?"
                + " AND " + I_HR_Periode_Salariale.COLUMNNAME_Date_Fin_Defaut + ">=?"
                + " AND " + I_HR_Periode_Salariale.COLUMNNAME_IsActive + "='Y'"
                + " ORDER BY " + I_HR_Periode_Salariale.COLUMNNAME_Date_Debut_Defaut + " DESC";
                

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setTimestamp(1, date);
            pstmt.setTimestamp(2, date);
            rs = pstmt.executeQuery();
            if (rs.next())
                return new MHRPeriodeSalariale(Env.getCtx(), rs, trxName);
        } catch (SQLException e) {
            log.severe("getPeriodePourDate : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    /**
     * Vérifie si une période chevauche une existante.
     */
    public static boolean existeChevauchement(Timestamp debut, Timestamp fin,
                                               String trxName) {
        String sql = "SELECT COUNT(*) FROM " + I_HR_Periode_Salariale.Table_Name
                + " WHERE " + I_HR_Periode_Salariale.COLUMNNAME_IsActive + "='Y'"
                + " AND " + I_HR_Periode_Salariale.COLUMNNAME_Date_Debut_Defaut + "<?"
                + " AND " + I_HR_Periode_Salariale.COLUMNNAME_Date_Fin_Defaut + ">?";

        int count = DB.getSQLValue(trxName, sql, fin, debut);
        return count > 0;
    }

    /**
     * Rapport de cohérence : trous et chevauchements.
     */
    public static String verifierCoherence(String trxName) {
        StringBuilder rapport = new StringBuilder();

        String sql = "SELECT Name, Date_Debut_Defaut, Date_Fin_Defaut "
                + "FROM " + I_HR_Periode_Salariale.Table_Name
                + " WHERE IsActive='Y'"
                + " ORDER BY Date_Debut_Defaut";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            rs = pstmt.executeQuery();

            String nomPrec = null;
            Timestamp finPrec = null;

            while (rs.next()) {
                String nom    = rs.getString("Name");
                Timestamp deb = rs.getTimestamp("Date_Debut_Defaut");
                Timestamp fin = rs.getTimestamp("Date_Fin_Defaut");

                if (finPrec != null) {
                    long diffJours = (deb.getTime() - finPrec.getTime())
                            / (1000L * 60 * 60 * 24);
                    if (diffJours > 1) {
                        rapport.append("TROU entre ").append(nomPrec)
                               .append(" et ").append(nom)
                               .append(" (").append(diffJours - 1).append(" jour(s))\n");
                    } else if (diffJours < 1) {
                        rapport.append("CHEVAUCHEMENT entre ").append(nomPrec)
                               .append(" et ").append(nom).append("\n");
                    }
                }
                nomPrec = nom;
                finPrec = fin;
            }
        } catch (SQLException e) {
            rapport.append("ERREUR SQL : ").append(e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }

        return rapport.length() == 0 ? "OK — aucune anomalie détectée" : rapport.toString();
    }
}
