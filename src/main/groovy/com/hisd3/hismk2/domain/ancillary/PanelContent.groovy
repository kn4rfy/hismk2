package com.hisd3.hismk2.domain.ancillary

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.Transient
import java.time.Instant

@TypeChecked
@Entity
@Table(schema = "ancillary", name = "panel_content")
class PanelContent extends AbstractAuditingEntity {

    @GraphQLQuery
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent", referencedColumnName = "id")
    Service parent

    @GraphQLQuery
    @Column(name = "process_code", columnDefinition = "varchar")
    String processCode

    @GraphQLQuery
    @Column(name = "servicename", columnDefinition = "varchar")
    String serviceName

    @GraphQLQuery
    @Column(name = "deleted", columnDefinition = "boolean")
    Boolean deleted

    @Transient
    Instant getCreated() {
        return createdDate
    }
}
