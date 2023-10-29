package cn.fuck.engine.oauth2.authentication.dto;

import cn.fuck.engine.assistant.core.definition.domain.AbstractEntity;

/**
 * <p>Description: 用户错误状态信息 </p>
 * @date : 2022/7/10 16:46
 */
public class SignInErrorStatus extends AbstractEntity {

    private int errorTimes;
    private int remainTimes;
    private Boolean locked;

    public int getErrorTimes() {
        return errorTimes;
    }

    public void setErrorTimes(int errorTimes) {
        this.errorTimes = errorTimes;
    }

    public int getRemainTimes() {
        return remainTimes;
    }

    public void setRemainTimes(int remainTimes) {
        this.remainTimes = remainTimes;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
