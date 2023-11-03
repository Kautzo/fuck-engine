package cn.fuck.engine.upms.entity;

import cn.fuck.engine.data.core.entity.BaseSysEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: 系统安全属性实体 </p>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统安全属性数据")
@TableName("sys_attribute")
public class SysAttribute extends BaseSysEntity {

    @Schema(description = "默认权限代码")
    @TableField(value = "attribute_code")
    private String attributeCode;

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

    @Schema(title = "表达式", description = "Security表达式字符串，通过该值设置动态权限")
    @TableField(value = "web_expression")
    private String webExpression;

    @Schema(title = "属性对应权限", description = "根据属性关联权限数据")
    @TableField(exist = false)
    private Set<SysPermission> permissions = new HashSet<>();

}
