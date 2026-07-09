package org.sitracel.conge.callout.conge.service;

import java.sql.Timestamp;

import org.compiere.util.Env;
import org.sitracel.bean.BeanInfoCongeDepartement;
import org.sitracel.bean.BeanPeriodeConge;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.time.HRCalendrierService;

/**
 * Service — logique métier du callout congé.
 * Remplace CalloutControllerConge.
 */
public final class CongeCalloutService {

    private CongeCalloutService() {}

    // =========================================================================
    // DISPONIBILITÉ DÉPARTEMENT
    // =========================================================================

    public static BeanInfoCongeDepartement getPeriodeCongeCritique(Integer bpartnerId,
                                                                    Timestamp dateDebut,
                                                                    Timestamp dateFin) {
        BeanInfoCongeDepartement bean = BeanFactory.getBeanInfoCongeDepartement();
        if (bpartnerId == null || dateDebut == null || dateFin == null) return bean;

        int nbTotal   = CongeCalloutRepository.getNombreEmployeDepartement(bpartnerId, dateDebut, null);
        int nbEnConge = CongeCalloutRepository.getNombreEmployeDepartementEnConge(
                            bpartnerId, dateDebut, dateFin, null);
        Timestamp jourCritique = CongeCalloutRepository.getJourCritiqueDepartement(
                            bpartnerId, dateDebut, dateFin, null);

        bean.setNombreEmployeDepartement(nbTotal);
        bean.setNombreEmployeDepartementConge(nbEnConge);
        bean.setJourCritique(jourCritique);

        double pourcentage = nbTotal > 0
                ? ((double) nbEnConge / nbTotal) * 100.0
                : 0.0;
        bean.setPourcentageEmployeConge(pourcentage);

        return bean;
    }

    // =========================================================================
    // CONGÉ MATERNITÉ
    // =========================================================================

    public static BeanPeriodeConge getPeriodeCongeMaternite(Timestamp dateEcheance,
                                                             Integer idTypeConge) {
        BeanPeriodeConge resultat = BeanFactory.getBeanPeriodeConge();
        if (dateEcheance == null || idTypeConge == null) return resultat;

        MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), idTypeConge, null);
        int periodeApres       = typeConge.getNombre_Jour_Après_Echeance();
        int periodeAvant       = typeConge.getNombre_Jour_Avant_Echeance();

        Timestamp dateDebut = HRCalendrierService.retirerJoursOuvrables(dateEcheance, periodeAvant);
        dateDebut           = HRCalendrierService.ajusterEnRetirant(dateDebut);

        Timestamp dateFin   = HRCalendrierService.ajouterJoursOuvrables(dateEcheance, periodeApres);
        dateFin             = HRCalendrierService.ajusterEnAjoutant(dateFin);

        resultat.setDateDebutConge(dateDebut);
        resultat.setDateFinConge(dateFin);

        return resultat;
    }

    // =========================================================================
    // AMPLIATION
    // =========================================================================

    public static String getAmpliation(String ampliation, String initial, String annee) {
        if (ampliation == null || initial == null) return ampliation;

        ampliation = ampliation.replaceAll("\\s+", "");

        if (ampliation.length() > 2) {
            ampliation = ampliation.replaceAll(
                "/" + ampliation.substring(ampliation.length() - 2), "");
        }

        if (!ampliation.isEmpty()) {
            if (ampliation.contains("/" + initial)) {
                ampliation = ampliation.replaceAll("/" + initial, "");
            } else {
                ampliation = ampliation + "/" + initial;
            }
        } else {
            ampliation = ampliation + "/" + initial;
        }

        if (annee != null && annee.length() > 2) {
            ampliation = ampliation + "/" + annee.substring(annee.length() - 2);
        }

        return ampliation;
    }
}
