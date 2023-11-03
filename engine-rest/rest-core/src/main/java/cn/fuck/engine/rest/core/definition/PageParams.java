package cn.fuck.engine.rest.core.definition;

import cn.fuck.engine.rest.core.annotation.EnumeratedValue;
import cn.fuck.engine.rest.core.utils.Wraps;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.hutool.core.map.MapUtil;
import org.dromara.hutool.core.text.StrPool;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.core.text.split.SplitUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Schema(title = "PageParams", description = "分页参数")
public class PageParams<T> {

    @Valid
    @Schema(description = "查询参数")
    private T model;

    @Schema(description = "当前页", example = "1")
    private long pageNum = 1;

    @Schema(description = "页面大小", example = "10")
    private long pageSize = 10;

    @Schema(description = "排序,默认id", allowableValues = "id,createTime,updateTime", example = "id")
    private String sort = "id";

    @EnumeratedValue(names = {"ASC", "DESC"}, message = "排序方式的值只能是大写 ASC 或者 DESC")
    @Schema(description = "排序规则, 默认DESC", allowableValues = "ASC, DESC", example = "DESC")
    private String order = "DESC";

    @Schema(description = "扩展参数")
    private Map<String, Object> extra = MapUtil.newHashMap();


    public PageParams(long pageNum, long pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * 构建分页对象
     *
     * @return 分页对象
     */
    @JsonIgnore
    public <E> IPage<E> buildPage() {
        PageParams params = this;
        return new Page(params.getPageNum(), params.getPageSize());
    }

    /**
     * 构建分页对象
     * <p>
     * 支持多个字段排序，用法：
     * eg.1, 参数：{order:"name,id", order:"desc,asc" }。 排序： name desc, id asc
     * eg.2, 参数：{order:"name", order:"desc,asc" }。 排序： name desc
     * eg.3, 参数：{order:"name,id", order:"desc" }。 排序： name desc
     *
     * @param entityClazz 字段中标注了@TableName 或 @TableId 注解的实体类。
     * @return 分页对象
     */
    @JsonIgnore
    public <E> IPage<E> buildPage(Class<?> entityClazz) {
        PageParams params = this;
        //没有排序参数
        if (StrUtil.isBlank(params.getSort())) {
            return new Page(params.getPageNum(), params.getPageSize());
        }

        Page<E> page = new Page(params.getPageNum(), params.getPageSize());

        List<OrderItem> orders = new ArrayList<>();
        String[] sortArr = SplitUtil.splitToArray(params.getSort(), StrPool.COMMA);
        String[] orderArr = SplitUtil.splitToArray(params.getOrder(), StrPool.COMMA);

        int len = Math.min(sortArr.length, orderArr.length);
        for (int i = 0; i < len; i++) {
            String humpSort = sortArr[i];
            // 简单的 驼峰 转 下划线
            String underlineSort = Wraps.getDbField(humpSort, entityClazz);
            orders.add(StrUtil.equalsAny(orderArr[i], "ASC", "asc") ? OrderItem.asc(underlineSort) : OrderItem.desc(underlineSort));
        }
        page.setOrders(orders);
        return page;
    }

    /**
     * 计算当前分页偏移量
     */
    @JsonIgnore
    public long offset() {
        long pageNum = this.pageNum;
        if (pageNum <= 1L) {
            return 0L;
        }
        return (pageNum - 1) * this.pageSize;
    }

    @JsonIgnore
    public PageParams<T> put(String key, Object value) {
        if (this.extra == null) {
            this.extra = new HashMap<>(16);
        }
        this.extra.put(key, value);
        return this;
    }

    @JsonIgnore
    public PageParams<T> putAll(Map<String, Object> extra) {
        if (this.extra == null) {
            this.extra = new HashMap<>(16);
        }
        this.extra.putAll(extra);
        return this;
    }
}
