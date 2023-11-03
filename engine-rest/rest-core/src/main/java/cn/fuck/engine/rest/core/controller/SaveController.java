package cn.fuck.engine.rest.core.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.data.core.entity.MPEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.dromara.hutool.core.bean.BeanUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 新增
 *
 */
public interface SaveController<Entity extends MPEntity, QueryDTO, SaveDTO, UpdateDTO, ResultVO>
        extends Controller<Entity, QueryDTO, SaveDTO, UpdateDTO, ResultVO> {

    /**
     * 新增
     *
     * @param saveDTO 保存参数
     * @return 实体
     */
    @Schema(description = "新增")
    @PostMapping
    default Result<?> save(@RequestBody @Validated SaveDTO saveDTO) {
        handlerSave(saveDTO);
        return Result.success();
    }

    default void handlerSave(SaveDTO saveDTO) {
        getBaseService().save(BeanUtil.toBean(saveDTO, getEntityClass()));
    }

}
