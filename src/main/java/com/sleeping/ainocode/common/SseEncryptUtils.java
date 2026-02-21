package com.sleeping.ainocode.common;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;

import java.nio.charset.StandardCharsets;

public class SseEncryptUtils {

    private SseEncryptUtils() {
    }

    public static String encrypt(String content) {
        if (StrUtil.isBlank(content)) {
            return "";
        }
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        return Base64.encode(bytes);
    }

    public static String decrypt(String encryptedContent) {
        if (StrUtil.isBlank(encryptedContent)) {
            return "";
        }
        byte[] bytes = Base64.decode(encryptedContent);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
