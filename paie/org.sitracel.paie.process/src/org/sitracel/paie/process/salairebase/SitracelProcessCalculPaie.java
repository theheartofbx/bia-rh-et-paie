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
    private int moisId = 0; // ID dans hr_mois
    private int annee      = 0;

    @Override
    protected void prepare() {
        for (ProcessInfoParameter para : getParameter()) {
            String name = para.getParameterName();
            if ("C_BPartner_ID".equals(name)) {
                bpartnerId = para.getParameterAsInt();
            } else if ("Mois".equals(name)) {
                moisId = para.getParameterAsInt();
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
        if (moisId <= 0) {
            throw new AdempiereUserError(
                    "Veuillez sélectionner un mois valide.");
        }
        // Lire la valeur numérique du mois depuis hr_mois
        int mois = org.compiere.util.DB.getSQLValue(get_TrxName(),
                "SELECT valeur_integer FROM adempiere.hr_mois WHERE hr_mois_id=?", moisId);
        if (mois <= 0) {
            throw new AdempiereUserError("Mois introuvable dans hr_mois (ID=" + moisId + ").");
        }

        if (annee < 1980 || annee > 2100) {
            throw new AdempiereUserError(
                    "L'année saisie (" + annee + ") est invalide. Veuillez saisir une année entre 1980 et 2100.");
        }

        // Jour de début de période
        int jourDebut = PeriodeSalarialeService.getParametre(
                "PERIODE_JOUR_DEBUT", 16, get_TrxName());

        // La période Juillet va du 16 juillet au 15 août
        // Donc on passe le 16 du mois sélectionné comme date de référence
        LocalDate dateReference = LocalDate.of(annee, mois, jourDebut);
        Timestamp tsReference = Timestamp.valueOf(dateReference.atStartOfDay());

        // Garantir que la période existe
        MHRPeriodeSalariale periode = PeriodeSalarialeService.garantirPeriodePourDate(
                tsReference, get_TrxName());

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
