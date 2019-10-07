package com.hisd3.hismk2.domain.inventory

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(schema = "inventory", name = "item")
class PurchaseOrderItems extends AbstractAuditingEntity {
    @GraphQLQuery
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id

    @GraphQLQuery
    @Column(name = "item", columnDefinition = "uuid")
    UUID item

    @GraphQLQuery
    @Column(name = "quantity", columnDefinition = "numeric")
    Integer quantity

    @GraphQLQuery
    @Column(name = "unit_measure", columnDefinition = "varchar")
    Integer unitMeasure

    @GraphQLQuery
    @Column(name = "date_completed", columnDefinition = "timestamp")
    LocalDateTime dateCompleted

    @GraphQLQuery
    @Column(name = "purchase_order", columnDefinition = "uuid")
    UUID purchaseOrder

    @GraphQLQuery
    @Column(name = "receiving_report", columnDefinition = "uuid")
    UUID receivingReport

    @GraphQLQuery
    @Column(name = "supplier_last_price", columnDefinition = "numeric")
    Integer supplierLastPrice
}
