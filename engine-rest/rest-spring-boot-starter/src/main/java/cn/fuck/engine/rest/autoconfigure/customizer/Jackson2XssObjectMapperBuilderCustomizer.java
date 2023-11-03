package cn.fuck.engine.rest.autoconfigure.customizer;

import cn.fuck.engine.assistant.core.json.jackson2.Jackson2CustomizerOrder;
import cn.fuck.engine.assistant.autoconfigure.customizer.BaseObjectMapperBuilderCustomizer;
import cn.fuck.engine.rest.protect.jackson2.XssStringJsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: Jackson Xss Customizer </p>
 */
public class Jackson2XssObjectMapperBuilderCustomizer implements BaseObjectMapperBuilderCustomizer {

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(String.class, new XssStringJsonDeserializer());

        builder.modulesToInstall(modules -> {
            List<Module> install = new ArrayList<>(modules);
            install.add(simpleModule);
            builder.modulesToInstall(toArray(install));
        });
    }

    @Override
    public int getOrder() {
        return Jackson2CustomizerOrder.CUSTOMIZER_XSS;
    }
}
