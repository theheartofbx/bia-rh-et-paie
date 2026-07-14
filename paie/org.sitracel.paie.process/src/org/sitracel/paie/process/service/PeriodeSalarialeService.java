package org.sitracel.paie.process.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.logging.Logger;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.paie.model.I_HR_Periode_Salariale;
import org.sitracel.paie.model.MHRPeriodeSalariale;

/**
 * Service de gestion des périodes salariales.
 *
 * Règles métier :
 *   - Jour de début = PERIODE_JOUR_DEBUT (paramètre, défaut 16)
 *   - Durée de référence = PERIODE_DUREE_REF (paramètre, défaut 30)
 *   - Continuité stricte : pas de trou, pas de chevauchement
 *   - Nombre_Jour_Salarial = toujours 30 (durée de référence fixe)
 *
 * Exception connue : Juillet 2025 (ID 2020) avec dates calendaires
 * réelles — historique figé, ne pas toucher.
 */
public class PeriodeSalarialeService {

    private static final Logger log =
            Logger.getLogger(PeriodeSalarialeService.class.getName());

    private static final int JOUR_DEBUT_DEFAUT = 16;
    private static final int DUREE_REF_DEFAUT  = 30;

    // ---------------------------------------------------------------
    // API publique
    // ---------------------------------------------------------------

    /**
     * Génère les 12 périodes d'une année si elles n'existent pas encore.
     * Une période déjà présente (même début) est ignorée silencieusement.
     *
     * @return nombre de périodes créées
     */
    public static int genererAnnee(int annee, String trxName) {
        int jourDebut  = getParametre("PERIODE_JOUR_DEBUT", JOUR_DEBUT_DEFAUT, trxName);
        int dureeRef   = getParametre("PERIODE_DUREE_REF",  DUREE_REF_DEFAUT,  trxName);
        int crees = 0;

        for (int mois = 1; mois <= 12; mois++) {
            LocalDate debut = buildDebut(annee, mois, jourDebut);
            LocalDate fin   = buildFin(debut);
            String nom      = buildNom(mois, annee);

            if (periodeExiste(debut, trxName)) {
                log.fine("Période déjà existante : " + nom);
                continue;
            }

            if (chevauchement(debut, fin, trxName)) {
                log.warning("Chevauchement détecté pour " + nom + " — ignorée");
                continue;
            }

            creerPeriode(nom, debut, fin, dureeRef, trxName);
            crees++;
            log.info("Période créée : " + nom
                    + " [" + debut + " → " + fin + "]");
        }

        return crees;
    }

    /**
     * Garantit que la période contenant la date donnée existe.
     * Utilisé par PayrollCalculEngine avant tout calcul.
     * Crée la période si absente, sans planter.
     *
     * @return la période (existante ou nouvellement créée), ou null si échec
     */
    public static MHRPeriodeSalariale garantirPeriodePourDate(
            Timestamp date, String trxName) {

        MHRPeriodeSalariale existante =
                PeriodeSalarialeRepository.getPeriodePourDate(date, trxName);
        if (existante != null) return existante;

        // Calculer à quelle période cette date appartient
        int jourDebut = getParametre("PERIODE_JOUR_DEBUT", JOUR_DEBUT_DEFAUT, trxName);
        int dureeRef  = getParametre("PERIODE_DUREE_REF",  DUREE_REF_DEFAUT,  trxName);

        LocalDate d = date.toLocalDateTime().toLocalDate();
        // Si on est avant le jour de début du mois courant, on est dans
        // la période du mois précédent
        int annee = d.getYear();
        int mois  = d.getMonthValue();
        if (d.getDayOfMonth() < jourDebut) {
            mois--;
            if (mois == 0) { mois = 12; annee--; }
        }

        LocalDate debut = buildDebut(annee, mois, jourDebut);
        LocalDate fin   = buildFin(debut);
        String nom      = buildNom(mois, annee);

        if (chevauchement(debut, fin, trxName)) {
            log.severe("Impossible de créer la période " + nom
                    + " : chevauchement détecté");
            return null;
        }

        MHRPeriodeSalariale creee = creerPeriode(nom, debut, fin, dureeRef, trxName);
        log.warning("Période créée automatiquement : " + nom
                + " [" + debut + " → " + fin + "]");
        return creee;
    }

