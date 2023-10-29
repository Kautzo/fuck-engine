package cn.fuck.engine.assistant.core.definition.constants;

/**
 * <p>Description: 基础共用常量值常量 </p>
 */
public interface BaseConstants {

    String NONE = "none";
    String CODE = "code";

    /* ---------- 配置属性通用常量 ---------- */

    String PROPERTY_ENABLED = ".enabled";
    String PROPERTY_PREFIX_SPRING = "spring";
    String PROPERTY_PREFIX_FUCK = "fuck";

    String PROPERTY_SPRING_CLOUD = PROPERTY_PREFIX_SPRING + ".cloud";
    String PROPERTY_SPRING_CLOUD_OPENFEIGN = PROPERTY_SPRING_CLOUD + ".openfeign";
    String PROPERTY_SPRING_DATA = PROPERTY_PREFIX_SPRING + ".data";
    String PROPERTY_SPRING_DATA_REDIS = PROPERTY_SPRING_DATA + ".redis";
    String PROPERTY_SPRING_JPA = PROPERTY_PREFIX_SPRING + ".jpa";
    String ANNOTATION_PREFIX = "${";
    String ANNOTATION_SUFFIX = "}";

    /* ---------- FUCK 自定义配置属性 ---------- */
    String PROPERTY_PREFIX_ACCESS = PROPERTY_PREFIX_FUCK + ".access";
    String PROPERTY_PREFIX_CACHE = PROPERTY_PREFIX_FUCK + ".cache";
    String PROPERTY_PREFIX_CAPTCHA = PROPERTY_PREFIX_FUCK + ".captcha";
    String PROPERTY_PREFIX_CLIENT = PROPERTY_PREFIX_FUCK + ".client";
    String PROPERTY_PREFIX_CRYPTO = PROPERTY_PREFIX_FUCK + ".crypto";
    String PROPERTY_PREFIX_ENDPOINT = PROPERTY_PREFIX_FUCK + ".endpoint";
    String PROPERTY_PREFIX_EVENT = PROPERTY_PREFIX_FUCK + ".event";
    String PROPERTY_PREFIX_LOG_CENTER = PROPERTY_PREFIX_FUCK + ".log-center";
    String PROPERTY_PREFIX_API = PROPERTY_PREFIX_FUCK + ".api";
    String PROPERTY_PREFIX_MESSAGE = PROPERTY_PREFIX_FUCK + ".message";
    String PROPERTY_PREFIX_DATA = PROPERTY_PREFIX_FUCK + ".data";
    String PROPERTY_PREFIX_NOSQL = PROPERTY_PREFIX_FUCK + ".nosql";
    String PROPERTY_PREFIX_OAUTH2 = PROPERTY_PREFIX_FUCK + ".oauth2";
    String PROPERTY_PREFIX_OSS = PROPERTY_PREFIX_FUCK + ".oss";
    String PROPERTY_PREFIX_PAY = PROPERTY_PREFIX_FUCK + ".pay";
    String PROPERTY_PREFIX_PLATFORM = PROPERTY_PREFIX_FUCK + ".platform";
    String PROPERTY_PREFIX_REST = PROPERTY_PREFIX_FUCK + ".rest";
    String PROPERTY_PREFIX_SECURE = PROPERTY_PREFIX_FUCK + ".secure";
    String PROPERTY_PREFIX_SMS = PROPERTY_PREFIX_FUCK + ".sms";
    String PROPERTY_PREFIX_SWAGGER = PROPERTY_PREFIX_FUCK + ".swagger";
    String PROPERTY_PREFIX_IP2REGION = PROPERTY_PREFIX_FUCK + ".ip2region";

    /* ---------- Spring 家族配置属性 ---------- */

    String ITEM_SWAGGER_ENABLED = PROPERTY_PREFIX_SWAGGER + PROPERTY_ENABLED;
    String ITEM_SPRING_APPLICATION_NAME = PROPERTY_PREFIX_SPRING + ".application.name";
    String ITEM_SPRING_SESSION_REDIS = PROPERTY_PREFIX_SPRING + ".session.redis.repository-type";

    String ANNOTATION_APPLICATION_NAME = ANNOTATION_PREFIX + ITEM_SPRING_APPLICATION_NAME + ANNOTATION_SUFFIX;


    /* ---------- 通用缓存常量 ---------- */

    String CACHE_PREFIX = "cache:";
    String CACHE_SIMPLE_BASE_PREFIX = CACHE_PREFIX + "simple:";
    String CACHE_TOKEN_BASE_PREFIX = CACHE_PREFIX + "token:";

    String AREA_PREFIX = "data:";


    /* ---------- Oauth2 和 Security 通用缓存常量 ---------- */

    /**
     * Oauth2 模式类型
     */
    String PASSWORD = "password";
    String SOCIAL_CREDENTIALS = "social_credentials";

    String OPEN_API_SECURITY_SCHEME_BEARER_NAME = "FUCK_AUTH";

    String BEARER_TYPE = "Bearer";
    String BEARER_TOKEN = BEARER_TYPE + SymbolConstants.SPACE;
    String BASIC_TYPE = "Basic";
    String BASIC_TOKEN = BASIC_TYPE + SymbolConstants.SPACE;
    String AUTHORITIES = "authorities";
    String AVATAR = "avatar";
    String EMPLOYEE_ID = "employeeId";
    String LICENSE = "license";
    String OPEN_ID = "openid";
    String PRINCIPAL = "principal";
    String ROLES = "roles";
    String SOURCE = "source";
    String USERNAME = "username";
}
