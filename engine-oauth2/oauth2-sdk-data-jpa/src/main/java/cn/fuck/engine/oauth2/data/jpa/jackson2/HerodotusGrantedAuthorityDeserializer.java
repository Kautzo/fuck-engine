package cn.fuck.engine.oauth2.data.jpa.jackson2;

import cn.fuck.engine.assistant.core.json.jackson2.utils.JsonNodeUtils;
import cn.fuck.engine.oauth2.core.definition.domain.HerodotusGrantedAuthority;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * <p>Description: HerodotusGrantedAuthority 反序列化 </p>
 * @date : 2022/3/17 20:28
 */
public class HerodotusGrantedAuthorityDeserializer extends JsonDeserializer<HerodotusGrantedAuthority> {
    @Override
    public HerodotusGrantedAuthority deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        String authority = JsonNodeUtils.findStringValue(jsonNode, "authority");
        return new HerodotusGrantedAuthority(authority);
    }
}
