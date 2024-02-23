package com.unicom.urban.appmtce.user;

import cn.hutool.core.convert.Convert;

import java.util.Collection;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setRoleCodes(String roleCodes) {
        this.roleCodes = roleCodes;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Boolean getHasOrg() {
        return hasOrg;
    }

    public void setHasOrg(Boolean hasOrg) {
        this.hasOrg = hasOrg;
    }

    public Boolean getIncludeSubOrg() {
        return includeSubOrg;
    }

    public void setIncludeSubOrg(Boolean includeSubOrg) {
        this.includeSubOrg = includeSubOrg;
    }
}
