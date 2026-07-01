/******************************************************************************
 * BIA RH et Paie — Projet Sitracel
 * Classe métier pour HR_Affectation
 *****************************************************************************/
package org.sitracel.contrat.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Classe métier pour la table HR_Affectation.
 *
 * Comme pour MHRContrat : aucune règle de blocage ici. La règle
 * d'unicité temporelle (un seul poste actif à la fois par employé)
 * sera appliquée dans le ModelValidator (Étape B), qui utilisera
 * la méthode chevauchePeriode() ci-dessous pour détecter un conflit.
 */
public class MHRAffectation extends X_HR_Affectation {

    private static final long serialVersionUID = 1L;

    /** Constructeur standard — chargement par ID */
    public MHRAffectation(Properties ctx, int HR_Affectation_ID, String trxName) {
        super(ctx, HR_Affectation_ID, trxName);
    }

    /** Constructeur ResultSet — utilisé par la factory */
    public MHRAffectation(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Indique si cette affectation couvre la date donnée.
     * Une affectation sans Date_Fin est considérée "en cours".
     *
     * @param date date à vérifier (non null)
     * @return true si la date tombe dans la période de l'affectation
     */
    public boolean couvreDate(Timestamp date) {
        if (date == null || getDate_Debut() == null) {
            return false;
        }
        boolean apresDebut = !date.before(getDate_Debut());
        boolean avantFin = (getDate_Fin() == null) || !date.after(getDate_Fin());
        return apresDebut && avantFin;
    }

    /**
     * Indique si la période de cette affectation chevauche celle d'une
     * autre affectation. Utilisée par le ModelValidator pour appliquer
     * la règle "un employé = une seule affectation active à la fois".
     *
     * @param autre l'autre affectation à comparer
     * @return true si les deux périodes se recoupent
     */
    public boolean chevauchePeriode(MHRAffectation autre) {
        if (autre == null || getDate_Debut() == null || autre.getDate_Debut() == null) {
            return false;
        }

        Timestamp debutA = getDate_Debut();
        Timestamp finA = getDate_Fin();
        Timestamp debutB = autre.getDate_Debut();
        Timestamp finB = autre.getDate_Fin();

        boolean debutAAvantOuEgalFinB = (finB == null) || !debutA.after(finB);
        boolean debutBAvantOuEgalFinA = (finA == null) || !debutB.after(finA);

        return debutAAvantOuEgalFinB && debutBAvantOuEgalFinA;
    }
}
