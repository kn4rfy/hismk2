package com.hisd3.hismk2.repository

import com.hisd3.hismk2.domain.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DepartmentRepository extends JpaRepository<Department, UUID> {
	
	@Query(value = "Select department from Department department where upper(department.departmentCode) = upper(:departmentCode)")
	Department findOneByDepartmentCode(@Param("departmentCode") String departmentCode)
	
	@Query(value = "Select department from Department department where upper(department.departmentName) = upper(:departmentName)")
	Department findOneByDepartmentName(@Param("departmentName") String departmentName)
	
	@Query(value = '''Select department from Department department where
            upper(department.departmentName) like upper(concat('%',:filter,'%')) or
            upper(department.departmentCode) like upper(concat('%',:filter,'%'))''')
	List<Department> departmentsByFilter(@Param("filter") String filter)
	
	@Query(value = '''Select department from Department department where
            upper(department.departmentName) like upper(concat('%',:name,'%'))''')
	Department findByName(@Param("name") String name)
	
	@Query(value = "Select department from Department department where department.hasRooms = true")
	List<Department> getDepartmentWithRooms()
	
	@Query(value = "Select department from Department department where department.parentDepartment is null")
	List<Department> getParentDepartments()
}