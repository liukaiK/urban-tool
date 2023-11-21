package com.unicom.urban.spring.boot;

import com.unicom.urban.common.constant.SysConstants;
import com.unicom.urban.constant.SecurityConstant;
import com.unicom.urban.properties.UrbanCasProperties;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collections;
import java.util.UUID;

/**
 * CAS的配置
 *
 * @author liukai
 */
@Configuration
@ComponentScan(basePackages = SysConstants.BASE_PACKAGE)
@EnableConfigurationProperties(UrbanCasProperties.class)
@ConditionalOnProperty(name = "urban.cas.enable", matchIfMissing = true)
public class UrbanCasAutoConfiguration {

    @Autowired
    private UrbanCasProperties urbanCasProperties;

    @Autowired
    private AuthenticationUserDetailsService<CasAssertionAuthenticationToken> authenticationUserDetailsService;

    @Autowired
    private LogoutHandler casLogoutHandler;

    @Autowired
    private SessionAuthenticationStrategy casSessionAuthenticationStrategy;

    private final static String CAS_LOGOUT_URL = "/logout/cas";

    /**
     * CAS登录成功后，带着ticket访问这个地址
     */
    private final static String URBAN_CAS_FILTER_PROCESSES_URL = "/login/cas";

    /**
     * 城管的CAS配置
     */
    @Bean
    @Order(10)
    @ConditionalOnMissingBean(name = SecurityConstant.CAS_BEAN_NAME)
    public SecurityFilterChain urbanCasSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers(HttpMethod.GET, SecurityConstant.REDIRECT_URL, URBAN_CAS_FILTER_PROCESSES_URL, CAS_LOGOUT_URL)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(CAS_LOGOUT_URL, HttpMethod.GET.name())).logoutSuccessUrl(urbanCasProperties.getTicketUrl() + "/logout").addLogoutHandler(casLogoutHandler)
                .and()
                .csrf().disable()
                .addFilterAt(new SingleSignOutFilter(), LogoutFilter.class)
                .addFilterBefore(casAuthenticationFilter(), RequestCacheAwareFilter.class)
                .exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint());
        return http.build();
    }

    @Bean
    @ConditionalOnMissingBean(CasAuthenticationFilter.class)
    public CasAuthenticationFilter casAuthenticationFilter() {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setFilterProcessesUrl(URBAN_CAS_FILTER_PROCESSES_URL);
        casAuthenticationFilter.setAuthenticationManager(new ProviderManager(Collections.singletonList(casAuthenticationProvider())));
        casAuthenticationFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler(urbanCasProperties.getSuccessUrl()));
        casAuthenticationFilter.setSessionAuthenticationStrategy(casSessionAuthenticationStrategy);
        return casAuthenticationFilter;
    }

    @Bean
    @ConditionalOnMissingBean(CasAuthenticationProvider.class)
    public AuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setTicketValidator(ticketValidator());
        casAuthenticationProvider.setKey(UUID.randomUUID().toString());
        casAuthenticationProvider.setAuthenticationUserDetailsService(authenticationUserDetailsService);
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        return casAuthenticationProvider;
    }

    @Bean
    @ConditionalOnMissingBean(CasAuthenticationEntryPoint.class)
    public AuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setLoginUrl(urbanCasProperties.getLoginUrl());
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        casAuthenticationEntryPoint.setEncodeServiceUrlWithSessionId(false);
        return casAuthenticationEntryPoint;
    }

    @Bean
    @ConditionalOnMissingBean(ServiceProperties.class)
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(urbanCasProperties.getService());
        return serviceProperties;
    }

    @Bean
    @ConditionalOnMissingBean(TicketValidator.class)
    public TicketValidator ticketValidator() {
        return new Cas30ServiceTicketValidator(urbanCasProperties.getTicketUrl());
    }

}
