package cn.fuck.engine.data.core.entity;

import cn.fuck.engine.assistant.core.definition.constants.DefaultConstants;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>Description: DISCRIMINATOR 类型多租户实体基础类 </p>
 * <p>
 * GTB Cloud 系统本身如果要改成多租户模式，还是建议使用 DATABASE 模式。
 * 根据需要指定具体是哪些实体（数据表）采用 DISCRIMINATOR 模式多租户。
 * 不要什么实体都加以防产生不必要的干扰。
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class TenantEntity extends BaseEntity {

    @Schema(title = "租户ID", description = "Partitioned 类型租户ID")
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    protected String tenantId = DefaultConstants.TENANT_ID;

}
