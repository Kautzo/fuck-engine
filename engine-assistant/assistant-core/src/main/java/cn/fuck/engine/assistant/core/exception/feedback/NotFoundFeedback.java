package cn.fuck.engine.assistant.core.exception.feedback;

import cn.fuck.engine.assistant.core.domain.Feedback;
import org.apache.hc.core5.http.HttpStatus;

/**
 * <p>Description: 406 类型错误反馈 </p>
 * <p>
 * 404	Not Found	服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设置"您所请求的资源无法找到"的个性页面
 * @date : 2023/9/27 20:07
 */
public class NotFoundFeedback extends Feedback {

    public NotFoundFeedback(String message) {
        super(message, HttpStatus.SC_NOT_FOUND);
    }

    public NotFoundFeedback(String message, int custom) {
        super(message, HttpStatus.SC_NOT_FOUND, custom);
    }
}
