package com.unicom.urban.appmtce.user;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {

    private String id;

    private String username;

    private String name;

    private String gender;

    @JsonFormat(pattern = DatePattern.NORM_DATE_PATTERN)
    private LocalDate birthday;

    private String telMobile;

    private String email;

    private String orgId;

    private String orgName;

    private String roleIds;

    private String roleCodes;

    private String roleNames;

    private String postId;

    private String postName;

    private String idCard;

    private String status;

    private String statusValue;

    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTime;

    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateTime;

    private String deleted;

    private String deletedValue;

    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime deletedTime;


    public String getStatusValue() {
        if ("0".equals(status)) {
            return "在职";
        } else {
            return "离职";
        }
    }

}
