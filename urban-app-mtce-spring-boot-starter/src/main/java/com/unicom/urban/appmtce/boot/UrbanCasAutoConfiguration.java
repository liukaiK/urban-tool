package com.unicom.urban.appmtce.boot;

import com.unicom.urban.appmtce.common.LoginRequestInterceptor;
import com.unicom.urban.appmtce.properties.UrbanAppMtceProperties;
import com.unicom.urban.appmtce.role.RoleFeignClient;
import com.unicom.urban.appmtce.user.UserFeignClient;
import com.unicom.urban.common.constant.SysConstants;
import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 应用维护相关的配置
 *
 * @author liukai
 */
@Configuration
@ComponentScan(basePackages = SysConstants.BASE_PACKAGE)
@EnableConfigurationProperties(UrbanAppMtceProperties.class)
@ConditionalOnProperty(name = "urban.appMtce", matchIfMissing = true)
public class UrbanCasAutoConfiguration {

    @Autowired
    private UrbanAppMtceProperties urbanAppMtceProperties;

    @Bean
    public UserFeignClient userFeignClient() {
        return Feign.builder()
                .requestInterceptor(requestInterceptor())
                .target(UserFeignClient.class, urbanAppMtceProperties.getHost());
    }

    @Bean
    public RoleFeignClient roleFeignClient() {
        return Feign.builder()
                .requestInterceptor(requestInterceptor())
                .target(RoleFeignClient.class, urbanAppMtceProperties.getHost());
    }

    public RequestInterceptor requestInterceptor() {
        return new LoginRequestInterceptor(urbanAppMtceProperties.getHost(), urbanAppMtceProperties.getAppKey(), urbanAppMtceProperties.getAppSecret());
    }

}
