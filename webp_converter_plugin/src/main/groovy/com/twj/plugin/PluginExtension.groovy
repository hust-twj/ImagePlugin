package com.twj.plugin

/**
 * 图片插件的配置类
 */
class PluginExtension {

    /**
     * 插件开关
     */
    boolean switchOn

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
        switchOn = true
        largeImageThreshold = 20
        whiteList = []
    }

}