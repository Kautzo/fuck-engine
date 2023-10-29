package cn.fuck.engine.oauth2.core.definition;

import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

/**
 * <p>Description: 自定义 Grant Type 类型 </p>
 */
public interface FuckGrantType {

    AuthorizationGrantType SOCIAL = new AuthorizationGrantType(BaseConstants.SOCIAL_CREDENTIALS);

    AuthorizationGrantType PASSWORD = new AuthorizationGrantType(BaseConstants.PASSWORD);
}
