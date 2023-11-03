package cn.fuck.engine.oauth2.authentication.customizer;

import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;
import cn.fuck.engine.oauth2.core.definition.domain.FuckUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: TokenCustomizer 通用处理 </p>
 */
public abstract class AbstractTokenCustomizer {

    protected void appendAll(Map<String, Object> attributes, Authentication authentication, Set<String> authorizedScopes) {

        appendAuthorities(attributes, authentication);
        appendCommons(attributes, authentication, authorizedScopes);

    }

    protected void appendAuthorities(Map<String, Object> attributes, Authentication authentication) {

        if (CollectionUtils.isNotEmpty(authentication.getAuthorities())) {
            Set<String> authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());
            attributes.put(BaseConstants.AUTHORITIES, authorities);
        }
    }

    protected void appendCommons(Map<String, Object> attributes, Authentication authentication, Set<String> authorizedScopes) {

        if (CollectionUtils.isNotEmpty(authorizedScopes)) {
            attributes.put(OAuth2ParameterNames.SCOPE, authorizedScopes);
        }

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            putUserInfo(attributes, authentication.getPrincipal());
        }

        if (authentication instanceof OAuth2AccessTokenAuthenticationToken) {
            Object details = authentication.getDetails();
            putUserInfo(attributes, details);
        }

        attributes.put("license", "Apache-2.0 Licensed | Copyright © 2020-2023 码 匠 君");
    }

    private void putUserInfo(Map<String, Object> attributes, Object object) {
        if (ObjectUtils.isNotEmpty(object) && object instanceof FuckUser principal) {
            attributes.put(BaseConstants.OPEN_ID, principal.getUserId());
        }
    }
}
