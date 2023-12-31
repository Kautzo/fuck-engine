package cn.fuck.engine.sms.core.domain;

import java.util.Map;

/**
 * <p>Description: 通知内容 </p>
 * @date : 2021/5/26 16:14
 */
public class Template {

    /**
     * 类型
     */
    private String type;

    /**
     * 参数列表
     */
    private Map<String, String> params;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
