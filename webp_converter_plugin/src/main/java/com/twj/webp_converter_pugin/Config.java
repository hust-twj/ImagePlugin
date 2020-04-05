package com.twj.webp_converter_pugin;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/4.
 */
public class Config {

    public static final String OPTIMIZE_WEBP_CONVERT = "ConvertWebp"; //webp化
    public static final String OPTIMIZE_COMPRESS_PICTURE = "Compress"; //压缩图片

    public float maxSize = 1024 * 1024;
    //是否检查大体积图片
    public boolean isCheckSize = true;
    //优化方式，webp化、压缩图片
    public String optimizeType = OPTIMIZE_WEBP_CONVERT;
    public boolean enableWhenDebug = true;
    //是否检查大像素图片
    public boolean isCheckPixels = true;
    public int maxWidth = 1000;
    public int maxHeight = 1000;
    //优化图片白名单
    public String[] whiteList = new String[]{};
    public String mctoolsDir = "";
    //是否支持webp化透明通道的图片,如果开启，请确保minSDK >= 18,或做了其他兼容措施
    public boolean isSupportAlphaWebp = false;
    public boolean multiThread = true;
    //大图检测白名单
    public String[] bigImageWhiteList = new String[]{};

    public void maxSize(float maxSize) {
        this.maxSize = maxSize;
    }

    public void isCheckSize(boolean check) {
        isCheckSize = check;
    }

    public void optimizeType(String optimizeType) {
        this.optimizeType = optimizeType;
    }

    public void isSupportAlphaWebp(boolean isSupportAlphaWebp) {
        this.isSupportAlphaWebp = isSupportAlphaWebp;
    }

    public void enableWhenDebug(boolean enableWhenDebug) {
        this.enableWhenDebug = enableWhenDebug;
    }

    public void isCheckPixels(boolean checkSize) {
        isCheckPixels = checkSize;
    }

    public void maxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void maxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void whiteList(String[] whiteList) {
        this.whiteList = whiteList;
    }

    public void mctoolsDir(String mctoolsDir) {
        this.mctoolsDir = mctoolsDir;
    }

    public void maxStroageSize(float maxSize) {
        this.maxSize = maxSize;
    }

    public void multiThread(boolean multiThread) {
        this.multiThread = multiThread;
    }

    public void bigImageWhiteList(String[] bigImageWhiteList) {
        this.bigImageWhiteList = bigImageWhiteList;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("<<<<<<<<<<<<<<McConfig>>>>>>>>>>>>" + "\n");
        result.append("maxSize :" + maxSize + "\n"
                + "isCheckSize: " + isCheckSize + "\n"
                + "optimizeType: " + optimizeType + "\n"
                + "enableWhenDebug: " + enableWhenDebug + "\n"
                + "isCheckPixels: " + isCheckPixels + "\n"
                + "maxWidth: " + maxWidth + ", maxHeight: " + maxHeight + "\n"
                + "mctoolsDir: " + mctoolsDir + "\n"
                + "isSupportAlphaWebp: " + isSupportAlphaWebp + "\n"
                + "multiThread: " + multiThread + "\n"
                + "whiteList : \n");
        for (String file : whiteList) {
            result.append("     -> : " + file + "\n");
        }
        result.append("bigImageWhiteList: \n");
        for (String file : bigImageWhiteList) {
            result.append("     -> : " + file + "\n");
        }
        result.append("<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>");
        return result.toString();
    }
}
