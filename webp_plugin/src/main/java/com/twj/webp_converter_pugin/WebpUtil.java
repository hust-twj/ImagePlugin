package com.twj.webp_converter_pugin;

import org.gradle.api.Project;

import java.io.File;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/4.
 */
public class WebpUtil {

    //api>=14设备支持webp
    public static final int VERSION_SUPPORT_WEBP = 14;
    public static final String TAG = "Webp";
    //总优化的大小
    private static long totalOptimizedSize = 0;

    private static boolean isPNGConvertSupported(Project project) {
        return true;
    }

    public static void formatWebp(File imgFile) {
        if (ImageUtil.isImage(imgFile)) {
            File webpFile = new File(imgFile.getPath().substring(0, imgFile.getPath().lastIndexOf(".")) + ".webp");
            CmdTools.cmd("cwebp", imgFile.getPath() + " -o " + webpFile.getPath() + " -m 6 -quiet");

            if (webpFile.length() < imgFile.length()) {
                //当前图片优化的大小
                int optimizedSize =  Math.round((imgFile.length() - webpFile.length()) / 1024f);
                String outputText = "图片：" + imgFile.getName() + " 转换 Webp 成功，删除原文件，保留webp文件" +
                        "\n转换前图片大小：" + Math.round(imgFile.length() / 1024f) + " kB" +
                        " | 转换后图片大小：" + Math.round(webpFile.length() / 1024f) + " kB" +
                        " | 优化的体积：" + optimizedSize + " kB ";

                totalOptimizedSize += optimizedSize;
                FileUtil.writeOrPrintContent(outputText);
                FileUtil.writeOrPrintContent("总优化体积：" + totalOptimizedSize + " kB\n");
                // 转换成功后，删除并添加webp到git
//                if (imgFile.exists()) {
//                    imgFile.delete();
//                }
                CmdTools.cmd("git add", webpFile.getPath());
            } else {
                //如果转换后的webp比原文件还大，舍弃
                if (webpFile.exists()) {
                    webpFile.delete();
                    String outputText = imgFile.getName() + " 转换 webp 后尺寸更大，舍弃转换后的文件\n";
                    FileUtil.writeOrPrintContent(outputText);
                }
            }
        }
    }

    public static void securityFormatWebp(File imgFile, Project project) {
        if (!isPNGConvertSupported(project)) {
            try {
                throw new Exception("minSDK < 14，Webp 转换不支持");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ImageUtil.isImage(imgFile)) {
            if (imgFile.getName().endsWith(Const.JPG) || imgFile.getName().endsWith(Const.JPEG)) {
                //jpg
                formatWebp(imgFile);
            } else if (imgFile.getName().endsWith(Const.PNG)) {
                //png
                if (!ImageUtil.isAlphaPNG(imgFile)) {
                    //不包含透明通道
                    formatWebp(imgFile);
                } else {
                    //包含透明通道的png，进行压缩
                    CompressUtil.compressImg(imgFile);
                }
            }
        }

    }

}
