package com.hisd3.hismk2.graphqlservices.hrm

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.UserDao
import com.hisd3.hismk2.dao.hrm.EmployeeDao
import com.hisd3.hismk2.domain.User
import com.hisd3.hismk2.domain.hrm.Employee
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao
	
	@Autowired
	UserDao userDao
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	ObjectMapper objectMapper
	
	@Autowired
	PasswordEncoder passwordEncoder
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "employees", description = "Get All Employees")
	Set<Employee> findAll() {
		employeeDao.findAll()
	}
	
	@GraphQLQuery(name = "searchEmployees", description = "Search employees")
	List<Employee> searchEmployees(@GraphQLArgument(name = "filter") String filter) {
		employeeDao.searchEmployees(filter)
	}
	
	@GraphQLQuery(name = "employee", description = "Get Employee By Id")
	Employee findById(@GraphQLArgument(name = "id") String id) {
		
		return employeeDao.findById(id)
	}
	
	//============== All Mutations ====================
	
	@GraphQLMutation
	Employee upsertEmployee(
			@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		if (id) {
			def employee = employeeDao.findById(id)
			objectMapper.updateValue(employee, fields)
			
			return employeeDao.save(employee)
		} else {
			def employee = objectMapper.convertValue(fields, Employee)
			
			User user = new User()
			user.login = fields["login"].toString().toLowerCase()
			user.password = passwordEncoder?.encode(fields["password"] as String)
			user.firstName = fields["firstName"]
			user.lastName = fields["lastName"]
			user.email = fields["login"].toString().toLowerCase() + "@hismkii.com"
			user.activated = true
			user.langKey = "en"
			userDao.save(user)
			
			employee.user = user
			
			employee.employeeNo = generatorService.getNextValue(GeneratorType.PATIENT_NO) { Long no ->
				StringUtils.leftPad(no.toString(), 6, "0")
			}
			
			return employeeDao.save(employee)
		}
	}
}
