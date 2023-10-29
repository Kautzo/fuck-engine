package cn.fuck.engine.supplier.tenant.service;

import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.data.tenant.entity.SysTenantDataSource;
import cn.fuck.engine.data.tenant.repository.SysTenantDataSourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>Description: 多租户数据源 </p>
 * @date : 2023/3/29 21:20
 */
@Service
public class SysTenantDataSourceService extends BaseService<SysTenantDataSource, String> {

    private static final Logger log = LoggerFactory.getLogger(SysTenantDataSourceService.class);

    private final SysTenantDataSourceRepository sysTenantDataSourceRepository;

    public SysTenantDataSourceService(SysTenantDataSourceRepository sysTenantDataSourceRepository) {
        this.sysTenantDataSourceRepository = sysTenantDataSourceRepository;
    }

    @Override
    public BaseRepository<SysTenantDataSource, String> getRepository() {
        return sysTenantDataSourceRepository;
    }

    public SysTenantDataSource findByTenantId(String tenantId) {
        SysTenantDataSource sysRole = sysTenantDataSourceRepository.findByTenantId(tenantId);
        log.debug("[FUCK] |- SysTenantDataSource Service findByTenantId.");
        return sysRole;
    }
}
