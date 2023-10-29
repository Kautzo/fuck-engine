package cn.fuck.engine.assistant.core.definition.constants;

import org.dromara.hutool.core.date.DatePattern;

/**
 * <p>Description: 默认常量合集 </p>
 * @date : 2023/5/30 10:27
 */
public interface DefaultConstants {

    String AUTHORIZATION_ENDPOINT = "/oauth2/authorize";
    String TOKEN_ENDPOINT = "/oauth2/token";
    String TOKEN_REVOCATION_ENDPOINT = "/oauth2/revoke";
    String TOKEN_INTROSPECTION_ENDPOINT = "/oauth2/introspect";
    String DEVICE_AUTHORIZATION_ENDPOINT = "/oauth2/device_authorization";
    String DEVICE_VERIFICATION_ENDPOINT = "/oauth2/device_verification";
    String JWK_SET_ENDPOINT = "/oauth2/jwks";
    String OIDC_CLIENT_REGISTRATION_ENDPOINT = "/connect/register";
    String OIDC_LOGOUT_ENDPOINT = "/connect/logout";
    String OIDC_USER_INFO_ENDPOINT = "/userinfo";

    String AUTHORIZATION_CONSENT_URI = "/oauth2/consent";
    String DEVICE_ACTIVATION_URI = "/oauth2/device_activation";
    String DEVICE_VERIFICATION_SUCCESS_URI = "/device_activated";

    /**
     * 默认租户ID
     */
    String TENANT_ID = "public";
    /**
     * 默认树形结构根节点
     */
    String TREE_ROOT_ID = SymbolConstants.ZERO;
    /**
     * 默认的时间日期格式
     */
    String DATE_TIME_FORMAT = DatePattern.NORM_DATETIME_PATTERN;
}
