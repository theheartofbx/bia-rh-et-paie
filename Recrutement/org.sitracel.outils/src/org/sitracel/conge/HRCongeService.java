package org.sitracel.conge;

import java.sql.Timestamp;

import org.compiere.util.Env;
import org.sitracel.bean.BeanConge;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHREmployeeChildren;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.contrat.model.MHRContrat;
import org.sitracel.employe.HRContratService;
import org.sitracel.employe.HREmployeService;
import org.sitracel.model.X_C_BPartner;
import org.sitracel.parametrage.HRParametreService;
import org.sitracel.time.HRCalendrierService;

/**
 * Service transversal — règles métier congés partagées entre modules.
 *
 * Regroupe les calculs de droits à congé, d'ancienneté et de disponibilité
 * utilisés par les modules Congés, Paie et Discipline.
 *
 * Remplace les méthodes congé de GeneralController.
 */
public final class HRCongeService {

    private HRCongeService() {}

    // =========================================================================
    // INFORMATIONS CONGÉ ANNUEL
    // =========================================================================

    /**
     * Calcule et retourne toutes les informations de congé d'un employé
     * pour un type de congé donné à une date donnée.
     *
     * Remplace GeneralController.getInfoConge().
     */
    public static BeanConge getInfoConge(Integer bpartnerId,
                                          Integer typeCongeId,
                                          Timestamp dateActuelle,
                                          String trxName) {
        BeanConge beanConge = BeanFactory.getBeanConge();
        if (bpartnerId == null || typeCongeId == null) {
			return beanConge;
		}

        MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), typeCongeId, trxName);
        if (typeConge == null || !typeConge.isCongeAnnuel()) {
			return beanConge;
		}

        // Chercher la date d'embauche dans HR_Contrat (nouveau systeme)
        MHRContrat contratActif =
            HRContratService.getContratActif(bpartnerId, dateActuelle, trxName);

        Timestamp dateDebutContrat = contratActif != null
            ? contratActif.getDate_Debut() : null;

        // Jours déjà utilisés sur l'année RH courante
        BeanPeriode[] conges = HRCongeRepository.getCongesValideByNameConge(
            bpartnerId,
            "Annuel",
            HRCalendrierService.getFirstDayOfYear(dateActuelle),
            HRCalendrierService.getLastDayOfYear(dateActuelle),
            null
        );

        int joursUtilises = 0;
        if (conges != null) {
            for (BeanPeriode periode : conges) {
                Integer jours = HRCalendrierService.getNombreJourTravaille(
                    periode.getDateDebutConge(), periode.getDateFinConge());
                if (jours != null) {
					joursUtilises += jours;
				}
            }
        }

        // Calcul ancienneté et droits
        beanConge = MHREmployeeChildren.getEnfantMoins6(
            bpartnerId, dateActuelle, beanConge, trxName);
        beanConge = HREmployeService.setAnciennete(
            beanConge, dateDebutContrat, dateActuelle);

        beanConge.setNombreJourCongeTotal(calculerDroitsCongeAnnuel(beanConge));
        beanConge.setNombreJourCongeUtilise(joursUtilises);

        // Dernier congé pris
        beanConge = HRCongeRepository.getDateDernierConge(
            bpartnerId, dateActuelle, dateDebutContrat, "Annuel", beanConge, null);

        return beanConge;
    }

    /**
     * Retourne uniquement le nombre de jours de congé max d'un employé
     * (sans charger les jours utilisés).
     *
     * Remplace GeneralController.getNombreJourCongeMax().
     */
    public static BeanConge getNombreJourCongeMax(Integer bpartnerId,
                                                   Timestamp dateActuelle,
                                                   String trxName) {
        BeanConge beanConge = BeanFactory.getBeanConge();
        if (bpartnerId == null) {
			return beanConge;
		}

        // Chercher la date d'embauche dans HR_Contrat (nouveau systeme)
        MHRContrat contratActif =
            HRContratService.getContratActif(bpartnerId, dateActuelle, trxName);

        Timestamp dateDebutContrat = contratActif != null
            ? contratActif.getDate_Debut() : null;

        beanConge = MHREmployeeChildren.getEnfantMoins6(
            bpartnerId, dateActuelle, beanConge, trxName);
        beanConge = HREmployeService.setAnciennete(
            beanConge, dateDebutContrat, dateActuelle);

        beanConge.setNombreJourCongeTotal(calculerDroitsCongeAnnuel(beanConge));
        return beanConge;
    }

    // =========================================================================
    // PARAMÈTRES SYSTÈME
    // =========================================================================

    /**
     * Retourne le nombre de jours de congé annuel de base.
     */
    public static int getNombreJourCongeAnnuelBase() {
        return HRParametreService.getParametreNumerique("Congé Annuel de Base");
    }

    /**
     * Retourne le nombre max d'absences avant déclenchement d'une
     * demande d'explication.
     */
    public static int getNombreJourMaxAbsenceAvantDemandeExplication() {
        return HRParametreService.getParametreNumerique(
            "Absence Max Avant Demande Explication");
    }

    // =========================================================================
    // UTILITAIRES INTERNES
    // =========================================================================

    /**
     * Calcule les droits à congé annuel selon l'ancienneté et le genre.
     *
     * Règle métier :
     *   - Base = paramètre système "Congé Annuel de Base"
     *   - +2 jours par tranche de 3 ans d'ancienneté
     *   - +2 jours par enfant de moins de 6 ans (femmes uniquement)
     */
    private static int calculerDroitsCongeAnnuel(BeanConge beanConge) {
        int nbBase = getNombreJourCongeAnnuelBase();

        if (beanConge.getAnneeAnciennete() != null) {
            nbBase += 2 * (beanConge.getAnneeAnciennete() / 3);
        }
        if (X_C_BPartner.SEX_Femme.equals(beanConge.getGenre())
                && beanConge.getNombreEnfantPetit() != null) {
            nbBase += 2 * beanConge.getNombreEnfantPetit();
        }
        return nbBase;
    }
}
