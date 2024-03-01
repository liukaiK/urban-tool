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

    private String format;

    private String deleted;

}
