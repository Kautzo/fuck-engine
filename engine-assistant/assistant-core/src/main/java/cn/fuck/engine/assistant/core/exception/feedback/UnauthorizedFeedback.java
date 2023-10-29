package cn.fuck.engine.assistant.core.exception.feedback;

import cn.fuck.engine.assistant.core.domain.Feedback;
import org.apache.hc.core5.http.HttpStatus;

/**
 * <p>Description: 401 类型错误反馈 </p>
 * <p>
 * 401	Unauthorized	请求要求用户的身份认证
 * @date : 2023/9/26 8:48
 */
public class UnauthorizedFeedback extends Feedback {
    public UnauthorizedFeedback(String value) {
        super(value, HttpStatus.SC_UNAUTHORIZED);
    }

    public UnauthorizedFeedback(String value, int custom) {
        super(value, HttpStatus.SC_UNAUTHORIZED, custom);
    }
}
