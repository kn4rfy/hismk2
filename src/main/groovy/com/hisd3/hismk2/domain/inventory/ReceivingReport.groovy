package com.hisd3.hismk2.domain.inventory

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.*
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(schema = "inventory", name = "receiving_report")
class ReceivingReport extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@GraphQLQuery
	@Column(name = "supplier", columnDefinition = 'varchar')
	String supplier
	
	@GraphQLQuery
	@Column(name = "ref_no", columnDefinition = 'varchar')
	String refNo

	@GraphQLQuery
	@Column(name = "rr_no", columnDefinition = 'varchar')
	String rrNo
	
	@GraphQLQuery
	@Column(name = "receiving_department", columnDefinition = 'varchar')
	String receivingDepartment
	
	@GraphQLQuery
	@Column(name = "ref_qlty", columnDefinition = 'varchar')
	String refQlty
	
	@GraphQLQuery
	@Column(name = "qlty_inspection_date", nullable = true)
	LocalDateTime qltyInspectionDate
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "receivingReport")
	Set<ReceivingReportItem> receivingItems = [] as Set

	@Transient
	Instant getDateCreated(){
		return createdDate
	}

	@Transient
	String getCreatedByString(){
		return createdBy
	}

}
