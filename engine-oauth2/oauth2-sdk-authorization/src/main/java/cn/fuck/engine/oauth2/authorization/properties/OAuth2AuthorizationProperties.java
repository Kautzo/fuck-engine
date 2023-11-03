package cn.fuck.engine.oauth2.authorization.properties;

import cn.fuck.engine.assistant.core.enums.Target;
import cn.fuck.engine.oauth2.core.constants.OAuth2Constants;
import cn.fuck.engine.oauth2.core.enums.Certificate;
import com.google.common.base.MoreObjects;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <p>Description: OAuth2 配置属性 </p>
 * <p>
 * 仅认证服务会使用到的安全相关配置，这是与 OAuth2Properties 的主要区别。
 */
@Data
@ConfigurationProperties(prefix = OAuth2Constants.PROPERTY_OAUTH2_AUTHORIZATION)
public class OAuth2AuthorizationProperties {

    /**
     * Token 校验是采用远程方式还是本地方式。
     */
    private Target validate = Target.REMOTE;

    /**
     * 是否使用严格模式。严格模式一定要求有权限，非严格模式没有权限管控的接口，只要认证通过也可以使用。
     */
    private Boolean strict = true;

    /**
     * JWT的密钥或者密钥对(JSON Web Key) 配置
     */
    private Jwk jwk = new Jwk();
    /**
     * 指定 Request Matcher 静态安全规则
     */
    private Matcher matcher = new Matcher();

    @Data
    public static class Jwk {

        /**
         * 证书策略：standard OAuth2 标准证书模式；custom 自定义证书模式
         */
        private Certificate certificate = Certificate.CUSTOM;
        /**
         * jks证书文件路径
         */
        private String jksKeyStore = "classpath*:certificate/fuck-cloud.jks";
        /**
         * jks证书密码
         */
        private String jksKeyPassword = "Fuck-Cloud";
        /**
         * jks证书密钥库密码
         */
        private String jksStorePassword = "Fuck-Cloud";
        /**
         * jks证书别名
         */
        private String jksKeyAlias = "fuck-cloud";


        private enum Strategy {
            STANDARD, CUSTOM
        }
    }

    /**
     * 用于手动的指定 Request Matcher 安全规则。
     * <p>
     * permitAll 比较常用，因此先只增加该项。后续可根据需要添加
     */
    @Data
    public static class Matcher {
        /**
         * 静态资源过滤
         */
        private List<String> staticResources;
        /**
         * Security "permitAll" 权限列表。
         */
        private List<String> permitAll;
        /**
         * 只校验是否请求中包含Token，不校验Token中是否包含该权限的资源
         */
        private List<String> hasAuthenticated;

    }
}
