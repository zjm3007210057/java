package com.me.string;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by zjm on 2017/3/4.
 */
public class StringUtil {

    public static boolean isBlank(String source){
        return StringUtils.isBlank(source);
    }

    public static boolean isEmpty(String source){
        return StringUtils.isEmpty(source);
    }

    public static boolean isNotBlank(String source){
        return !isBlank(source);
    }

    public static boolean isNotEmpty(String source){
        return !isEmpty(source);
    }

    public static boolean equals(String s1, String s2){
        return StringUtils.equals(s1, s2);
    }

    public static boolean notEquals(String s1, String s2){
        return !equals(s1, s2);
    }
}
