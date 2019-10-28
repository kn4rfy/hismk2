package com.hisd3.hismk2.domain.pms

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.bms.Room
import com.hisd3.hismk2.domain.hrm.Employee
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.Instant
import java.time.LocalDateTime

@TypeChecked
@Entity
@Table(schema = "pms", name = "cases")
class Case extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient", referencedColumnName = "id")
	Patient patient
	
	@GraphQLQuery
	@Column(name = "case_no", columnDefinition = "varchar")
	String caseNo
	
	@GraphQLQuery
	@Column(name = "status", columnDefinition = "varchar")
	String status
	
	@PrePersist
	void preInsert() {
		if (this.status == null)
			this.status = 'ACTIVE'
	}
	
	@GraphQLQuery
	@Column(name = "service_type", columnDefinition = "varchar")
	String serviceType
	
	@GraphQLQuery
	@Column(name = "accommodation_type", columnDefinition = "varchar")
	String accommodationType
	
	@GraphQLQuery
	@Column(name = "registry_type", columnDefinition = "varchar")
	String registryType
	
	@GraphQLQuery
	@Column(name = "entry_datetime", columnDefinition = "timestamp")
	Instant entryDateTime
	
	@GraphQLQuery
	@Column(name = "admission_datetime", columnDefinition = "timestamp")
	LocalDateTime admissionDatetime
	
	@GraphQLQuery
	@Column(name = "discharged_datetime", columnDefinition = "timestamp")
	LocalDateTime dischargedDatetime
	
	@GraphQLQuery
	@Column(name = "may_go_home_datetime", columnDefinition = "timestamp")
	LocalDateTime mayGoHomeDatetime
	
	@GraphQLQuery
	@Column(name = "chief_complaint", columnDefinition = "varchar")
	String chiefComplaint
	
	@GraphQLQuery
	@Column(name = "admitting_diagnosis", columnDefinition = "varchar")
	String admittingDiagnosis
	
	@GraphQLQuery
	@Column(name = "discharge_diagnosis", columnDefinition = "varchar")
	String dischargeDiagnosis
	
	@GraphQLQuery
	@Column(name = "history_present_illness", columnDefinition = "varchar")
	String historyPresentIllness
	
	@GraphQLQuery
	@Column(name = "past_medical_history", columnDefinition = "varchar")
	String pastMedicalHistory
	
	@GraphQLQuery
	@Column(name = "pre_op_diagnosis", columnDefinition = "varchar")
	String preOpDiagnosis
	
	@GraphQLQuery
	@Column(name = "post_op_diagnosis", columnDefinition = "varchar")
	String postOpDiagnosis
	
	@GraphQLQuery
	@Column(name = "surgical_procedure", columnDefinition = "varchar")
	String proceduresPerformed
	
	@GraphQLQuery
	@Column(name = "informant", columnDefinition = "varchar")
	String informant
	
	@GraphQLQuery
	@Column(name = "informant_address", columnDefinition = "varchar")
	String informantAddress
	
	@GraphQLQuery
	@Column(name = "informant_relation", columnDefinition = "varchar")
	String informantRelation
	
	@GraphQLQuery
	@Column(name = "informant_contact", columnDefinition = "varchar")
	String informantContact
	
	@GraphQLQuery
	@Column(name = "occupation", columnDefinition = "varchar")
	String occupation
	
	@GraphQLQuery
	@Column(name = "company_name", columnDefinition = "varchar")
	String companyName
	
	@GraphQLQuery
	@Column(name = "company_address", columnDefinition = "varchar")
	String companyAddress
	
	@GraphQLQuery
	@Column(name = "company_contact", columnDefinition = "varchar")
	String companyContact
	
	@GraphQLQuery
	@Column(name = "emergency_contact_name", columnDefinition = "varchar")
	String emergencyContactName
	
	@GraphQLQuery
	@Column(name = "emergency_contact_address", columnDefinition = "varchar")
	String emergencyContactAddress
	
	@GraphQLQuery
	@Column(name = "emergency_contact_relation", columnDefinition = "varchar")
	String emergencyContactRelation
	
	@GraphQLQuery
	@Column(name = "emergency_contact", columnDefinition = "varchar")
	String emergencyContact
	
	@GraphQLQuery
	@Column(name = "guarantor_name", columnDefinition = "varchar")
	String guarantorName
	
	@GraphQLQuery
	@Column(name = "guarantor_address", columnDefinition = "varchar")
	String guarantorAddress
	
	@GraphQLQuery
	@Column(name = "guarantor_relation", columnDefinition = "varchar")
	String guarantorRelation
	
	@GraphQLQuery
	@Column(name = "guarantor_contact", columnDefinition = "varchar")
	String guarantorContact
	
	@GraphQLQuery
	@Column(name = "history_input_datetime", columnDefinition = "timestamp")
	LocalDateTime historyInputDatetime
	
	@GraphQLQuery
	@Column(name = "triage", columnDefinition = "varchar")
	String triage
	
	@GraphQLQuery
	@Column(name = "height", columnDefinition = "varchar")
	String height
	
	@GraphQLQuery
	@Column(name = "weight", columnDefinition = "varchar")
	String weight
	
	@GraphQLQuery
	@Column(name = "initial_bp", columnDefinition = "varchar")
	String initialBp
	
	@GraphQLQuery
	@Column(name = "initial_temperature", columnDefinition = "varchar")
	String initialTemperature
	
	@GraphQLQuery
	@Column(name = "initial_pulse", columnDefinition = "varchar")
	String initialPulse
	
	@GraphQLQuery
	@Column(name = "initial_resp", columnDefinition = "varchar")
	String initialResp
	
	@GraphQLQuery
	@Column(name = "initial_o2sat", columnDefinition = "varchar")
	String initialO2sat
	
	@GraphQLQuery
	@Column(name = "followup_datetime", columnDefinition = "timestamp")
	LocalDateTime followupDatetime
	
	@GraphQLQuery
	@Column(name = "disposition", columnDefinition = "varchar")
	String disposition
	
	@GraphQLQuery
	@Column(name = "home_medication", columnDefinition = "varchar")
	String homeMedication
	
	@GraphQLQuery
	@Column(name = "special_instructions", columnDefinition = "varchar")
	String specialInstructions
	
	@GraphQLQuery
	@Column(name = "lacerated_wound", columnDefinition = "varchar")
	String laceratedWound
	
	@GraphQLQuery
	@Column(name = "head_injury", columnDefinition = "varchar")
	String headInjury
	
	@GraphQLQuery
	@Column(name = "pertinent_past_medical_history", columnDefinition = "varchar")
	String pertinentPastMedicalHistory
	
	@GraphQLQuery
	@Column(name = "previous_admission", columnDefinition = "varchar")
	String previousAdmission
	
	@GraphQLQuery
	@Column(name = "how_taken_to_room", columnDefinition = "varchar")
	String howTakenToRoom
	
	@GraphQLQuery
	@Column(name = "transferred_in", columnDefinition = "boolean")
	Boolean transferredIn
	
	@GraphQLQuery
	@Column(name = "reason_for_transfer_in", columnDefinition = "varchar")
	String reasonForTransferIn
	
	@GraphQLQuery
	@Column(name = "originating_hci", columnDefinition = "varchar")
	String originatingHci
	
	@GraphQLQuery
	@Column(name = "transferred_out", columnDefinition = "boolean")
	Boolean transferredOut
	
	@GraphQLQuery
	@Column(name = "reason_for_transfer_out", columnDefinition = "varchar")
	String reasonForTransferOut
	
	@GraphQLQuery
	@Column(name = "transfer_hci", columnDefinition = "varchar")
	String transferHci
	
	@GraphQLQuery
	@Column(name = "icd_diagnosis", columnDefinition = "varchar")
	String icdDiagnosis
	
	@GraphQLQuery
	@Column(name = "rvs_diagnosis", columnDefinition = "varchar")
	String rvsDiagnosis

	@GraphQLQuery
	@Column(name = "primary_dx", columnDefinition = "varchar")
	String primaryDx

	@GraphQLQuery
	@Column(name = "secondary_dx", columnDefinition = "varchar")
	String secondaryDx
	
	@GraphQLQuery
	@Column(name = "physical_exam_list", columnDefinition = "varchar")
	String physicalExamList
	
	@GraphQLQuery
	@Column(name = "pertinent_symptoms_list", columnDefinition = "varchar")
	String pertinentSymptomsList
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attending_physician", referencedColumnName = "id")
	Employee attendingPhysician
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admitting_physician", referencedColumnName = "id")
	Employee admittingPhysician
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room", referencedColumnName = "id")
	Room room
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department", referencedColumnName = "id")
	Department department
}
