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
public interface UpdateController<Entity extends MPEntity, QueryDTO, SaveDTO, UpdateDTO, ResultVO>
        extends Controller<Entity, QueryDTO, SaveDTO, UpdateDTO, ResultVO> {

    /**
     * 修改
     *
     * @param updateDTO 修改DTO
     */
    @Schema(title = "修改", description = "修改UpdateDTO中不为空的字段")
    @PutMapping
    default Result<?> update(@RequestBody @Validated UpdateDTO updateDTO) {
        handlerUpdate(updateDTO);
        return Result.success();
    }

    /**
     * 自定义更新
     *
     * @param updateDTO 修改DTO
     */
    default void handlerUpdate(UpdateDTO updateDTO) {
        getBaseService().updateById(BeanUtil.toBean(updateDTO, getEntityClass()));
    }
}
