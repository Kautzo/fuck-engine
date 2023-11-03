package cn.fuck.engine.upms.enums;

import cn.fuck.engine.assistant.core.definition.enums.BaseUiEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 机构类别 </p>
 */
@Schema(title = "机构类别")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrganizationCategory implements BaseUiEnum<Integer> {

    /**
     * enum
     */
    ENTERPRISE(0, "企业机构"),
    PARTY(1, "党组机构"),
    LEAGUE(2, "团青机构");

    private static final Map<Integer, OrganizationCategory> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (OrganizationCategory organizationCategory : OrganizationCategory.values()) {
            INDEX_MAP.put(organizationCategory.getValue(), organizationCategory);
            JSON_STRUCTURE.add(organizationCategory.getValue(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", organizationCategory.getValue())
                            .put("key", organizationCategory.name())
                            .put("text", organizationCategory.getDescription())
                            .put("index", organizationCategory.getValue())
                            .build());
        }
    }

    @Schema(title = "枚举值")
    private final Integer value;
    @Schema(title = "文字")
    private final String description;

    OrganizationCategory(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static OrganizationCategory get(Integer index) {
        return INDEX_MAP.get(index);
    }

    public static List<Map<String, Object>> getPreprocessedJsonStructure() {
        return JSON_STRUCTURE;
    }

    /**
     * 不加@JsonValue，转换的时候转换出完整的对象。
     * 加了@JsonValue，只会显示相应的属性的值
     * <p>
     * 不使用@JsonValue @JsonDeserializer类里面要做相应的处理
     *
     * @return Enum索引
     */
    @JsonValue
    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
