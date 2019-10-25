package com.hisd3.hismk2.graphqlservices.philhealth

import com.hisd3.hismk2.domain.bms.ICDCode
import com.hisd3.hismk2.repository.philhealth.ICDCodesRepository
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@TypeChecked
@Component
@GraphQLApi
class ICDCodesService {
	
	@Autowired
	private ICDCodesRepository icdCodesRepository
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "icd_codes", description = "Get All ICD Codes")
	Set<ICDCode> getICDCodes() {
		return icdCodesRepository.getICDCodes()
	}
	
	@GraphQLQuery(name = "searchICDCodes", description = "search ICD Codes")
	Set<ICDCode> searchICDCodes(@GraphQLArgument(name = "filter") String filter) {
		return icdCodesRepository.searchICDCodes(filter)
	}
}
