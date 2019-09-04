package com.hisd3.hismk2.dao.philhealth

import com.hisd3.hismk2.domain.bms.ICDCode
import com.hisd3.hismk2.domain.pms.Patient
import com.hisd3.hismk2.repository.philhealth.ICDCodesRepository
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@TypeChecked
@Service
@Transactional
class ICDDao {
	
	@Autowired
	private ICDCodesRepository icdCodesRepository
	
	@PersistenceContext
	EntityManager entityManager

	List<ICDCode> getICDCodes() {
		return icdCodesRepository.getICDCodes()
	}

	List<ICDCode> searchICDCodes(String filter) {
		return icdCodesRepository.searchICDCodes(filter)
	}
}
