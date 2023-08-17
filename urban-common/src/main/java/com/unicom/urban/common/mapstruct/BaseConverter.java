package com.unicom.urban.common.mapstruct;


import com.unicom.urban.common.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface BaseConverter<E extends BaseEntity, V> {

    V convert(E entity);

    Collection<V> convert(Collection<E> entities);

    default Page<V> convertPage(Page<E> page) {
        List<V> list = page.stream().map(this::convert).collect(Collectors.toList());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }


}
