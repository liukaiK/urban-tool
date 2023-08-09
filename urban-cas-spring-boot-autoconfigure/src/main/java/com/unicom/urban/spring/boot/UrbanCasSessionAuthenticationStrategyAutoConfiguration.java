package com.unicom.urban.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
public class UrbanCasSessionAuthenticationStrategyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "casSessionAuthenticationStrategy")
    public SessionAuthenticationStrategy casSessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

}
