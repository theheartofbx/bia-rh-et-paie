package org.sitracel.notification.template;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotificationGestionTemplate {

    private static final Pattern VAR_PATTERN =
            Pattern.compile("\\$\\{([^}]+)}");

    public static String render(
            String template,
            Map<String, Object> vars
    ) {

        if (template == null || vars == null) {
            return template;
        }

        Matcher matcher = VAR_PATTERN.matcher(template);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String key = matcher.group(1);
            Object value = vars.get(key);
            matcher.appendReplacement(
                sb,
                Matcher.quoteReplacement(
                    value != null ? value.toString() : ""
                )
            );
        }

        matcher.appendTail(sb);
        return sb.toString();
    }
}
