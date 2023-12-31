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
 * <p>Description: OAuth2 Signature </p>
 */
@Getter
@Schema(name = "签名算法")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Signature implements BaseUiEnum<Integer> {

    /**
     * enum
     */
    RS256(0, "RS256"),
    RS384(1, "RS384"),
    RS512(2, "RS512"),
    ES256(3, "ES256"),
    ES384(4, "ES384"),
    ES512(5, "ES512"),
    PS256(6, "PS256"),
    PS384(7, "PS384"),
    PS512(8, "PS512");

    private static final Map<Integer, Signature> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (Signature signature : Signature.values()) {
            INDEX_MAP.put(signature.getValue(), signature);
            JSON_STRUCTURE.add(signature.getValue(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", signature.getValue())
                            .put("key", signature.name())
                            .put("text", signature.getDescription())
                            .put("index", signature.getValue())
                            .build());
        }
    }

    @JsonValue
    @EnumValue
    @Schema(title = "枚举值")
    private final Integer value;
    @Schema(name = "文字")
    private final String description;

    Signature(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static Signature get(Integer index) {
        return INDEX_MAP.get(index);
    }

    public static List<Map<String, Object>> getPreprocessedJsonStructure() {
        return JSON_STRUCTURE;
    }

}
