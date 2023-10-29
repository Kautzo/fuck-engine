package cn.fuck.engine.supplier.upms.logic.domain.generator;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * <p>Description: SysInterfaceUuid </p>
 * @date : 2023/3/7 11:04
 */
@IdGeneratorType(SysInterfaceUuidGeneratorType.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})
public @interface SysInterfaceUuidGenerator {
}
