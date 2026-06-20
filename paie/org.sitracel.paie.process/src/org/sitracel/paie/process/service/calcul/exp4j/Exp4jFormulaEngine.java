package org.sitracel.paie.process.service.calcul.exp4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

/**
 * Moteur d'évaluation de formules arithmétiques via exp4j.
 *
 * Supporte :
 *   - Opérateurs : +, -, *, /, (, )
 *   - Fonctions  : Math.min(), Math.max()
 *   - Variables  : tout code élément de paie (SB, IL, IT, BCNPS, etc.)
 *
 * Ne supporte PAS :
 *   - Opérateur ternaire ? :
 *   - if / else
 *   - Ces cas sont traités par les calculateurs natifs (BaremeFiscalCameroun, etc.)
 *
 * Utilisation :
 *   Map<String, BigDecimal> vars = new HashMap<>();
 *   vars.put("SB", new BigDecimal("300000"));
 *   vars.put("IL", new BigDecimal("45000"));
 *   BigDecimal result = Exp4jFormulaEngine.evaluer("Math.min((SB + IL), 750000)", vars);
 */
public class Exp4jFormulaEngine {

    // exp4j n'accepte pas "Math.min" — on définit des fonctions "min" et "max"
    private static final Function FUNC_MIN = new Function("min", 2) {
        @Override
        public double apply(double... args) {
            return Math.min(args[0], args[1]);
        }
    };

    private static final Function FUNC_MAX = new Function("max", 2) {
        @Override
        public double apply(double... args) {
            return Math.max(args[0], args[1]);
        }
    };

    /**
     * Évalue une formule arithmétique avec les variables fournies.
     *
     * @param formule   formule stockée en base, ex: "Math.min((SB + IL), 750000)"
     * @param variables map code_element → montant calculé jusqu'ici
     * @return          résultat arrondi à l'entier inférieur (FCFA)
     * @throws IllegalArgumentException si la formule est invalide ou une variable manque
     */
    public static BigDecimal evaluer(String formule, Map<String, BigDecimal> variables) {
        if (formule == null || formule.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        // Normaliser : "Math.min" → "min", "Math.max" → "max"
        String formulaNormalisee = formule
                .replace("Math.min", "min")
                .replace("Math.max", "max")
                .trim();

        try {
            ExpressionBuilder builder = new ExpressionBuilder(formulaNormalisee)
                    .functions(FUNC_MIN, FUNC_MAX);

            // Déclarer toutes les variables connues
            for (String cle : variables.keySet()) {
                if (formulaNormalisee.contains(cle)) {
                    builder.variable(cle);
                }
            }

            Expression expression = builder.build();

            // Injecter les valeurs
            for (Map.Entry<String, BigDecimal> entry : variables.entrySet()) {
                if (formulaNormalisee.contains(entry.getKey())) {
                    BigDecimal valeur = entry.getValue() != null
                            ? entry.getValue()
                            : BigDecimal.ZERO;
                    expression.setVariable(entry.getKey(), valeur.doubleValue());
                }
            }

            double resultat = expression.evaluate();

            if (Double.isNaN(resultat) || Double.isInfinite(resultat)) {
                return BigDecimal.ZERO;
            }

            return BigDecimal.valueOf(resultat).setScale(0, RoundingMode.FLOOR);

        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Erreur évaluation formule [" + formule + "] : " + e.getMessage(), e);
        }
    }
}
