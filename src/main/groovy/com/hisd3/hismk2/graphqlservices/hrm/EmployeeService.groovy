package com.hisd3.hismk2.graphqlservices.hrm

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.domain.User
import com.hisd3.hismk2.domain.hrm.Employee
import com.hisd3.hismk2.repository.UserRepository
import com.hisd3.hismk2.repository.hrm.EmployeeRepository
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@TypeChecked
@Component
@GraphQLApi
class EmployeeService {
	
	@Autowired
	private UserRepository userRepository
	
	@Autowired
	private EmployeeRepository employeeRepository
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	ObjectMapper objectMapper
	
	@Autowired
	PasswordEncoder passwordEncoder
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "employees", description = "Get All Employees")
	List<Employee> findAll() {
		employeeRepository.findAll().sort { it.lastName }
	}
	
	@GraphQLQuery(name = "searchEmployees", description = "Search employees")
	List<Employee> searchEmployees(@GraphQLArgument(name = "filter") String filter) {
		employeeRepository.searchEmployees(filter).sort { it.lastName }
	}
	
	@GraphQLQuery(name = "employee", description = "Get Employee By Id")
	Employee findById(@GraphQLArgument(name = "id") UUID id) {
		
		return id ? employeeRepository.findById(id).get() : null
	}
	
	//============== All Mutations ====================
	
	@GraphQLMutation
	Employee upsertEmployee(
			@GraphQLArgument(name = "id") UUID id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		if (id) {
			Employee employee = employeeRepository.findById(id).get()
			objectMapper.updateValue(employee, fields)
			
			return employeeRepository.save(employee)
		} else {
			Employee employee = objectMapper.convertValue(fields, Employee)
			
			User user = new User()
			user.login = fields["login"].toString().toLowerCase()
			user.password = passwordEncoder?.encode(fields["password"] as String)
			user.firstName = fields["firstName"]
			user.lastName = fields["lastName"]
			user.email = fields["login"].toString().toLowerCase() + "@hismkii.com"
			user.activated = true
			user.langKey = "en"
			userRepository.save(user)
			
			employee.user = user
			
			employee.employeeNo = generatorService.getNextValue(GeneratorType.PATIENT_NO) { Long no ->
				StringUtils.leftPad(no.toString(), 6, "0")
			}
			
			return employeeRepository.save(employee)
		}
	}
}
