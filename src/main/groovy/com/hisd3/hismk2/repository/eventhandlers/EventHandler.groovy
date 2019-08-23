package com.hisd3.hismk2.repository.eventhandlers

import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.Patient
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import groovy.transform.TypeChecked
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.core.annotation.HandleAfterCreate
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.RepositoryEventHandler

import javax.transaction.Transactional

@TypeChecked
@RepositoryEventHandler
@Transactional
class EventHandler {
	
	@Autowired
	private GeneratorService generatorService
	
	@HandleBeforeCreate
	handleBeforeCreatePatient(Patient patient) {
		if (!patient.patientNo) {
			
			patient.patientNo = generatorService?.getNextValue(GeneratorType.PATIENT_NO, { i ->
				StringUtils.leftPad(i.toString(), 6, "0")
			})
			
		}
	}
	
	@HandleAfterCreate
	handleAfterCreatePatient(Patient patient) {
	
	}
	
	@HandleBeforeCreate
	handleBeforeCreateCase(Case patientCase) {
		if (!patientCase.caseNo) {
			
			patientCase.caseNo = generatorService?.getNextValue(GeneratorType.CASE_NO, { i ->
				StringUtils.leftPad(i.toString(), 6, "0")
			})
			
		}
	}
	
	@HandleBeforeCreate
	handleAfterCreateCase(Case patientCase) {
		if (!patientCase.caseNo) {
			
			patientCase.caseNo = generatorService?.getNextValue(GeneratorType.CASE_NO, { i ->
				StringUtils.leftPad(i.toString(), 6, "0")
			})
			
		}
	}
	
}
