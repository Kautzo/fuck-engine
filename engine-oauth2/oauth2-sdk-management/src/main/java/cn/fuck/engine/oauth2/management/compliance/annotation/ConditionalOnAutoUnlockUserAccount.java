package cn.fuck.engine.oauth2.management.compliance.annotation;

import cn.fuck.engine.oauth2.management.compliance.condition.AutoUnlockUserAccountCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 自动解锁用户账号条件注解 </p>
 * @date : 2022/7/9 22:20
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(AutoUnlockUserAccountCondition.class)
public @interface ConditionalOnAutoUnlockUserAccount {
}
