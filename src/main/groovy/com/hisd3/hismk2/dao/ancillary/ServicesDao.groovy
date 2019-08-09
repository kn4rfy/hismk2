package com.hisd3.hismk2.dao.ancillary

import com.hisd3.hismk2.domain.ancillary.Services
import com.hisd3.hismk2.domain.pms.Patient
import com.hisd3.hismk2.repository.ancillary.ServicesRepository
import com.hisd3.hismk2.repository.pms.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
@Transactional
class ServicesDao {

    @Autowired
    private ServicesRepository servicesRepository


    @PersistenceContext
    EntityManager entityManager

    Set<Services> findAll() {
        return servicesRepository.findAll()
    }

    Services findById(String id){
        return servicesRepository.findById(Long.parseLong(id)).get()
    }
}
