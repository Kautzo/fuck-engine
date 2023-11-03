package cn.fuck.engine.rest.protect.configuration;

import cn.fuck.engine.rest.condition.annotation.ConditionalOnSMCrypto;
import cn.fuck.engine.rest.condition.annotation.ConditionalOnStandardCrypto;
import cn.fuck.engine.rest.protect.crypto.processor.AESCryptoProcessor;
import cn.fuck.engine.rest.protect.crypto.processor.RSACryptoProcessor;
import cn.fuck.engine.rest.protect.crypto.processor.SM4CryptoProcessor;
import cn.fuck.engine.rest.core.definition.crypto.AsymmetricCryptoProcessor;
import cn.fuck.engine.rest.core.definition.crypto.SymmetricCryptoProcessor;
import cn.fuck.engine.rest.protect.crypto.processor.SM2CryptoProcessor;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 非对称算法配置 </p>
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class CryptoStrategyConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Protect Crypto Strategy] Auto Configure.");
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnSMCrypto
    static class SMCryptoConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public AsymmetricCryptoProcessor sm2CryptoProcessor() {
            SM2CryptoProcessor sm2CryptoProcessor = new SM2CryptoProcessor();
            log.trace("[FUCK] |- Strategy [SM Asymmetric SM2 Crypto Processor] Auto Configure.");
            return sm2CryptoProcessor;
        }

        @Bean
        @ConditionalOnMissingBean
        public SymmetricCryptoProcessor sm4CryptoProcessor() {
            SM4CryptoProcessor sm4CryptoProcessor = new SM4CryptoProcessor();
            log.trace("[FUCK] |- Strategy [SM Symmetric SM4 Crypto Processor] Auto Configure.");
            return sm4CryptoProcessor;
        }
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnStandardCrypto
    static class StandardCryptoConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public AsymmetricCryptoProcessor rsaCryptoProcessor() {
            RSACryptoProcessor rsaCryptoProcessor = new RSACryptoProcessor();
            log.trace("[FUCK] |- Strategy [Standard Asymmetric RSA Crypto Processor] Auto Configure.");
            return rsaCryptoProcessor;
        }

        @Bean
        @ConditionalOnMissingBean
        public SymmetricCryptoProcessor aesCryptoProcessor() {
            AESCryptoProcessor aesCryptoProcessor = new AESCryptoProcessor();
            log.trace("[FUCK] |- Strategy [Standard Symmetric AES Crypto Processor] Auto Configure.");
            return aesCryptoProcessor;
        }
    }
}
