package cn.fuck.engine.upms.service;

import cn.fuck.engine.upms.entity.SysAttributePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysAttributePermissionService extends IService<SysAttributePermission> {
    List<SysAttributePermission> getByAttributeId(String attributeId);
}
