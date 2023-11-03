package cn.fuck.engine.rest.condition.constants;

import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;

/**
 * <p>Description: Rest 模块常量 </p>
 */
public interface RestConstants extends BaseConstants {


    String PROPERTY_OPENFEIGN_OKHTTP = PROPERTY_SPRING_CLOUD_OPENFEIGN + ".okhttp";
    String PROPERTY_OPENFEIGN_HTTPCLIENT = PROPERTY_SPRING_CLOUD_OPENFEIGN + ".httpclient";
    String PROPERTY_REST_SCAN = PROPERTY_PREFIX_REST + ".scan";
    String ITEM_PLATFORM_DATA_ACCESS_STRATEGY = PROPERTY_PREFIX_PLATFORM + ".data-access-strategy";
    String ITEM_PLATFORM_ARCHITECTURE = PROPERTY_PREFIX_PLATFORM + ".architecture";

    String ITEM_SCAN_ENABLED = PROPERTY_REST_SCAN + PROPERTY_ENABLED;
    String ITEM_OPENFEIGN_OKHTTP_ENABLED = PROPERTY_OPENFEIGN_OKHTTP + PROPERTY_ENABLED;
    String ITEM_OPENFEIGN_HTTPCLIENT_ENABLED = PROPERTY_OPENFEIGN_HTTPCLIENT + ".hc5" + PROPERTY_ENABLED;
    String ITEM_PROTECT_CRYPTO_STRATEGY = PROPERTY_PREFIX_CRYPTO + ".crypto-strategy";

    String CACHE_NAME_TOKEN_IDEMPOTENT = CACHE_TOKEN_BASE_PREFIX + "idempotent:";
    String CACHE_NAME_TOKEN_ACCESS_LIMITED = CACHE_TOKEN_BASE_PREFIX + "access_limited:";
    String CACHE_NAME_TOKEN_SECURE_KEY = CACHE_TOKEN_BASE_PREFIX + "secure_key:";
}
