package cn.fuck.engine.message.kafka.autoconfigure.annotation;

import cn.fuck.engine.message.core.constants.MessageConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * <p>Description: 开启传统kafka使用方式支持 </p>
 * @date : 2021/8/11 20:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ConditionalOnProperty(value = MessageConstants.ITEM_KAFKA_ENABLED)
public @interface ConditionalOnKafkaEnabled {
}
