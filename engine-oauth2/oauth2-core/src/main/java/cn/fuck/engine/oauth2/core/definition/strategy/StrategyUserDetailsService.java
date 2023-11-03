package cn.fuck.engine.oauth2.core.definition.strategy;

import cn.fuck.engine.assistant.core.domain.AccessPrincipal;
import cn.fuck.engine.oauth2.core.definition.domain.FuckUser;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>Description: 系统用户服务策略定义 </p>
 */
public interface StrategyUserDetailsService {

    FuckUser findUserDetailsByUsername(String userName) throws AuthenticationException;

    FuckUser findUserDetailsBySocial(String source, AccessPrincipal accessPrincipal) throws AuthenticationException;
}
