package com.hisd3.hismk2.domain

import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(schema = "pms", name = "patient_cases")
class PatientCase extends AbstractAuditingEntity {
	
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
	@Column(name = "service_type", columnDefinition = "varchar")
	String serviceType
	
	@GraphQLQuery
	@Column(name = "accomodation_type", columnDefinition = "varchar")
	String accomodationType
	
	@GraphQLQuery
	@Column(name = "registry_type", columnDefinition = "varchar")
	String registryType
	
	@GraphQLQuery
	@Column(name = "entry_datetime", columnDefinition = "timestamp")
	LocalDateTime entryDatetime
	
	@GraphQLQuery
	@Column(name = "discharged_datetime", columnDefinition = "timestamp")
	LocalDateTime discharged_datetime
	
	@GraphQLQuery
	@Column(name = "maygohome_datetime", columnDefinition = "timestamp")
	LocalDateTime maygohome_datetime
	
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
	@Column(name = "pertinent_medical_history", columnDefinition = "varchar")
	String pertinentMedicalHistory
	
	@GraphQLQuery
	@Column(name = "preop_diagnosis", columnDefinition = "varchar")
	String preopDiagnosis
	
	@GraphQLQuery
	@Column(name = "postop_diagnosis", columnDefinition = "varchar")
	String postopDiagnosis
	
	@GraphQLQuery
	@Column(name = "surgical_procedure", columnDefinition = "varchar")
	String surgicalProcedure
	
	@GraphQLQuery
	@Column(name = "case_informant", columnDefinition = "varchar")
	String caseInformant
	
	@GraphQLQuery
	@Column(name = "case_informant_relation", columnDefinition = "varchar")
	String caseInformantRelation
	
	@GraphQLQuery
	@Column(name = "case_informant_address", columnDefinition = "varchar")
	String caseInformantAddress
}
