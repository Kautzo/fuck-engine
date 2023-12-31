package cn.fuck.engine.upms.domain.deserializer;

import cn.fuck.engine.upms.entity.SysUser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * <p>Description: SysUser 反序列化 空对象 '{}' 转 为 null </p>
 */
public class SysUserEmptyToNull extends JsonDeserializer<SysUser> {

    @Override
    public SysUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.readValueAsTree();
        if (jsonNode.isEmpty() || jsonNode.isNull()) {
            return null;
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.treeToValue(jsonNode, SysUser.class);
        }
    }
}
