package cn.fuck.engine.oauth2.core.enums;

/**
 * <p>Description: 证书使用策略 </p>
 * @date : 2022/3/6 18:32
 */
public enum Certificate {

    /**
     * Spring Authorization Server 默认的 JWK 生成方式
     */
    STANDARD,
    /**
     * 自定义证书 JWK 生成方式
     */
    CUSTOM;
}
