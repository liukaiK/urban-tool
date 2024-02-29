package com.unicom.urban.appmtce.user;

import com.unicom.urban.appmtce.common.RestResponsePage;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface UserFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/users")
    RestResponsePage<UserResponse> search(@SpringQueryMap UserSearch userSearch, Pageable pageable);

    @RequestMapping(method = RequestMethod.GET, value = "/v1/users/{id}")
    UserResponse find(@PathVariable("id") String id);

}
