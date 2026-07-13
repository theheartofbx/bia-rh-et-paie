package org.sitracel.paie.process.service.calcul;

import java.math.BigDecimal;
import java.util.Map;

import org.sitracel.paie.process.service.calcul.exp4j.Exp4jFormulaEngine;
import org.sitracel.paie.process.service.calcul.fiscal.BaremeFiscalCameroun;
import org.sitracel.paie.process.service.calcul.fiscal.PayrollBairppCalculator;

/**
 * Dispatcher des formules de paie.
 *
 * Chaque code élément est routé vers le bon moteur de calcul :
 *
 *   BAIRPP → PayrollBairppCalculator  (logique fiscale annuelle)
 *   IRPP   → BaremeFiscalCameroun     (barème 4 tranches, base = BMIRPP)
 *   autres → Exp4jFormulaEngine       (arithmétique pure)
 *
 * Convention des variables :
 *   La map "variables" contient tous les montants déjà calculés,
 *   indexés par leur code élément (ex: "SB", "IL", "BCNPS"...).
 *   Les éléments sont calculés dans l'ordre du rang (hr_rang_calcul_id),
 *   donc quand on calcule X, tous les éléments dont X dépend sont déjà dans la map.
 */
public class FormulaEvaluator {

    // Codes éléments traités par des calculateurs natifs
    private static final String CODE_BAIRPP = "BAIRPP";
    private static final String CODE_BMIRPP = "BMIRPP";
    private static final String CODE_IRPP   = "IRPP";

    /**
     * Évalue un élément de paie.
     *
     * @param codeElement  code de l'élément (ex: "BCNPS", "BAIRPP", "IRPP")
     * @param formule      formule stockée en base (peut être null pour BAIRPP/IRPP)
     * @param variables    montants déjà calculés, indexés par code élément
     * @return             montant calculé, jamais null
     */
    public static BigDecimal evaluer(String codeElement,
                                     String formule,
                                     Map<String, BigDecimal> variables) {

        if (codeElement == null) {
            return BigDecimal.ZERO;
        }

        switch (codeElement) {

            case CODE_BAIRPP:
                return calculerBairpp(variables);

            case CODE_BMIRPP:
                return calculerBmirpp(variables);

            case CODE_IRPP:
                return calculerIrpp(variables);

            default:
                // Tous les autres éléments : formule arithmétique via exp4j
                if (formule == null || formule.trim().isEmpty()) {
                    return BigDecimal.ZERO;
                }
                return Exp4jFormulaEngine.evaluer(formule, variables);
        }
    }

    // -------------------------------------------------------------------------
    // Calculateurs natifs
    // -------------------------------------------------------------------------

    /**
     * BAIRPP = Base Annuelle IRPP
     * Dépend de : SBR (mensuel), et des charges salariales annuelles.
     * Les charges salariales = CNPS + PF + PV + CFC-S (somme déjà en variables).
     */
    private static BigDecimal calculerBairpp(Map<String, BigDecimal> variables) {
        return PayrollBairppCalculator.calculer(variables);
    }

    /**
     * BMIRPP = Base Mensuelle IRPP
     * Formule : si (BAIRPP / 12) < 62000 alors 0, sinon (BAIRPP / 12)
     */
    private static BigDecimal calculerBmirpp(Map<String, BigDecimal> variables) {
        BigDecimal bairpp = get(variables, "BAIRPP");
        BigDecimal mensuel = bairpp.divide(new BigDecimal("12"), 2, java.math.RoundingMode.FLOOR);
        if (mensuel.compareTo(new BigDecimal("62000")) < 0) {
            return BigDecimal.ZERO;
        }
        return mensuel;
    }

    /**
     * IRPP = Impôt sur le Revenu des Personnes Physiques
     * Dépend de : BMIRPP (base mensuelle imposable, déjà calculée avant IRPP)
     */
    private static BigDecimal calculerIrpp(Map<String, BigDecimal> variables) {
        BigDecimal bmirpp = get(variables, "BMIRPP");
        return BaremeFiscalCameroun.calculer(bmirpp);
    }

    // -------------------------------------------------------------------------
    // Utilitaire
    // -------------------------------------------------------------------------

    private static BigDecimal get(Map<String, BigDecimal> variables, String code) {
        BigDecimal val = variables.get(code);
        return val != null ? val : BigDecimal.ZERO;
    }
}
