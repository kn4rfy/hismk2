package com.hisd3.hismk2.domain.pms

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.Instant

@Entity
@Table(schema = "pms", name = "doctor_order_item")
class DoctorOrderItem extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_order", referencedColumnName = "id")
	DoctorOrder doctorOrder
	
	@GraphQLQuery
	@Column(name = "order", columnDefinition = "varchar")
	String order
	
	@GraphQLQuery
	@Column(name = "type", columnDefinition = "varchar")
	String type
	
	@GraphQLQuery
	@Column(name = "status", columnDefinition = "varchar")
	String status
	
	@GraphQLQuery
	@Column(name = "entry_datetime", columnDefinition = "timestamp")
	Instant entryDateTime
}
