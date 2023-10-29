package cn.fuck.engine.assistant.core.exception.feedback;

import cn.fuck.engine.assistant.core.domain.Feedback;
import org.apache.hc.core5.http.HttpStatus;

/**
 * <p>Description: 501 类型错误反馈 </p>
 * <p>
 * 501	Not Implemented	服务器不支持请求的功能，无法完成请求
 * @date : 2023/9/26 8:54
 */
public class NotImplementedFeedback extends Feedback {
    public NotImplementedFeedback(String value) {
        super(value, HttpStatus.SC_NOT_IMPLEMENTED);
    }

    public NotImplementedFeedback(String value, int custom) {
        super(value, HttpStatus.SC_NOT_IMPLEMENTED, custom);
    }
}
