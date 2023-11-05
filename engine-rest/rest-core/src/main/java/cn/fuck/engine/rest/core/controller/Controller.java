package cn.fuck.engine.rest.core.controller;

import cn.fuck.engine.data.core.entity.MPEntity;
import cn.fuck.engine.rest.core.service.BaseService;


/**
 * <p> Description : Controller基础定义 </p>
 */
public interface Controller<Entity extends MPEntity, SaveDTO, UpdateDTO, QueryDTO, ResultVO> {

    /**
     * 获取Service
     *
     * @return Service
     */
    BaseService<Entity, SaveDTO, UpdateDTO, QueryDTO, ResultVO> getBaseService();

    /**
     * 获取实体的类型
     *
     * @return 实体的类型
     */
    Class<Entity> getEntityClass();

    /**
     * 获取返回VO的类型
     *
     * @return 返回VO的类型
     */
    Class<ResultVO> getResultVOClass();

}
