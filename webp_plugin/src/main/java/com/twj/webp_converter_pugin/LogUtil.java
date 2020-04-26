package com.twj.webp_converter_pugin;


/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/4.
 */
public class LogUtil {

    public static void log(String stage, String filePath, String oldInfo, String newInfo) {
        println("[" + stage + "][" + filePath + "][" + "oldInfo: " + oldInfo + "][newInfo: " + newInfo + "]");
    }

    public static void log(String stage, String info, String result) {
        println("[+" + stage + "][ +Info:" + info + "][Result: " + result + "]");
    }

    public static void log(String str) {
        println(str);
    }

    public static void log(Exception exception) {
        System.out.println(exception);
    }

    private static void println(String str) {
        System.out.println(str);
    }
}
