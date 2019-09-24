package com.hisd3.hismk2.domain.ancillary

import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(schema = "ancillary", name = "integration_config")
class IntegrationConfig {
    @GraphQLQuery
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id

    @GraphQLQuery
    @Column(name = "nas_location", columnDefinition = "varchar")
    String nasLocation

    @GraphQLQuery
    @Column(name = "carestream_url", columnDefinition = "varchar")
    String carestreamUrl

    @GraphQLQuery
    @Column(name = "watched_directory", columnDefinition = "varchar")
    String watchDirectory

    @GraphQLQuery
    @Column(name = "middleware_ip", columnDefinition = "varchar")
    String middlewateIp

    @GraphQLQuery
    @Column(name = "adt_port", columnDefinition = "varchar")
    String adtPort

    @GraphQLQuery
    @Column(name = "orm_port", columnDefinition = "varchar")
    String ormPort

    @GraphQLQuery
    @Column(name = "demo_mode", columnDefinition = "bool")
    Boolean demoMode

    @GraphQLQuery
    @Column(name = "enable_integration", columnDefinition = "bool")
    Boolean enableIntegrtion

}
