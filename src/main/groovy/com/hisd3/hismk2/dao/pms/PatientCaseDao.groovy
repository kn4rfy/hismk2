package com.hisd3.hismk2.dao.pms

import com.hisd3.hismk2.domain.pms.NurseNote
import com.hisd3.hismk2.domain.pms.PatientCase
import com.hisd3.hismk2.domain.pms.VitalSign
import com.hisd3.hismk2.repository.pms.PatientCaseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
@Transactional
class PatientCaseDao {
	
	@Autowired
	private PatientCaseRepository patientCaseRepository
	
	@PersistenceContext
	EntityManager entityManager
	
	List<PatientCase> getPatientCases() {
		return patientCaseRepository.getPatientCases()
	}
	
	Set<NurseNote> getNurseNotesByPatientCase(PatientCase patientCase) {
		
		def mergedPatientCase = entityManager.merge(patientCase)
		mergedPatientCase.nurseNotes.size()
		return mergedPatientCase.nurseNotes as Set
	}
	
	Set<VitalSign> getVitalSignsByPatientCase(PatientCase patientCase) {
		
		def mergedPatientCase = entityManager.merge(patientCase)
		mergedPatientCase.vitalSigns.size()
		return mergedPatientCase.vitalSigns as Set
	}
}