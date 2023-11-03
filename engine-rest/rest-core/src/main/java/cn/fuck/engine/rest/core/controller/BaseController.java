package cn.fuck.engine.rest.core.controller;

import cn.fuck.engine.data.core.entity.MPEntity;
import cn.fuck.engine.data.core.service.BaseService;


/**
 * SuperNoPoiController
 * <p>
 * 继承该类，就拥有了如下方法：
 * 1，page 分页查询，并支持子类扩展4个方法：handlerQueryParams、query、handlerWrapper、handlerResult
 * 2，save 保存，并支持子类扩展方法：handlerSave
 * 3，update 修改，并支持子类扩展方法：handlerUpdate
 * 4，delete 删除，并支持子类扩展方法：handlerDelete
 * 5，get 单体查询， 根据ID直接查询DB
 * 6，detail 单体详情查询， 根据ID直接查询DB
 * 7，list 列表查询，根据参数条件，查询列表
 * <p>
 *
 */
public abstract class BaseController<Service extends BaseService<Entity>, Entity extends MPEntity, QueryDTO, SaveDTO, UpdateDTO, ResultVO>
        extends BaseAbstractController<Service, Entity, QueryDTO, SaveDTO, UpdateDTO, ResultVO>
        implements SaveController<Entity, QueryDTO, SaveDTO, UpdateDTO, ResultVO>,
        UpdateController<Entity, QueryDTO, SaveDTO, UpdateDTO, ResultVO>,
        DeleteController<Entity, QueryDTO, SaveDTO, UpdateDTO, ResultVO>,
        QueryController<Entity, QueryDTO, SaveDTO, UpdateDTO, ResultVO> {


}
