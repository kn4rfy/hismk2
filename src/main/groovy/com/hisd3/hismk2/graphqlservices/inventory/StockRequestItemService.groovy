package com.hisd3.hismk2.graphqlservices.inventory

import com.hisd3.hismk2.dao.inventory.StockRequestItemDao
import com.hisd3.hismk2.domain.inventory.StockRequestItem
import com.hisd3.hismk2.repository.inventory.StockRequestItemRepository
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
@TypeChecked
class StockRequestItemService {
	
	@Autowired
	StockRequestItemRepository stockRequestItemRepository

	@Autowired
	StockRequestItemDao stockRequestItemDao
	
	@GraphQLQuery(name = "getSRItemsBySRId", description = "List of SR Items by SR Id")
	List<StockRequestItem> getSRItemsBySRId(@GraphQLArgument(name = "srId") String srId) {
		return stockRequestItemRepository.getSRItemsBySRId(UUID.fromString(srId))
	}

	@GraphQLMutation
	List<StockRequestItem> upsertStockRequestItems(
		@GraphQLArgument(name = "stockRequestId") String stockRequestId,
		@GraphQLArgument(name = "stockRequestItems") List<Map<String, Object>> stockRequestItems) {

		return stockRequestItemDao.saveStockRequestItem(stockRequestId, stockRequestItems)
	}
}
