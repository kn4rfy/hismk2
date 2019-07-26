package com.hisd3.hismk2.domain

import org.hibernate.annotations.Formula
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(schema = "pms", name = "patients")
class Patient extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    UUID id


    @Column(name = "patient_no", columnDefinition = "varchar")
    String patientNo

    @Column(name = "firstname", columnDefinition = "varchar")
    String firstname

    @Column(name = "lastname", columnDefinition = "varchar")
    String lastname

    @Column(name = "middlename", columnDefinition = "varchar")
    String middlename


    @Column(name = "name_suffix", columnDefinition = "varchar")
    String nameSuffix

    @Column(name = "address", columnDefinition = "varchar")
    String address

    @Column(name = "country", columnDefinition = "varchar")
    String country

    @Column(name = "state_province", columnDefinition = "varchar")
    String stateProvince

    @Column(name = "city_municipality", columnDefinition = "varchar")
    String cityMunicipality

    @Column(name = "barangay", columnDefinition = "varchar")
    String barangay

    @Column(name = "gender", columnDefinition = "varchar")
    String gender


    @Column(name = "dob", columnDefinition = "date")
    LocalDateTime dob

    @Column(name = "allergies", columnDefinition = "varchar")
    String allergies

    @Column(name = "father", columnDefinition = "varchar")
    String father


    @Column(name = "mother", columnDefinition = "varchar")
    String mother

    @Column(name = "father_occupation", columnDefinition = "varchar")
    String fatherOccupation

    @Column(name = "mother_occupation", columnDefinition = "varchar")
    String motherOccupation


    @Column(name = "emergency_contact_name", columnDefinition = "varchar")
    String emergencyContactName

    @Column(name = "emergency_contact_address", columnDefinition = "varchar")
    String emergencyContactAddress

    @Column(name = "emergency_contact_relationship", columnDefinition = "varchar")
    String emergencyContactRelationship

    @Column(name = "emegency_contact_no", columnDefinition = "varchar")
    String emegencyContactNo

    @Column(name = "guarantor_name", columnDefinition = "varchar")
    String guarantorName

    @Column(name = "guarantor_address", columnDefinition = "varchar")
    String guarantorAddress

    @Column(name = "guarantor_relationship", columnDefinition = "varchar")
    String guarantorRelationship

    @Column(name = "guarantor_contact_no", columnDefinition = "varchar")
    String guarantorContactNo

    @Formula("concat(lastname , coalesce(', ' || nullif(firstname,'') , ''), coalesce(' ' || nullif(middlename,'') , ''), coalesce(' ' || nullif(name_suffix,'') , ''))")
    String fullname



}
