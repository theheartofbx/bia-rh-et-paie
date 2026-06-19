package org.sitracel.mission.callout;

import org.sitracel.mission.callout.service.MissionCalloutService;
import org.sitracel.mission.model.MHRMissionFrais;

/**
 * @deprecated Utiliser {@link MissionCalloutService} à la place.
 *
 * Conservé pour compatibilité descendante.
 * Délègue intégralement à MissionCalloutService.
 * Ne pas ajouter de nouvelles méthodes ici.
 */
@Deprecated
public class ControlerMission {

    /** @deprecated Utiliser {@link MissionCalloutService#recalculerEtatMission} */
    @Deprecated
    public static void recalculerEtatMission(int hrMissionId, String trxName) {
        MissionCalloutService.recalculerEtatMission(hrMissionId, trxName);
    }

    /** @deprecated Utiliser {@link MissionCalloutService#majValidationRejet} */
    @Deprecated
    public static void majValidationRejet(MHRMissionFrais frais) {
        MissionCalloutService.majValidationRejet(frais);
    }
}
