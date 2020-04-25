package com.twj.webp_converter_pugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/4.
 */
public class FileUtil {

    private static String rootDir;

    /**
     * 输出文件路径
     */
    private static String outputFilePath;

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

    public static void setOutputFilePath(String filePath) {
        outputFilePath = filePath;
    }

    public static String getOutputFilePath() {
        return outputFilePath;
    }

    /**
     * 写入内指定目录
     *
     * @param filePath 文件路径
     * @param content  写入内容
     */
    public static void WriteToFile(String filePath, String content) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filePath, true));
            pw.println(content);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将内容写入文件或者打印输出
     */
    public static void writeOrPrintContent(String content) {
        if (StringUtil.isNotEmpty(FileUtil.getOutputFilePath())) {
            WriteToFile(FileUtil.getOutputFilePath(), content);
        } else {
            System.out.println(content);
        }
    }

}
