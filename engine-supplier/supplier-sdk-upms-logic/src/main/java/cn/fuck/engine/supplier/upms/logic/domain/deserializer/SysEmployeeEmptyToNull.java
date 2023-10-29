package cn.fuck.engine.supplier.upms.logic.domain.deserializer;

import cn.fuck.engine.supplier.upms.logic.entity.hr.SysEmployee;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * <p>Description: SysEmployee 反序列化 空对象 '{}' 转 为 null </p>
 * @date : 2021/10/10 20:19
 */
public class SysEmployeeEmptyToNull extends JsonDeserializer<SysEmployee> {

    @Override
    public SysEmployee deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.readValueAsTree();
        if (jsonNode.isEmpty() || jsonNode.isNull()) {
            return null;
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.treeToValue(jsonNode, SysEmployee.class);
        }
    }
}
