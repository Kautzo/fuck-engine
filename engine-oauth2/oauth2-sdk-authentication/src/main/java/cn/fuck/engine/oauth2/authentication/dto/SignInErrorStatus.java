package cn.fuck.engine.oauth2.authentication.dto;


import lombok.Data;

/**
 * <p>Description: 用户错误状态信息 </p>
 */
@Data
public class SignInErrorStatus {

    private int errorTimes;
    private int remainTimes;
    private Boolean locked;

}
