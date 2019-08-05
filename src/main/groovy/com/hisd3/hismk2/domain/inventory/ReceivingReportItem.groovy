package com.hisd3.hismk2.domain.inventory

import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(schema = "inventory", name = "receiving_report_items")
class ReceivingReportItem {

    @GraphQLQuery
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id

    @Column(name = "receiving_report")
    String receivingReport

    @Column(name = "barcode")
    String barcode

    @Column(name = "description")
    String description

    @Column(name = "ref_no")
    String refNo

    @Column(name = "po_qty")
    Number poQty

    @Column(name = "qty_delivered")
    Number qtyDelivered

    @Column(name = "po_balance_qty")
    Number poBalanceQty

    @Column(name = "amt")
    Number amt


}
