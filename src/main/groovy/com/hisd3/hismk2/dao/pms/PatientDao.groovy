package com.hisd3.hismk2.dao.pms

import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.Patient
import com.hisd3.hismk2.repository.pms.CaseRepository
import com.hisd3.hismk2.repository.pms.PatientRepository
import com.hisd3.hismk2.utils.OffsetBasedPageRequest
import groovy.transform.TypeChecked
import io.leangen.graphql.execution.relay.Page
import io.leangen.graphql.execution.relay.generic.PageFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@TypeChecked
@Service
@Transactional
class PatientDao {
	
	@Autowired
	private PatientRepository patientRepository
	
	@Autowired
	private CaseRepository caseRepository
	
	@PersistenceContext
	EntityManager entityManager
	
	List<Patient> findAll() {
		return patientRepository.findAll()
	}
	
	Patient findById(String id) {
		return patientRepository.findById(UUID.fromString(id)).get()
	}
	
	Page<Patient> getPatientRelayPage(int first, int offset) {
		def pageable = patientRepository.findAll(new OffsetBasedPageRequest(offset, first, new Sort(Sort.Direction.ASC, "patientNo")))
		
		PageFactory.createOffsetBasedPage(pageable.content, pageable.totalElements, offset)
		
	}
	
	List<Case> getPatientCases(Patient patient) {
		return caseRepository.getPatientCases(patient.id)
	}
	
	Set<Case> filterPatientCase(String filter, Patient patient) {
		def mergedPatient = entityManager.merge(patient)
		mergedPatient.patientCases.size()
		return mergedPatient.patientCases as Set
	}
	
	List<Patient> searchPatients(String filter) {
		return patientRepository.searchPatients(filter)
	}
	
	Patient save(Patient patient) {
		patientRepository.save(patient)
	}
	
}
