package com.hisd3.hismk2.repository.eventhandlers

import com.hisd3.hismk2.domain.inventory.StockRequest
import com.hisd3.hismk2.domain.inventory.StockRequestItem
import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.Patient
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import groovy.transform.TypeChecked
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.core.annotation.*

import javax.transaction.Transactional
import java.time.LocalDateTime

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
	
	@HandleBeforeSave
	handleBeforeSaveCase(Case patientCase) {
		
		println(patientCase.mayGoHomeDatetime)
		
		if (patientCase.mayGoHomeDatetime != null || patientCase.mayGoHomeDatetime == '') {
			patientCase.mayGoHomeDatetime = LocalDateTime.now()
		} else {
			patientCase.mayGoHomeDatetime = null
		}
	}
	
	@HandleAfterSave
	handleMedicationAfterSave(StockRequest stockRequest) {
		if (stockRequest.status == "CLAIMABLE") {
			println("MUST NOTIFY THAT REQUEST IS CLAIMABLE")
			for (StockRequestItem stockRequestItem in stockRequest.stockRequestItems) {
				if (stockRequestItem.itemReferenceId != null) {
					//deduct on inventory
				} else {
					println("Item has no inventory reference id and cannot charge or deduct on inventory : " + stockRequestItem.itemDescription)
				}
			}
		}
		if (stockRequest.status == "CLAIMED") {
			println("MUST CHARGE MEDCS")
		}
	}
}
