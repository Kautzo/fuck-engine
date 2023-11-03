package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.upms.entity.SysAttributePermission;
import cn.fuck.engine.upms.mapper.SysAttributePermissionMapper;
import cn.fuck.engine.upms.service.SysAttributePermissionService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SysAttributePermissionServiceImpl extends ServiceImpl<SysAttributePermissionMapper, SysAttributePermission> implements SysAttributePermissionService {
    @Override
    public List<SysAttributePermission> getByAttributeId(String attributeId) {
        return list(Wrappers.lambdaQuery(SysAttributePermission.class).eq(SysAttributePermission::getAttributeId, attributeId));
    }
}
