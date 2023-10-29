package cn.fuck.engine.supplier.upms.logic.service.security;

import cn.fuck.engine.supplier.upms.logic.entity.security.SysSocialUser;
import cn.fuck.engine.supplier.upms.logic.repository.security.SysSocialUserRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>Description: 社会化登录用户服务 </p>
 * @date : 2021/5/16 16:29
 */
@Service
public class SysSocialUserService extends BaseService<SysSocialUser, String> {

    private final SysSocialUserRepository sysSocialUserRepository;

    public SysSocialUserService(SysSocialUserRepository sysSocialUserRepository) {
        this.sysSocialUserRepository = sysSocialUserRepository;
    }

    @Override
    public BaseRepository<SysSocialUser, String> getRepository() {
        return sysSocialUserRepository;
    }

    public SysSocialUser findByUuidAndSource(String uuid, String source) {
        return sysSocialUserRepository.findSysSocialUserByUuidAndSource(uuid, source);
    }
}
