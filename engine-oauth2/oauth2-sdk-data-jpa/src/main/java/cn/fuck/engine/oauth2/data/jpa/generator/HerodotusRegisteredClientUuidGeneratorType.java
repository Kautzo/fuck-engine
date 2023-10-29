package cn.fuck.engine.oauth2.data.jpa.generator;

import cn.fuck.engine.data.core.identifier.AbstractUuidGenerator;
import cn.fuck.engine.oauth2.data.jpa.entity.HerodotusRegisteredClient;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

import java.lang.reflect.Member;

/**
 * <p>Description: OAuth2RegisteredClient Id 生成器 </p>
 * <p>
 * 指定ID生成器，解决实体ID无法手动设置问题。
 * @date : 2022/1/22 17:50
 */
public class HerodotusRegisteredClientUuidGeneratorType extends AbstractUuidGenerator {

    public HerodotusRegisteredClientUuidGeneratorType(HerodotusRegisteredClientUuidGenerator config, Member idMember, CustomIdGeneratorCreationContext creationContext) {
        super(idMember);
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (ObjectUtils.isEmpty(object)) {
            throw new HibernateException(new NullPointerException());
        }

        HerodotusRegisteredClient herodotusRegisteredClient = (HerodotusRegisteredClient) object;

        if (StringUtils.isEmpty(herodotusRegisteredClient.getId())) {
            return super.generate(session, object);
        } else {
            return herodotusRegisteredClient.getId();
        }
    }
}