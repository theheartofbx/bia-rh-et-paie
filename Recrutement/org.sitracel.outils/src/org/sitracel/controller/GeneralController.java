package org.sitracel.controller;

import java.sql.Timestamp;
import java.util.List;

import org.sitracel.bean.BeanConge;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.conge.HRCongeService;
import org.sitracel.employe.HREmployeService;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.time.HRCalendrierService;

/**
 * @deprecated Utiliser les services dédiés à la place :
 *   - Calculs dates/jours  → {@link HRCalendrierService}
 *   - Données employés     → {@link HREmployeService}
 *   - Règles congés        → {@link HRCongeService}
 *
 * Cette classe est conservée pour la compatibilité descendante.
 * Elle délègue intégralement aux nouveaux services.
 * Ne pas ajouter de nouvelles méthodes ici.
 */
@Deprecated
public class GeneralController {

    // =========================================================================
    // DÉLÉGATION → HRCalendrierService
    // =========================================================================

    /** @deprecated Utiliser {@link HRCalendrierService#getNombreJourTravaille} */
    @Deprecated
    public static Integer getNombreJourTravaille(Timestamp dateDebut, Timestamp dateFin) {
        return HRCalendrierService.getNombreJourTravaille(dateDebut, dateFin);
    }

    /** @deprecated Utiliser {@link HRCalendrierService#getNombreJour} */
    @Deprecated
    public static Integer getNombreJour(Timestamp dateDebut, Timestamp dateFin) {
        return HRCalendrierService.getNombreJour(dateDebut, dateFin);
    }

    /** @deprecated Utiliser {@link HRCalendrierService#getNombreMois} */
    @Deprecated
    public static Integer getNombreMois(Timestamp dateDebut, Timestamp dateFin) {
        return HRCalendrierService.getNombreMois(dateDebut, dateFin);
    }

    /** @deprecated Utiliser {@link HRCalendrierService#ajouterJoursOuvrables} */
    @Deprecated
    public static Timestamp ajouterNombreJour(Timestamp date, int nombreJour) {
        return HRCalendrierService.ajouterJoursOuvrables(date, nombreJour);
    }

    /** @deprecated Utiliser {@link HRCalendrierService#retirerJoursOuvrables} */
    @Deprecated
    public static Timestamp retirerNombreJour(Timestamp date, int nombreJour) {
        return HRCalendrierService.retirerJoursOuvrables(date, nombreJour);
    }

    /** @deprecated Utiliser {@link HRCalendrierService#ajusterEnAjoutant} */
    @Deprecated
    public static Timestamp ajusterenAjoutant(Timestamp date) {
        return HRCalendrierService.ajusterEnAjoutant(date);
    }

    /** @deprecated Utiliser {@link HRCalendrierService#ajusterEnRetirant} */
    @Deprecated
    public static Timestamp ajusterenRetirant(Timestamp date) {
        return HRCalendrierService.ajusterEnRetirant(date);
    }

    /** @deprecated Utiliser {@link HRCalendrierService#getFirstDayOfYear} */
    @Deprecated
    public static Timestamp getFirstDayOfaYear(Timestamp anyDayOfYear) {
        return HRCalendrierService.getFirstDayOfYear(anyDayOfYear);
    }

    /** @deprecated Utiliser {@link HRCalendrierService#getLastDayOfYear} */
    @Deprecated
    public static Timestamp getLastDayOfaYear(Timestamp anyDayOfYear) {
        return HRCalendrierService.getLastDayOfYear(anyDayOfYear);
    }

    /** @deprecated Utiliser {@link HRCalendrierService#getFirstDayOfThisYear} */
    @Deprecated
    public static Timestamp getFirstDayOfThisYear() {
        return HRCalendrierService.getFirstDayOfThisYear();
    }

    /** @deprecated Utiliser {@link HRCalendrierService#getLastDayOfThisYear} */
    @Deprecated
    public static Timestamp getLastDayOfThisYear() {
        return HRCalendrierService.getLastDayOfThisYear();
    }

