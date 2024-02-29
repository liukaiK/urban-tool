package com.unicom.urban.appmtce.user;

import cn.hutool.core.convert.Convert;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;


@Getter
@Setter
@Builder
public class UserSearch {

    private String id;

    private String name;

    private String username;

    private String gender;

    private String status;

    private String orgId;

    private String postId;

    private String roleCodes;

    private String deleted;

    private Boolean hasOrg;

    /**
     * 是否查询子组织结构所有人，默认不查询
     */
    private Boolean includeSubOrg = false;

    public Long getPostIdLong() {
        return Convert.toLong(this.postId);
    }

    public Long getOrgIdLong() {
        return Convert.toLong(this.orgId);
    }

    public Collection<String> getRoleCodes() {
        return Convert.toList(String.class, this.roleCodes);
    }

    /**
     * 搜索有组织机构的用户
     */
    public boolean hasOrg() {
        return this.hasOrg;
    }

    public boolean includeSubOrg() {
        return this.includeSubOrg;
    }

}
