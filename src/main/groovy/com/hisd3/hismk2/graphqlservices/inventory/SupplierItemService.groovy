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
	
	@GraphQLQuery(name = "supplier_item_list", description = "List of Supplies")
	List<SupplierItem> allSupplyItems() {
		return supplierItemRepository.findAll()
	}
	
	@GraphQLQuery(name = "supply_item", description = "Supply Item")
	SupplierItem getSupplyItem(
			@GraphQLArgument(name = "id") String id
	) {
		return supplierItemRepository.findById(UUID.fromString(id)).get()
	}
}
