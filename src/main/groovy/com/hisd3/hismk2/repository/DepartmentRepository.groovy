package com.hisd3.hismk2.repository

import com.hisd3.hismk2.domain.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DepartmentRepository extends JpaRepository<Department, UUID> {
	
	@Query(value = '''Select d from Department d where
            lower(d.departmentName) like concat('%',:filter,'%') or 
            lower(d.departmentCode) like concat('%',:filter,'%')''')
	List<Department> searchDepartments(@Param("filter") String filter)
}