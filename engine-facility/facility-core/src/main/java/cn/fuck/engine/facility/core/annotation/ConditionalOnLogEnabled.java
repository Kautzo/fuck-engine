package cn.fuck.engine.facility.core.annotation;

import cn.fuck.engine.facility.core.constants.FacilityConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * <p>Description: 日志中心是否开启条件注解 </p>
 * @date : 2021/8/8 9:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ConditionalOnProperty(name = FacilityConstants.ITEM_LOG_CENTER_ENABLED)
public @interface ConditionalOnLogEnabled {
}
