package cn.fuck.engine.upms.entity;

import cn.fuck.engine.data.core.entity.BaseSysEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>Description: 系统应用程序接口实体 </p>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统应用接口")
@TableName("sys_interface")
public class SysInterface extends BaseSysEntity {

    @Schema(description = "接口代码")
    private String interfaceCode;

    @Schema(description = "请求方法")
    @TableField(value = "request_method")
    private String requestMethod;

    @Schema(description = "服务ID")
    @TableField(value = "service_id")
    private String serviceId;

    @Schema(description = "接口所在类")
    @TableField(value = "class_name")
    private String className;

    @Schema(description = "接口对应方法")
    @TableField(value = "method_name")
    private String methodName;

    @Schema(description = "请求URL")
    @TableField(value = "url")
    private String url;

}
