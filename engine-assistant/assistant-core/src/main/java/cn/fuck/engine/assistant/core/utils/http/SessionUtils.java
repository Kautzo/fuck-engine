package cn.fuck.engine.assistant.core.utils.http;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServerHttpRequest;

/**
 * <p>Description: Session 操作工具类 </p>
 * @date : 2023/9/2 15:30
 */
public class SessionUtils {

    private static final String[] SESSION_IDS = new String[]{"JSESSIONID, SESSION"};

    /**
     * 将 getSession 统一封装为一个方法，方便统一修改
     *
     * @param httpServletRequest httpServletRequest {@link HttpServletRequest}
     * @param create             是否创建新的 Session
     * @return {@link HttpSession}
     */
    public static HttpSession getSession(HttpServletRequest httpServletRequest, boolean create) {
        return httpServletRequest.getSession(create);
    }

    /**
     * 将 getSession 统一封装为一个方法，方便统一修改
     * <p>
     * 该方法默认不创建新的 getSession
     *
     * @param httpServletRequest {@link HttpServletRequest}
     * @return {@link HttpSession} or null
     */
    public static HttpSession getSession(HttpServletRequest httpServletRequest) {
        return getSession(httpServletRequest, false);
    }

    /**
     * 获取 Session Id。
     *
     * @param httpServletRequest {@link HttpServletRequest}
     * @param create             create 是否创建新的 Session
     * @return id 或者 null
     */
    public static String getSessionId(HttpServletRequest httpServletRequest, boolean create) {
        HttpSession httpSession = getSession(httpServletRequest, create);
        return ObjectUtils.isNotEmpty(httpSession) ? httpSession.getId() : null;
    }

    /**
     * 获取 Session ID。
     *
     * @param httpServletRequest {@link HttpServletRequest}
     * @return session ID 或者 null
     */
    public static String getSessionId(HttpServletRequest httpServletRequest) {
        return getSessionId(httpServletRequest, false);
    }

    /**
     * 获取 Session ID
     *
     * @param httpInputMessage {@link HttpInputMessage}
     * @return session ID 或者 null
     */
    public static String getSessionIdFromHeader(HttpInputMessage httpInputMessage) {
        return CookieUtils.getAnyFromHeader(httpInputMessage, SESSION_IDS);
    }

    /**
     * 获取 Session ID
     *
     * @param serverHttpRequest {@link ServerHttpRequest}
     * @return session ID 或者 null
     */
    public static String getSessionIdFromHeader(ServerHttpRequest serverHttpRequest) {
        return CookieUtils.getAnyFromHeader(serverHttpRequest, SESSION_IDS);
    }

    /**
     * 解析 Session ID
     *
     * @param httpServletRequest {@link HttpServletRequest}
     * @return session ID 或者 null
     */
    public static String analyseSessionId(HttpServletRequest httpServletRequest) {
        String sessionId = getSessionId(httpServletRequest);
        if (StringUtils.isBlank(sessionId)) {
            sessionId = HeaderUtils.getHerodotusSession(httpServletRequest);
        }
        return sessionId;
    }

    /**
     * 解析 Session ID
     *
     * @param serverHttpRequest {@link ServerHttpRequest}
     * @return session ID 或者 null
     */
    public static String analyseSessionId(ServerHttpRequest serverHttpRequest) {
        String sessionId = getSessionIdFromHeader(serverHttpRequest);
        if (StringUtils.isBlank(sessionId)) {
            sessionId = HeaderUtils.getHerodotusSession(serverHttpRequest);
        }
        return sessionId;
    }

    /**
     * 解析 Session ID
     *
     * @param httpInputMessage {@link HttpInputMessage}
     * @return session ID 或者 null
     */
    public static String analyseSessionId(HttpInputMessage httpInputMessage) {
        String sessionId = getSessionIdFromHeader(httpInputMessage);
        if (StringUtils.isBlank(sessionId)) {
            sessionId = HeaderUtils.getHerodotusSession(httpInputMessage);
        }
        return sessionId;
    }
}
