package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.domain.pms.Output
import com.hisd3.hismk2.repository.pms.OutputRepository
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@TypeChecked
@Component
@GraphQLApi
class OutputService {
	
	@Autowired
	private OutputRepository outputRepository
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "outputs", description = "Get all Outputs")
	List<Output> findAll() {
		return outputRepository.findAll().sort { it.entryDateTime }
	}
	
	@GraphQLQuery(name = "output", description = "Get Output By Id")
	Output findById(@GraphQLArgument(name = "id") UUID id) {
		return outputRepository.findById(id).get()
	}
	
	@GraphQLQuery(name = "outputsByCase", description = "Get all Outputs by Case Id")
	List<Output> getOutputsByCase(@GraphQLArgument(name = "caseId") UUID caseId) {
		return outputRepository.getOutputsByCase(caseId).sort { it.entryDateTime }
	}
}
