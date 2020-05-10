# learnPinyin_backend

这是微信小程序[易学拼音pinyin](https://github.com/Sebastian-Getts/learnPinyin)的后台项目，为小程序提供接口调用服务。

## 后台架构

- SpringBoot 2.2.5.RELEASE
  - Lombok
  - jpinyin
  - httpcomponents
- Juhe API

**note:**  使用时请先注册聚合数据获取API相关的appKey。

## 目前提供的功能

- 产生随机汉字、拼音
- 汉字的详细释义（调JuheAPI）
- 历史上的今天（调Juhe API）

