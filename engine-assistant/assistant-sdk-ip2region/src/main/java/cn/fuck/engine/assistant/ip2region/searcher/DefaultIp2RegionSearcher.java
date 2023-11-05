package cn.fuck.engine.assistant.ip2region.searcher;

import cn.fuck.engine.assistant.core.utils.ResourceUtils;
import cn.fuck.engine.assistant.ip2region.definition.Ip2RegionSearcher;
import cn.fuck.engine.assistant.ip2region.exception.SearchIpLocationException;
import cn.fuck.engine.assistant.core.definition.constants.SymbolConstants;
import cn.fuck.engine.assistant.ip2region.domain.IpLocation;
import cn.fuck.engine.assistant.ip2region.utils.IpLocationUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * <p>Description: 默认的Ip2Region查询实现 </p>
 */
public class DefaultIp2RegionSearcher implements Ip2RegionSearcher, InitializingBean, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(DefaultIp2RegionSearcher.class);

    private IpV4Searcher ipV4Searcher;
    private IpV6Searcher ipV6Searcher;
    private final String ipV4Resource;
    private final String ipV6Resource;

    public DefaultIp2RegionSearcher(String ipV4Resource, String ipV6Resource) {
        this.ipV4Resource = ipV4Resource;
        this.ipV6Resource = ipV6Resource;
    }

    private byte[] toBytes(String location) throws IllegalAccessException {
        Resource resource = ResourceUtils.getResource(location);
        if (ObjectUtils.isNotEmpty(resource)) {
            log.debug("[FUCK] |- Load ip region database [{}]", resource.getFilename());
            return ResourceUtils.toBytes(resource);
        } else {
            log.error("[FUCK] |- Cannot read ip region database in resources folder!");
            throw new IllegalAccessException("Cannot read ip region database in resources folder");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] ipV4Database = toBytes(this.ipV4Resource);
        if (ObjectUtils.isNotEmpty(ipV4Database)) {
            this.ipV4Searcher = IpV4Searcher.newWithBuffer(ipV4Database);
        }

        byte[] ipV6Database = toBytes(this.ipV6Resource);
        if (ObjectUtils.isNotEmpty(ipV6Database)) {
            this.ipV6Searcher = new IpV6Searcher(ipV6Database);
        }
    }

    @Override
    public IpLocation memorySearch(long ip) {
        try {
            return IpLocationUtils.toIpV4Location(ipV4Searcher.search(ip));
        } catch (IOException e) {
            log.error("[FUCK] |- Search ip v4 location catch io exception!", e);
            throw new SearchIpLocationException(e);
        }
    }

    @Override
    public IpLocation memorySearch(String ip) {
        // 1. ipv4
        String[] ipV4Part = IpLocationUtils.getIpV4Part(ip);
        if (ipV4Part.length == 4) {
            return memorySearch(IpV4Searcher.getIpAdder(ipV4Part));
        }
        // 2. 非 ipv6
        if (!ip.contains(SymbolConstants.COLON)) {
            throw new IllegalArgumentException("invalid ip location [" + ip + "]");
        }
        // 3. ipv6
        return ipV6Searcher.query(ip);
    }

    @Override
    public void destroy() throws Exception {
        if (ObjectUtils.isNotEmpty(ipV4Searcher)) {
            this.ipV4Searcher.close();
        }
    }
}
