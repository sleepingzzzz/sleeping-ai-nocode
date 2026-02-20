package com.sleeping.ainocode.common;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Pattern;

public final class SanitizeUtils {

    private static final Pattern HTML_TAG_PATTERN = Pattern.compile("<[^>]*>");
    private static final Pattern SCRIPT_PATTERN = Pattern.compile("(?i)<script[^>]*>.*?</script>", Pattern.DOTALL);
    private static final Pattern EVENT_HANDLER_PATTERN = Pattern.compile("(?i)\\s*on\\w+\\s*=", Pattern.DOTALL);
    private static final Pattern JAVASCRIPT_URL_PATTERN = Pattern.compile("(?i)javascript\\s*:", Pattern.DOTALL);
    private static final Pattern DATA_URL_PATTERN = Pattern.compile("(?i)data\\s*:", Pattern.DOTALL);
    private static final Pattern VBSCRIPT_URL_PATTERN = Pattern.compile("(?i)vbscript\\s*:", Pattern.DOTALL);

    private SanitizeUtils() {
    }

    public static String sanitize(String input) {
        if (StrUtil.isBlank(input)) {
            return input;
        }
        
        String sanitized = input;
        sanitized = SCRIPT_PATTERN.matcher(sanitized).replaceAll("");
        sanitized = EVENT_HANDLER_PATTERN.matcher(sanitized).replaceAll("");
        sanitized = JAVASCRIPT_URL_PATTERN.matcher(sanitized).replaceAll("");
        sanitized = DATA_URL_PATTERN.matcher(sanitized).replaceAll("");
        sanitized = VBSCRIPT_URL_PATTERN.matcher(sanitized).replaceAll("");
        sanitized = HTML_TAG_PATTERN.matcher(sanitized).replaceAll("");
        
        sanitized = sanitized
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
        
        return sanitized.trim();
    }

    public static String sanitizePreserveBasic(String input) {
        if (StrUtil.isBlank(input)) {
            return input;
        }
        
        String sanitized = input;
        sanitized = SCRIPT_PATTERN.matcher(sanitized).replaceAll("");
        sanitized = EVENT_HANDLER_PATTERN.matcher(sanitized).replaceAll("");
        sanitized = JAVASCRIPT_URL_PATTERN.matcher(sanitized).replaceAll("");
        sanitized = DATA_URL_PATTERN.matcher(sanitized).replaceAll("");
        sanitized = VBSCRIPT_URL_PATTERN.matcher(sanitized).replaceAll("");
        
        return sanitized.trim();
    }
}
