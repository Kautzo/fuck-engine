package cn.fuck.engine.oauth2.authorization.customizer;

import cn.fuck.engine.assistant.core.definition.BearerTokenResolver;
import cn.fuck.engine.assistant.core.domain.PrincipalDetails;
import cn.fuck.engine.oauth2.core.utils.PrincipalUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.introspection.BadOpaqueTokenException;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

@Slf4j
public class FuckBearerTokenResolver implements BearerTokenResolver {

    private final JwtDecoder jwtDecoder;
    private final OpaqueTokenIntrospector opaqueTokenIntrospector;
    private final boolean isRemoteValidate;

    public FuckBearerTokenResolver(JwtDecoder jwtDecoder, OpaqueTokenIntrospector opaqueTokenIntrospector, boolean isRemoteValidate) {
        this.jwtDecoder = jwtDecoder;
        this.opaqueTokenIntrospector = opaqueTokenIntrospector;
        this.isRemoteValidate = isRemoteValidate;
    }

    @Override
    public PrincipalDetails resolve(String token) {

        if (StringUtils.isBlank(token)) {
            throw new IllegalArgumentException("token can not be null");
        }

        BearerTokenAuthenticationToken bearer = new BearerTokenAuthenticationToken(token);

        if (isRemoteValidate) {
            OAuth2AuthenticatedPrincipal principal = getOpaque(bearer);
            if (ObjectUtils.isNotEmpty(principal)) {
                PrincipalDetails details = PrincipalUtils.toPrincipalDetails(principal);
                log.debug("[FUCK] |- Resolve OPAQUE token to principal details [{}]", details);
                return details;
            }
        } else {
            Jwt jwt = getJwt(bearer);
            if (ObjectUtils.isNotEmpty(jwt)) {
                PrincipalDetails details = PrincipalUtils.toPrincipalDetails(jwt);
                log.debug("[FUCK] |- Resolve JWT token to principal details [{}]", details);
                return details;
            }
        }

        return null;
    }

    private Jwt getJwt(BearerTokenAuthenticationToken bearer) {
        try {
            return this.jwtDecoder.decode(bearer.getToken());
        } catch (BadJwtException failed) {
            log.warn("[FUCK] |- Failed to decode since the JWT was invalid");
        } catch (JwtException failed) {
            log.warn("[FUCK] |- Failed to decode JWT, catch exception", failed);
        }

        return null;
    }

    private OAuth2AuthenticatedPrincipal getOpaque(BearerTokenAuthenticationToken bearer) {
        try {
            return this.opaqueTokenIntrospector.introspect(bearer.getToken());
        } catch (BadOpaqueTokenException failed) {
            log.warn("Failed to introspect since the Opaque was invalid");
        } catch (OAuth2IntrospectionException failed) {
            log.warn("[FUCK] |- Failed to introspect Opaque, catch exception", failed);
        }

        return null;
    }
}