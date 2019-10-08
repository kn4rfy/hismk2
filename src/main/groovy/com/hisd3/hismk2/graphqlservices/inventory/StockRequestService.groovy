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
	
	@GraphQLQuery(name = "getStockRequestsByPatient", description = "List of Stock Requests with patient and status")
	List<StockRequest> getStockRequestsByPatient(@GraphQLArgument(name = "status") String status, @GraphQLArgument(name = "patientId") String patientId) {
		return stockRequestRepository.getStockRequestsByPatient(UUID.fromString(patientId))
	}
	
	@GraphQLQuery(name = "getStockRequestsByStatus", description = "List of Stock Requests with patient and status")
	List<StockRequest> getStockRequestsByStatus(@GraphQLArgument(name = "status") String status) {
		return stockRequestRepository.getStockRequestsByStatus(status)
	}
}
