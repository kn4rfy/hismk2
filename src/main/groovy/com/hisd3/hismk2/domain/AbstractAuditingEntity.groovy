package com.hisd3.hismk2.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import io.leangen.graphql.annotations.GraphQLQuery
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener)
class AbstractAuditingEntity {
	@GraphQLQuery
	@CreatedBy
	@Column(name = "created_by", nullable = false, length = 50, updatable = false)
	@JsonIgnore
	String createdBy


	@GraphQLQuery
	@CreatedDate
	@Column(name = "created_date", nullable = false)
	Instant createdDate


	@GraphQLQuery
	@LastModifiedBy
	@Column(name = "last_modified_by", length = 50)
	@JsonIgnore
	String lastModifiedBy


	@GraphQLQuery
	@LastModifiedDate
	@Column(name = "last_modified_date")
	@JsonIgnore
	Instant lastModifiedDate
}
