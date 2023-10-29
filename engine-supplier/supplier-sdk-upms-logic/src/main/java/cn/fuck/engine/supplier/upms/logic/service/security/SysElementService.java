package cn.fuck.engine.supplier.upms.logic.service.security;

import cn.fuck.engine.supplier.upms.logic.repository.security.SysElementRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysElement;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysRole;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Description: SysMenuService </p>
 * @date : 2022/7/14 15:55
 */
@Service
public class SysElementService extends BaseService<SysElement, String> {

    private final SysElementRepository sysElementRepository;

    public SysElementService(SysElementRepository sysElementRepository) {
        this.sysElementRepository = sysElementRepository;
    }

    @Override
    public BaseRepository<SysElement, String> getRepository() {
        return sysElementRepository;
    }

    public Page<SysElement> findByCondition(int pageNumber, int pageSize, String path, String title) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Specification<SysElement> specification = (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (ObjectUtils.isNotEmpty(path)) {
                predicates.add(criteriaBuilder.like(root.get("path"), like(path)));
            }

            if (ObjectUtils.isNotEmpty(title)) {
                predicates.add(criteriaBuilder.like(root.get("title"), like(title)));
            }

            Predicate[] predicateArray = new Predicate[predicates.size()];
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(predicateArray)));
            return criteriaQuery.getRestriction();
        };

        return this.findByPage(specification, pageable);
    }

    public SysElement assign(String elementId, String[] roles) {

        Set<SysRole> sysRoles = new HashSet<>();
        for (String role : roles) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(role);
            sysRoles.add(sysRole);
        }

        SysElement sysElement = findById(elementId);
        sysElement.setRoles(sysRoles);

        return saveAndFlush(sysElement);
    }
}
