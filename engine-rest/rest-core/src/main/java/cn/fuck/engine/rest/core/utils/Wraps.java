package cn.fuck.engine.rest.core.utils;


import cn.fuck.engine.rest.core.exception.IllegalOperationException;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dromara.hutool.core.date.DateUtil;
import org.dromara.hutool.core.lang.Assert;
import org.dromara.hutool.core.map.MapUtil;
import org.dromara.hutool.core.reflect.FieldUtil;
import org.dromara.hutool.core.text.StrUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;


/**
 * Wrappers 工具类， 该方法的主要目的是为了 缩短代码长度
 *
 */
public final class Wraps {
    /**
     * 开始时间
     */
    public static final String ST = "_st";
    /**
     * 结束时间
     */
    public static final String ED = "_ed";
    /**
     * 等于
     */
    public static final String EQ = "_eq";
    /**
     * 不等于
     */
    public static final String NE = "_ne";
    /**
     * 大于等于
     */
    public static final String GE = "_ge";
    /**
     * 大于
     */
    public static final String GT = "_gt";
    /**
     * 小于
     */
    public static final String LT = "_lt";
    /**
     * 小于等于
     */
    public static final String LE = "_le";
    /**
     * 模糊
     */
    public static final String LIKE = "_like";
    /**
     * 左模糊
     */
    public static final String LIKE_LEFT = "_likeLeft";
    /**
     * 右模糊
     */
    public static final String LIKE_RIGHT = "_likeRight";

    /**
     * 获取 QueryWrap&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return QueryWrapper&lt;T&gt;
     */
    public static <T> QueryWrapper<T> q() {
        return new QueryWrapper<>();
    }

    /**
     * 获取 QueryWrap&lt;T&gt;
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return QueryWrapper&lt;T&gt;
     */
    public static <T> QueryWrapper<T> q(T entity) {
        return new QueryWrapper<>(entity);
    }

    /**
     * 构建查询条件
     * 1. 若model不为空，则将model中不为空的参数拼接到sql中；
     * 2. 若extra中有 _st、_ed、_ge、_gt、_le、_lt、_eq、_ne、_like、_likeLeft、_likeRigth 等结尾的参数，在sql中拼接为相应的查询条件
     *
     * @param model      model 条件对象实例
     * @param extra      extra 扩展参数
     * @param modelClazz modelClazz 条件对象类型
     */
    public static <Entity> QueryWrapper<Entity> q(Entity model, Map<String, Object> extra, Class<Entity> modelClazz) {
        QueryWrapper<Entity> wrapper = model != null ? Wraps.q(model) : Wraps.q();

        if (MapUtil.isNotEmpty(extra)) {
            //TODO 拼装区间

        }
        return wrapper;
    }

    /**
     * 根据 bean字段 反射出 数据库字段
     *
     * @param beanField 字段
     * @param clazz     类型
     * @return 数据库字段名
     */
    public static String getDbField(String beanField, Class<?> clazz) {
        Assert.notNull(clazz, "实体类不能为空");
        Assert.notEmpty(beanField, "字段名不能为空");
        Field field = FieldUtil.getField(clazz, beanField);
        Assert.notNull(field, "在类：{}中找不到属性：{}", clazz.getSimpleName(), beanField);

        TableField tf = field.getAnnotation(TableField.class);
        if (tf != null && StrUtil.isNotBlank(tf.value())) {
            return tf.value();
        }
        TableId ti = field.getAnnotation(TableId.class);
        if (ti != null && StrUtil.isNotBlank(ti.value())) {
            return ti.value();
        }
        throw new IllegalOperationException(StrUtil.format("列表映射失败:{}", beanField) );
    }

}
