package com.hisd3.hismk2.dao.ancillary

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.domain.ancillary.DiagnosticResult
import com.hisd3.hismk2.repository.ancillary.DiagnosticsResultRepository
import com.hisd3.hismk2.services.GeneratorService
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@TypeChecked
@Service
@Transactional
class DiagnosticResultDao {
	
	@Autowired
	private DiagnosticsResultRepository diagnosticsResultRepository
	
	@Autowired
	private ObjectMapper objectMapper
	
	@Autowired
	GeneratorService generatorService
	
	@PersistenceContext
	EntityManager entityManager
	
	List<DiagnosticResult> findAll() {
		return diagnosticsResultRepository.findAll()
	}
	
	List<DiagnosticResult> findByOrderSlip(UUID id) {
		return diagnosticsResultRepository.findByOrderSlipItem(id)
	}
}
