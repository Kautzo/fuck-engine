package cn.fuck.engine.assistant.core.exception.feedback;

import cn.fuck.engine.assistant.core.domain.Feedback;
import org.apache.hc.core5.http.HttpStatus;

/**
 * <p>Description: 405 类型错误反馈 </p>
 * <p>
 * 405	Method Not Allowed	客户端请求中的方法被禁止
 * @date : 2023/9/26 8:52
 */
public class MethodNotAllowedFeedback extends Feedback {
    public MethodNotAllowedFeedback(String value) {
        super(value, HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    public MethodNotAllowedFeedback(String value, int custom) {
        super(value, HttpStatus.SC_METHOD_NOT_ALLOWED, custom);
    }
}
