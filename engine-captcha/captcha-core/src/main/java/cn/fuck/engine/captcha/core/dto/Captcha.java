package cn.fuck.engine.captcha.core.dto;

import cn.fuck.engine.assistant.core.definition.domain.AbstractDTO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 验证码返回数据基础类 </p>
 */
public abstract class Captcha extends AbstractDTO {

    @Schema(title = "验证码身份")
    private String identity;

    @Schema(title = "验证码类别")
    private String category;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
