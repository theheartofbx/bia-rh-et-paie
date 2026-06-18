package org.sitracel.notification.process.notifier;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRDelaiReponse;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;
import org.sitracel.mission.model.MHRMission;
import org.sitracel.mission.model.MHRMissionAffectation;
import org.sitracel.model.MCBPartner;
import org.sitracel.notification.model.MHRNotification;

/**
 * Construit la map de variables pour le rendu d'un template de notification.
 *
 * Variables communes disponibles dans tous les templates :
 *   {{employe}}   — Nom complet de l'employé concerné
 *   {{emetteur}}  — Nom complet de l'émetteur
 *   {{date}}      — Date d'émission du document
 *
 * Variables spécifiques par type de document :
 *   Congé         : {{debut}}, {{fin}}, {{type_conge}}
 *   Sanction      : {{type_sanction}}
 *   Demande expl. : {{motif}}, {{delai}}
 *   Mission       : {{titre_mission}}, {{debut}}, {{fin}}
 */
public final class HRNotificationVariableBuilder {

    private static final SimpleDateFormat FMT =
        new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);

    private HRNotificationVariableBuilder() {}

    public static Map<String, String> build(
            MHRNotification notif,
            String trxName
    ) {
        Map<String, String> vars = new HashMap<>();

        if (notif == null) return vars;

        // Retrouver le PO source depuis la notification
        PO po = MTable.get(Env.getCtx(), notif.getAD_Table_ID())
                      .getPO(notif.getNumero_Enregistrement(), trxName);

        if (po == null) return vars;

        // Variables selon le type de document
        if (po instanceof MHRHoliday) {
            buildHoliday(vars, (MHRHoliday) po);
        } else if (po instanceof MHRPunishment) {
            buildSanction(vars, (MHRPunishment) po);
        } else if (po instanceof MHRDemandeExplication) {
            buildDemandeExplication(vars, (MHRDemandeExplication) po);
        } else if (po instanceof MHRMission) {
            buildMission(vars, (MHRMission) po);
        } else if (po instanceof MHRMissionAffectation) {
            buildMissionAffectation(vars, (MHRMissionAffectation) po);
        }

        return vars;
    }

    // ── CONGÉ ─────────────────────────────────────────────────────────────────

    private static void buildHoliday(Map<String, String> v, MHRHoliday h) {
        v.put("employe",    nomBPartner(h.getC_BPartner_ID()));
        v.put("emetteur",   nomBPartner(h.getEmis_Par_Nom_ID()));
        v.put("date",       fmt(h.getDate_Emission()));
        v.put("debut",      fmt(h.getDate_Debut_Souhaitee()));
        v.put("fin",        fmt(h.getDate_Fin_Souhaitee()));
        v.put("type_conge", typeConge(h.getEmission_Conge_ID()));
    }

    // ── SANCTION ──────────────────────────────────────────────────────────────

    private static void buildSanction(Map<String, String> v, MHRPunishment p) {
        v.put("employe",       nomBPartner(p.getC_BPartner_ID()));
        v.put("emetteur",      nomBPartner(p.getEmis_Par_Nom_ID()));
        v.put("date",          fmt(p.getDate_Emission()));
        v.put("type_sanction", typeSanction(p.getEmission_Sanction_ID()));
    }

    // ── DEMANDE D'EXPLICATION ─────────────────────────────────────────────────

    private static void buildDemandeExplication(
            Map<String, String> v, MHRDemandeExplication d) {
        v.put("employe",  nomBPartner(d.getC_BPartner_ID()));
        v.put("emetteur", nomBPartner(d.getEmis_Par_Nom_ID()));
        v.put("date",     fmt(d.getDate_Emission()));
        v.put("motif",    safe(d.getMotif_Demande_Explication()));
        v.put("delai",    delaiReponse(d.getHR_Delai_Reponse_ID()));
    }

    // ── MISSION ───────────────────────────────────────────────────────────────

    private static void buildMission(Map<String, String> v, MHRMission m) {
        v.put("employe",        nomBPartner(m.getEmis_Par_Nom_ID()));
        v.put("emetteur",       nomBPartner(m.getEmis_Par_Nom_ID()));
        v.put("date",           fmt(m.getDate_Debut()));
        v.put("titre_mission",  safe(m.getName()));
        v.put("debut",          fmt(m.getDate_Debut()));
        v.put("fin",            fmt(m.getDate_Fin()));
    }

    private static void buildMissionAffectation(
            Map<String, String> v, MHRMissionAffectation ma) {
        v.put("employe",  nomBPartner(ma.getEmployee_ID()));
        v.put("emetteur", nomBPartner(ma.getAffecte_Par_Nom_ID()));
        v.put("date",     fmt(ma.getCreated()));
    }

    // ── UTILITAIRES ───────────────────────────────────────────────────────────

    private static String nomBPartner(int bpartnerId) {
        if (bpartnerId <= 0) return "";
        MCBPartner bp = new MCBPartner(Env.getCtx(), bpartnerId, null);
        String nom = safe(bp.getName());
        if (bp.getName2() != null && !bp.getName2().isBlank()) {
            nom = nom + " " + bp.getName2().trim();
        }
        return nom.trim();
    }

    private static String typeConge(int emissionCongeId) {
        if (emissionCongeId <= 0) return "";
        MHRTypeConge tc = new MHRTypeConge(Env.getCtx(), emissionCongeId, null);
        return safe(tc.getNom_Conge());
    }

    private static String typeSanction(int emissionSanctionId) {
        if (emissionSanctionId <= 0) return "";
        MHRSanctionAutorisation sa = new MHRSanctionAutorisation(
            Env.getCtx(), emissionSanctionId, null);
        if (sa.getHR_TypeSanction_ID() <= 0) return "";
        MHRTypeSanction ts = new MHRTypeSanction(
            Env.getCtx(), sa.getHR_TypeSanction_ID(), null);
        return safe(ts.getNom_Sanction());
    }

    private static String delaiReponse(int delaiId) {
        if (delaiId <= 0) return "";
        MHRDelaiReponse dr = new MHRDelaiReponse(Env.getCtx(), delaiId, null);
        return safe(dr.getName());
    }

    private static String fmt(java.sql.Timestamp ts) {
        return ts != null ? FMT.format(ts) : "";
    }

    private static String safe(String s) {
        return s != null ? s.trim() : "";
    }
}
