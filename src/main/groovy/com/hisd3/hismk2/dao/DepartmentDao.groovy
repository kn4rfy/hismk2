package com.hisd3.hismk2.dao

import com.hisd3.hismk2.repository.DepartmentRepository
import com.hisd3.hismk2.domain.Department
import javax.persistence.EntityManager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.PersistenceContext

@Service
@Transactional
class DepartmentDao {

    @Autowired
    private DepartmentRepository departmentRepository

    @PersistenceContext
    EntityManager entityManager

    List<Department> findAll() {
        return departmentRepository.findAll()
    }

    List<Department> searchDepartments(String filter) {
        return departmentRepository.searchDepartments(filter)
    }

    Department findById(String id) {
        return departmentRepository.findById(UUID.fromString(id)).get()
    }

    Department save(Department department) {
        departmentRepository.save(department)
    }
}