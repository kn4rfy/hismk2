package com.hisd3.hismk2.domain.ancillary

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.Instant
import java.time.LocalDateTime

@TypeChecked
@Entity
@Table(schema = "ancillary", name = "orderslip_item")
class OrderSlipItem extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service", referencedColumnName = "id")
	Service service
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderslip", referencedColumnName = "id")
	Orderslip orderslip
	
	@GraphQLQuery
	@Column(name = "time_started", columnDefinition = "timestamp")
	LocalDateTime timeStarted
	
	@GraphQLQuery
	@Column(name = "time_completed", columnDefinition = "timestamp")
	LocalDateTime timeCompleted
	
	@GraphQLQuery
	@Column(name = "status", columnDefinition = "varchar")
	String status
	
	@GraphQLQuery
	@Column(name = "posted", columnDefinition = "boolean")
	Boolean posted
	
	@GraphQLQuery
	@Column(name = "deleted", columnDefinition = "boolean")
	Boolean deleted
	
	@GraphQLQuery
	@Column(name = "batch_num", columnDefinition = "varchar")
	String batchNumber
	
	@Transient
	Instant getCreated() {
		return createdDate
	}
	
}
