package com.hisd3.hismk2.domain.billing

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.Department
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*

@Entity
@Table(name = "billing_item", schema = "billing")
class BillingItem extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing", referencedColumnName = "id")
	Billing billing
	
	@GraphQLQuery
	@Column(name = "record_no", columnDefinition = "varchar")
	String recordNo
	
	@GraphQLQuery
	@Column(name = "item_type", columnDefinition = "varchar")
	String itemType
	
	@GraphQLQuery
	@Column(name = "item", columnDefinition = "varchar")
	String item
	
	@GraphQLQuery
	@Column(name = "description", columnDefinition = "varchar")
	String description
	
	@GraphQLQuery
	@Column(name = "qty", columnDefinition = "decimal")
	Integer qty
	
	@GraphQLQuery
	@Column(name = "price", columnDefinition = "decimal")
	BigDecimal price
	
	@GraphQLQuery
	@Column(name = "status", columnDefinition = "varchar")
	String status
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department", referencedColumnName = "id")
	Department department
}
