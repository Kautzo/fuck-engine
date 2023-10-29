package cn.fuck.engine.assistant.core.exception.feedback;

import cn.fuck.engine.assistant.core.domain.Feedback;
import org.apache.hc.core5.http.HttpStatus;

/**
 * <p>Description: 415 类型错误反馈 </p>
 * <p>
 * 415	Unsupported Media Type	服务器无法处理请求附带的媒体格式
 * @date : 2023/9/26 8:53
 */
public class UnsupportedMediaTypeFeedback extends Feedback {
    public UnsupportedMediaTypeFeedback(String value) {
        super(value, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    public UnsupportedMediaTypeFeedback(String value, int custom) {
        super(value, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, custom);
    }
}
