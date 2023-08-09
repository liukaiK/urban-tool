package com.unicom.urban.properties;

import com.unicom.urban.common.util.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "urban.cas")
public class UrbanCasProperties {

    /**
     * service地址
     */
    private String service;

    /**
     * 用于获取票据的地址
     */
    private String ticketUrl;

    /**
     * cas的登录地址
     */
    private String loginUrl;

    /**
     * 认证成功之后的跳转地址
     */
    private String successUrl;

    public void init() {
        if (!StringUtils.hasText(this.loginUrl)) {
            this.loginUrl = this.ticketUrl + "/login";
        }

    }

}
