package com.hisd3.hismk2.domain.pms

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.Formula
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

import javax.persistence.*
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
	@Column(name = "first_name", columnDefinition = "varchar")
	String firstName
	
	@GraphQLQuery
	@Column(name = "last_name", columnDefinition = "varchar")
	String lastName
	
	@GraphQLQuery
	@Column(name = "middle_name", columnDefinition = "varchar")
	String middleName
	
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
	@Column(name = "civil_status", columnDefinition = "varchar")
	String civilStatus
	
	@GraphQLQuery
	@Column(name = "citizenship", columnDefinition = "varchar")
	String citizenship
	
	@GraphQLQuery
	@Column(name = "name_of_spouse", columnDefinition = "varchar")
	String nameOfSpouse
	
	@GraphQLQuery
	@Formula("concat(last_name , coalesce(', ' || nullif(first_name,'') , ''), coalesce(' ' || nullif(middle_name,'') , ''), coalesce(' ' || nullif(name_suffix,'') , ''))")
	String fullName
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	Set<Case> patientCases = [] as Set
	
}
