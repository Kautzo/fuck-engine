package cn.fuck.engine.oauth2.core.enums;

import cn.fuck.engine.assistant.core.definition.enums.BaseUiEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 应用类别 </p>
 */
@Getter
@Schema(title = "应用类型")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ApplicationType implements BaseUiEnum<Integer> {

    /**
     * 应用类型
     */
    WEB(0, "PC网页应用"),
    SERVICE(1, "服务应用"),
    APP(2, "手机APP应用"),
    WAP(3, "手机网页应用"),
    MINI(4, "小程序应用"),
    IOT(5, "物联网应用");

    private static final Map<Integer, ApplicationType> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCT = new ArrayList<>();

    static {
        for (ApplicationType applicationType : ApplicationType.values()) {
            INDEX_MAP.put(applicationType.getValue(), applicationType);
            JSON_STRUCT.add(applicationType.getValue(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", applicationType.getValue())
                            .put("key", applicationType.name())
                            .put("text", applicationType.getDescription())
                            .put("index", applicationType.getValue())
                            .build());
        }
    }

    @JsonValue
    @EnumValue
    @Schema(title = "枚举值")
    private final Integer value;
    @Schema(title = "文字")
    private final String description;

    ApplicationType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static ApplicationType get(Integer index) {
        return INDEX_MAP.get(index);
    }

    public static List<Map<String, Object>> getPreprocessedJsonStructure() {
        return JSON_STRUCT;
    }

}
