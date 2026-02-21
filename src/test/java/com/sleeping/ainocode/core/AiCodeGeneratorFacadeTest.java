package com.sleeping.ainocode.core;

import com.sleeping.ainocode.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.io.File;
import java.util.List;

@SpringBootTest
class AiCodeGeneratorFacadeTest {

    @Resource
    private AiCodeGeneratorFacade aiCodeGeneratorFacade;

    @Test
    void generateAndSaveHtmlCode() {
        File file = aiCodeGeneratorFacade.generateAndSaveCode("任务记录网站", CodeGenTypeEnum.HTML);
        Assertions.assertNotNull(file);
        Assertions.assertTrue(file.exists());
        System.out.println("HTML文件保存路径: " + file.getAbsolutePath());
    }

    @Test
    void generateAndSaveMultiFileCode() {
        File file = aiCodeGeneratorFacade.generateAndSaveCode("任务记录网站", CodeGenTypeEnum.MULTI_FILE);
        Assertions.assertNotNull(file);
        Assertions.assertTrue(file.exists());
        System.out.println("多文件保存路径: " + file.getAbsolutePath());
    }

    @Test
    void generateAndSaveHtmlCodeStream() {
        Flux<String> codeStream = aiCodeGeneratorFacade.generateAndSaveCodeStream("简单的待办事项列表", CodeGenTypeEnum.HTML);
        List<String> result = codeStream.collectList().block();
        Assertions.assertNotNull(result);
        String completeContent = String.join("", result);
        Assertions.assertNotNull(completeContent);
        Assertions.assertFalse(completeContent.isEmpty());
        System.out.println("流式生成HTML完成，内容长度: " + completeContent.length());
    }

    @Test
    void generateAndSaveMultiFileCodeStream() {
        Flux<String> codeStream = aiCodeGeneratorFacade.generateAndSaveCodeStream("任务记录网站", CodeGenTypeEnum.MULTI_FILE);
        List<String> result = codeStream.collectList().block();
        Assertions.assertNotNull(result);
        String completeContent = String.join("", result);
        Assertions.assertNotNull(completeContent);
        Assertions.assertFalse(completeContent.isEmpty());
        System.out.println("流式生成多文件完成，内容长度: " + completeContent.length());
    }

    @Test
    void generateAndSaveCodeWithAppId() {
        File file = aiCodeGeneratorFacade.generateAndSaveCode("任务记录网站", CodeGenTypeEnum.MULTI_FILE, 1L);
        Assertions.assertNotNull(file);
        Assertions.assertTrue(file.exists());
        System.out.println("基于 appId 的文件保存路径: " + file.getAbsolutePath());
    }

    @Test
    void generateAndSaveCodeStreamWithAppId() {
        Flux<String> codeStream = aiCodeGeneratorFacade.generateAndSaveCodeStream("任务记录网站", CodeGenTypeEnum.MULTI_FILE, 1L);
        List<String> result = codeStream.collectList().block();
        Assertions.assertNotNull(result);
        String completeContent = String.join("", result);
        Assertions.assertNotNull(completeContent);
        Assertions.assertFalse(completeContent.isEmpty());
        System.out.println("流式生成完成（带 appId），内容长度: " + completeContent.length());
    }
}
