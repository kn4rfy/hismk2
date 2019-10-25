package com.hisd3.hismk2.graphqlservices.philhealth

import com.hisd3.hismk2.domain.philhealth.RVSCode
import com.hisd3.hismk2.repository.philhealth.RVSCodesRepository
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@TypeChecked
@Component
@GraphQLApi
class RVSCodesService {
	
	@Autowired
	private RVSCodesRepository rvsCodesRepository
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "rvs_codes", description = "Get All RVS Codes")
	Set<RVSCode> getRVSCodes() {
		return rvsCodesRepository.getRVSCodes()
	}
	
	@GraphQLQuery(name = "searchRVSCodes", description = "search RVS Codes")
	Set<RVSCode> searchRVSCodes(@GraphQLArgument(name = "filter") String filter) {
		return rvsCodesRepository.searchRVSCodes(filter)
	}
}
