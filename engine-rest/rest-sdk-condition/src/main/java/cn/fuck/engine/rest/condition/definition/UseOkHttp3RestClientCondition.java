package cn.fuck.engine.rest.condition.definition;

import cn.fuck.engine.rest.condition.constants.RestPropertyFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 使用 OkHttp 客户端条件 </p>
 * @date : 2023/6/15 21:27
 */
public class UseOkHttp3RestClientCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(UseOkHttp3RestClientCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        boolean result = RestPropertyFinder.isOpenFeignOkHttpEnabled(context.getEnvironment());
        log.debug("[FUCK] |- Condition [Use OkHttp AS Rest Client] value is [{}]", result);
        return result;
    }
}
