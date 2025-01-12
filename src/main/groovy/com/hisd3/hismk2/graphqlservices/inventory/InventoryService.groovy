package com.hisd3.hismk2.graphqlservices.inventory

import com.hisd3.hismk2.domain.inventory.Inventory
import com.hisd3.hismk2.repository.inventory.InventoryRepository
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
@TypeChecked
class InventoryService {
	
	@Autowired
	InventoryRepository inventoryRepository
	
	@GraphQLQuery(name = "inventory_list", description = "List of Inventory filtered by department")
	List<Inventory> allItems(@GraphQLArgument(name = "departmentid") UUID departmentid, @GraphQLArgument(name = "filter") String filter) {
		return inventoryRepository.inventoryByDepartmentAndFilter(departmentid, filter)
	}
	
	@GraphQLQuery(name = "inventoryItemsByFilter", description = "List of Inventory Items")
	List<Inventory> getInventoryByFilter(@GraphQLArgument(name = "filter") String filter) {
		return inventoryRepository.itemsByFilter(filter).sort { it.item.descLong }
	}
	
}
