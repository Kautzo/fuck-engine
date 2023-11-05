package cn.fuck.engine.assistant.autoconfigure.customizer;

import com.fasterxml.jackson.databind.Module;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.core.Ordered;

import java.util.List;

/**
 * <p>Description: 提取公共操作 </p>
 */
public interface BaseObjectMapperBuilderCustomizer extends Jackson2ObjectMapperBuilderCustomizer, Ordered {

    default Module[] toArray(List<Module> modules) {
        if (CollectionUtils.isNotEmpty(modules)) {
            Module[] temps = new Module[modules.size()];
            return modules.toArray(temps);
        } else {
            return new Module[]{};
        }
    }
}
