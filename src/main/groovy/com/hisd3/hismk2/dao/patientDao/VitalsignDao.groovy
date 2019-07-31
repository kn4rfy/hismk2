package com.hisd3.hismk2.dao.patientDao

import com.hisd3.hismk2.domain.patientDom.Vitalsign
import com.hisd3.hismk2.repository.patientRepo.VitalsignRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Service
@Transactional
class VitalsignDao {

    @Autowired
    private VitalsignRepository vitalsignRepository

    @PersistenceContext
    EntityManager entityManager

    List<Vitalsign> getVitalsignsByCase(String patientCase) {
        return vitalsignRepository.getVitalsignsByCase(patientCase)
    }

    List<Vitalsign> getVitalsigns() {
        return vitalsignRepository.getVitalsigns()
    }
}
