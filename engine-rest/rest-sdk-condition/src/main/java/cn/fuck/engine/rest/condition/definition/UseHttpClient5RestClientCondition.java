package cn.fuck.engine.rest.condition.definition;

import cn.fuck.engine.rest.condition.constants.RestPropertyFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 使用 HttpClient 客户端条件 </p>
 * @date : 2023/6/15 21:27
 */
public class UseHttpClient5RestClientCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(UseHttpClient5RestClientCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        boolean result = RestPropertyFinder.isOpenFeignHttpClientEnabled(context.getEnvironment());
        log.debug("[FUCK] |- Condition [Use HttpClient AS Rest Client] value is [{}]", result);
        return result;
    }
}
