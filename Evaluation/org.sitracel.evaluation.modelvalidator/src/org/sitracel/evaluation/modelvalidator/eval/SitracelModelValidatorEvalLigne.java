package org.sitracel.evaluation.modelvalidator.eval;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.logging.Logger;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.evaluation.model.I_HR_EvalLigne;

/**
 * ModelValidator HR_EvalLigne
 *
 * BEFORE_NEW / BEFORE_CHANGE :
 *   1. Garde-fou : évaluation validée → lecture seule
 *   2. Garde-fou : Score_Employe entre ScoreMin et ScoreMax
 *   3. Garde-fou : Score_N1 entre ScoreMin et ScoreMax
 *   4. Auto-calcul ScoreFinal : Score_N1 si renseigné, sinon Score_Employe
 *   5. Auto-flag IsEvalue = Y si ScoreFinal != null
 *
 * AFTER_NEW / AFTER_CHANGE / AFTER_DELETE :
 *   6. Recalcul des indicateurs sur HR_Eval parent
 *   7. Recalcul des formules sur HR_EvalResultat (via Nashorn)
 *   8. Mise à jour ScoreTotal sur HR_Eval si formule principale
 */
public class SitracelModelValidatorEvalLigne implements ModelValidator {

    private static final Logger log = Logger.getLogger(
            SitracelModelValidatorEvalLigne.class.getName());

    private int m_AD_Client_ID = -1;

