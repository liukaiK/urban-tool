package com.unicom.urban.spring.boot;

import com.unicom.urban.properties.UrbanCasProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
public class UrbanCasSessionAuthenticationStrategyAutoConfiguration {

    @Autowired
    private UrbanCasProperties urbanCasProperties;

    @Bean
    @ConditionalOnMissingBean(name = "casSessionAuthenticationStrategy")
    public SessionAuthenticationStrategy casSessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(ServiceProperties.class)
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(urbanCasProperties.getService());
        return serviceProperties;
    }

}
