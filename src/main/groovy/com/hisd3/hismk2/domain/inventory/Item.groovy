package com.hisd3.hismk2.domain.inventory


import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(schema = "inventory", name = "receiving_report")
class Item {

    @GraphQLQuery
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id

    @Column(name = "stock_code")
    String stock_code

    @Column(name = "desc_short")
    String desc_short

    @Column(name = "desc_long")
    String desc_long

    @Column(name = "unit_measure")
    String unit_measure

    @Column(name = "minimum_level")
    String minimum_level

    @Column(name = "economic_order_qty")
    BigDecimal economic_order_qty

    @Column(name = "active")
    Boolean active

    @Column(name = "stock_classification")
    String stock_classification

    @Column(name = "indication_code")
    BigDecimal indication_code

    @Column(name = "brand")
    String brand

    @Column(name = "generic_name")
    String generic_name

    @Column(name = "is_medicine")
    Boolean is_medicine

    @Column(name = "indication_desc")
    String indication_desc

    @Column(name = "medication_description")
    String medication_description

    @Column(name = "sku")
    String sku

    @Column(name = "route")
    String route

    @Column(name = "package_name")
    String package_name

    @Column(name = "package_content_count")
    BigDecimal package_content_count

    @Column(name = "dose")
    BigDecimal dose

    @Column(name = "dose_unit")
    BigDecimal dose_unit

    @Column(name = "s2_drug")
    Boolean s2_drug

    @Column(name = "form")
    String form

    @Column(name = "vatable")
    String vatable

    @Column(name = "preparation")
    String preparation

    @Column(name = "distributor")
    String distributor

    @Column(name = "advocate")
    String advocate

    @Column(name = "category")
    String category

    @Column(name = "sub_category")
    String sub_category

    @Column(name = "sub_sub_category")
    String sub_sub_category

    @Column(name = "formulary_item")
    String formulary_item

    @Column(name = "fluid")
    Boolean fluid

    @Column(name = "gas")
    Boolean gas

    @Column(name = "direct_asset")
    Boolean direct_asset

    @Column(name = "consignor")
    String consignor

    @Column(name = "consignment_item")
    Boolean consignment_item

    @Column(name = "consignor_name")
    String consignor_name

    @Column(name = "comlogic_medicine_code")
    String comlogic_medicine_code

    @Column(name = "comlogic_medicine_generic_code")
    String comlogic_medicine_generic_code

    @Column(name = "created_by")
    String created_by

    @Column(name = "created_date",nullable = true)
    LocalDateTime created_date

    @Column(name = "last_modified_by")
    String last_modified_by

    @Column(name = "last_modified_date",nullable = true)
    LocalDateTime last_modified_date
}
