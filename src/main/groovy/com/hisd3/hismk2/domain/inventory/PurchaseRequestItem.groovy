package com.hisd3.hismk2.domain.inventory

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*

@Entity
@Table(schema = "inventory", name = "purchase_request_items")
class PurchaseRequestItem extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`ref_item`", referencedColumnName = "id")
	Item refItem
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "`ref_pr`", referencedColumnName = "id")
	PurchaseRequest refPr
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "`ref_po`", referencedColumnName = "id")
	PurchaseOrder refPo
	
	@GraphQLQuery
	@Column(name = "item_name", columnDefinition = "varchar")
	String itemName
	
	@GraphQLQuery
	@Column(name = "qty", columnDefinition = "numeric")
	Integer qty
	
	@GraphQLQuery
	@Column(name = "price", columnDefinition = "numeric")
	Double price
	
}
