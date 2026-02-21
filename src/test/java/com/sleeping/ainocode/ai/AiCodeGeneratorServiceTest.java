package com.sleeping.ainocode.ai;

import com.sleeping.ainocode.ai.model.HtmlCodeResult;
import com.sleeping.ainocode.ai.model.MultiFileCodeResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeGeneratorServiceTest {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    @Test
    void generateHtmlCode() {
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode("做一个简单的待办事项列表");
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getHtmlCode());
        System.out.println("生成的HTML代码描述: " + result.getDescription());
    }

    @Test
    void generateMultiFileCode() {
        MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode("做一个简单的留言板");
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getHtmlCode());
        System.out.println("生成的多文件代码描述: " + result.getDescription());
    }
}
