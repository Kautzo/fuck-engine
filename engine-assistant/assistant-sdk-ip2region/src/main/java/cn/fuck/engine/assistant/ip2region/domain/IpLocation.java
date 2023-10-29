package cn.fuck.engine.assistant.ip2region.domain;

import cn.fuck.engine.assistant.core.definition.constants.SymbolConstants;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>Description: Ip 信息详情 </p>
 * @date : 2023/10/22 23:00
 */
public class IpLocation implements Serializable {

    /**
     * 国家
     */
    private String country;
    /**
     * 区域
     */
    private String region;
    /**
     * 省
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 运营商
     */
    private String isp;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getLocation() {
        return createLocation(false, SymbolConstants.BLANK);
    }

    public String getLocationWithIsp() {
        return createLocation(true, SymbolConstants.SPACE);
    }

    private String createLocation(boolean withIsp, String separator) {
        Set<String> result = new LinkedHashSet<>();
        result.add(country);
        result.add(region);
        result.add(province);
        result.add(city);
        if (withIsp) {
            result.add(isp);
        }
        result.removeIf(Objects::isNull);
        return StringUtils.join(result, separator);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("country", country)
                .add("region", region)
                .add("province", province)
                .add("city", city)
                .add("isp", isp)
                .toString();
    }
}
