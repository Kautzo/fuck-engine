package cn.fuck.engine.assistant.core.exception.feedback;

import cn.fuck.engine.assistant.core.domain.Feedback;
import org.apache.hc.core5.http.HttpStatus;

/**
 * <p>Description: 406 类型错误反馈 </p>
 * <p>
 * 406	Not Acceptable	服务器无法根据客户端请求的内容特性完成请求
 * @date : 2023/9/26 8:52
 */
public class NotAcceptableFeedback extends Feedback {
    public NotAcceptableFeedback(String value) {
        super(value, HttpStatus.SC_NOT_ACCEPTABLE);
    }

    public NotAcceptableFeedback(String value, int custom) {
        super(value, HttpStatus.SC_NOT_ACCEPTABLE, custom);
    }
}
