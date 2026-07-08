package org.sitracel.conge.model;

/**
 * Constantes des statuts possibles d'un conge (table HR_CongeStatut).
 *
 * IDs fixes, crees Session 9 - ne jamais recreer ces lignes de donnees
 * sous d'autres identifiants sans mettre a jour cette classe.
 */
public final class CongeStatut {

    private CongeStatut() {}

    public static final int EMIS        = 1000000;
    public static final int APPROUVE    = 1000001;
    public static final int DESAPPROUVE = 1000002;
    public static final int VALIDE      = 1000003;
    public static final int REJETE      = 1000004;
}
