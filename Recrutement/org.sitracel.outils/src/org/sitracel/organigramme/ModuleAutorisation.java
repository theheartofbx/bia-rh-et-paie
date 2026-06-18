package org.sitracel.organigramme;

/**
 * Modules disposant d'une table d'autorisation propre.
 *
 * Chaque module définit :
 *   - La table d'autorisation à interroger
 *   - La colonne identifiant le type de document
 *
 * Pour ajouter un nouveau module (ex: Mission, Recrutement) :
 *   1. Ajouter une entrée ici avec la table et la colonne type
 *   2. S'assurer que la table contient IsEmission/IsApprobation/IsValidation
 *   3. C'est tout — OrganigrammeService.getActeurs() fonctionne automatiquement
 */
public enum ModuleAutorisation {

    CONGE(
        "HR_Autorisation_Conge",
        "HR_Type_Conge_ID"
    ),

    SANCTION(
        "HR_Sanction_Autorisation",
        "HR_TypeSanction_ID"
    ),

    ABSENCE(
        "HR_Autorisation_Absence",
        "HR_Type_Absence_ID"
    );

    /*
     * MISSION — à décommenter quand la table d'autorisation sera créée :
     * MISSION("HR_Autorisation_Mission", "HR_Type_Mission_ID"),
     *
     * RECRUTEMENT — à décommenter quand la table sera créée :
     * RECRUTEMENT("HR_Autorisation_Recrutement", "HR_Type_Recrutement_ID"),
     */

    private final String tableAutorisation;
    private final String colonneType;

    ModuleAutorisation(String tableAutorisation, String colonneType) {
        this.tableAutorisation = tableAutorisation;
        this.colonneType = colonneType;
    }

    public String getTableAutorisation() { return tableAutorisation; }
    public String getColonneType()       { return colonneType; }
}
