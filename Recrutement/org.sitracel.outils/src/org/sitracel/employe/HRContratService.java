package org.sitracel.employe;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
 * Depuis cette session : ajout de isUserRH(), déplacée depuis
 * MissionCalloutRepository — un ModelValidator (mission.modelvalidator)
 * en a besoin sans pouvoir dépendre du bundle callout (mauvais sens de
 * dépendance), donc centralisée ici comme le reste des vérifications
 * de droits transversales.
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
     * Retourne l'ID du département du poste actuel d'un employé, ou null
     * si l'employé n'a pas d'affectation active ou si son poste n'a pas
     * de département renseigné.
     */
    public static Integer getDepartementActuel(int bpartnerId, Timestamp dateReference, String trxName) {
        return HRContratRepository.getDepartementActuel(Env.getCtx(), bpartnerId, dateReference, trxName);
    }

    /**
     * Retourne les C_BPartner_ID des employés appartenant au même
     * département qu'un employé donné, à une date de référence.
     */
    public static List<Integer> getBPartnersMemeDepartement(int bpartnerId, Timestamp dateReference, String trxName) {
        Integer departementId = getDepartementActuel(bpartnerId, dateReference, trxName);
        if (departementId == null) {
            return new ArrayList<>();
        }
        return HRContratRepository.getBPartnersMemeDepartement(departementId, dateReference, trxName);
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

    /**
     * Indique si l'utilisateur porte, parmi TOUS ses rôles assignés
     * (pas seulement le rôle courant de la session), un rôle RH.
     *
     * Différent de beneficieDerogationEligibilite() qui vérifie un seul
     * AD_Role_ID donné (typiquement le rôle courant) — ici on scanne
     * tous les rôles de l'utilisateur.
     */
    public static boolean isUserRH(int adUserId, String trxName) {
        if (adUserId <= 0) {
            return false;
        }

        String sql =
            "SELECT 1 "
            + "FROM AD_User_Roles ur "
            + "JOIN AD_Role r ON r.AD_Role_ID = ur.AD_Role_ID "
            + "WHERE ur.AD_User_ID = ? "
            + "AND r.Name IN ('Ressource Humaine', 'Ressource Humaine - Responsable')";

        return DB.getSQLValue(trxName, sql, adUserId) == 1;
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
