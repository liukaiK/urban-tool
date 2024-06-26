package com.unicom.urban.common.util;

import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;

/**
 * 对称AES加密解密工具
 *
 * @author liukai
 */
public class AESUtil {

    private final static AES AES = new AES(new byte[]{-51, 117, -22, 4, 118, 77, -26, -8, 36, -11, 51, -25, 62, 50, -65, 12});

    /**
     * 加密
     */
    public static String encrypt(String str) {
        if (StringUtils.hasText(str)) {
            return AES.encryptHex(str);
        } else {
            return null;
        }

    }

    /**
     * 解密
     */
    public static String decrypt(String str) {
        if (StringUtils.hasText(str)) {
            return AES.decryptStr(str, StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }

}
