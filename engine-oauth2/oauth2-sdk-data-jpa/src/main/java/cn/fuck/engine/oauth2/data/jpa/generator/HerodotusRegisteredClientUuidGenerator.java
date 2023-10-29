package cn.fuck.engine.oauth2.data.jpa.generator;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * <p>Description: HerodotusRegisteredClientUuid 注解 </p>
 * @date : 2022/11/7 15:49
 */
@IdGeneratorType(HerodotusRegisteredClientUuidGeneratorType.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})
public @interface HerodotusRegisteredClientUuidGenerator {
}