package cn.fuck.engine.rest.core.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.data.core.entity.MPEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.dromara.hutool.core.bean.BeanUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 新增
 *
 */
public interface SaveController<Entity extends MPEntity, SaveDTO, UpdateDTO, QueryDTO, ResultVO>
        extends Controller<Entity, SaveDTO, UpdateDTO, QueryDTO, ResultVO> {

    /**
     * 新增
     *
     * @param saveDTO 保存参数
     * @return 实体
     */
    @Schema(description = "新增")
    @PostMapping
    default Result<ResultVO> save(@RequestBody @Validated SaveDTO saveDTO) {
        return Result.content(handlerSave(saveDTO));
    }

    default ResultVO handlerSave(SaveDTO saveDTO) {
        Entity entity = getBaseService().save(saveDTO);
        return BeanUtil.toBean(entity, getResultVOClass());
    }

}
