package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.dao.pms.OutputDao
import com.hisd3.hismk2.domain.pms.Output
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class OutputService {
	
	@Autowired
	OutputDao outputDao
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "outputs", description = "Get all outputs")
	List<Output> findAll() {
		return outputDao.findAll()
	}
	
	@GraphQLQuery(name = "outputsByCase", description = "Get all patient outputs by case ID")
	List<Output> getOutputsByCase(@GraphQLArgument(name = "caseId") UUID caseId) {
		return outputDao.getOutputsByCase(caseId)
	}
}
