package com.unicom.urban.appmtce.menu;

import com.unicom.urban.appmtce.common.RestResponsePage;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface MenuFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/menus")
    RestResponsePage<MenuResponse> search(@SpringQueryMap MenuSearch menuSearch, Pageable pageable);

    @RequestMapping(method = RequestMethod.GET, value = "/v1/menus/{id}")
    MenuResponse find(@PathVariable("id") String id);

}
