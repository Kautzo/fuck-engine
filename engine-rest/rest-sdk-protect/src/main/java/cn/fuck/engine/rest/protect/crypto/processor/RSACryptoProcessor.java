package cn.fuck.engine.rest.protect.crypto.processor;

import cn.fuck.engine.assistant.core.definition.constants.SymbolConstants;
import cn.fuck.engine.assistant.core.domain.SecretKey;
import cn.fuck.engine.rest.core.definition.crypto.AsymmetricCryptoProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.codec.binary.Base64;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.crypto.SecureUtil;
import org.dromara.hutool.crypto.asymmetric.KeyType;
import org.dromara.hutool.crypto.asymmetric.RSA;

/**
 * <p>Description: RSA 加密算法处理器 </p>
 */
@Slf4j
public class RSACryptoProcessor implements AsymmetricCryptoProcessor {

    private static final String PKCS8_PUBLIC_KEY_BEGIN = "-----BEGIN PUBLIC KEY-----";
    private static final String PKCS8_PUBLIC_KEY_END = "-----END PUBLIC KEY-----";

    @Override
    public SecretKey createSecretKey() {
        RSA rsa = SecureUtil.rsa();
        SecretKey secretKey = new SecretKey();
        secretKey.setPrivateKey(rsa.getPrivateKeyBase64());
        secretKey.setPublicKey(appendPkcs8Padding(rsa.getPublicKeyBase64()));
        return secretKey;
    }

    /**
     * 去除 RSA Pkcs8Padding 中的标记格式 '-----BEGIN PUBLIC KEY-----' 和 '-----END PUBLIC KEY-----'，以及 '\n'
     *
     * @param key RSA Key
     * @return 清楚格式后的 RSA KEY
     */
    private String removePkcs8Padding(String key) {
        String result = StringUtils.replace(key, SymbolConstants.NEW_LINE, SymbolConstants.BLANK);
        String[] values = StringUtils.split(result, "-----");
        if (ArrayUtils.isNotEmpty(values)) {
            return values[1];
        }
        return key;
    }

    /**
     * 将 RSA PublicKey 转换为 Pkcs8Padding 格式。
     *
     * @param key RSA PublicKey
     * @return 转换为 Pkcs8Padding 格式的 RSA PublicKey
     */
    public String appendPkcs8Padding(String key) {
        return PKCS8_PUBLIC_KEY_BEGIN + SymbolConstants.NEW_LINE + key + SymbolConstants.NEW_LINE + PKCS8_PUBLIC_KEY_END;
    }

    @Override
    public String decrypt(String content, String privateKey) {
        byte[] base64Data = Base64.decode(content);
        RSA rsa = SecureUtil.rsa(privateKey, null);
        String result = StrUtil.utf8Str(rsa.decrypt(base64Data, KeyType.PrivateKey));
        log.trace("[FUCK] |- RSA crypto decrypt data, value is : [{}]", result);
        return result;
    }

    @Override
    public String encrypt(String content, String publicKey) {
        // 去除前端 RSA PublicKey中的 '-----BEGIN PUBLIC KEY-----'格式
        String key = removePkcs8Padding(publicKey);
        RSA rsa = SecureUtil.rsa(null, key);
        byte[] encryptedData = rsa.encrypt(content, KeyType.PublicKey);
        String result = Base64.encode(encryptedData);
        log.trace("[FUCK] |- RSA crypto decrypt data, value is : [{}]", result);
        return result;
    }
}
