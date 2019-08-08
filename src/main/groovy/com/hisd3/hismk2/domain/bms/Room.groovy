package com.hisd3.hismk2.domain.bms

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.*

@Entity
@Table(schema = "bms", name = "rooms")
class Room extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@GraphQLQuery
	@Column(name = "room_no", columnDefinition = "varchar")
	String roomNo
	
	@GraphQLQuery
	@Column(name = "bed_no", columnDefinition = "varchar")
	String bedNo
	
	@GraphQLQuery
	@Column(name = "description", columnDefinition = "varchar")
	String description
	
	@GraphQLQuery
	@Column(name = "price", columnDefinition = "numeric")
	BigDecimal price
	
	@GraphQLQuery
	@Column(name = "status", columnDefinition = "varchar")
	String status
}
