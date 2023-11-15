## 工程结构

```
fuck-engine
├── dependencies -- 工程Maven顶级依赖，统一控制版本和依赖
├── engine-assistant -- 核心通用代码包
├    ├── assistant-core -- 核心通用代码
├    └── assistant-autoconfigure -- Assistant  模块统一 Starter
├── engine-cache -- 缓存模块
├    ├── cache-core -- 缓存通用代码
├    ├── cache-sdk-caffeine -- Caffeine 缓存配置相关代码模块
├    ├── cache-sdk-jetcache -- JetCache 相关代码模块
├    ├── cache-sdk-redis -- Redis 缓存配置相关代码模块
├    ├── cache-sdk-redisson -- Redisson 相关代码模块
├    └── cache-spring-boot-starter -- Cache  模块统一 Starter
├── engine-captcha -- 验证码模块
├    ├── captcha-core -- 验证码共性通用代码
├    ├── captcha-sdk-behavior -- 行为验证码（包括拼图滑块、文字点选）
├    ├── captcha-sdk-graphic -- 传统图形验证码（包括算数类型、中文类型、字母类型、GIF类型）
├    ├── captcha-sdk-hutool -- Hutool验证码（包括圆圈干扰、扭曲干扰、线段干扰）
├    └── captcha-spring-boot-starter -- Captcha  模块统一 Starter
├── engine-data -- 数据访问模块
├    ├── data-core -- 数据访问共性通用代码
├    ├── data-sdk-mybatis-plus -- MybatisPlus 相关代码模块
├    └── data-spring-boot-starter -- Data 模块统一 Starter
├── engine-facility -- 微服务基础设施模块
├    ├── facility-alibaba-spring-boot-starter -- 面向 Spring Cloud Alibaba 的微服务基础设施适配模块
├    ├── facility-core -- 基础设施共性通用代码
├    └── facility-gateway-spring-boot-starter -- Alibaba Sentinel 在 Gateway 环境下基础设施适配模块
├── engine-message -- 消息模块
├    ├── message-core -- 消息共性通用代码
├    ├── message-kafka-spring-boot-starter -- 基础 Kafka 配置 Starter
├    ├── message-rabbitmaq-spring-boot-starter -- 基础 RabbitMQ 配置 Starter
├    ├── message-sdk-websocket -- 基于 WebSocket 的消息代码模块
├    ├── message-security-spring-boot-starter -- 安全相关事件统一 Starter
├    └── message-spring-boot-starter -- Message  模块统一 Starter
├── engine-oauth2 -- OAuth2 认证模块
├    ├── oauth2-core -- OAuth2 共性通用代码模块
├    ├── oauth2-sdk-authentication -- Spring Authorization Server 认证逻辑处理模块
├    ├── oauth2-sdk-authorization -- Spring Authorization Server 授权逻辑处理模块
├    ├── oauth2-sdk-data -- 封装的 Spring Authorization Server 数据访问代码模块
├    └── oauth2-sdk-management -- Spring Authorization Server 应用管理模块
├── engine-rest -- 服务Rest接口模块
├    ├── rest-core -- 服务 Rest 接口共性通用代码
├    ├── rest-sdk-condition -- 服务级 REST 相关自定义条件注解模块
├    ├── rest-sdk-protect -- 前后端数据加密、接口幂等、防刷、Xss 和 SQL 注入 Rest API 防护模块
├    ├── rest-sdk-service -- 基于 REST 的服务基础内容配置代码模块
├    ├── rest-spring-boot-starter -- Rest 模块统一 Starter(包括通用CRUD代码)
├    └── web-server-spring-boot-starter -- 基础 Web Server 配置模块
├── engine-sms -- 短信接入模块
├    ├── sms-core -- 短信共性通用代码模块
├    ├── sms-sdk-aliyun -- 阿里云短信发送模块
├    ├── sms-sdk-chinamobile -- 移动短信发送模块
├    ├── sms-sdk-huawei -- 华为短信发送模块
├    ├── sms-sdk-jd -- 京东短信发送模块
├    ├── sms-sdk-netease -- 网易短信发送模块
├    ├── sms-sdk-qiniu -- 七牛短信发送模块
├    ├── sms-sdk-tencent -- 腾讯短信发送模块
├    ├── sms-sdk-upyun -- 又拍短信发送模块
├    └── sms-spring-boot-starter -- SMS 模块统一 Starter
├── engine-supplier -- 应用支持模块
├    ├── supplier-sdk-message -- 消息功能支持模块
├    ├── supplier-sdk-upms-logic -- UPMS 基础服务支持模块
├    └── supplier-sdk-upms-rest -- UPMS 基础服务 REST 模块
└──  readme -- README
```