package com.hisd3.hismk2.graphqlservices.inventory

import com.hisd3.hismk2.domain.inventory.Item
import com.hisd3.hismk2.repository.inventory.ItemRepository
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
@TypeChecked
class ItemService {
	
	@Autowired
	ItemRepository itemRepository
	
	@GraphQLQuery(name = "items", description = "List of Items")
	List<Item> allItems() {
		return itemRepository.findAll()
	}
	
	@GraphQLQuery(name = "item", description = "Get Item By Id")
	Item findById(@GraphQLArgument(name = "id") UUID id) {
		return itemRepository.findById(id).get()
	}
}
