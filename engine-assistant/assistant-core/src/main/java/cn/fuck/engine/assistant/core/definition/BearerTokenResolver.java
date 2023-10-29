package cn.fuck.engine.assistant.core.definition;

import cn.fuck.engine.assistant.core.domain.PrincipalDetails;

/**
 * <p>Description: 身份信息解析器 </p>
 */
public interface BearerTokenResolver {

    PrincipalDetails resolve(String token);
}
