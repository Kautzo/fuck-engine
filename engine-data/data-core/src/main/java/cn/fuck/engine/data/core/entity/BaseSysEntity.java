package cn.fuck.engine.data.core.entity;

import cn.fuck.engine.data.core.enums.DataItemStatus;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>Description: 框架基础权限实体通用基础类</p>
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseSysEntity extends BaseEntity {

    @Schema(title = "数据状态")
    @TableField(value = "status", fill = FieldFill.INSERT_UPDATE)
    protected DataItemStatus status = DataItemStatus.ENABLE;

    @Schema(title = "是否为保留数据", description = "True 为不能删，False为可以删除")
    @TableField(value = "reserved", fill = FieldFill.INSERT_UPDATE)
    protected Boolean reserved = Boolean.FALSE;

    @Version
    @Schema(description = "版本号")
    @TableField(value = "reversion", fill = FieldFill.INSERT_UPDATE)
    protected Integer reversion = 0;

    @Schema(description = "备注")
    @TableField(value = "description", fill = FieldFill.INSERT_UPDATE)
    protected String description;

}
