package cn.fuck.engine.oauth2.authorization.auditing;

import cn.fuck.engine.data.core.entity.BaseEntity;
import cn.fuck.engine.oauth2.core.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyBatis Plus 元数据处理类
 * 用于自动 注入 id, createTime, updateTime, createdBy, updatedBy 等字段
 * <p>
 * 判断逻辑：
 * 1. insert 方法，自动填充 id, createTime, updateTime, createdBy, updatedBy 字段，字段为空则自动生成，不为空则使用传递进来的
 * 2. update 方法，自动填充 id, updateTime, updatedBy 字段，字段为空则自动生成，不为空则使用传递进来的
 * <p>
 * 注入值：
 * id：  IdUtil.getSnowflake(workerId, dataCenterId);
 * createTime：LocalDateTime.now()
 * updateTime：LocalDateTime.now()
 * createdBy：BaseContextHandler.getLoginId()
 * updatedBy：BaseContextHandler.getLoginId()
 *
 */
@Slf4j
public class FuckMetaObjectHandler implements MetaObjectHandler {


    public FuckMetaObjectHandler() {
        super();
    }

    /**
     * 注意：不支持 复合主键 自动注入！！
     * <p>
     * 1、所有的继承了Entity、SuperEntity的实体，在insert时，
     * id： id为空时， 通过IdGenerate生成唯一ID。
     * createdBy, updatedBy: 自动赋予 当前线程上的登录人id。
     * createTime, updateTime: 自动赋予 服务器的当前时间。
     * <p>
     * 注意：实体中字段为空时才会赋值，若手动传值了，这里不会重新赋值
     * <p>
     * 2、未继承任何父类的实体，且主键标注了 @TableId(value = "xxx", type = IdType.INPUT) 也能自动赋值，主键的字段名称任意
     * <p>
     * 3、未继承任何父类的实体，但主键名为 id，也能自动赋值
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        fillCreated(metaObject);
        fillUpdated(metaObject);
    }


    /**
     * 所有的继承了Entity、SuperEntity的实体，在update时，
     * updatedBy: 自动赋予 当前线程上的登录人id
     * updateTime: 自动赋予 服务器的当前时间
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("start update fill ....");
        fillUpdated(metaObject);
    }


    private void fillCreated(MetaObject metaObject) {
        // 设置创建时间和创建人
        if (metaObject.getOriginalObject() instanceof BaseEntity) {
            created(metaObject);
            return;
        }

        if (metaObject.hasGetter("createBy")) {
            Object oldVal = metaObject.getValue("createBy");
            if (oldVal == null) {
                this.setFieldValByName("createBy", SecurityUtils.getUserId(), metaObject);
            }
        }
        if (metaObject.hasGetter("createTime")) {
            Object oldVal = metaObject.getValue("createTime");
            if (oldVal == null) {
                this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
            }
        }

    }

    private void created(MetaObject metaObject) {
        BaseEntity entity = (BaseEntity) metaObject.getOriginalObject();
        if (StrUtil.isBlank(entity.getCreateBy())) {
            this.setFieldValByName("createBy", SecurityUtils.getUserId(), metaObject);
        }
        if (entity.getCreateTime() == null) {
            this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }


    private void fillUpdated(MetaObject metaObject) {
        // 修改人 修改时间
        if (metaObject.getOriginalObject() instanceof BaseEntity) {
            update(metaObject);
            return;
        }

        if (metaObject.hasGetter("updateBy")) {
            Object oldVal = metaObject.getValue("updateBy");
            if (oldVal == null) {
                this.setFieldValByName("updateBy", SecurityUtils.getUserId(), metaObject);
            }
        }
        if (metaObject.hasGetter("updateTime")) {
            Object oldVal = metaObject.getValue("updateTime");
            if (oldVal == null) {
                this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            }
        }
    }

    private void update(MetaObject metaObject) {
        BaseEntity entity = (BaseEntity) metaObject.getOriginalObject();
        if (StrUtil.isBlank(entity.getUpdateBy())) {
            this.setFieldValByName("updateBy", SecurityUtils.getUserId(), metaObject);
        }
        if (entity.getUpdateTime() == null) {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }

}
