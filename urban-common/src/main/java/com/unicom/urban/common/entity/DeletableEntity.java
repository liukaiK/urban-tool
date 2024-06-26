package com.unicom.urban.common.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class DeletableEntity<ID extends Serializable> extends BaseEntity<ID> {

    /**
     * 数据是否已被删除
     */
    @Column(nullable = false, columnDefinition = "bigint unsigned")
    private Long deleted = 0L;

    private LocalDateTime deletedTime;

    public void remove() {
        this.deleted = (Long) getId();
        this.deletedTime = LocalDateTime.now();
    }

    public Long getDeleted() {
        return deleted;
    }

    public LocalDateTime getDeletedTime() {
        return deletedTime;
    }

}
