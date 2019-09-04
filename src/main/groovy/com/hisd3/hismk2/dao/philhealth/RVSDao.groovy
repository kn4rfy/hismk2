package com.hisd3.hismk2.dao.philhealth

import com.hisd3.hismk2.domain.bms.ICDCode
import com.hisd3.hismk2.domain.philhealth.RVSCode
import com.hisd3.hismk2.repository.philhealth.ICDCodesRepository
import com.hisd3.hismk2.repository.philhealth.RVSCodesRepository
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@TypeChecked
@Service
@Transactional
class RVSDao {
	
	@Autowired
	private RVSCodesRepository rvsCodesRepository
	
	@PersistenceContext
	EntityManager entityManager

	List<RVSCode> getRVSCodes() {
		return rvsCodesRepository.getRVSCodes()
	}

	List<RVSCode> searchRVSCodes(String filter) {
		return rvsCodesRepository.searchRVSCodes(filter)
	}
}
