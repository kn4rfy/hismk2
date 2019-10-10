package com.hisd3.hismk2.graphqlservices.sales

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.domain.inventory.Supplier
import com.hisd3.hismk2.domain.sales.SalesTransaction
import com.hisd3.hismk2.repository.inventory.SupplierRepository
import com.hisd3.hismk2.repository.sales.SalesTransactionItemRepository
import com.hisd3.hismk2.repository.sales.SalesTransactionRepository
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
class SalesTransactionService {

    @Autowired
    SalesTransactionRepository salesTransactionRepository

    @Autowired
    ObjectMapper objectMapper

    @GraphQLQuery(name = "sales_transaction_list", description = "List of Sale Transaction")
    List<SalesTransaction> allSalesTransaction(
            @GraphQLArgument(name = "filter") String filter
    ) {
        return salesTransactionRepository.findAllByFilter(filter)
    }

    @GraphQLQuery(name = "sales_transaction", description = "Sales Transaction")
    SalesTransaction getSaleTransaction(
            @GraphQLArgument(name = "id") String id
    ) {
        return salesTransactionRepository.findById(UUID.fromString(id)).get()
    }

    //======================= Mutation ========================//

    @GraphQLMutation
    SalesTransaction addSaleTransaction(
            @GraphQLArgument(name = "id") String id,
            @GraphQLArgument(name = "fields") Map<String, Object> fields
    ) {
        if (id) {
            SalesTransaction salesTrarnsaction = salesTransactionRepository.findById(UUID.fromString(id)) as SalesTransaction
            objectMapper.updateValue(salesTrarnsaction, fields)

           return salesTransactionRepository.save(salesTrarnsaction)

        } else {
            def salesTrarnsaction = objectMapper.convertValue(fields, SalesTransaction)

            return salesTransactionRepository.save(salesTrarnsaction)
        }
    }

}
