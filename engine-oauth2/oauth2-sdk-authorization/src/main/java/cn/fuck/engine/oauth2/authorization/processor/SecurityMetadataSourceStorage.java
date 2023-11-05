package cn.fuck.engine.oauth2.authorization.processor;

import cn.fuck.engine.oauth2.authorization.definition.FuckConfigAttribute;
import cn.fuck.engine.oauth2.authorization.definition.FuckRequest;
import cn.fuck.engine.oauth2.authorization.definition.FuckRequestMatcher;
import cn.fuck.engine.cache.jetcache.utils.JetCacheUtils;
import cn.fuck.engine.oauth2.core.constants.OAuth2Constants;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>Description: SecurityAttribute 本地存储 </p>
 */
@Slf4j
public class SecurityMetadataSourceStorage {

    private static final String KEY_COMPATIBLE = "COMPATIBLE";
    /**
     * 模式匹配权限缓存。主要存储 包含 "*"、"?" 和 "{"、"}" 等特殊字符的路径权限。
     * 该种权限，需要通过遍历，利用 AntPathRequestMatcher 机制进行匹配
     */
    private final Cache<String, LinkedHashMap<FuckRequest, List<FuckConfigAttribute>>> compatible;
    /**
     * 直接索引权限缓存，主要存储全路径权限
     * 该种权限，直接通过 Map Key 进行获取
     */
    private final Cache<FuckRequest, List<FuckConfigAttribute>> indexable;

    public SecurityMetadataSourceStorage() {
        this.compatible = JetCacheUtils.create(OAuth2Constants.CACHE_NAME_SECURITY_METADATA_COMPATIBLE, CacheType.BOTH, null, true, true);
        this.indexable = JetCacheUtils.create(OAuth2Constants.CACHE_NAME_SECURITY_METADATA_INDEXABLE, CacheType.BOTH, null, true, true);
    }

    /**
     * 从 compatible 缓存中读取数据。
     *
     * @return 需要进行模式匹配的权限数据
     */
    private LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> readFromCompatible() {
        LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> compatible = this.compatible.get(KEY_COMPATIBLE);
        if (MapUtils.isNotEmpty(compatible)) {
            return compatible;
        }
        return new LinkedHashMap<>();

    }

    /**
     * 写入 compatible 缓存
     *
     * @param compatible 请求路径和权限配置属性映射Map
     */
    private void writeToCompatible(LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> compatible) {
        this.compatible.put(KEY_COMPATIBLE, compatible);
    }

    /**
     * 从 indexable 缓存中读取数据
     *
     * @param fuckRequest 自定义扩展的 AntPathRequestMatchers {@link FuckRequest}
     * @return 权限配置属性对象集合
     */
    private List<FuckConfigAttribute> readFromIndexable(FuckRequest fuckRequest) {
        return this.indexable.get(fuckRequest);
    }

    /**
     * 写入 indexable 缓存
     *
     * @param fuckRequest 自定义扩展的 AntPathRequestMatchers {@link FuckRequest}
     * @param configAttributes 权限配置属性
     */
    private void writeToIndexable(FuckRequest fuckRequest, List<FuckConfigAttribute> configAttributes) {
        this.indexable.put(fuckRequest, configAttributes);
    }

    /**
     * 根据请求的 url 和 method 获取权限对象
     *
     * @param url    请求 URL
     * @param method 请求 method
     * @return 与请求url 和 method 匹配的权限数据，或者是空集合
     */
    public List<FuckConfigAttribute> getConfigAttribute(String url, String method) {
        FuckRequest fuckRequest = new FuckRequest(url, method);
        return readFromIndexable(fuckRequest);
    }

    /**
     * 从 compatible 缓存中获取全部不需要路径匹配的（包含*号的url）请求权限映射Map
     *
     * @return 如果缓存中存在，则返回请求权限映射Map集合，如果不存在则返回一个空的{@link LinkedHashMap}
     */
    public LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> getCompatible() {
        return readFromCompatible();
    }

