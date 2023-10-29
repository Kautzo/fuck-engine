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
 * <p>Description: 标准算法策略条件 </p>
 * @date : 2022/5/3 23:00
 */
public class StandardCryptoCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(StandardCryptoCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = RestPropertyFinder.getCryptoStrategy(conditionContext.getEnvironment());
        boolean result = StringUtils.isNotBlank(property) && StringUtils.equalsIgnoreCase(property, CryptoStrategy.STANDARD.name());
        log.debug("[FUCK] |- Condition [Standard Crypto] value is [{}]", result);
        return result;
    }
}
