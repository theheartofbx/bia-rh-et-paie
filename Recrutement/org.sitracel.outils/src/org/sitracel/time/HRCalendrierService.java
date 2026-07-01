package org.sitracel.time;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Set;

import org.sitracel.conge.HRCongeRepository;
import org.sitracel.conge.model.MHRPublicHoliday;

/**
 * Service transversal de gestion du calendrier RH.
 *
 * Regroupe tous les calculs de dates, jours travaillés et ajustements
 * calendaires utilisés par plusieurs modules (Congés, Discipline, Paie...).
 *
 * Remplace les méthodes dates/calendrier de GeneralController.
 */
public final class HRCalendrierService {

    private HRCalendrierService() {}

    // =========================================================================
    // COMPTAGE DE JOURS
    // =========================================================================

    /**
     * Nombre de jours travaillés entre deux dates (hors dimanches et jours fériés).
     */
    public static Integer getNombreJourTravaille(Timestamp dateDebut, Timestamp dateFin) {
        if (dateDebut == null || dateFin == null) {
			return null;
		}

        LocalDate debut = toLocalDate(dateDebut);
        LocalDate fin   = toLocalDate(dateFin);
        Set<LocalDate> joursFeries = HRCongeRepository.getAllJoursFeries(dateDebut, dateFin, null);

        int resultat = 0;
        for (LocalDate date = debut; !date.isAfter(fin); date = date.plusDays(1)) {
            if (date.getDayOfWeek() != DayOfWeek.SUNDAY && !joursFeries.contains(date)) {
                resultat++;
            }
        }
        return resultat;
    }

    /**
     * Nombre de jours calendaires entre deux dates.
     */
    public static Integer getNombreJour(Timestamp dateDebut, Timestamp dateFin) {
        if (dateDebut == null || dateFin == null) {
			return null;
		}
        return (int) ChronoUnit.DAYS.between(toLocalDate(dateDebut), toLocalDate(dateFin));
    }

    /**
     * Nombre de mois entre deux dates.
     */
    public static Integer getNombreMois(Timestamp dateDebut, Timestamp dateFin) {
        if (dateDebut == null || dateFin == null) {
			return null;
		}
        return (int) ChronoUnit.MONTHS.between(toLocalDate(dateDebut), toLocalDate(dateFin));
    }

    // =========================================================================
    // AJUSTEMENTS DE DATES
    // =========================================================================

    /**
     * Ajoute n jours ouvrables à une date (saute dimanches et jours fériés).
     */
    public static Timestamp ajouterJoursOuvrables(Timestamp date, int nombreJour) {
        if (date == null) {
			return null;
		}
        LocalDate jour = toLocalDate(date);
        for (int i = 1; i <= nombreJour; i++) {
            jour = jour.plusDays(1);
            date = ajusterEnAjoutant(Timestamp.valueOf(
                LocalDateTime.of(jour, date.toLocalDateTime().toLocalTime())));
        }
        return date;
    }

    /**
     * Retire n jours ouvrables à une date (saute dimanches et jours fériés).
     */
    public static Timestamp retirerJoursOuvrables(Timestamp date, int nombreJour) {
        if (date == null) {
			return null;
		}
        LocalDate jour = toLocalDate(date);
        for (int i = 1; i <= nombreJour; i++) {
            jour = jour.minusDays(1);
            date = ajusterEnRetirant(Timestamp.valueOf(
                LocalDateTime.of(jour, date.toLocalDateTime().toLocalTime())));
        }
        return date;
    }

    /**
     * Avance la date si elle tombe un dimanche ou jour férié.
     */
    public static Timestamp ajusterEnAjoutant(Timestamp date) {
        if (date == null) {
			return null;
		}
        LocalDate jour = toLocalDate(date);
        if (jour.getDayOfWeek() == DayOfWeek.SUNDAY || MHRPublicHoliday.isJourFerie(date, null)) {
            return Timestamp.valueOf(jour.plusDays(1).atStartOfDay());
        }
        return date;
    }

    /**
     * Recule la date si elle tombe un dimanche ou jour férié.
     */
    public static Timestamp ajusterEnRetirant(Timestamp date) {
        if (date == null) {
			return null;
		}
        LocalDate jour = toLocalDate(date);
        if (jour.getDayOfWeek() == DayOfWeek.SUNDAY || MHRPublicHoliday.isJourFerie(date, null)) {
            return Timestamp.valueOf(jour.minusDays(1).atStartOfDay());
        }
        return date;
    }

    // =========================================================================
    // BORNES D'ANNÉE
    // =========================================================================

    /**
     * Premier jour de l'année RH (= 1er janvier de l'année précédant la date).
     * Convention métier : l'année RH commence le 1er jan de l'année N-1.
     */
    public static Timestamp getFirstDayOfYear(Timestamp anyDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(anyDay);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * Dernier jour de l'année RH (= 1er janvier de l'année suivant la date).
     */
    public static Timestamp getLastDayOfYear(Timestamp anyDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(anyDay);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * Premier jour de l'année en cours (convention RH).
     */
    public static Timestamp getFirstDayOfThisYear() {
        return getFirstDayOfYear(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * Dernier jour de l'année en cours (convention RH).
     */
    public static Timestamp getLastDayOfThisYear() {
        return getLastDayOfYear(new Timestamp(System.currentTimeMillis()));
    }

    // =========================================================================
    // DISPONIBILITÉ
    // =========================================================================

    /**
     * Vérifie qu'un jour est disponible pour un employé
     * (ni congé, ni suspension, ni absence, ni dimanche/férié).
     */
    public static boolean isJourDisponible(Integer bpartnerId, Timestamp date) {
        if (bpartnerId == null || date == null) {
			return false;
		}
        return !HRCongeRepository.isJourAnyCongeNonRejete(bpartnerId, date, null)
            && !HRCongeRepository.isJourSuspensionNonRejete(bpartnerId, date, null)
            && !HRCongeRepository.isJourAbsence(bpartnerId, date, null)
            && !MHRPublicHoliday.isJourFerie(date, null);
    }

    /**
     * Vérifie qu'une période est disponible pour un employé.
     */
    public static boolean isPeriodeDisponible(Integer bpartnerId,
                                              Timestamp dateDebut,
                                              Timestamp dateFin) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) {
			return false;
		}
        return !HRCongeRepository.chevaucheAnyCongeNonRejete(bpartnerId, dateDebut, dateFin, null)
            && !HRCongeRepository.chevaucheSuspensionNonRejete(bpartnerId, dateDebut, dateFin, null)
            && !HRCongeRepository.isPeriodeAbsence(bpartnerId, dateDebut, dateFin, null);
    }

    // =========================================================================
    // UTILITAIRE INTERNE
    // =========================================================================

    private static LocalDate toLocalDate(Timestamp ts) {
        return ts.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
