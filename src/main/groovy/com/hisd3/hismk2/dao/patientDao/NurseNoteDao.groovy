package com.hisd3.hismk2.dao.patientDao

import com.hisd3.hismk2.domain.patientDom.NurseNote
import com.hisd3.hismk2.repository.patientRepo.NurseNoteRepository
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

    List<NurseNote> getNurseNotesByCase(String patientCase) {
        return nurseNoteRepository.getNurseNotesByCase(patientCase)
    }

    List<NurseNote> getNurseNotes() {
        return nurseNoteRepository.getNurseNotes()
    }
}
