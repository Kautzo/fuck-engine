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
 * @author gengwei.zheng
 */
@Schema(title = "性别")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Gender implements BaseUiEnum<Integer> {
    /**
     * enum
     */
    MAN(0, "男"),
    WOMAN(1, "女"),
    OTHERS(2, "其它");

    private static final Map<Integer, Gender> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (Gender gender : Gender.values()) {
            INDEX_MAP.put(gender.getValue(), gender);
            JSON_STRUCTURE.add(gender.getValue(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", gender.getValue())
                            .put("key", gender.name())
                            .put("text", gender.getDescription())
                            .put("index", gender.getValue())
                            .build());
        }
    }

    @Schema(title = "枚举值")
    private final Integer value;
    @Schema(title = "文字")
    private final String description;

    Gender(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static Gender get(Integer index) {
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
        return description;
    }
}
