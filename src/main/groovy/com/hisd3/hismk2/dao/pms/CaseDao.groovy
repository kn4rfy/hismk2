package com.hisd3.hismk2.dao.pms

import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.NurseNote
import com.hisd3.hismk2.domain.pms.Patient
import com.hisd3.hismk2.domain.pms.VitalSign
import com.hisd3.hismk2.repository.pms.CaseRepository
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@TypeChecked
@Service
@Transactional
class CaseDao {
	
	@Autowired
	private CaseRepository caseRepository
	
	@PersistenceContext
	EntityManager entityManager
	
	List<Case> findAll() {
		return caseRepository.findAll()
	}
	
	Case findById(String id) {
		return caseRepository.findById(UUID.fromString(id)).get()
	}

	Case findByCaseNo(String caseNo) {
		return caseRepository.findByCaseNo(caseNo)
	}
	
	Case getPatientActiveCase(UUID patientId) {
		return caseRepository.getPatientActiveCase(patientId)
	}
	
	Set<NurseNote> getNurseNotes(Case parentCase) {
		
		def mergedCase = entityManager.merge(parentCase)
		mergedCase.caseNurseNotes.size()
		return mergedCase.caseNurseNotes as Set
	}

	Set<VitalSign> getVitalSigns(Case parentCase) {
		
		def mergedCase = entityManager.merge(parentCase)
		mergedCase.caseVitalSigns.size()
		return mergedCase.caseVitalSigns as Set
	}

	Case save(Case pCase) {
		caseRepository.save(pCase)
	}
}