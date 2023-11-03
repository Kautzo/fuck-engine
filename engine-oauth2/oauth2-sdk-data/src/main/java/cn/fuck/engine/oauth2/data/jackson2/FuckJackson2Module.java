package cn.fuck.engine.oauth2.data.jackson2;

import cn.fuck.engine.assistant.core.json.jackson2.Jackson2Constants;
import cn.fuck.engine.oauth2.core.definition.details.FormLoginWebAuthenticationDetails;
import cn.fuck.engine.oauth2.core.definition.domain.FuckGrantedAuthority;
import cn.fuck.engine.oauth2.core.definition.domain.FuckUser;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.security.jackson2.SecurityJackson2Modules;

/**
 * <p>Description: 自定义 User Details Module </p>
 */
public class FuckJackson2Module extends SimpleModule {

    public FuckJackson2Module() {
        super(FuckJackson2Module.class.getName(), Jackson2Constants.VERSION);
    }

    @Override
    public void setupModule(SetupContext context) {
        SecurityJackson2Modules.enableDefaultTyping(context.getOwner());
        context.setMixInAnnotations(FuckUser.class, FuckUserMixin.class);
        context.setMixInAnnotations(FuckGrantedAuthority.class, FuckGrantedAuthorityMixin.class);
        context.setMixInAnnotations(FormLoginWebAuthenticationDetails.class, FormLoginWebAuthenticationDetailsMixin.class);
    }
}
