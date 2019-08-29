package com.hisd3.hismk2.domain.billing

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.springframework.data.annotation.CreatedDate

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import java.time.Instant


@Entity
@Table(name = "billing", schema = "billing")
class Billing extends AbstractAuditingEntity {
    @GraphQLQuery
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id

    @GraphQLQuery
    @CreatedDate
    @Column(name = "entry_datetime", nullable = false)
    Instant entryDatetime

    @GraphQLQuery
    @Column(name = "billing_no", columnDefinition = "varchar")
    String billingNo

    @GraphQLQuery
    @Column(name = "status", columnDefinition = "varchar")
    String status


}