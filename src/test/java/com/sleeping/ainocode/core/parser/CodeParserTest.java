package com.sleeping.ainocode.core.parser;

import com.sleeping.ainocode.ai.model.HtmlCodeResult;
import com.sleeping.ainocode.ai.model.MultiFileCodeResult;
import com.sleeping.ainocode.model.enums.CodeGenTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CodeParserTest {

    @Test
    void parseHtmlCode() {
        String codeContent = """
                随便写一段描述：
                ```html
                <!DOCTYPE html>
                <html>
                <head>
                    <title>测试页面</title>
                </head>
                <body>
                    <h1>Hello World!</h1>
                </body>
                </html>
                ```
                随便写一段描述
                """;
        HtmlCodeParser parser = new HtmlCodeParser();
        HtmlCodeResult result = parser.parseCode(codeContent);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getHtmlCode());
        Assertions.assertTrue(result.getHtmlCode().contains("<h1>Hello World!</h1>"));
    }

    @Test
    void parseHtmlCodeWithDescription() {
        String codeContent = """
                这是一个简单的测试页面：
                ```html
                <!DOCTYPE html>
                <html>
                <body>
                    <h1>Hello World!</h1>
                </body>
                </html>
                ```
                页面生成完成！
                """;
        HtmlCodeParser parser = new HtmlCodeParser();
        HtmlCodeResult result = parser.parseCode(codeContent);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getHtmlCode());
        Assertions.assertNotNull(result.getDescription());
        Assertions.assertTrue(result.getDescription().contains("测试页面"));
        Assertions.assertTrue(result.getDescription().contains("生成完成"));
    }

    @Test
    void parseHtmlCodeWithoutBlock() {
        String codeContent = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>测试页面</title>
                </head>
                <body>
                    <h1>Hello World!</h1>
                </body>
                </html>
                """;
        HtmlCodeParser parser = new HtmlCodeParser();
        HtmlCodeResult result = parser.parseCode(codeContent);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getHtmlCode());
        Assertions.assertTrue(result.getHtmlCode().contains("<h1>Hello World!</h1>"));
    }

    @Test
    void parseMultiFileCode() {
        String codeContent = """
                创建一个完整的网页：
                ```html
                <!DOCTYPE html>
                <html>
                <head>
                    <title>多文件示例</title>
                    <link rel="stylesheet" href="style.css">
                </head>
                <body>
                    <h1>欢迎使用</h1>
                    <script src="script.js"></script>
                </body>
                </html>
                ```
                ```css
                h1 {
                    color: blue;
                    text-align: center;
                }
                ```
                ```js
                console.log('页面加载完成');
                ```
                文件创建完成！
                """;
        MultiFileCodeParser parser = new MultiFileCodeParser();
        MultiFileCodeResult result = parser.parseCode(codeContent);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getHtmlCode());
        Assertions.assertNotNull(result.getCssCode());
        Assertions.assertNotNull(result.getJsCode());
        Assertions.assertTrue(result.getHtmlCode().contains("<h1>欢迎使用</h1>"));
        Assertions.assertTrue(result.getCssCode().contains("color: blue"));
        Assertions.assertTrue(result.getJsCode().contains("console.log"));
    }

    @Test
    void parseMultiFileCodeWithDescription() {
        String codeContent = """
                这是一个留言板应用：
                ```html
                <html><body>留言板</body></html>
                ```
                ```css
                body { margin: 0; }
                ```
                ```js
                console.log('留言板加载完成');
                ```
                留言板功能已实现，支持添加和删除留言。
                """;
        MultiFileCodeParser parser = new MultiFileCodeParser();
        MultiFileCodeResult result = parser.parseCode(codeContent);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getHtmlCode());
        Assertions.assertNotNull(result.getCssCode());
        Assertions.assertNotNull(result.getJsCode());
        Assertions.assertNotNull(result.getDescription());
        Assertions.assertTrue(result.getDescription().contains("留言板应用"));
        Assertions.assertTrue(result.getDescription().contains("添加和删除留言"));
    }

    @Test
    void parseMultiFileCodeWithJavascriptTag() {
        String codeContent = """
                ```html
                <html><body>Test</body></html>
                ```
                ```javascript
                console.log('test');
                ```
                """;
        MultiFileCodeParser parser = new MultiFileCodeParser();
        MultiFileCodeResult result = parser.parseCode(codeContent);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getHtmlCode());
        Assertions.assertNotNull(result.getJsCode());
    }

    @Test
    void executeParserHtml() {
        String codeContent = """
                ```html
                <html><body>Test</body></html>
                ```
                """;
        Object result = CodeParserExecutor.executeParser(codeContent, CodeGenTypeEnum.HTML);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result instanceof HtmlCodeResult);
        HtmlCodeResult htmlResult = (HtmlCodeResult) result;
        Assertions.assertNotNull(htmlResult.getHtmlCode());
    }

    @Test
    void executeParserMultiFile() {
        String codeContent = """
                ```html
                <html><body>Test</body></html>
                ```
                ```css
                body { margin: 0; }
                ```
                ```js
                console.log('test');
                ```
                """;
        Object result = CodeParserExecutor.executeParser(codeContent, CodeGenTypeEnum.MULTI_FILE);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result instanceof MultiFileCodeResult);
        MultiFileCodeResult multiFileResult = (MultiFileCodeResult) result;
        Assertions.assertNotNull(multiFileResult.getHtmlCode());
        Assertions.assertNotNull(multiFileResult.getCssCode());
        Assertions.assertNotNull(multiFileResult.getJsCode());
    }
}
