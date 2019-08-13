package com.hisd3.hismk2.graphqlservices.hrm

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.DepartmentDao
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.services.GeneratorService
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class DepartmentService {
	
	@Autowired
	DepartmentDao departmentDao
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	ObjectMapper objectMapper
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "departments", description = "Get All Departments")
	Set<Department> findAll() {
		departmentDao.findAll()
	}
	
	@GraphQLQuery(name = "searchDepartments", description = "Search departments")
	List<Department> searchDepartments(@GraphQLArgument(name = "filter") String filter) {
		departmentDao.searchDepartments(filter)
	}
	
	@GraphQLQuery(name = "department", description = "Get Department By Id")
	Department findById(@GraphQLArgument(name = "id") String id) {
		
		return departmentDao.findById(id)
	}
	
	//============== All Mutations ====================
	
	@GraphQLMutation
	Department upsertDepartment(
			@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		if (id) {
			def department = departmentDao.findById(id)
			objectMapper.updateValue(department, fields)
			
			department.costCenter = false
			department.revenueCenter = false
			department.specialArea = false
			department.subDepartment = false
			
			return departmentDao.save(department)
		} else {
			def department = objectMapper.convertValue(fields, Department)
			
			department.costCenter = false
			department.revenueCenter = false
			department.specialArea = false
			department.subDepartment = false
			
			return departmentDao.save(department)
		}
	}
}