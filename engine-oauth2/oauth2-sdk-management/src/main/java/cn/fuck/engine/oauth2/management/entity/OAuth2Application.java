package cn.fuck.engine.oauth2.management.entity;

import cn.fuck.engine.oauth2.management.definition.AbstractOAuth2RegisteredClient;
import cn.fuck.engine.oauth2.core.enums.ApplicationType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * <p>Description: OAuth2 应用 </p>
 * <p>
 * Spring Authorization Server 默认的 RegisteredClient 不便于扩展。增加该类用于存储标准 RegisteredClient 表结构以外的扩展信息。
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "OAuth2应用实体")
@TableName(value = "oauth2_application")
public class OAuth2Application extends AbstractOAuth2RegisteredClient {

    @Schema(description = "应用名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "应用名称不能为空")
    @TableField(value = "application_name")
    private String applicationName;

    @Schema(title = "应用简称", description = "应用的简称、别名、缩写等信息")
    @TableField(value = "abbreviation")
    private String abbreviation;

    @Schema(title = "Logo", description = "Logo存储信息，可以是URL或者路径等")
    @TableField(value = "logo")
    private String logo;

    @Schema(title = "主页信息", description = "应用相关的主页信息方便查询")
    @TableField(value = "homepage")
    private String homepage;

    @Schema(title = "应用类型", description = "用于区分不同类型的应用")
    @TableField(value = "application_type")
    private ApplicationType applicationType;

    @TableField(exist = false)
    private List<OAuth2Scope> scopes;
}
