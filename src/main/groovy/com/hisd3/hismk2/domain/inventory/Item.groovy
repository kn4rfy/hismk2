package com.hisd3.hismk2.domain.inventory

import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(schema = "inventory", name = "item")
class Item {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@GraphQLQuery
	@Column(name = "stock_code")
	String stock_code
	
	@GraphQLQuery
	@Column(name = "desc_short")
	String desc_short
	
	@GraphQLQuery
	@Column(name = "desc_long")
	String desc_long
	
	@GraphQLQuery
	@Column(name = "unit_measure")
	String unit_measure
	
	@GraphQLQuery
	@Column(name = "minimum_level")
	String minimum_level
	
	@GraphQLQuery
	@Column(name = "economic_order_qty")
	BigDecimal economic_order_qty
	
	@GraphQLQuery
	@Column(name = "active")
	Boolean active
	
	@GraphQLQuery
	@Column(name = "stock_classification")
	String stock_classification
	
	@GraphQLQuery
	@Column(name = "indication_code")
	BigDecimal indication_code
	
	@GraphQLQuery
	@Column(name = "brand")
	String brand
	
	@GraphQLQuery
	@Column(name = "generic_name")
	String generic_name
	
	@GraphQLQuery
	@Column(name = "is_medicine")
	Boolean is_medicine
	
	@GraphQLQuery
	@Column(name = "indication_desc")
	String indication_desc
	
	@GraphQLQuery
	@Column(name = "medication_description")
	String medication_description
	
	@GraphQLQuery
	@Column(name = "sku")
	String sku
	
	@GraphQLQuery
	@Column(name = "route")
	String route
	
	@GraphQLQuery
	@Column(name = "package_name")
	String package_name
	
	@GraphQLQuery
	@Column(name = "package_content_count")
	BigDecimal package_content_count
	
	@GraphQLQuery
	@Column(name = "dose")
	BigDecimal dose
	
	@GraphQLQuery
	@Column(name = "dose_unit")
	BigDecimal dose_unit
	
	@GraphQLQuery
	@Column(name = "s2_drug")
	Boolean s2_drug
	
	@GraphQLQuery
	@Column(name = "form")
	String form
	
	@GraphQLQuery
	@Column(name = "vatable")
	String vatable
	
	@GraphQLQuery
	@Column(name = "preparation")
	String preparation
	
	@GraphQLQuery
	@Column(name = "distributor")
	String distributor
	
	@GraphQLQuery
	@Column(name = "advocate")
	String advocate
	
	@GraphQLQuery
	@Column(name = "category")
	String category
	
	@GraphQLQuery
	@Column(name = "sub_category")
	String sub_category
	
	@GraphQLQuery
	@Column(name = "sub_sub_category")
	String sub_sub_category
	
	@GraphQLQuery
	@Column(name = "formulary_item")
	String formulary_item
	
	@GraphQLQuery
	@Column(name = "fluid")
	Boolean fluid
	
	@GraphQLQuery
	@Column(name = "gas")
	Boolean gas
	
	@GraphQLQuery
	@Column(name = "direct_asset")
	Boolean direct_asset
	
	@GraphQLQuery
	@Column(name = "consignor")
	String consignor
	
	@GraphQLQuery
	@Column(name = "consignment_item")
	Boolean consignment_item
	
	@GraphQLQuery
	@Column(name = "consignor_name")
	String consignor_name
	
	@GraphQLQuery
	@Column(name = "comlogic_medicine_code")
	String comlogic_medicine_code
	
	@GraphQLQuery
	@Column(name = "comlogic_medicine_generic_code")
	String comlogic_medicine_generic_code
	
	@GraphQLQuery
	@Column(name = "created_by")
	String created_by
	
	@GraphQLQuery
	@Column(name = "created_date", nullable = true)
	LocalDateTime created_date
	
	@GraphQLQuery
	@Column(name = "last_modified_by")
	String last_modified_by
	
	@GraphQLQuery
	@Column(name = "last_modified_date", nullable = true)
	LocalDateTime last_modified_date
}
