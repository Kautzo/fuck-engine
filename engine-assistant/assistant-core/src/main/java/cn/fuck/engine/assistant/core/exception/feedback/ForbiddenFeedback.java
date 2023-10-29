package cn.fuck.engine.assistant.core.exception.feedback;

import cn.fuck.engine.assistant.core.domain.Feedback;
import org.apache.hc.core5.http.HttpStatus;

/**
 * <p>Description: 403 类型错误反馈 </p>
 * <p>
 * 403	Forbidden	服务器理解请求客户端的请求，但是拒绝执行此请求
 * @date : 2023/9/26 8:51
 */
public class ForbiddenFeedback extends Feedback {
    public ForbiddenFeedback(String value) {
        super(value, HttpStatus.SC_FORBIDDEN);
    }

    public ForbiddenFeedback(String value, int custom) {
        super(value, HttpStatus.SC_FORBIDDEN, custom);
    }
}
