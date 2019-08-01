package com.hisd3.hismk2.dao.pms

import com.hisd3.hismk2.domain.pms.NurseNote
import com.hisd3.hismk2.repository.pms.NurseNoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
@Transactional
class NurseNoteDao {
	
	@Autowired
	private NurseNoteRepository nurseNoteRepository
	
	@PersistenceContext
	EntityManager entityManager
	
	List<NurseNote> getNurseNotesByPatientCase(String patientCase) {
		return nurseNoteRepository.getNurseNotesByPatientCase(patientCase)
	}
	
	List<NurseNote> getNurseNotes() {
		return nurseNoteRepository.getNurseNotes()
	}
}
