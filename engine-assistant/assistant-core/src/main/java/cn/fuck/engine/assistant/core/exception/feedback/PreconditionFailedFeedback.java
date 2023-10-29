package cn.fuck.engine.assistant.core.exception.feedback;

import cn.fuck.engine.assistant.core.domain.Feedback;
import org.apache.hc.core5.http.HttpStatus;

/**
 * <p>Description: 412 类型错误反馈 </p>
 * <p>
 * 412	Precondition Failed	客户端请求信息的先决条件错误
 * @date : 2023/9/26 8:53
 */
public class PreconditionFailedFeedback extends Feedback {
    public PreconditionFailedFeedback(String value) {
        super(value, HttpStatus.SC_PRECONDITION_FAILED);
    }

    public PreconditionFailedFeedback(String value, int custom) {
        super(value, HttpStatus.SC_PRECONDITION_FAILED, custom);
    }
}
