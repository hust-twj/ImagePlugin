package com.twj.plugin

import com.twj.webp_converter_pugin.ImageUtil
import com.twj.webp_converter_pugin.WebpUtil
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileTree

public class WebpConverterPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        def subProject = project.getRootProject().getSubprojects();
        subProject.eachWithIndex {
            Project sub, int index ->
                println "--- Project-- ': $index -- 模块名：$sub.name'"
                //遍历所有子 Project 的 src/main/res 文件目录
                FileTree fileTree = sub.fileTree(dir: 'src/main/res')
                fileTree.each { File file ->
                    //todo 需要keep住一些不需要转换的图片
                    // todo 阈值可配置
                    if (ImageUtil.isImage(file) && ImageUtil.isBigSizeImage(file, 20)) {
                        System.out.println("模块 $sub.name 中找到可优化图片：$file.name 其大小为：${file.length()}")
                        WebpUtil.formatWebp(file)
                    }

                }
        }

    }

}