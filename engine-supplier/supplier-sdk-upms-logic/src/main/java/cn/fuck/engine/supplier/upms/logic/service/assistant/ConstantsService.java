package cn.fuck.engine.supplier.upms.logic.service.assistant;

import cn.fuck.engine.data.core.enums.DataItemStatus;
import cn.fuck.engine.supplier.upms.logic.enums.Gender;
import cn.fuck.engine.supplier.upms.logic.enums.Identity;
import cn.fuck.engine.supplier.upms.logic.enums.OrganizationCategory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: Upms 常量 服务 </p>
 * @date : 2022/2/25 15:37
 */
@Service
public class ConstantsService {

    private static final List<Map<String, Object>> STATUS_ENUM = DataItemStatus.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> GENDER_ENUM = Gender.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> IDENTITY_ENUM = Identity.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> ORGANIZATION_CATEGORY_ENUM = OrganizationCategory.getPreprocessedJsonStructure();

    public Map<String, Object> getAllEnums() {
        Map<String, Object> map = new HashMap<>(8);
        map.put("status", STATUS_ENUM);
        map.put("gender", GENDER_ENUM);
        map.put("identity", IDENTITY_ENUM);
        map.put("organizationCategory", ORGANIZATION_CATEGORY_ENUM);
        return map;
    }
}
