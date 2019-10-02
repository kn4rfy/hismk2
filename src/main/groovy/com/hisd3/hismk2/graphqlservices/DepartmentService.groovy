package com.hisd3.hismk2.graphqlservices

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.repository.DepartmentRepository
import com.hisd3.hismk2.services.GeneratorService
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@TypeChecked
@Component
@GraphQLApi
class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	ObjectMapper objectMapper
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "departments", description = "Get All Departments")
	List<Department> findAll() {
		departmentRepository.findAll()
	}
	
	@GraphQLQuery(name = "searchDepartments", description = "Search departments")
	List<Department> searchDepartments(@GraphQLArgument(name = "filter") String filter) {
		departmentRepository.searchDepartments(filter)
	}
	
	@GraphQLQuery(name = "department", description = "Get Department By Id")
	Department findById(@GraphQLArgument(name = "id") UUID id) {
		return departmentRepository.findById(id).get()
	}
	
	//============== All Mutations ====================
	
	@GraphQLMutation
	Department upsertDepartment(
			@GraphQLArgument(name = "id") UUID id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		
		if (id) {
			def department = departmentRepository.findById(id).get()
			objectMapper.updateValue(department, fields)
			
			def deptId = fields["parentDepartmentId"]
			
			if (deptId) {
				Department dept = departmentRepository.findById(deptId as UUID).get()
				department.parentDepartment = dept
			}
			
			return departmentRepository.save(department)
		} else {
			def department = objectMapper.convertValue(fields, Department)
			
			def deptId = fields["parentDepartmentId"]
			
			if (deptId) {
				Department dept = departmentRepository.findById(deptId as UUID).get()
				department.parentDepartment = dept
			}
			
			return departmentRepository.save(department)
		}
	}
}