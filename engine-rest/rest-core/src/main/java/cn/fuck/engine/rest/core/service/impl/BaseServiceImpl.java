package cn.fuck.engine.rest.core.service.impl;

import cn.fuck.engine.data.core.entity.MPEntity;
import cn.fuck.engine.data.core.service.BaseManager;
import cn.fuck.engine.rest.core.service.BaseService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dromara.hutool.core.bean.BeanUtil;
import org.dromara.hutool.core.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public class BaseServiceImpl<Manager extends BaseManager<Entity>, Entity extends MPEntity, SaveDTO, UpdateDTO, QueryDTO, ResultVO>
        implements BaseService<Entity, SaveDTO, UpdateDTO, QueryDTO, ResultVO> {

    @Autowired
    protected Manager baseManger;

    @Override
    public Class<Entity> getEntityClass() {
        return baseManger.getEntityClass();
    }

    @Override
    public BaseManager<Entity> getBaseManager() {
        return baseManger;
    }

    // 保存操作
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Entity save(SaveDTO saveDTO) {
        Entity entity = saveBefore(saveDTO);
        baseManger.save(entity);
        return entity;
    }

    protected Entity saveBefore(SaveDTO saveDTO) {
        return BeanUtil.toBean(saveDTO, getEntityClass());
    }

    protected void saveAfter(SaveDTO saveDTO, Entity entity) {
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Entity> saveBatch(List<SaveDTO> saveDTOList) {
        List<Entity> entityList = saveBatchBefore(saveDTOList);
        baseManger.saveBatch(entityList);
        saveBatchAfter(saveDTOList, entityList);
        return entityList;
    }

    protected List<Entity> saveBatchBefore(List<SaveDTO> saveDTOList) {
        return BeanUtil.copyToList(saveDTOList, getEntityClass());
    }

    protected void saveBatchAfter(List<SaveDTO> saveDTOList, List<Entity> entityList) {
    }

    // 更新操作
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Entity updateById(UpdateDTO updateDTO) {
        Entity entity = updateBefore(updateDTO);
        baseManger.updateById(entity);
        updateAfter(updateDTO, entity);
        return entity;
    }

    protected Entity updateBefore(UpdateDTO updateDTO) {
        return BeanUtil.toBean(updateDTO, getEntityClass());
    }

    protected void updateAfter(UpdateDTO updateDTO, Entity entity) {
    }

    // 删除操作
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<String> idList) {
        return baseManger.removeByIds(idList);
    }

    // 查询操作
    @Override
    @Transactional(readOnly = true)
    public Entity getById(String id) {
        Entity entity = baseManger.getById(id);
        Assert.notNull(entity, "Id Error");
        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entity> list() {
        return baseManger.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entity> list(Wrapper<Entity> queryWrapper) {
        return baseManger.list(queryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entity> listByIds(List<String> ids) {
        return baseManger.listByIds(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public <Page extends IPage<Entity>> Page page(Page page, Wrapper<Entity> queryWrapper) {
        return baseManger.page(page, queryWrapper);
    }
}
