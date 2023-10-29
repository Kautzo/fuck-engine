package cn.fuck.engine.supplier.upms.logic.converter;

import cn.fuck.engine.assistant.core.utils.WellFormedUtils;
import cn.fuck.engine.supplier.upms.logic.entity.hr.SysOrganization;
import org.dromara.hutool.core.tree.TreeNode;
import org.springframework.core.convert.converter.Converter;


/**
 * <p>Description: SysOrganization 转 TreeNode 转换器 </p>
 * @date : 2023/5/30 10:37
 */
public class SysOrganizationToTreeNodeConverter implements Converter<SysOrganization, TreeNode<String>> {
    @Override
    public TreeNode<String> convert(SysOrganization sysOrganization) {
        TreeNode<String> treeNode = new TreeNode<>();
        treeNode.setId(sysOrganization.getOrganizationId());
        treeNode.setName(sysOrganization.getOrganizationName());
        treeNode.setParentId(WellFormedUtils.parentId(sysOrganization.getParentId()));
        return treeNode;
    }
}
