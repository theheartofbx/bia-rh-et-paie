package org.sitracel.contrat.modelvalidator.service;

import java.util.List;

import org.sitracel.contrat.model.MHRAffectation;
import org.sitracel.contrat.model.MHRContrat;

/**
 * Logique métier du ModelValidator Contrat.
 *
 * Chaque méthode renvoie :
 *   - null si tout est correct (l'enregistrement peut continuer)
 *   - un message d'erreur explicite si l'enregistrement doit être bloqué
 *
 * C'est la convention iDempiere pour ModelValidator.modelChange().
 */
public class ContratValidatorService {

    public static String validerContrat(MHRContrat contrat) {
        // Si les champs obligatoires ne sont pas encore renseignés,
        // on laisse les contrôles standards d'iDempiere (champ requis)
        // faire leur travail — pas la peine de dupliquer cette vérification ici.
        if (contrat.getC_BPartner_ID() <= 0 || contrat.getDate_Debut() == null) {
            return null;
        }

        List<MHRContrat> autresContrats = ContratValidatorRepository.getAutresContrats(
            contrat.getC_BPartner_ID(),
            contrat.getHR_Contrat_ID(),
            contrat.get_TrxName()
        );

        for (MHRContrat autre : autresContrats) {
            if (contrat.chevauchePeriode(autre)) {
                return "Ce contrat chevauche un contrat existant pour cet employé "
                    + "(contrat en conflit : HR_Contrat_ID=" + autre.getHR_Contrat_ID() + "). "
                    + "Vérifiez les dates de début et de fin.";
            }
        }

        return null;
    }

    public static String validerAffectation(MHRAffectation affectation) {
        if (affectation.getC_BPartner_ID() <= 0 || affectation.getDate_Debut() == null) {
            return null;
        }

        List<MHRAffectation> autresAffectations = ContratValidatorRepository.getAutresAffectations(
            affectation.getC_BPartner_ID(),
            affectation.getHR_Affectation_ID(),
            affectation.get_TrxName()
        );

        for (MHRAffectation autre : autresAffectations) {
            if (affectation.chevauchePeriode(autre)) {
                return "Cet employé a déjà une affectation active sur cette période "
                    + "(affectation en conflit : HR_Affectation_ID=" + autre.getHR_Affectation_ID() + "). "
                    + "Un employé ne peut avoir qu'une seule affectation active à la fois.";
            }
        }

        return null;
    }
}
