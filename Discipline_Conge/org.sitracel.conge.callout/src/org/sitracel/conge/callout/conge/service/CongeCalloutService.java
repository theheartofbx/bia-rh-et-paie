package org.sitracel.conge.callout.conge.service;

import java.sql.Timestamp;

import org.sitracel.bean.BeanInfoCongeDepartement;
import org.sitracel.beanfactory.BeanFactory;

/**
 * Service — logique métier du callout congé.
 *
 * Regroupe les calculs métier utilisés par les callouts congé :
 * disponibilité département, période critique, etc.
 *
 * Remplace CalloutControllerConge.
 */
public final class CongeCalloutService {

    private CongeCalloutService() {}

    // =========================================================================
    // DISPONIBILITÉ DÉPARTEMENT
    // =========================================================================

    /**
     * Calcule et retourne les informations de criticité département
     * pour une période de congé donnée.
     *
     * Remplace CalloutControllerConge.getPeriodeCongeCritique().
     *
     * @return BeanInfoCongeDepartement contenant :
     *   - nombre total d'employés dans le département
     *   - nombre d'employés déjà en congé sur la période
     *   - seuil de jours critiques du département
     *   - pourcentage d'employés en congé
     */
    public static BeanInfoCongeDepartement getPeriodeCongeCritique(Integer bpartnerId,
                                                                     Timestamp dateDebut,
                                                                     Timestamp dateFin) {
        BeanInfoCongeDepartement bean = BeanFactory.getBeanInfoCongeDepartement();

        if (bpartnerId == null || dateDebut == null || dateFin == null) return bean;

        int nbTotal = CongeCalloutRepository.getNombreEmployeDepartement(bpartnerId, null);
        int nbEnConge = CongeCalloutRepository.getNombreEmployeDepartementEnConge(
            bpartnerId, dateDebut, dateFin, null);
        int jourCritique = CongeCalloutRepository.getJourCritiqueDepartement(bpartnerId, null);

        bean.setNombreEmployeDepartement(nbTotal);
        bean.setNombreEmployeDepartementConge(nbEnConge);
        bean.setJourCritique(jourCritique);

        double pourcentage = nbTotal > 0
            ? ((double) nbEnConge / nbTotal) * 100.0
            : 0.0;
        bean.setPourcentageEmployeConge(pourcentage);

        return bean;
    }
}
