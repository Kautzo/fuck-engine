package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.oauth2.management.entity.OAuth2Compliance;
import cn.fuck.engine.oauth2.management.repository.OAuth2ComplianceRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import com.google.common.net.HttpHeaders;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.http.server.servlet.JakartaServletUtil;
import org.dromara.hutool.http.useragent.UserAgent;
import org.dromara.hutool.http.useragent.UserAgentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: ActionAuditService </p>
 * @date : 2022/7/7 20:37
 */
@Service
public class OAuth2ComplianceService extends BaseService<OAuth2Compliance, String> {

    private static final Logger log = LoggerFactory.getLogger(OAuth2ComplianceService.class);

    private final OAuth2ComplianceRepository complianceRepository;

    public OAuth2ComplianceService(OAuth2ComplianceRepository complianceRepository) {
        this.complianceRepository = complianceRepository;
    }

    @Override
    public BaseRepository<OAuth2Compliance, String> getRepository() {
        return complianceRepository;
    }

    public Page<OAuth2Compliance> findByCondition(int pageNumber, int pageSize, String principalName, String clientId, String ip) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Specification<OAuth2Compliance> specification = (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(principalName)) {
                predicates.add(criteriaBuilder.equal(root.get("principalName"), principalName));
            }

            if (StringUtils.isNotBlank(clientId)) {
                predicates.add(criteriaBuilder.equal(root.get("clientId"), clientId));
            }

            if (StringUtils.isNotBlank(ip)) {
                predicates.add(criteriaBuilder.equal(root.get("ip"), ip));
            }

            Predicate[] predicateArray = new Predicate[predicates.size()];
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(predicateArray)));
            return criteriaQuery.getRestriction();
        };

        return this.findByPage(specification, pageable);
    }

    public OAuth2Compliance save(String principalName, String clientId, String operation, HttpServletRequest request) {
        OAuth2Compliance compliance = toEntity(principalName, clientId, operation, request);
        log.debug("[FUCK] |- Sign in user is [{}]", compliance);
        return super.save(compliance);
    }

    private UserAgent getUserAgent(HttpServletRequest request) {
        return UserAgentUtil.parse(request.getHeader(HttpHeaders.USER_AGENT));
    }

    private String getIp(HttpServletRequest request) {
        return JakartaServletUtil.getClientIP(request, "");
    }

    public OAuth2Compliance toEntity(String principalName, String clientId, String operation, HttpServletRequest request) {
        OAuth2Compliance audit = new OAuth2Compliance();
        audit.setPrincipalName(principalName);
        audit.setClientId(clientId);
        audit.setOperation(operation);

        UserAgent userAgent = getUserAgent(request);
        if (ObjectUtils.isNotEmpty(userAgent)) {
            audit.setIp(getIp(request));
            audit.setMobile(userAgent.isMobile());
            audit.setOsName(userAgent.getOs().getName());
            audit.setBrowserName(userAgent.getBrowser().getName());
            audit.setMobileBrowser(userAgent.getBrowser().isMobile());
            audit.setEngineName(userAgent.getEngine().getName());
            audit.setMobilePlatform(userAgent.getPlatform().isMobile());
            audit.setIphoneOrIpod(userAgent.getPlatform().isIPhoneOrIPod());
            audit.setIpad(userAgent.getPlatform().isIPad());
            audit.setIos(userAgent.getPlatform().isIos());
            audit.setAndroid(userAgent.getPlatform().isAndroid());
        }

        return audit;
    }
}