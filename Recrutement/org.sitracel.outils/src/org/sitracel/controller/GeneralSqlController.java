package org.sitracel.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanConge;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.conge.HRCongeRepository;
import org.sitracel.employe.HREmployeRepository;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.model.MHROrganigramme;
import org.sitracel.paie.model.I_HR_Calcul_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Indemnite_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Paie;
import org.sitracel.paie.model.I_HR_ElementBasePaieEmploye;
import org.sitracel.paie.model.I_HR_GestionPaieEmploye;
import org.sitracel.paie.model.I_HR_Historique_Paie;
import org.sitracel.paie.model.I_HR_Periode_Salariale;
import org.sitracel.paie.model.I_HR_Retenue_Salariale;
import org.sitracel.paie.model.MHRCalculConge;
import org.sitracel.paie.model.MHRCalculIndemniteConge;
import org.sitracel.paie.model.MHRCalculPaie;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRGestionPaieEmploye;
import org.sitracel.paie.model.MHRHistoriquePaie;
import org.sitracel.paie.model.MHRPeriodeSalariale;
import org.sitracel.paie.model.MHRRetenueSalariale;
import org.sitracel.parametrage.HRParametreService;

/**
 * @deprecated Utiliser les repositories dédiés à la place :
 *   - Congés / absences / jours fériés → {@link HRCongeRepository}
 *   - Employés / hiérarchie            → {@link HREmployeRepository}
 *   - Paramètres système               → {@link HRParametreService}
 *   - Données paie                     → PayrollRepository (à créer module Paie)
 *
 * Les méthodes paie (reset, calcul, historique, retenues) sont conservées
 * ici temporairement jusqu'à la création du PayrollRepository.
 * Ne pas ajouter de nouvelles méthodes ici.
 */
@Deprecated
public class GeneralSqlController {

    public static CLogger log = CLogger.getCLogger(PO.class);

    // =========================================================================
    // DÉLÉGATION → HRCongeRepository
    // =========================================================================

    /** @deprecated Utiliser {@link HRCongeRepository#isJourAbsence} */
    @Deprecated
    public static boolean isJourAbsence(Integer bpartnerID, Timestamp date, String trxName) {
        return HRCongeRepository.isJourAbsence(bpartnerID, date, trxName);
    }

    /** @deprecated Utiliser {@link HRCongeRepository#isPeriodeAbsence} */
    @Deprecated
    public static boolean isPeriodeAbsence(Integer bpartnerID, Timestamp dateDebut,
                                            Timestamp dateFin, String trxName) {
        return HRCongeRepository.isPeriodeAbsence(bpartnerID, dateDebut, dateFin, trxName);
    }

    /** @deprecated Utiliser {@link HRCongeRepository#isJourAnyCongeNonRejete} */
    @Deprecated
    public static boolean isJourAnyCongeNonRejete(Integer bpartnerID, Timestamp date,
                                                   String trxName) {
        return HRCongeRepository.isJourAnyCongeNonRejete(bpartnerID, date, trxName);
    }

    /** @deprecated Utiliser {@link HRCongeRepository#chevaucheAnyCongeNonRejete} */
    @Deprecated
    public static boolean chevaucheAnyCongeNonRejete(Integer bpartnerID, Timestamp dateDebut,
                                                      Timestamp dateFin, String trxName) {
        return HRCongeRepository.chevaucheAnyCongeNonRejete(bpartnerID, dateDebut, dateFin, trxName);
    }

    /** @deprecated Utiliser {@link HRCongeRepository#chevaucheSuspensionNonRejete} */
    @Deprecated
    public static boolean chevaucheSuspensionNonRejete(Integer bpartnerID, Timestamp dateDebut,
                                                        Timestamp dateFin, String trxName) {
        return HRCongeRepository.chevaucheSuspensionNonRejete(bpartnerID, dateDebut, dateFin, trxName);
    }

