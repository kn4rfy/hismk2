package com.hisd3.hismk2.graphqlservices.inventory

import com.hisd3.hismk2.domain.inventory.PurchaseOrder
import com.hisd3.hismk2.repository.inventory.PurchaseOrderRepository
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
class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository

    @GraphQLQuery(name = "poList", description = "list of all purchase order")
    List<PurchaseOrder> poList() {
        return purchaseOrderRepository.findAll()
    }

    @GraphQLMutation(name = "upSertPurchaseOrder", description = "upsert po")
    PurchaseOrder upSertPurchaseaOrder(@GraphQLArgument(name = "fields") Map<String,Object> fields, @GraphQLArgument(name = "items") List<Map<String,Object>> items){
        PurchaseOrder purchaseOrder = new PurchaseOrder()

        return  purchaseOrder
    }
}
