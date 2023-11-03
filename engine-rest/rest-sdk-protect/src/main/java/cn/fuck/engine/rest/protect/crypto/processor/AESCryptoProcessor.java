package cn.fuck.engine.rest.protect.crypto.processor;

import cn.fuck.engine.rest.core.definition.crypto.SymmetricCryptoProcessor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.codec.binary.Base64;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.core.util.ByteUtil;
import org.dromara.hutool.core.util.RandomUtil;
import org.dromara.hutool.crypto.SecureUtil;
import org.dromara.hutool.crypto.symmetric.AES;

/**
 * <p>Description: AES 加密算法处理器 </p>
 */
@Slf4j
public class AESCryptoProcessor implements SymmetricCryptoProcessor {

    @Override
    public String createKey() {
        return RandomUtil.randomStringUpper(16);
    }

    @Override
    public String decrypt(String data, String key) {
        AES aes = SecureUtil.aes(ByteUtil.toUtf8Bytes(key));
        byte[] result = aes.decrypt(Base64.decode(ByteUtil.toUtf8Bytes(data)));
        log.trace("[FUCK] |- AES crypto decrypt data, value is : [{}]", result);
        return StrUtil.utf8Str(result);
    }

    @Override
    public String encrypt(String data, String key) {
        AES aes = SecureUtil.aes(ByteUtil.toUtf8Bytes(key));
        byte[] result = aes.encrypt(ByteUtil.toUtf8Bytes(data));
        log.trace("[FUCK] |- AES crypto encrypt data, value is : [{}]", result);
        return StrUtil.utf8Str(result);
    }
}
