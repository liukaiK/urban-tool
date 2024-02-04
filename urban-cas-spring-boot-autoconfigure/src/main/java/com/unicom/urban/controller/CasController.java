package com.unicom.urban.controller;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicom.urban.common.util.HttpUtil;
import com.unicom.urban.common.util.ResponseUtil;
import com.unicom.urban.constant.SecurityConstant;
import com.unicom.urban.properties.UrbanCasProperties;
import org.jasig.cas.client.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.MediaType;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@ConditionalOnMissingBean(name = SecurityConstant.CAS_BEAN_NAME)
public class CasController {

    @Autowired
    private ServiceProperties serviceProperties;

    @Autowired
    private UrbanCasProperties urbanCasProperties;

    @Autowired
    private ObjectMapper objectMapper;

    private boolean encodeServiceUrlWithSessionId = false;


    /**
     * 请这个地址 然后重定向到cas
     */
    @GetMapping(SecurityConstant.REDIRECT_URL)
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String urlEncodedService = createServiceUrl(request, response);
        String redirectUrl = createRedirectUrl(urlEncodedService);
        if (HttpUtil.isAjaxRequest(request)) {
            Map<String, Object> map = new HashMap<>(3);
            map.put("code", 401);
            map.put("message", StrUtil.format("请跳转至:{}地址", redirectUrl));
            map.put("data", redirectUrl);
            ResponseUtil.write(response, objectMapper.writeValueAsString(map), MediaType.APPLICATION_JSON_VALUE);
        } else {
            response.sendRedirect(redirectUrl);
        }

    }


    protected String createServiceUrl(HttpServletRequest request, HttpServletResponse response) {
        return CommonUtils.constructServiceUrl(null, response, this.serviceProperties.getService(), null, null, this.serviceProperties.getArtifactParameter(), this.encodeServiceUrlWithSessionId);
    }

    protected String createRedirectUrl(String serviceUrl) {
        return CommonUtils.constructRedirectUrl(urbanCasProperties.getLoginUrl(), this.serviceProperties.getServiceParameter(), serviceUrl, this.serviceProperties.isSendRenew(), false);
    }


}
