package org.sitracel.discipline.callout.demandeexplication.controller;

/**
 * @deprecated Logique callout demande d'explication.
 * Conservé pour compatibilité avec CalloutAmpliationDemandeExplication.
 */
@Deprecated
public class CalloutControllerDemandeExplication {

    private CalloutControllerDemandeExplication() {}

    /**
     * Construit la chaîne ampliation pour une demande d'explication.
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
