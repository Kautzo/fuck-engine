package cn.fuck.engine.rest.core.definition.dto;

import cn.fuck.engine.data.core.enums.DataItemStatus;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 框架基础实体通用基础类 </p>
 * @date : 2022/3/18 15:47
 */
public class BaseSysDTO extends BaseDTO {

    @Schema(title = "数据状态")
    private DataItemStatus status = DataItemStatus.ENABLE;

    @Schema(title = "是否为保留数据", description = "True 为不能删，False为可以删除")
    private Boolean reserved = Boolean.FALSE;

    @Schema(title = "版本号")
    private Integer reversion = 0;

    /**
     * 角色描述,UI界面显示使用
     */
    @Schema(title = "备注")
    private String description;

    public DataItemStatus getStatus() {
        return status;
    }

    public void setStatus(DataItemStatus status) {
        this.status = status;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public Integer getReversion() {
        return reversion;
    }

    public void setReversion(Integer reversion) {
        this.reversion = reversion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
