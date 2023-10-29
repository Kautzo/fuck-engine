package cn.fuck.engine.assistant.core.exception.feedback;

import cn.fuck.engine.assistant.core.domain.Feedback;
import org.apache.hc.core5.http.HttpStatus;

/**
 * <p>Description: 200 类型错误反馈 </p>
 * <p>
 * 200	OK	请求成功。一般用于GET与POST请求
 * @date : 2023/9/26 10:07
 */
public class OkFeedback extends Feedback {
    public OkFeedback(String value) {
        super(value, HttpStatus.SC_OK);
    }

    public OkFeedback(String value, int custom) {
        super(value, HttpStatus.SC_OK, custom);
    }
}
