package com.hisd3.hismk2.domain.inventory

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.*

@Entity
@Table(schema = "inventory", name = "stock_request")
class StockRequest extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@GraphQLQuery
	@Column(name = "stock_request_no")
	BigDecimal stockRequestNo
	
	@GraphQLQuery
	@Column(name = "status")
	String status
	
	@GraphQLQuery
	@Column(name = "patient")
	String patient
	
	@GraphQLQuery
	@Column(name = "requested_by")
	String requestedBy
	
	@GraphQLQuery
	@Column(name = "requesting_department")
	String requestingDepartment
	
	@GraphQLQuery
	@Column(name = "requested_department")
	String requestedDepartment
	
	@GraphQLQuery
	@Column(name = "requesting_department_reference_id")
	UUID requestingDepartmentReferenceId
	
	@GraphQLQuery
	@Column(name = "requested_department_reference_id")
	UUID requestedDepartmentReferenceId
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stockRequest")
	List<StockRequestItem> stockRequestItems
	
}
