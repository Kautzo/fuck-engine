package cn.fuck.engine.rest.core.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.data.core.entity.MPEntity;
import cn.fuck.engine.rest.core.definition.PageParams;
import cn.fuck.engine.rest.core.utils.Wraps;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import org.dromara.hutool.core.bean.BeanUtil;
import org.dromara.hutool.core.collection.CollUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface PageController<Entity extends MPEntity, SaveDTO, UpdateDTO, QueryDTO, ResultVO>
        extends Controller<Entity, SaveDTO, UpdateDTO, QueryDTO, ResultVO> {

    /**
     * 处理查询参数
     *
     * @param params 前端传递的参数
     */
    default void handlerQueryParams(PageParams<QueryDTO> params) {
    }

    /**
     * 执行分页查询
     * <p>
     * 子类可以覆盖后重写查询逻辑
     *
     * @param params 分页参数
     * @return 分页信息
     */
    default IPage<Entity> query(PageParams<QueryDTO> params) {
        // 处理查询参数，如：覆盖前端传递的 current、size、sort 等参数 以及 model 中的参数 【提供给子类重写】【无默认实现】
        handlerQueryParams(params);

        // 构建分页参数(current、size)和排序字段等
        IPage<Entity> page = params.buildPage(getEntityClass());
        Entity model = BeanUtil.toBean(params.getModel(), getEntityClass());

        // 根据前端传递的参数，构建查询条件【提供给子类重写】【有默认实现】
        QueryWrapper<Entity> wrapper = handlerWrapper(model, params);

        // 执行单表分页查询
        getBaseService().page(page, wrapper);

        return page;
    }

    /**
     * 处理对象中的非空参数和扩展字段中的区间参数，可以覆盖后处理组装查询条件
     *
     * @param model  实体类
     * @param params 分页参数
     * @return 查询构造器
     */
    default QueryWrapper<Entity> handlerWrapper(Entity model, PageParams<QueryDTO> params) {
        return Wraps.q(model, params.getExtra(), getEntityClass());
    }


    /**
     * 处理查询后的数据
     * <p>
     *
     * @param page 分页对象
     */
    default void handlerResult(IPage<ResultVO> page) {
    }

    /**
     * 分页查询
     *
     * @param params 分页参数
     * @return 分页数据
     */
    @Operation(description = "分页列表查询")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    default Result<IPage<ResultVO>> page(@RequestBody @Validated PageParams<QueryDTO> params) {
        return Result.content(handlerPage(params));
    }

    default IPage<ResultVO> handlerPage(PageParams<QueryDTO> params) {
        IPage<Entity> page = query(params);
        IPage<ResultVO> voPage = toBeanPage(page, getResultVOClass());
        handlerResult(voPage);
        return toBeanPage(voPage, getResultVOClass());
    }

    default <T, E> IPage<T> toBeanPage(IPage<E> page, Class<T> destinationClass) {
        if (page == null || destinationClass == null) {
            return null;
        }
        IPage<T> newPage = new Page<>(page.getCurrent(), page.getSize());
        newPage.setPages(page.getPages());
        newPage.setTotal(page.getTotal());

        List<E> list = page.getRecords();
        if (CollUtil.isEmpty(list)) {
            return newPage;
        }

        List<T> destinationList = BeanUtil.copyToList(list, destinationClass);

        newPage.setRecords(destinationList);
        return newPage;
    }
}
