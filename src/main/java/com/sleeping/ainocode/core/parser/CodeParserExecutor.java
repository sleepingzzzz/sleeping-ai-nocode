package com.sleeping.ainocode.core.parser;

import com.sleeping.ainocode.exception.BusinessException;
import com.sleeping.ainocode.exception.ErrorCode;
import com.sleeping.ainocode.model.enums.CodeGenTypeEnum;

public class CodeParserExecutor {

    private static final HtmlCodeParser htmlCodeParser = new HtmlCodeParser();
    private static final MultiFileCodeParser multiFileCodeParser = new MultiFileCodeParser();

    public static Object executeParser(String codeContent, CodeGenTypeEnum codeGenType) {
        return switch (codeGenType) {
            case HTML -> htmlCodeParser.parseCode(codeContent);
            case MULTI_FILE -> multiFileCodeParser.parseCode(codeContent);
        };
    }
}
