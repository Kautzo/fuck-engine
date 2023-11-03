package cn.fuck.engine.oauth2.core.definition.strategy;

import cn.fuck.engine.oauth2.core.definition.domain.FuckPermission;

import java.util.List;

/**
 * <p>Description: 系统范围服务策略定义 </p>
 */
public interface StrategyPermissionDetailsService {

    /**
     * 获取全部权限
     *
     * @return 权限集合
     */
    List<FuckPermission> findAll();
}
