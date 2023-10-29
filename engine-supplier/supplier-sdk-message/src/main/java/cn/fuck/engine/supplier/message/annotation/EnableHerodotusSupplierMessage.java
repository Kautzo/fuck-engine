package cn.fuck.engine.supplier.message.annotation;

import cn.fuck.engine.supplier.message.configuration.SupplierMessageConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 开启 Supplier Message </p>
 * @date : 2021/11/8 11:36
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SupplierMessageConfiguration.class)
public @interface EnableHerodotusSupplierMessage {
}
