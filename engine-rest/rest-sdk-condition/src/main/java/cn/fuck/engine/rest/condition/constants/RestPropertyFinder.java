package cn.fuck.engine.rest.condition.constants;


import cn.fuck.engine.assistant.core.context.PropertyResolver;
import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;
import org.springframework.core.env.Environment;

/**
 * <p>Description: 策略模块配置获取器 </p>
 * @date : 2022/2/1 19:23
 */
public class RestPropertyFinder {

    public static String getApplicationName(Environment environment) {
        return PropertyResolver.getProperty(environment, BaseConstants.ITEM_SPRING_APPLICATION_NAME);
    }

    public static String getCryptoStrategy(Environment environment, String defaultValue) {
        return PropertyResolver.getProperty(environment, RestConstants.ITEM_PROTECT_CRYPTO_STRATEGY, defaultValue);
    }

    public static String getCryptoStrategy(Environment environment) {
        return PropertyResolver.getProperty(environment, RestConstants.ITEM_PROTECT_CRYPTO_STRATEGY);
    }

    public static boolean isScanEnabled(Environment environment) {
        return PropertyResolver.getBoolean(environment, RestConstants.ITEM_SCAN_ENABLED);
    }

    public static boolean isOpenFeignOkHttpEnabled(Environment environment) {
        return PropertyResolver.getBoolean(environment, RestConstants.ITEM_OPENFEIGN_OKHTTP_ENABLED);
    }

    public static boolean isOpenFeignHttpClientEnabled(Environment environment) {
        return PropertyResolver.getBoolean(environment, RestConstants.ITEM_OPENFEIGN_HTTPCLIENT_ENABLED);
    }

    public static String getDataAccessStrategy(Environment environment, String defaultValue) {
        return PropertyResolver.getProperty(environment, RestConstants.ITEM_PLATFORM_DATA_ACCESS_STRATEGY, defaultValue);
    }

    public static String getDataAccessStrategy(Environment environment) {
        return PropertyResolver.getProperty(environment, RestConstants.ITEM_PLATFORM_DATA_ACCESS_STRATEGY);
    }

    public static String getArchitecture(Environment environment, String defaultValue) {
        return PropertyResolver.getProperty(environment, RestConstants.ITEM_PLATFORM_ARCHITECTURE, defaultValue);
    }

    public static String getArchitecture(Environment environment) {
        return PropertyResolver.getProperty(environment, RestConstants.ITEM_PLATFORM_ARCHITECTURE);
    }
}
