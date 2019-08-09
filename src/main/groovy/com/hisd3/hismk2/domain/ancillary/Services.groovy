package com.hisd3.hismk2.domain.ancillary

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.User
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*

@Entity
@Table(schema = "ancillary", name = "services")
class Services extends AbstractAuditingEntity {

    @GraphQLQuery
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "revenuecenter", referencedColumnName = "id")
    RevenueCenter revenueCenter

    @Column(name = "department", columnDefinition = "uuid")
    UUID department

    @GraphQLQuery
    @Column(name = "servicename", columnDefinition = "varchar")
    String service_name

    @GraphQLQuery
    @Column(name = "service_code", columnDefinition = "varchar")
    String service_code

    @GraphQLQuery
    @Column(name = "description", columnDefinition = "varchar")
    String description

    @GraphQLQuery
    @Column(name = "category", columnDefinition = "varchar")
    String category

    @GraphQLQuery
    @Column(name = "notes", columnDefinition = "varchar")
    String notes

    @GraphQLQuery
    @Column(name = "base_price", columnDefinition = "numeric")
    Number base_price

    @GraphQLQuery
    @Column(name = "cost", columnDefinition = "numeric")
    Number cost

    @GraphQLQuery
    @Column(name = "readersfee", columnDefinition = "numeric")
    Number readers_fee

}
