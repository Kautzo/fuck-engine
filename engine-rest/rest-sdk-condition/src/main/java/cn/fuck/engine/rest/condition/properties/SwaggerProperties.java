package cn.fuck.engine.rest.condition.properties;

import cn.fuck.engine.rest.condition.constants.RestConstants;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: Swagger 自定义配置 </p>
 * @date : 2023/5/9 18:45
 */
@ConfigurationProperties(prefix = RestConstants.PROPERTY_PREFIX_SWAGGER)
public class SwaggerProperties {

    /**
     * 是否开启Swagger
     */
    private Boolean enabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("enabled", enabled)
                .toString();
    }
}
