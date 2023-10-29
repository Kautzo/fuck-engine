package cn.fuck.engine.data.core.manager.impl;

import cn.fuck.engine.data.core.manager.BaseManager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class BaseManagerImpl<M extends BaseMapper<E>, E> extends ServiceImpl<M, E> implements BaseManager<E> {
}
