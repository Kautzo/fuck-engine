package cn.fuck.engine.rest.core.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.data.core.entity.MPEntity;
import cn.fuck.engine.rest.core.annotation.Idempotent;
import io.swagger.v3.oas.annotations.media.Schema;
import org.dromara.hutool.core.bean.BeanUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 查询Controller
 */
public interface QueryController<Entity extends MPEntity, QueryDTO, SaveDTO, UpdateDTO, ResultVO>
        extends PageController<Entity, QueryDTO, SaveDTO, UpdateDTO, ResultVO> {

    /**
     * 查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Idempotent
    @Schema(description = "单体查询")
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    default Result<ResultVO> getById(@RequestParam String id) {
        return Result.content(BeanUtil.toBean(getBaseService().findById(id), getResultVOClass()));
    }

}
