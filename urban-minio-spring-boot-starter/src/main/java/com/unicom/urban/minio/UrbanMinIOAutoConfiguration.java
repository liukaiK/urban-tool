package com.unicom.urban.minio;

import com.unicom.urban.common.constant.SysConstants;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * MinIO的配置
 *
 * @author liukai
 */
@Configuration
@ComponentScan(basePackages = SysConstants.BASE_PACKAGE)
@EnableConfigurationProperties(UrbanMinIOProperties.class)
@ConditionalOnProperty(name = "urban.minio.enable", matchIfMissing = true)
public class UrbanMinIOAutoConfiguration {

    @Autowired
    private UrbanMinIOProperties urbanMinIOProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(urbanMinIOProperties.getEndpoint(), urbanMinIOProperties.getPort(), urbanMinIOProperties.isSecure())
                .credentials(urbanMinIOProperties.getAccessKey(), urbanMinIOProperties.getSecretKey())
                .build();
    }


}
