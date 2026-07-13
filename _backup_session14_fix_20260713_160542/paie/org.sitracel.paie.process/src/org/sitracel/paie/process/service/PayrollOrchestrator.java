package org.sitracel.paie.process.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.model.MCBPartner;
import org.sitracel.paie.model.MHRGestionPaieEmploye;
import org.sitracel.paie.model.MHRPeriodeSalariale;
import org.sitracel.paie.process.service.calcul.CongeCalculEngine;
import org.sitracel.paie.process.service.calcul.IndemniteEngine;
import org.sitracel.paie.process.service.calcul.PayrollCalculEngine;
import org.sitracel.paie.process.service.persistence.PayrollPersistence;

/**
 * Orchestrateur du module Paie.
 *
 * Point d'entrée unique pour tous les calculs de paie.
 * Délègue à des moteurs spécialisés selon le contexte.
 *
 * CALCUL MENSUEL :
 *   calculerPaie(bpartnerId, periodeId)
 *   → PayrollCalculEngine  (éléments de paie, présence, retenues, NP)
 *
 * CALCUL POUR TOUS LES EMPLOYÉS :
 *   calculerPaieTousEmployes(periodeId)
 *   → boucle sur tous les employés actifs → calculerPaie()
 *
 * INDEMNITÉ CONGÉ :
 *   calculerIndemniteConge(bpartnerId, holidayId)
 *   → CongeCalculEngine (IBC, IRPP congé, NP congé)
 *
 * INDEMNITÉ LICENCIEMENT :
 *   calculerIndemniteLicenciement(bpartnerId)
 *   → IndemniteEngine (barème légal camerounais)
 *   → Crée une HR_MouvementPaie (versement unique)
 *
 * INDEMNITÉ RETRAITE :
 *   calculerIndemniteRetraite(bpartnerId)
 *   → IndemniteEngine (2× indemnité licenciement, paramétrable)
 *   → Crée une HR_MouvementPaie (versement unique)
 */
public class PayrollOrchestrator {

    private static final CLogger log = CLogger.getCLogger(PayrollOrchestrator.class);

    // -------------------------------------------------------------------------
    // Calcul mensuel — un employé
    // -------------------------------------------------------------------------

    /**
     * Calcule la paie d'un employé pour une période salariale.
     * Point d'entrée principal depuis SitracelProcessCalculPaie.
     *
     * @param bpartnerId  ID de l'employé
     * @param periodeId   ID de la période salariale
     * @return            message de résultat pour l'interface iDempiere
     */
    public static String calculerPaie(int bpartnerId, int periodeId) {
        log.info("PayrollOrchestrator.calculerPaie — bpartnerId=" + bpartnerId
                + " periodeId=" + periodeId);

        boolean ok = PayrollCalculEngine.calculerPaie(bpartnerId, periodeId, null);

        if (ok) {
            return "Calcul de paie effectué avec succès pour l'employé " + bpartnerId;
        } else {
            return "Calcul de paie ignoré pour l'employé " + bpartnerId
                    + " — vérifier le contrat actif et les logs.";
        }
    }

    // -------------------------------------------------------------------------
    // Calcul mensuel — tous les employés actifs
    // -------------------------------------------------------------------------

    /**
     * Calcule la paie de tous les employés actifs pour une période.
     * Point d'entrée depuis SitracelProcessCalculerPaie (avec paramètres).
     *
     * @param periodeId  ID de la période salariale
     * @return           message de résultat avec compteurs
     */
    public static String calculerPaieTousEmployes(int periodeId) {
        log.info("PayrollOrchestrator.calculerPaieTousEmployes — periodeId=" + periodeId);

        List<MCBPartner> employes = getEmployesActifs(null);
        if (employes.isEmpty()) {
            return "Aucun employé actif trouvé.";
        }

        int succes  = 0;
        int ignores = 0;

        for (MCBPartner employe : employes) {
            boolean ok = PayrollCalculEngine.calculerPaie(
                    employe.getC_BPartner_ID(), periodeId, null);
            if (ok) succes++; else ignores++;
        }

        return "Calcul de paie terminé — "
                + succes + " employé(s) calculé(s), "
                + ignores + " ignoré(s). Voir logs pour détails.";
    }

    // -------------------------------------------------------------------------
    // Indemnité congé
    // -------------------------------------------------------------------------

    /**
     * Calcule les indemnités de congé pour un employé partant en congé.
     * Appelé depuis le ModelValidator congé lors de la validation.
     *
     * @param bpartnerId  ID de l'employé
     * @param holidayId   ID du congé validé
     * @return            message de résultat
     */
    public static String calculerIndemniteConge(int bpartnerId, int holidayId) {
        log.info("PayrollOrchestrator.calculerIndemniteConge — bpartnerId=" + bpartnerId
                + " holidayId=" + holidayId);

        MCBPartner bpartner = new MCBPartner(Env.getCtx(), bpartnerId, null);
        MHRHoliday holiday  = new MHRHoliday(Env.getCtx(), holidayId, null);

        if (bpartner.getC_BPartner_ID() == 0 || holiday.getHR_Holiday_ID() == 0) {
            return "Calcul indemnité congé abandonné : employé ou congé introuvable.";
        }

        boolean ok = CongeCalculEngine.calculerIndemniteConge(bpartner, holiday, null);

        return ok
                ? "Indemnité de congé calculée pour l'employé " + bpartnerId
                : "Calcul indemnité congé ignoré — vérifier salaire_cotisable et logs.";
    }

    // -------------------------------------------------------------------------
    // Indemnité licenciement
    // -------------------------------------------------------------------------

