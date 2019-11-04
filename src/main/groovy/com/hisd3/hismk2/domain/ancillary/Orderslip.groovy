package com.hisd3.hismk2.domain.ancillary

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.pms.Case
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.Instant

@TypeChecked
@Entity
@Table(schema = "ancillary", name = "orderslips")
class Orderslip extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id

//	@NotFound(action = NotFoundAction.IGNORE)
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "service", referencedColumnName = "id")
//	Service service
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_number", referencedColumnName = "id")
	Case parentCase
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department", referencedColumnName = "id")
	Department department
	
	@Column(name = "doctors_order", columnDefinition = "uuid")
	UUID doctorsOrder
	
	@Column(name = "requesting_physician", columnDefinition = "uuid")
	UUID requestingPhysician
	
	@GraphQLQuery
	@Column(name = "requesting_physician_name", columnDefinition = "varchar")
	String requestingPhysicianName
	
	@GraphQLQuery
	@Column(name = "orderslip_no", columnDefinition = "varchar")
	String orderSlipNo

//	@GraphQLQuery
//	@Column(name = "time_started", columnDefinition = "timestamp")
//	LocalDateTime timeStarted
//
//	@GraphQLQuery
//	@Column(name = "time_completed", columnDefinition = "timestamp")
//	LocalDateTime timeCompleted
//
	@GraphQLQuery
	@Column(name = "status", columnDefinition = "varchar")
	String status
	
	@GraphQLQuery
	@Column(name = "notes", columnDefinition = "varchar")
	String notes
//
//	@GraphQLQuery
//	@Column(name = "accession", columnDefinition = "varchar")
//	String accession

//	@GraphQLQuery
//	@Column(name = "posted", columnDefinition = "boolean")
//	Boolean posted

//	@GraphQLQuery
//	@Column(name = "submitted_via_hl7", columnDefinition = "boolean")
//	Boolean submittedViaHl7
//
//	@GraphQLQuery
//	@Column(name = "batchnum", columnDefinition = "varchar")
//	String batchNum
	
	@GraphQLQuery
	@Column(name = "deleted", columnDefinition = "boolean")
	Boolean deleted
	
	@Transient
	Instant getCreated() {
		return createdDate
	}
	
}
