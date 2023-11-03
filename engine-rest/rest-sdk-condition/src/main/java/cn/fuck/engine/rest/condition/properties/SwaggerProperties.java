package cn.fuck.engine.rest.condition.properties;

import cn.fuck.engine.rest.condition.constants.RestConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: Swagger 自定义配置 </p>
 */
@Data
@ConfigurationProperties(prefix = RestConstants.PROPERTY_PREFIX_SWAGGER)
public class SwaggerProperties {

    /**
     * 是否开启Swagger
     */
    private Boolean enabled;

}
