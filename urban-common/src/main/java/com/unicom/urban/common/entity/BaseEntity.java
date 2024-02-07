package com.unicom.urban.common.entity;

import cn.hutool.core.date.DatePattern;
import com.unicom.urban.common.constant.SysConstants;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liukai
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<ID extends Serializable> implements Persistable<ID>, Serializable {

    @Id
    @Column(nullable = false, columnDefinition = "bigint unsigned")
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = SysConstants.SNOW_CLASS)
    protected ID id;

    @CreatedDate
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTime;

    @LastModifiedDate
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateTime;

    @Transient
    private transient boolean _new = true;

    @Override
    public boolean isNew() {
        return _new;
    }

    @PostLoad
    void postLoad() {
        this._new = false;
    }

    public void makeInsertable() {
        this._new = true;
    }

    public void makeUpdatable() {
        this._new = false;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public ID getId() {
        return id;
    }

    /**
     * 这个方法会触发懒加载
     */
    public String getIdStr() {
        return String.valueOf(this.id);
    }

}
