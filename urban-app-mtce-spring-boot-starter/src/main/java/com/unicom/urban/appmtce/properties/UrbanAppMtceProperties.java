package com.unicom.urban.appmtce.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@Setter
@Getter
@ConfigurationProperties(prefix = "urban.app-mtce")
public class UrbanAppMtceProperties implements InitializingBean {

    private String url;

    private String appKey;

    private String appSecret;

    @Override
    public void afterPropertiesSet() {
        Assert.hasText(this.url, "url属性不能为空");
        Assert.hasText(this.appKey, "请配置appKey");
        Assert.hasText(this.appSecret, "请配置appSecret");
    }

}
