package com.hisd3.hismk2.dao.pms

import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.NurseNote
import com.hisd3.hismk2.domain.pms.VitalSign
import com.hisd3.hismk2.repository.pms.CaseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
@Transactional
class CaseDao {
	
	@Autowired
	private CaseRepository parentCaseRepository
	
	@PersistenceContext
	EntityManager entityManager
	
	List<Case> findAll() {
		return parentCaseRepository.findAll()
	}
	
	Case findById(String id) {
		return parentCaseRepository.findById(UUID.fromString(id)).get()
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
}