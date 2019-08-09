package com.hisd3.hismk2.graphqlservices.ancillary

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.RevenueCenterDao
import com.hisd3.hismk2.domain.ancillary.RevenueCenter
import com.hisd3.hismk2.services.GeneratorService
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class RevenueCenterService {

    @Autowired
    RevenueCenterDao revenueCenterDao

    @Autowired
    GeneratorService generatorService



    @Autowired
    ObjectMapper objectMapper

    //============== All Queries ====================

    @GraphQLQuery(name = "revenuecenter", description = "Get All RevenueCenters")
    Set<RevenueCenter> findAll() {
        revenueCenterDao.findAll()
    }
}
