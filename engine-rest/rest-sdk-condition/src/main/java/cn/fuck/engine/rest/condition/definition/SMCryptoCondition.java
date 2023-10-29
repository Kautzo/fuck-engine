package cn.fuck.engine.rest.condition.definition;

import cn.fuck.engine.rest.condition.constants.RestPropertyFinder;
import cn.fuck.engine.rest.core.enums.CryptoStrategy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 国密算法策略条件 </p>
 * @date : 2022/5/3 22:57
 */
public class SMCryptoCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(SMCryptoCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = RestPropertyFinder.getCryptoStrategy(conditionContext.getEnvironment(), CryptoStrategy.SM.name());
        boolean result = StringUtils.isNotBlank(property) && StringUtils.equalsIgnoreCase(property, CryptoStrategy.SM.name());
        log.debug("[FUCK] |- Condition [SM Crypto Strategy] value is [{}]", result);
        return result;
    }
}
