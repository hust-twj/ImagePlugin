package com.twj.webp_converter_pugin;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/25.
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

}
