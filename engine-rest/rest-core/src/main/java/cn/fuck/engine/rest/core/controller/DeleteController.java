package cn.fuck.engine.rest.core.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.data.core.entity.MPEntity;
import cn.fuck.engine.rest.core.annotation.Idempotent;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;


/**
 * 删除Controller
 */
public interface DeleteController<Entity extends MPEntity, QueryDTO, SaveDTO, UpdateDTO, ResultVO>
        extends Controller<Entity, QueryDTO, SaveDTO, UpdateDTO, ResultVO> {

    /**
     * 删除方法
     *
     * @param id id
     * @return 是否成功
     */
    @Schema(description = "删除")
    @DeleteMapping
    default Result<Boolean> delete(@RequestParam String id) {
        List<String> ids = Collections.singletonList(id);
        return Result.content(handlerDelete(ids));
    }

    /**
     * 删除方法
     *
     * @param ids id
     * @return 是否成功
     */
    @Schema(description = "批量删除")
    @DeleteMapping("/batch")
    default Result<Boolean> delete(@RequestBody List<String> ids) {
        return Result.content(handlerDelete(ids));
    }

    /**
     * 自定义删除
     *
     * @param ids id
     * @return true false, 调用默认更新, 返回其他不调用默认更新
     */
    default Boolean handlerDelete(List<String> ids) {
        return getBaseService().removeByIds(ids);
    }
}
