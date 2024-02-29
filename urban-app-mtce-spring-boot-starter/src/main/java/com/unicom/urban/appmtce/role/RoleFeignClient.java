package com.unicom.urban.appmtce.role;

import com.unicom.urban.appmtce.common.RestResponsePage;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface RoleFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/roles")
    RestResponsePage<RoleResponse> search(@SpringQueryMap RoleSearch roleSearch, Pageable pageable);

    @RequestMapping(method = RequestMethod.GET, value = "/v1/roles/{id}")
    RoleResponse find(@PathVariable("id") String id);

}
