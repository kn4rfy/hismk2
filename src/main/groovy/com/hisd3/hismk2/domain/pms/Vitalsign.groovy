package com.hisd3.hismk2.domain.pms

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(schema = "pms", name = "patient_vital_signs")
class Vitalsign extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_case", referencedColumnName = "id")
	PatientCase patientCase
	
	@GraphQLQuery
	@Column(name = "systolic", columnDefinition = "varchar")
	String systolic
	
	@GraphQLQuery
	@Column(name = "diastolic", columnDefinition = "varchar")
	String diastolic
	
	@GraphQLQuery
	@Column(name = "temperature", columnDefinition = "varchar")
	String temperature
	
	@GraphQLQuery
	@Column(name = "pulse_rate", columnDefinition = "varchar")
	String pulseRate
	
	@GraphQLQuery
	@Column(name = "respiratory_rate", columnDefinition = "varchar")
	String respiratoryRate
	
	@GraphQLQuery
	@Column(name = "oxygen_saturation", columnDefinition = "varchar")
	String oxygenSaturation
	
	@GraphQLQuery
	@Column(name = "entry_datetime", columnDefinition = "timestamp")
	LocalDateTime entryDateTime
}
