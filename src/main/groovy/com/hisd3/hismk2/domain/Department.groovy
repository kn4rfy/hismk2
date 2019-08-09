package com.hisd3.hismk2.domain

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.*

@Entity
@Table(schema = "public", name = "department")
class Department extends AbstractAuditingEntity {

    @GraphQLQuery
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id

    @GraphQLQuery
    @Column(name = "description", columnDefinition = "varchar")
    String description

    @GraphQLQuery
    @Column(name = "parent_department", columnDefinition = "uuid")
    String parent_department

    @GraphQLQuery
    @Column(name = "department_head", columnDefinition = "uuid")
    String department_head

    @GraphQLQuery
    @Column(name = "deprecated", columnDefinition = "varchar")
    String deprecated

    @GraphQLQuery
    @Column(name = "special_area", columnDefinition = "boolean")
    String special_area

    @GraphQLQuery
    @Column(name = "cost_center", columnDefinition = "boolean")
    String costCenter

    @GraphQLQuery
    @Column(name = "revenue_center", columnDefinition = "boolean")
    String revenueCenter

    @GraphQLQuery
    @Column(name = "sub_department", columnDefinition = "boolean")
    String subDepartment

    @GraphQLQuery
    @Column(name = "deleted", columnDefinition = "boolean")
    String deleted

}