    /** @deprecated Utiliser {@link HRCongeRepository#isJourSuspensionNonRejete} */
    @Deprecated
    public static boolean isJourSuspensionNonRejete(Integer bpartnerID, Timestamp date,
                                                     String trxName) {
        return HRCongeRepository.isJourSuspensionNonRejete(bpartnerID, date, trxName);
    }

    /** @deprecated Utiliser {@link HRCongeRepository#isJourCongesNonRejeteByNameConge} */
    @Deprecated
    public static boolean isJourCongesNonRejetebyNameConge(Integer bpartnerID, String nomConge,
                                                            Timestamp date, String trxName) {
        return HRCongeRepository.isJourCongesNonRejeteByNameConge(bpartnerID, nomConge, date, trxName);
    }

    /** @deprecated Utiliser {@link HRCongeRepository#getAllJoursFeries} */
    @Deprecated
    public static Set<LocalDate> getAllJoursFeries(Timestamp dateDebut, Timestamp dateFin,
                                                   String trxName) {
        return HRCongeRepository.getAllJoursFeries(dateDebut, dateFin, trxName);
    }

    /** @deprecated Utiliser {@link HRCongeRepository#getCongesValideByNameConge} */
    @Deprecated
    public static BeanPeriode[] getCongesValidebyNameConge(Integer idCBPartner, String nomConge,
                                                            Timestamp dateDebut, Timestamp dateFin,
                                                            String trxName) {
        return HRCongeRepository.getCongesValideByNameConge(idCBPartner, nomConge,
            dateDebut, dateFin, trxName);
    }

    /** @deprecated Utiliser {@link HRCongeRepository#getDateDernierConge} */
    @Deprecated
    public static BeanConge getDateDernierConge(Integer idCBPartner, Timestamp dateMax,
                                                 Timestamp dateMin, String nomConge,
                                                 BeanConge beanInfoConge, String trxName) {
        return HRCongeRepository.getDateDernierConge(idCBPartner, dateMax, dateMin,
            nomConge, beanInfoConge, trxName);
    }

    // =========================================================================
    // DÉLÉGATION → HREmployeRepository
    // =========================================================================

    /** @deprecated Utiliser {@link HREmployeRepository#getCurrentJobId} */
    @Deprecated
    public static Integer getCurrentJobId(Integer bpartnerId) {
        return HREmployeRepository.getCurrentJobId(bpartnerId);
    }

    /** @deprecated Utiliser {@link HREmployeRepository#getPostesResponsables} */
    @Deprecated
    public static List<Integer> getPostesResponsables(Integer posteId) {
        return HREmployeRepository.getPostesResponsables(posteId);
    }

    /** @deprecated Utiliser {@link HREmployeRepository#getPostesResponsablesParCategorie} */
    @Deprecated
    public static List<Integer> getPostesResponsablesbyCategorie(Integer posteId,
                                                                  Integer categorieId) {
        return HREmployeRepository.getPostesResponsablesParCategorie(posteId, categorieId);
    }

    /** @deprecated Utiliser {@link HREmployeRepository#getEmployeesByJob} */
    @Deprecated
    public static List<Integer> getEmployeesByJob(Integer posteId) {
        return HREmployeRepository.getEmployeesByJob(posteId);
    }

    /** @deprecated Utiliser {@link HREmployeRepository#getEmployeesByRoles} */
    @Deprecated
    public static List<Integer> getEmployeesByRoles(List<String> roles, String trxName) {
        return HREmployeRepository.getEmployeesByRoles(roles, trxName);
    }

    /** @deprecated Utiliser {@link HREmployeRepository#getDatesDerniersContrats} */
    @Deprecated
    public static ArrayList<MHRElementBasePaieEmploye> getDatesDerniersContrats(
            Integer idCBPartner, Timestamp dateMax, String trxName) {
        return new ArrayList<>(
            HREmployeRepository.getDatesDerniersContrats(idCBPartner, dateMax, trxName));
    }

    // =========================================================================
    // DÉLÉGATION → HRParametreService
    // =========================================================================

