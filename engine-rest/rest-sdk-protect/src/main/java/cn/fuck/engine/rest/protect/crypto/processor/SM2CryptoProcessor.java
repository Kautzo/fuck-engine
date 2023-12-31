package cn.fuck.engine.rest.protect.crypto.processor;

import cn.fuck.engine.assistant.core.domain.SecretKey;
import cn.fuck.engine.rest.core.definition.crypto.AsymmetricCryptoProcessor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.dromara.hutool.core.codec.HexUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.crypto.asymmetric.KeyType;
import org.dromara.hutool.crypto.asymmetric.SM2;
import org.dromara.hutool.crypto.bc.BCUtil;
import org.dromara.hutool.crypto.bc.SmUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: 国密 SM2 算法处理 </p>
 * <p>
 * 主要用于前后端数据加密处理，与 sm-crypto 交互
 */
@Slf4j
public class SM2CryptoProcessor implements AsymmetricCryptoProcessor {

    @Override
    public SecretKey createSecretKey() {
        // 随机生成秘钥
        SM2 sm2 = SmUtil.sm2();
        // sm2的加解密时有两种方式即 C1C2C3、 C1C3C2，
        sm2.setMode(SM2Engine.Mode.C1C3C2);
        // 生成私钥
        String privateKey = HexUtil.encodeHexStr(BCUtil.encodeECPrivateKey(sm2.getPrivateKey()));
        // 生成公钥
        String publicKey = HexUtil.encodeHexStr(((BCECPublicKey) sm2.getPublicKey()).getQ().getEncoded(false));

        SecretKey secretKey = new SecretKey();
        secretKey.setPrivateKey(privateKey);
        secretKey.setPublicKey(publicKey);
        return secretKey;
    }

    @Override
    public String decrypt(String content, String privateKey) {
        // 可用的 Hutool SM2 解密
        SM2 sm2 = SmUtil.sm2(privateKey, null);
        sm2.setMode(SM2Engine.Mode.C1C3C2);

        String result = StrUtil.utf8Str(sm2.decrypt(content, KeyType.PrivateKey));
        log.trace("[FUCK] |- SM2 crypto decrypt data, value is : [{}]", result);

        return result;
    }

    @Override
    public String encrypt(String content, String publicKey) {
        SM2 sm2 = SmUtil.sm2(null, publicKey);

        String result = sm2.encryptHex(content, KeyType.PublicKey);
        log.trace("[FUCK] |- SM2 crypto encrypt data, value is : [{}]", result);
        return result;
    }

}
