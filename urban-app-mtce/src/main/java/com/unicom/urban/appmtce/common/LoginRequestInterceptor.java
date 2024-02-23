package com.unicom.urban.appmtce.common;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class LoginRequestInterceptor implements RequestInterceptor {

    @JsonIgnore
    private String host;

    private String appKey;

    private String appSecret;

    @JsonIgnore
    private ObjectMapper objectMapper;

    public LoginRequestInterceptor() {
    }

    public LoginRequestInterceptor(String host, String appKey, String appSecret) {
        this.host = host;
        this.appKey = appKey;
        this.appSecret = appSecret;
        objectMapper = new ObjectMapper();
    }

    @Override
    public void apply(RequestTemplate request) {
        try {
            String body = HttpUtil.createPost(host + "/v1/login").body(objectMapper.writeValueAsString(this)).execute().body();
            AccessToken accessToken = objectMapper.readValue(body, AccessToken.class);
            request.header(accessToken.getTokenName(), accessToken.getAccessToken());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
