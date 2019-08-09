package com.hisd3.hismk2.repository.hrm

import com.hisd3.hismk2.domain.hrm.Employee
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface EmployeeRepository extends JpaRepository<Employee, UUID> {
	
	@Query(
			value = "Select e from Employee e where lower(e.fullName) like concat('%',:filter,'%')",
			countQuery = "Select count(e) from Employee e where lower(e.fullName) like concat('%',:filter,'%')"
	)
	Page<Employee> getEmployees(@Param("filter") String filter, Pageable pageable)

	@Query(
			value = "Select e from Employee e where lower(e.fullName) like concat('%',:filter,'%')",
			countQuery = "Select count(e) from Employee e where lower(e.fullName) like concat('%',:filter,'%')"
	)
	List<Employee> searchEmployees(@Param("filter") String filter)
}