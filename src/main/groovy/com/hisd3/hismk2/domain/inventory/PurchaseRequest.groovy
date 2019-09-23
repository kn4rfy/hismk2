package com.hisd3.hismk2.domain.inventory

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.Instant

@Entity
@Table(schema = "inventory", name = "purchase_request")
class PurchaseRequest extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@GraphQLQuery
	@Column(name = "department", columnDefinition = "uuid")
	UUID department
	
	@GraphQLQuery
	@Column(name = "department_name", columnDefinition = "varchar")
	String departmentName
	
	@GraphQLQuery
	@Column(name = "pr_no", columnDefinition = "varchar")
	String prNo
	
	@GraphQLQuery
	@Column(name = "requested_by", columnDefinition = "uuid")
	UUID requestedBy
	
	@GraphQLQuery
	@Column(name = "requested_by_name", columnDefinition = "varchar")
	String requestedByName
	
	@GraphQLQuery
	@Column(name = "requested_date", columnDefinition = "timestamp")
	Instant requestedDate
	
	@GraphQLQuery
	@Column(name = "approved_by", columnDefinition = "uuid")
	UUID approvedBy
	
	@GraphQLQuery
	@Column(name = "approved_name", columnDefinition = "varchar")
	String approvedName
	
	@GraphQLQuery
	@Column(name = "approved_date", columnDefinition = "timestamp")
	Instant approvedDate
	
	@GraphQLQuery
	@Column(name = "suggested_supplier_name", columnDefinition = "varchar")
	String suggestedSupplierName
	
	@GraphQLQuery
	@Column(name = "request_type", columnDefinition = "varchar")
	String requestType
	
	@GraphQLQuery
	@Column(name = "date_needed", columnDefinition = "timestamp")
	Instant dateNeeded
	
}
