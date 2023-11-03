package cn.fuck.engine.oauth2.authentication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <p>Description: 登录提示信息 </p>
 * @date : 2022/7/8 20:52
 */
@Data
@Schema(title = "登录错误提示信息")
public class SignInErrorPrompt {

    @NotBlank(message = "登录用户名不能为空")
    @Schema(name = "登录用户名", title = "必须是有效的用户名")
    private String username;

}
