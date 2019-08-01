package com.hisd3.hismk2.dao.pms

import com.hisd3.hismk2.domain.pms.Vitalsign
import com.hisd3.hismk2.repository.pms.VitalsignRepository
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
	
	List<Vitalsign> getVitalsignsByPatientCase(String patientCase) {
		return vitalsignRepository.getVitalsignsByCase(patientCase)
	}
	
	List<Vitalsign> getVitalsigns() {
		return vitalsignRepository.getVitalsigns()
	}
}
