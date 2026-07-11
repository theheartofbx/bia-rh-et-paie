package org.sitracel.notification.process.notifier;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Moteur de rendu des templates de notification.
 *
 * Remplace les variables {{nomVariable}} par leur valeur.
 *
 * Exemple :
 *   template : "Bonjour {{employe}}, votre congé du {{debut}} est approuvé."
 *   variables : { "employe" → "Jean Kamga", "debut" → "01/07/2026" }
 *   résultat  : "Bonjour Jean Kamga, votre congé du 01/07/2026 est approuvé."
 *
 * Si une variable n'est pas trouvée dans la map, elle est laissée telle quelle.
 */
public final class HRNotificationTemplateEngine {

    private static final Pattern VARIABLE_PATTERN =
        Pattern.compile("\\{\\{(\\w+)\\}\\}");

    private HRNotificationTemplateEngine() {}

    public static String render(String template, Map<String, String> variables) {
        if (template == null || template.trim().isEmpty()) return "";
        if (variables == null || variables.isEmpty()) return template;

        StringBuffer resultat = new StringBuffer();
        Matcher matcher = VARIABLE_PATTERN.matcher(template);

        while (matcher.find()) {
            String nomVariable = matcher.group(1);
            String valeur = variables.getOrDefault(nomVariable, matcher.group(0));
            matcher.appendReplacement(resultat,
                Matcher.quoteReplacement(valeur));
        }
        matcher.appendTail(resultat);

        return resultat.toString();
    }
}
