package com.hisd3.hismk2.graphqlservices.inventory

import com.hisd3.hismk2.dao.inventory.PurchaseRequestDao
import com.hisd3.hismk2.domain.inventory.PurchaseRequest
import com.hisd3.hismk2.domain.inventory.PurchaseRequestItem
import com.hisd3.hismk2.repository.hrm.EmployeeRepository
import com.hisd3.hismk2.repository.inventory.ItemRepository
import com.hisd3.hismk2.repository.inventory.PurchaseRequestItemRepository
import com.hisd3.hismk2.repository.inventory.PurchaseRequestRepository
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.Instant

@TypeChecked
@Component
@GraphQLApi
class PurchaseRequestService {
	
	@Autowired
	PurchaseRequestRepository purchaseRequestRepository
	
	@Autowired
	PurchaseRequestItemRepository purchaseRequestItemRepository
	
	@Autowired
	ItemRepository itemRepository
	
	@Autowired
	PurchaseRequestDao purchaseRequestDao
	
	@Autowired
	EmployeeRepository employeeRepository
	
	@Autowired
	GeneratorService generatorService
	
	@GraphQLQuery
	List<PurchaseRequest> getAllPurchaseRequest() {
		purchaseRequestRepository.findAll()
	}
	
	@GraphQLQuery
	List<PurchaseRequestItem> getAllPurchaseRequestItems(@GraphQLArgument(name = "prId") UUID prId) {
		purchaseRequestDao.getpRItems(prId)
	}
	
	@GraphQLQuery
	PurchaseRequest getPurchaseRequest(@GraphQLArgument(name = "prId") UUID prId) {
		if (prId) {
			return purchaseRequestRepository.findById(prId).get()
		}
	}
	
	@GraphQLMutation
	PurchaseRequest savePurchaseReqeust(
			@GraphQLArgument(name = "items") ArrayList<Map<String, Object>> items,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		if (fields.get("id")) {
			def pr = purchaseRequestRepository.findById(UUID.fromString(fields.get("id") as String)).get()
			
			if (fields.get("requestedBy")) {
				def employee = employeeRepository.findById(UUID.fromString(fields.get("requestedBy") as String)).get()
				pr.requestedBy = employee.id
				pr.requestedByName = employee.fullName
				
			}
			
			if (fields.get("approvedBy")) {
				def employee = employeeRepository.findById(UUID.fromString(fields.get("approvedBy") as String)).get()
				pr.approvedBy = employee.id
				pr.approvedName = employee.fullName
				
			}
			
			if (fields.get("approvedDate")) {
				pr.approvedDate = Instant.parse(fields.get("approvedDate") as String)
			}
			if (fields.get("requestedDate")) {
				pr.requestedDate = Instant.parse(fields.get("requestedDate") as String)
			}
			
			if (fields.get("dateNeeded")) {
				pr.dateNeeded = Instant.parse(fields.get("dateNeeded") as String)
			}
			
			pr.suggestedSupplierName = fields.get("requestType", "")
			pr.requestType = fields.get("requestType", "")
			pr.status = fields.get("status", "")
			
			items.each {
				it ->
					if (it.get("id") == null) {
						def prItems = new PurchaseRequestItem()
						prItems.refPr = pr.id
						if (it.get("refItem") != null) {
							def item = itemRepository.findById(UUID.fromString(it.get("refItem") as String)).get()
							prItems.refItem = item.id
							prItems.itemName = item.descLong
							prItems.qty = it.get("qty") as Integer
							
						} else {
							prItems.itemName = it.get("itemName")
							prItems.qty = it.get("qty") as Integer
						}
						
						purchaseRequestItemRepository.save(prItems)
					} else {
						def prItems = purchaseRequestItemRepository.findById(UUID.fromString(it.get("id") as String)).get()
						prItems.qty = it.get("qty") as Integer
						purchaseRequestItemRepository.save(prItems)
						
					}
			}
			
			return purchaseRequestRepository.save(pr)
			
		} else {
			def pr = new PurchaseRequest()
			pr.prNo = generatorService.getNextValue(GeneratorType.PR_NO) { Long no ->
				StringUtils.leftPad(no.toString(), 5, "0")
			}
			if (fields.get("requestedBy")) {
				def employee = employeeRepository.findById(UUID.fromString(fields.get("requestedBy") as String)).get()
				pr.requestedBy = employee.id
				pr.requestedByName = employee.fullName
				
			}
			
			if (fields.get("approvedBy")) {
				def employee = employeeRepository.findById(UUID.fromString(fields.get("approvedBy") as String)).get()
				pr.approvedBy = employee.id
				pr.approvedName = employee.fullName
				
			}
			
			if (fields.get("approvedDate")) {
				pr.approvedDate = Instant.parse(fields.get("approvedDate") as String)
			}
			if (fields.get("requestedDate")) {
				pr.requestedDate = Instant.parse(fields.get("requestedDate") as String)
			}
			
			if (fields.get("dateNeeded")) {
				pr.dateNeeded = Instant.parse(fields.get("dateNeeded") as String)
			}
			
			pr.suggestedSupplierName = fields.get("requestType", "")
			pr.requestType = fields.get("requestType", "")
			pr.status = fields.get("status", "")
			
			def afterSave = purchaseRequestRepository.save(pr)
			
			items.each {
				it ->
					if (it.get("id") == null) {
						def prItems = new PurchaseRequestItem()
						prItems.refPr = afterSave.id
						if (it.get("refItem") != null) {
							def item = itemRepository.findById(UUID.fromString(it.get("refItem") as String)).get()
							prItems.refItem = item.id
							prItems.itemName = item.descLong
							prItems.qty = it.get("qty") as Integer
							
						} else {
							prItems.itemName = it.get("itemName")
							prItems.qty = it.get("qty") as Integer
						}
						
						purchaseRequestItemRepository.save(prItems)
					} else {
						def prItems = purchaseRequestItemRepository.findById(UUID.fromString(it.get("id") as String)).get()
						prItems.qty = it.get("qty") as Integer
						purchaseRequestItemRepository.save(prItems)
						
					}
			}
			
			return afterSave
		}
	}
	
}
