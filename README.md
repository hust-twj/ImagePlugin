### 1 介绍

该 gradle 插件的主要功能是扫描并检测项目中的大体积图片，并将其转换为 webp 图片（默认开启），扫描和转换的结果默认会在`check_result.txt`中展示。同时，该插件还可以检测图片的唯一性，比如对于内容相同但命名不同的重复图片，同样也会在扫描结果文件中显示。

功能如下：
 - 大图检测并转 webp
 - 转 webp 后删除原图，并将新 webp 图添加到 git
 - 重复图片检测
 - 将检测结果写入到指定文件
 - 丰富的参数配置：全局开关；阈值控制；转换开关；文件白名单；扫描模块范围、输出文件等

### 2  准备

首先，需要明白的是，系统将`png`或者`jpg`图片转为`webp`图片利用的是`cwebp`这个工具。这也是该插件的原理。

`cwebp`命令和使用可以查阅官方文档：https://developers.google.com/speed/webp/docs/cwebp 

默认情况下是没有`cwebp`环境的，因此需要增加`cwebp`的环境配置。具体配置方式如下：

#### 2.1 Mac 系统

在`terminal`中可以使用 `Homebrew `直接安装：`brew install webp`。

安装完毕后，在`terminal`中输入`cwebp`，如果可以找到 `cwebp ` 命令，就说明安装成功。

#### 2.1 Windows 系统

首先下载`cwebp.exe`，链接如下（需科学上网）：

https://storage.googleapis.com/downloads.webmproject.org/releases/webp/index.html

将最新版本对应的`zip`文件下载在本地，并解压，然后将其中的`bin`目录添加到系统的环境变量。

环境变量配置完毕后，在命令行工具中输入`cwebp`，如果可以找到 `cwebp ` 命令，就说明安装成功。


### 3 使用

#### 3.1  依赖

配置好了环境后，就可以使用插件的`webp`图片转换功能。

首先，在项目的`build.gradle `中添加插件依赖：

```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.husttwj.plugin:webp_plugin:1.0.1'
    }
}

```


#### 3.2 引入插件并配置参数

然后，在 app 的 `build.gradle`中引入插件，还可以设置一些可配置的参数：

```
//引入插件
apply plugin: 'webp_plugin'

//设置配置参数(可选)
webpPlugin {

    //是否检测：插件全局开关。false时，不会开启扫描。
    //建议项目检测后，该参数设为false，以节省编译时间
    isCheckEnable = true

    //找到大体积图片后是否转换为webp：false时，仅仅输出日志信息，不会执行转换webp的操作
    canConvertWebp = true

    //大图片判定阈值：默认为20Kb，图片体积超过该值才会被转换
    largeImageThreshold = 30

   //转换白名单：在白名单中图片不会被转换webp
    whiteList = [
            "bg_2.png",
            "back_icon.png"
    ]

    //指定扫描范围：只有在范围内的模块，才会进行扫描
    scopeModuleList = [
            "app",
            "module_a",
            "module_b"
    ]

    //是否检测图片的唯一性：根据图片文件的 MD5 值判断是否为同一张图片
    checkUniqueImage = true
    
    //扫描结果文件名
    outputFileName = "check_result.txt"
}

```

