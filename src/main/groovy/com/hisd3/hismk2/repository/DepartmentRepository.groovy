package com.hisd3.hismk2.repository

import com.hisd3.hismk2.domain.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DepartmentRepository extends JpaRepository<Department,UUID> {
    @Query(
            value = "Select d from Department d",
            countQuery = "Select count(d) from Department d"
    )
    List<Department> getAllDepartment()
}