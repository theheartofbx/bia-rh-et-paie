package org.sitracel.organigramme;

/**
 * Actions qu'une catégorie de responsabilité peut exercer
 * sur un type de document donné.
 *
 * La combinaison (TypeDocument + Categorie + Action) est définie
 * dans les tables d'autorisation de chaque module :
 *   HR_Autorisation_Conge      → congés
 *   HR_Sanction_Autorisation   → sanctions
 *   HR_Autorisation_Absence    → absences
 *
 * Exemple :
 *   TypeConge="Annuel" + Categorie3 + VALIDATION = Y
 *   → la Catégorie 3 peut valider un congé annuel
 */
public enum ActionOrganigramme {

    /** Peut créer / émettre le document */
    EMISSION,

    /** Peut approuver (1er niveau de décision) */
    APPROBATION,

    /** Peut valider définitivement (niveau final) */
    VALIDATION,

    /** Peut gérer la compensation congé */
    COMPENSATION,

    /** Reçoit l'information sans pouvoir de décision (CC/BCC) */
    INFORMATION
}
