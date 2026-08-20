package org.sitracel.evaluation.modelvalidator.grille;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.sitracel.evaluation.model.I_HR_EvalGrilleFormule;

/**
 * ModelValidator HR_EvalGrilleFormule
 *
 * BEFORE_NEW / BEFORE_CHANGE :
 *   1. Grille validée → lecture seule
 *   2. Évaluations générées → interdire modification
 *   3. Validation syntaxe formule (Nashorn)
 *   4. Variables = acronymes de la grille
 *   5. Division par zéro (ScoreTest != 0 si au dénominateur)
 *   6. Calcul test avec les ScoreTest
 *   7. Vérification si() explicite
 *   8. Une seule formule IsPrincipale = Y par grille
 *
 * BEFORE_DELETE :
 *   9. Grille validée → pas de suppression
 *  10. Évaluations générées → interdire suppression
 */
public class SitracelModelValidatorGrilleFormule implements ModelValidator {

    private static final Logger log = Logger.getLogger(
            SitracelModelValidatorGrilleFormule.class.getName());

    private int m_AD_Client_ID = -1;

    // =========================================================================
    // INITIALISATION
    // =========================================================================

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) m_AD_Client_ID = client.getAD_Client_ID();
        engine.addModelChange(I_HR_EvalGrilleFormule.Table_Name, this);
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
        int formuleId = po.get_ID();

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

        // --- Garde-fou : une seule formule IsPrincipale par grille ---
        Object isPrincipaleObj = po.get_Value("IsPrincipale");
        boolean isPrincipale = isPrincipaleObj != null && "Y".equals(isPrincipaleObj.toString());

        if (isPrincipale) {
            String sqlDoublon = "SELECT COUNT(*) FROM HR_EvalGrilleFormule"
                + " WHERE HR_EvalGrille_ID = ? AND IsPrincipale = 'Y' AND IsActive = 'Y'"
                + " AND HR_EvalGrilleFormule_ID != ?";
            int doublon = DB.getSQLValueEx(po.get_TrxName(), sqlDoublon,
                grilleId, isNew ? 0 : formuleId);
            if (doublon > 0) {
                return "Une formule principale existe déjà dans cette grille. "
                    + "Désactivez-la d'abord.";
            }
        }

        // --- Validation de la formule ---
        String formule = (String) po.get_Value("Formule");
        if (formule == null || formule.trim().isEmpty()) {
            return "La formule est obligatoire.";
        }
        formule = formule.trim();

        // Charger les acronymes et ScoreTest de la grille
        Map<String, BigDecimal> acronymes = chargerAcronymesGrille(grilleId, po.get_TrxName());

        if (acronymes.isEmpty()) {
            po.set_ValueOfColumn("IsValide", "N");
            po.set_ValueOfColumn("MessageErreur", "La grille n'a aucun objectif avec un acronyme.");
            po.set_ValueOfColumn("ResultatTest", null);
            return null;
        }

        // Étape 1 : extraire les variables de la formule
        Set<String> variablesFormule = extraireVariables(formule);

        // Étape 2 : vérifier que chaque variable existe dans la grille
        for (String var : variablesFormule) {
            if (!acronymes.containsKey(var)) {
                po.set_ValueOfColumn("IsValide", "N");
                po.set_ValueOfColumn("MessageErreur",
                    "Variable inconnue : '" + var + "'. Acronymes disponibles : "
                    + acronymes.keySet());
                po.set_ValueOfColumn("ResultatTest", null);
                return null;
            }
        }

        // Étape 3 : vérification si() explicite
        String erreurSi = verifierSiExplicite(formule);
        if (erreurSi != null) {
            po.set_ValueOfColumn("IsValide", "N");
            po.set_ValueOfColumn("MessageErreur", erreurSi);
            po.set_ValueOfColumn("ResultatTest", null);
            return null;
        }

        // Étape 4 : division par zéro potentielle
        for (String var : variablesFormule) {
            BigDecimal scoreTest = acronymes.get(var);
            if (scoreTest != null && scoreTest.compareTo(BigDecimal.ZERO) == 0) {
                if (estAuDenominateur(formule, var)) {
                    po.set_ValueOfColumn("IsValide", "N");
                    po.set_ValueOfColumn("MessageErreur",
                        "'" + var + "' est utilisé comme diviseur mais son ScoreTest est 0. "
                        + "Mettez un ScoreTest différent de 0.");
                    po.set_ValueOfColumn("ResultatTest", null);
                    return null;
                }
            }
        }

        // Étape 5 : calcul test avec Nashorn
        try {
            double resultat = evaluerFormule(formule, acronymes);
            if (Double.isNaN(resultat) || Double.isInfinite(resultat)) {
                po.set_ValueOfColumn("IsValide", "N");
                po.set_ValueOfColumn("MessageErreur",
                    "La formule produit un résultat non calculable (NaN ou Infini) "
                    + "avec les valeurs de test.");
                po.set_ValueOfColumn("ResultatTest", null);
            } else {
                po.set_ValueOfColumn("IsValide", "Y");
                po.set_ValueOfColumn("MessageErreur", null);
                po.set_ValueOfColumn("ResultatTest", BigDecimal.valueOf(resultat));
                log.info("Formule validée. Résultat test = " + resultat);
            }
        } catch (Exception e) {
            po.set_ValueOfColumn("IsValide", "N");
            po.set_ValueOfColumn("MessageErreur",
                "Erreur de syntaxe : " + e.getMessage());
            po.set_ValueOfColumn("ResultatTest", null);
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

        return null;
    }

    // =========================================================================
    // MÉTHODES PRIVÉES
    // =========================================================================

    /**
     * Vérifie si des évaluations ont déjà été générées avec cette grille.
     * Si oui, retourne un message d'erreur. Sinon retourne null.
     */
    private String verifierEvaluationsExistantes(int grilleId, String trxName) {
        int nbEval = DB.getSQLValueEx(trxName,
            "SELECT COUNT(*) FROM HR_Eval"
            + " WHERE HR_EvalGrille_ID = ? AND IsGeneree = 'Y' AND IsActive = 'Y'",
            grilleId);
        if (nbEval > 0) {
            return "Impossible : " + nbEval + " évaluation(s) ont déjà été générée(s) "
                + "avec cette grille. La modification ou suppression d'une formule "
                + "pourrait avoir des répercussions imprévisibles sur ces évaluations.";
        }
        return null;
    }

    /**
     * Charger les acronymes et ScoreTest de la grille
     */
    private Map<String, BigDecimal> chargerAcronymesGrille(int grilleId, String trxName) {
        Map<String, BigDecimal> map = new LinkedHashMap<String, BigDecimal>();
        String sql = "SELECT Acronyme, ScoreTest FROM HR_EvalGrilleLigne"
            + " WHERE HR_EvalGrille_ID = ? AND IsActive = 'Y' AND Acronyme IS NOT NULL";
        java.sql.PreparedStatement pstmt = null;
        java.sql.ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, grilleId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String acr = rs.getString(1);
                BigDecimal scoreTest = rs.getBigDecimal(2);
                if (acr != null) map.put(acr.toUpperCase(), scoreTest);
            }
        } catch (Exception e) {
            log.severe("Erreur chargement acronymes : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return map;
    }

    /**
     * Extraire les variables de la formule en excluant les fonctions connues
     */
    private Set<String> extraireVariables(String formule) {
        Set<String> fonctions = new HashSet<String>(Arrays.asList(
            "min", "max", "abs", "round", "floor", "ceil", "pow", "sqrt",
            "clamp", "pct", "sup", "supeg", "inf", "infeg", "egal", "entre", "si"
        ));
        Set<String> variables = new LinkedHashSet<String>();
        String normalized = formule.replaceAll("[^A-Za-z0-9_]", " ");
        String[] tokens = normalized.split("\\s+");
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            if (token.matches("[0-9]+\\.?[0-9]*")) continue;
            if (fonctions.contains(token.toLowerCase())) continue;
            variables.add(token.toUpperCase());
        }
        return variables;
    }

    /**
     * Vérifier que chaque si() utilise une comparaison explicite
     */
    private String verifierSiExplicite(String formule) {
        Set<String> comparaisons = new HashSet<String>(Arrays.asList(
            "sup", "supeg", "inf", "infeg", "egal", "entre"
        ));

        String lower = formule.toLowerCase();
        int idx = 0;
        while ((idx = lower.indexOf("si(", idx)) >= 0) {
            if (idx > 0 && Character.isLetterOrDigit(lower.charAt(idx - 1))) {
                idx++;
                continue;
            }
            int start = idx + 3;
            while (start < lower.length() && lower.charAt(start) == ' ') start++;
            boolean found = false;
            for (String comp : comparaisons) {
                if (lower.startsWith(comp + "(", start)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return "Le premier argument de si() à la position " + idx
                    + " doit être une comparaison (sup, supeg, inf, infeg, egal, entre).";
            }
            idx = start;
        }
        return null;
    }

    /**
     * Vérifier si une variable est au dénominateur
     */
    private boolean estAuDenominateur(String formule, String variable) {
        String normalized = formule.replaceAll("\\s+", "");
        int idx = 0;
        while ((idx = normalized.indexOf("/", idx)) >= 0) {
            idx++;
            while (idx < normalized.length()
                    && (normalized.charAt(idx) == ' ' || normalized.charAt(idx) == '(')) {
                idx++;
            }
            int start = idx;
            while (idx < normalized.length()
                    && (Character.isLetterOrDigit(normalized.charAt(idx))
                        || normalized.charAt(idx) == '_')) {
                idx++;
            }
            String token = normalized.substring(start, idx);
            if (token.equalsIgnoreCase(variable)) return true;
        }
        return false;
    }

    /**
     * Évaluer la formule avec Nashorn (Java 8)
     */
    private double evaluerFormule(String formule, Map<String, BigDecimal> variables)
            throws Exception {
        javax.script.ScriptEngine engine =
            new javax.script.ScriptEngineManager().getEngineByName("js");

        StringBuilder script = new StringBuilder();
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

        for (Map.Entry<String, BigDecimal> entry : variables.entrySet()) {
            double val = entry.getValue() != null ? entry.getValue().doubleValue() : 0.0;
            script.append("var " + entry.getKey() + " = " + val + ";\n");
        }

        script.append(formule);

        Object result = engine.eval(script.toString());
        if (result instanceof Number) {
            return ((Number) result).doubleValue();
        }
        throw new Exception("La formule ne retourne pas un nombre.");
    }
}
