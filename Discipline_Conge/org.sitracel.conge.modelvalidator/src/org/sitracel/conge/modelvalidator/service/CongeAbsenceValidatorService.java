package org.sitracel.conge.modelvalidator.service;

/**
 * Service — logique métier restante côté Congé.
 *
 * Depuis Session 9, toute la logique Absence (seuil de demandes
 * d'explication, compensation) vit dans
 * org.sitracel.absence.modelvalidator.service.AbsenceValidatorService.
 * Ne pas la réintroduire ici.
 */
public final class CongeAbsenceValidatorService {

    private CongeAbsenceValidatorService() {}

    /**
     * Met à jour le département de l'employé dans les congés en cours
     * lorsque son poste/contrat change.
     */
    public static void updateDepartment(Integer bpartnerId) {
        if (bpartnerId == null) return;
        // Géré directement par iDempiere via les FK — aucune action
        // supplémentaire nécessaire dans cette version.
    }
}
