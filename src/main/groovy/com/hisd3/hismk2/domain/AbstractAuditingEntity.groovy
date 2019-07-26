package com.hisd3.hismk2.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Parameter
import org.hibernate.annotations.Type
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import java.sql.Date
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener)
abstract class AbstractAuditingEntity {
    @CreatedBy
    // @NotNull
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    @JsonIgnore
    String createdBy

    @CreatedDate
   /* @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime",
            parameters = [ @Parameter(name = "javaZone", value = "jvm")])*/
    @Column(name = "created_date", nullable = false)
    Instant createdDate

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    String lastModifiedBy

    @LastModifiedDate
  /*  @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime",
            parameters = [ @Parameter(name = "javaZone", value = "jvm")])*/
    @Column(name = "last_modified_date")
    @JsonIgnore
    Instant lastModifiedDate
}
