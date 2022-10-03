# 拍拍女朋友

## 介绍
使用测试微信公众号自动给女朋友推送信息，包括：
- 每日快送：天气，恋爱纪念日，等等
- 新增票务：新购买的火车票（即将上线机票和酒店）

## 前置条件
前往https://chromedriver.chromium.org/downloads 以下载对应的chromedriver执行文件，将它移动到/Pat-Pat-Girlfriend/chromedriver.exe

## Log
**2022-10-01**

1. 每天拉取携程订单(火车票和机酒)，如有新订单则推送微信公众号
2. 每日推送微信公众号关于天气与恋爱纪念日

**2022-10-02**

迁移成用maven管控packages

**2022-10-03**

建立server demo来接收用户点击公众号菜单的事件

## TODO
- 联通用户点击菜单事件和回应
- 利用Github Action部署自动化定时推送
   
