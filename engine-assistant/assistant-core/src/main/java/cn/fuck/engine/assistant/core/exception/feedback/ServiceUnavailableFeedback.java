package cn.fuck.engine.assistant.core.exception.feedback;

import cn.fuck.engine.assistant.core.domain.Feedback;
import org.apache.hc.core5.http.HttpStatus;

/**
 * <p>Description: 503 类型错误反馈 </p>
 * <p>
 * 503	Service Unavailable	由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中
 * @date : 2023/9/26 8:54
 */
public class ServiceUnavailableFeedback extends Feedback {
    public ServiceUnavailableFeedback(String value) {
        super(value, HttpStatus.SC_SERVICE_UNAVAILABLE);
    }

    public ServiceUnavailableFeedback(String value, int custom) {
        super(value, HttpStatus.SC_SERVICE_UNAVAILABLE, custom);
    }
}
