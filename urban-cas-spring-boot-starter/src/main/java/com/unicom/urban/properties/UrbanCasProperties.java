package com.unicom.urban.properties;

import com.unicom.urban.common.util.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "urban.cas")
public class UrbanCasProperties implements InitializingBean {

    /**
     * 是否启用城管CAS认证
     */
    private boolean enable;

    /**
     * service地址
     */
    private String service;

    /**
     * 用于获取票据的地址
     */
    private String ticketUrl;

    /**
     * cas的登录地址 例如：http://127.0.0.1:8080/cas/login
     */
    private String loginUrl;

    /**
     * cas的退出地址 例如：http://127.0.0.1:8080/cas/logout
     */
    private String logoutUrl;

    /**
     * 认证成功之后的跳转地址
     */
    private String successUrl;

    @Override
    public void afterPropertiesSet() {
        if (!StringUtils.hasText(this.loginUrl)) {
            this.loginUrl = this.ticketUrl + "/login";
        }
    }

}
