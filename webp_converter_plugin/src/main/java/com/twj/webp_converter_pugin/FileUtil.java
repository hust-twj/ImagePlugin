package com.twj.webp_converter_pugin;

import java.io.File;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/4.
 */
public class FileUtil {

    private static String rootDir;

    public static void setRootDir(String dir) {
        rootDir = dir;
    }

    public static String getRootDirPath() {
        return rootDir;
    }

    public static File getToolsDir() {
        return new File(rootDir + "/mctools/");
    }

    public static String getToolsDirPath() {
        return rootDir + "/mctools/";
    }
}
