package cn.fuck.engine.data.mybatis.plus.configuration;

import cn.fuck.engine.data.mybatis.plus.enhance.FuckIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: Mybatis Plus 配置</p>
 */
@Slf4j
@MapperScan({"cn.fuck.**.mappper*"})
@Configuration(proxyBeanMethods = false)
public class MybatisPlusConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Data Mybatis Plus] Auto Configure.");
    }


    /**
     * 防止 修改与删除时对全表进行操作
     *
     * @return {@link MybatisPlusInterceptor}
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        log.trace("[FUCK] |- Bean [Mybatis Plus Interceptor] Auto Configure.");
        return mybatisPlusInterceptor;
    }

//    @Bean
//    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
//        BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
//        log.trace("[FUCK] |- Bean [Block Attack Inner Interceptor] Auto Configure.");
//        return blockAttackInnerInterceptor;
//    }

    @Bean
    public IdentifierGenerator identifierGenerator() {
        FuckIdentifierGenerator fuckIdentifierGenerator = new FuckIdentifierGenerator();
        log.trace("[FUCK] |- Bean [FUCK Identifier Generator] Auto Configure.");
        return fuckIdentifierGenerator;
    }
}
