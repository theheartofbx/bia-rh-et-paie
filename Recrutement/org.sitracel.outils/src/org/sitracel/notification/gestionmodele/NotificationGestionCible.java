package org.sitracel.notification.gestionmodele;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sitracel.bean.BeanParametreNotificationCible;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.enumeration.NotificationCible;

public final class NotificationGestionCible {

    private NotificationGestionCible() {
        // utilitaire
    }

    /**
     * Résout les BPartner_ID pour UNE cible donnée
     * → UNE SEULE requête max
     */
    public static List<Integer> traiterCible(
            NotificationCible cible,
            List<Integer> cibleIds,
            BeanParametreNotificationCible parametreNotificationCible
    ) {

        if (cible == null) {
            return Collections.emptyList();
        }

        switch (cible) {

            case SUPERIEUR:
                return traiterSuperieurs(parametreNotificationCible.getReferenceBPartnerId());

            case EMPLOYE:
                return traiterEmploye(parametreNotificationCible.getReferenceBPartnerId());

            case EMETTEUR:
                return traiterEmetteur(parametreNotificationCible.getEmetteurBPartnerId());

            case ROLE:
                return traiterRoles(cibleIds);

            case CATEGORIE_RESP:
                return traiterCategories(cibleIds,
                		parametreNotificationCible.getDateReference());

            default:
                return Collections.emptyList();
        }
    }

    /* ==========================
     * RESOLVERS CONCRETS
     * ========================== */

    private static List<Integer> traiterSuperieurs(
            int referenceBPartnerId
    ) {

        if (referenceBPartnerId <= 0) {
            return Collections.emptyList();
        }

        return NotificationGestionDestinataireControler.safeList(
            GeneralController.getSuperieursHierarchiques(
                referenceBPartnerId
            )
        );
    }

    private static List<Integer> traiterEmploye(
            int bpartnerId
    ) {

        if (bpartnerId <= 0) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<Integer>();
        result.add(bpartnerId);
        return result;
    }

    private static List<Integer> traiterEmetteur(
            int bpartnerId
    ) {

        if (bpartnerId <= 0) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<Integer>();
        result.add(bpartnerId);
        return result;
    }
    
    private static List<Integer> traiterRoles(
            List<Integer> roleIds
    ) {

        if (roleIds == null || roleIds.isEmpty()) {
            return Collections.emptyList();
        }

        return NotificationGestionDestinataireControler.safeList(
            GeneralSqlController.getEmployeesByRoles(roleIds)
        );
    }

    private static List<Integer> traiterCategories(
            List<Integer> categorieIds, Timestamp dateReference
    ) {

        if (categorieIds == null || categorieIds.isEmpty() || dateReference == null) {
            return Collections.emptyList();
        }

        return NotificationGestionDestinataireControler.safeList(
            GeneralSqlController.getEmployeesByCategoriesResponsabilite(
                categorieIds,
                dateReference
            )
        );
    }
}
