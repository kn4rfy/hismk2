package com.hisd3.hismk2.dao.billing

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.domain.billing.Billing
import com.hisd3.hismk2.domain.billing.BillingItem
import com.hisd3.hismk2.repository.DepartmentRepository
import com.hisd3.hismk2.repository.ancillary.ServiceRepository
import com.hisd3.hismk2.repository.billing.BillingItemRepository
import com.hisd3.hismk2.repository.billing.BillingRepository
import com.hisd3.hismk2.repository.pms.CaseRepository
import com.hisd3.hismk2.repository.pms.PatientRepository
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import groovy.transform.TypeChecked
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.time.Instant

@TypeChecked
@Service
@Transactional
class BillingDao {
	@Autowired
	BillingRepository billingRepository
	
	@Autowired
	BillingItemRepository billingItemRepository
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	DepartmentRepository departmentRepository

	@Autowired
	ServiceRepository serviceRepository

	@Autowired
	PatientRepository patientRepository

	@Autowired
	CaseRepository caseRepository

	@Autowired
	ObjectMapper objectMapper
	
	List<Billing> getBillingByPatient(UUID patientid) {
		billingRepository.getByPatientId(patientid)
	}
	
	List<BillingItem> getBillingItemsByBill(UUID billingId) {
		billingItemRepository.getBillingItemsByBill(billingId)
	}
	
	Billing saveBillingItems(String patientId, String caseId, String billingId, List<Map<String, Object>> billingItems) {
		
		if (billingId) {
			
			//.get(0) means that we get the first active billing result
			def billingDto = billingRepository.findById(UUID.fromString(billingId)).get()

			if (billingItems) {
				billingItems.each {
					Map<String, Object> billingItem ->
						
					def billingItemDto = new BillingItem()
					billingItemDto.billing = billingDto;

					if(billingItem.itemType == 'SERVICE') {

						def item = serviceRepository.findById(UUID.fromString(billingItem.get("item") as String)).get()

						billingItemDto.recordNo = Math.random()
						billingItemDto.description = item.serviceName
						billingItemDto.price = item.basePrice
						billingItemDto.qty = billingItem.get("qty", 0) as Integer

						billingItemDto.department = departmentRepository.findById(
							UUID.fromString(billingItem.get("department", 0) as String)
						).get()

						billingItemRepository.save(billingItemDto)
					}
				}
			}
			
			return billingDto
		} else {
			def newBilling = new Billing()
			def patientDto = patientRepository.findById(UUID.fromString(patientId)).get()
			newBilling.patient = patientDto
			newBilling.patientCase = caseRepository.findById(UUID.fromString(caseId)).get()
			newBilling.entryDatetime = Instant.now()
			newBilling.status = "ACTIVE"
			newBilling.billingNo = generatorService.getNextValue(GeneratorType.RR_NO) { Long no ->
				StringUtils.leftPad(no.toString(), 5, "0")
			}

			def newBilling2 = billingRepository.save(newBilling)

			if (billingItems) {
				billingItems.each {
					Map<String, Object> billingItem ->

						def billingItemDto = new BillingItem()
						billingItemDto.billing = newBilling;

						if (billingItem.itemType == 'SERVICE') {

							def item = serviceRepository.findById(UUID.fromString(billingItem.get("item") as String)).get()

							billingItemDto.recordNo = Math.random()
							billingItemDto.description = item.serviceName
							billingItemDto.price = item.basePrice
							billingItemDto.qty = billingItem.get("qty", 0) as Integer

							billingItemDto.department = departmentRepository.findById(
									UUID.fromString(billingItem.get("department", 0) as String)
							).get()

							billingItemRepository.save(billingItemDto)
						}
				}

				return newBilling2
			}
		}
	}
}