package com.hisd3.hismk2.graphqlservices.inventory

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.domain.inventory.Supplier
import com.hisd3.hismk2.domain.inventory.SupplierItem
import com.hisd3.hismk2.repository.inventory.PurchaseRequestItemRepository
import com.hisd3.hismk2.repository.inventory.SupplierItemRepository
import com.hisd3.hismk2.repository.inventory.SupplierRepository
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
@TypeChecked
class SupplierService {
	
	@Autowired
	SupplierRepository supplierRepository
	
	@Autowired
	PurchaseRequestItemRepository purchaseRequestItemRepository
	
	@Autowired
	SupplierItemRepository supplierItemRepository
	
	@Autowired
	ObjectMapper objectMapper
	
	@GraphQLQuery(name = "supplier_list", description = "List of Suppliers")
	List<Supplier> allSupplier(
			@GraphQLArgument(name = "filter") String filter
	) {
		return supplierRepository.findAllByFilter(filter)
	}
	
	@GraphQLQuery(name = "supplier", description = "List of Suppliers")
	Supplier getSupplier(
			@GraphQLArgument(name = "id") String id
	) {
		return supplierRepository.findById(UUID.fromString(id)).get()
	}
	
	@GraphQLQuery(name = "supplier_by_pr", description = "List of supplier base on pr items")
	Set<Supplier> supplierByPr() {
		def prItems = purchaseRequestItemRepository.getPrItemByWherePoIsNotNullandStatusIsApproved()
		Set<Supplier> supplierSet = []
		prItems.each {
			it ->
				List<SupplierItem> supplierItems = supplierItemRepository.findByItem(it.refItem.id)
				
				supplierItems.each {
					it2 ->
						supplierSet.add(it2.supplier)
				}
			
		}
		
		supplierSet.unique {
			supplier -> supplier.id
		}
		
		return supplierSet
	}
	
}
