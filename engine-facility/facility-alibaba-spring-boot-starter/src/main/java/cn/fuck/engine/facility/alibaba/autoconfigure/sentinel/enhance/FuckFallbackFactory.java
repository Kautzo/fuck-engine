package cn.fuck.engine.facility.alibaba.autoconfigure.sentinel.enhance;

import feign.Target;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * <p>Description: Feign 统一 Fallback 工厂 </p>
 */
public class FuckFallbackFactory<T> implements FallbackFactory<T> {

    private final Target<T> target;

    public FuckFallbackFactory(Target<T> target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T create(Throwable cause) {
        final Class<T> targetType = target.type();
        final String targetName = target.name();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetType);
        enhancer.setUseCache(true);
        enhancer.setCallback(new FuckFeignFallback<>(targetType, targetName, cause));
        return (T) enhancer.create();
    }

}
