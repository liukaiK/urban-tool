package com.unicom.urban.minio;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "urban.minio")
public class UrbanMinIOProperties implements InitializingBean {

    private String endpoint = "127.0.0.1";

    private Integer port = 9000;

    private Boolean secure = false;

    private String bucket;

    private String accessKey;

    private String secretKey;

    @Override
    public void afterPropertiesSet() throws Exception {
    }

}
