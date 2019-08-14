package com.hisd3.hismk2.domain.inventory

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*

@Entity
@Table(schema = "inventory", name = "receiving_report_items")
class ReceivingReportItem extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiving_report", referencedColumnName = "id")
	ReceivingReport receivingReport
	
	@GraphQLQuery
	@Column(name = "barcode", columnDefinition = 'varchar')
	String barcode
	
	@GraphQLQuery
	@Column(name = "description", columnDefinition = 'varchar')
	String description
	
	@GraphQLQuery
	@Column(name = "ref_no", columnDefinition = 'varchar')
	String refNo
	
	@GraphQLQuery
	@Column(name = "po_qty", columnDefinition = 'numeric')
	Integer poQty
	
	@GraphQLQuery
	@Column(name = "qty_delivered", columnDefinition = 'numeric')
	Integer qtyDelivered
	
	@GraphQLQuery
	@Column(name = "po_balance_qty", columnDefinition = 'numeric')
	Integer poBalanceQty
	
	@GraphQLQuery
	@Column(name = "amt", columnDefinition = 'numeric')
	Integer amt
	
}
