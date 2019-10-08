package com.hisd3.hismk2.domain.inventory

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.hrm.Employee
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.*

@Entity
@Table(schema = "inventory", name = "stock_request_item")
class StockRequestItem extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@GraphQLQuery
	@Column(name = "expected_barcode")
	String expectedBarcode
	
	@GraphQLQuery
	@Column(name = "item_description")
	String itemDescription
	
	@GraphQLQuery
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item", referencedColumnName = "id")
	Item item
	
	@GraphQLQuery
	@Column(name = "requested_qty")
	BigDecimal requestedQty
	
	@GraphQLQuery
	@Column(name = "prepared_qty")
	BigDecimal preparedQty
	
	@GraphQLQuery
	@Column(name = "deducted_to_inventory")
	BigDecimal deductedToInventory
	
	@GraphQLQuery
	@Column(name = "billed_to_patient")
	BigDecimal billedToPatient
	
	@GraphQLQuery
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ordered_by", referencedColumnName = "id")
	Employee orderedBy
	
	@GraphQLQuery
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_request", referencedColumnName = "id")
	StockRequest stockRequest
	
}