    /**
     * Calcule et enregistre l'indemnité de licenciement.
     * Crée une HR_MouvementPaie de type indemnité (ajoutée au NP).
     * Le versement se fait lors du prochain calcul de paie.
     *
     * @param bpartnerId  ID de l'employé licencié
     * @param periodeId   ID de la période de versement souhaitée
     * @return            message avec le montant calculé
     */
    public static String calculerIndemniteLicenciement(int bpartnerId, int periodeId) {
        log.info("PayrollOrchestrator.calculerIndemniteLicenciement — bpartnerId=" + bpartnerId);

        java.math.BigDecimal montant =
                IndemniteEngine.calculerIndemniteLicenciement(bpartnerId, null);

        if (montant.compareTo(java.math.BigDecimal.ZERO) == 0) {
            return "Indemnité de licenciement nulle — ancienneté insuffisante ou "
                    + "historique SBR absent.";
        }

        enregistrerIndemniteRetenue(bpartnerId, periodeId, montant, true, false, null);

        return "Indemnité de licenciement enregistrée : "
                + montant.toPlainString() + " FCFA — sera versée à la période " + periodeId;
    }

    // -------------------------------------------------------------------------
    // Indemnité retraite
    // -------------------------------------------------------------------------

    /**
     * Calcule et enregistre l'indemnité de retraite.
     * = indemnité licenciement × COEF_RETRAITE (paramétrable en base).
     *
     * @param bpartnerId  ID de l'employé partant à la retraite
     * @param periodeId   ID de la période de versement souhaitée
     * @return            message avec le montant calculé
     */
    public static String calculerIndemniteRetraite(int bpartnerId, int periodeId) {
        log.info("PayrollOrchestrator.calculerIndemniteRetraite — bpartnerId=" + bpartnerId);

        java.math.BigDecimal montant =
                IndemniteEngine.calculerIndemniteRetraite(bpartnerId, null);

        if (montant.compareTo(java.math.BigDecimal.ZERO) == 0) {
            return "Indemnité de retraite nulle — ancienneté insuffisante ou "
                    + "historique SBR absent.";
        }

        enregistrerIndemniteRetenue(bpartnerId, periodeId, montant, false, true, null);

        return "Indemnité de retraite enregistrée : "
                + montant.toPlainString() + " FCFA — sera versée à la période " + periodeId;
    }

    // -------------------------------------------------------------------------
    // Méthodes privées
    // -------------------------------------------------------------------------

    /**
     * Crée un enregistrement HR_MouvementPaie pour une indemnité versée.
     * Type indemnité = ajoutée au NP (pas soustraite).
     * Versement unique : montant_mensualite = montant_total = solde.
     */
    private static void enregistrerIndemniteRetenue(int bpartnerId,
                                                     int periodeId,
                                                     java.math.BigDecimal montant,
                                                     boolean isLicenciement,
                                                     boolean isRetraite,
                                                     String trxName) {
        org.sitracel.paie.model.MHRMouvementPaie retenue =
                new org.sitracel.paie.model.MHRMouvementPaie(Env.getCtx(), 0, trxName);

        retenue.setHR_Mouvement_Paie_ID(
                DB.getNextID(Env.getCtx(),
                        org.sitracel.paie.model.I_HR_Mouvement_Paie.Table_Name,
                        trxName));
        retenue.setC_BPartner_ID(bpartnerId);
        retenue.setMontant_Total(montant);
        retenue.setMontant_Mensualite(montant);       // versement unique
        retenue.setMontant_Derniere_Mensualite(montant);
        retenue.setNombre_Mensualite(1);
        retenue.setSolde(montant);
        retenue.setDebut_Prelevement_ID(periodeId);   // versé à cette période
        retenue.setIsActive(true);
        retenue.setIsIndemniteLicenciement(isLicenciement);
        retenue.setIsIndemniteRetraite(isRetraite);
        retenue.setName(isLicenciement ? "Indemnité de licenciement"
                                       : "Indemnité de retraite");
        retenue.save();

        // Marquer comme indemnité — IsIndemnite est géré directement en SQL
        // car la colonne n'est pas encore enregistrée dans le dictionnaire AD_Column.
        // Quand elle le sera, on pourra utiliser le setter du modèle à la place.
        DB.executeUpdate(
                "UPDATE " + I_HR_Mouvement_Paie.Table_Name
                + " SET IsIndemnite='Y' WHERE "
                + I_HR_Mouvement_Paie.COLUMNNAME_HR_Mouvement_Paie_ID + "="
                + retenue.getHR_Mouvement_Paie_ID(),
                trxName);

        log.info("HR_MouvementPaie créée — bpartnerId=" + bpartnerId
                + " montant=" + montant + " periodeId=" + periodeId);
    }

    /**
     * Retourne tous les employés actifs ayant un contrat en cours.
     */
    private static List<MCBPartner> getEmployesActifs(String trxName) {
        List<MCBPartner> resultat = new ArrayList<>();

        String sql = "SELECT DISTINCT bp.c_bpartner_id"
                + " FROM adempiere.c_bpartner bp"
                + " INNER JOIN adempiere.hr_elementbasepaieemploye epe"
                + " ON epe.c_bpartner_id = bp.c_bpartner_id"
                + " WHERE bp.isactive='Y'"
                + " AND epe.isactive='Y'"
                + " AND bp.ad_client_id=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, Env.getAD_Client_ID(Env.getCtx()));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MCBPartner(Env.getCtx(), rs.getInt(1), trxName));
            }
        } catch (SQLException e) {
            log.severe("getEmployesActifs : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }
}
