package com.hisd3.hismk2.dao

import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.repository.DepartmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
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

    Department findById(String id) {
        return departmentRepository.findById(Long.parseLong(id)).get()
    }
}
