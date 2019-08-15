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
	List<StockRequest> allStockRequests() {
		return stockRequestRepository.findAll()
	}
	
	@GraphQLQuery(name = "stockrequests", description = "List of Stock Requests with filter and status")
	List<StockRequest> allStockRequests(@GraphQLArgument(name = "status") String status, @GraphQLArgument(name = "filter") String filter) {
		return stockRequestRepository.stockRequestByFilter(status, filter)
	}
	
	@GraphQLQuery(name = "stockrequest", description = "Get stockrequest By Id")
	StockRequest findById(@GraphQLArgument(name = "id") UUID id) {
		return stockRequestRepository.findById(id).get()
	}
}
