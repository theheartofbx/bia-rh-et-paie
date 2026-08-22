package org.sitracel.evaluation.modelvalidator.grille;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.sitracel.evaluation.model.I_HR_EvalGrilleLigne;

/**
 * ModelValidator HR_EvalGrilleLigne
 *
 * BEFORE_NEW / BEFORE_CHANGE :
 *   1. Grille validée → lecture seule
 *   2. Évaluations générées → interdire modification
 *   3. Acronyme unique dans la grille
 *   4. Format acronyme : [A-Z0-9_]+
 *   5. ScoreMin < ScoreMax
 *   6. SeuilEchec < SeuilValidation
 *   7. Auto-copie depuis l'objectif du catalogue
 *   8. Renommage acronyme → invalider les formules
 *
 * BEFORE_DELETE :
 *   9. Grille validée → pas de suppression
 *  10. Évaluations générées → interdire suppression
 *  11. Invalider les formules qui utilisent cet acronyme
 */
public class SitracelModelValidatorGrilleLigne implements ModelValidator {

    private static final Logger log = Logger.getLogger(
            SitracelModelValidatorGrilleLigne.class.getName());

    private int m_AD_Client_ID = -1;

    // =========================================================================
    // INITIALISATION
    // =========================================================================

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) m_AD_Client_ID = client.getAD_Client_ID();
        engine.addModelChange(I_HR_EvalGrilleLigne.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return m_AD_Client_ID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String docValidate(PO po, int timing) { return null; }

    // =========================================================================
    // DISPATCH
    // =========================================================================

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            return beforeSave(po, type == TYPE_BEFORE_NEW);
        }
        if (type == TYPE_BEFORE_DELETE) {
            return beforeDelete(po);
        }
        return null;
    }

    // =========================================================================
    // BEFORE_NEW / BEFORE_CHANGE
    // =========================================================================

    private String beforeSave(PO po, boolean isNew) {
        int grilleId = (Integer) po.get_Value("HR_EvalGrille_ID");
        int ligneId = po.get_ID();
        String acronyme = (String) po.get_Value("Acronyme");

        // --- Garde-fou : grille validée → lecture seule ---
        String isGrilleValidee = DB.getSQLValueString(po.get_TrxName(),
            "SELECT IsValidee FROM HR_EvalGrille WHERE HR_EvalGrille_ID = ?", grilleId);
        if ("Y".equals(isGrilleValidee)) {
            return "La grille est validée, aucune modification n'est possible.";
        }

        // --- Garde-fou : évaluations générées → interdire modification ---
        if (!isNew) {
            String errEval = verifierEvaluationsExistantes(grilleId, po.get_TrxName());
            if (errEval != null) return errEval;
        }

        // --- Garde-fou : format acronyme ---
        if (acronyme == null || acronyme.trim().isEmpty()) {
            return "L'acronyme est obligatoire.";
        }
        acronyme = acronyme.trim().toUpperCase();
        po.set_ValueOfColumn("Acronyme", acronyme);

        // --- Garde-fou : SeqNo unique dans la grille ---
        Object seqNoObj = po.get_Value("SeqNo");
        if (seqNoObj != null) {
            int seqNo = ((Number) seqNoObj).intValue();
            String sqlSeq = "SELECT COUNT(*) FROM HR_EvalGrilleLigne"
                + " WHERE HR_EvalGrille_ID = ? AND SeqNo = ? AND IsActive = 'Y'"
                + " AND HR_EvalGrilleLigne_ID != ?";
            int doublonSeq = DB.getSQLValueEx(po.get_TrxName(), sqlSeq,
                grilleId, seqNo, isNew ? 0 : ligneId);
            if (doublonSeq > 0) {
                return "Le numéro de séquence " + seqNo + " est déjà utilisé dans cette grille.";
            }
        }

        if (!acronyme.matches("[A-Z0-9_]+")) {
            return "L'acronyme '" + acronyme
                + "' est invalide. Seuls les majuscules, chiffres et underscores sont autorisés.";
        }

        // --- Garde-fou : acronyme unique dans la grille ---
        String sqlDoublon = "SELECT COUNT(*) FROM HR_EvalGrilleLigne"
            + " WHERE HR_EvalGrille_ID = ? AND Acronyme = ? AND IsActive = 'Y'"
            + " AND HR_EvalGrilleLigne_ID != ?";
        int doublon = DB.getSQLValueEx(po.get_TrxName(), sqlDoublon,
            grilleId, acronyme, isNew ? 0 : ligneId);
        if (doublon > 0) {
            return "L'acronyme '" + acronyme + "' est déjà utilisé dans cette grille.";
        }

        // --- Garde-fou : ScoreMin < ScoreMax ---
        BigDecimal scoreMin = (BigDecimal) po.get_Value("ScoreMin");
        BigDecimal scoreMax = (BigDecimal) po.get_Value("ScoreMax");
        if (scoreMin != null && scoreMax != null
                && scoreMin.compareTo(scoreMax) >= 0) {
            return "Le score minimum (" + scoreMin
                + ") doit être inférieur au score maximum (" + scoreMax + ").";
        }

        // --- Garde-fou : SeuilEchec < SeuilValidation ---
        BigDecimal seuilEchec = (BigDecimal) po.get_Value("SeuilEchec");
        BigDecimal seuilValidation = (BigDecimal) po.get_Value("SeuilValidation");
        if (seuilEchec != null && seuilValidation != null
                && seuilEchec.compareTo(seuilValidation) >= 0) {
            return "Le seuil d'échec (" + seuilEchec
                + ") doit être inférieur au seuil de validation (" + seuilValidation + ").";
        }

        // --- Auto-copie depuis l'objectif du catalogue ---
        int objectifId = (Integer) po.get_Value("HR_EvalObjectif_ID");
        if (objectifId > 0 && (isNew || po.is_ValueChanged("HR_EvalObjectif_ID"))) {
            autoRemplirDepuisObjectif(po, objectifId);
        }

        // --- Invalidation des formules si acronyme modifié ---
        if (!isNew && po.is_ValueChanged("Acronyme")) {
            String ancienAcronyme = (String) po.get_ValueOld("Acronyme");
            if (ancienAcronyme != null && !ancienAcronyme.isEmpty()) {
                invaliderFormulesAvecAcronyme(grilleId, ancienAcronyme,
                    "Invalidée : l'acronyme '" + ancienAcronyme
                    + "' a été renommé en '" + acronyme + "'",
                    po.get_TrxName());
            }
        }

        return null;
    }

    // =========================================================================
    // BEFORE_DELETE
    // =========================================================================

    private String beforeDelete(PO po) {
        int grilleId = (Integer) po.get_Value("HR_EvalGrille_ID");

        // --- Garde-fou : grille validée → pas de suppression ---
        String isGrilleValidee = DB.getSQLValueString(po.get_TrxName(),
            "SELECT IsValidee FROM HR_EvalGrille WHERE HR_EvalGrille_ID = ?", grilleId);
        if ("Y".equals(isGrilleValidee)) {
            return "La grille est validée, aucune suppression n'est possible.";
        }

        // --- Garde-fou : évaluations générées → interdire suppression ---
        String errEval = verifierEvaluationsExistantes(grilleId, po.get_TrxName());
        if (errEval != null) return errEval;

        // --- Invalider les formules qui utilisent cet acronyme ---
        String acronyme = (String) po.get_Value("Acronyme");
        if (acronyme != null && !acronyme.isEmpty()) {
            invaliderFormulesAvecAcronyme(grilleId, acronyme,
                "Invalidée : l'acronyme '" + acronyme + "' a été supprimé de la grille",
                po.get_TrxName());
        }

        return null;
    }

    // =========================================================================
    // MÉTHODES PRIVÉES
    // =========================================================================

    /**
     * Vérifie si des évaluations ont déjà été générées avec cette grille.
     */
    private String verifierEvaluationsExistantes(int grilleId, String trxName) {
        int nbEval = DB.getSQLValueEx(trxName,
            "SELECT COUNT(*) FROM HR_Eval"
            + " WHERE HR_EvalGrille_ID = ? AND IsGeneree = 'Y' AND IsActive = 'Y'",
            grilleId);
        if (nbEval > 0) {
            return "Impossible : " + nbEval + " évaluation(s) ont déjà été générée(s) "
                + "avec cette grille. La modification ou suppression "
                + "pourrait avoir des répercussions imprévisibles sur ces évaluations.";
        }
        return null;
    }

    /**
     * Auto-remplir les propriétés depuis l'objectif du catalogue
     */
    private void autoRemplirDepuisObjectif(PO po, int objectifId) {
        String trx = po.get_TrxName();

        String isProgressif = DB.getSQLValueString(trx,
            "SELECT IsProgressif FROM HR_EvalObjectif WHERE HR_EvalObjectif_ID = ?", objectifId);
        if (isProgressif != null) po.set_ValueOfColumn("IsProgressif", isProgressif);

        String isBinaire = DB.getSQLValueString(trx,
            "SELECT IsBinaire FROM HR_EvalObjectif WHERE HR_EvalObjectif_ID = ?", objectifId);
        if (isBinaire != null) po.set_ValueOfColumn("IsBinaire", isBinaire);

        String isSubjectif = DB.getSQLValueString(trx,
            "SELECT IsSubjectif FROM HR_EvalObjectif WHERE HR_EvalObjectif_ID = ?", objectifId);
        if (isSubjectif != null) po.set_ValueOfColumn("IsSubjectif", isSubjectif);

        String isPourcentage = DB.getSQLValueString(trx,
            "SELECT IsPourcentage FROM HR_EvalObjectif WHERE HR_EvalObjectif_ID = ?", objectifId);
        if (isPourcentage != null) po.set_ValueOfColumn("IsPourcentage", isPourcentage);

        BigDecimal scoreMaxDefaut = DB.getSQLValueBD(trx,
            "SELECT ScoreMax_Defaut FROM HR_EvalObjectif WHERE HR_EvalObjectif_ID = ?", objectifId);
        if (scoreMaxDefaut != null && po.get_Value("ScoreMax") == null) {
            po.set_ValueOfColumn("ScoreMax", scoreMaxDefaut);
        }
    }

    /**
     * Invalider toutes les formules de la grille qui contiennent l'acronyme donné
     */
    private void invaliderFormulesAvecAcronyme(int grilleId, String acronyme,
            String messageErreur, String trxName) {
        String sql = "SELECT HR_EvalGrilleFormule_ID, Formule"
            + " FROM HR_EvalGrilleFormule"
            + " WHERE HR_EvalGrille_ID = ? AND IsActive = 'Y'";

        java.sql.PreparedStatement pstmt = null;
        java.sql.ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, grilleId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int formuleId = rs.getInt(1);
                String formule = rs.getString(2);
                if (formule != null && contientAcronyme(formule, acronyme)) {
                    DB.executeUpdateEx(
                        "UPDATE HR_EvalGrilleFormule"
                        + " SET IsValide = 'N', MessageErreur = ?"
                        + " WHERE HR_EvalGrilleFormule_ID = ?",
                        new Object[]{ messageErreur, formuleId }, trxName);
                    log.info("Formule ID=" + formuleId + " invalidée : " + messageErreur);
                }
            }
        } catch (Exception e) {
            log.severe("Erreur invalidation formules : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
    }

    /**
     * Vérifie si la formule contient l'acronyme comme mot entier
     */
    private boolean contientAcronyme(String formule, String acronyme) {
        String normalized = formule.replaceAll("[^A-Za-z0-9_]", " ");
        String[] tokens = normalized.split("\\s+");
        for (String token : tokens) {
            if (token.equalsIgnoreCase(acronyme)) return true;
        }
        return false;
    }
}
