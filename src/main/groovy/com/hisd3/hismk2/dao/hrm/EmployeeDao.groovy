package com.hisd3.hismk2.dao.hrm

import com.hisd3.hismk2.domain.hrm.Employee
import com.hisd3.hismk2.domain.pms.Patient
import com.hisd3.hismk2.repository.hrm.EmployeeRepository
import com.hisd3.hismk2.utils.OffsetBasedPageRequest
import graphql.language.IntValue
import io.leangen.graphql.execution.relay.Page
import io.leangen.graphql.execution.relay.generic.PageFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

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

	Page<Employee> getEmployeesByPage(String filter, int first, int offset) {
		def pageable = employeeRepository.searchEmployees(filter, new OffsetBasedPageRequest(offset, first, new Sort(Sort.Direction.ASC, "employeeNo")))

		PageFactory.createOffsetBasedPage(pageable.content, pageable.totalElements, offset)
	}
	
	Employee findById(String id) {
		return employeeRepository.findById(UUID.fromString(id)).get()
	}
	
	Employee save(Employee employee) {
		employeeRepository.save(employee)
	}
}