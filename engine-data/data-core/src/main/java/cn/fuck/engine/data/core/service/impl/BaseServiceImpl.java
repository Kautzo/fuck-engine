package cn.fuck.engine.data.core.service.impl;

import cn.fuck.engine.data.core.entity.MPEntity;
import cn.fuck.engine.data.core.service.BaseService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class BaseServiceImpl<Mapper extends BaseMapper<Entity>, Entity extends MPEntity>
        extends ServiceImpl<Mapper, Entity>
        implements BaseService<Entity> {
}
