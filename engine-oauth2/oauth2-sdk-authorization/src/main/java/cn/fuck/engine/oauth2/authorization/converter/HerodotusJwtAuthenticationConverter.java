package cn.fuck.engine.oauth2.authorization.converter;

import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

/**
 * <p>Description: 扩展的 JwtAuthenticationConverter </p>
 * @date : 2022/3/22 11:49
 */
public class HerodotusJwtAuthenticationConverter extends JwtAuthenticationConverter {

    public HerodotusJwtAuthenticationConverter() {
        HerodotusJwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new HerodotusJwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName(BaseConstants.AUTHORITIES);

        this.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    }
}
