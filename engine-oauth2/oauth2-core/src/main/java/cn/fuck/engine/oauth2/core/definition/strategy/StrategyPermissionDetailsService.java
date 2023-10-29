package cn.fuck.engine.oauth2.core.definition.strategy;

import cn.fuck.engine.oauth2.core.definition.domain.HerodotusPermission;

import java.util.List;

/**
 * <p>Description: 系统范围服务策略定义 </p>
 * @date : 2022/3/31 22:34
 */
public interface StrategyPermissionDetailsService {

    /**
     * 获取全部权限
     *
     * @return 权限集合
     */
    List<HerodotusPermission> findAll();
}