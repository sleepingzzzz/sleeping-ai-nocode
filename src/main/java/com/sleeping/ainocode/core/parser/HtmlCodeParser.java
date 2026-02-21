package com.sleeping.ainocode.core.parser;

import com.sleeping.ainocode.ai.model.HtmlCodeResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlCodeParser implements CodeParser<HtmlCodeResult> {

    private static final Pattern HTML_CODE_PATTERN = Pattern.compile("```html\\s*\\n([\\s\\S]*?)```", Pattern.CASE_INSENSITIVE);
    private static final Pattern CODE_BLOCK_PATTERN = Pattern.compile("```[\\s\\S]*?```", Pattern.CASE_INSENSITIVE);

    @Override
    public HtmlCodeResult parseCode(String codeContent) {
        HtmlCodeResult result = new HtmlCodeResult();
        String htmlCode = extractHtmlCode(codeContent);
        if (htmlCode != null && !htmlCode.trim().isEmpty()) {
            result.setHtmlCode(htmlCode.trim());
        } else {
            result.setHtmlCode(codeContent.trim());
        }
        String description = extractDescription(codeContent);
        if (description != null && !description.trim().isEmpty()) {
            result.setDescription(description.trim());
        }
        return result;
    }

    private String extractHtmlCode(String content) {
        Matcher matcher = HTML_CODE_PATTERN.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private String extractDescription(String content) {
        String description = CODE_BLOCK_PATTERN.matcher(content).replaceAll("");
        description = description.replaceAll("\\n+", " ").trim();
        return description.isEmpty() ? null : description;
    }
}
