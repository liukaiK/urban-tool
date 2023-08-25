package com.unicom.urban.controller;

import com.unicom.urban.constant.SecurityConstant;
import com.unicom.urban.properties.UrbanCasProperties;
import org.jasig.cas.client.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ConditionalOnMissingBean(name = SecurityConstant.CAS_BEAN_NAME)
public class CasController {

    @Autowired
    private ServiceProperties serviceProperties;

    @Autowired
    private UrbanCasProperties urbanCasProperties;

    private boolean encodeServiceUrlWithSessionId = false;


    /**
     * 请这个地址 然后重定向到cas
     */
    @GetMapping(SecurityConstant.REDIRECT_URL)
    public String sso(HttpServletRequest request, HttpServletResponse response) {
        String urlEncodedService = createServiceUrl(request, response);
        String redirectUrl = createRedirectUrl(urlEncodedService);
        return "redirect:" + redirectUrl;
    }


    protected String createServiceUrl(HttpServletRequest request, HttpServletResponse response) {
        return CommonUtils.constructServiceUrl(null, response, this.serviceProperties.getService(), null, null, this.serviceProperties.getArtifactParameter(), this.encodeServiceUrlWithSessionId);
    }

    protected String createRedirectUrl(String serviceUrl) {
        return CommonUtils.constructRedirectUrl(urbanCasProperties.getLoginUrl(), this.serviceProperties.getServiceParameter(), serviceUrl, this.serviceProperties.isSendRenew(), false);
    }


}
