package cn.fuck.engine.access.wxapp.annotation;

import cn.fuck.engine.access.wxapp.condition.WxappEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 微信小程序开启条件注解 </p>
 * @date : 2022/1/24 14:40
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(WxappEnabledCondition.class)
public @interface ConditionalOnWxappEnabled {
}
