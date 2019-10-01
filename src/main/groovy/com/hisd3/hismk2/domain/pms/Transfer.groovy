package com.hisd3.hismk2.domain.pms

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.bms.Room
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
@Table(schema = "pms", name = "transfers")
class Transfer extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@GraphQLQuery
	@Column(name = "entry_datetime", columnDefinition = "timestamp")
	Instant entryDateTime
	
	@GraphQLQuery
	@Column(name = "registry_type", columnDefinition = "varchar")
	String registryType
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department", referencedColumnName = "id")
	Department department
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "`case`", referencedColumnName = "id")
	Case parentCase
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "room", referencedColumnName = "id")
	Room room
	
	@GraphQLQuery
	@Column(name = "status", columnDefinition = "varchar")
	String status
}
