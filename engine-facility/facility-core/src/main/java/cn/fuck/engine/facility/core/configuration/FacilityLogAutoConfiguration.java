package cn.fuck.engine.facility.core.configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import cn.fuck.engine.assistant.core.json.jackson2.utils.Jackson2Utils;
import cn.fuck.engine.facility.core.constants.FacilityConstants;
import cn.fuck.engine.facility.core.annotation.ConditionalOnLogEnabled;
import cn.fuck.engine.facility.core.properties.LogProperties;
import com.google.common.base.MoreObjects;
import jakarta.annotation.PostConstruct;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import org.apache.skywalking.apm.toolkit.log.logback.v1.x.logstash.TraceIdJsonProvider;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.logging.LogLevel;

import java.util.Map;

/**
 * <p>Description: 基础设置日志配置 </p>
 * @date : 2022/2/5 19:01
 */
@AutoConfiguration
@ConditionalOnLogEnabled
@EnableConfigurationProperties({LogProperties.class})
public class FacilityLogAutoConfiguration {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FacilityLogAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Facility Log] Auto Configure.");
    }


    @Value(FacilityConstants.ANNOTATION_APPLICATION_NAME)
    private String serviceName;

    @Autowired
    private LogProperties logProperties;

    @PostConstruct
    public void init() {

        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        LoggerContext loggerContext = rootLogger.getLoggerContext();

        LogstashTcpSocketAppender logstashTcpSocketAppender = new LogstashTcpSocketAppender();
        logstashTcpSocketAppender.setName("LOGSTASH");
        logstashTcpSocketAppender.addDestination(logProperties.getServerAddr());
        logstashTcpSocketAppender.setKeepAliveDuration(logProperties.getKeepAliveDuration());
        logstashTcpSocketAppender.setReconnectionDelay(logProperties.getReconnectionDelay());
        logstashTcpSocketAppender.setWriteTimeout(logProperties.getWriteTimeout());
        logstashTcpSocketAppender.setContext(loggerContext);

        TraceIdJsonProvider traceIdJsonProvider = new TraceIdJsonProvider();
        traceIdJsonProvider.setContext(loggerContext);

        CustomFields customFields = new CustomFields();
        customFields.setService(serviceName);

        LogstashEncoder logstashEncoder = new LogstashEncoder();
        logstashEncoder.setCustomFields(Jackson2Utils.toJson(customFields));
        logstashEncoder.addProvider(traceIdJsonProvider);

        logstashTcpSocketAppender.setEncoder(logstashEncoder);
        logstashTcpSocketAppender.start();

        rootLogger.addAppender(logstashTcpSocketAppender);
        rootLogger.setLevel(Level.toLevel(logProperties.getLogLevel().name(), Level.INFO));

        Map<String, LogLevel> loggers = logProperties.getLoggers();
        loggers.forEach((key, value) -> {
            Logger logger = (Logger) LoggerFactory.getLogger(key);
            logger.setLevel(Level.toLevel(value.name()));
        });
    }

    private static class CustomFields {

        private String service;

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("service", service)
                    .toString();
        }
    }
}
