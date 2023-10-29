package cn.fuck.engine.rest.core.controller;

import cn.fuck.engine.assistant.core.definition.constants.DefaultConstants;
import cn.fuck.engine.assistant.core.definition.domain.Entity;
import cn.fuck.engine.assistant.core.domain.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.dromara.hutool.core.tree.MapTree;
import org.dromara.hutool.core.tree.TreeNode;
import org.dromara.hutool.core.tree.TreeUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p> Description : Controller基础定义 </p>
 * <p>
 * 这里只在方法上做了泛型，主要是考虑到返回的结果数据可以是各种类型，而不一定受限于某一种类型。
 * @date : 2020/4/29 18:56
 */
public interface Controller {

    /**
     * 数据实体转换为统一响应实体
     *
     * @param domain 数据实体
     * @param <E>    {@link Entity} 子类型
     * @return {@link Result} Entity
     */
    default <E extends Entity> Result<E> result(E domain) {
        if (ObjectUtils.isNotEmpty(domain)) {
            return Result.content(domain);
        } else {
            return Result.empty();
        }
    }

    /**
     * 数据列表转换为统一响应实体
     *
     * @param domains 数据实体 List
     * @param <E>     {@link Entity} 子类型
     * @return {@link Result} List
     */
    default <E extends Entity> Result<List<E>> result(List<E> domains) {

        if (null == domains) {
            return Result.failure("查询数据失败！");
        }

        if (CollectionUtils.isNotEmpty(domains)) {
            return Result.success("查询数据成功！", domains);
        } else {
            return Result.empty("未查询到数据！");
        }
    }

    /**
     * 数据分页对象转换为统一响应实体
     *
     * @param pages 分页查询结果 {@link Page}
     * @param <E>   {@link Entity} 子类型
     * @return {@link Result} Map
     */
    default <E extends Entity> Result<Map<String, Object>> result(Page<E> pages) {
        if (null == pages) {
            return Result.failure("查询数据失败！");
        }

        if (CollectionUtils.isNotEmpty(pages.getContent())) {
            return Result.success("查询数据成功！", getPageInfoMap(pages));
        } else {
            return Result.empty("未查询到数据！");
        }
    }

    /**
     * 数据 Map 转换为统一响应实体
     *
     * @param map 数据 Map
     * @return {@link Result} Map
     */
    default Result<Map<String, Object>> result(Map<String, Object> map) {

        if (null == map) {
            return Result.failure("查询数据失败！");
        }

        if (MapUtils.isNotEmpty(map)) {
            return Result.success("查询数据成功！", map);
        } else {
            return Result.empty("未查询到数据！");
        }
    }

    /**
     * 数据操作结果转换为统一响应实体
     *
     * @param parameter 数据ID
     * @param <ID>      ID 数据类型
     * @return {@link Result} String
     */
    default <ID extends Serializable> Result<String> result(ID parameter) {
        if (ObjectUtils.isNotEmpty(parameter)) {
            return Result.success();
        } else {
            return Result.failure();
        }
    }

    /**
     * 数据操作结果转换为统一响应实体
     *
     * @param status 操作状态
     * @return {@link Result} String
     */
    default Result<Boolean> result(boolean status) {
        if (status) {
            return Result.success("操作成功!", status);
        } else {
            return Result.failure("操作失败!", status);
        }
    }

    default <E extends Entity> Result<List<MapTree<String>>> result(List<E> domains, Converter<E, TreeNode<String>> toTreeNode) {
        if (ObjectUtils.isNotEmpty(domains)) {
            List<TreeNode<String>> treeNodes = domains.stream().map(toTreeNode::convert).collect(Collectors.toList());
            return Result.success("查询数据成功", TreeUtil.build(treeNodes, DefaultConstants.TREE_ROOT_ID));
        } else {
            return Result.empty("未查询到数据！");
        }
    }

    /**
     * Page 对象转换为 Map
     *
     * @param pages 分页查询结果 {@link Page}
     * @param <E>   {@link Entity} 子类型
     * @return Map
     */
    default <E extends Entity> Map<String, Object> getPageInfoMap(Page<E> pages) {
        return getPageInfoMap(pages.getContent(), pages.getTotalPages(), pages.getTotalElements());
    }

    /**
     * Page 对象转换为 Map
     *
     * @param content       数据实体 List
     * @param totalPages    分页总页数
     * @param totalElements 总的数据条目
     * @param <E>           {@link Entity} 子类型
     * @return Map
     */
    default <E extends Entity> Map<String, Object> getPageInfoMap(List<E> content, int totalPages, long totalElements) {
        Map<String, Object> result = new HashMap<>(8);
        result.put("content", content);
        result.put("totalPages", totalPages);
        result.put("totalElements", totalElements);
        return result;
    }
}
