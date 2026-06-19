package org.sitracel.employe;

import java.sql.Timestamp;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.compiere.util.Env;
import org.sitracel.bean.BeanConge;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.model.MCBPartner;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;

/**
 * Service transversal — données RH des employés.
 *
 * Regroupe les opérations métier liées aux employés, à leur hiérarchie
 * et à leur historique contractuel.
 *
 * Remplace les méthodes employé de GeneralController.
 */
public final class HREmployeService {

    private HREmployeService() {}

    // =========================================================================
    // IDENTIFICATION
    // =========================================================================

    /**
     * Retourne l'identifiant complet d'un employé à partir de son AD_User_ID.
     */
    public static BeanIdentifiant getIdentifiant(Integer adUserId, String trxName) {
        if (adUserId == null) return null;
        return MCBPartner.getIdentifiant(adUserId, trxName);
    }

    /**
     * Retourne l'identifiant complet d'un employé à partir de son C_BPartner_ID.
     */
    public static BeanIdentifiant getIdentifiantByBPartner(Integer bpartnerId, String trxName) {
        if (bpartnerId == null) return null;
        return MCBPartner.getIdentifiantByBPartner(bpartnerId, trxName);
    }

    // =========================================================================
    // CONTRAT
    // =========================================================================

    /**
     * Retourne le dernier contrat actif d'un employé à une date donnée.
     * Un contrat est "le dernier" si le contrat précédent a une date de fin.
     */
    public static MHRElementBasePaieEmploye getDateDernierContrat(
            Integer bpartnerId, Timestamp dateMax) {

        if (bpartnerId == null || dateMax == null) return null;

        List<MHRElementBasePaieEmploye> listeContrats =
            HREmployeRepository.getDatesDerniersContrats(bpartnerId, dateMax, null);

        if (listeContrats == null || listeContrats.isEmpty()) return null;

        for (int i = 0; i < listeContrats.size(); i++) {
            MHRElementBasePaieEmploye contratActuel = listeContrats.get(i);
            if (i + 1 < listeContrats.size()) {
                MHRElementBasePaieEmploye contratPrecedent = listeContrats.get(i + 1);
                if (contratPrecedent.getDate_Fin() != null) {
                    return contratActuel;
                }
            } else {
                return contratActuel;
            }
        }
        return null;
    }

    /**
     * Calcule et injecte les informations d'ancienneté dans un BeanConge.
     */
    public static BeanConge setAnciennete(BeanConge beanConge,
                                          Timestamp dateDebut,
                                          Timestamp dateFin) {
        if (beanConge == null || dateDebut == null || dateFin == null) {
            return beanConge;
        }

        var date1 = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        var date2 = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period periode = Period.between(date1, date2);

        beanConge.setAnneeAnciennete(periode.getYears());
        beanConge.setMoisAnciennete(
            (int) java.time.temporal.ChronoUnit.MONTHS.between(date1, date2));
        beanConge.setJourAnciennete(
            (int) java.time.temporal.ChronoUnit.DAYS.between(date1, date2));

        return beanConge;
    }

    // =========================================================================
    // HIÉRARCHIE
    // =========================================================================

    /**
     * Retourne tous les supérieurs hiérarchiques d'un employé
     * (remonte l'organigramme tous niveaux, tous postes).
     */
    public static List<Integer> getSuperieursHierarchiques(Integer bpartnerId) {
        Set<Integer> result   = new LinkedHashSet<>();
        Set<Integer> visites  = new HashSet<>();

        if (bpartnerId == null) return new ArrayList<>();

        Integer posteInitial = HREmployeRepository.getCurrentJobId(bpartnerId);
        if (posteInitial == null) return new ArrayList<>();

        explorerHierarchie(posteInitial, visites, result);
        return new ArrayList<>(result);
    }

    /**
     * Retourne les supérieurs hiérarchiques filtrés par catégorie de responsabilité.
     */
    public static List<Integer> getSuperieursHierarchiquesParCategorie(
            Integer bpartnerId, Integer categorieResponsabiliteId) {

        Set<Integer> result  = new LinkedHashSet<>();
        Set<Integer> visites = new HashSet<>();

        if (bpartnerId == null || categorieResponsabiliteId == null) return new ArrayList<>();

        Integer posteInitial = HREmployeRepository.getCurrentJobId(bpartnerId);
        if (posteInitial == null) return new ArrayList<>();

        explorerHierarchieParCategorie(posteInitial, categorieResponsabiliteId, visites, result);
        return new ArrayList<>(result);
    }

    // =========================================================================
    // UTILITAIRES INTERNES — exploration hiérarchique
    // =========================================================================

    private static void explorerHierarchie(Integer posteCourant,
                                            Set<Integer> visites,
                                            Set<Integer> result) {
        if (posteCourant == null || visites.contains(posteCourant)) return;
        visites.add(posteCourant);

        List<Integer> postesResponsables =
            HREmployeRepository.getPostesResponsables(posteCourant);

        for (Integer posteResponsable : postesResponsables) {
            List<Integer> responsables =
                HREmployeRepository.getEmployeesByJob(posteResponsable);
            result.addAll(responsables);
        }
    }

    private static void explorerHierarchieParCategorie(Integer posteCourant,
                                                        Integer categorieId,
                                                        Set<Integer> visites,
                                                        Set<Integer> result) {
        if (posteCourant == null || categorieId == null
                || visites.contains(posteCourant)) return;
        visites.add(posteCourant);

        List<Integer> postesResponsables =
            HREmployeRepository.getPostesResponsablesParCategorie(posteCourant, categorieId);

        for (Integer posteResponsable : postesResponsables) {
            List<Integer> responsables =
                HREmployeRepository.getEmployeesByJob(posteResponsable);
            result.addAll(responsables);
        }
    }
}
