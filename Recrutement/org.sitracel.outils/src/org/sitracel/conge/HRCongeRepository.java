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
import org.sitracel.conge.model.I_HR_Holiday;
import org.sitracel.conge.model.I_HR_Public_Holiday;

/**
 * Repository transversal - requetes SQL conges et absences.
 *
 * Regroupe toutes les requetes SQL liees aux conges, absences et suspensions
 * utilisees par plusieurs modules (Conges, Discipline, Paie...).
 *
 * Remplace les methodes SQL conge/absence de GeneralSqlController.
 *
 * Depuis Session 9 : correction de 5 methodes qui interpretaient a tort
 * Emission_Conge_ID comme pointant vers HR_Autorisation_Conge, alors
 * qu'il pointe directement vers HR_Type_Conge (confirme via le
 * dictionnaire AD_Column). Meme correction que chargerTypeConge()
 * dans CongeProcessService. Egalement corrige : chevaucheSuspensionNonRejete/
 * isJourSuspensionNonRejete utilisaient HR_TypeSanction.IsSuspension
 * (colonne inexistante) - bonne condition : Incidence_Sanction_ID =
 * 'Periode de Suspension', via le bon chemin de jointure
 * (Emission_Sanction_ID -> HR_Sanction_Autorisation -> HR_TypeSanction),
 * meme motif que getAllPeriodeSuspensionValide() dans ce meme fichier.
 */
public final class HRCongeRepository {

    private static final CLogger log = CLogger.getCLogger(HRCongeRepository.class);

    private HRCongeRepository() {}

    // =========================================================================
    // CHEVAUCHEMENTS - CONGES
    // =========================================================================

    /**
     * Verifie si un employe a un conge non rejete qui chevauche la periode.
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
            + " AND HR_CongeStatut_ID <> 1000004"
            + " AND ((" + I_HR_Holiday.COLUMNNAME_Date_Debut_Souhaitee + " <= ?"
            + "   AND " + I_HR_Holiday.COLUMNNAME_Date_Fin_Souhaitee + " >= ?)"
            + " OR (" + I_HR_Holiday.COLUMNNAME_Date_Debut_Ajustee + " <= ?"
            + "   AND " + I_HR_Holiday.COLUMNNAME_Date_Fin_Ajustee + " >= ?))";

        return DB.getSQLValue(trxName, sql,
            bpartnerId, dateFin, dateDebut, dateFin, dateDebut) == 1;
    }

    /**
     * Verifie si un employe a un conge non rejete a une date precise.
     */
    public static boolean isJourAnyCongeNonRejete(Integer bpartnerId,
                                                   Timestamp date,
                                                   String trxName) {
        if (bpartnerId == null || date == null) {
            return false;
        }

        String sql = "SELECT 1 FROM " + I_HR_Holiday.Table_Name
            + " WHERE " + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND HR_CongeStatut_ID <> 1000004"
            + " AND " + I_HR_Holiday.COLUMNNAME_Date_Debut_Souhaitee + " <= ?"
            + " AND " + I_HR_Holiday.COLUMNNAME_Date_Fin_Souhaitee + " >= ?";

        return DB.getSQLValue(trxName, sql, bpartnerId, date, date) == 1;
    }

