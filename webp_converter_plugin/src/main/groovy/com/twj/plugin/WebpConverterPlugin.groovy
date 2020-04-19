package com.twj.plugin

import com.twj.webp_converter_pugin.ImageUtil
import com.twj.webp_converter_pugin.WebpUtil
import com.twj.webp_converter_pugin.MD5Util

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileTree

class WebpConverterPlugin implements Plugin<Project> {

    // 定义gradle配置名称
    static final String EXT_CONFIG_NAME = 'webpPlugin'

    @Override
    void apply(Project project) {
        //创建配置项
        project.extensions.create(EXT_CONFIG_NAME, PluginExtension)
        project.afterEvaluate {
            //读取配置文件
            PluginExtension extension = project.extensions.findByName(EXT_CONFIG_NAME)
            //不开启检测的话，直接返回
            if (!extension.isCheckSize) {
                return
            }

            def subProject = project.getRootProject().getSubprojects()
            def imagesMap = new HashMap<String, String>()
            subProject.eachWithIndex {
                Project sub, int index ->

                    //只在配置文件中的
                    Iterable<String> scopeList = extension.scopeList
                    if (!scopeList.empty && !scopeList.contains(sub.name)) {
                        return
                    }
                    // println "--- Project-- ': $index -- 模块名：$sub.name'"
                    //遍历所有子 Project 的 src/main/res 文件目录
                    FileTree fileTree = sub.fileTree(dir: 'src/main/res')
                    fileTree.each { File file ->

                        Iterable<String> whiteList = extension.whiteList
                        int largeImageThreshold = extension.largeImageThreshold
                        boolean canConvertWebp = extension.canConvertWebp

                        if (!whiteList.contains(file.getName()) && ImageUtil.isImage(file)
                                && ImageUtil.isBigSizeImage(file, largeImageThreshold)) {
                            int size = Math.round(file.length() / 1024)
                            System.out.println("模块 $sub.name 中找到可优化大图片：$file.name " +
                                    "其体积为为：$size  Kb")
                            //开启转换Webp开关
                            if (canConvertWebp) {
                                WebpUtil.formatWebp(file)
                            }
                        }

                        boolean checkUniqueImage = extension.checkUniqueImage
                        if (checkUniqueImage && ImageUtil.isImage(file)) {
                            checkUniqueImages(imagesMap, file)
                        }
                    }
            }
        }

    }

    /**
     * 检测图片唯一性
     * @param imageMap 图片集合
     * @param imageFile 图片文件
     */
    private static void checkUniqueImages(HashMap<String, String> imageMap, File imageFile) {
        String fileMd5String = MD5Util.getFileMD5(imageFile)
        if (imageMap.containsKey(fileMd5String)) {
            System.out.println("重复图片: ${imageFile.getName()} --- ${imageMap.get(fileMd5String)}")
        } else {
            imageMap.put(fileMd5String, imageFile.getName())
        }
    }

}