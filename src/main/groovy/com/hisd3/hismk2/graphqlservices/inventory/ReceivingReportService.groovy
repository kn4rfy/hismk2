package com.hisd3.hismk2.graphqlservices.inventory

import com.hisd3.hismk2.domain.inventory.ReceivingReport
import com.hisd3.hismk2.repository.inventory.ReceivingReportRepository
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class ReceivingReportService {

    @Autowired
    ReceivingReportRepository receivingReportRepository;

    @GraphQLQuery(name = "receivingReport", description = "list of receiving report")
    List<ReceivingReport> receivingReportList (){
        return receivingReportRepository.findAll()
    }
}
