package org.sitracel.paie.process.service.calcul.fiscal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Calcul de la BAIRPP — Base Annuelle de l'IRPP (fiscalité camerounaise)
 *
 * Formule :
 *   BAIRPP = (SBR * 12) - abattement_forfaitaire - charges_deductibles
 *
 * Abattement forfaitaire = 30% du salaire brut annuel, plafonné à 3 000 000 FCFA
 * Charges déductibles    = cotisations salariales annuelles (CNPS, etc.)
 *
 * Utilisé par : FormulaEvaluator pour le code élément "BAIRPP"
 */
public class PayrollBairppCalculator {

    private static final BigDecimal TAUX_ABATTEMENT       = new BigDecimal("0.30");
    private static final BigDecimal PLAFOND_ABATTEMENT    = new BigDecimal("3000000");
    private static final BigDecimal DOUZE                 = new BigDecimal("12");

    /**
     * Calcule la BAIRPP.
     *
     * @param sbrMensuel          salaire brut mensuel (SBR)
     * @param chargesSalarialesAnnuelles  total cotisations salariales sur l'année
     * @return BAIRPP arrondie à l'entier inférieur, jamais négative
     */
    public static BigDecimal calculer(BigDecimal sbrMensuel,
                                      BigDecimal chargesSalarialesAnnuelles) {

        if (sbrMensuel == null || sbrMensuel.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal chargesAnnuelles = chargesSalarialesAnnuelles != null
                ? chargesSalarialesAnnuelles
                : BigDecimal.ZERO;

        // Salaire brut annuel
        BigDecimal sbrAnnuel = sbrMensuel.multiply(DOUZE);

        // Abattement forfaitaire 30%, plafonné à 3 000 000
        BigDecimal abattement = sbrAnnuel.multiply(TAUX_ABATTEMENT);
        if (abattement.compareTo(PLAFOND_ABATTEMENT) > 0) {
            abattement = PLAFOND_ABATTEMENT;
        }

        // BAIRPP = SBR annuel - abattement - charges salariales annuelles
        BigDecimal bairpp = sbrAnnuel.subtract(abattement).subtract(chargesAnnuelles);

        // Jamais négative
        if (bairpp.compareTo(BigDecimal.ZERO) < 0) {
            bairpp = BigDecimal.ZERO;
        }

        return bairpp.setScale(0, RoundingMode.FLOOR);
    }
}
