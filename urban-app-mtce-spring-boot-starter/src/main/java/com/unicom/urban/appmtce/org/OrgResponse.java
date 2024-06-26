package com.unicom.urban.appmtce.org;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.unicom.urban.appmtce.user.UserResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class OrgResponse {

    private String id;

    private String name;

    private String code;

    private String parentId;

    private String contactName;

    private String contactTel;

    private String status;

    private String statusValue;

    private List<UserResponse> users;

    private Collection<OrgResponse> children;

    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTime;

    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateTime;

    private String deleted;

    private String deletedValue;

    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime deletedTime;

}
