package cn.fuck.engine.data.core.service.impl;

import cn.fuck.engine.data.core.service.BaseManager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class BaseManagerImpl<Mapper extends BaseMapper<Entity>, Entity>
        extends ServiceImpl<Mapper, Entity>
        implements BaseManager<Entity> {
}
