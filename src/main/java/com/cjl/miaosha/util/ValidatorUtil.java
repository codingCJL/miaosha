package com.cjl.miaosha.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cjl
 * @date 2020/3/25 9:46
 * 参数校验工具
 */
public class ValidatorUtil {
    private static final Pattern mobile_pattern=Pattern.compile("1\\d{10}");
    public static boolean isMobile(String src){
        if(StringUtils.isEmpty(src)){
            return false;
        }
        Matcher m=mobile_pattern.matcher(src);
        return m.matches();
    }

    public static void main(String[] args) {

    }

}
