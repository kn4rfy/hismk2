package com.hisd3.hismk2.dao

import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.User
import com.hisd3.hismk2.domain.ancillary.Orderslip
import com.hisd3.hismk2.repository.DepartmentRepository
import com.hisd3.hismk2.repository.UserRepository
import com.hisd3.hismk2.repository.ancillary.OrderslipRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
@Transactional
class OrderslipDao {

    @Autowired
    private OrderslipRepository orderslipRepository

    @PersistenceContext
    EntityManager entityManager

    List<Orderslip> findAll() {
        return orderslipRepository.findAll()
    }

    Orderslip findById(String id) {
        return orderslipRepository.findById(Long.parseLong(id)).get()
    }
}
