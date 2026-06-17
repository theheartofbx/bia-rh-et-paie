package org.sitracel.paie.utils;

import java.sql.Timestamp;

import org.sitracel.controller.GeneralController;

final class Intervals {
    private Intervals() {}

    static boolean overlaps(Timestamp s1, Timestamp e1, Timestamp s2, Timestamp e2) {
        if (s1 == null || e1 == null || s2 == null || e2 == null) return false;
        return !(e1.before(s2) || e2.before(s1));
    }

    static int workedDaysOverlap(Timestamp s1, Timestamp e1, Timestamp s2, Timestamp e2) {
        if (!overlaps(s1, e1, s2, e2)) return 0;
        Timestamp start = s1.after(s2) ? s1 : s2;
        Timestamp end   = e1.before(e2) ? e1 : e2;
        return GeneralController.getNombreJourTravaille(start, end);
    }
}
