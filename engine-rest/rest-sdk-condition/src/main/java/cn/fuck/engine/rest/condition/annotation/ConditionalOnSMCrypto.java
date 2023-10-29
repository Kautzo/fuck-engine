package cn.fuck.engine.rest.condition.annotation;

import cn.fuck.engine.rest.condition.definition.SMCryptoCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 国密算法条件注解 </p>
 * @date : 2022/5/3 23:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(SMCryptoCondition.class)
public @interface ConditionalOnSMCrypto {
}