    /** @deprecated Utiliser {@link HRCalendrierService#isJourDisponible} */
    @Deprecated
    public static boolean isJourDisponible(Integer bpartnerID, Timestamp date) {
        return HRCalendrierService.isJourDisponible(bpartnerID, date);
    }

    /** @deprecated Utiliser {@link HRCalendrierService#isPeriodeDisponible} */
    @Deprecated
    public static boolean isPeriodeDisponible(Integer bpartnerID,
                                               Timestamp dateDebut,
                                               Timestamp dateFin) {
        return HRCalendrierService.isPeriodeDisponible(bpartnerID, dateDebut, dateFin);
    }

    // =========================================================================
    // DÉLÉGATION → HREmployeService
    // =========================================================================

    /** @deprecated Utiliser {@link HREmployeService#getDateDernierContrat} */
    @Deprecated
    public static MHRElementBasePaieEmploye getDateDernierContrat(
            Integer bpartnerID, Timestamp dateMax) {
        return HREmployeService.getDateDernierContrat(bpartnerID, dateMax);
    }

    /** @deprecated Utiliser {@link HREmployeService#setAnciennete} */
    @Deprecated
    public static BeanConge setAnciennete(BeanConge beanConge,
                                           Timestamp dateDebut,
                                           Timestamp dateFin) {
        return HREmployeService.setAnciennete(beanConge, dateDebut, dateFin);
    }

    /** @deprecated Utiliser {@link HREmployeService#getSuperieursHierarchiques} */
    @Deprecated
    public static List<Integer> getSuperieursHierarchiques(Integer cBPartnerId) {
        return HREmployeService.getSuperieursHierarchiques(cBPartnerId);
    }

    /** @deprecated Utiliser {@link HREmployeService#getSuperieursHierarchiquesParCategorie} */
    @Deprecated
    public static List<Integer> getSuperieursHierarchiquesbyCategorie(
            Integer cBPartnerId, Integer categorieResponsabiliteId) {
        return HREmployeService.getSuperieursHierarchiquesParCategorie(
            cBPartnerId, categorieResponsabiliteId);
    }

    // =========================================================================
    // DÉLÉGATION → HRCongeService
    // =========================================================================

    /** @deprecated Utiliser {@link HRCongeService#getInfoConge} */
    @Deprecated
    public static BeanConge getInfoConge(Integer idCBPartner,
                                          Integer idTypeConge,
                                          Timestamp dateActuelle,
                                          String trxName) {
        return HRCongeService.getInfoConge(idCBPartner, idTypeConge, dateActuelle, trxName);
    }

    /** @deprecated Utiliser {@link HRCongeService#getNombreJourCongeMax} */
    @Deprecated
    public static BeanConge getNombreJourCongeMax(Integer idCBPartner,
                                                   Timestamp dateActuelle,
                                                   String trxName) {
        return HRCongeService.getNombreJourCongeMax(idCBPartner, dateActuelle, trxName);
    }

    /** @deprecated Utiliser {@link HRCongeService#getNombreJourCongeAnnuelBase} */
    @Deprecated
    public static int getNombreJourCongeAnnuelBase() {
        return HRCongeService.getNombreJourCongeAnnuelBase();
    }

    /** @deprecated Utiliser {@link HRCongeService#getNombreJourMaxAbsenceAvantDemandeExplication} */
    @Deprecated
    public static int getNombreJourMaxAbsenceAvantDemandeExplication() {
        return HRCongeService.getNombreJourMaxAbsenceAvantDemandeExplication();
    }

    // =========================================================================
    // MÉTHODES SUPPRIMÉES
    // =========================================================================

    /**
     * Ancienne méthode d'envoi email avec mot de passe Gmail en dur.
     * SUPPRIMÉE — utiliser HRMailUtil dans org.sitracel.notification.process.
     *
     * @deprecated Supprimée pour raisons de sécurité.
     */
    @Deprecated
    public static void sendEmail(Object... ignored) {
        throw new UnsupportedOperationException(
            "sendEmail() supprimé. Utiliser HRMailUtil.createMail() à la place.");
    }
}
