package com.cjl.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * @author cjl
 * @date 2020/3/24 17:20
 */
public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    //加盐值
    private static final String salt="1a2b3c";

    //将用户输入的密码和固定的salt加密
    public static String inputPassToFormPass(String inputPass){
        String str = inputPass+salt;
        return md5(str);
    }

    //将用户输入的密码和固定的salt加密的结果和随机salt加密
    public static String formPassToDBPass(String formPass,String salt){
        String str = formPass+salt;
        return md5(str);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));
        System.out.println(formPassToDBPass("ceed236a1804014d4425fa8dcf1e539c", "a90d"));
    }
}
