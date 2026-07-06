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
 * de calcul sur les dates, utilisées ensuite par le ModelValidator pour
 * décider d'autoriser ou non une opération.
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
     * Retourne la date de fin "effective" à utiliser pour tous les calculs
     * de période (chevauchement, inclusion, couverture) :
     *   - Date_Fin si elle est renseignée (le contrat est réellement clos)
     *   - sinon Date_Fin_Prevue en repli (utile pour un CDD/Stage/Intérim/
     *     Prestation pas encore clôturé administrativement, mais dont
     *     l'échéance contractuelle est connue)
     *   - null si aucune des deux n'est renseignée (CDI en cours, ou
     *     durée déterminée sans échéance connue)
     *
     * Sans ce repli, un CDD dont "Jusqu'au" n'a pas encore été rempli
     * serait traité comme un contrat sans limite dans le temps — ce qui
     * bloquerait à tort tout autre contrat de cet employé, même des
     * années avant ou après.
     */
    public Timestamp getDateFinEffective() {
        if (getDate_Fin() != null) {
            return getDate_Fin();
        }
        return getDate_Fin_Prevue();
    }

    /**
     * Indique si ce contrat couvre la date donnée.
     *
     * @param date date à vérifier (non null)
     * @return true si la date tombe dans la période du contrat
     */
    public boolean couvreDate(Timestamp date) {
        if (date == null || getDate_Debut() == null) {
            return false;
        }
        Timestamp finEffective = getDateFinEffective();
        boolean apresDebut = !date.before(getDate_Debut());
        boolean avantFin = (finEffective == null) || !date.after(finEffective);
        return apresDebut && avantFin;
    }

    /**
     * Indique si la période de ce contrat chevauche celle d'un autre
     * contrat. Deux périodes se chevauchent dès qu'elles partagent au
     * moins un jour commun. Une fin effective null signifie "sans limite".
     *
     * @param autre l'autre contrat à comparer
     * @return true si les deux périodes se recoupent
     */
    public boolean chevauchePeriode(MHRContrat autre) {
        if (autre == null || getDate_Debut() == null || autre.getDate_Debut() == null) {
            return false;
        }

        Timestamp debutA = getDate_Debut();
        Timestamp finA = getDateFinEffective();
        Timestamp debutB = autre.getDate_Debut();
        Timestamp finB = autre.getDateFinEffective();

        boolean debutAAvantOuEgalFinB = (finB == null) || !debutA.after(finB);
        boolean debutBAvantOuEgalFinA = (finA == null) || !debutB.after(finA);

        return debutAAvantOuEgalFinB && debutBAvantOuEgalFinA;
    }
}
