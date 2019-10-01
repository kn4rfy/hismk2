package com.hisd3.hismk2.domain.inventory


import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.*

@Entity
@Table(schema = "inventory", name = "supplier")
class Supplier extends AbstractAuditingEntity {

    @GraphQLQuery
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id

    @GraphQLQuery
    @Column(name = "supplier_name")
    String supplierName

    @GraphQLQuery
    @Column(name = "description")
    String description

    @GraphQLQuery
    @Column(name = "address")
    String address

    @GraphQLQuery
    @Column(name = "contact_person")
    String contactPerson


    @GraphQLQuery
    @Column(name = "contact_no")
    String contactNo


    @GraphQLQuery
    @Column(name = "email")
    String email


    @GraphQLQuery
    @Column(name = "supplier_code")
    String supplierCode


}
