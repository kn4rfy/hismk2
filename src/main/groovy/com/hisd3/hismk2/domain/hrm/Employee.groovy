package com.hisd3.hismk2.domain.hrm

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.User
import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.Patient
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.Formula
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(schema = "hrm", name = "employees")
class Employee extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`user`", referencedColumnName = "id")
	User user

	@GraphQLQuery
	@Column(name = "employee_no", columnDefinition = "varchar")
	String employeeNo
	
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
	@Column(name = "emergency_contact_name", columnDefinition = "varchar")
	String emergencyContactName
	
	@GraphQLQuery
	@Column(name = "emergency_contact_address", columnDefinition = "varchar")
	String emergencyContactAddress
	
	@Column(name = "emergency_contact_relationship", columnDefinition = "varchar")
	String emergencyContactRelationship
	
	@GraphQLQuery
	@Column(name = "emergency_contact_no", columnDefinition = "varchar")
	String emergencyContactNo
	
	@GraphQLQuery
	@Formula("concat(last_name , coalesce(', ' || nullif(first_name,'') , ''), coalesce(' ' || nullif(middle_name,'') , ''), coalesce(' ' || nullif(name_suffix,'') , ''))")
	String fullName
	
}
