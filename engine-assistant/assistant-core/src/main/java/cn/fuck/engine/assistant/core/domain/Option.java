package cn.fuck.engine.assistant.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Description: 前端选项基础格式 </p>
 */
@Data
public class Option implements Serializable {

    private String label;

    private String value;

}
