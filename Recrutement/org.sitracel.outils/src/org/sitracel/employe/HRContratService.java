package org.sitracel.employe;

import java.sql.Timestamp;

import org.compiere.util.Env;
import org.sitracel.contrat.model.MHRAffectation;
import org.sitracel.contrat.model.MHRContrat;

/**
 * Service transversal — éligibilité contrat/affectation d'un employé.
 *
 * C'est la SEULE porte d'entrée que les modules métier (congé, discipline,
 * mission, recrutement) doivent utiliser pour savoir si un employé est
 * "actif" à une date donnée. Ils ne doivent jamais interroger directement
 * HR_Contrat ou HR_Affectation.
 *
 * Remplace à terme HREmployeService.getDateDernierContrat() (heuristique
 * fragile basée sur HR_ElementBasePaieEmploye) — ce remplacement se fera
 * à l'Étape 6 du plan, une fois ce service éprouvé.
 */
public final class HRContratService {

    private HRContratService() {}

    /**
     * Un employé est éligible si et seulement si il a, à la date donnée :
     *   1. un contrat avec le statut "Actif"
     *   2. ET une affectation à un poste
     */
    public static boolean estEligible(int bpartnerId, Timestamp dateReference, String trxName) {
        return getContratActif(bpartnerId, dateReference, trxName) != null
            && getAffectationActive(bpartnerId, dateReference, trxName) != null;
    }

    public static MHRContrat getContratActif(int bpartnerId, Timestamp dateReference, String trxName) {
        return HRContratRepository.getContratActif(Env.getCtx(), bpartnerId, dateReference, trxName);
    }

    public static MHRAffectation getAffectationActive(int bpartnerId, Timestamp dateReference, String trxName) {
        return HRContratRepository.getAffectationActive(Env.getCtx(), bpartnerId, dateReference, trxName);
    }

    /**
     * Retourne un message explicite pour l'utilisateur quand
     * estEligible() renvoie false. Ne renvoie jamais une "boîte noire".
     *
     * @return null si l'employé est éligible (pas de motif de blocage)
     */
    public static String getMotifInaligibilite(int bpartnerId, Timestamp dateReference, String trxName) {
        MHRContrat contrat = getContratActif(bpartnerId, dateReference, trxName);
        if (contrat == null) {
            return "Aucun contrat actif à cette date.";
        }

        MHRAffectation affectation = getAffectationActive(bpartnerId, dateReference, trxName);
        if (affectation == null) {
            return "Contrat actif mais aucune affectation à un poste à cette date.";
        }

        return null;
    }
}
