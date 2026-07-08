package org.sitracel.employe;

import java.sql.Timestamp;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
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
 *
 * Depuis Session 9 : ajout de la dérogation par rôle (utilisée par
 * Congé, et prévue pour Discipline selon le même principe).
 */
public final class HRContratService {

    private static final CLogger log = CLogger.getCLogger(HRContratService.class);

    /**
     * Noms des rôles bénéficiant d'une dérogation à l'éligibilité
     * contrat/affectation — comparaison par NOM, jamais par ID
     * (les ID de rôle diffèrent d'un serveur à l'autre, le nom doit
     * rester cohérent entre le serveur personnel et celui du client).
     */
    private static final String[] ROLES_DEROGATION_ELIGIBILITE = {
        "Ressource Humaine",
        "Ressource Humaine - Responsable"
    };

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

    /**
     * Indique si le rôle donné bénéficie d'une dérogation à
     * l'éligibilité (ex : RH peut créer un congé pour un employé
     * techniquement non éligible, pour régulariser une situation).
     *
     * Comparaison par nom de rôle, jamais par AD_Role_ID.
     */
    public static boolean beneficieDerogationEligibilite(int adRoleId, String trxName) {
        if (adRoleId <= 0) {
            return false;
        }

        String nomRole = getNomRole(adRoleId, trxName);
        if (nomRole == null) {
            return false;
        }

        for (String roleAutorise : ROLES_DEROGATION_ELIGIBILITE) {
            if (roleAutorise.equalsIgnoreCase(nomRole)) {
                return true;
            }
        }
        return false;
    }

    private static String getNomRole(int adRoleId, String trxName) {
        String sql = "SELECT Name FROM AD_Role WHERE AD_Role_ID = ?";
        try {
            return DB.getSQLValueStringEx(trxName, sql, adRoleId);
        } catch (Exception erreurCatch) {
            log.warning("getNomRole : " + erreurCatch.getMessage());
            return null;
        }
    }
}
