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
     * 图片扫描的子模块范围
     * 默认扫描全部模块的图片，配置后仅在配置范围内才会扫描
     */
    Iterable<String> scopeList

    /**
     * 优化图片白名单（名单中的图片不转换）
     */
    Iterable<String> whiteList

    /**
     * 是否检测图片唯一性（防止图片名不同，但图片内容一样）
     */
    boolean checkUniqueImage

    /**
     * 输出文件名
     */
    String outputFileName

    PluginExtension() {
        isCheckSize = true
        canConvertWebp = true
        checkUniqueImage = true
        largeImageThreshold = 20
        scopeList = []
        whiteList = []
    }

}