package com.hisd3.hismk2.dao


import com.hisd3.hismk2.domain.ancillary.RevenueCenter
import com.hisd3.hismk2.repository.ancillary.RevenueCenterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
@Transactional
class RevenueCenterDao {

    @Autowired
    private RevenueCenterRepository revenueCenterRepository

    @PersistenceContext
    EntityManager entityManager

    List<RevenueCenter> findAll() {
        return revenueCenterRepository.findAll()
    }

    RevenueCenter findById(String id) {
        return revenueCenterRepository.findById(Long.parseLong(id)).get()
    }
}
