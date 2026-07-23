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
 * Process iDempiere — Calcul de la paie pour TOUS les employés actifs.
 *
 * Paramètres :
 *   - Mois  (Integer, 1-12)
 *   - Annee (Integer, ex: 2026)
 *
 * Le process déduit la période salariale correspondante,
 * la crée si elle n'existe pas, puis lance le calcul.
 */
public class SitracelProcessCalculPaieTous extends SvrProcess {

    private int mois  = 0;
    private int annee = 0;

    @Override
    protected void prepare() {
        for (ProcessInfoParameter para : getParameter()) {
            String name = para.getParameterName();
            if ("Mois".equals(name)) {
                mois = para.getParameterAsInt();
            } else if ("Annee".equals(name)) {
                annee = para.getParameterAsInt();
            }
        }
    }

    @Override
    protected String doIt() throws Exception {

        if (mois < 1 || mois > 12) {
            throw new AdempiereUserError(
                    "Veuillez sélectionner un mois valide (1-12).");
        }
        if (annee < 2000 || annee > 2100) {
            throw new AdempiereUserError(
                    "Veuillez saisir une année valide (ex: 2026).");
        }

        // Jour de début de période (paramètre PERIODE_JOUR_DEBUT, défaut 16)
        int jourDebut = PeriodeSalarialeService.getParametre(
                "PERIODE_JOUR_DEBUT", 16, get_TrxName());

        // La période du mois M commence le jourDebut du mois M-1
        // Ex: Juillet 2026 = du 16 juin 2026 au 15 juillet 2026
        LocalDate dateDebutPeriode;
        if (mois == 1) {
            dateDebutPeriode = LocalDate.of(annee - 1, 12, jourDebut);
        } else {
            dateDebutPeriode = LocalDate.of(annee, mois - 1, jourDebut);
        }

        Timestamp tsDebut = Timestamp.valueOf(dateDebutPeriode.atStartOfDay());

        // Garantir que la période existe (la crée si absente)
        MHRPeriodeSalariale periode = PeriodeSalarialeService.garantirPeriodePourDate(
                tsDebut, get_TrxName());

        if (periode == null || periode.getHR_Periode_Salariale_ID() <= 0) {
            throw new AdempiereUserError(
                    "Impossible de trouver ou créer la période salariale pour "
                    + mois + "/" + annee + ".");
        }

        log.info("Période : " + periode.getName()
                + " (ID=" + periode.getHR_Periode_Salariale_ID() + ")");

        return PayrollOrchestrator.calculerPaieTousEmployes(
                periode.getHR_Periode_Salariale_ID());
    }
}
