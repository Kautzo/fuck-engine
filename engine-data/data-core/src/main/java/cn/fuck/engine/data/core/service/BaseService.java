package cn.fuck.engine.data.core.service;

import cn.fuck.engine.data.core.entity.MPEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dromara.hutool.core.lang.Assert;

public interface BaseService<Entity extends MPEntity>
        extends IService<Entity> {
    default Entity findById(String id){
        Entity byId = getById(id);
        Assert.notNull(byId, "Id Error");
        return byId;
    }
}
