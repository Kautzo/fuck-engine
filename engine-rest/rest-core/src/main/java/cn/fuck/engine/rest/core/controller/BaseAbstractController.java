package cn.fuck.engine.rest.core.controller;

import cn.fuck.engine.data.core.entity.MPEntity;
import cn.fuck.engine.rest.core.service.BaseService;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 简单的实现了BaseController，为了获取注入 Service 和 实体类型
 * <p>
 * 基类该类后，没有任何方法。
 * 可以让业务Controller继承 SuperSimpleController 后，按需实现 *Controller 接口
 *
 */
public abstract class BaseAbstractController<S extends BaseService<Entity, SaveDTO, UpdateDTO, QueryDTO, ResultVO>,
        Entity extends MPEntity, SaveDTO, UpdateDTO, QueryDTO, ResultVO>
        implements Controller<Entity, SaveDTO, UpdateDTO, QueryDTO, ResultVO> {

    @Autowired
    protected S baseService;

    protected Class<ResultVO> resultVOClass = currentResultVOClass();

    protected Class<ResultVO> currentResultVOClass() {
        return (Class<ResultVO>) ReflectionKit.getSuperClassGenericType(this.getClass(), BaseAbstractController.class, 5);
    }

    @Override
    public Class<Entity> getEntityClass() {
        return baseService.getEntityClass();
    }

    @Override
    public Class<ResultVO> getResultVOClass() {
        return this.resultVOClass;
    }

    @Override
    public BaseService<Entity, SaveDTO, UpdateDTO, QueryDTO, ResultVO> getBaseService() {
        return baseService;
    }

}
