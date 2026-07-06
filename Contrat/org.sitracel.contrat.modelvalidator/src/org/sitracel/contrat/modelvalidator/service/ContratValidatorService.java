package org.sitracel.contrat.modelvalidator.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.compiere.util.Env;
import org.sitracel.contrat.model.MHRAffectation;
import org.sitracel.contrat.model.MHRContrat;

/**
 * Logique métier du ModelValidator Contrat.
 *
 * Chaque méthode de validation renvoie :
 *   - null si tout est correct (l'enregistrement peut continuer)
 *   - un message d'erreur explicite si l'enregistrement doit être bloqué
 *
 * C'est la convention iDempiere pour ModelValidator.modelChange().
 */
public class ContratValidatorService {

    private static final String FORMAT_DATE = "dd/MM/yyyy";

    // =========================================================================
    // CONTRAT
    // =========================================================================

    public static String validerContrat(MHRContrat contrat) {
        if (contrat.getC_BPartner_ID() <= 0 || contrat.getDate_Debut() == null) {
            return null;
        }

        if (contrat.getDate_Fin() != null && !contrat.getDate_Fin().after(contrat.getDate_Debut())) {
            return "La date de fin doit être postérieure à la date de début.";
        }

        if (contrat.getDate_Fin_Prevue() != null && !contrat.getDate_Fin_Prevue().after(contrat.getDate_Debut())) {
            return "La date de fin prévue doit être postérieure à la date de début.";
        }

        String nomType = getNomTypeContrat(contrat);
        boolean estCDI = "CDI".equalsIgnoreCase(nomType);

        if (nomType != null) {
            if (!estCDI && contrat.getDate_Fin_Prevue() == null) {
                return "Un contrat de type " + nomType + " doit avoir une date de fin prévue.";
            }
            if (estCDI && contrat.getDate_Fin_Prevue() != null) {
                return "Un CDI ne peut pas avoir de date de fin prévue (durée indéterminée).";
            }
        }

        if (estCDI && contrat.getDate_Fin() != null) {
            boolean ruptureExplicite = contrat.getDate_Rupture() != null
                || estRenseigne(contrat.getMotif_Rupture());
            if (!ruptureExplicite) {
                return "Un CDI ne peut avoir de date de fin que suite à une rupture "
                    + "(renseignez la date ou le motif de rupture).";
            }
        }

        List<MHRContrat> autresContrats = ContratValidatorRepository.getAutresContrats(
            contrat.getC_BPartner_ID(),
            contrat.getHR_Contrat_ID(),
            contrat.get_TrxName()
        );

        for (MHRContrat autre : autresContrats) {
            if (contrat.chevauchePeriode(autre)) {
                return "Ce contrat chevauche un contrat existant pour cet employé : "
                    + decrireContrat(autre)
                    + ". Modifiez les dates ou clôturez d'abord l'autre contrat.";
            }
        }

        appliquerStatutAutomatique(contrat);

        return null;
    }

    /**
     * Calcule et applique le statut du contrat selon ses dates :
     *   - Rupture renseignée (date ou motif)               -> Rompu
     *   - Date de fin renseignée et déjà passée             -> Terminé
     *   - Durée déterminée, pas de Date_Fin, mais échéance
     *     prévue (Date_Fin_Prevue) déjà passée               -> Terminé
     *   - Statut actuellement "Suspendu"                    -> respecté (décision manuelle RH)
     *   - Sinon                                              -> Actif
     */
    private static void appliquerStatutAutomatique(MHRContrat contrat) {
        boolean ruptureRenseignee = contrat.getDate_Rupture() != null
            || estRenseigne(contrat.getMotif_Rupture());

        Timestamp maintenant = new Timestamp(System.currentTimeMillis());

        String nomStatutCible;
        if (ruptureRenseignee) {
            nomStatutCible = "Rompu";
        } else if (contrat.getDate_Fin() != null && contrat.getDate_Fin().before(maintenant)) {
            nomStatutCible = "Terminé";
        } else if (contrat.getDate_Fin() == null
                && contrat.getDate_Fin_Prevue() != null
                && contrat.getDate_Fin_Prevue().before(maintenant)) {
            // Contrat à durée déterminée arrivé à son échéance prévue,
            // sans clôture explicite (Date_Fin) — on le considère terminé.
            nomStatutCible = "Terminé";
        } else if ("Suspendu".equalsIgnoreCase(getNomStatutActuel(contrat))) {
            return;
        } else {
            nomStatutCible = "Actif";
        }

        Integer statutId = ContratValidatorRepository.getStatutContratID(nomStatutCible, contrat.get_TrxName());
        if (statutId != null) {
            contrat.setHR_ContratStatut_ID(statutId);
        }
    }

