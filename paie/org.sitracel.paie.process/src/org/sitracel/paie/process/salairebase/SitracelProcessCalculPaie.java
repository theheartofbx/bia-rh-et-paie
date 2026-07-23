package org.sitracel.paie.process.salairebase;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.sitracel.paie.model.MHRPeriodeSalariale;
import org.sitracel.paie.process.service.PayrollOrchestrator;
import org.sitracel.paie.process.service.PeriodeSalarialeService;

/**
 * Process iDempiere — Calcul de la paie pour UN employé.
 *
 * Paramètres :
 *   - C_BPartner_ID (Integer) — employé
 *   - Mois  (Integer, 1-12)
 *   - Annee (Integer, ex: 2026)
 *
 * Le process déduit la période salariale, la crée si absente,
 * puis lance le calcul pour l'employé sélectionné.
 */
public class SitracelProcessCalculPaie extends SvrProcess {

    private int bpartnerId = 0;
    private int mois       = 0;
    private int annee      = 0;

    @Override
    protected void prepare() {
        for (ProcessInfoParameter para : getParameter()) {
            String name = para.getParameterName();
            if ("C_BPartner_ID".equals(name)) {
                bpartnerId = para.getParameterAsInt();
            } else if ("Mois".equals(name)) {
                mois = para.getParameterAsInt();
            } else if ("Annee".equals(name)) {
                annee = para.getParameterAsInt();
            }
        }
    }

    @Override
    protected String doIt() throws Exception {

        if (bpartnerId <= 0) {
            throw new AdempiereUserError(
                    "Veuillez sélectionner un employé avant de lancer le calcul.");
        }
        if (mois < 1 || mois > 12) {
            throw new AdempiereUserError(
                    "Veuillez sélectionner un mois valide (1-12).");
        }
        if (annee < 2000 || annee > 2100) {
            throw new AdempiereUserError(
                    "Veuillez saisir une année valide (ex: 2026).");
        }

        // Jour de début de période
        int jourDebut = PeriodeSalarialeService.getParametre(
                "PERIODE_JOUR_DEBUT", 16, get_TrxName());

        // Date de début de la période
        LocalDate dateDebutPeriode;
        if (mois == 1) {
            dateDebutPeriode = LocalDate.of(annee - 1, 12, jourDebut);
        } else {
            dateDebutPeriode = LocalDate.of(annee, mois - 1, jourDebut);
        }

        Timestamp tsDebut = Timestamp.valueOf(dateDebutPeriode.atStartOfDay());

        // Garantir que la période existe
        MHRPeriodeSalariale periode = PeriodeSalarialeService.garantirPeriodePourDate(
                tsDebut, get_TrxName());

        if (periode == null || periode.getHR_Periode_Salariale_ID() <= 0) {
            throw new AdempiereUserError(
                    "Impossible de trouver ou créer la période salariale pour "
                    + mois + "/" + annee + ".");
        }

        log.info("Période : " + periode.getName()
                + " (ID=" + periode.getHR_Periode_Salariale_ID() + ")");

        return PayrollOrchestrator.calculerPaie(
                bpartnerId, periode.getHR_Periode_Salariale_ID());
    }
}