    /**
     * Vérifie la cohérence de toutes les périodes existantes.
     * Retourne un rapport texte des anomalies trouvées.
     */
    public static String verifierCoherence(String trxName) {
        return PeriodeSalarialeRepository.verifierCoherence(trxName);
    }

    // ---------------------------------------------------------------
    // Méthodes privées
    // ---------------------------------------------------------------

    private static LocalDate buildDebut(int annee, int mois, int jourDebut) {
        // Gérer le cas où le jour de début dépasse les jours du mois
        LocalDate premier = LocalDate.of(annee, mois, 1);
        int maxJour = premier.lengthOfMonth();
        int jour = Math.min(jourDebut, maxJour);
        return LocalDate.of(annee, mois, jour);
    }

    private static LocalDate buildFin(LocalDate debut) {
        // Fin = jour avant le début du mois suivant
        LocalDate debutSuivant = debut.plusMonths(1);
        return debutSuivant.minusDays(1);
    }

    private static String buildNom(int mois, int annee) {
        String[] MOIS = {"", "Janvier", "Février", "Mars", "Avril", "Mai",
                "Juin", "Juillet", "Août", "Septembre", "Octobre",
                "Novembre", "Décembre"};
        return MOIS[mois] + " " + annee;
    }

    private static boolean periodeExiste(LocalDate debut, String trxName) {
        Timestamp ts = Timestamp.valueOf(debut.atStartOfDay());
        return PeriodeSalarialeRepository.getPeriodeParDebut(ts, trxName) != null;
    }

    private static boolean chevauchement(LocalDate debut, LocalDate fin,
                                          String trxName) {
        Timestamp tsDebut = Timestamp.valueOf(debut.atStartOfDay());
        Timestamp tsFin   = Timestamp.valueOf(fin.atStartOfDay());
        return PeriodeSalarialeRepository.existeChevauchement(tsDebut, tsFin, trxName);
    }

    private static MHRPeriodeSalariale creerPeriode(String nom, LocalDate debut,
            LocalDate fin, int dureeRef, String trxName) {

        // Utiliser DB.executeUpdate pour contourner les méthodes protected de PO
        // La séquence est gérée par iDempiere via MHRPeriodeSalariale(ctx, 0, trx)
        MHRPeriodeSalariale p = new MHRPeriodeSalariale(Env.getCtx(), 0, trxName);
        p.setName(nom);
        p.setDate_Debut_Defaut(Timestamp.valueOf(debut.atStartOfDay()));
        p.setDate_Fin_Defaut(Timestamp.valueOf(fin.atStartOfDay()));
        p.setNombre_Jour_Salarial(dureeRef);
        p.setIsCongeAnnuelDeduit(true);
        p.setIsCongeMatPatlDeduit(true);
        p.setIsAvantDebutContratDeduit(true);
        p.setIsGestionPresenceAuto(true);
        p.setIsActive(true);
        p.save();

        // IsSuspensionDeduit via SQL direct (colonne sans setter typé)
        if (p.getHR_Periode_Salariale_ID() > 0) {
            DB.executeUpdate(
                "UPDATE HR_Periode_Salariale SET IsSuspensionDeduit='N' WHERE HR_Periode_Salariale_ID="
                + p.getHR_Periode_Salariale_ID(), trxName);
        }

        return p;
    }

    static int getParametre(String nom, int defaut, String trxName) {
        try {
            String sql = "SELECT Valeur_Parametre FROM HR_Parametre_Numerique "
                    + "WHERE Name=? AND IsActive='Y'";
            int val = DB.getSQLValue(trxName, sql, nom);
            return val > 0 ? val : defaut;
        } catch (Exception e) {
            log.warning("Paramètre " + nom + " non trouvé, valeur par défaut : " + defaut);
            return defaut;
        }
    }
}
