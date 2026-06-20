package org.sitracel.paie.process.service.calcul.fiscal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Barème fiscal camerounais — IRPP / IBC
 *
 * Utilisé pour :
 *   - IRPP paie    : base = BMIRPP  (mensuel)
 *   - IRPP congé   : base = IBC     (indemnité brute de congé)
 *
 * Tranches mensuelles (FCFA) :
 *   T1 : 0          → 166 667   → 10%
 *   T2 : 166 667    → 250 000   → 15%
 *   T3 : 250 000    → 416 667   → 25%
 *   T4 : 416 667    → ∞         → 35%
 */
public class BaremeFiscalCameroun {

    private static final BigDecimal TRANCHE_1_MAX = new BigDecimal("166667");
    private static final BigDecimal TRANCHE_2_MAX = new BigDecimal("250000");
    private static final BigDecimal TRANCHE_3_MAX = new BigDecimal("416667");

    private static final BigDecimal TAUX_T1 = new BigDecimal("0.10");
    private static final BigDecimal TAUX_T2 = new BigDecimal("0.15");
    private static final BigDecimal TAUX_T3 = new BigDecimal("0.25");
    private static final BigDecimal TAUX_T4 = new BigDecimal("0.35");

    /**
     * Calcule l'impôt sur la base imposable fournie.
     *
     * @param base  montant mensuel imposable (BMIRPP ou IBC), jamais null
     * @return      montant de l'impôt, arrondi à l'entier inférieur (FCFA)
     */
    public static BigDecimal calculer(BigDecimal base) {
        if (base == null || base.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal impot = BigDecimal.ZERO;

        // Tranche 1 : 0 → 166 667
        if (base.compareTo(TRANCHE_1_MAX) <= 0) {
            impot = base.multiply(TAUX_T1);
            return arrondir(impot);
        }
        impot = impot.add(TRANCHE_1_MAX.multiply(TAUX_T1));

        // Tranche 2 : 166 667 → 250 000
        if (base.compareTo(TRANCHE_2_MAX) <= 0) {
            impot = impot.add(base.subtract(TRANCHE_1_MAX).multiply(TAUX_T2));
            return arrondir(impot);
        }
        impot = impot.add(TRANCHE_2_MAX.subtract(TRANCHE_1_MAX).multiply(TAUX_T2));

        // Tranche 3 : 250 000 → 416 667
        if (base.compareTo(TRANCHE_3_MAX) <= 0) {
            impot = impot.add(base.subtract(TRANCHE_2_MAX).multiply(TAUX_T3));
            return arrondir(impot);
        }
        impot = impot.add(TRANCHE_3_MAX.subtract(TRANCHE_2_MAX).multiply(TAUX_T3));

        // Tranche 4 : au-delà de 416 667
        impot = impot.add(base.subtract(TRANCHE_3_MAX).multiply(TAUX_T4));

        return arrondir(impot);
    }

    private static BigDecimal arrondir(BigDecimal montant) {
        return montant.setScale(0, RoundingMode.FLOOR);
    }
}
