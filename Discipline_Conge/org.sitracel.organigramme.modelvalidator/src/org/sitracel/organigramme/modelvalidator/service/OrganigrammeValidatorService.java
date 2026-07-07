package org.sitracel.organigramme.modelvalidator.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sitracel.organigramme.model.MHROrganigramme;

/**
 * Logique métier du ModelValidator Organigramme.
 *
 * Chaque méthode de validation renvoie :
 *   - null si tout est correct (l'enregistrement peut continuer)
 *   - un message d'erreur explicite si l'enregistrement doit être bloqué
 */
public class OrganigrammeValidatorService {

    /** Garde-fou : profondeur maximale de remontée, au cas où des
     * données existantes contiendraient déjà une boucle imprévue. */
    private static final int PROFONDEUR_MAX = 50;

    private OrganigrammeValidatorService() {}

    public static String validerLien(MHROrganigramme lien) {
        if (lien.getPoste_ID() <= 0 || lien.getPoste_Responsable_ID() <= 0) {
            return null;
        }

        int posteId = lien.getPoste_ID();
        int posteResponsableId = lien.getPoste_Responsable_ID();
        int categorieId = lien.getHR_Categorie_Responsabilite_ID();
        int ligneId = lien.getHR_Organigramme_ID();
        String trxName = lien.get_TrxName();

        if (posteId == posteResponsableId) {
            return "Un poste ne peut pas être son propre responsable.";
        }

        if (OrganigrammeValidatorRepository.existeDoublon(
                posteId, posteResponsableId, categorieId, ligneId, trxName)) {
            return "Ce lien existe déjà : ce poste a déjà ce même poste "
                + "responsable pour cette catégorie de responsabilité.";
        }

        if (creeUneBoucle(posteResponsableId, posteId, ligneId, trxName)) {
            return "Ce lien créerait une boucle dans l'organigramme : en "
                + "remontant la hiérarchie depuis le poste responsable, "
                + "on retomberait sur le poste de départ.";
        }

        return null;
    }

    /**
     * Vérifie si, en remontant la hiérarchie depuis posteDepart,
     * on retombe sur posteRecherche (le poste qu'on est en train
     * de rattacher). Explore toutes les branches (un poste peut avoir
     * plusieurs responsables, un par catégorie).
     */
    private static boolean creeUneBoucle(int posteDepart, int posteRecherche,
                                          int excludeId, String trxName) {
        return remonteContientPoste(
            posteDepart, posteRecherche, new HashSet<>(), excludeId, trxName, 0);
    }

    private static boolean remonteContientPoste(int courant, int posteRecherche,
                                                 Set<Integer> visites, int excludeId,
                                                 String trxName, int profondeur) {
        if (courant == posteRecherche) {
            return true;
        }
        if (profondeur >= PROFONDEUR_MAX || !visites.add(courant)) {
            return false;
        }

        List<Integer> responsables = OrganigrammeValidatorRepository
            .getPostesResponsables(courant, excludeId, trxName);

        for (Integer suivant : responsables) {
            if (remonteContientPoste(
                    suivant, posteRecherche, visites, excludeId, trxName, profondeur + 1)) {
                return true;
            }
        }
        return false;
    }
}
