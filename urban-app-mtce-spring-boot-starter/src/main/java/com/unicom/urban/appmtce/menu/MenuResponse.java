package com.unicom.urban.appmtce.menu;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
public class MenuResponse {

    private String id;

    private String level;

    private String name;

    private String type;

    private String icon;

    private String path;

    private String permission;

    private String sort;

    private String description;

    private String target;

    private String visible;

    private String componentPath;

    private Collection<MenuResponse> children;

    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTime;

    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateTime;

    private String deleted;

    private String deletedValue;

    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime deletedTime;

}
