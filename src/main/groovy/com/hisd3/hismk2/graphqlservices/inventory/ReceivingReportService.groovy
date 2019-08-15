package com.hisd3.hismk2.graphqlservices.inventory

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.inventory.ReceivingDao
import com.hisd3.hismk2.domain.inventory.ReceivingReport
import com.hisd3.hismk2.domain.inventory.ReceivingReportItem
import com.hisd3.hismk2.repository.inventory.ReceivingReportRepository
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class ReceivingReportService {
	
	@Autowired
	ReceivingReportRepository receivingReportRepository
	
	@Autowired
	ReceivingDao receivingDao
	
	@Autowired
	ObjectMapper objectMapper
	
	@GraphQLQuery(name = "receivingReport", description = "list of receiving report")
	List<ReceivingReport> receivingReportList() {
		return receivingDao.findAll()
	}
	
	@GraphQLQuery(name = "receivingItems", description = "get list of items")
	Set<ReceivingReportItem> getItems(@GraphQLContext ReceivingReport receivingReport) {
		return receivingDao.getReceivingItems(receivingReport)
	}
	
	@GraphQLQuery(name = "getReceivingById", description = "get receiving report")
	ReceivingReport getReceivingById(@GraphQLArgument(name = "id") UUID id) {
		return receivingDao.getReceivingReport(id)
	}
	
	@GraphQLMutation
	ReceivingReport addReceivingReport(
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		def receiving = objectMapper.convertValue(fields, ReceivingReport)
		return receivingDao.save(receiving)
	}
	
}
