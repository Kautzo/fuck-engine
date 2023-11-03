package cn.fuck.engine.oauth2.authorization.definition;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 自定义SecurityConfig </p>
 * <p>
 * 自定义SecurityConfig，主要为了构建无参数构造函数，以解决序列化出错问题
 */
@Getter
@NoArgsConstructor
public class FuckConfigAttribute implements ConfigAttribute {

    private String attribute;

    public FuckConfigAttribute(String config) {
        Assert.hasText(config, "You must provide a configuration attribute");
        this.attribute = config;
    }

    public static FuckConfigAttribute create(String attribute) {
        Assert.notNull(attribute, "You must supply an array of attribute names");
        return new FuckConfigAttribute(attribute.trim());
    }

    public static List<ConfigAttribute> createListFromCommaDelimitedString(String access) {
        return createList(StringUtils.commaDelimitedListToStringArray(access));
    }

    public static List<ConfigAttribute> createList(String... attributeNames) {
        Assert.notNull(attributeNames, "You must supply an array of attribute names");
        List<ConfigAttribute> attributes = new ArrayList<>(attributeNames.length);
        for (String attribute : attributeNames) {
            attributes.add(new FuckConfigAttribute(attribute.trim()));
        }
        return attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FuckConfigAttribute that = (FuckConfigAttribute) o;
        return Objects.equal(attribute, that.attribute);
    }

}
