package cn.fuck.engine.data.core.entity;

import cn.fuck.engine.assistant.core.definition.constants.DefaultConstants;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity extends MPEntity {

    @Schema(title = "ID")
    @TableId(type = IdType.ASSIGN_ID)
    @TableField(value = "id", fill = FieldFill.INSERT)
    protected String id;

    @Schema(title = "创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    protected String createBy;

    @Schema(title = "数据创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
    protected Date createTime;

    @Schema(title = "修改人")
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    protected String updateBy;

    @Schema(title = "数据更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
    protected Date updateTime;

}