    /**
     * 向 compatible 缓存中添加需要路径匹配的（包含*号的url）请求权限映射Map。
     * <p>
     * 如果缓存中不存在以{@link RequestMatcher}为Key的数据，那么添加数据
     * 如果缓存中存在以{@link RequestMatcher}为Key的数据，那么合并数据
     *
     * @param fuckRequest 请求匹配对象 {@link FuckRequest}
     * @param configAttributes 权限配置 {@link ConfigAttribute}
     */
    private void appendToCompatible(FuckRequest fuckRequest, List<FuckConfigAttribute> configAttributes) {
        LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> compatible = this.getCompatible();
//        compatible.merge(requestMatcher, configAttributes, (oldConfigAttributes, newConfigAttributes) -> {
//            newConfigAttributes.addAll(oldConfigAttributes);
//            return newConfigAttributes;
//        });

        // 使用merge会让整个功能的设计更加复杂，暂时改为直接覆盖已有数据，后续视情况再做变更。
        compatible.put(fuckRequest, configAttributes);
        log.trace("[FUCK] |- Append [{}] to Compatible cache, current size is [{}]", fuckRequest, compatible.size());
        writeToCompatible(compatible);
    }

    /**
     * 向 compatible 缓存中添加需要路径匹配的（包含*号的url）请求权限映射Map。
     * <p>
     * 如果缓存中不存在以{@link RequestMatcher}为Key的数据，那么添加数据
     * 如果缓存中存在以{@link RequestMatcher}为Key的数据，那么合并数据
     *
     * @param configAttributes 请求权限映射Map
     */
    private void appendToCompatible(LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> configAttributes) {
        configAttributes.forEach(this::appendToCompatible);
    }

    /**
     * 向 indexable 缓存中添加需请求权限映射。
     * <p>
     * 如果缓存中不存在以{@link FuckRequest}为Key的数据，那么添加数据
     * 如果缓存中存在以{@link FuckRequest}为Key的数据，那么合并数据
     *
     * @param fuckRequest 请求匹配对象 {@link FuckRequest}
     * @param configAttributes 权限配置 {@link FuckConfigAttribute}
     */
    private void appendToIndexable(FuckRequest fuckRequest, List<FuckConfigAttribute> configAttributes) {
        writeToIndexable(fuckRequest, configAttributes);
    }

    /**
     * 向 indexable 缓存中添加请求权限映射Map。
     *
     * @param configAttributes 请求权限映射Map
     */
    private void appendToIndexable(LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> configAttributes) {
        configAttributes.forEach(this::appendToIndexable);
    }

    /**
     * 将权限数据添加至本地存储
     *
     * @param configAttributes 权限数据
     * @param isIndexable      true 存入 indexable cache；false 存入 compatible cache
     */
    public void addToStorage(LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> configAttributes, boolean isIndexable) {
        if (MapUtils.isNotEmpty(configAttributes)) {
            if (isIndexable) {
                appendToIndexable(configAttributes);
            } else {
                appendToCompatible(configAttributes);
            }
        }
    }


    /**
     * 将权限数据添加至本地存储，存储之前进行规则冲突校验
     *
     * @param matchers         校验资源
     * @param configAttributes 权限数据
     * @param isIndexable      true 存入 indexable cache；false 存入 compatible cache
     */
    public void addToStorage(LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> matchers, LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> configAttributes, boolean isIndexable) {
        LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> result = new LinkedHashMap<>();
        if (MapUtils.isNotEmpty(matchers) && MapUtils.isNotEmpty(configAttributes)) {
            result = checkConflict(matchers, configAttributes);
        }

        addToStorage(result, isIndexable);
    }

    /**
     * 规则冲突校验
     * <p>
     * 如存在规则冲突，则保留可支持最大化范围规则，冲突的其它规则则不保存
     *
     * @param matchers         校验资源
     * @param configAttributes 权限数据
     * @return 去除冲突的权限数据
     */
    private LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> checkConflict(LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> matchers, LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> configAttributes) {

        LinkedHashMap<FuckRequest, List<FuckConfigAttribute>> result = new LinkedHashMap<>(configAttributes);

        for (FuckRequest matcher : matchers.keySet()) {
            for (FuckRequest item : configAttributes.keySet()) {
                FuckRequestMatcher requestMatcher = new FuckRequestMatcher(matcher);
                if (requestMatcher.matches(item)) {
                    result.remove(item);
                    log.trace("[FUCK] |- Pattern [{}] is conflict with [{}], so remove it.", item.getPattern(), matcher.getPattern());
                }
            }
        }

        return result;
    }
}
