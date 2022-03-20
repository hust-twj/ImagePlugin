### 1 介绍

该 gradle 插件的主要功能是扫描并检测项目中的大体积图片（大小可配置），并将其转换为 webp 格式，扫描和转换的结果默认会写入`check_result.txt`文件中以便于展示。同时，该插件还可以检测重复图片，对于内容相同（MD5 值相同）但命名不同的图片，同样也会显示在扫描结果文件中。

插件主要功能如下：
 - 大图检测
 - 重复图片检测
 - 大图转 webp 后删除原图并添加到git
 - 将大图和重复图片的检测结果写入到指定文件，便于统一查看
 - 丰富的参数配置，包括：
    - 全局开关配置
    - 阈值配置
    - 转换开关
    - 文件白名单
    - 指定扫描模块范围
    - 自定义输出文件等

### 2  准备

首先，该插件将`png`或`jpg`图片转为`webp`图片是利用`cwebp`工具。`cwebp`命令和使用可以查阅[ 官方文档 ](https://developers.google.com/speed/webp/docs/cwebp) 。如果没有`cwebp`环境，则需要增加`cwebp`的环境配置。具体配置方式如下：

#### 2.1 Mac 系统

在`terminal`中可以使用 `Homebrew `安装：`brew install webp`。安装完毕后，在`terminal`中输入`cwebp`，如果可以找到 `cwebp ` 命令，就说明安装成功。

#### 2.2 Windows 系统

[点这个链接（需科学上网）](https://storage.googleapis.com/downloads.webmproject.org/releases/webp/index.html) 下载`cwebp.exe`，将最新版本对应的`zip`文件下载在本地，并解压，然后将其中的`bin`目录添加到系统的环境变量。

系统环境变量配置完毕后，在命令行工具中输入`cwebp`，如果可以找到 `cwebp ` 命令，就说明安装成功。

### 3 使用介绍

#### 3.1 添加依赖

配置好上述环境后，就可以使用插件的功能。步骤如下：

首先，在项目的`build.gradle `中添加插件依赖：

```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.husttwj.plugin:webp_plugin:1.0.3'
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

    //是否检测：插件全局开关。false 时，不会开启扫描。
    //如果不启用该插件，该参数可设为 false，以节省编译时间
    isCheckEnable = true

    //找到大体积图片后是否转换为 webp。false 时，仅仅输出日志信息，不会执行转换 webp 的操作
    canConvertWebp = true

    //大图片判定阈值：默认为20Kb，图片体积超过该值才会被转换
    largeImageThreshold = 20

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

### 4 效果演示

应用该插件，对项目进行扫描，结果在'check_result.txt'中展示，如下：
![扫描结果](https://github.com/hust-twj/Resources/blob/master/images/check_result.jpg?raw=true)

上图 4 处标记的的意义：

- 第 1 处：显示对应模块的检测结果（默认超过20k判定为大图）；

- 第 2 处：显示大图转换的结果，包含转换前大小、转换后大小、优化的大小；

- 第 3 处：显示优化体积的累计值（全部优化大小以最后一个值为准）；

- 第 4 处：显示重复图片的检测结果（如果有的话）

通过上述文件，大图和重复图片检测结果便一目了然。
