package com.unicom.urban.appmtce.boot;

import com.unicom.urban.appmtce.common.LoginRequestInterceptor;
import com.unicom.urban.appmtce.menu.MenuFeignClient;
import com.unicom.urban.appmtce.properties.UrbanAppMtceProperties;
import com.unicom.urban.appmtce.role.RoleFeignClient;
import com.unicom.urban.appmtce.user.UserFeignClient;
import com.unicom.urban.common.constant.SysConstants;
import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 应用维护相关的配置
 *
 * @author liukai
 */
@Configuration
@Import({FeignClientsConfiguration.class})
@ComponentScan(basePackages = SysConstants.BASE_PACKAGE)
@EnableConfigurationProperties(UrbanAppMtceProperties.class)
@ConditionalOnProperty(name = "urban.app-mtce", matchIfMissing = true)
public class UrbanAppMtceAutoConfiguration {

    @Autowired
    private UrbanAppMtceProperties urbanAppMtceProperties;

    @Bean
    public UserFeignClient userFeignClient(Encoder encoder, Decoder decoder, Contract contract) {
        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptor(loginRequestInterceptor())
                .target(UserFeignClient.class, urbanAppMtceProperties.getUrl());
    }

    @Bean
    public RoleFeignClient roleFeignClient(Encoder encoder, Decoder decoder, Contract contract) {
        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptor(loginRequestInterceptor())
                .target(RoleFeignClient.class, urbanAppMtceProperties.getUrl());
    }

    @Bean
    public MenuFeignClient menuFeignClient(Encoder encoder, Decoder decoder, Contract contract) {
        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptor(loginRequestInterceptor())
                .target(MenuFeignClient.class, urbanAppMtceProperties.getUrl());
    }

    public RequestInterceptor loginRequestInterceptor() {
        return new LoginRequestInterceptor(urbanAppMtceProperties.getUrl(), urbanAppMtceProperties.getAppKey(), urbanAppMtceProperties.getAppSecret());
    }

}
