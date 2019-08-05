package com.hisd3.hismk2.domain.inventory

import com.hisd3.hismk2.domain.pms.NurseNote
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.hibernate.type.OneToOneType

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.Size
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(schema = "inventory", name = "receiving_report")
class ReceivingReport {

    @GraphQLQuery
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id

    @Column(name = "supplier")
    String supplier

    @Column(name = "ref_no")
    String refNo

    @Column(name = "receiving_department")
    String receivingDepartment

    @Column(name = "ref_qlty")
    String refQlty

    @Column(name = "qlty_inspection_date",nullable = true)
    LocalDateTime qltyInspectionDate

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receivingReport")
    Set<ReceivingReportItem> items = [] as Set
}
