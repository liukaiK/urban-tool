package com.unicom.urban.appmtce.application;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationResponse {

    private String id;

    private String name;

    private String url;

    private String icon;

    private String description;

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
