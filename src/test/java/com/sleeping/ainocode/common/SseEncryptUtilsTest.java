package com.sleeping.ainocode.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SseEncryptUtilsTest {

    @Test
    void testEncryptAndDecrypt() {
        String original = "Hello World 你好世界";
        String encrypted = SseEncryptUtils.encrypt(original);
        String decrypted = SseEncryptUtils.decrypt(encrypted);
        Assertions.assertEquals(original, decrypted);
    }

    @Test
    void testEncryptWithSpaces() {
        String original = "  多个空格  和制表符\t测试  ";
        String encrypted = SseEncryptUtils.encrypt(original);
        String decrypted = SseEncryptUtils.decrypt(encrypted);
        Assertions.assertEquals(original, decrypted);
    }

    @Test
    void testEncryptEmptyString() {
        String original = "";
        String encrypted = SseEncryptUtils.encrypt(original);
        Assertions.assertEquals("", encrypted);
    }

    @Test
    void testEncryptNull() {
        String encrypted = SseEncryptUtils.encrypt(null);
        Assertions.assertEquals("", encrypted);
    }

    @Test
    void testDecryptEmptyString() {
        String decrypted = SseEncryptUtils.decrypt("");
        Assertions.assertEquals("", decrypted);
    }

    @Test
    void testDecryptNull() {
        String decrypted = SseEncryptUtils.decrypt(null);
        Assertions.assertEquals("", decrypted);
    }

    @Test
    void testEncryptCodeContent() {
        String code = "<html>\n  <body>\n    <h1>Hello</h1>\n  </body>\n</html>";
        String encrypted = SseEncryptUtils.encrypt(code);
        String decrypted = SseEncryptUtils.decrypt(encrypted);
        Assertions.assertEquals(code, decrypted);
    }
}
