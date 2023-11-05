package cn.fuck.engine.rest.core.service;

import cn.fuck.engine.data.core.entity.MPEntity;
import cn.fuck.engine.data.core.service.BaseManager;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Collection;
import java.util.List;

public interface BaseService<Entity extends MPEntity, SaveDTO, UpdateDTO, QueryDTO, ResultVO> {
    Class<Entity> getEntityClass();

    BaseManager<Entity> getBaseManager();

    Entity save(SaveDTO saveDTO);

    List<Entity> saveBatch(List<SaveDTO> saveDTOList);

    Entity updateById(UpdateDTO updateDTO);

    boolean removeByIds(Collection<String> idList);

    Entity getById(String id);

    List<Entity> list();
    List<Entity> list(Wrapper<Entity> queryWrapper);

    List<Entity> listByIds(List<String> ids);

    <Page extends IPage<Entity>> Page page(Page page, Wrapper<Entity> queryWrapper);
}
