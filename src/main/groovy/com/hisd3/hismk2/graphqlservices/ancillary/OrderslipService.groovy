package com.hisd3.hismk2.graphqlservices.ancillary

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.OrderslipDao
import com.hisd3.hismk2.domain.ancillary.Orderslip
import com.hisd3.hismk2.services.GeneratorService
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class OrderslipService {

    @Autowired
    OrderslipDao orderslipDao

    @Autowired
    GeneratorService generatorService



    @Autowired
    ObjectMapper objectMapper

    //============== All Queries ====================

    @GraphQLQuery(name = "orderslips", description = "Get All Orderslips")
    Set<Orderslip> findAll() {
        orderslipDao.findAll()
    }
}
