package com.hisd3.hismk2.dao.pms

import com.hisd3.hismk2.domain.pms.Output
import com.hisd3.hismk2.repository.pms.OutputRepository
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@TypeChecked
@Transactional
class OutputDao {
	
	@Autowired
	private OutputRepository outputRepository
	
	List<Output> findAll() {
		return outputRepository.findAll()
	}
	
	Output findById(String id) {
		return outputRepository.findById(UUID.fromString(id)).get()
	}
	
	List<Output> getOutputsByCase(UUID id) {
		return outputRepository.getOutputsByCase(id)
	}
}
