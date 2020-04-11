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

    private static boolean isPNGConvertSupported(Project project) {
        return true;
    }

    public static void formatWebp(File imgFile) {
        if (ImageUtil.isImage(imgFile)) {
            File webpFile = new File(imgFile.getPath().substring(0, imgFile.getPath().lastIndexOf(".")) + ".webp");
            CmdTools.cmd("cwebp", imgFile.getPath() + " -o " + webpFile.getPath() + " -m 6 -quiet");

            if (webpFile.length() < imgFile.length()) {
                System.out.println("图片：" + imgFile.getName() + " 转换成 Webp 成功，删除原文件，保留webp文件" +
                        "\n转换前图片大小：" + Math.round(imgFile.length() / 1024f) + " kB" +
                        "\n转换后图片大小：" + Math.round(webpFile.length() / 1024f) + " kB" +
                        "\n   优化的体积：" + Math.round((imgFile.length() - webpFile.length()) / 1024f) + " kB");

               // LogUtil.log(imgFile.getName() + " 转换成webp成功，删除原文件，保留webp文件");
                // 转换成功后，删除并添加webp到git
//                if (imgFile.exists()) {
//                    imgFile.delete();
//                }
                CmdTools.cmd("git add", webpFile.getPath());
            } else {
                //如果转换后的webp比原文件还大的话，抛弃
                if (webpFile.exists()) {
                    webpFile.delete();
                    LogUtil.log(imgFile.getName() + " 转换成webp后尺寸更大，舍弃转换后的webp文件");
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
