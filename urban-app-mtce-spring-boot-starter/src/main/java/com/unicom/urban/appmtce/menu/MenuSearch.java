package com.unicom.urban.appmtce.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MenuSearch {

    private String id;

    private String name;

    private String parentId;

    /**
     * 可传tree和list
     */
    private String format;

    /**
     * 可以传多个，用逗号分割
     */
    private String roleCodes;

    private String deleted;

}
