package cn.fuck.engine.oauth2.core.utils;

import cn.fuck.engine.oauth2.core.definition.domain.FuckUser;
import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;
import cn.fuck.engine.assistant.core.domain.PrincipalDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;


/**
 * <p>Description: 身份信息工具类 </p>
 */
public class PrincipalUtils {

    public static PrincipalDetails toPrincipalDetails(FuckUser fuckUser) {
        PrincipalDetails details = new PrincipalDetails();
        details.setOpenId(fuckUser.getUserId());
        details.setUserName(fuckUser.getUsername());
        return details;
    }

    public static PrincipalDetails toPrincipalDetails(OAuth2AuthenticatedPrincipal authenticatedPrincipal) {
        PrincipalDetails details = new PrincipalDetails();
        details.setOpenId(authenticatedPrincipal.getAttribute(BaseConstants.OPEN_ID));
        details.setUserName(authenticatedPrincipal.getName());
        return details;
    }

    public static PrincipalDetails toPrincipalDetails(Jwt jwt) {
        PrincipalDetails details = new PrincipalDetails();
        details.setOpenId(jwt.getClaimAsString(BaseConstants.OPEN_ID));
        details.setUserName(jwt.getClaimAsString(JwtClaimNames.SUB));
        return details;
    }
}
