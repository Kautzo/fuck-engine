package cn.fuck.engine.rest.condition.definition;

import cn.fuck.engine.rest.condition.constants.RestPropertyFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 使用默认客户端条件 </p>
 * @date : 2023/6/15 23:12
 */
public class UseSimpleRestClientCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(UseSimpleRestClientCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        boolean isOkHttpEnabled = RestPropertyFinder.isOpenFeignOkHttpEnabled(context.getEnvironment());
        boolean isHttpClientEnabled = RestPropertyFinder.isOpenFeignHttpClientEnabled(context.getEnvironment());
        boolean result = !isOkHttpEnabled && !isHttpClientEnabled;
        log.debug("[FUCK] |- Condition [Use Simple Rest Client] value is [{}]", result);
        return result;
    }
}
