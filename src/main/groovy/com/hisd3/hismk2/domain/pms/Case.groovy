package com.hisd3.hismk2.domain.pms

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.bms.Room
import com.hisd3.hismk2.domain.hrm.Employee
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*
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
	LocalDateTime entryDateTime
	
	@GraphQLQuery
	@Column(name = "discharged_datetime", columnDefinition = "timestamp")
	LocalDateTime dischargedDateTime
	
	@GraphQLQuery
	@Column(name = "may_go_home_datetime", columnDefinition = "timestamp")
	LocalDateTime mayGoHomeDateTime
	
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
	String surgicalProcedure
	
	@GraphQLQuery
	@Column(name = "informant", columnDefinition = "varchar")
	String informant
	
	@GraphQLQuery
	@Column(name = "informant_relation", columnDefinition = "varchar")
	String informantRelation
	
	@GraphQLQuery
	@Column(name = "informant_address", columnDefinition = "varchar")
	String informantAddress
	
	@GraphQLQuery
	@Column(name = "admission_date", columnDefinition = "timestamp")
	LocalDateTime admissionDate
	
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCase")
	Set<NurseNote> caseNurseNotes = [] as Set
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCase")
	Set<VitalSign> caseVitalSigns = [] as Set
}
