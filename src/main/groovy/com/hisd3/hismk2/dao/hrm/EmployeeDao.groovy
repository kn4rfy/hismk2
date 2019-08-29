package com.hisd3.hismk2.dao.hrm

import com.hisd3.hismk2.domain.hrm.Employee
import com.hisd3.hismk2.repository.hrm.EmployeeRepository
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@TypeChecked
@Service
@Transactional
class EmployeeDao {
	
	@Autowired
	private EmployeeRepository employeeRepository
	
	@PersistenceContext
	EntityManager entityManager
	
	List<Employee> findAll() {
		return employeeRepository.findAll()
	}
	
	List<Employee> searchEmployees(String filter) {
		return employeeRepository.searchEmployees(filter)
	}
	
	Employee findById(String id) {
		return employeeRepository.findById(UUID.fromString(id)).get()
	}
	
	Employee save(Employee employee) {
		employeeRepository.save(employee)
	}
}