    /** @deprecated Utiliser {@link HRParametreService#getParametreNumerique} */
    @Deprecated
    public static int getParametreFromParametreNumerique(String nomParametre) {
        return HRParametreService.getParametreNumerique(nomParametre);
    }

    // =========================================================================
    // MÉTHODES PAIE — conservées temporairement
    // Seront migrées vers PayrollRepository lors du module Paie
    // =========================================================================

    public static MHRHistoriquePaie getHistoriquePaie(Integer cbpartnerid,
            Integer hrElementBasePaieID, Integer periodeSalarialeID, String trxName) {
        MHRHistoriquePaie resultat = null;
        if (cbpartnerid == null || hrElementBasePaieID == null || periodeSalarialeID == null)
            return null;
        String sql = "SELECT * FROM " + I_HR_Historique_Paie.Table_Name
            + " WHERE " + I_HR_Historique_Paie.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND " + I_HR_Historique_Paie.COLUMNNAME_HR_Element_Base_Paie_ID + " = ?"
            + " AND " + I_HR_Historique_Paie.COLUMNNAME_HR_Periode_Salariale_ID + " = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, cbpartnerid);
            pstmt.setInt(2, hrElementBasePaieID);
            pstmt.setInt(3, periodeSalarialeID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                resultat = new MHRHistoriquePaie(Env.getCtx(), rs, trxName);
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    public static void resetCalculPaie(Integer cbpartnerid, String trxName) {
        if (cbpartnerid == null) return;
        String sql = "SELECT * FROM " + I_HR_Calcul_Paie.Table_Name
            + " WHERE " + I_HR_Calcul_Paie.COLUMNNAME_C_BPartner_ID + " = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, cbpartnerid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                new MHRCalculPaie(Env.getCtx(), rs, trxName).delete(true);
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
    }

    public static void resetCalculConge(Integer cbpartnerid, String trxName) {
        if (cbpartnerid == null) return;
        String sql = "SELECT * FROM " + I_HR_Calcul_Conge.Table_Name
            + " WHERE " + I_HR_Calcul_Conge.COLUMNNAME_C_BPartner_ID + " = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, cbpartnerid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                new MHRCalculConge(Env.getCtx(), rs, trxName).delete(true);
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
    }

    public static void resetIndemniteConge(Integer cbpartnerid, String trxName) {
        if (cbpartnerid == null) return;
        String sql = "SELECT * FROM " + I_HR_Calcul_Indemnite_Conge.Table_Name
            + " WHERE " + I_HR_Calcul_Indemnite_Conge.COLUMNNAME_C_BPartner_ID + " = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, cbpartnerid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                new MHRCalculIndemniteConge(Env.getCtx(), rs, trxName).delete(true);
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
    }

    public static ArrayList<MHRGestionPaieEmploye> getAllGestionPaieEmploye(String trxName) {
        ArrayList<MHRGestionPaieEmploye> resultat = new ArrayList<>();
        String sql = "SELECT gpe.* FROM " + I_HR_GestionPaieEmploye.Table_Name + " gpe"
            + " LEFT JOIN HR_Rang_Calcul rc ON gpe.HR_Rang_Calcul_ID = rc.HR_Rang_Calcul_ID"
            + " ORDER BY rc.Rang ASC";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MHRGestionPaieEmploye(Env.getCtx(), rs, null));
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    public static ArrayList<MHRGestionPaieEmploye> getAllGestionCongeEmploye(String trxName) {
        ArrayList<MHRGestionPaieEmploye> resultat = new ArrayList<>();
        String sql = "SELECT gpe.* FROM " + I_HR_GestionPaieEmploye.Table_Name + " gpe"
            + " LEFT JOIN HR_Rang_Calcul rc ON gpe.HR_Rang_Calcul_ID = rc.HR_Rang_Calcul_ID"
            + " WHERE gpe." + I_HR_GestionPaieEmploye.COLUMNNAME_IsIndemniteConge + " = 'Y'"
            + " ORDER BY rc.Rang ASC";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MHRGestionPaieEmploye(Env.getCtx(), rs, null));
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    public static ArrayList<MHRRetenueSalariale> getAllRetenueEmploye(
            Integer cbpartnerid, MHRPeriodeSalariale periodeSalariale, String trxName) {
        ArrayList<MHRRetenueSalariale> resultat = new ArrayList<>();
        if (cbpartnerid == null || periodeSalariale == null) return resultat;
        String sql = "SELECT * FROM " + I_HR_Retenue_Salariale.Table_Name
            + " WHERE " + I_HR_Retenue_Salariale.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND " + I_HR_Retenue_Salariale.COLUMNNAME_Debut_Prelevement_ID + " <= ?"
            + " AND (" + I_HR_Retenue_Salariale.COLUMNNAME_Fin_Prelevement_ID + " >= ?"
            + "   OR " + I_HR_Retenue_Salariale.COLUMNNAME_Fin_Prelevement_ID + " IS NULL)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, cbpartnerid);
            pstmt.setInt(2, periodeSalariale.getHR_Periode_Salariale_ID());
            pstmt.setInt(3, periodeSalariale.getHR_Periode_Salariale_ID());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MHRRetenueSalariale(Env.getCtx(), rs, trxName));
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    public static ArrayList<MHRElementBasePaieEmploye> getElementBasePaieEmploye(
            Integer bpartnerID, Timestamp dateDebut, Timestamp dateFin, String trxName) {
        ArrayList<MHRElementBasePaieEmploye> resultat = new ArrayList<>();
        if (bpartnerID == null || dateDebut == null || dateFin == null) return resultat;
        String sql = "WITH intervals AS ("
            + " SELECT " + I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_ElementBasePaieEmploye_ID
            + ", " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut
            + ", COALESCE(" + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin
            + ", ?) AS " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin
            + " FROM " + I_HR_ElementBasePaieEmploye.Table_Name
            + " WHERE " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + " = ?)"
            + " SELECT " + I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_ElementBasePaieEmploye_ID
            + ", " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin
            + " FROM intervals"
            + " WHERE (CAST(" + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut
            + " AS timestamp), CAST(" + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin
            + " AS timestamp)) OVERLAPS (?, ?)"
            + " ORDER BY " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + " ASC";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setTimestamp(1, dateFin);
            pstmt.setInt(2, bpartnerID);
            pstmt.setTimestamp(3, dateDebut);
            pstmt.setTimestamp(4, dateFin);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MHRElementBasePaieEmploye e = new MHRElementBasePaieEmploye(
                    Env.getCtx(),
                    rs.getInt(I_HR_ElementBasePaieEmploye.COLUMNNAME_HR_ElementBasePaieEmploye_ID),
                    null);
                if (e != null) {
                    if (e.getDate_Debut() != null && e.getDate_Debut().before(dateDebut))
                        e.setDate_Debut(dateDebut);
                    Timestamp datFinRS = rs.getTimestamp(
                        I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin);
                    if (e.getDate_Fin() == null) {
                        e.setDate_Fin(dateFin.before(datFinRS) ? dateFin : datFinRS);
                    } else if (dateFin.before(e.getDate_Fin())) {
                        e.setDate_Fin(dateFin);
                    }
                    resultat.add(e);
                }
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    public static MHRPeriodeSalariale getPeriodeSalarialeFinRetenue(
            Timestamp dateDebut, Integer nombreMensualite, String trxName) {
        if (dateDebut == null || nombreMensualite == null || nombreMensualite <= 0) return null;
        String sql = "SELECT * FROM " + I_HR_Periode_Salariale.Table_Name
            + " WHERE " + I_HR_Periode_Salariale.COLUMNNAME_Date_Debut_Defaut + " >= ?"
            + " ORDER BY " + I_HR_Periode_Salariale.COLUMNNAME_Date_Debut_Defaut + " ASC";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setTimestamp(1, dateDebut);
            rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
                if (count == nombreMensualite) {
                    return new MHRPeriodeSalariale(Env.getCtx(), rs, trxName);
                }
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }
}
