package cn.fuck.engine.rest.core.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.data.core.entity.MPEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.dromara.hutool.core.bean.BeanUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 修改Controller
 */
public interface UpdateController<Entity extends MPEntity, SaveDTO, UpdateDTO, QueryDTO, ResultVO>
        extends Controller<Entity, SaveDTO, UpdateDTO, QueryDTO, ResultVO> {

    /**
     * 修改
     *
     * @param updateDTO 修改DTO
     */
    @Schema(title = "修改", description = "修改UpdateDTO中不为空的字段")
    @PutMapping
    default Result<ResultVO> update(@RequestBody @Validated UpdateDTO updateDTO) {
        return Result.content(handlerUpdate(updateDTO));
    }

    /**
     * 自定义更新
     *
     * @param updateDTO 修改DTO
     */
    default ResultVO handlerUpdate(UpdateDTO updateDTO) {
        Entity entity = getBaseService().updateById(updateDTO);
        return BeanUtil.toBean(entity, getResultVOClass());
    }
}
