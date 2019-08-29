package com.hisd3.hismk2.domain.inventory

import com.hisd3.hismk2.domain.Department
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.Type

import javax.persistence.*

@Entity
@Table(schema = "inventory", name = "inventory")
class Inventory {
	
	@GraphQLQuery
	@Id
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@GraphQLQuery
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item", referencedColumnName = "id")
	Item item
	
	@GraphQLQuery
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department", referencedColumnName = "id")
	Department department
	
	@GraphQLQuery
	@Column(name = "quantity")
	BigDecimal quantity
}
