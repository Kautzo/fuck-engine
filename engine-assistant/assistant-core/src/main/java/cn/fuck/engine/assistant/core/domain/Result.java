package cn.fuck.engine.assistant.core.domain;


import cn.fuck.engine.assistant.core.definition.constants.DefaultConstants;
import cn.fuck.engine.assistant.core.definition.constants.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 统一响应实体 </p>
 */
@Data
@Accessors(chain = true)
@Schema(title = "统一响应返回实体",
        description = "所有Rest接口统一返回的实体定义",
        example = "new Result<T>().ok().message(\"XXX\")")
public class Result<T> implements Serializable {

    @Schema(title = "响应时间戳", pattern = DefaultConstants.DATE_TIME_FORMAT)
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
    private final Date timestamp = new Date();
    @Schema(title = "校验错误信息")
    private final Error error = new Error();
    @Schema(title = "自定义响应编码")
    private int code = 0;
    @Schema(title = "响应返回信息")
    private String message;
    @Schema(title = "请求路径")
    private String path;
    @Schema(title = "响应返回数据")
    private T data;
    @Schema(title = "http状态码")
    private int status;
    @Schema(title = "链路追踪TraceId")
    private String traceId;

    private static <T> Result<T> create(String message, String detail, int code, int status, T data, StackTraceElement[] stackTrace) {
        Result<T> result = new Result<>();
        if (StringUtils.isNotBlank(message)) {
            result.setMessage(message);
        }

        if (StringUtils.isNotBlank(detail)) {
            result.detail(detail);
        }

        result.setCode(code);
        result.setStatus(status);

        if (ObjectUtils.isNotEmpty(data)) {
            result.setData(data);
        }

        if (ArrayUtils.isNotEmpty(stackTrace)) {
            result.stackTrace(stackTrace);
        }

        return result;
    }

    public static <T> Result<T> success(String message, int code, int status, T data) {
        return create(message, null, code, status, data, null);
    }

    public static <T> Result<T> success(String message, int code, T data) {
        return success(message, code, HttpStatus.SC_OK, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return success(message, ErrorCodes.OK.getSequence(), data);
    }

    public static <T> Result<T> success(String message) {
        return success(message, null);
    }

    public static <T> Result<T> success() {
        return success("操作成功！");
    }

    public static <T> Result<T> content(T data) {
        return success("操作成功！", data);
    }

    public static <T> Result<T> failure(String message, String detail, int code, int status, T data, StackTraceElement[] stackTrace) {
        return create(message, detail, code, status, data, stackTrace);
    }

    public static <T> Result<T> failure(String message, String detail, int code, int status, T data) {
        return failure(message, detail, code, status, data, null);
    }

    public static <T> Result<T> failure(String message, int code, int status, T data) {
        return failure(message, message, code, status, data);
    }

    public static <T> Result<T> failure(String message, String detail, int code, T data) {
        return failure(message, detail, code, HttpStatus.SC_INTERNAL_SERVER_ERROR, data);
    }

    public static <T> Result<T> failure(String message, int code, T data) {
        return failure(message, message, code, data);
    }

    public static <T> Result<T> failure(Feedback feedback) {
        return failure(feedback, null);
    }

    public static <T> Result<T> failure(Feedback feedback, T data) {
        int code = ErrorCodeMapper.get(feedback);
        return failure(feedback.getMessage(), code, feedback.getStatus(), data);
    }

    public static <T> Result<T> failure(String message, T data) {
        return failure(message, ErrorCodes.INTERNAL_SERVER_ERROR.getSequence(), data);
    }

    public static <T> Result<T> failure(String message) {
        return failure(message, null);
    }

    public static <T> Result<T> failure() {
        return failure("操作失败！");
    }

    public static <T> Result<T> empty(String message, int code, int status) {
        return create(message, null, code, status, null, null);
    }

    public static <T> Result<T> empty(String message, int code) {
        return empty(message, code, ErrorCodes.NO_CONTENT.getStatus());
    }

    public static <T> Result<T> empty(Feedback feedback) {
        int code = ErrorCodeMapper.get(feedback);
        return empty(feedback.getMessage(), code, feedback.getStatus());
    }

    public static <T> Result<T> empty(String message) {
        return empty(message, ErrorCodes.NO_CONTENT.getSequence());
    }

    public static <T> Result<T> empty() {
        return empty("未查询到相关内容！");
    }

    public Result<T> type(Feedback feedback) {
        this.code = ErrorCodeMapper.get(feedback);
        ;
        this.message = feedback.getMessage();
        this.status = feedback.getStatus();
        return this;
    }

    public Result<T> stackTrace(StackTraceElement[] stackTrace) {
        this.error.setStackTrace(stackTrace);
        return this;
    }

    public Result<T> detail(String detail) {
        this.error.setDetail(detail);
        return this;
    }

    public Result<T> validation(String message, String code, String field) {
        this.error.setMessage(message);
        this.error.setCode(code);
        this.error.setField(field);
        return this;
    }

    public Map<String, Object> toModel() {
        Map<String, Object> result = new HashMap<>(8);
        result.put("code", code);
        result.put("message", message);
        result.put("path", path);
        result.put("data", data);
        result.put("status", status);
        result.put("timestamp", timestamp);
        result.put("error", error);
        return result;
    }
}
