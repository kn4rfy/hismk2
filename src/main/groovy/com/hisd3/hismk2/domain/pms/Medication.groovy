package com.hisd3.hismk2.domain.pms

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.hrm.Employee
import com.hisd3.hismk2.domain.inventory.Item
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(schema = "pms", name = "medications")
class Medication extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`case`", referencedColumnName = "id")
	Case parentCase
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medicine", referencedColumnName = "id")
	Item medicine
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee", referencedColumnName = "id")
	Employee orderingPhysician
	
	@GraphQLQuery
	@Column(name = "frequency", columnDefinition = "varchar")
	String frequency
	
	@GraphQLQuery
	@Column(name = "dose", columnDefinition = "varchar")
	String dose
	
	@GraphQLQuery
	@Column(name = "route", columnDefinition = "varchar")
	String route
	
	@GraphQLQuery
	@Column(name = "remarks", columnDefinition = "varchar")
	String remarks
	
	@GraphQLQuery
	@Column(name = "type", columnDefinition = "varchar")
	String type
	
	@GraphQLQuery
	@Column(name = "entry_datetime", columnDefinition = "timestamp")
	LocalDateTime entryDateTime
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "medication")
	Set<Administration> administrations = [] as Set
}
