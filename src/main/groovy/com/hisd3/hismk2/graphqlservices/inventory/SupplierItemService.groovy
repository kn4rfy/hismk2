package com.hisd3.hismk2.graphqlservices.inventory

import com.hisd3.hismk2.domain.inventory.SupplierItem
import com.hisd3.hismk2.repository.inventory.SupplierItemRepository
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
@TypeChecked
class SupplierItemService {
	
	@Autowired
	SupplierItemRepository supplierItemRepository
	
	@GraphQLQuery(name = "allSupplyItemsBySupplier", description = "List of Supplies")
	List<SupplierItem> allSupplyItems(
			@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "filter") String filter
	) {
		return supplierItemRepository.findBySupplier(UUID.fromString(id), filter)
	}
	
	@GraphQLQuery(name = "getSupplyItem", description = "Supply Item")
	SupplierItem getSupplyItem(
			@GraphQLArgument(name = "id") String id
	) {
		return supplierItemRepository.findById(UUID.fromString(id)).get()
	}
	
}
