package cn.fuck.engine.oauth2.authorization.converter;

import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

/**
 * <p>Description: 扩展的 JwtAuthenticationConverter </p>
 */
public class FuckJwtAuthenticationConverter extends JwtAuthenticationConverter {

    public FuckJwtAuthenticationConverter() {
        FuckJwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new FuckJwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName(BaseConstants.AUTHORITIES);

        this.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    }
}
