package com.unicom.urban.appmtce.org;

import com.unicom.urban.appmtce.common.RestResponsePage;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface OrgFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/orgs")
    RestResponsePage<OrgResponse> search(@SpringQueryMap OrgSearch orgSearch, Pageable pageable);

    @RequestMapping(method = RequestMethod.GET, value = "/v1/orgs/{id}")
    OrgResponse find(@PathVariable("id") String id);

}
