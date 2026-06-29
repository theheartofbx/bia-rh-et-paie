package org.sitracel.discipline.callout.sanction.controller;

/**
 * @deprecated Logique callout discipline — ampliation sanction.
 * Conservé pour compatibilité avec CalloutAmpliationSanction.
 */
@Deprecated
public class CalloutControllerDiscipline {

    private CalloutControllerDiscipline() {}

    /**
     * Construit la chaîne ampliation à partir d'une abréviation et d'une année.
     */
    public static String getAmpliation(String ampliationActuelle,
                                        String abreviation,
                                        String annee) {
        if (abreviation == null) return ampliationActuelle;
        String suffixe = abreviation + (annee != null ? annee : "");
        if (ampliationActuelle == null || ampliationActuelle.isEmpty()) {
            return suffixe;
        }
        if (ampliationActuelle.contains(suffixe)) {
            return ampliationActuelle;
        }
        return ampliationActuelle + " / " + suffixe;
    }
}
