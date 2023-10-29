package cn.fuck.engine.supplier.upms.logic.converter;

import cn.fuck.engine.assistant.core.utils.WellFormedUtils;
import cn.fuck.engine.supplier.upms.logic.entity.hr.SysDepartment;
import org.dromara.hutool.core.tree.TreeNode;
import org.springframework.core.convert.converter.Converter;


/**
 * <p>Description: SysDepartment 转 TreeNode 转换器 </p>
 * @date : 2023/5/30 10:37
 */
public class SysDepartmentToTreeNodeConverter implements Converter<SysDepartment, TreeNode<String>> {
    @Override
    public TreeNode<String> convert(SysDepartment sysDepartment) {
        TreeNode<String> treeNode = new TreeNode<>();
        treeNode.setId(sysDepartment.getDepartmentId());
        treeNode.setName(sysDepartment.getDepartmentName());
        treeNode.setParentId(WellFormedUtils.parentId(sysDepartment.getParentId()));
        return treeNode;
    }
}
