package cn.fuck.engine.rest.condition.definition;

import cn.fuck.engine.rest.condition.constants.RestPropertyFinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 使用 OkHttp 客户端条件 </p>
 */
@Slf4j
public class UseOkHttp3RestClientCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        boolean result = RestPropertyFinder.isOpenFeignOkHttpEnabled(context.getEnvironment());
        log.debug("[FUCK] |- Condition [Use OkHttp AS Rest Client] value is [{}]", result);
        return result;
    }
}
