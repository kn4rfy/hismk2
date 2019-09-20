package com.hisd3.hismk2.dao.pms

import com.hisd3.hismk2.domain.pms.Intake
import com.hisd3.hismk2.repository.pms.IntakeRepository
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@TypeChecked
@Transactional
class IntakeDao {
	
	@Autowired
	private IntakeRepository intakeRepository
	
	List<Intake> findAll() {
		return intakeRepository.findAll()
	}
	
	Intake findById(String id) {
		return intakeRepository.findById(UUID.fromString(id)).get()
	}
	
	List<Intake> getIntakesByCase(UUID id) {
		return intakeRepository.getIntakesByCase(id)
	}
}
