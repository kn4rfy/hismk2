package com.hisd3.hismk2.domain

import com.hisd3.hismk2.domain.pms.Transfer
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*

@Entity
@Table(schema = "public", name = "departments")
class Department extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@GraphQLQuery
	@Column(name = "department_code", columnDefinition = "varchar")
	String departmentCode
	
	@GraphQLQuery
	@Column(name = "department_name", columnDefinition = "varchar")
	String departmentName
	
	@GraphQLQuery
	@Column(name = "department_desc", columnDefinition = "varchar")
	String departmentDesc
	
	@GraphQLQuery
	@Column(name = "department_head", columnDefinition = "uuid default null")
	UUID departmentHead
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentDepartment", referencedColumnName = "id")
	Department parentDepartment
	
	@GraphQLQuery
	@Column(name = "special_area", columnDefinition = "boolean default false")
	Boolean specialArea
	
	@GraphQLQuery
	@Column(name = "cost_center", columnDefinition = "boolean default false")
	Boolean costCenter
	
	@GraphQLQuery
	@Column(name = "revenue_center", columnDefinition = "boolean default false")
	Boolean revenueCenter
	
	@GraphQLQuery
	@Column(name = "sub_department", columnDefinition = "boolean default false")
	Boolean subDepartment
	
	@GraphQLQuery
	@Column(name = "deleted", columnDefinition = "boolean default false")
	Boolean deleted
	
	@GraphQLQuery
	@Column(name = "deprecated", columnDefinition = "boolean")
	Boolean deprecated
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	Set<Transfer> departmentTransfers = [] as Set
	
}
