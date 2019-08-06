package com.hisd3.hismk2.dao.hrm

import com.hisd3.hismk2.domain.hrm.Employee
import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.Patient
import com.hisd3.hismk2.repository.hrm.EmployeeRepository
import com.hisd3.hismk2.repository.pms.PatientRepository
import com.hisd3.hismk2.utils.OffsetBasedPageRequest
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

	Employee findById(String id) {
		return employeeRepository.findById(UUID.fromString(id)).get()
	}
	
	Page<Employee> getEmployeeRelayPage(int first, int offset) {
		def pageable = employeeRepository.findAll(new OffsetBasedPageRequest(offset, first, new Sort(Sort.Direction.ASC, "employeeNo")))
		
		PageFactory.createOffsetBasedPage(pageable.content, pageable.totalElements, offset)
		
	}

	Employee save(Employee employee) {
		employeeRepository.save(employee)
	}
	
}
