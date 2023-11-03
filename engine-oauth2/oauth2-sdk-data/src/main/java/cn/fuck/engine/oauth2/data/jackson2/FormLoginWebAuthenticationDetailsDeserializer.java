package cn.fuck.engine.oauth2.data.jackson2;

import cn.fuck.engine.assistant.core.json.jackson2.utils.JsonNodeUtils;
import cn.fuck.engine.oauth2.core.definition.details.FormLoginWebAuthenticationDetails;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * <p>Description: FormLoginWebAuthenticationDetailsDeserializer </p>
 * @date : 2022/4/14 11:48
 */
public class FormLoginWebAuthenticationDetailsDeserializer extends JsonDeserializer<FormLoginWebAuthenticationDetails> {
    @Override
    public FormLoginWebAuthenticationDetails deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);

        String remoteAddress = JsonNodeUtils.findStringValue(jsonNode, "remoteAddress");
        String sessionId = JsonNodeUtils.findStringValue(jsonNode, "sessionId");
        String parameterName = JsonNodeUtils.findStringValue(jsonNode, "parameterName");
        String category = JsonNodeUtils.findStringValue(jsonNode, "category");
        String code = JsonNodeUtils.findStringValue(jsonNode, "code");
        String identity = JsonNodeUtils.findStringValue(jsonNode, "identity");
        boolean closed = JsonNodeUtils.findBooleanValue(jsonNode, "closed");

        return new FormLoginWebAuthenticationDetails(remoteAddress, sessionId, closed, parameterName, category, code, identity);
    }
}
