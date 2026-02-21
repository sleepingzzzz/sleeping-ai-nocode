package com.sleeping.ainocode.core;

import com.sleeping.ainocode.ai.AiCodeGeneratorService;
import com.sleeping.ainocode.ai.model.HtmlCodeResult;
import com.sleeping.ainocode.ai.model.MultiFileCodeResult;
import com.sleeping.ainocode.core.parser.CodeParserExecutor;
import com.sleeping.ainocode.core.saver.CodeFileSaverExecutor;
import com.sleeping.ainocode.exception.BusinessException;
import com.sleeping.ainocode.exception.ErrorCode;
import com.sleeping.ainocode.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.File;

@Service
@Slf4j
public class AiCodeGeneratorFacade {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    public File generateAndSaveCode(String userMessage, CodeGenTypeEnum codeGenTypeEnum) {
        if (codeGenTypeEnum == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "生成类型为空");
        }
        return switch (codeGenTypeEnum) {
            case HTML -> {
                HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode(userMessage);
                yield CodeFileSaverExecutor.executeSaver(result, CodeGenTypeEnum.HTML);
            }
            case MULTI_FILE -> {
                MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode(userMessage);
                yield CodeFileSaverExecutor.executeSaver(result, CodeGenTypeEnum.MULTI_FILE);
            }
        };
    }

    public Flux<String> generateAndSaveCodeStream(String userMessage, CodeGenTypeEnum codeGenTypeEnum) {
        if (codeGenTypeEnum == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "生成类型为空");
        }
        return switch (codeGenTypeEnum) {
            case HTML -> {
                Flux<String> codeStream = aiCodeGeneratorService.generateHtmlCodeStream(userMessage);
                yield processCodeStream(codeStream, CodeGenTypeEnum.HTML);
            }
            case MULTI_FILE -> {
                Flux<String> codeStream = aiCodeGeneratorService.generateMultiFileCodeStream(userMessage);
                yield processCodeStream(codeStream, CodeGenTypeEnum.MULTI_FILE);
            }
        };
    }

    private Flux<String> processCodeStream(Flux<String> codeStream, CodeGenTypeEnum codeGenType) {
        StringBuilder codeBuilder = new StringBuilder();
        return codeStream
                .doOnNext(chunk -> codeBuilder.append(chunk))
                .doOnComplete(() -> {
                    try {
                        String completeCode = codeBuilder.toString();
                        Object parsedResult = CodeParserExecutor.executeParser(completeCode, codeGenType);
                        File savedDir = CodeFileSaverExecutor.executeSaver(parsedResult, codeGenType);
                        log.info("代码保存成功，路径: {}", savedDir.getAbsolutePath());
                    } catch (Exception e) {
                        log.error("代码保存失败: {}", e.getMessage(), e);
                    }
                });
    }
}
