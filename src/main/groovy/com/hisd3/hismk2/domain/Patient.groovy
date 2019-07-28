package com.hisd3.hismk2.domain

import groovy.transform.ToString
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.Formula
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(schema = "pms", name = "patients")
class Patient extends AbstractAuditingEntity {

    @GraphQLQuery
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id

    @GraphQLQuery
    @Column(name = "patient_no", columnDefinition = "varchar")
    String patientNo

    @GraphQLQuery
    @Column(name = "firstname", columnDefinition = "varchar")
    String firstname

    @GraphQLQuery
    @Column(name = "lastname", columnDefinition = "varchar")
    String lastname

    @GraphQLQuery
    @Column(name = "middlename", columnDefinition = "varchar")
    String middlename

    @GraphQLQuery
    @Column(name = "name_suffix", columnDefinition = "varchar")
    String nameSuffix

    @GraphQLQuery
    @Column(name = "address", columnDefinition = "varchar")
    String address

    @GraphQLQuery
    @Column(name = "country", columnDefinition = "varchar")
    String country

    @GraphQLQuery
    @Column(name = "state_province", columnDefinition = "varchar")
    String stateProvince

    @GraphQLQuery
    @Column(name = "city_municipality", columnDefinition = "varchar")
    String cityMunicipality

    @GraphQLQuery
    @Column(name = "barangay", columnDefinition = "varchar")
    String barangay

    @GraphQLQuery
    @Column(name = "gender", columnDefinition = "varchar")
    String gender


    @GraphQLQuery
    @Column(name = "dob", columnDefinition = "date")
    LocalDateTime dob

    @GraphQLQuery
    @Column(name = "allergies", columnDefinition = "varchar")
    String allergies


    @GraphQLQuery
    @Column(name = "father", columnDefinition = "varchar")
    String father


    @GraphQLQuery
    @Column(name = "mother", columnDefinition = "varchar")
    String mother

    @GraphQLQuery
    @Column(name = "father_occupation", columnDefinition = "varchar")
    String fatherOccupation


    @GraphQLQuery
    @Column(name = "mother_occupation", columnDefinition = "varchar")
    String motherOccupation

    @GraphQLQuery
    @Column(name = "emergency_contact_name", columnDefinition = "varchar")
    String emergencyContactName

    @GraphQLQuery
    @Column(name = "emergency_contact_address", columnDefinition = "varchar")
    String emergencyContactAddress

    @Column(name = "emergency_contact_relationship", columnDefinition = "varchar")
    String emergencyContactRelationship


    @GraphQLQuery
    @Column(name = "emegency_contact_no", columnDefinition = "varchar")
    String emegencyContactNo

    @GraphQLQuery
    @Column(name = "guarantor_name", columnDefinition = "varchar")
    String guarantorName

    @GraphQLQuery
    @Column(name = "guarantor_address", columnDefinition = "varchar")
    String guarantorAddress

    @GraphQLQuery
    @Column(name = "guarantor_relationship", columnDefinition = "varchar")
    String guarantorRelationship

    @GraphQLQuery
    @Column(name = "guarantor_contact_no", columnDefinition = "varchar")
    String guarantorContactNo

    @GraphQLQuery
    @Formula("concat(lastname , coalesce(', ' || nullif(firstname,'') , ''), coalesce(' ' || nullif(middlename,'') , ''), coalesce(' ' || nullif(name_suffix,'') , ''))")
    String fullname


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    Set<PatientCase> patientCases = [] as Set



}
