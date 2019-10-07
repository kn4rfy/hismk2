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
@Table(schema = "inventory", name = "purchase_order_items")
class PurchaseOrder extends AbstractAuditingEntity {
    @GraphQLQuery
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id

    @GraphQLQuery
    @Column(name = "supplier", columnDefinition = "uuid")
    UUID supplier

    @GraphQLQuery
    @Column(name = "prepared_by", columnDefinition = "uuid")
    UUID preparedBy

    @GraphQLQuery
    @Column(name = "approved_by", columnDefinition = "uuid")
    UUID approvedBy

    @GraphQLQuery
    @Column(name = "reviewed_by", columnDefinition = "uuid")
    UUID reviewdBy

    @GraphQLQuery
    @Column(name = "sent_by", columnDefinition = "uuid")
    UUID sentBy

    @GraphQLQuery
    @Column(name = "sent_date", columnDefinition = "timestamp")
    LocalDateTime sentDate

    @GraphQLQuery
    @Column(name = "prepared_date", columnDefinition = "timestamp")
    LocalDateTime preparedDate

    @GraphQLQuery
    @Column(name = "approved_date", columnDefinition = "timestamp")
    LocalDateTime approvedDate

    @GraphQLQuery
    @Column(name = "reviewed_date", columnDefinition = "timestamp")
    LocalDateTime reviewedDate

    @GraphQLQuery
    @Column(name = "payment_terms", columnDefinition = "varchar")
    String paymentTerms

    @GraphQLQuery
    @Column(name = "delivery_terms", columnDefinition = "varchar")
    String deliveryTerms

    @GraphQLQuery
    @Column(name = "remarks", columnDefinition = "varchar")
    String remarks

    @GraphQLQuery
    @Column(name = "status", columnDefinition = "varchar")
    String status

    @GraphQLQuery
    @Column(name = "po_number", columnDefinition = "numeric")
    Integer poNumber

    @GraphQLQuery
    @Column(name = "prepared_by_signature", columnDefinition = "bytea")
    Byte[] preparedBySignature

    @GraphQLQuery
    @Column(name = "approved_by_2", columnDefinition = "uuid")
    UUID approvedBy2

    @GraphQLQuery
    @Column(name = "approved_by_2_date", columnDefinition = "timestamp")
    LocalDateTime approvedBy2Date

    @GraphQLQuery
    @Column(name = "approved_by_2_signature", columnDefinition = "bytea")
    Byte[] approvedBy2Signature

    @GraphQLQuery
    @Column(name = "approved_by_3", columnDefinition = "uuid")
    UUID approvedBy3

    @GraphQLQuery
    @Column(name = "approved_by_3_signature", columnDefinition = "bytea")
    Byte[] approvedBy3Signature

    @GraphQLQuery
    @Column(name = "approved_by_3_date", columnDefinition = "timestamp")
    LocalDateTime approvedBy3Date

    @GraphQLQuery
    @Column(name = "approved_by_4", columnDefinition = "uuid")
    UUID approvedBy4

    @GraphQLQuery
    @Column(name = "approved_by_4_signature", columnDefinition = "bytea")
    Byte[] approvedBy4Signature

    @GraphQLQuery
    @Column(name = "approved_by_4_date", columnDefinition = "timestamp")
    LocalDateTime approvedBy4Date

    @GraphQLQuery
    @Column(name = "revision_number", columnDefinition = "numeric")
    Integer revisionNumber

    @GraphQLQuery
    @Column(name = "revision_note", columnDefinition = "varchar")
    String revisionNote

    @GraphQLQuery
    @Column(name = "approved_by_position", columnDefinition = "varchar")
    String approvedByPosition

    @GraphQLQuery
    @Column(name = "approved_by_2_position", columnDefinition = "varchar")
    String approvedBy2Position

    @GraphQLQuery
    @Column(name = "approved_by_3_position", columnDefinition = "varchar")
    String approvedBy3Position

    @GraphQLQuery
    @Column(name = "approved_by_4_position", columnDefinition = "varchar")
    String approvedBy4Position

    @GraphQLQuery
    @Column(name = "po_sent_to_supplier", columnDefinition = "bool")
    Boolean poSentToSupplier

    @GraphQLQuery
    @Column(name = "po_sent_notes", columnDefinition = "varchar")
    String poSentNotes

    @GraphQLQuery
    @Column(name = "last_revision_by", columnDefinition = "uuid")
    UUID lastRevisionBy

    @GraphQLQuery
    @Column(name = "last_revision_date", columnDefinition = "timestamp")
    LocalDateTime lastRevisionDate

    @GraphQLQuery
    @Column(name = "revision_by_history", columnDefinition = "varchar")
    String revisionByHistory

    @GraphQLQuery
    @Column(name = "eta_date", columnDefinition = "timestamp")
    LocalDateTime etaDate



}