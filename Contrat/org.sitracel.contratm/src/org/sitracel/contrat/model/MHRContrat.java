/******************************************************************************
 * BIA RH et Paie — Projet Sitracel
 * Classe métier pour HR_Contrat
 *****************************************************************************/
package org.sitracel.contrat.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Classe métier pour la table HR_Contrat.
 *
 * Cette classe ne contient AUCUNE règle de blocage (pas de refus de
 * création, pas d'exception levée). Elle fournit uniquement des méthodes
 * de calcul sur les dates, utilisées ensuite par le ModelValidator
 * (Étape B) pour décider d'autoriser ou non une opération.
 */
public class MHRContrat extends X_HR_Contrat {

    private static final long serialVersionUID = 1L;

    /** Constructeur standard — chargement par ID */
    public MHRContrat(Properties ctx, int HR_Contrat_ID, String trxName) {
        super(ctx, HR_Contrat_ID, trxName);
    }

    /** Constructeur ResultSet — utilisé par la factory */
    public MHRContrat(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Indique si ce contrat couvre la date donnée.
     *
     * Un contrat sans Date_Fin est considéré "en cours" (cas du CDI) :
     * il couvre toute date à partir de Date_Debut, sans limite haute.
     *
     * @param date date à vérifier (non null)
     * @return true si la date tombe dans la période du contrat
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
     * Indique si la période de ce contrat chevauche celle d'un autre
     * contrat. Deux périodes se chevauchent dès qu'elles partagent au
     * moins un jour commun. Une Date_Fin null signifie "sans limite".
     *
     * @param autre l'autre contrat à comparer
     * @return true si les deux périodes se recoupent
     */
    public boolean chevauchePeriode(MHRContrat autre) {
        if (autre == null || getDate_Debut() == null || autre.getDate_Debut() == null) {
            return false;
        }

        Timestamp debutA = getDate_Debut();
        Timestamp finA = getDate_Fin();
        Timestamp debutB = autre.getDate_Debut();
        Timestamp finB = autre.getDate_Fin();

        // Deux périodes [debutA, finA] et [debutB, finB] se chevauchent
        // si et seulement si : debutA <= finB ET debutB <= finA
        // (en traitant une Date_Fin null comme "infini")
        boolean debutAAvantOuEgalFinB = (finB == null) || !debutA.after(finB);
        boolean debutBAvantOuEgalFinA = (finA == null) || !debutB.after(finA);

        return debutAAvantOuEgalFinB && debutBAvantOuEgalFinA;
    }
}