    // =========================================================================
    // INITIALISATION
    // =========================================================================

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) m_AD_Client_ID = client.getAD_Client_ID();
        engine.addModelChange(I_HR_EvalLigne.Table_Name, this);
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
            return beforeSave(po);
        }
        if (type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE || type == TYPE_AFTER_DELETE) {
            recalculerParent(po, type == TYPE_AFTER_DELETE);
        }
        return null;
    }

    // =========================================================================
    // BEFORE_NEW / BEFORE_CHANGE
    // =========================================================================

    private String beforeSave(PO po) {
        int evalId = (Integer) po.get_Value("HR_Eval_ID");

        // --- Garde-fou 1 : évaluation validée → lecture seule ---
        String isValidee = DB.getSQLValueString(po.get_TrxName(),
            "SELECT IsValidee FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if ("Y".equals(isValidee)) {
            return "L'évaluation est validée, aucune modification n'est possible.";
        }

        // --- Récupérer les bornes ---
        BigDecimal scoreMin = (BigDecimal) po.get_Value("ScoreMin");
        BigDecimal scoreMax = (BigDecimal) po.get_Value("ScoreMax");

        // --- Garde-fou 2 : Score_Employe entre ScoreMin et ScoreMax ---
        BigDecimal scoreEmploye = (BigDecimal) po.get_Value("Score_Employe");
        if (scoreEmploye != null && scoreEmploye.compareTo(BigDecimal.ZERO) != 0) {
            if (scoreMin != null && scoreEmploye.compareTo(scoreMin) < 0) {
                return "Le score employé (" + scoreEmploye
                    + ") ne peut pas être inférieur au minimum (" + scoreMin + ").";
            }
            if (scoreMax != null && scoreEmploye.compareTo(scoreMax) > 0) {
                return "Le score employé (" + scoreEmploye
                    + ") ne peut pas dépasser le maximum (" + scoreMax + ").";
            }
        }

        // --- Garde-fou 3 : Score_N1 entre ScoreMin et ScoreMax ---
        BigDecimal scoreN1 = (BigDecimal) po.get_Value("Score_N1");
        if (scoreN1 != null && scoreN1.compareTo(BigDecimal.ZERO) != 0) {
            if (scoreMin != null && scoreN1.compareTo(scoreMin) < 0) {
                return "Le score N+1 (" + scoreN1
                    + ") ne peut pas être inférieur au minimum (" + scoreMin + ").";
            }
            if (scoreMax != null && scoreN1.compareTo(scoreMax) > 0) {
                return "Le score N+1 (" + scoreN1
                    + ") ne peut pas dépasser le maximum (" + scoreMax + ").";
            }
        }

        // --- Auto-calcul ScoreFinal ---
        BigDecimal scoreFinal = null;
        if (scoreN1 != null && scoreN1.compareTo(BigDecimal.ZERO) != 0) {
            scoreFinal = scoreN1;
        } else if (scoreEmploye != null && scoreEmploye.compareTo(BigDecimal.ZERO) != 0) {
            scoreFinal = scoreEmploye;
        }
        po.set_ValueOfColumn("ScoreFinal", scoreFinal);

        // --- Auto-flag IsEvalue ---
        boolean isEvalue = (scoreFinal != null && scoreFinal.compareTo(BigDecimal.ZERO) != 0);
        po.set_ValueOfColumn("IsEvalue", isEvalue ? "Y" : "N");

        return null;
    }

    // =========================================================================
    // AFTER_NEW / AFTER_CHANGE / AFTER_DELETE
    // =========================================================================

    private void recalculerParent(PO po, boolean isDelete) {
        int evalId = (Integer) po.get_Value("HR_Eval_ID");
        String trx = po.get_TrxName();

        try {
            // --- Recalcul des indicateurs ---
            recalculerIndicateurs(evalId, trx);

            // --- Recalcul des formules ---
            recalculerFormules(evalId, trx);

        } catch (Exception e) {
            log.severe("Erreur recalcul parent eval=" + evalId + " : " + e.getMessage());
        }
    }

    // =========================================================================
    // RECALCUL DES INDICATEURS
    // =========================================================================

    private void recalculerIndicateurs(int evalId, String trx) {
        int nbObjectifs = DB.getSQLValueEx(trx,
            "SELECT COUNT(*) FROM HR_EvalLigne"
            + " WHERE HR_Eval_ID = ? AND IsActive = 'Y'", evalId);

        int nbEvalues = DB.getSQLValueEx(trx,
            "SELECT COUNT(*) FROM HR_EvalLigne"
            + " WHERE HR_Eval_ID = ? AND IsActive = 'Y' AND IsEvalue = 'Y'", evalId);

        int nbReussis = DB.getSQLValueEx(trx,
            "SELECT COUNT(*) FROM HR_EvalLigne"
            + " WHERE HR_Eval_ID = ? AND IsActive = 'Y' AND IsEvalue = 'Y'"
            + " AND SeuilValidation IS NOT NULL AND SeuilValidation > 0"
            + " AND ScoreFinal >= SeuilValidation", evalId);

        int nbEchec = DB.getSQLValueEx(trx,
            "SELECT COUNT(*) FROM HR_EvalLigne"
            + " WHERE HR_Eval_ID = ? AND IsActive = 'Y' AND IsEvalue = 'Y'"
            + " AND SeuilEchec IS NOT NULL AND SeuilEchec > 0"
            + " AND ScoreFinal < SeuilEchec", evalId);

        int nbEliminatoires = DB.getSQLValueEx(trx,
            "SELECT COUNT(*) FROM HR_EvalLigne"
            + " WHERE HR_Eval_ID = ? AND IsActive = 'Y' AND IsEvalue = 'Y'"
            + " AND IsEliminatoire = 'Y'"
            + " AND SeuilEchec IS NOT NULL AND SeuilEchec > 0"
            + " AND ScoreFinal < SeuilEchec", evalId);

        int pctAvancement = (nbObjectifs > 0)
            ? (nbEvalues * 100 / nbObjectifs) : 0;

        DB.executeUpdateEx(
            "UPDATE HR_Eval SET"
            + " NombreObjectifs = ?, NombreEvalues = ?,"
            + " NombreReussis = ?, NombreEchec = ?,"
            + " NombreEliminatoires = ?, PourcentageAvancement = ?,"
            + " Updated = now(), UpdatedBy = ?"
            + " WHERE HR_Eval_ID = ?",
            new Object[]{
                nbObjectifs, nbEvalues, nbReussis, nbEchec,
                nbEliminatoires, pctAvancement,
                Env.getAD_User_ID(Env.getCtx()), evalId
            }, trx);
    }

    // =========================================================================
    // RECALCUL DES FORMULES
    // =========================================================================

    private void recalculerFormules(int evalId, String trx) {
        // --- Charger les ScoreFinal de chaque ligne par acronyme ---
        Map<String, BigDecimal> scores = new LinkedHashMap<String, BigDecimal>();
        String sqlLignes = "SELECT Acronyme, ScoreFinal FROM HR_EvalLigne"
            + " WHERE HR_Eval_ID = ? AND IsActive = 'Y' AND Acronyme IS NOT NULL";

        java.sql.PreparedStatement pstmt = null;
        java.sql.ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sqlLignes, trx);
            pstmt.setInt(1, evalId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String acr = rs.getString(1);
                BigDecimal sf = rs.getBigDecimal(2);
                if (acr != null) {
                    scores.put(acr.toUpperCase(), sf != null ? sf : BigDecimal.ZERO);
                }
            }
        } catch (Exception e) {
            log.severe("Erreur chargement scores : " + e.getMessage());
            return;
        } finally {
            DB.close(rs, pstmt);
        }

        // --- Pour chaque résultat, évaluer la formule ---
        String sqlResultats = "SELECT HR_EvalResultat_ID, Formule, IsPrincipale"
            + " FROM HR_EvalResultat"
            + " WHERE HR_Eval_ID = ? AND IsActive = 'Y'";

        try {
            pstmt = DB.prepareStatement(sqlResultats, trx);
            pstmt.setInt(1, evalId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int resultatId = rs.getInt(1);
                String formule = rs.getString(2);
                String isPrincipale = rs.getString(3);

                if (formule == null || formule.trim().isEmpty()) continue;

                try {
                    double resultat = evaluerFormule(formule, scores);
                    BigDecimal bdResultat = BigDecimal.valueOf(resultat)
                        .setScale(2, RoundingMode.HALF_UP);

                    DB.executeUpdateEx(
                        "UPDATE HR_EvalResultat SET"
                        + " Resultat = ?, IsCalcule = 'Y', MessageErreur = NULL,"
                        + " Updated = now(), UpdatedBy = ?"
                        + " WHERE HR_EvalResultat_ID = ?",
                        new Object[]{
                            bdResultat,
                            Env.getAD_User_ID(Env.getCtx()),
                            resultatId
                        }, trx);

                    // --- Si formule principale → ScoreTotal ---
                    if ("Y".equals(isPrincipale)) {
                        DB.executeUpdateEx(
                            "UPDATE HR_Eval SET ScoreTotal = ?,"
                            + " Updated = now(), UpdatedBy = ?"
                            + " WHERE HR_Eval_ID = ?",
                            new Object[]{
                                bdResultat,
                                Env.getAD_User_ID(Env.getCtx()),
                                evalId
                            }, trx);
                    }

                } catch (Exception e) {
                    DB.executeUpdateEx(
                        "UPDATE HR_EvalResultat SET"
                        + " Resultat = NULL, IsCalcule = 'N',"
                        + " MessageErreur = ?,"
                        + " Updated = now(), UpdatedBy = ?"
                        + " WHERE HR_EvalResultat_ID = ?",
                        new Object[]{
                            "Erreur de calcul : " + e.getMessage(),
                            Env.getAD_User_ID(Env.getCtx()),
                            resultatId
                        }, trx);
                }
            }
        } catch (Exception e) {
            log.severe("Erreur recalcul formules : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
    }

    // =========================================================================
    // MOTEUR DE FORMULES (Nashorn - Java 8)
    // =========================================================================

    /**
     * Évaluer la formule avec Nashorn (Java 8)
     */
    private double evaluerFormule(String formule, Map<String, BigDecimal> variables)
            throws Exception {
        javax.script.ScriptEngine engine =
            new javax.script.ScriptEngineManager().getEngineByName("js");

        StringBuilder script = new StringBuilder();

        // Fonctions personnalisées
        script.append("function min(a,b){ return Math.min(a,b); }\n");
        script.append("function max(a,b){ return Math.max(a,b); }\n");
        script.append("function abs(a){ return Math.abs(a); }\n");
        script.append("function round(a){ return Math.round(a); }\n");
        script.append("function floor(a){ return Math.floor(a); }\n");
        script.append("function ceil(a){ return Math.ceil(a); }\n");
        script.append("function pow(a,b){ return Math.pow(a,b); }\n");
        script.append("function sqrt(a){ return Math.sqrt(a); }\n");
        script.append("function clamp(x,mn,mx){ return Math.min(Math.max(x,mn),mx); }\n");
        script.append("function pct(v,t){ return (v/t)*100; }\n");
        script.append("function sup(a,b){ return a>b?1:0; }\n");
        script.append("function supeg(a,b){ return a>=b?1:0; }\n");
        script.append("function inf(a,b){ return a<b?1:0; }\n");
        script.append("function infeg(a,b){ return a<=b?1:0; }\n");
        script.append("function egal(a,b){ return a==b?1:0; }\n");
        script.append("function entre(x,mn,mx){ return (x>=mn&&x<=mx)?1:0; }\n");
        script.append("function si(c,v,f){ return c==1?v:f; }\n");

        // Variables
        for (Map.Entry<String, BigDecimal> entry : variables.entrySet()) {
            double val = entry.getValue() != null ? entry.getValue().doubleValue() : 0.0;
            script.append("var " + entry.getKey() + " = " + val + ";\n");
        }

        // Formule
        script.append(formule);

        Object result = engine.eval(script.toString());
        if (result instanceof Number) {
            return ((Number) result).doubleValue();
        }
        throw new Exception("La formule ne retourne pas un nombre.");
    }
}
