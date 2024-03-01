package com.unicom.urban.appmtce.org;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrgSearch {

    private String id;

    private String name;

    private String code;

    private String format;

    private String parentId;

    private String contactName;

    private String contactTel;

    private String deleted;

    private String status;


}
