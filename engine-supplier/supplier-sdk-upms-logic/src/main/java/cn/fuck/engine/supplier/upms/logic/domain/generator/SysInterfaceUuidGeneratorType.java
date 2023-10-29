package cn.fuck.engine.supplier.upms.logic.domain.generator;

import cn.fuck.engine.supplier.upms.logic.entity.security.SysInterface;
import cn.fuck.engine.data.core.identifier.AbstractUuidGenerator;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

import java.lang.reflect.Member;

/**
 * <p>Description: 自定义UUID生成器，使得保存实体类时可以在保留主键生成策略的情况下自定义表的主键 </p>
 * @date : 2023/3/7 11:03
 */
public class SysInterfaceUuidGeneratorType extends AbstractUuidGenerator {

    public SysInterfaceUuidGeneratorType(SysInterfaceUuidGenerator config, Member idMember, CustomIdGeneratorCreationContext creationContext) {
        super(idMember);
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (ObjectUtils.isEmpty(object)) {
            throw new HibernateException(new NullPointerException());
        }

        SysInterface sysInterface = (SysInterface) object;

        if (StringUtils.isEmpty(sysInterface.getInterfaceId())) {
            return super.generate(session, object);
        } else {
            return sysInterface.getInterfaceId();
        }
    }
}
