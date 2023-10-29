package cn.fuck.engine.access.core.constants;

import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;

/**
 * <p>Description: 接入模块常量 </p>
 */
public interface AccessConstants extends BaseConstants {

    String PROPERTY_ACCESS_JUSTAUTH = PROPERTY_PREFIX_ACCESS + ".justauth";
    String ITEM_JUSTAUTH_ENABLED = PROPERTY_ACCESS_JUSTAUTH + PROPERTY_ENABLED;
    String PROPERTY_ACCESS_WXAPP = PROPERTY_PREFIX_ACCESS + ".wxapp";
    String ITEM_WXAPP_ENABLED = PROPERTY_ACCESS_WXAPP + PROPERTY_ENABLED;
    String PROPERTY_ACCESS_WXMPP = PROPERTY_PREFIX_ACCESS + ".wxmpp";
    String ITEM_WXMPP_ENABLED = PROPERTY_ACCESS_WXMPP + PROPERTY_ENABLED;


    String CACHE_NAME_TOKEN_JUSTAUTH = CACHE_TOKEN_BASE_PREFIX + "justauth:";
}
