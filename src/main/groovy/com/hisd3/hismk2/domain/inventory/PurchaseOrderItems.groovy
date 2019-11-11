package com.hisd3.hismk2.domain.inventory

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(schema = "inventory", name = "purchase_order_items")
class PurchaseOrderItems extends AbstractAuditingEntity {
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id


	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item", referencedColumnName = "id")
	Item item
	
	@GraphQLQuery
	@Column(name = "quantity", columnDefinition = "numeric")
	Integer quantity

	@GraphQLQuery
	@Column(name = "pr_nos", columnDefinition = "varchar")
	String prNos
	
	@GraphQLQuery
	@Column(name = "unit_measure", columnDefinition = "varchar")
	Integer unitMeasure
	
	@GraphQLQuery
	@Column(name = "date_completed", columnDefinition = "timestamp")
	LocalDateTime dateCompleted

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purchase_order", referencedColumnName = "id")
	PurchaseOrder purchaseOrder

	
	@GraphQLQuery
	@Column(name = "receiving_report", columnDefinition = "uuid")
	UUID receivingReport
	
	@GraphQLQuery
	@Column(name = "supplier_last_price", columnDefinition = "numeric")
	Integer supplierLastPrice
}
