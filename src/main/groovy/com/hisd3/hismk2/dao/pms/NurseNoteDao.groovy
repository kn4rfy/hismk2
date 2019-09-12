package com.hisd3.hismk2.dao.pms

import com.hisd3.hismk2.domain.pms.NurseNote
import com.hisd3.hismk2.domain.pms.Transfer
import com.hisd3.hismk2.repository.pms.NurseNoteRepository
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@TypeChecked
@Transactional
class NurseNoteDao {
	
	@Autowired
	private NurseNoteRepository nurseNoteRepository
	
	List<NurseNote> findAll() {
		return nurseNoteRepository.findAll()
	}
	
	NurseNote findById(String id) {
		return nurseNoteRepository.findById(UUID.fromString(id)).get()
	}

	List<NurseNote> getNurseNotesByCase(UUID id) {
		return nurseNoteRepository.getNurseNotesByCase(id)
	}
}
