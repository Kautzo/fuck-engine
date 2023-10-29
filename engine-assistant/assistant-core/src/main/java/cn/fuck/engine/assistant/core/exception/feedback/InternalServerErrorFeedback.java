package cn.fuck.engine.assistant.core.exception.feedback;

import cn.fuck.engine.assistant.core.domain.Feedback;
import org.apache.hc.core5.http.HttpStatus;

/**
 * <p>Description: 500 类型错误反馈 </p>
 * <p>
 * 500	Internal Server Error	服务器内部错误，无法完成请求
 * @date : 2023/9/26 8:54
 */
public class InternalServerErrorFeedback extends Feedback {
    public InternalServerErrorFeedback(String value) {
        super(value, HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorFeedback(String value, int custom) {
        super(value, HttpStatus.SC_INTERNAL_SERVER_ERROR, custom);
    }
}
