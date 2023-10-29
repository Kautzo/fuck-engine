package cn.fuck.engine.assistant.core.domain;

import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: 用户登录额外信息 </p>
 */
@Data
public class PrincipalDetails {

    private String openId;

    private String userName;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(BaseConstants.OPEN_ID, this.openId);
        map.put(BaseConstants.USERNAME, this.userName);
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrincipalDetails that = (PrincipalDetails) o;
        return Objects.equal(openId, that.openId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(openId);
    }

}
