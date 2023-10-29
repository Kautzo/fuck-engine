package cn.fuck.engine.data.autoconfigure;

import cn.fuck.engine.data.core.properties.DataProperties;
import cn.fuck.engine.data.mybatis.plus.configuration.MybatisPlusConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: Data组件自动注入 </p>
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(DataProperties.class)
@Import({
        MybatisPlusConfiguration.class
})
public class DataAutoConfiguration {


    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Data Starter] Auto Configure.");
    }
}
