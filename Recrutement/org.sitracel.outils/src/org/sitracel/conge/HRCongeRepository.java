package org.sitracel.conge;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.bean.BeanConge;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.absence.model.I_HR_Absence;
import org.sitracel.conge.model.I_HR_Autorisation_Conge;
import org.sitracel.conge.model.I_HR_Holiday;
import org.sitracel.conge.model.I_HR_Public_Holiday;

/**
 * Repository transversal — requêtes SQL congés et absences.
 *
 * Regroupe toutes les requêtes SQL liées aux congés, absences et suspensions
 * utilisées par plusieurs modules (Congés, Discipline, Paie...).
 *
 * Remplace les méthodes SQL congé/absence de GeneralSqlController.
 */
public final class HRCongeRepository {

    private static final CLogger log = CLogger.getCLogger(HRCongeRepository.class);

    private HRCongeRepository() {}

    // =========================================================================
    // CHEVAUCHEMENTS — CONGÉS
    // =========================================================================

    /**
     * Vérifie si un employé a un congé non rejeté qui chevauche la période.
     */
    public static boolean chevaucheAnyCongeNonRejete(Integer bpartnerId,
                                                      Timestamp dateDebut,
                                                      Timestamp dateFin,
                                                      String trxName) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) {
			return false;
		}

        String sql = "SELECT 1 FROM " + I_HR_Holiday.Table_Name
            + " WHERE " + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND " + I_HR_Holiday.COLUMNNAME_IsRejetee + " = 'N'"
            + " AND ((" + I_HR_Holiday.COLUMNNAME_Date_Debut_Souhaitee + " <= ?"
            + "   AND " + I_HR_Holiday.COLUMNNAME_Date_Fin_Souhaitee + " >= ?)"
            + " OR (" + I_HR_Holiday.COLUMNNAME_Date_Debut_Ajustee + " <= ?"
            + "   AND " + I_HR_Holiday.COLUMNNAME_Date_Fin_Ajustee + " >= ?))";

        return DB.getSQLValue(trxName, sql,
            bpartnerId, dateFin, dateDebut, dateFin, dateDebut) == 1;
    }

    /**
     * Vérifie si un employé a un congé non rejeté à une date précise.
     */
    public static boolean isJourAnyCongeNonRejete(Integer bpartnerId,
                                                   Timestamp date,
                                                   String trxName) {
        if (bpartnerId == null || date == null) {
			return false;
		}

        String sql = "SELECT 1 FROM " + I_HR_Holiday.Table_Name
            + " WHERE " + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND " + I_HR_Holiday.COLUMNNAME_IsRejetee + " = 'N'"
            + " AND " + I_HR_Holiday.COLUMNNAME_Date_Debut_Souhaitee + " <= ?"
            + " AND " + I_HR_Holiday.COLUMNNAME_Date_Fin_Souhaitee + " >= ?";

        return DB.getSQLValue(trxName, sql, bpartnerId, date, date) == 1;
    }

    /**
     * Vérifie si un employé a un congé validé (par nom de type) à une date.
     */
    public static boolean isJourCongesNonRejeteByNameConge(Integer bpartnerId,
                                                            String nomConge,
                                                            Timestamp date,
                                                            String trxName) {
        if (bpartnerId == null || nomConge == null || date == null) {
			return false;
		}

        String sql = "SELECT 1"
            + " FROM " + I_HR_Holiday.Table_Name + " h"
            + " JOIN " + I_HR_Autorisation_Conge.Table_Name + " ac"
            + "   ON ac." + I_HR_Autorisation_Conge.COLUMNNAME_HR_Autorisation_Conge_ID
            + "    = h." + I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
            + " JOIN HR_Type_Conge tc ON tc.HR_Type_Conge_ID = ac.HR_Type_Conge_ID"
            + " WHERE h." + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND h." + I_HR_Holiday.COLUMNNAME_IsRejetee + " = 'N'"
            + " AND tc.Nom_Conge = ?"
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Debut_Souhaitee + " <= ?"
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Fin_Souhaitee + " >= ?";

        return DB.getSQLValue(trxName, sql, bpartnerId, nomConge, date, date) == 1;
    }

    /**
     * Retourne les périodes de congés validés par nom de type entre deux dates.
     */
    public static BeanPeriode[] getCongesValideByNameConge(Integer bpartnerId,
                                                            String nomConge,
                                                            Timestamp dateDebut,
                                                            Timestamp dateFin,
                                                            String trxName) {
        if (bpartnerId == null || nomConge == null) {
			return null;
		}

        String sql = "SELECT h." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective
            + ", h." + I_HR_Holiday.COLUMNNAME_Date_Fin_Effective
            + " FROM " + I_HR_Holiday.Table_Name + " h"
            + " JOIN " + I_HR_Autorisation_Conge.Table_Name + " ac"
            + "   ON ac." + I_HR_Autorisation_Conge.COLUMNNAME_HR_Autorisation_Conge_ID
            + "    = h." + I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
            + " JOIN HR_Type_Conge tc ON tc.HR_Type_Conge_ID = ac.HR_Type_Conge_ID"
            + " WHERE h." + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND h." + I_HR_Holiday.COLUMNNAME_IsValidee + " = 'Y'"
            + " AND tc.Nom_Conge = ?"
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective + " >= ?"
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Fin_Effective + " <= ?";

        java.util.List<BeanPeriode> list = new java.util.ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setString(2, nomConge);
            pstmt.setTimestamp(3, dateDebut);
            pstmt.setTimestamp(4, dateFin);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BeanPeriode bp = BeanFactory.getBeanPeriode();
                bp.setDateDebutConge(rs.getTimestamp(1));
                bp.setDateFinConge(rs.getTimestamp(2));
                list.add(bp);
            }
        } catch (SQLException e) {
            log.warning("getCongesValideByNameConge : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return list.toArray(new BeanPeriode[0]);
    }

    /**
     * Charge la date du dernier congé validé dans un BeanConge.
     */
    public static BeanConge getDateDernierConge(Integer bpartnerId,
                                                 Timestamp dateActuelle,
                                                 Timestamp dateDebutContrat,
                                                 String nomConge,
                                                 BeanConge beanConge,
                                                 String trxName) {
        if (bpartnerId == null || beanConge == null) {
			return beanConge;
		}

        String sql = "SELECT h." + I_HR_Holiday.COLUMNNAME_Date_Fin_Effective
            + " FROM " + I_HR_Holiday.Table_Name + " h"
            + " JOIN " + I_HR_Autorisation_Conge.Table_Name + " ac"
            + "   ON ac." + I_HR_Autorisation_Conge.COLUMNNAME_HR_Autorisation_Conge_ID
            + "    = h." + I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
            + " JOIN HR_Type_Conge tc ON tc.HR_Type_Conge_ID = ac.HR_Type_Conge_ID"
            + " WHERE h." + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND h." + I_HR_Holiday.COLUMNNAME_IsValidee + " = 'Y'"
            + " AND tc.Nom_Conge = ?"
            + " ORDER BY h." + I_HR_Holiday.COLUMNNAME_Date_Fin_Effective + " DESC"
            + " LIMIT 1";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setString(2, nomConge);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                beanConge.setDateFinDernierConge(rs.getTimestamp(1));
            }
        } catch (SQLException e) {
            log.warning("getDateDernierConge : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return beanConge;
    }

    // =========================================================================
    // CHEVAUCHEMENTS — SUSPENSIONS
    // =========================================================================

    /**
     * Vérifie si un employé a une suspension non rejetée qui chevauche la période.
     */
    public static boolean chevaucheSuspensionNonRejete(Integer bpartnerId,
                                                        Timestamp dateDebut,
                                                        Timestamp dateFin,
                                                        String trxName) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) {
			return false;
		}

        String sql = "SELECT 1 FROM HR_Punishment p"
            + " JOIN HR_TypeSanction ts ON ts.HR_TypeSanction_ID = p.HR_TypeSanction_ID"
            + " WHERE p.C_BPartner_ID = ?"
            + " AND p.IsRejetee = 'N'"
            + " AND ts.IsSuspension = 'Y'"
            + " AND p.Date_Debut_Application <= ?"
            + " AND p.Date_Fin_Application >= ?";

        return DB.getSQLValue(trxName, sql, bpartnerId, dateFin, dateDebut) == 1;
    }

    /**
     * Vérifie si un employé a une suspension non rejetée à une date précise.
     */
    public static boolean isJourSuspensionNonRejete(Integer bpartnerId,
                                                     Timestamp date,
                                                     String trxName) {
        if (bpartnerId == null || date == null) {
			return false;
		}

        String sql = "SELECT 1 FROM HR_Punishment p"
            + " JOIN HR_TypeSanction ts ON ts.HR_TypeSanction_ID = p.HR_TypeSanction_ID"
            + " WHERE p.C_BPartner_ID = ?"
            + " AND p.IsRejetee = 'N'"
            + " AND ts.IsSuspension = 'Y'"
            + " AND p.Date_Debut_Application <= ?"
            + " AND p.Date_Fin_Application >= ?";

        return DB.getSQLValue(trxName, sql, bpartnerId, date, date) == 1;
    }

    // =========================================================================
    // ABSENCES
    // =========================================================================

    /**
     * Vérifie si un employé a une absence enregistrée sur la période.
     */
    public static boolean isPeriodeAbsence(Integer bpartnerId,
                                            Timestamp dateDebut,
                                            Timestamp dateFin,
                                            String trxName) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) {
			return false;
		}

        String sql = "SELECT 1 FROM " + I_HR_Absence.Table_Name
            + " WHERE " + I_HR_Absence.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND " + I_HR_Absence.COLUMNNAME_Date_Absence + " BETWEEN ? AND ?";

        return DB.getSQLValue(trxName, sql, bpartnerId, dateDebut, dateFin) == 1;
    }

    /**
     * Vérifie si un employé a une absence enregistrée à une date précise.
     */
    public static boolean isJourAbsence(Integer bpartnerId,
                                         Timestamp date,
                                         String trxName) {
        if (bpartnerId == null || date == null) {
			return false;
		}

        String sql = "SELECT 1 FROM " + I_HR_Absence.Table_Name
            + " WHERE " + I_HR_Absence.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND " + I_HR_Absence.COLUMNNAME_Date_Absence + " = ?"
            + " AND EXTRACT(DOW FROM " + I_HR_Absence.COLUMNNAME_Date_Absence + ") != 0";

        return DB.getSQLValue(trxName, sql, bpartnerId, date) == 1;
    }

    // =========================================================================
    // JOURS FÉRIÉS
    // =========================================================================

    /**
     * Retourne l'ensemble des jours fériés entre deux dates.
     */
    public static Set<LocalDate> getAllJoursFeries(Timestamp dateDebut,
                                                    Timestamp dateFin,
                                                    String trxName) {
        Set<LocalDate> joursFeries = new HashSet<>();
        if (dateDebut == null || dateFin == null) {
			return joursFeries;
		}

        String sql = "SELECT " + I_HR_Public_Holiday.COLUMNNAME_Date_Jour_Ferie
            + " FROM " + I_HR_Public_Holiday.Table_Name
            + " WHERE " + I_HR_Public_Holiday.COLUMNNAME_Date_Jour_Ferie + " BETWEEN ? AND ?"
            + " AND IsActive = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setTimestamp(1, dateDebut);
            pstmt.setTimestamp(2, dateFin);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Timestamp ts = rs.getTimestamp(1);
                if (ts != null) {
                    joursFeries.add(
                        ts.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                }
            }
        } catch (SQLException e) {
            log.warning("getAllJoursFeries : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return joursFeries;
    }

    // =========================================================================
    // GESTION DES ABSENCES — DÉPLACÉ DEPUIS AbsenceCalloutRepository
    // =========================================================================

    /**
     * Vérifie si une absence existe déjà pour un employé à une date donnée.
     */
    public static boolean isAbsenceExist(java.sql.Timestamp dateAbsence,
                                          Integer bpartnerId,
                                          String trxName) {
        if (dateAbsence == null || bpartnerId == null) {
			return false;
		}

        String sql = "SELECT 1 FROM " + I_HR_Absence.Table_Name
            + " WHERE " + I_HR_Absence.COLUMNNAME_Date_Absence + " = ?"
            + " AND "   + I_HR_Absence.COLUMNNAME_C_BPartner_ID + " = ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setTimestamp(1, dateAbsence);
            pstmt.setInt(2, bpartnerId);
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (java.sql.SQLException e) {
            log.warning("isAbsenceExist : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return false;
    }

    /**
     * Supprime toutes les absences de suspension d'un employé sur une période.
     */
    public static void annulerAbsenceConge(Integer bpartnerId,
                                            java.sql.Timestamp dateDebut,
                                            java.sql.Timestamp dateFin,
                                            String trxName) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) {
			return;
		}

        String sql = "DELETE FROM " + I_HR_Absence.Table_Name
            + " WHERE " + I_HR_Absence.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND "   + I_HR_Absence.COLUMNNAME_Date_Absence + " BETWEEN ? AND ?"
            + " AND "   + I_HR_Absence.COLUMNNAME_IsConge + " = 'N'"
            + " AND "   + I_HR_Absence.COLUMNNAME_IsCongeTraite + " = 'Y'"
            + " AND "   + I_HR_Absence.COLUMNNAME_IsDemandeExplicationTraite + " = 'Y'";

        PreparedStatement pstmt = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateDebut);
            pstmt.setTimestamp(3, dateFin);
            pstmt.executeUpdate();
        } catch (java.sql.SQLException e) {
            log.warning("annulerAbsenceConge : " + e.getMessage());
        } finally {
            DB.close(null, pstmt);
        }
    }

    /**
     * Retourne toutes les périodes de suspension valides (non rejetées)
     * d'un employé chevauchant la période donnée.
     */
    public static BeanPeriode[] getAllPeriodeSuspensionValide(Integer bpartnerId,
                                                                Timestamp dateDebut,
                                                                Timestamp dateFin,
                                                                String trxName) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) {
            return new BeanPeriode[0];
        }

        String sql = "SELECT p.Date_Debut_Application, p.Date_Fin_Application"
            + " FROM HR_Punishment p"
            + " JOIN HR_Sanction_Autorisation sa ON sa.HR_Sanction_Autorisation_ID = p.Emission_Sanction_ID"
            + " JOIN HR_TypeSanction ts ON ts.HR_TypeSanction_ID = sa.HR_TypeSanction_ID"
            + " WHERE p.C_BPartner_ID = ?"
            + " AND p.IsRejetee = 'N'"
            + " AND ts.Incidence_Sanction_ID = 'Période de Suspension'"
            + " AND p.Date_Debut_Application <= ?"
            + " AND p.Date_Fin_Application >= ?";

        java.util.List<BeanPeriode> list = new java.util.ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateFin);
            pstmt.setTimestamp(3, dateDebut);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BeanPeriode bp = BeanFactory.getBeanPeriode();
                bp.setDateDebutConge(rs.getTimestamp(1));
                bp.setDateFinConge(rs.getTimestamp(2));
                list.add(bp);
            }
        } catch (SQLException e) {
            log.warning("getAllPeriodeSuspensionValide : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return list.toArray(new BeanPeriode[0]);
    }

    // =========================================================================
    // COMPTAGE ABSENCES NON TRAITÉES
    // =========================================================================

    /**
     * Retourne le nombre d'absences non traitées d'un employé sur une période.
     * Utilisé par CongeAbsenceValidatorService pour décider si une demande
     * d'explication doit être créée.
     */
    public static int getNombreAbsencesNonTraitees(int bpartnerId,
                                                    Timestamp dateDebut,
                                                    Timestamp dateFin,
                                                    String trxName) {
        if (bpartnerId <= 0 || dateDebut == null || dateFin == null) {
			return 0;
		}

        String sql = "SELECT COUNT(*) FROM " + I_HR_Absence.Table_Name
            + " WHERE " + I_HR_Absence.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND "   + I_HR_Absence.COLUMNNAME_Date_Absence + " BETWEEN ? AND ?"
            + " AND "   + I_HR_Absence.COLUMNNAME_IsDemandeExplication + " = 'N'"
            + " AND "   + I_HR_Absence.COLUMNNAME_IsDemandeExplicationTraite + " = 'N'"
            + " AND "   + I_HR_Absence.COLUMNNAME_IsConge + " = 'N'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateDebut);
            pstmt.setTimestamp(3, dateFin);
            rs = pstmt.executeQuery();
            if (rs.next()) {
				return rs.getInt(1);
			}
        } catch (SQLException erreurCatch) {
            log.warning("getNombreAbsencesNonTraitees : " + erreurCatch.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return 0;
    }

}