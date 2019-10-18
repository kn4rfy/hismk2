package com.hisd3.hismk2.graphqlservices.inventory

import com.hisd3.hismk2.domain.inventory.StockRequest
import com.hisd3.hismk2.repository.inventory.StockRequestRepository
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
@TypeChecked
class StockRequestService {
	
	@Autowired
	StockRequestRepository stockRequestRepository
	
	@GraphQLQuery(name = "stockrequests", description = "List of Stock Requests")
	List<StockRequest> allStockRequests(@GraphQLArgument(name = "patientId") String patientId) {
		return stockRequestRepository.findAll()
	}
	
	@GraphQLQuery(name = "getStockRequest", description = "List of ")
	StockRequest findById(@GraphQLArgument(name = "id") UUID id) {
		return stockRequestRepository.findById(id).get()
	}
	
	@GraphQLQuery(name = "getStockRequestsByPatientAndStatus", description = "List of Stock Requests with patient and status")
	List<StockRequest> getStockRequestsByPatientAndStatus(@GraphQLArgument(name = "status") String status, @GraphQLArgument(name = "patientId") String patientId) {
		return stockRequestRepository.getStockRequestsByPatientAndStatus(status, UUID.fromString(patientId))
	}
	
	@GraphQLQuery(name = "getStockRequestsByPatient", description = "List of Stock Requests with patient")
	List<StockRequest> getStockRequestsByPatient(@GraphQLArgument(name = "patientId") String patientId) {
		return stockRequestRepository.getStockRequestsByPatient(UUID.fromString(patientId))
	}
	
	@GraphQLQuery(name = "getStockRequestsByStatus", description = "List of Stock Requests with status")
	List<StockRequest> getStockRequestsByStatus(@GraphQLArgument(name = "status") String status) {
		return stockRequestRepository.getStockRequestsByStatus(status)
	}

//	StockRequestItem saveStockRequestItems(List<Map<String, Object>> billingItems) {
//
//		if (billingId) {
//
//
//			//.get(0) means that we get the first active billing result
//			def billingDto = billingRepository.findById(billingId).get()
//
//			if (billingItems) {
//				billingItems.each {
//					Map<String, Object> billingItem ->
//
//						def billingItemDto = new BillingItem()
//						billingItemDto.billing = billingDto
//
//						def item = itemRepository.findById(UUID.fromString(billingItem.get("item") as String)).get()
//						Random rnd = new Random()
//
//						billingItemDto.recordNo = rnd.nextInt(999999)
//						billingItemDto.qty = billingItem.get("qty", 0) as Integer
//
//						billingItemDto.description = item.descLong
//						billingItemDto.price = item.basePrice
//						billingItemDto.status = 'ACTIVE'
//
//						def department = billingItem.get("department", "") as String
//
//						billingItemDto.department = departmentRepository.findById(
//								UUID.fromString(department)
//						).get()
//
//						billingItemRepository.save(billingItemDto)
//				}
//			}
//
//			return billingDto
//		}
//	}
}
