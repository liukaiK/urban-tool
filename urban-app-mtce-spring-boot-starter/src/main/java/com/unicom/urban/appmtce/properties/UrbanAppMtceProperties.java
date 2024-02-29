package com.unicom.urban.appmtce.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "urban.appMtce")
public class UrbanAppMtceProperties implements InitializingBean {

    private String host;

    private String appKey;

    private String appSecret;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

}