    // =========================================================================
    // AFFECTATION
    // =========================================================================

    public static String validerAffectation(MHRAffectation affectation) {
        if (affectation.getDate_Debut() == null) {
            return null;
        }

        MHRContrat contratParent = null;
        if (affectation.getHR_Contrat_ID() > 0) {
            contratParent = new MHRContrat(Env.getCtx(), affectation.getHR_Contrat_ID(), affectation.get_TrxName());
        }

        if (contratParent != null && contratParent.getC_BPartner_ID() > 0) {
            if (affectation.getC_BPartner_ID() <= 0) {
                affectation.setC_BPartner_ID(contratParent.getC_BPartner_ID());
            } else if (affectation.getC_BPartner_ID() != contratParent.getC_BPartner_ID()) {
                return "Cette affectation doit concerner le même employé que son contrat.";
            }
        }

        if (affectation.getC_BPartner_ID() <= 0) {
            return null;
        }

        if (affectation.getDate_Fin() != null && !affectation.getDate_Fin().after(affectation.getDate_Debut())) {
            return "La date de fin doit être postérieure à la date de début.";
        }

        if (contratParent != null && contratParent.getDate_Debut() != null) {
            String erreurInclusion = validerInclusionDansContrat(affectation, contratParent);
            if (erreurInclusion != null) {
                return erreurInclusion;
            }
        }

        List<MHRAffectation> autresAffectations = ContratValidatorRepository.getAutresAffectations(
            affectation.getC_BPartner_ID(),
            affectation.getHR_Affectation_ID(),
            affectation.get_TrxName()
        );

        for (MHRAffectation autre : autresAffectations) {
            if (affectation.chevauchePeriode(autre)) {
                return "Cet employé a déjà une affectation active sur cette période : "
                    + decrireAffectation(autre)
                    + ". Un employé ne peut avoir qu'une seule affectation active à la fois.";
            }
        }

        return null;
    }

    private static String validerInclusionDansContrat(MHRAffectation affectation, MHRContrat contrat) {
        if (affectation.getDate_Debut().before(contrat.getDate_Debut())) {
            return "L'affectation ne peut pas commencer avant le début du contrat ("
                + formaterDate(contrat.getDate_Debut()) + ").";
        }

        if (contrat.getDateFinEffective() != null) {
            if (affectation.getDate_Fin() == null || affectation.getDate_Fin().after(contrat.getDateFinEffective())) {
                return "L'affectation doit se terminer au plus tard à la fin du contrat ("
                    + formaterDate(contrat.getDateFinEffective()) + ").";
            }
        }

        return null;
    }

    // =========================================================================
    // UTILITAIRES
    // =========================================================================

    private static boolean estRenseigne(String texte) {
        return texte != null && !texte.trim().isEmpty();
    }

    private static String formaterDate(Timestamp date) {
        if (date == null) {
            return "?";
        }
        return new SimpleDateFormat(FORMAT_DATE).format(date);
    }

    private static String getNomTypeContrat(MHRContrat contrat) {
        if (contrat.getHR_ContratType_ID() <= 0) {
            return null;
        }
        try {
            return contrat.getHR_ContratType().getName();
        } catch (RuntimeException erreurCatch) {
            return null;
        }
    }

    private static String getNomStatutActuel(MHRContrat contrat) {
        if (contrat.getHR_ContratStatut_ID() <= 0) {
            return null;
        }
        try {
            return contrat.getHR_ContratStatut().getName();
        } catch (RuntimeException erreurCatch) {
            return null;
        }
    }

    private static String decrireContrat(MHRContrat contrat) {
        String nomType = getNomTypeContrat(contrat);
        String debut = formaterDate(contrat.getDate_Debut());
        String fin = contrat.getDate_Fin() != null ? formaterDate(contrat.getDateFinEffective()) : "en cours";
        return (nomType != null ? nomType : "contrat") + " du " + debut + " (" + fin + ")";
    }

    private static String decrireAffectation(MHRAffectation affectation) {
        String debut = formaterDate(affectation.getDate_Debut());
        String fin = affectation.getDate_Fin() != null ? formaterDate(affectation.getDate_Fin()) : "en cours";
        return "affectation du " + debut + " (" + fin + ")";
    }
}
