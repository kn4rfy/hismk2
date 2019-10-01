package com.hisd3.hismk2.graphqlservices.inventory

import com.hisd3.hismk2.domain.inventory.Supplier
import com.hisd3.hismk2.repository.inventory.SupplierRepository
import groovy.transform.TypeChecked
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
	
	@GraphQLQuery(name = "supplier_list", description = "List of Suppliers")
	List<Supplier> allSupplier() {
		return supplierRepository.findAll()
	}
	
}
