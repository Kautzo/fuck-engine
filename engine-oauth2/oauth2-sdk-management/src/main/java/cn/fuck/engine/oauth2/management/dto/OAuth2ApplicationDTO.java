package cn.fuck.engine.oauth2.management.dto;

import cn.fuck.engine.oauth2.management.entity.OAuth2Application;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class OAuth2ApplicationDTO extends OAuth2Application {

    @Schema(title = "应用对应Scope", description = "传递应用对应Scope ID数组")
    private Set<String> scopeIds;

}
