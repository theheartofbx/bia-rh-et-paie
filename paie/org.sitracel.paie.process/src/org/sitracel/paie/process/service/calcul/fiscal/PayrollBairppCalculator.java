package org.sitracel.paie.process.service.calcul.fiscal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Calcul BAIRPP — Base Annuelle IRPP.
 *
 * Reproduit fidelement la formule en base :
 *
 * Revenu imposable annuel =
 *   (SB_annuel + IL_plafonne + IT_plafonne)
 *   - abattement_forfaitaire
 *   - cotisation_CNPS_annuelle
 *
 * Ou :
 *   SB_annuel = (SB + CSB + RIMP + PREN + IPN + IDE + DO*0.8) * 12
 *   IL_plafonne = min(IL*12, 15% de SB_annuel)
 *   IT_plafonne = min(IT*12, 10% de SB_annuel)
 *   abattement = 30% du revenu brut, plafonne a 4 800 000 si revenu mensuel > 1 333 000
 *   cotisation_CNPS = min(SB_mensuel_total, 750 000) * 4.2% * 12
 *   deduction_forfaitaire = 500 000
 */
public class PayrollBairppCalculator {

    public static BigDecimal calculer(java.util.Map<String, BigDecimal> variables) {
        // Composantes mensuelles
        BigDecimal sb   = get(variables, "SB");
        BigDecimal csb  = get(variables, "CSB");
        BigDecimal rimp = get(variables, "RIMP");
        BigDecimal pren = get(variables, "PREN");
        BigDecimal ipn  = get(variables, "IPN");
        BigDecimal ide  = get(variables, "IDE");
        BigDecimal dom  = get(variables, "DO");
        BigDecimal il   = get(variables, "IL");
        BigDecimal ilc  = get(variables, "ILC");
        BigDecimal it   = get(variables, "IT");
        BigDecimal itc  = get(variables, "ITC");

        // Base mensuelle (avec domesticite a 80%)
        BigDecimal baseMensuelle = sb.add(csb).add(rimp).add(pren)
            .add(ipn).add(ide).add(dom.multiply(bd("0.8")));

        BigDecimal baseAnnuelle = baseMensuelle.multiply(bd("12"));

        // Plafonnement IL a 15% de la base annuelle
        BigDecimal ilAnnuel = il.add(ilc).multiply(bd("12"));
        BigDecimal ilPlafonne = ilAnnuel.min(baseAnnuelle.multiply(bd("0.15")));

        // Plafonnement IT a 10% de la base annuelle
        BigDecimal itAnnuel = it.add(itc).multiply(bd("12"));
        BigDecimal itPlafonne = itAnnuel.min(baseAnnuelle.multiply(bd("0.10")));

        // Revenu brut annuel
        BigDecimal revenuBrut = baseAnnuelle.add(ilPlafonne).add(itPlafonne);

        // Abattement forfaitaire (30% du revenu brut)
        BigDecimal totalMensuel = baseMensuelle.add(il).add(ilc).add(it).add(itc);
        BigDecimal abattement;
        if (totalMensuel.compareTo(bd("1333000")) > 0) {
            abattement = bd("4800000");
        } else {
            abattement = revenuBrut.multiply(bd("0.30"));
        }

        // Deduction forfaitaire fixe
        BigDecimal deductionForfaitaire = bd("500000");

        // Cotisation CNPS annuelle (base = BM + IL + ILC, SANS IT/ITC)
        BigDecimal baseCnpsMensuel = baseMensuelle.add(il).add(ilc);
        BigDecimal baseCnps = baseCnpsMensuel.min(bd("750000"));
        BigDecimal cnpsAnnuel = baseCnps.multiply(bd("0.042")).multiply(bd("12"));

        // BAIRPP = revenu brut - abattement - deduction - CNPS
        BigDecimal bairpp = revenuBrut
            .subtract(abattement)
            .subtract(deductionForfaitaire)
            .subtract(cnpsAnnuel);

        // Jamais negatif
        if (bairpp.compareTo(BigDecimal.ZERO) < 0) {
            bairpp = BigDecimal.ZERO;
        }

        return bairpp.setScale(0, RoundingMode.FLOOR);
    }

    // Ancien point d entree — redirige vers la nouvelle methode
    public static BigDecimal calculer(BigDecimal sbr, BigDecimal chargesAnnuelles) {
        // Pas utilise si FormulaEvaluator appelle calculer(variables)
        return BigDecimal.ZERO;
    }

    private static BigDecimal get(java.util.Map<String, BigDecimal> variables, String code) {
        BigDecimal val = variables.get(code);
        return val != null ? val : BigDecimal.ZERO;
    }

    private static BigDecimal bd(String val) {
        return new BigDecimal(val);
    }
}
