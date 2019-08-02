package com.hisd3.hismk2.dao.pms

import com.hisd3.hismk2.domain.pms.NurseNote
import com.hisd3.hismk2.repository.pms.NurseNoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
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
}
