package cn.fuck.engine.rest.core.definition.dto;

import cn.fuck.engine.assistant.core.definition.domain.AbstractDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Description: DTO基类定义 </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseDTO extends AbstractDTO {

    @Schema(title = "排序值")
    private Integer ranking = 0;

}
