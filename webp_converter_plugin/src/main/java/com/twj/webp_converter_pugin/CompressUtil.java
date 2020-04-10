package com.twj.webp_converter_pugin;

import java.io.File;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/4.
 */
public class CompressUtil {

    private static final String TAG = CompressUtil.class.getSimpleName();

    public static void compressImg(File imgFile) {
        if (!ImageUtil.isImage(imgFile)) {
            return;
        }
        long oldSize = imgFile.length();
        long newSize;
        if (ImageUtil.isJPG(imgFile)) {
            String tempFilePath = imgFile.getPath().substring(0, imgFile.getPath().lastIndexOf(".")) + "_temp" +
                    imgFile.getPath().substring(imgFile.getPath().lastIndexOf("."));
            CmdTools.cmd("guetzli", imgFile.getPath() + " " + tempFilePath);
            File tempFile = new File(tempFilePath);
            newSize = tempFile.length();
            LogUtil.log("newSize = " + newSize);
            if (newSize < oldSize) {
                String imgFileName = imgFile.getPath();
                if (imgFile.exists()) {
                    imgFile.delete();
                }
                tempFile.renameTo(new File(imgFileName));
            } else {
                if (tempFile.exists()) {
                    tempFile.delete();
                }
            }

        } else {
            CmdTools.cmd("pngquant", "--skip-if-larger --speed 1 --nofs --strip --force --output "
                    + imgFile.getPath() + " -- " + imgFile.getPath());
            newSize = new File(imgFile.getPath()).length();
        }

        LogUtil.log(TAG, imgFile.getPath(), String.valueOf(oldSize), String.valueOf(newSize));
    }
}
