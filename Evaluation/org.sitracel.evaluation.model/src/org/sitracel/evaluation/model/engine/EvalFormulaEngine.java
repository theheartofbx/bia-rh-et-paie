package org.sitracel.evaluation.model.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

/**
 * Moteur d'évaluation de formules pour le module Auto-Évaluation.
 *
 * Fonctions disponibles :
 *   Numériques  : min, max, abs, round, floor, ceil, pow, sqrt, clamp, pct
 *   Comparaison : sup, supeg, inf, infeg, egal, entre  (retournent 1 ou 0)
 *   Conditionnel: si(comparaison, valeur_vrai, valeur_faux)
 *
 * Variables = acronymes des objectifs (ex: CA, DELAI, LEADER)
 */
public class EvalFormulaEngine {

    // =========================================================================
    // FONCTIONS NUMÉRIQUES
    // =========================================================================

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

    private static final Function FUNC_ABS = new Function("abs", 1) {
        @Override
        public double apply(double... args) {
            return Math.abs(args[0]);
        }
    };

    private static final Function FUNC_ROUND = new Function("round", 1) {
        @Override
        public double apply(double... args) {
            return Math.round(args[0]);
        }
    };

    private static final Function FUNC_FLOOR = new Function("floor", 1) {
        @Override
        public double apply(double... args) {
            return Math.floor(args[0]);
        }
    };

    private static final Function FUNC_CEIL = new Function("ceil", 1) {
        @Override
        public double apply(double... args) {
            return Math.ceil(args[0]);
        }
    };

    private static final Function FUNC_POW = new Function("pow", 2) {
        @Override
        public double apply(double... args) {
            return Math.pow(args[0], args[1]);
        }
    };

    private static final Function FUNC_SQRT = new Function("sqrt", 1) {
        @Override
        public double apply(double... args) {
            return Math.sqrt(args[0]);
        }
    };

    private static final Function FUNC_CLAMP = new Function("clamp", 3) {
        @Override
        public double apply(double... args) {
            return Math.min(Math.max(args[0], args[1]), args[2]);
        }
    };

    private static final Function FUNC_PCT = new Function("pct", 2) {
        @Override
        public double apply(double... args) {
            if (args[1] == 0) return 0;
            return (args[0] / args[1]) * 100;
        }
    };

    // =========================================================================
    // FONCTIONS DE COMPARAISON (retournent 1 ou 0)
    // =========================================================================

    private static final Function FUNC_SUP = new Function("sup", 2) {
        @Override
        public double apply(double... args) {
            return args[0] > args[1] ? 1 : 0;
        }
    };

    private static final Function FUNC_SUPEG = new Function("supeg", 2) {
        @Override
        public double apply(double... args) {
            return args[0] >= args[1] ? 1 : 0;
        }
    };

    private static final Function FUNC_INF = new Function("inf", 2) {
        @Override
        public double apply(double... args) {
            return args[0] < args[1] ? 1 : 0;
        }
    };

    private static final Function FUNC_INFEG = new Function("infeg", 2) {
        @Override
        public double apply(double... args) {
            return args[0] <= args[1] ? 1 : 0;
        }
    };

    private static final Function FUNC_EGAL = new Function("egal", 2) {
        @Override
        public double apply(double... args) {
            return Math.abs(args[0] - args[1]) < 0.0001 ? 1 : 0;
        }
    };

    private static final Function FUNC_ENTRE = new Function("entre", 3) {
        @Override
        public double apply(double... args) {
            return (args[0] >= args[1] && args[0] <= args[2]) ? 1 : 0;
        }
    };

    // =========================================================================
    // FONCTION CONDITIONNELLE
    // =========================================================================

    private static final Function FUNC_SI = new Function("si", 3) {
        @Override
        public double apply(double... args) {
            return args[0] == 1 ? args[1] : args[2];
        }
    };

    // =========================================================================
    // TOUTES LES FONCTIONS
    // =========================================================================

    private static final Function[] ALL_FUNCTIONS = {
        FUNC_MIN, FUNC_MAX, FUNC_ABS, FUNC_ROUND, FUNC_FLOOR, FUNC_CEIL,
        FUNC_POW, FUNC_SQRT, FUNC_CLAMP, FUNC_PCT,
        FUNC_SUP, FUNC_SUPEG, FUNC_INF, FUNC_INFEG, FUNC_EGAL, FUNC_ENTRE,
        FUNC_SI
    };

    // =========================================================================
    // ÉVALUATION
    // =========================================================================

    /**
     * Évalue une formule avec les variables fournies.
     *
     * @param formule   expression (ex: "CA * 3 + DELAI * 2")
     * @param variables map acronyme → valeur numérique
     * @return          résultat arrondi à 2 décimales
     * @throws IllegalArgumentException si la formule est invalide
     */
    public static BigDecimal evaluer(String formule, Map<String, BigDecimal> variables) {
        if (formule == null || formule.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        String formulaNormalisee = formule.trim();

        try {
            ExpressionBuilder builder = new ExpressionBuilder(formulaNormalisee)
                    .functions(ALL_FUNCTIONS);

            // Déclarer les variables
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
                throw new IllegalArgumentException(
                    "Résultat non calculable (NaN ou Infini)");
            }

            return BigDecimal.valueOf(resultat).setScale(2, RoundingMode.HALF_UP);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException(
                "Erreur évaluation formule [" + formule + "] : " + e.getMessage(), e);
        }
    }
}
