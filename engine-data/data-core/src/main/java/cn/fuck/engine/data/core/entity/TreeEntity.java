//package cn.fuck.engine.data.core.entity;
//
//import cn.fuck.engine.data.core.constants.DataConstants;
//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableLogic;
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.experimental.Accessors;
//
//import java.util.List;
//
//@Data
//@Accessors(chain = true)
//@EqualsAndHashCode(callSuper = true)
//public abstract class TreeEntity<E, T> extends BaseEntity<T> {
//
//    @Schema(description = "父节点ID")
//    @TableField(value = DataConstants.PARENT_ID_COLUMN, fill = FieldFill.INSERT)
//    protected T parentId;
//
//    @TableLogic
//    @Schema(description = "删除标识")
//    @TableField(value = DataConstants.DEL_FLAG_COLUMN, fill = FieldFill.INSERT_UPDATE)
//    protected Integer delFlag;
//
//    @Schema(description = "排序")
//    @TableField(value = DataConstants.RANKING_COLUMN, fill = FieldFill.INSERT_UPDATE)
//    protected Double ranking;
//
//    @TableField(exist = false)
//    protected List<E> children;
//
//}
