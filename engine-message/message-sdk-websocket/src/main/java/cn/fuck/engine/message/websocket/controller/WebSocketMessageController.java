package cn.fuck.engine.message.websocket.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.message.websocket.definition.WebSocketMessageSender;
import cn.fuck.engine.message.websocket.utils.WebSocketUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: WebSocket 消息接口 </p>
 */
@RestController
@RequestMapping("/message/websocket")
@Tags({
        @Tag(name = "消息接口"),
        @Tag(name = "WebSocket消息接口")
})
public class WebSocketMessageController {

    private final WebSocketMessageSender webSocketMessageSender;

    public WebSocketMessageController(WebSocketMessageSender webSocketMessageSender) {
        this.webSocketMessageSender = webSocketMessageSender;
    }

    @Operation(summary = "后端发送通知", description = "后端发送 WebSocket 广播通知接口",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
            responses = {@ApiResponse(description = "是否成功", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "message", required = true, description = "消息实体")
    })
    @PostMapping("/send/notice")
    public Result<String> sendNotice(@RequestBody String message) {

        if (StringUtils.isNotBlank(message)) {
            webSocketMessageSender.sendNoticeToAll(message);
        }

        return Result.success(message);
    }

    @Operation(summary = "获取统计信息", description = "获取WebSocket相关的统计信息")
    @GetMapping(value = "/stat")
    public Result<Map<String, Object>> findAllStat() {
        Map<String, Object> stat = new HashMap<>();
        stat.put("onlineCount", WebSocketUtils.getOnlineCount());
        return Result.success("获取统计信息成功", stat);
    }
}
