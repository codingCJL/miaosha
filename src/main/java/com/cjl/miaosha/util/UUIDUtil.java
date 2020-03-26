package com.cjl.miaosha.util;

import java.util.UUID;

/**
 * @author cjl
 * @date 2020/3/25 14:27
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
