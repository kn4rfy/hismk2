package com.hisd3.hismk2.graphqlservices.ancillary

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.ancillary.ServicesDao
import com.hisd3.hismk2.domain.ancillary.Services
import com.hisd3.hismk2.repository.ancillary.ServicesRepository
import com.hisd3.hismk2.services.GeneratorService
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class ServicesService {

    @Autowired
    ServicesDao servicesDao

    @Autowired
    GeneratorService generatorService

    @Autowired
    private ServicesRepository servicesRepository

    @Autowired
    ObjectMapper objectMapper

    //============== All Queries ====================

    @GraphQLQuery(name = "services", description = "Get All Services")
    Set<Services> findAll() {
        servicesDao.findAll()
    }
}
