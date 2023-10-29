package cn.fuck.engine.oauth2.data.jpa.jackson2;

import cn.fuck.engine.assistant.core.json.jackson2.Jackson2Constants;
import cn.fuck.engine.oauth2.core.definition.details.FormLoginWebAuthenticationDetails;
import cn.fuck.engine.oauth2.core.definition.domain.HerodotusGrantedAuthority;
import cn.fuck.engine.oauth2.core.definition.domain.FuckUser;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.security.jackson2.SecurityJackson2Modules;

/**
 * <p>Description: 自定义 User Details Module </p>
 * @date : 2022/2/17 23:39
 */
public class HerodotusJackson2Module extends SimpleModule {

    public HerodotusJackson2Module() {
        super(HerodotusJackson2Module.class.getName(), Jackson2Constants.VERSION);
    }

    @Override
    public void setupModule(SetupContext context) {
        SecurityJackson2Modules.enableDefaultTyping(context.getOwner());
        context.setMixInAnnotations(FuckUser.class, HerodotusUserMixin.class);
        context.setMixInAnnotations(HerodotusGrantedAuthority.class, HerodotusGrantedAuthorityMixin.class);
        context.setMixInAnnotations(FormLoginWebAuthenticationDetails.class, FormLoginWebAuthenticationDetailsMixin.class);
    }
}
