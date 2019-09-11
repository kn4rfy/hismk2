package com.hisd3.hismk2.graphqlservices.inventory

import com.hisd3.hismk2.dao.inventory.PurchaseRequestDao
import com.hisd3.hismk2.domain.inventory.PurchaseRequest
import com.hisd3.hismk2.domain.inventory.PurchaseRequestItem
import com.hisd3.hismk2.repository.inventory.ItemRepository
import com.hisd3.hismk2.repository.inventory.PurchaseRequestItemRepository
import com.hisd3.hismk2.repository.inventory.PurchaseRequestRepository
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import liquibase.util.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class PurchaseRequestService {
	
	@Autowired
	PurchaseRequestRepository purchaseRequestRepository
	
	@Autowired
	PurchaseRequestItemRepository purchaseRequestItemRepository

    @Autowired
    ItemRepository itemRepository

	@Autowired
	PurchaseRequestDao purchaseRequestDao
	
	@GraphQLQuery
	List<PurchaseRequest> getAllPurchaseRequest() {
		purchaseRequestRepository.findAll()
	}
	
	@GraphQLQuery
	List<PurchaseRequestItem> getAllPurchaseRequestItems(@GraphQLArgument(name = "prId") UUID prId) {
		purchaseRequestDao.getpRItems(prId)
	}


    @GraphQLMutation
    List<PurchaseRequestItem> addPurchaseRequestItems(
            @GraphQLArgument(name = "id") UUID id,
            @GraphQLArgument(name = "fields") Map<String, Object> fields
    ) {
        if(id != null){
            def pr = purchaseRequestRepository.findById(id).get()
            def prItems = new PurchaseRequestItem()
            def item = itemRepository.findById(UUID.fromString(fields.get("id") as String)).get()

            prItems.refItem = item.id
            prItems.refPr = pr.id
            prItems.itemName = item.descLong

            purchaseRequestItemRepository.save(prItems)

            return getAllPurchaseRequestItems(pr.id)
        }
    }

}
