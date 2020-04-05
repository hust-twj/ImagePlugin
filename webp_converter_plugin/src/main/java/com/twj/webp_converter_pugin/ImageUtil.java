package com.twj.webp_converter_pugin;

import java.io.File;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/4.
 */
public class ImageUtil {

    private static final String SIZE_TAG = "SizeCheck";

    /**
     * 是否为图片（不包含.9图）
     */
    public static boolean isImage(File file) {
        return (file.getName().endsWith(Const.JPG) ||
                file.getName().endsWith(Const.PNG) ||
                file.getName().endsWith(Const.JPEG)
        ) && !file.getName().endsWith(Const.DOT_9PNG);
    }

    public static boolean isJPG(File file) {
        return file.getName().endsWith(Const.JPG) || file.getName().endsWith(Const.JPEG);
    }

    public static boolean isPNG(File file) {
        return file.getName().endsWith(Const.PNG);
    }

    public static boolean isAlphaPNG(File filePath) {
        if (filePath.exists()) {
//            try {
//                ImageIO img = ImageIO.read(filePath);
//                img.colorModel.hasAlpha();
//            } catch (Exception e) {
//                LogUtil.log(e.getMessage());
//                return false;
//            }
        }
        return false;
    }

    /**
     * 是否为大体积图片
     * @param imgFile 图片文件
     * @param imageSizeKb 图片文件大小（kB）
     * @return
     */
    public static boolean isBigSizeImage(File imgFile, float imageSizeKb) {
        if (isImage(imgFile)) {
            if (imgFile.length() >= imageSizeKb * 1024) {
                LogUtil.log(SIZE_TAG, imgFile.getPath(), true + "");
                return true;
            }
        }
        return false;
    }

//    fun isBigPixelImage(imgFile: File, maxWidth: Int, maxHeight: Int): Boolean {
//            if (isImage(imgFile)) {
//                val sourceImg = ImageIO.read(FileInputStream(imgFile))
//                if (sourceImg.height > maxHeight || sourceImg.width > maxWidth) {
//                    return true
//                }
//            }
//        return false
//    }

}
