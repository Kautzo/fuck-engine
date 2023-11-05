package cn.fuck.engine.assistant.ip2region.searcher.test;

import cn.fuck.engine.assistant.ip2region.configuration.Ip2RegionConfiguration;
import cn.fuck.engine.assistant.ip2region.definition.Ip2RegionSearcher;
import cn.fuck.engine.assistant.ip2region.domain.IpLocation;
import cn.fuck.engine.assistant.ip2region.properties.Ip2RegionProperties;
import cn.fuck.engine.assistant.ip2region.searcher.DefaultIp2RegionSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: Ip2Region 测试 </p>
 */
class DefaultIp2RegionSearcherTest {

    private static final String ipV4Resource = "classpath:db/ip2region.xdb";
    private static final  String ipV6Resource = "classpath:db/ipv6wry.db";

    private Ip2RegionSearcher searcher;

    @BeforeEach
    public void setup() throws Exception {
        Ip2RegionConfiguration configuration = new Ip2RegionConfiguration();
        searcher = configuration.defaultIp2RegionSearcher(new Ip2RegionProperties());
        ((DefaultIp2RegionSearcher) searcher).afterPropertiesSet();
    }

    @Test
    void testIpV4Region() throws Exception {
        Assertions.assertEquals(searcher.memorySearch("220.248.12.158").getLocation(), "中国上海市上海");
        Assertions.assertEquals(searcher.memorySearch("222.240.36.135").getLocation(), "中国长沙市湖南省");
        Assertions.assertEquals(searcher.memorySearch("172.30.13.97").getLocation(), "内网IP");
        Assertions.assertEquals(searcher.memorySearch("223.26.64.0").getLocation(), "中国台北台湾省");
        Assertions.assertEquals(searcher.memorySearch("223.26.128.0").getLocation(), "韩国首尔");
        Assertions.assertEquals(searcher.memorySearch("223.26.67.0").getLocation(), "中国台湾省");
        Assertions.assertEquals(searcher.memorySearch("223.29.220.0").getLocation(), "印度北方邦");
        Assertions.assertEquals(searcher.memorySearch("82.120.124.0").getLocation(), "法国Loire");
    }

    @Test
    void testIpV6Region() throws Exception {
        Assertions.assertNotNull(searcher.memorySearch("::ffff:1111:2222"));
        Assertions.assertNotNull(searcher.memorySearch("2001:db8::ffff:1111:2222"));
        Assertions.assertNotNull(searcher.memorySearch("::1"));
        Assertions.assertNotNull(searcher.memorySearch("2406:840:20::1"));
        Assertions.assertNotNull(searcher.memorySearch("2c0f:feb0:a::"));
        Assertions.assertNotNull(searcher.memorySearch("240e:109:8047::"));
        Assertions.assertNotNull(searcher.memorySearch("1111:1111:1111::1111"));
    }

    @Test
    void test2() {
        IpLocation location = searcher.memorySearch("127.0.0.1");
        Assertions.assertNotNull(location);
    }
}
