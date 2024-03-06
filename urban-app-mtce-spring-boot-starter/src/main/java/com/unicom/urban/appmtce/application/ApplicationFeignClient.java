package com.unicom.urban.appmtce.application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface ApplicationFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/applications")
    ApplicationResponse find();

}