    /**
     * Verifie si un employe a un conge non rejete (par nom de type) a une date.
     *
     * CORRIGE (Session 9) : jointure directe sur HR_Type_Conge via
     * Emission_Conge_ID (pas via HR_Autorisation_Conge, qui n'a jamais
     * ete le bon chemin).
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
            + " JOIN HR_Type_Conge tc ON tc.HR_Type_Conge_ID = h." + I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
            + " WHERE h." + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND h.HR_CongeStatut_ID <> 1000004"
            + " AND tc.Nom_Conge = ?"
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Debut_Souhaitee + " <= ?"
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Fin_Souhaitee + " >= ?";

        return DB.getSQLValue(trxName, sql, bpartnerId, nomConge, date, date) == 1;
    }

    /**
     * Retourne les periodes de conges valides par nom de type entre deux dates.
     *
     * CORRIGE (Session 9) : meme correction de jointure.
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
            + " JOIN HR_Type_Conge tc ON tc.HR_Type_Conge_ID = h." + I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
            + " WHERE h." + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND h.HR_CongeStatut_ID = 1000003"
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
     * Charge la date du dernier conge valide dans un BeanConge.
     *
     * CORRIGE (Session 9) : meme correction de jointure.
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
            + " JOIN HR_Type_Conge tc ON tc.HR_Type_Conge_ID = h." + I_HR_Holiday.COLUMNNAME_Emission_Conge_ID
            + " WHERE h." + I_HR_Holiday.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND h.HR_CongeStatut_ID = 1000003"
            + " AND tc.Nom_Conge = ?"
            + " ORDER BY h." + I_HR_Holiday.COLUMNNAME_Date_Fin_Effective + " DESC"
            ;

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
    // CHEVAUCHEMENTS - SUSPENSIONS
    // =========================================================================

    /**
     * Verifie si un employe a une suspension non rejetee qui chevauche la periode.
     *
     * CORRIGE (Session 9) : jointure via Emission_Sanction_ID ->
     * HR_Sanction_Autorisation -> HR_TypeSanction (HR_Punishment n'a pas
     * de colonne directe vers HR_TypeSanction), et condition
     * Incidence_Sanction_ID = 'Periode de Suspension' au lieu de la
     * colonne IsSuspension qui n'existe pas.
     */
    public static boolean chevaucheSuspensionNonRejete(Integer bpartnerId,
                                                        Timestamp dateDebut,
                                                        Timestamp dateFin,
                                                        String trxName) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) {
            return false;
        }

        String sql = "SELECT 1 FROM HR_Punishment p"
            + " JOIN HR_Sanction_Autorisation sa ON sa.HR_Sanction_Autorisation_ID = p.Emission_Sanction_ID"
            + " JOIN HR_TypeSanction ts ON ts.HR_TypeSanction_ID = sa.HR_TypeSanction_ID"
            + " WHERE p.C_BPartner_ID = ?"
            + " AND p.IsRejetee = 'N'"
            + " AND ts.Incidence_Sanction_ID = 'Période de Suspension'"
            + " AND p.Date_Debut_Application <= ?"
            + " AND p.Date_Fin_Application >= ?";

        return DB.getSQLValue(trxName, sql, bpartnerId, dateFin, dateDebut) == 1;
    }

    /**
     * Verifie si un employe a une suspension non rejetee a une date precise.
     *
     * CORRIGE (Session 9) : meme correction que ci-dessus.
     */
    public static boolean isJourSuspensionNonRejete(Integer bpartnerId,
                                                     Timestamp date,
                                                     String trxName) {
        if (bpartnerId == null || date == null) {
            return false;
        }

        String sql = "SELECT 1 FROM HR_Punishment p"
            + " JOIN HR_Sanction_Autorisation sa ON sa.HR_Sanction_Autorisation_ID = p.Emission_Sanction_ID"
            + " JOIN HR_TypeSanction ts ON ts.HR_TypeSanction_ID = sa.HR_TypeSanction_ID"
            + " WHERE p.C_BPartner_ID = ?"
            + " AND p.IsRejetee = 'N'"
            + " AND ts.Incidence_Sanction_ID = 'Période de Suspension'"
            + " AND p.Date_Debut_Application <= ?"
            + " AND p.Date_Fin_Application >= ?";

        return DB.getSQLValue(trxName, sql, bpartnerId, date, date) == 1;
    }

    // =========================================================================
    // ABSENCES
    // =========================================================================

    /**
     * Verifie si un employe a une absence enregistree sur la periode.
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
     * Verifie si un employe a une absence enregistree a une date precise.
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
    // JOURS FERIES
    // =========================================================================

    /**
     * Retourne l'ensemble des jours feries entre deux dates.
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
    // GESTION DES ABSENCES
    // =========================================================================

    /**
     * Verifie si une absence existe deja pour un employe a une date donnee.
     */
    /**
     * Retourne les dates pour lesquelles une absence existe deja
     * dans la periode [dateDebut, dateFin[ pour un employe donne.
     * Utilise par la validation de conge et de suspension pour
     * verifier la disponibilite avant de creer les absences.
     */
    public static java.util.List<Timestamp> getAbsencesExistantesDansPeriode(
            int bpartnerId, Timestamp dateDebut, Timestamp dateFin,
            String trxName) {
        java.util.List<Timestamp> dates = new java.util.ArrayList<Timestamp>();
        if (dateDebut == null || dateFin == null || bpartnerId <= 0) {
            return dates;
        }
        String sql = "SELECT " + I_HR_Absence.COLUMNNAME_Date_Absence
            + " FROM " + I_HR_Absence.Table_Name
            + " WHERE " + I_HR_Absence.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND " + I_HR_Absence.COLUMNNAME_Date_Absence + " >= ?"
            + " AND " + I_HR_Absence.COLUMNNAME_Date_Absence + " < ?"
            + " AND IsActive = 'Y'"
            + " ORDER BY " + I_HR_Absence.COLUMNNAME_Date_Absence;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateDebut);
            pstmt.setTimestamp(3, dateFin);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                dates.add(rs.getTimestamp(1));
            }
        } catch (SQLException e) {
            log.warning("getAbsencesExistantesDansPeriode : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return dates;
    }

    public static boolean isAbsenceExist(Timestamp dateAbsence,
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
        } catch (SQLException e) {
            log.warning("isAbsenceExist : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return false;
    }

    /**
     * Supprime toutes les absences de conge d'un employe sur une periode.
     */
    public static void annulerAbsenceConge(Integer bpartnerId,
                                            Timestamp dateDebut,
                                            Timestamp dateFin,
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
        } catch (SQLException e) {
            log.warning("annulerAbsenceConge : " + e.getMessage());
        } finally {
            DB.close(null, pstmt);
        }
    }

    /**
     * Retourne toutes les periodes de suspension valides (non rejetees)
     * d'un employe chevauchant la periode donnee.
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
    // COMPTAGE ABSENCES NON TRAITEES
    // =========================================================================

    /**
     * Retourne le nombre d'absences non traitees d'un employe sur une periode.
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
            + " AND "   + I_HR_Absence.COLUMNNAME_IsConge + " = 'N'"
	        + " AND "   + I_HR_Absence.COLUMNNAME_Date_Absence + " > COALESCE("
	        + "   (SELECT MAX(de.Date_Emission) FROM HR_Demande_Explication de"
	        + "    WHERE de.C_BPartner_ID = " + I_HR_Absence.Table_Name + "." + I_HR_Absence.COLUMNNAME_C_BPartner_ID
	        + "    AND de.IsActive = 'Y'), TIMESTAMP '1900-01-01')"	        
            + " AND ("  + I_HR_Absence.COLUMNNAME_HR_Type_Absence_ID + " IS NULL"
            + "  OR "   + I_HR_Absence.COLUMNNAME_HR_Type_Absence_ID
            + " = (SELECT HR_Type_Absence_ID FROM HR_Type_Absence WHERE Nom_Absence = 'Absence Injustifiée' AND IsActive = 'Y'))";

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
