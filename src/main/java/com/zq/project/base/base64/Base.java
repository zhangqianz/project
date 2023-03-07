package com.zq.project.base.base64;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author zhangqian
 * @date 2023/3/7 10:04
 * @description: base64加密解密算法
 */
public class Base {

    public static void main(String[] args) {
        String name="张骞";
        byte[] encode = Base64.getEncoder().encode(name.getBytes(StandardCharsets.UTF_8));
        System.out.println(Arrays.toString(encode));
        byte[] bytes = Base64.getDecoder().decode(encode);
        System.out.println(Arrays.toString(bytes));
    }
}
