package com.hisd3.hismk2.domain.inventory

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.hrm.Employee
import com.hisd3.hismk2.domain.pms.Patient
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
	String stockRequestNo

	@GraphQLQuery
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient", referencedColumnName = "id")
	Patient patient

	@GraphQLQuery
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requesting_department", referencedColumnName = "id")
	Department requestingDepartment

	@GraphQLQuery
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requested_department", referencedColumnName = "id")
	Department requestedDepartment

	@GraphQLQuery
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requested_by", referencedColumnName = "id")
	Employee requestedBy

	@GraphQLQuery
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prepared_by", referencedColumnName = "id")
	Employee preparedBy

	@GraphQLQuery
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dispensed_by", referencedColumnName = "id")
	Employee dispensedBy

	@GraphQLQuery
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "claimed_by", referencedColumnName = "id")
	Employee claimedBy

	@GraphQLQuery
	@Column(name = "status")
	String status
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stockRequest")
	List<StockRequestItem> stockRequestItems
	
}
