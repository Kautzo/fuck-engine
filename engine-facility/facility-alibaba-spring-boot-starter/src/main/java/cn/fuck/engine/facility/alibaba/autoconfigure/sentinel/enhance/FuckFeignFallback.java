package cn.fuck.engine.facility.alibaba.autoconfigure.sentinel.enhance;

import cn.fuck.engine.assistant.core.definition.constants.SymbolConstants;
import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.assistant.core.exception.GlobalExceptionHandler;
import com.google.common.base.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>Description: 统一 fallback 实体 </p>
 */
@Slf4j
public class FuckFeignFallback<T> implements MethodInterceptor {

    private final Class<T> targetType;
    private final String targetName;
    private final Throwable cause;

    public FuckFeignFallback(Class<T> targetType, String targetName, Throwable cause) {
        this.targetType = targetType;
        this.targetName = targetName;
        this.cause = cause;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        String errorMessage = cause.getMessage();
        String path = targetType.getName() + SymbolConstants.FORWARD_SLASH + method.getName();

        Result<String> result = GlobalExceptionHandler.resolveException((Exception) cause, path);
        log.error("[FUCK] |- Feign remote call fallback : [{}.{}] serviceId:[{}] message:[{}]", targetType.getName(), method.getName(), targetName, errorMessage);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FuckFeignFallback<?> that = (FuckFeignFallback<?>) o;
        return Objects.equal(targetType, that.targetType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(targetType);
    }
}
