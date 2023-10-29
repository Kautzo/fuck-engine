package cn.fuck.engine.rest.service.feign;

import cn.fuck.engine.assistant.core.utils.http.HeaderUtils;
import cn.fuck.engine.rest.core.annotation.Inner;
import feign.MethodMetadata;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

/**
 * <p>Description: 自定义 Inner 处理器 </p>
 * @date : 2022/5/31 11:28
 */
public class FeignInnerContract extends SpringMvcContract {

    private static final Logger log = LoggerFactory.getLogger(FeignInnerContract.class);

    @Override
    protected void processAnnotationOnMethod(MethodMetadata data, Annotation methodAnnotation, Method method) {

        if (methodAnnotation instanceof Inner) {
            Inner inner = findMergedAnnotation(method, Inner.class);
            if (ObjectUtils.isNotEmpty(inner)) {
                log.debug("[FUCK] |- Found inner annotation on Feign interface, add header!");
                data.template().header(HeaderUtils.X_HERODOTUS_FROM_IN, "true");
            }
        }

        super.processAnnotationOnMethod(data, methodAnnotation, method);
    }
}
