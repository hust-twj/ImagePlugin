package com.twj.plugin

/**
 * webp插件参数的配置
 */
class PluginExtension {

    /**
     * 是否检测大体积图片
     */
    boolean isCheckSize

    /**
     * 是否转换大体积图片为Webp格式
     */
    boolean canConvertWebp

    /**
     * 大尺寸图片阈值，超过该值才会检测
     * 默认大小：20kB
     */
    int largeImageThreshold

    /**
     * 优化图片白名单
     */
    Iterable<String> whiteList

    PluginExtension() {
        isCheckSize = true
        canConvertWebp = true
        largeImageThreshold = 20
        whiteList = []
    }

}